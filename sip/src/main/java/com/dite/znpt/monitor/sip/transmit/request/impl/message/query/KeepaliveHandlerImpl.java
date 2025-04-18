package com.dite.znpt.monitor.sip.transmit.request.impl.message.query;

import com.alibaba.fastjson.JSON;
import com.dite.znpt.monitor.constant.dict.DeviceStatus;
import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.service.DeviceVideoService;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessorAbstract;
import com.dite.znpt.monitor.sip.transmit.request.impl.MessageRequestProcessorImpl;
import com.dite.znpt.monitor.sip.transmit.request.impl.message.IMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.header.ViaHeader;
import javax.sip.message.Response;
import java.text.ParseException;
import java.time.LocalDateTime;

/**
 * @Author: huise23
 * @Date: 2022/9/1 13:35
 * @Description:
 */
@Slf4j
@Component
public class KeepaliveHandlerImpl extends ISipRequestProcessorAbstract implements InitializingBean, IMessageHandler {

    private final static String CMD_TYPE = "Keepalive";

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
            log.info("接收到keepalive消息");
            // 判断RPort是否改变，改变则说明路由nat信息变化，修改设备信息
            // 获取到通信地址等信息
            ViaHeader viaHeader = (ViaHeader) evt.getRequest().getHeader(ViaHeader.NAME);
            log.info("viaHeader:{}", JSON.toJSONString(viaHeader));
            String ip = viaHeader.getHost();
            int rPort = viaHeader.getRPort();
            // 解析本地地址替代
            if (ObjectUtils.isEmpty(ip) || rPort == -1) {
                ip = viaHeader.getHost();
                rPort = viaHeader.getPort();
            }
            log.info("port:{}",rPort);
            if (entity.getPort() != rPort) {
                entity.setPort(rPort);
                entity.setHostAddress(ip.concat(":").concat(String.valueOf(rPort)));
            }
            entity.setKeepaliveTime(LocalDateTime.now());
            // 回复200 OK
            responseAck(evt, Response.OK);
            if (DeviceStatus.ONLINE.getValue().equals(entity.getStatus())) {
                entity.setUpdateTime(LocalDateTime.now());
                deviceVideoService.updateById(entity);
            }else {
                // 对于已经离线的设备判断他的注册是否已经过期
                if (deviceVideoService.expire(entity)){
                    deviceVideoService.online(entity);
                }
            }
        } catch (SipException | InvalidArgumentException | ParseException e) {
            e.printStackTrace();
        }
    }
}
