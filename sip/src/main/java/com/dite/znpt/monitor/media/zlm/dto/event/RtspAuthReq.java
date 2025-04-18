package com.dite.znpt.monitor.media.zlm.dto.event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/30 9:35
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RtspAuthReq extends BaseEventReq {
    /**
     * 请求的密码是否必须为明文(base64鉴权需要明文密码)
     */
    @JSONField(name = "must_no_encrypt")
    private Boolean mustNoEncrypt;
    /**
     * rtsp播放鉴权加密realm
     */
    private String realm;
    /**
     * 播放用户名
     */
    @JSONField(name = "user_name")
    private String userName;

}
