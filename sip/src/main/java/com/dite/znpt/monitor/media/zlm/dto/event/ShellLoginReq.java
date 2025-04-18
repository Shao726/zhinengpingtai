package com.dite.znpt.monitor.media.zlm.dto.event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/30 9:37
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShellLoginReq extends BaseEventReq {
    /**
     * 终端登录用户密码
     */
    private String passwd;
    /**
     * 终端登录用户名
     */
    @JSONField(name = "user_name")
    private String userName;
}
