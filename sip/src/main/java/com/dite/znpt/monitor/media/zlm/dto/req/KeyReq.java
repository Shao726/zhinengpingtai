package com.dite.znpt.monitor.media.zlm.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 17:30
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class KeyReq extends BaseReq {
    /**
     * addStreamProxy接口返回的key
     */
    private String key;
}
