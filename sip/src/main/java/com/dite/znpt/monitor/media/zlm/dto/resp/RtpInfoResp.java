package com.dite.znpt.monitor.media.zlm.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 12:32
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RtpInfoResp extends BaseResp {
    /**
     * 是否存在
     */
    private Boolean exist;
    /**
     * 推流客户端ip
     */
    @JSONField(name = "peer_ip")
    private String peerIp;
    /**
     * 客户端端口号
     */
    @JSONField(name = "peer_port")
    private Integer peerPort;
    /**
     * 本地监听的网卡ip
     */
    @JSONField(name = "local_ip")
    private String localIp;
    /**
     * 本地监听端口号
     */
    @JSONField(name = "local_port")
    private Integer localPort;
}
