package com.dite.znpt.monitor.media.zlm.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 13:14
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SnapReq extends BaseReq {
    /**
     * 需要截图的url，可以是本机的，也可以是远程主机的
     */
    private String url;
    /**
     * 截图失败超时时间，防止FFmpeg一直等待截图
     */
    @JSONField(name = "timeout_sec")
    private Integer timeoutSec;
    /**
     * 截图的过期时间，该时间内产生的截图都会作为缓存返回
     */
    @JSONField(name = "expire_sec")
    private Integer expireSec;
}
