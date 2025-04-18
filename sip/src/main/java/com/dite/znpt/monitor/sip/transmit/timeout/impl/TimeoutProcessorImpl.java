package com.dite.znpt.monitor.sip.transmit.timeout.impl;

import com.dite.znpt.monitor.sip.transmit.SipProcessorFactoryImpl;
import com.dite.znpt.monitor.sip.transmit.timeout.ITimeoutProcessor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sip.TimeoutEvent;
import javax.sip.header.CallIdHeader;

/**
 * @author huise23
 */
@Component
public class TimeoutProcessorImpl implements InitializingBean, ITimeoutProcessor {

    @Autowired
    private SipProcessorFactoryImpl sipProcessorFactory;


    @Override
    public void afterPropertiesSet() {
        sipProcessorFactory.addTimeoutProcessor(this);
    }

    @Override
    public void process(TimeoutEvent event) {
        CallIdHeader callIdHeader = event.getClientTransaction().getDialog().getCallId();
        String callId = callIdHeader.getCallId();
        // TODO

    }
}
