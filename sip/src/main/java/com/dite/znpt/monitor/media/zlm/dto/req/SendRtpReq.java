package com.dite.znpt.monitor.media.zlm.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 13:23
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SendRtpReq extends StreamReq {
    /**
     * 推流的rtp的ssrc,指定不同的ssrc可以同时推流到多个服务器
     */
    private String ssrc;
    /**
     * 目标ip或域名
     */
    @JSONField(name = "dst_url")
    private String dstUrl;
    /**
     * 目标端口
     */
    @JSONField(name = "dst_port")
    private Integer dstPort;
    /**
     * 是否为udp模式,否则为tcp模式
     */
    @JSONField(name = "is_udp")
    private Integer isUdp;
    /**
     * 使用的本机端口，为0或不传时默认为随机端口
     */
    @JSONField(name = "src_port")
    private Integer srcPort;
    /**
     * 使用的本机端口，为0或不传时默认为随机端口
     */
    private Integer pt;
    /**
     * 发送时，rtp的负载类型。为1时，负载为ps；为0时，为es；不传时默认为1
     */
    @JSONField(name = "use_ps")
    private Integer usePs;
    /**
     * 当use_ps 为0时，有效。为1时，发送音频；为0时，发送视频；不传时默认为0
     */
    @JSONField(name = "only_audio")
    private Integer onlyAudio;
}
