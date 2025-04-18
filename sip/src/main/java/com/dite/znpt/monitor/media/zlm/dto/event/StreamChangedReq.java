package com.dite.znpt.monitor.media.zlm.dto.event;

import com.dite.znpt.monitor.media.zlm.dto.resp.MediaResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/30 9:38
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StreamChangedReq extends MediaResp {
    /**
     * 流注册或注销
     */
    private Boolean regist;
}
