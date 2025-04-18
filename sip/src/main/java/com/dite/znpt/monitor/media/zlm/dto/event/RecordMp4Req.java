package com.dite.znpt.monitor.media.zlm.dto.event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/30 9:32
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecordMp4Req extends BaseEventReq {
    /**
     * 文件名
     */
    @JSONField(name = "file_name")
    private String fileName;
    /**
     * 文件绝对路径
     */
    @JSONField(name = "file_path")
    private String filePath;
    /**
     * 文件大小，单位字节
     */
    @JSONField(name = "file_size")
    private Integer fileSize;
    /**
     * 文件所在目录路径
     */
    private String folder;
    /**
     * 开始录制时间戳
     */
    @JSONField(name = "start_time")
    private Integer startTime;
    /**
     * 录制时长，单位秒
     */
    @JSONField(name = "time_len")
    private Integer timeLen;
    /**
     * http/rtsp/rtmp点播相对url路径
     */
    private String url;
}
