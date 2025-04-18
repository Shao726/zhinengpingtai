package com.dite.znpt.monitor.media.zlm.dto.resp;

import lombok.Data;



/**
 * @Author: huise23
 * @Date: 2022/8/29 10:40
 * @Description: 各epoll(或select)线程负载以及延时
 */
@Data
public class ThreadsLoadResp {
    /**
     * 该线程延时
     */
    private Integer delay;
    /**
     * 该线程负载
     */
    private Integer load;
}
