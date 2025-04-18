package com.dite.znpt.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.PageResult;
import com.dite.znpt.domain.Result;
import com.dite.znpt.monitor.domain.entity.DeviceVideoChannelEntity;
import com.dite.znpt.monitor.domain.req.VideoInfoReq;
import com.dite.znpt.monitor.domain.resp.VideoInfoResp;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoChannelEditReq;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoChannelListResp;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoChannelResp;
import com.dite.znpt.monitor.domain.vo.video.VideoPayResp;

import java.util.List;

/**
 * @Author: huise23
 * @Date: 2022/8/11 18:10
 * @Description:
 */
public interface DeviceVideoChannelService extends IService<DeviceVideoChannelEntity> {
    /**
     * 查询视频通道列表
     *
     * @param videoId 视频id
     * @param keyword 查询条件
     * @return {@link PageResult <  DeviceVideoChannelListResp >}
     */
    PageResult<DeviceVideoChannelListResp> selectDeviceVideoChannel(Long videoId, String keyword);

     /**
     * 查询所有视频通道列表
     *
     * @param keyword 查询条件
     * @return {@link PageResult< DeviceVideoChannelListResp>}
     */
    PageResult<DeviceVideoChannelListResp> selectAllDeviceVideoChannel(String keyword);

    /**
     * 查询视频通道详情
     *
     * @param channelCode
     * @return {@link DeviceVideoChannelResp}
     */
    DeviceVideoChannelResp getDeviceVideoChannelDetail(String channelCode);

    /**
     * 编辑视频设备通道
     *
     * @param channelId
     * @param req
     * @return
     */
    void editDeviceVideoChannel(Long channelId, DeviceVideoChannelEditReq req);

    /**
     * 根据通道id删除通道信息
     *
     * @param channelIds
     * @return {@link boolean}
     */
    Result removeByChannelIds(List<Long> channelIds);

    /**
     * 根据通道编码查询通道信息
     *
     * @param channelCode 通道编码
     * @return 通道信息
     */
    DeviceVideoChannelEntity getByCode(String channelCode);
    
    /**
    * 根据设备id查询设备通道
    * @param videoId 设备id
    * @return {@link List< DeviceVideoChannelEntity>}
    */
    List<DeviceVideoChannelEntity> selectDeviceVideoChannelByVideoCode(Long videoId);

    /**
     * 播放直播视频
     *
     * @param channelCode
     * @return
     */
    VideoPayResp play(String channelCode);

    /**
     * 是否在线
     *
     * @param channelCode
     * @return
     */
    boolean isOnline(String channelCode);

    /**
     * 停止播放直播
     *
     * @param channelCode
     * @return
     */
    void stop(String channelCode);

    /**
    * 下线视频设备下的所有通道
    * @param videoId
    * @return
    */
    void offlineByVideoId(Long videoId);

    /**
     * 查询通道及视频信息
     *
     * @param videoInfoReq 查询参数
     * @return {@link VideoInfoResp }
     * @author huise23
     * @since 2024-12-03 13:54:52
     */
    List<VideoInfoResp> selectVideoInfoList(VideoInfoReq videoInfoReq);

}
