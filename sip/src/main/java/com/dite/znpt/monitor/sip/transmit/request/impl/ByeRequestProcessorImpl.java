package com.dite.znpt.monitor.sip.transmit.request.impl;

import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.media.zlm.ZlmService;
import com.dite.znpt.monitor.service.DeviceVideoService;
import com.dite.znpt.monitor.sip.transmit.SipProcessorFactoryImpl;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessor;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessorAbstract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sip.*;
import javax.sip.address.SipURI;
import javax.sip.header.FromHeader;
import javax.sip.header.HeaderAddress;
import javax.sip.message.Response;
import java.text.ParseException;

/**
 * @Author: huise23
 * @Date: 2022/8/10 16:55
 * @Description:
 */
@Slf4j
@Component
public class ByeRequestProcessorImpl extends ISipRequestProcessorAbstract implements InitializingBean, ISipRequestProcessor {

    private final String METHOD = "BYE";

    @Resource
    private SipProcessorFactoryImpl sipProcessorFactory;

    @Resource
    private DeviceVideoService deviceVideoService;

    @Resource
    private ZlmService zlmService;

    @Override
    public void afterPropertiesSet() {
        sipProcessorFactory.addRequestProcessor(METHOD, this);
    }
    @Override
    public void process(RequestEvent evt) {
        try {
            responseAck(evt, Response.OK);
            Dialog dialog = evt.getDialog();
            if (dialog == null) {
                return;
            }
            if (dialog.getState().equals(DialogState.TERMINATED)) {
                String channelCode = ((SipURI) ((HeaderAddress) evt.getRequest().getHeader(FromHeader.NAME)).getAddress().getURI()).getUser();
                DeviceVideoEntity videoEntity = deviceVideoService.getByChannelCode(channelCode);
                zlmService.release(videoEntity.getVideoCode(), channelCode);
            }
        } catch (SipException | InvalidArgumentException | ParseException e) {
            e.printStackTrace();
        }
    }

}
