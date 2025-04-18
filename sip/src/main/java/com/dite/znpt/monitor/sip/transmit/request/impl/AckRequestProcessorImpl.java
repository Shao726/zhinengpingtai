package com.dite.znpt.monitor.sip.transmit.request.impl;

import com.dite.znpt.monitor.sip.transmit.SipProcessorFactoryImpl;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessor;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessorAbstract;
import gov.nist.javax.sip.header.CSeq;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sip.Dialog;
import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.message.Request;

/**
 * @Author: huise23
 * @Date: 2022/8/10 16:54
 * @Description:
 */
@Slf4j
@Component
public class AckRequestProcessorImpl extends ISipRequestProcessorAbstract implements InitializingBean, ISipRequestProcessor {

    private final String METHOD = "ACK";

    @Resource
    private SipProcessorFactoryImpl sipProcessorFactory;

    @Override
    public void afterPropertiesSet() {
        sipProcessorFactory.addRequestProcessor(METHOD, this);
    }


    @Override
    public void process(RequestEvent evt) {
        Request request = evt.getRequest();
        Dialog dialog = evt.getDialog();
        try {
            log.info("接收到bye请求：{}", getRootElement(evt));
            CSeq csReq = (CSeq) request.getHeader(CSeq.NAME);
            Request ackRequest = dialog.createAck(csReq.getSeqNumber());
            dialog.sendAck(ackRequest);
            log.info("send ack : " + ackRequest.toString());
        } catch (SipException | InvalidArgumentException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
