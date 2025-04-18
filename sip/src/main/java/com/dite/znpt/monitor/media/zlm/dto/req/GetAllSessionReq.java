package com.dite.znpt.monitor.media.zlm.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 17:21
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetAllSessionReq extends BaseReq{
    /**
     *筛选本机端口，例如筛选rtsp链接：554
     */
    @JSONField(name = "local_port")
    private Integer localPort;
    /**
     *筛选客户端ip
     */
    @JSONField(name = "peer_ip")
    private String peerIp;
}
