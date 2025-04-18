package com.dite.znpt.monitor.media.zlm.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 17:30
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class StreamIdReq extends BaseReq {
    /**
     * RTP的ssrc，16进制字符串或者是流的id(openRtpServer接口指定)
     */
    @JSONField(name = "stream_id")
    private String streamId;
}
