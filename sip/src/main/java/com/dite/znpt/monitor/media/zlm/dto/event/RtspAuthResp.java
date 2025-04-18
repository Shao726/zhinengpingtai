package com.dite.znpt.monitor.media.zlm.dto.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author: huise23
 * @Date: 2022/8/30 9:35
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class RtspAuthResp extends BaseEventResp {
    /**
     * 用户密码
     */
    private String passwd;
    /**
     * 用户密码是否已加密
     */
    private Boolean encrypted;

    public static RtspAuthResp success() {
        RtspAuthResp resp = new RtspAuthResp();
        resp.setSuccess();
        return resp.setEncrypted(false);
    }
}
