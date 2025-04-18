package com.dite.znpt.monitor.sip.transmit;


import cn.hutool.json.JSONUtil;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessor;
import com.dite.znpt.monitor.sip.transmit.response.ISipResponseProcessor;
import com.dite.znpt.monitor.sip.transmit.timeout.ITimeoutProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.sip.*;
import javax.sip.header.CSeqHeader;
import javax.sip.message.Response;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: huise23
 * @Date: 2022/8/10 16:44
 * @Description:
 */
@Slf4j
@Component
public class SipProcessorFactoryImpl implements SipProcessorFactoryI {

    private static Map<String, ISipRequestProcessor> requestProcessorMap = new ConcurrentHashMap<>();

    private static Map<String, ISipResponseProcessor> responseProcessorMap = new ConcurrentHashMap<>();

    private static ITimeoutProcessor timeoutProcessor;

    public void addRequestProcessor(String method, ISipRequestProcessor processor) {
        requestProcessorMap.put(method, processor);
    }

    public void addResponseProcessor(String method, ISipResponseProcessor processor) {
        responseProcessorMap.put(method, processor);
    }

    public void addTimeoutProcessor(ITimeoutProcessor processor) {
        timeoutProcessor = processor;
    }

    @Override
    @Async
    public void processRequest(RequestEvent requestEvent) {
        log.info("requestEvent:"+ JSONUtil.toJsonStr(requestEvent.getRequest()));
        String method = requestEvent.getRequest().getMethod();
        ISipRequestProcessor sipRequestProcessor = requestProcessorMap.get(method);
        if (sipRequestProcessor == null) {
            log.warn("不支持方法{}的request", method);
            return;
        }
        sipRequestProcessor.process(requestEvent);
    }

    @Override
    @Async
    public void processResponse(ResponseEvent responseEvent) {
        Response response = responseEvent.getResponse();
        int status = response.getStatusCode();
        if ((status >= 200 && status < 300) || status == Response.UNAUTHORIZED) {
            CSeqHeader cseqHeader = (CSeqHeader) responseEvent.getResponse().getHeader(CSeqHeader.NAME);
            String method = cseqHeader.getMethod();
            ISipResponseProcessor sipResponseProcessor = responseProcessorMap.get(method);
            if (sipResponseProcessor == null) {
                log.warn("不支持方法{}的response", method);
                return;
            }
            sipResponseProcessor.process(responseEvent);
        } else if ((status >= 100) && (status < 200)) {
            // 增加其它无需回复的响应，如101、180等
        } else {
            //未完成 接收到失败的response响应！status：400,message:Bad Request
            log.warn("接收到失败的response响应！status：" + status + ",message:" + response.getReasonPhrase());
        }
    }

    @Override
    public void processTimeout(TimeoutEvent timeoutEvent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void processIOException(IOExceptionEvent exceptionEvent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void processTransactionTerminated(TransactionTerminatedEvent transactionTerminatedEvent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void processDialogTerminated(DialogTerminatedEvent dialogTerminatedEvent) {
        // TODO Auto-generated method stub
    }
}
