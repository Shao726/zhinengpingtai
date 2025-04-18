package com.dite.znpt.monitor.domain.req;

import lombok.Data;

import java.util.List;

/**
 * @Date:2023/9/5 16:19
 * @Description: 视频服务配置新增对象
 */
@Data
public class MonitorConfigAddReq {
    private List<String> ipAddresses;
}
