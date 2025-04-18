package com.dite.znpt.monitor.sip.transmit.request;

import gov.nist.javax.sip.SipProviderImpl;
import gov.nist.javax.sip.SipStackImpl;
import gov.nist.javax.sip.message.SIPRequest;
import gov.nist.javax.sip.stack.SIPServerTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sip.*;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.header.ContentTypeHeader;
import javax.sip.header.ExpiresHeader;
import javax.sip.header.HeaderFactory;
import javax.sip.header.ViaHeader;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import javax.sip.message.Response;
import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: huise23
 * @Date: 2022/8/10 16:48
 * @Description:
 */

@Slf4j
public abstract class ISipRequestProcessorAbstract {

    @Autowired
    @Qualifier(value="tcpSipProvider")
    private SipProviderImpl tcpSipProvider;

    @Autowired
    @Qualifier(value="udpSipProvider")
    private SipProviderImpl udpSipProvider;

    /**
     * 根据 RequestEvent 获取 ServerTransaction
     * @param evt
     * @return
     */
    public ServerTransaction getServerTransaction(RequestEvent evt) {
        Request request = evt.getRequest();
        ServerTransaction serverTransaction = evt.getServerTransaction();
        // 判断TCP还是UDP
        boolean isTcp = false;
        ViaHeader reqViaHeader = (ViaHeader) request.getHeader(ViaHeader.NAME);
        String transport = reqViaHeader.getTransport();
        if ("TCP".equals(transport)) {
            isTcp = true;
        }

        if (serverTransaction == null) {
            try {
                if (isTcp) {
                    SipStackImpl stack = (SipStackImpl)tcpSipProvider.getSipStack();
                    serverTransaction = (SIPServerTransaction) stack.findTransaction((SIPRequest)request, true);
                    if (serverTransaction == null) {
                        serverTransaction = tcpSipProvider.getNewServerTransaction(request);
                    }
                } else {
                    SipStackImpl stack = (SipStackImpl)udpSipProvider.getSipStack();
                    serverTransaction = (SIPServerTransaction) stack.findTransaction((SIPRequest)request, true);
                    if (serverTransaction == null) {
                        serverTransaction = udpSipProvider.getNewServerTransaction(request);
                    }
                }
            } catch (TransactionAlreadyExistsException e) {
                log.error(e.getMessage());
            } catch (TransactionUnavailableException e) {
                log.error(e.getMessage());
            }
        }
        return serverTransaction;
    }

    public AddressFactory getAddressFactory() {
        try {
            return SipFactory.getInstance().createAddressFactory();
        } catch (PeerUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HeaderFactory getHeaderFactory() {
        try {
            return SipFactory.getInstance().createHeaderFactory();
        } catch (PeerUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MessageFactory getMessageFactory() {
        try {
            return SipFactory.getInstance().createMessageFactory();
        } catch (PeerUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 回复状态码
     * 100 trying
     * 200 OK
     * 400
     * 404
     * @param evt
     * @throws SipException
     * @throws InvalidArgumentException
     * @throws ParseException
     */
    public void responseAck(RequestEvent evt, int statusCode) throws SipException, InvalidArgumentException, ParseException {
        Response response = getMessageFactory().createResponse(statusCode, evt.getRequest());
        ServerTransaction serverTransaction = getServerTransaction(evt);
        if (serverTransaction == null) {
            log.warn("回复失败：{}", response);
            return;
        }
        serverTransaction.sendResponse(response);
        if (statusCode >= 200 && !"NOTIFY".equals(evt.getRequest().getMethod())) {
            if (serverTransaction.getDialog() != null) {
                serverTransaction.getDialog().delete();
            }
        }
    }

    public void responseAck(RequestEvent evt, int statusCode, String msg) throws SipException, InvalidArgumentException, ParseException {
        Response response = getMessageFactory().createResponse(statusCode, evt.getRequest());
        response.setReasonPhrase(msg);
        ServerTransaction serverTransaction = getServerTransaction(evt);
        serverTransaction.sendResponse(response);
        if (statusCode >= 200 && !"NOTIFY".equals(evt.getRequest().getMethod())) {
            if (serverTransaction.getDialog() != null) {
                serverTransaction.getDialog().delete();
            }
        }
    }

    /**
    * 回复带sdp的200
    * @param evt
    * @param sdp
    * @param sipServerCode SIP服务国标编码
    * @param serverIp SIP服务ip
    * @param sipServerPort SIP服务端口
    * @return
    */
    public void responseSdpAck(RequestEvent evt, String sdp, String sipServerCode, String serverIp, Integer sipServerPort) throws SipException, InvalidArgumentException, ParseException {
        Response response = getMessageFactory().createResponse(Response.OK, evt.getRequest());
        SipFactory sipFactory = SipFactory.getInstance();
        ContentTypeHeader contentTypeHeader = sipFactory.createHeaderFactory().createContentTypeHeader("APPLICATION", "SDP");
        response.setContent(sdp, contentTypeHeader);

        // 兼容国标中的使用编码@域名作为RequestURI的情况
        SipURI sipURI = (SipURI)evt.getRequest().getRequestURI();
        if (sipURI.getPort() == -1) {
            sipURI = sipFactory.createAddressFactory().createSipURI(sipServerCode,  serverIp+":" + sipServerPort);
        }
        log.debug("responseSdpAck SipURI: {}:{}", sipURI.getHost(), sipURI.getPort());

        Address concatAddress = sipFactory.createAddressFactory().createAddress(
                sipFactory.createAddressFactory().createSipURI(sipURI.getUser(),  sipURI.getHost()+":"+sipURI.getPort()
                ));
        response.addHeader(sipFactory.createHeaderFactory().createContactHeader(concatAddress));
        getServerTransaction(evt).sendResponse(response);
    }

    /**
     * 回复带xml的200
     * @param evt
     * @param xml
     * @param sipServerCode SIP服务国标编码
     * @param serverIp SIP服务ip
     * @param sipServerPort SIP服务端口
     * @throws SipException
     * @throws InvalidArgumentException
     * @throws ParseException
     */
    public Response responseXmlAck(RequestEvent evt, String xml, String sipServerCode, String serverIp, Integer sipServerPort) throws SipException, InvalidArgumentException, ParseException {
        Response response = getMessageFactory().createResponse(Response.OK, evt.getRequest());
        SipFactory sipFactory = SipFactory.getInstance();
        ContentTypeHeader contentTypeHeader = sipFactory.createHeaderFactory().createContentTypeHeader("Application", "MANSCDP+xml");
        response.setContent(xml, contentTypeHeader);

        // 兼容国标中的使用编码@域名作为RequestURI的情况
        SipURI sipURI = (SipURI)evt.getRequest().getRequestURI();
        if (sipURI.getPort() == -1) {
            sipURI = sipFactory.createAddressFactory().createSipURI(sipServerCode,  serverIp + ":" + sipServerPort);
        }
        log.debug("responseXmlAck SipURI: {}:{}", sipURI.getHost(), sipURI.getPort());

        Address concatAddress = sipFactory.createAddressFactory().createAddress(
                sipFactory.createAddressFactory().createSipURI(sipURI.getUser(),  sipURI.getHost()+":"+sipURI.getPort()
                ));
        response.addHeader(sipFactory.createHeaderFactory().createContactHeader(concatAddress));
        response.addHeader(evt.getRequest().getHeader(ExpiresHeader.NAME));
        getServerTransaction(evt).sendResponse(response);
        return response;
    }

    public Element getRootElement(RequestEvent evt) throws DocumentException {
        return getRootElement(evt, "gb2312");
    }

    public Element getRootElement(RequestEvent evt, String charset) throws DocumentException {
        if (charset == null) {
            charset = "gb2312";
        }
        Request request = evt.getRequest();
        SAXReader reader = new SAXReader();
        reader.setEncoding(charset);
        // 对海康出现的未转义字符做处理。
        String[] destStrArray = new String[]{"&lt;","&gt;","&amp;","&apos;","&quot;"};
        char despChar = '&'; // 或许可扩展兼容其他字符
        byte destBye = (byte) despChar;
        List<Byte> result = new ArrayList<>();
        byte[] rawContent = request.getRawContent();
        if (rawContent == null) {
            return null;
        }
        for (int i = 0; i < rawContent.length; i++) {
            if (rawContent[i] == destBye) {
                boolean resul = false;
                for (String destStr : destStrArray) {
                    if (i + destStr.length() <= rawContent.length) {
                        byte[] bytes = Arrays.copyOfRange(rawContent, i, i + destStr.length());
                        resul = resul || (Arrays.equals(bytes,destStr.getBytes()));
                    }
                }
                if (resul) {
                    result.add(rawContent[i]);
                }
            }else {
                result.add(rawContent[i]);
            }
        }
        Byte[] bytes = new Byte[0];
        byte[] bytesResult = ArrayUtils.toPrimitive(result.toArray(bytes));

        Document xml = reader.read(new ByteArrayInputStream(bytesResult));
        return xml.getRootElement();
    }
}
