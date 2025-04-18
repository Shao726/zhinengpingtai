package com.dite.znpt.monitor.media.zlm.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: huise23
 * @Date: 2022/8/29 17:24
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class IdReq extends BaseReq {
    /**
     * 客户端唯一id，可以通过getAllSession接口获取
     */
    private Long id;
}
