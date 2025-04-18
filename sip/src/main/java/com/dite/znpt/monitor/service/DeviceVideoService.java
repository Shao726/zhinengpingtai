package com.dite.znpt.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.PageResult;
import com.dite.znpt.domain.Result;
import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoEditReq;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoListResp;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoNumResp;

/**
 * @Author: huise23
 * @Date: 2022/8/11 18:09
 * @Description:
 */
public interface DeviceVideoService extends IService<DeviceVideoEntity> {

    /**
     * 查询视频设备列表
     *
     * @param status  状态
     * @param keyword 设备编码或者设备名称
     * @return {@link PageResult< DeviceVideoListResp>}
     */
    PageResult<DeviceVideoListResp> selectDeviceVideoList(String status, String keyword, String hostAddress);

    /**
     * 查询视频设备数量
     *
     * @return {@link DeviceVideoNumResp}
     */
    DeviceVideoNumResp countDeviceVideoNum();

    /**
     * 编辑视频设备信息
     *
     * @param videoId 视频设备id
     * @param req     视频设备信息
     * @return
     */
    void editDeviceVideo(Long videoId, DeviceVideoEditReq req);

    /**
     * 删除视频设备
     *
     * @param videoId 视频设备id
     * @return
     */
    Result removeByVideoId(Long videoId);

    /**
     * 根据端口和host查询设备
     *
     * @param host 地址
     * @param port 端口
     * @return 设备
     */
    DeviceVideoEntity getDeviceByHostAndPort(String host, int port);

    /**
     * 设备离线
     *
     * @param videoCode videoCode
     */
    void offline(String videoCode);

    /**
    * 上线设备
    * @param entity
    * @return
    */
    void online(DeviceVideoEntity entity);
    
    /**
    * 判断是否注册已经失效
    * @param entity 设备信息
    * @return {@link boolean} 
    */
    boolean expire(DeviceVideoEntity entity);

    /**
     * 根据 videoCode 获取设备
     *
     * @param videoCode videoCode
     * @return 设备
     */
    DeviceVideoEntity getByCode(String videoCode);

    /**
     * 根据 videoCode 获取设备
     *
     * @param channelCode
     * @return 设备
     */
    DeviceVideoEntity getByChannelCode(String channelCode);

    /**
     * 查询设备状态
     * @param videoId
     * @return
     */
    void queryDeviceStatus(Long videoId);

}
