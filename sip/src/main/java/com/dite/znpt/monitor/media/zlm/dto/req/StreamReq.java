package com.dite.znpt.monitor.media.zlm.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author: huise23
 * @Date: 2022/8/29 13:05
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class StreamReq extends BaseReq {
    /**
     * 协议，例如 rtsp或rtmp
     */
    private String schema;
    /**
     * 筛选虚拟主机，例如__defaultVhost__
     */
    private String vhost;
    /**
     * 筛选应用名，例如 live
     */
    private String app;
    /**
     * 筛选流id，例如 test
     */
    private String stream;
}
