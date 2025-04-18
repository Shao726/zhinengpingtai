package com.dite.znpt.monitor.media.zlm.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 11:59
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CloseStreamResp extends BaseResp {
    /**
     * 筛选命中的流个数
     */
    @JSONField(name = "count_hit")
    private Integer countHit;
    /**
     * 被关闭的流个数，可能小于count_hit
     */
    @JSONField(name = "count_closed")
    private Integer countClosed;
}
