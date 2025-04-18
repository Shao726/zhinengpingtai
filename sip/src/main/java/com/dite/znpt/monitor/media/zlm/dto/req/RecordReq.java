package com.dite.znpt.monitor.media.zlm.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 13:11
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecordReq extends StreamReq {
    /**
     * 0为hls，1为mp4
     */
    private Integer type;
    /**
     * 录像保存目录
     */
    @JSONField(name = "customized_path")
    private String customizedPath;
    /**
     * mp4录像切片时间大小,单位秒，置0则采用配置项
     */
    @JSONField(name = "max_second")
    private Integer maxSecond;
}
