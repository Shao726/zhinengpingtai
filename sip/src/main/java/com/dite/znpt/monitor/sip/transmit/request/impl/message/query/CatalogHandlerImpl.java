package com.dite.znpt.monitor.sip.transmit.request.impl.message.query;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.dite.znpt.monitor.constant.dict.DeviceStatus;
import com.dite.znpt.monitor.domain.entity.DeviceVideoChannelEntity;
import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.service.DeviceVideoChannelService;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: huise23
 * @Date: 2022/9/1 13:33
 * @Description:
 */
@Slf4j
@Component
public class CatalogHandlerImpl extends ISipRequestProcessorAbstract implements InitializingBean, IMessageHandler {

    private final static String CMD_TYPE = "Catalog";

    @Resource
    private MessageRequestProcessorImpl messageRequestProcessor;

    @Resource
    private DeviceVideoService deviceVideoService;

    @Resource
    private DeviceVideoChannelService deviceVideoChannelService;

    @Override
    public void afterPropertiesSet(){
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
            log.info("接收到catalog消息");
            Element deviceChannelElement = rootElement.element("DeviceList");
            Element sumNumElement = rootElement.element("SumNum");
            Element snElement = rootElement.element("SN");
            if (snElement == null || sumNumElement == null || deviceChannelElement == null) {
                log.info("报文异常：{}，{}，{}", deviceChannelElement.getText(), sumNumElement.getText(), snElement.getText());
                responseAck(evt, Response.BAD_REQUEST);
                return;
            }
            Iterator<Element> deviceListIterator = deviceChannelElement.elementIterator();
            if (deviceListIterator != null) {
                List<DeviceVideoChannelEntity> channelEntityList = deviceVideoChannelService.selectDeviceVideoChannelByVideoCode(entity.getVideoId());
                Map<String, DeviceVideoChannelEntity> channelCodeMapEntity = channelEntityList.stream().collect(Collectors.toMap(k->k.getChannelCode(), v->v));
                // 遍历DeviceList
                while (deviceListIterator.hasNext()) {
                    Element itemDevice = deviceListIterator.next();
                    Element channelDeviceElement = itemDevice.element("DeviceID");

                    int parental = NumberUtil.isInteger(XmlUtil.getText(itemDevice,"Parental")) ? NumberUtil.parseInt(XmlUtil.getText(itemDevice,"Parental")): 0;
                    String parentId = XmlUtil.getText(itemDevice, "ParentID");
                    if (channelDeviceElement == null || parental != 0 || StrUtil.isEmpty(parentId)) {
                        continue;
                    }
                    String channelCode = channelDeviceElement.getText();
                    DeviceVideoChannelEntity channelEntity = channelCodeMapEntity.containsKey(channelCode) ? channelCodeMapEntity.get(channelCode) : new DeviceVideoChannelEntity();
                    channelEntity.setChannelCode(channelCode);
                    channelEntity.setVideoId(entity.getVideoId());
                    channelEntity.setChannelName( ObjectUtil.isNotEmpty(itemDevice.element("Name")) ? itemDevice.element("Name").getText(): "");
                    channelEntity.setManufacture(XmlUtil.getText(itemDevice,"Manufacturer"));
                    channelEntity.setModel(XmlUtil.getText(itemDevice,"Model"));
                    channelEntity.setOwner(XmlUtil.getText(itemDevice,"Owner"));
                    channelEntity.setCivilCode(XmlUtil.getText(itemDevice,"CivilCode"));
                    channelEntity.setBlock(XmlUtil.getText(itemDevice,"Block"));
                    channelEntity.setAddress(XmlUtil.getText(itemDevice,"Address"));
                    channelEntity.setParental(parental);
                    channelEntity.setParentId(parentId);
                    channelEntity.setSafetyWay(NumberUtil.isInteger(XmlUtil.getText(itemDevice,"SafetyWay")) ? NumberUtil.parseInt(XmlUtil.getText(itemDevice,"SafetyWay")):0);
                    channelEntity.setRegisterWay(NumberUtil.isInteger(XmlUtil.getText(itemDevice,"RegisterWay"))? NumberUtil.parseInt(XmlUtil.getText(itemDevice,"RegisterWay")): 1);
                    channelEntity.setCertNum(XmlUtil.getText(itemDevice,"CertNum"));
                    channelEntity.setCertifiable(NumberUtil.isInteger(XmlUtil.getText(itemDevice,"Certifiable"))? NumberUtil.parseInt(XmlUtil.getText(itemDevice,"Certifiable")): 0);
                    channelEntity.setErrCode(NumberUtil.isInteger(XmlUtil.getText(itemDevice,"ErrCode"))? NumberUtil.parseInt(XmlUtil.getText(itemDevice,"ErrCode")): 0);
                    channelEntity.setCertNum(XmlUtil.getText(itemDevice,"CertNum"));
                    channelEntity.setEndTime(XmlUtil.getText(itemDevice,"EndTime"));
                    channelEntity.setSecrecy(XmlUtil.getText(itemDevice,"Secrecy"));
                    channelEntity.setIpAddress(XmlUtil.getText(itemDevice,"IPAddress"));
                    channelEntity.setPort(NumberUtil.isInteger(XmlUtil.getText(itemDevice,"Port"))? NumberUtil.parseInt(XmlUtil.getText(itemDevice,"Port")): 0);
                    channelEntity.setPassword(XmlUtil.getText(itemDevice,"Password"));
                    Element statusElement = itemDevice.element("Status");
                    String status = statusElement != null && "OFF".equals(statusElement.getText())? DeviceStatus.OFFLINE.getValue() : DeviceStatus.ONLINE.getValue();
                    channelEntity.setStatus(status);
                    channelEntity.setLongitude(NumberUtil.isDouble(XmlUtil.getText(itemDevice,"Longitude"))?NumberUtil.parseDouble(XmlUtil.getText(itemDevice,"Longitude")): 0.00);
                    channelEntity.setLatitude(NumberUtil.isDouble(XmlUtil.getText(itemDevice,"Latitude"))?NumberUtil.parseDouble(XmlUtil.getText(itemDevice,"Latitude")): 0.00);
                    channelEntity.setUpdateTime(LocalDateTime.now());
                    channelEntityList.add(channelEntity);
                }
                deviceVideoChannelService.saveOrUpdateBatch(channelEntityList);

                entity.setChannelCount(channelEntityList.size());
                entity.setUpdateTime(LocalDateTime.now());
                deviceVideoService.updateById(entity);

                responseAck(evt, Response.OK);
            }
        } catch (DocumentException | SipException | InvalidArgumentException | ParseException e) {
            e.printStackTrace();
        }
    }

}
