package com.dite.znpt.monitor.media.zlm;

import com.dite.znpt.monitor.media.zlm.dto.MediaItem;

/**
 * @Author: huise23
 * @Date: 2022/8/30 10:39
 * @Description: 流媒体服务管理主业务
 */
public interface ZlmService {
    /**
     * 点播视频
     *
     * @param deviceCode  设备编码
     * @param channelCode 通道编码
     * @return 流信息
     */
    MediaItem play(String deviceCode, String channelCode);

    /**
     * 失败的时候释放流媒体资源
     *
     * @param deviceCode  设备编码
     * @param channelCode 通道编码
     */
    void release(String deviceCode, String channelCode);

    /**
     * 失败的时候释放流媒体资源
     *
     * @param media 流媒体信息
     */
    void release(MediaItem media);

    /**
     * 停止点播
     *
     * @param mediaServerId 流媒体服务器id,通过配置文件设置
     * @param streamId 流ID
     */
    void display(String mediaServerId, String streamId);
}
