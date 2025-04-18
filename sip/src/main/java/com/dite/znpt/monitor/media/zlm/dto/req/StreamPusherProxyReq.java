package com.dite.znpt.monitor.media.zlm.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 13:50
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StreamPusherProxyReq extends StreamReq {
    /**
     * 目标转推url，带参数需要自行url转义
     */
    @JSONField(name = "dst_url")
    private String dstUrl;
    /**
     * 转推失败重试次数，默认无限重试
     */
    @JSONField(name = "retry_count")
    private Integer retryCount;
    /**
     * rtsp拉流时，拉流方式，0：tcp，1：udp，2：组播
     */
    @JSONField(name = "rtp_type")
    private Integer rtpType;
    /**
     * 拉流超时时间，单位秒，float类型
     */
    @JSONField(name = "timeout_sec")
    private Float timeoutSec;
}
