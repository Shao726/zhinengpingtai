package com.dite.znpt.monitor.media.zlm.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 10:51
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CloseStreamReq extends StreamReq {
    /**
     * 是否强制关闭(有人在观看是否还关闭)
     */
    private Integer force;
}
