package com.dite.znpt.monitor.media.zlm.dto.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author: huise23
 * @Date: 2022/8/30 9:15
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class HttpAccessResp extends BaseEventResp {
    /**
     * 该客户端能访问或被禁止的顶端目录，如果为空字符串，则表述为当前目录
     */
    private String path;
    /**
     * 本次授权结果的有效期，单位秒
     */
    private Integer second;
    /**
     * 服务器id,通过配置文件设置
     */
    private String mediaServerId;

    public static HttpAccessResp success() {
        HttpAccessResp resp = new HttpAccessResp();
        resp.setSuccess();
        return resp.setSecond(600).setPath("");
    }
}
