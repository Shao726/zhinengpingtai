package com.dite.znpt.monitor.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.PageResult;
import com.dite.znpt.domain.Result;
import com.dite.znpt.exception.ServiceException;
import com.dite.znpt.monitor.constant.IotCacheConstants;
import com.dite.znpt.monitor.constant.IotDictConstants;
import com.dite.znpt.monitor.constant.IotRespMessage;
import com.dite.znpt.monitor.constant.dict.DeviceStatus;
import com.dite.znpt.monitor.constant.dict.SipTransferMode;
import com.dite.znpt.monitor.constant.dict.StreamTransferMode;
import com.dite.znpt.monitor.domain.entity.DeviceVideoChannelEntity;
import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoEditReq;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoListResp;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoNumResp;
import com.dite.znpt.monitor.mapper.DeviceVideoMapper;
import com.dite.znpt.monitor.service.DeviceVideoChannelService;
import com.dite.znpt.monitor.service.DeviceVideoService;
import com.dite.znpt.monitor.sip.transmit.cmd.ISipDeviceCommander;
import com.dite.znpt.monitor.utils.DictUtils;
import com.dite.znpt.service.RedisService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: huise23
 * @Date: 2022/8/11 18:11
 * @Description:
 */
@Slf4j
@Service
public class DeviceVideoServiceImpl extends ServiceImpl<DeviceVideoMapper, DeviceVideoEntity> implements DeviceVideoService {

    @Resource
    private RedisService redisService;

    @Resource
    private DeviceVideoChannelService deviceVideoChannelService;

    @Resource
    private ISipDeviceCommander sipDeviceCommander;

    /**
     * 查询视频设备列表
     *
     * @param status  状态
     * @param keyword 设备编码或者设备名称
     * @return {@link List <  DeviceVideoListResp >}
     */
    @Override
    public PageResult<DeviceVideoListResp> selectDeviceVideoList(String status, String keyword, String hostAddress) {
        List<DeviceVideoListResp> deviceVideoListResps = this.baseMapper.selectDeviceVideoList(status, keyword,hostAddress);
        int total = (int) new PageInfo(deviceVideoListResps).getTotal();
        deviceVideoListResps = deviceVideoListResps.stream().peek(resp -> {
            resp.setStatusLabel(DictUtils.getDictLabel(DeviceStatus.class, resp.getStatus()));
            resp.setStreamModeLabel(DictUtils.getDictLabel(StreamTransferMode.class, resp.getStreamMode()));
            resp.setTransportLabel(DictUtils.getDictLabel(SipTransferMode.class, resp.getTransport()));
        }).collect(Collectors.toList());
        return PageResult.ok(deviceVideoListResps,total);
    }

    /**
     * 查询视频设备数量
     *
     * @param
     * @return {@link DeviceVideoNumResp}
     */
    @Override
    public DeviceVideoNumResp countDeviceVideoNum() {
        DeviceVideoNumResp deviceVideoNumResp = new DeviceVideoNumResp();
        List<DeviceVideoListResp> deviceVideoList = this.baseMapper.selectDeviceVideoList(null, null,null);
        deviceVideoNumResp.setAllDevice(deviceVideoList.stream().count());
        deviceVideoNumResp.setOnlineDevice(deviceVideoList.stream().filter(item -> IotDictConstants.IOT_DEVICE_STATUS_ONLINE.equals(item.getStatus())).count());
        deviceVideoNumResp.setOfflineDevice(deviceVideoList.stream().filter(item -> IotDictConstants.IOT_DEVICE_STATUS_OFFLINE.equals(item.getStatus())).count());
        return deviceVideoNumResp;
    }

    /**
     * 编辑视频设备信息
     *
     * @param videoId 视频设备id
     * @param req     视频设备信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editDeviceVideo(Long videoId, DeviceVideoEditReq req) {
        DeviceVideoEntity entity = this.getById(videoId);
        if (null == entity) {
            throw new ServiceException(IotRespMessage.ID_NOT_FOUND);
        }
        BeanUtil.copyProperties(req, entity);
        this.updateById(entity);
    }

    /**
     * 删除视频设备
     *
     * @param videoId 视频设备id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result removeByVideoId(Long videoId) {
        DeviceVideoEntity entity = this.getById(videoId);
        if (null == entity) {
            throw new ServiceException(IotRespMessage.ID_NOT_FOUND);
        }
        if (!DeviceStatus.OFFLINE.getValue().equals(entity.getStatus())) {
            return Result.error(IotRespMessage.DEVICE_VIDEO_CANNOT_DELETE);
        }
        Result result = Result.ok();
        List<DeviceVideoChannelEntity> list = deviceVideoChannelService.list(Wrappers.lambdaQuery(DeviceVideoChannelEntity.class).eq(DeviceVideoChannelEntity::getVideoId,videoId));
        List<Long> channelIds = list.stream().map(DeviceVideoChannelEntity::getChannelId).collect(Collectors.toList());
        if(CollectionUtil.isNotEmpty(channelIds)){
            result = deviceVideoChannelService.removeByChannelIds(channelIds);
        }
        if (HttpStatus.HTTP_OK == result.getCode()) {
            return this.removeById(videoId) ? Result.ok() : Result.error();
        } else {
            return result;
        }
    }

    @Override
    public DeviceVideoEntity getDeviceByHostAndPort(String host, int port) {
        return super.getOne(Wrappers.lambdaQuery(DeviceVideoEntity.class)
                .eq(DeviceVideoEntity::getIp, host)
                .eq(DeviceVideoEntity::getPort, port)
//                .orderByDesc(DeviceVideoEntity::getCreateTime)
                .last("limit 1"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void offline(String videoCode) {
        DeviceVideoEntity entity = this.getByCode(videoCode);
        entity.setStatus(DeviceStatus.OFFLINE.getValue());
//        entity.setUpdateTime(LocalDateTime.now());
        this.updateById(entity);
        deviceVideoChannelService.offlineByVideoId(entity.getVideoId());

    }

    /**
     * 上线设备
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void online(DeviceVideoEntity entity) {
        log.info("[设备上线] deviceId：{}->{}:{}",entity.getVideoCode(), entity.getIp(), entity.getPort());
        String deviceCacheKey = IotCacheConstants.getIotDeviceVideoKey(entity.getVideoCode());
        entity.setStatus(DeviceStatus.ONLINE.getValue());
        if(entity.getVideoId() == null){
            // 设备首次上线
            if(redisService.hasKey(deviceCacheKey)){
                // 脏数据
                redisService.deleteObject(deviceCacheKey);
            }
            this.save(entity);
        } else {
            entity.setUpdateTime(LocalDateTime.now());
            this.updateById(entity);
        }
        sipDeviceCommander.queryDeviceInfo(entity);
        sipDeviceCommander.queryCatalog(entity);
    }

    @Override
    public boolean expire(DeviceVideoEntity entity) {
        LocalDateTime expireDateTime = entity.getRegisterTime().plus(entity.getExpires(), ChronoUnit.MILLIS);
        return expireDateTime.isBefore(LocalDateTime.now());
    }

    @Override
    public DeviceVideoEntity getByCode(String videoCode) {
        return super.getOne(Wrappers.lambdaQuery(DeviceVideoEntity.class)
                .eq(DeviceVideoEntity::getVideoCode, videoCode)
                .orderByDesc(DeviceVideoEntity::getCreateTime)
                .last("limit 1"));
    }

    /**
     * 根据 videoCode 获取设备
     *
     * @param channelCode
     * @return 设备
     */
    @Override
    public DeviceVideoEntity getByChannelCode(String channelCode) {
        DeviceVideoChannelEntity channelEntity = Optional.ofNullable(deviceVideoChannelService.getByCode(channelCode)).orElseThrow(()-> new ServiceException(IotRespMessage.ID_NOT_FOUND));
        return this.getById(channelEntity.getVideoId());
    }

    /**
     * 查询设备状态
     *
     * @param videoId
     * @return
     */
    @Override
    public void queryDeviceStatus(Long videoId) {
        DeviceVideoEntity entity = Optional.ofNullable(this.getById(videoId)).orElseThrow( () -> new ServiceException(IotRespMessage.ID_NOT_FOUND));
        sipDeviceCommander.queryDeviceStatus(entity);
    }

}
