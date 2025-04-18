package com.dite.znpt.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.monitor.domain.entity.DeviceVideoChannelEntity;
import com.dite.znpt.monitor.domain.req.VideoInfoReq;
import com.dite.znpt.monitor.domain.resp.VideoInfoResp;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoChannelListResp;
import com.dite.znpt.monitor.domain.vo.video.DeviceVideoChannelResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: huise23
 * @Date: 2022/8/11 18:08
 * @Description:
 */
public interface DeviceVideoChannelMapper extends BaseMapper<DeviceVideoChannelEntity> {

    /**
     * 查询视频通道列表
     *
     * @param videoId 视频id
     * @param keyword 插叙条件
     * @return {@link List< DeviceVideoChannelListResp>}
     */
    List<DeviceVideoChannelListResp> selectDeviceVideoChannel(@Param("videoId") Long videoId, @Param("keyword") String keyword);

    /**
     * 查询所有视频通道列表
     *
     * @param keyword 插叙条件
     * @return {@link List< DeviceVideoChannelListResp>}
     */
    List<DeviceVideoChannelListResp> selectAllDeviceVideoChannel(@Param("keyword") String keyword);

    /**
     * 查询视频通道详情
     *
     * @param channelCode 通道code
     * @return {@link DeviceVideoChannelResp}
     */
    DeviceVideoChannelResp getDeviceVideoChannelDetail(@Param("channelCode") String channelCode);

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
