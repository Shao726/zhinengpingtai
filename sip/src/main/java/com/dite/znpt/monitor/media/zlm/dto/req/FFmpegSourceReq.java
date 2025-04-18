package com.dite.znpt.monitor.media.zlm.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 12:26
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FFmpegSourceReq extends BaseReq {
    /**
     * FFmpeg拉流地址,支持任意协议或格式(只要FFmpeg支持即可)
     */
    @JSONField(name = "src_url")
    private String srcUrl;
    /**
     * FFmpeg rtmp推流地址，一般都是推给自己，例如rtmp://127.0.0.1/live/stream_form_ffmpeg
     */
    @JSONField(name = "dst_url")
    private String dstUrl;
    /**
     * FFmpeg推流成功超时时间
     */
    @JSONField(name = "timeout_ms")
    private Integer timeoutMs;
    /**
     * 是否开启hls录制
     */
    @JSONField(name = "enable_hls")
    private Integer enableHls;
    /**
     * 是否开启mp4录制
     */
    @JSONField(name = "enable_mp4")
    private Integer enableMp4;
    /**
     * 配置文件中FFmpeg命令参数模板key(非内容)，置空则采用默认模板:ffmpeg.cmd
     */
    @JSONField(name = "ffmpeg_cmd_key")
    private String ffmpegCmdKey;
}
