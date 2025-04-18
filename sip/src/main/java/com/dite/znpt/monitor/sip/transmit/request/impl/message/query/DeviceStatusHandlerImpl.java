package com.dite.znpt.monitor.sip.transmit.request.impl.message.query;

import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.service.DeviceVideoService;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessorAbstract;
import com.dite.znpt.monitor.sip.transmit.request.impl.MessageRequestProcessorImpl;
import com.dite.znpt.monitor.sip.transmit.request.impl.message.IMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.message.Response;
import java.text.ParseException;
import java.util.Objects;

/**
 * @Author: huise23
 * @Date: 2022/9/1 13:34
 * @Description:
 */
@Slf4j
@Component
public class DeviceStatusHandlerImpl extends ISipRequestProcessorAbstract implements InitializingBean, IMessageHandler {

    private final static String CMD_TYPE = "DeviceStatus";

    @Resource
    private MessageRequestProcessorImpl messageRequestProcessor;

    @Override
    public void afterPropertiesSet() {
        messageRequestProcessor.addHandler(CMD_TYPE, this);
    }


    @Resource
    private DeviceVideoService deviceVideoService;
    /**
     * 处理消息
     *
     * @param evt
     * @param entity
     * @return
     */
    @Override
    public void process(DeviceVideoEntity entity, RequestEvent evt) {
        try {
            Element rootElement = getRootElement(evt);
            log.info("接收到DeviceStatus消息");
            responseAck(evt, Response.OK);
            Element onlineElement = rootElement.element("Online");
            String text = onlineElement.getText();
            if (Objects.equals(text.trim().toUpperCase(), "ONLINE")) {
                deviceVideoService.online(entity);
            }else {
                deviceVideoService.offline(entity.getVideoCode());
            }
        } catch (DocumentException | SipException | InvalidArgumentException | ParseException e) {
            e.printStackTrace();
        }
    }
}
