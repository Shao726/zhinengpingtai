package com.dite.znpt.monitor.media.zlm.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: huise23
 * @Date: 2022/8/29 12:04
 * @Description:
 */
@Data
public class SessionResp {
    /**
     * 该tcp链接唯一id
     */
    private Long id;
    /**
     * 本机网卡ip
     */
    @JSONField(name = "local_ip")
    private String localIp;
    /**
     * 本机端口号	(这是个rtmp播放器或推流器)
     */
    @JSONField(name = "local_port")
    private Integer localPort;
    /**
     * 客户端ip
     */
    @JSONField(name = "peer_ip")
    private String peerIp;
    /**
     * 客户端端口号
     */
    @JSONField(name = "peer_port")
    private Integer peerPort;
    /**
     * 客户端TCPSession typeid
     */
    private String typeid;
}
