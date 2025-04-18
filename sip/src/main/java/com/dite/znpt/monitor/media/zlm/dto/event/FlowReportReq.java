package com.dite.znpt.monitor.media.zlm.dto.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/30 9:04
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FlowReportReq extends BaseEventReq {
    /**
     * tcp链接维持时间，单位秒
     */
    private Integer duration;
    /**
     * true为播放器，false为推流器
     */
    private Boolean player;
    /**
     * 耗费上下行流量总和，单位字节
     */
    private Integer totalBytes;
}
