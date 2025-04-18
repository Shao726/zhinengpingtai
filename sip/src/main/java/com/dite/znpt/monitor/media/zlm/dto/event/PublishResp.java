package com.dite.znpt.monitor.media.zlm.dto.event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/30 9:27
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PublishResp extends BaseEventResp {
    /**
     * 是否转换成hls协议
     */
    @JSONField(name = "enable_hls")
    private Boolean enableHls;
    /**
     * 是否允许mp4录制
     */
    @JSONField(name = "enable_mp4")
    private Boolean enableMp4;
    /**
     * 是否转rtsp协议
     */
    @JSONField(name = "enable_rtsp")
    private Boolean enableRtsp;
    /**
     * 是否转rtmp/flv协议
     */
    @JSONField(name = "enable_rtmp")
    private Boolean enableRtmp;
    /**
     * 是否转http-ts/ws-ts协议
     */
    @JSONField(name = "enable_ts")
    private Boolean enableTs;
    /**
     * 是否转http-fmp4/ws-fmp4协议
     */
    @JSONField(name = "enable_fmp4")
    private Boolean enableFmp4;
    /**
     * 转协议时是否开启音频
     */
    @JSONField(name = "enable_audio")
    private Boolean enableAudio;
    /**
     * 转协议时，无音频是否添加静音aac音频
     */
    @JSONField(name = "add_mute_audio")
    private Boolean addMuteAudio;
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
    /**
     * 断连续推延时，单位毫秒，置空使用配置文件默认值
     */
    @JSONField(name = "continue_push_ms")
    private Long continuePushMs;

    public static PublishResp success() {
        PublishResp resp = new PublishResp();
        resp.setSuccess();
        return resp;
    }
}
