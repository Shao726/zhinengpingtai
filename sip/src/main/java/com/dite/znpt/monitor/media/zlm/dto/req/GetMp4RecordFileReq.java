package com.dite.znpt.monitor.media.zlm.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 13:04
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetMp4RecordFileReq extends StreamReq {
    /**
     * 流的录像日期，格式为2020-02-01,如果不是完整的日期，那么是搜索录像文件夹列表，否则搜索对应日期下的mp4文件列表
     */
    private String period;
    /**
     * 自定义搜索路径，与startRecord方法中的customized_path一样，默认为配置文件的路径
     */
    @JSONField(name = "customized_path")
    private String customizedPath;
}
