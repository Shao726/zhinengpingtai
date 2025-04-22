package com.dite.znpt.monitor.media.zlm.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: huise23
 * @Date: 2022/8/29 11:25
 * @Description:
 */
@Data
public class Track implements Serializable {
    private static final long serialVersionUID = 5317048895056912057L;
    /**
     * 音频通道数
     */
    private Integer channels;
    /**
     * H264 = 0, H265 = 1, AAC = 2, G711A = 3, G711U = 4
     */
    @JSONField(name = "codec_id")
    private Integer codecId;
    /**
     * 编码类型名称
     */
    @JSONField(name = "codec_id_name")
    private String codecIdName;
    /**
     * Video = 0, Audio = 1
     */
    @JSONField(name = "codec_type")
    private Integer codecType;
    /**
     * 轨道是否准备就绪
     */
    private Boolean ready;
    /**
     * 音频采样位数
     */
    @JSONField(name = "sample_bit")
    private Integer sampleBit;
    /**
     * 音频采样率
     */
    @JSONField(name = "sample_rate")
    private Integer sampleRate;
    /**
     * 视频fps
     */
    private Integer fps;
    /**
     * 视频高
     */
    private Integer height;
    /**
     * 视频宽
     */
    private Integer width;
}
