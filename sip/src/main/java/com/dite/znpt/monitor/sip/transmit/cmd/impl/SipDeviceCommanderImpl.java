package com.dite.znpt.monitor.sip.transmit.cmd.impl;

import cn.hutool.core.util.SerializeUtil;
import com.dite.znpt.monitor.constant.IotCacheConstants;
import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.media.zlm.ZlmService;
import com.dite.znpt.monitor.sip.transmit.cmd.ISipDeviceCommander;
import com.dite.znpt.monitor.sip.transmit.cmd.SipRequestHeaderProvider;
import com.dite.znpt.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sip.ClientTransaction;
import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import javax.sip.SipProvider;
import javax.sip.address.SipURI;
import javax.sip.header.CallIdHeader;
import javax.sip.header.ViaHeader;
import javax.sip.message.Request;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: huise23
 * @Date: 2022/8/30 8:50
 * @Description:
 */
@Slf4j
@Component
public class SipDeviceCommanderImpl implements ISipDeviceCommander {

   private final static Pattern VIA_HEADER_PATTERN = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)\\:(\\d+)");
    @Resource
    @Qualifier(value="tcpSipProvider")
    private SipProvider tcpSipProvider;

    @Resource
    @Qualifier(value="udpSipProvider")
    private SipProvider udpSipProvider;

    @Resource
    private SipRequestHeaderProvider sipRequestHeaderProvider;

    @Resource
    private ZlmService zlmService;

    @Resource
    private RedisService redisService;

    /**
     * 查询目录列表
     * @param entity
     * @return {@link boolean}
     */
    @Override
    public boolean queryCatalog(DeviceVideoEntity entity) {
        try {
            StringBuffer content = new StringBuffer(200);
            String charset = entity.getCharset();
            content.append("<?xml version=\"1.0\" encoding=\"" + charset + "\"?>\r\n");
            content.append("<Query>\r\n");
            content.append("  <CmdType>Catalog</CmdType>\r\n");
            content.append("  <SN>" + (int)((Math.random()*9+1)*100000) + "</SN>\r\n");
            content.append("  <DeviceID>" + entity.getVideoCode() + "</DeviceID>\r\n");
            content.append("</Query>\r\n");

            String tag = String.valueOf(System.nanoTime());
            Request request = sipRequestHeaderProvider.createMessageRequest(entity, content.toString(), tag, tag, null);
            transmitRequest(entity.getTransport(), request);
        } catch (SipException | ParseException | InvalidArgumentException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 查询设备信息
     * @param entity
     * @return {@link boolean}
     */
    @Override
    public boolean queryDeviceInfo(DeviceVideoEntity entity) {
        try {
            StringBuffer content = new StringBuffer(200);
            String charset = entity.getCharset();
            content.append("<?xml version=\"1.0\" encoding=\"" + charset + "\"?>\r\n");
            content.append("<Query>\r\n");
            content.append("<CmdType>DeviceInfo</CmdType>\r\n");
            content.append("<SN>" + (int)((Math.random()*9+1)*100000) + "</SN>\r\n");
            content.append("<DeviceID>" + entity.getVideoCode() + "</DeviceID>\r\n");
            content.append("</Query>\r\n");
            String tag = String.valueOf(System.nanoTime());
            Request request = sipRequestHeaderProvider.createMessageRequest(entity, content.toString(), tag, tag, null);
            transmitRequest(entity.getTransport(), request);
        } catch (SipException | ParseException | InvalidArgumentException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 查询设备状态
     * @param entity
     * @return {@link boolean}
     */
    @Override
    public boolean queryDeviceStatus(DeviceVideoEntity entity) {
        try {
            StringBuffer content = new StringBuffer(200);
            content.append("<?xml version=\"1.0\" ?>\r\n");
            content.append("<Query>\r\n");
            content.append("<CmdType>DeviceStatus</CmdType>\r\n");
            content.append("<SN>" + (int)((Math.random()*9+1)*100000) + "</SN>\r\n");
            content.append("<DeviceID>" + entity.getVideoCode() + "</DeviceID>\r\n");
            content.append("</Query>\r\n");
            String tag = String.valueOf(System.nanoTime());
            Request request = sipRequestHeaderProvider.createMessageRequest(entity, content.toString(), tag, tag, tag);
            transmitRequest(entity.getTransport(), request);
            return true;

        } catch (SipException | ParseException | InvalidArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 请求预览视频流
     *
     * @param entity
     * @param channelCode 流传输协议
     * @param ssrc
     * @param ssrcPort
     * @return {@link String}
     */
    @Override
    public void playStreamCmd(DeviceVideoEntity entity, String channelCode, String ssrc, Integer ssrcPort, String mediaIp){
        try {
            String streamMode = entity.getStreamMode().toUpperCase();
            StringBuffer content = new StringBuffer(200);
            content.append("v=0\r\n");
            content.append("o=" + channelCode + " 0 0 IN IP4 " + mediaIp + "\r\n");
            content.append("s=Play\r\n");
            content.append("c=IN IP4 " + mediaIp + "\r\n");
            content.append("t=0 0\r\n");
                if("TCP-PASSIVE".equals(streamMode)) {
                    content.append("m=video " + ssrcPort + " TCP/RTP/AVP 96 97 98 99\r\n");
                }else if ("TCP-ACTIVE".equals(streamMode)) {
                    content.append("m=video " + ssrcPort + " TCP/RTP/AVP 96 97 98 99\r\n");
                }else if("UDP".equals(streamMode)) {
                    content.append("m=video " + ssrcPort + " RTP/AVP 96 97 98 99\r\n");
                }
                content.append("a=recvonly\r\n");
                content.append("a=rtpmap:96 PS/90000\r\n");
                content.append("a=rtpmap:98 H264/90000\r\n");
                content.append("a=rtpmap:97 MPEG4/90000\r\n");
                content.append("a=rtpmap:99 H265/90000\r\n");
                if ("TCP-PASSIVE".equals(streamMode)) { // tcp被动模式
                    content.append("a=setup:passive\r\n");
                    content.append("a=connection:new\r\n");
                } else if ("TCP-ACTIVE".equals(streamMode)) { // tcp主动模式
                    content.append("a=setup:active\r\n");
                    content.append("a=connection:new\r\n");
                }
            content.append("y=" + ssrc + "\r\n");//ssrc
            String tm = Long.toString(System.currentTimeMillis());
            CallIdHeader callIdHeader = "TCP".equals(entity.getTransport()) ? tcpSipProvider.getNewCallId() : udpSipProvider.getNewCallId();
            Request request = sipRequestHeaderProvider.createInviteRequest(entity, channelCode, content.toString(), null, "FromInvt" + tm, null, ssrc, callIdHeader);
            ClientTransaction clientTransaction = transmitRequest(entity.getTransport(), request);
            System.out.println("-----------------------------------------------------------");
            redisService.setCacheObject(IotCacheConstants.getClientTransactionCacheKey(ssrc), SerializeUtil.serialize(clientTransaction));
        } catch (ParseException | InvalidArgumentException | SipException e) {
            System.out.println("[zlm]拉流失败");
            zlmService.release(entity.getVideoCode(), channelCode);
            e.printStackTrace();
        }
    }

    /**
     * 停止视频流
     *
     * @param ssrc
     * @return
     */
    @Override
    public void stopStreamCmd(String ssrc) {
        try {
            ClientTransaction transaction = redisService.hasKey(ssrc) ? SerializeUtil.deserialize(redisService.getCacheObject(ssrc)) : null;
            if ( null == transaction || null == transaction.getDialog()) {
                return;
            }
            Request byeRequest = transaction.getDialog().createRequest(Request.BYE);
            SipURI byeURI = (SipURI) byeRequest.getRequestURI();
            String vh = transaction.getRequest().getHeader(ViaHeader.NAME).toString();

            Matcher matcher = VIA_HEADER_PATTERN.matcher(vh);
            if (VIA_HEADER_PATTERN.matcher(vh).find()) {
                byeURI.setHost(matcher.group(1));
            }
            ViaHeader viaHeader = (ViaHeader) byeRequest.getHeader(ViaHeader.NAME);
            String protocol = viaHeader.getTransport().toUpperCase();
            ClientTransaction clientTransaction = null;
            if("TCP".equals(protocol)) {
                clientTransaction = tcpSipProvider.getNewClientTransaction(byeRequest);
            } else if("UDP".equals(protocol)) {
                clientTransaction = udpSipProvider.getNewClientTransaction(byeRequest);
            }
            transaction.getDialog().sendRequest(clientTransaction);
            redisService.deleteObject(ssrc);
        } catch (SipException | ParseException e) {
            e.printStackTrace();
        }
    }

    private ClientTransaction transmitRequest(String transport, Request request) throws SipException {
        ClientTransaction clientTransaction =  "TCP".equals(transport) ? tcpSipProvider.getNewClientTransaction(request) : udpSipProvider.getNewClientTransaction(request);
        clientTransaction.sendRequest();
        return clientTransaction;
    }
}
