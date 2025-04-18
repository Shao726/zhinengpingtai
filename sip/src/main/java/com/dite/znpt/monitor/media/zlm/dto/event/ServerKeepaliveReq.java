package com.dite.znpt.monitor.media.zlm.dto.event;

import com.dite.znpt.monitor.media.zlm.dto.resp.StatisticResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/30 9:43
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerKeepaliveReq extends BaseEventReq {
    private StatisticResp data;
}
