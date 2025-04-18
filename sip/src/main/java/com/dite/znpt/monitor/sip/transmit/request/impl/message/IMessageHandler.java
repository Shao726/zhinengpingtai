package com.dite.znpt.monitor.sip.transmit.request.impl.message;

import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;

import javax.sip.RequestEvent;

/**
 * @Author: huise23
 * @Date: 2022/9/1 13:48
 * @Description:
 */
public interface IMessageHandler {

    /**
    * 处理消息
    * @param evt
    * @return  
    */
    void process(DeviceVideoEntity entity, RequestEvent evt);
}
