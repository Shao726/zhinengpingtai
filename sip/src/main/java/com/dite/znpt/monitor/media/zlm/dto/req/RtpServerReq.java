package com.dite.znpt.monitor.media.zlm.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: huise23
 * @Date: 2022/8/29 13:17
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RtpServerReq extends BaseReq {
    /**
     * 接收端口，0则为随机端口
     */
    private Integer port;
    /**
     * 启用UDP监听的同时是否监听TCP端口
     */
    @JSONField(name = "enable_tcp")
    private Integer enableTcp;
    /**
     * 该端口绑定的流ID，该端口只能创建这一个流(而不是根据ssrc创建多个)
     */
    @JSONField(name = "stream_id")
    private String streamId;

    public RtpServerReq(Integer port, Integer enableTcp, String streamId) {
        this.port = port;
        this.enableTcp = enableTcp;
        this.streamId = streamId;
    }

    public RtpServerReq(Integer enableTcp, String streamId) {
        this(0, enableTcp, streamId);
    }

    public RtpServerReq(String streamId) {
        this(1, streamId);
    }
}
