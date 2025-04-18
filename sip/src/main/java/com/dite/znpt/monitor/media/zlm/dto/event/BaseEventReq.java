package com.dite.znpt.monitor.media.zlm.dto.event;

import lombok.Data;

/**
 * @Author: huise23
 * @Date: 2022/8/30 9:24
 * @Description:
 */
@Data
public class BaseEventReq {
    /**
     * 服务器id,通过配置文件设置
     */
    private String mediaServerId;
    /**
     * 流应用名
     */
    private String app;
    /**
     * TCP链接唯一ID
     */
    private String id;
    /**
     * 播放器ip
     */
    private String ip;
    /**
     * 播放url参数
     */
    private String params;
    /**
     * 播放器端口号
     */
    private Integer port;
    /**
     * 播放的协议，可能是rtsp、rtmp、http
     */
    private String schema;
    /**
     * 流ID
     */
    private String stream;
    /**
     * 流虚拟主机
     */
    private String vhost;
}
