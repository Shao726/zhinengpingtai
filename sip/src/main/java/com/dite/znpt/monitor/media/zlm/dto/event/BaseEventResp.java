package com.dite.znpt.monitor.media.zlm.dto.event;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: huise23
 * @Date: 2022/8/29 10:33
 * @Description: 请求响应基类
 */
@Data
@Accessors(chain = true)
public class BaseEventResp {
    /**
     * code == 0时代表完全成功
     */
    private Integer code;
    /**
     * 失败提示
     */
    private String msg;
    /**
     * 失败具体原因
     */
    private String err;
    /**
     * 该rtsp流是否需要rtsp专有鉴权，空字符串代码不需要鉴权
     */
    private String realm;
    /**
     * 是否关闭推流或拉流
     */
    private Boolean close;

    public BaseEventResp setSuccess(){
      return this.setCode(0).setMsg("success").setErr("");
    }

    public static BaseEventResp success() {
        return new BaseEventResp().setSuccess();
    }
}
