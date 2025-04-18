package com.dite.znpt.monitor.sip.transmit.response;


import javax.sip.ResponseEvent;

/**
 * @Author: huise23
 * @Date: 2022/8/10 16:47
 * @Description:
 */
public interface ISipResponseProcessor {
    void process(ResponseEvent evt);
}
