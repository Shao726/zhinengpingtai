package com.dite.znpt.monitor.media.zlm.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 12:19
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StreamProxyReq extends StreamReq {
    /**
     * 拉流地址，例如rtmp://live.hkstv.hk.lxdns.com/live/hks2	Y
     */
    private String url;
    /**
     * 拉流重试次数，默认为-1无限重试
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
    private Integer timeoutSec;
    /**
     * 是否转换成hls协议
     */
    @JSONField(name = "enable_hls")
    private Integer enableHls;
    /**
     * 是否允许mp4录制
     */
    @JSONField(name = "enable_mp4")
    private Integer enableMp4;
    /**
     * 是否转rtsp协议
     */
    @JSONField(name = "enable_rtsp")
    private Integer enableRtsp;
    /**
     * 是否转rtmp/flv协议
     */
    @JSONField(name = "enable_rtmp")
    private Integer enableRtmp;
    /**
     * 是否转http-ts/ws-ts协议
     */
    @JSONField(name = "enable_ts")
    private Integer enableTs;
    /**
     * 是否转http-fmp4/ws-fmp4协议
     */
    @JSONField(name = "enable_fmp4")
    private Integer enableFmp4;
    /**
     * 转协议时是否开启音频
     */
    @JSONField(name = "enable_audio")
    private Integer enableAudio;
    /**
     * 转协议时，无音频是否添加静音aac音频
     */
    @JSONField(name = "add_mute_audio")
    private Integer addMuteAudio;
    /**
     * mp4录制文件保存根目录，置空使用默认
     */
    @JSONField(name = "mp4_save_path")
    private String mp4SavePath;
    /**
     * mp4录制切片大小，单位秒
     */
    @JSONField(name = "mp4_max_second")
    private Integer mp4MaxSecond;
    /**
     * hls文件保存保存根目录，置空使用默认
     */
    @JSONField(name = "hls_save_path")
    private String hlsSavePath;
}
