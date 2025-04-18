package com.dite.znpt.monitor.sip;

import com.dite.znpt.monitor.sip.config.SipConfig;
import com.dite.znpt.monitor.sip.transmit.SipProcessorFactoryI;
import gov.nist.javax.sip.SipProviderImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sip.*;
import java.util.Properties;
import java.util.TooManyListenersException;

/**
 * @Author: huise23
 * @Date: 2022/8/10 16:20
 * @Description:
 */
@Slf4j
@Component
public class SipLayer {

    @Resource
    private SipConfig sipConfig;

    @Resource
    private SipProcessorFactoryI sipProcessorFactory;

    private SipStack sipStack;

    private SipFactory sipFactory;

    @PostConstruct
    public void initLogging() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @Bean("sipFactory")
    SipFactory createSipFactory() {
        sipFactory = SipFactory.getInstance();
        sipFactory.setPathName("gov.nist");
        return sipFactory;
    }

    @Bean("sipStack")
    @DependsOn({"sipFactory"})
    SipStack createSipStack() throws PeerUnavailableException {
        Properties properties = new Properties();
        properties.setProperty("javax.sip.STACK_NAME", "GB28181_SIP");
        properties.setProperty("gov.nist.javax.sip.PATH_NAME", "gov.nist");
        properties.setProperty("javax.sip.IP_ADDRESS", sipConfig.getIp());
        /**
         * 完整配置参考 gov.nist.javax.sip.SipStackImpl，需要下载源码
         * gov/nist/javax/sip/SipStackImpl.class
         */
        if (log.isDebugEnabled()) {
            properties.setProperty("gov.nist.javax.sip.LOG_MESSAGE_CONTENT", "true");
        }
        // 接收所有notify请求，即使没有订阅
        properties.setProperty("gov.nist.javax.sip.DELIVER_UNSOLICITED_NOTIFY", "true");
        // 为_NULL _对话框传递_终止的_事件
        properties.setProperty("gov.nist.javax.sip.DELIVER_TERMINATED_EVENT_FOR_NULL_DIALOG", "true");
        // 会话清理策略
        properties.setProperty("gov.nist.javax.sip.RELEASE_REFERENCES_STRATEGY", "Normal");
        // 处理由该服务器处理的基于底层TCP的保持生存超时
        properties.setProperty("gov.nist.javax.sip.RELIABLE_CONNECTION_KEEP_ALIVE_TIMEOUT", "60");

        /**
         * sip_server_log.log 和 sip_debug_log.log public static final int TRACE_NONE =
         * 0; public static final int TRACE_MESSAGES = 16; public static final int
         * TRACE_EXCEPTION = 17; public static final int TRACE_DEBUG = 32;
         */
        if (log.isDebugEnabled()) {
            properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "DEBUG");
        }
        properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "INFO");
//        properties.setProperty("gov.nist.javax.sip.STACK_LOGGER", "gov.nist.core.CommonLoggerSlf4j"); // 通过 SLF4J 桥接
        sipStack = sipFactory.createSipStack(properties);

        return sipStack;
    }

    @Bean(name = "tcpSipProvider")
    @DependsOn("sipStack")
    SipProviderImpl startTcpListener() throws InvalidArgumentException, TransportNotSupportedException, ObjectInUseException, TooManyListenersException {
        ListeningPoint tcpListeningPoint  = sipStack.createListeningPoint(sipConfig.getIp(), sipConfig.getPort(), "TCP");
        SipProviderImpl tcpSipProvider = (SipProviderImpl)sipStack.createSipProvider(tcpListeningPoint);
        tcpSipProvider.setDialogErrorsAutomaticallyHandled();
        tcpSipProvider.addSipListener(sipProcessorFactory);
        log.info("[Sip Server] TCP 启动成功 {}:{}", sipConfig.getIp(), sipConfig.getPort());
        return tcpSipProvider;
    }

    @Bean(name = "udpSipProvider")
    @DependsOn("sipStack")
    SipProviderImpl startUdpListener() throws InvalidArgumentException, TransportNotSupportedException, ObjectInUseException, TooManyListenersException {
        ListeningPoint udpListeningPoint = sipStack.createListeningPoint(sipConfig.getIp(), sipConfig.getPort(), "UDP");
        SipProviderImpl udpSipProvider = (SipProviderImpl)sipStack.createSipProvider(udpListeningPoint);
        udpSipProvider.addSipListener(sipProcessorFactory);
        log.info("[Sip Server] UDP 启动成功 {}:{}", sipConfig.getIp(), sipConfig.getPort());
        return udpSipProvider;
    }

}
