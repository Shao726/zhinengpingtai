package com.dite.znpt.monitor.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.PageResult;
import com.dite.znpt.domain.Result;
import com.dite.znpt.exception.ServiceException;
import com.dite.znpt.monitor.constant.IotRespMessage;
import com.dite.znpt.monitor.constant.dict.CameraType;
import com.dite.znpt.monitor.constant.dict.DeviceStatus;
import com.dite.znpt.monitor.constant.dict.YesOrNo;
import com.dite.znpt.monitor.domain.entity.DeviceVideoChannelEntity;
import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.domain.req.VideoInfoReq;
import com.dite.znpt.monitor.domain.resp.VideoInfoResp;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoChannelEditReq;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoChannelListResp;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoChannelResp;
import com.dite.znpt.monitor.domain.vo.video.VideoPayResp;
import com.dite.znpt.monitor.mapper.DeviceVideoChannelMapper;
import com.dite.znpt.monitor.media.zlm.ZlmApi;
import com.dite.znpt.monitor.media.zlm.ZlmService;
import com.dite.znpt.monitor.media.zlm.cache.MediaServerChannelCache;
import com.dite.znpt.monitor.media.zlm.dto.MediaItem;
import com.dite.znpt.monitor.media.zlm.dto.req.StreamReq;
import com.dite.znpt.monitor.media.zlm.dto.resp.MediaResp;
import com.dite.znpt.monitor.service.DeviceVideoChannelService;
import com.dite.znpt.monitor.service.DeviceVideoService;
import com.dite.znpt.monitor.sip.config.SipConfig;
import com.dite.znpt.monitor.sip.transmit.cmd.ISipDeviceCommander;
import com.dite.znpt.monitor.utils.DictUtils;
import com.dite.znpt.util.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: huise23
 * @Date: 2022/8/11 18:10
 * @Description:
 */
@Service
public class DeviceVideoChannelServiceImpl extends ServiceImpl<DeviceVideoChannelMapper, DeviceVideoChannelEntity> implements DeviceVideoChannelService {

    @Resource
    private MediaServerChannelCache channelCache;

    @Resource
    private DeviceVideoService deviceVideoService;

    @Resource
    private ISipDeviceCommander sipDeviceCommander;

    @Resource
    private ZlmService zlmService;

    @Resource
    private ZlmApi zlmApi;

    @Resource
    private SipConfig sipConfig;

    /**
     * 查询视频通道列表
     *
     * @param videoId 视频id
     * @param keyword 查询条件
     * @return {@link PageResult <  DeviceVideoChannelListResp >}
     */
    @Override
    public PageResult<DeviceVideoChannelListResp> selectDeviceVideoChannel(Long videoId, String keyword) {
        PageUtil.startPage();
        List<DeviceVideoChannelListResp> list = this.baseMapper.selectDeviceVideoChannel(videoId, keyword);
        return buildPageResult(list);
    }

    /**
     * 查询所有视频通道列表
     *
     * @param keyword 查询条件
     * @return {@link PageResult< DeviceVideoChannelListResp>}
     */
    @Override
    public PageResult<DeviceVideoChannelListResp> selectAllDeviceVideoChannel(String keyword) {
        PageUtil.startPage();
        List<DeviceVideoChannelListResp> list = this.baseMapper.selectAllDeviceVideoChannel(keyword);
        return buildPageResult(list);
    }

    private PageResult<DeviceVideoChannelListResp> buildPageResult(List<DeviceVideoChannelListResp> list) {
        if (CollectionUtil.isEmpty(list)) {
            return PageResult.ok(list, 0);
        }
        long total = new PageInfo<>(list).getTotal();
        list.stream().peek(resp -> {
            resp.setCameraTypeLabel(DictUtils.getDictLabel(CameraType.class, resp.getCameraType()));
            resp.setPtzControlLabel(DictUtils.getDictLabel(YesOrNo.class, resp.getPtzControl()));
            resp.setStatusLabel(DictUtils.getDictLabel(DeviceStatus.class, resp.getStatus()));
        }).collect(Collectors.toList());
        return PageResult.ok(list, total);
    }

    /**
     * 查询视频通道详情
     *
     * @param channelCode
     * @return {@link DeviceVideoChannelResp}
     */
    @Override
    public DeviceVideoChannelResp getDeviceVideoChannelDetail(String channelCode) {
        return this.baseMapper.getDeviceVideoChannelDetail(channelCode);
    }

    /**
     * 编辑视频设备通道
     *
     * @param channelId
     * @param req
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editDeviceVideoChannel(Long channelId, DeviceVideoChannelEditReq req) {
        DeviceVideoChannelEntity entity = this.getById(channelId);
        BeanUtil.copyProperties(req, entity);
        entity.setUpdateTime(LocalDateTime.now());
        this.updateById(entity);
    }

    /**
     * 根据通道id删除通道信息
     *
     * @param channelIds
     * @return {@link boolean}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result removeByChannelIds(List<Long> channelIds) {
        List<DeviceVideoChannelEntity> list = this.listByIds(channelIds);
        List<Long> ids = list.stream().map(DeviceVideoChannelEntity::getChannelId).collect(Collectors.toList());
        if (ids.size() == list.size()) {
            return Result.ok(this.removeByIds(ids));
        } else {
            return Result.error(IotRespMessage.DEVICE_VIDEO_CANNOT_DELETE);
        }
    }

    @Override
    public DeviceVideoChannelEntity getByCode(String channelCode) {
        return super.getOne(Wrappers.lambdaQuery(DeviceVideoChannelEntity.class)
                .eq(DeviceVideoChannelEntity::getChannelCode, channelCode)
                .orderByDesc(DeviceVideoChannelEntity::getCreateTime)
                .last("limit 1"));
    }

    /**
     * 根据设备国标编码查询设备通道
     *
     * @param videoId 设备id
     * @return {@link List< DeviceVideoChannelEntity>}
     */
    @Override
    public List<DeviceVideoChannelEntity> selectDeviceVideoChannelByVideoCode(Long videoId) {
        return this.list(Wrappers.lambdaQuery(DeviceVideoChannelEntity.class).eq(DeviceVideoChannelEntity::getVideoId, videoId));
    }

    /**
     * 播放视频
     *
     * @param channelCode
     * @return
     */
    @Override
    public VideoPayResp play(String channelCode) {
        DeviceVideoChannelEntity channelEntity = Optional.ofNullable(this.getByCode(channelCode)).orElseThrow(() -> new ServiceException(IotRespMessage.ID_NOT_FOUND));
        DeviceVideoEntity videoEntity = Optional.ofNullable(deviceVideoService.getById(channelEntity.getVideoId())).orElseThrow(() -> new ServiceException(IotRespMessage.ID_NOT_FOUND));
        MediaItem mediaItem = zlmService.play(videoEntity.getVideoCode(), channelEntity.getChannelCode());
        if (!mediaItem.getIsCache()) {
            sipDeviceCommander.playStreamCmd(videoEntity, channelEntity.getChannelCode(), mediaItem.getSsrc(), mediaItem.getRtpPort(), mediaItem.getServer().getApiHost());
        }
        return VideoPayResp.builder()
                .mediaType(sipConfig.getMediaType())
                .streamMediaFormatList(mediaItem.getFormatList(sipConfig.getMediaRouter()))
                .build();
    }

    /**
     * 是否在线
     *
     * @param channelCode
     * @return
     */
    @Override
    public boolean isOnline(String channelCode) {
        DeviceVideoChannelEntity channelEntity = this.getByCode(channelCode);
        return Objects.nonNull(channelEntity) && channelEntity.getStatus().equals(DeviceStatus.ONLINE.getValue());
    }

    /**
     * 停止播放直播
     *
     * @param channelCode
     * @return
     */
    @Override
    public void stop(String channelCode) {
        DeviceVideoChannelEntity channelEntity = Optional.ofNullable(this.getByCode(channelCode)).orElseThrow(() -> new ServiceException(IotRespMessage.ID_NOT_FOUND));
        DeviceVideoEntity videoEntity = Optional.ofNullable(deviceVideoService.getById(channelEntity.getVideoId())).orElseThrow(() -> new ServiceException(IotRespMessage.ID_NOT_FOUND));
        MediaItem mediaItem = channelCache.get(videoEntity.getVideoCode(), channelEntity.getChannelCode());
        if (null != mediaItem) {
            List<MediaResp> list = zlmApi.getMediaList(mediaItem.getServer(), new StreamReq().setStream(mediaItem.getStreamId()));
            if (CollectionUtil.isNotEmpty(list) && list.get(0).getTotalReaderCount() <= 1) {
                // 当只有一个人观看时，想设备发送停止推流命令
                sipDeviceCommander.stopStreamCmd(mediaItem.getSsrc());
            }
            zlmService.release(videoEntity.getVideoCode(), channelEntity.getChannelCode());
        }
    }

    /**
     * 下线视频设备下的所有通道
     *
     * @param videoId
     * @return
     */
    @Override
    public void offlineByVideoId(Long videoId) {
        List<DeviceVideoChannelEntity> list = this.list(Wrappers.lambdaQuery(DeviceVideoChannelEntity.class).eq(DeviceVideoChannelEntity::getVideoId, videoId));
        if (ObjectUtil.isNotNull(list)) {
            this.updateBatchById(
                    list.stream().peek(entity -> {
                        entity.setUpdateTime(LocalDateTime.now());
                        entity.setStatus(DeviceStatus.OFFLINE.getValue());
                    }).collect(Collectors.toList())
            );
        }
    }

    /**
     * 查询通道及视频信息
     *
     * @param videoInfoReq 查询参数
     * @return {@link VideoInfoResp }
     * @author huise23
     * @since 2024-12-03 13:54:52
     */
    @Override
    public List<VideoInfoResp> selectVideoInfoList(VideoInfoReq videoInfoReq) {
        return this.baseMapper.selectVideoInfoList(videoInfoReq);
    }

}
