package com.dite.znpt.monitor.media.zlm.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: huise23
 * @Date: 2022/8/29 11:20
 * @Description:
 */
@Data
public class OriginSock implements Serializable {
    private static final long serialVersionUID = 5628294142872524316L;

    private String identifier;
    @JSONField(name = "local_ip")
    private String localIp;
    @JSONField(name = "local_port")
    private Integer localPort;
    @JSONField(name = "peer_ip")
    private String peerIp;
    @JSONField(name = "peer_port")
    private Integer peerPort;
}
