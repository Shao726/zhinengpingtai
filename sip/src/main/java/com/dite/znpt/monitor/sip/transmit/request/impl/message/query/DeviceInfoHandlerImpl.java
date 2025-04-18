package com.dite.znpt.monitor.sip.transmit.request.impl.message.query;

import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.service.DeviceVideoService;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessorAbstract;
import com.dite.znpt.monitor.sip.transmit.request.impl.MessageRequestProcessorImpl;
import com.dite.znpt.monitor.sip.transmit.request.impl.message.IMessageHandler;
import com.dite.znpt.monitor.sip.utils.XmlUtil;
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
import java.time.LocalDateTime;

/**
 * @Author: huise23
 * @Date: 2022/9/1 13:34
 * @Description: blog.csdn.net/Marvin1311/article/details/98845468
 */
@Slf4j
@Component
public class DeviceInfoHandlerImpl extends ISipRequestProcessorAbstract implements InitializingBean, IMessageHandler {

    private final static String CMD_TYPE = "DeviceInfo";

    @Resource
    private MessageRequestProcessorImpl messageRequestProcessor;

    @Resource
    private DeviceVideoService deviceVideoService;

    @Override
    public void afterPropertiesSet() {
        messageRequestProcessor.addHandler(CMD_TYPE, this);
    }

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
            log.info("接收到deviceInfo消息");
            entity.setVideoName(XmlUtil.getText(rootElement,"DeviceName"));
            entity.setManufacturer(XmlUtil.getText(rootElement,"Manufacturer"));
            entity.setModel(XmlUtil.getText(rootElement,"Model"));
            entity.setFirmware(XmlUtil.getText(rootElement,"Firmware"));
            entity.setUpdateTime(LocalDateTime.now());
            deviceVideoService.updateById(entity);
            responseAck(evt, Response.OK);
        } catch (DocumentException | SipException | InvalidArgumentException | ParseException e) {
            e.printStackTrace();
        }
    }
}
