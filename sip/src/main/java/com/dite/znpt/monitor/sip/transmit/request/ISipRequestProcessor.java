package com.dite.znpt.monitor.sip.transmit.request;

import javax.sip.RequestEvent;

/**
 * @Author: huise23
 * @Date: 2022/8/10 16:46
 * @Description:
 */
public interface ISipRequestProcessor {
    void process(RequestEvent event);
}
