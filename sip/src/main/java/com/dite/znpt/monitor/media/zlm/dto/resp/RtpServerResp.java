package com.dite.znpt.monitor.media.zlm.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: huise23
 * @Date: 2022/8/29 13:22
 * @Description:
 */
@Data
public class RtpServerResp {
    /**
     * 绑定的端口号
     */
    private Integer port;
    /**
     * 绑定的流ID
     */
    @JSONField(name = "stream_id")
    private String streamId;
}
