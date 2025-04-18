package com.dite.znpt.monitor.sip.transmit.request.impl;

import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.service.DeviceVideoService;
import com.dite.znpt.monitor.sip.transmit.SipProcessorFactoryImpl;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessor;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessorAbstract;
import com.dite.znpt.monitor.sip.transmit.request.impl.message.IMessageHandler;
import com.dite.znpt.monitor.sip.utils.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sip.RequestEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: huise23
 * @Date: 2022/8/29 18:43
 * @Description:
 */
@Slf4j
@Component
public class MessageRequestProcessorImpl  extends ISipRequestProcessorAbstract implements InitializingBean, ISipRequestProcessor {

    private final String METHOD = "MESSAGE";

    public Map<String, IMessageHandler> messageHandlerMap = new ConcurrentHashMap<>();

    public void addHandler(String cmdType, IMessageHandler messageHandler) {
        messageHandlerMap.put(cmdType, messageHandler);
    }

    @Resource
    private SipProcessorFactoryImpl sipProcessorFactory;

    @Resource
    private DeviceVideoService deviceVideoService;

    @Override
    public void afterPropertiesSet() {
        sipProcessorFactory.addRequestProcessor(METHOD, this);
    }

    @Override
    public void process(RequestEvent event) {
        try {
            Element rootElement = getRootElement(event);
            String cmd = XmlUtil.getText(rootElement,"CmdType");
            log.info("接收到message请求：{}", cmd);
            IMessageHandler messageHandler = messageHandlerMap.get(cmd);
            if(null == messageHandler){
                log.info("不支持的消息类型:{}", cmd);
                return;
            }
            String videoCode = rootElement.element("DeviceID").getText();
            DeviceVideoEntity entity = deviceVideoService.getByCode(videoCode);
            if(null == entity){
                log.info("设备未注册，国标编码:{}", videoCode);
                return;
            }
            messageHandler.process(entity, event);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }



}
