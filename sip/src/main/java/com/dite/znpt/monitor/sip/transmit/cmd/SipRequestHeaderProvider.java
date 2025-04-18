package com.dite.znpt.monitor.sip.transmit.cmd;

import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.sip.config.SipConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sip.InvalidArgumentException;
import javax.sip.PeerUnavailableException;
import javax.sip.SipFactory;
import javax.sip.SipProvider;
import javax.sip.address.Address;
import javax.sip.address.SipURI;
import javax.sip.header.*;
import javax.sip.message.Request;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * 摄像头命令request构造器
 *
 * @author yun11
 * @date 2023/05/11
 */
@Component
public class SipRequestHeaderProvider {

	@Resource
	private SipConfig sipConfig;
	
	@Resource
	private SipFactory sipFactory;
	
	@Resource
	@Qualifier(value="tcpSipProvider")
	private SipProvider tcpSipProvider;
	
	@Resource
	@Qualifier(value="udpSipProvider")
	private SipProvider udpSipProvider;

	/**
	* 创建Message信令请求报文
	* @param device
	* @param content
	* @param viaTag
	* @param fromTag
	* @param toTag
	* @return {@link Request}
	*/
	public Request createMessageRequest(DeviceVideoEntity device, String content, String viaTag, String fromTag, String toTag) throws ParseException, InvalidArgumentException, PeerUnavailableException {
		Request request = null;
		// sipuri
		SipURI requestUri = sipFactory.createAddressFactory().createSipURI(device.getVideoCode(), device.getHostAddress());
		// via
		ArrayList<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
		ViaHeader viaHeader = sipFactory.createHeaderFactory().createViaHeader(sipConfig.getIp(), sipConfig.getPort(), device.getTransport(), viaTag);
		viaHeader.setRPort();
		viaHeaders.add(viaHeader);
		// from
		SipURI fromSipURI = SipFactory.getInstance().createAddressFactory().createSipURI(sipConfig.getId(), sipConfig.getDomain());
		Address fromAddress = SipFactory.getInstance().createAddressFactory().createAddress(fromSipURI);
		FromHeader fromHeader = SipFactory.getInstance().createHeaderFactory().createFromHeader(fromAddress, fromTag);
		// to
		SipURI toSipUri = sipFactory.createAddressFactory().createSipURI(device.getVideoCode(), device.getHostAddress());
		Address toAddress = sipFactory.createAddressFactory().createAddress(toSipUri);
		ToHeader toHeader = sipFactory.createHeaderFactory().createToHeader(toAddress, toTag);
		// callid
		CallIdHeader callIdHeader = "TCP".equals(device.getTransport()) ? tcpSipProvider.getNewCallId() : udpSipProvider.getNewCallId();
		// Forwards
		MaxForwardsHeader maxForwards = sipFactory.createHeaderFactory().createMaxForwardsHeader(70);
		// ceq
		CSeqHeader cSeqHeader = sipFactory.createHeaderFactory().createCSeqHeader(Long.valueOf(Math.abs(new SecureRandom().nextInt(Integer.MAX_VALUE)) - 1000), Request.MESSAGE);

		request = sipFactory.createMessageFactory().createRequest(requestUri, Request.MESSAGE, callIdHeader, cSeqHeader, fromHeader,
				toHeader, viaHeaders, maxForwards);
		ContentTypeHeader contentTypeHeader = sipFactory.createHeaderFactory().createContentTypeHeader("Application", "MANSCDP+xml");
		request.setContent(content, contentTypeHeader);
		return request;
	}

	public Request createInviteRequest(DeviceVideoEntity entity, String channelId, String content, String viaTag, String fromTag, String toTag, String ssrc, CallIdHeader callIdHeader) throws ParseException, InvalidArgumentException, PeerUnavailableException {
		Request request = null;
		//请求行
		SipURI requestLine = sipFactory.createAddressFactory().createSipURI(channelId, entity.getHostAddress());
		//via
		ArrayList<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
		ViaHeader viaHeader = sipFactory.createHeaderFactory().createViaHeader(sipConfig.getIp(), sipConfig.getPort(), entity.getTransport(), viaTag);
		viaHeader.setRPort();
		viaHeaders.add(viaHeader);
		//from
		SipURI fromSipUri = sipFactory.createAddressFactory().createSipURI(sipConfig.getId(), sipConfig.getDomain());
		Address fromAddress = sipFactory.createAddressFactory().createAddress(fromSipUri);
		//必须要有标记，否则无法创建会话，无法回应ack
		FromHeader fromHeader = sipFactory.createHeaderFactory().createFromHeader(fromAddress, fromTag);
		//to
		SipURI toSipUri = sipFactory.createAddressFactory().createSipURI(channelId, entity.getHostAddress());
		Address toAddress = sipFactory.createAddressFactory().createAddress(toSipUri);
		ToHeader toHeader = sipFactory.createHeaderFactory().createToHeader(toAddress,null);

		//Forwards
		MaxForwardsHeader maxForwards = sipFactory.createHeaderFactory().createMaxForwardsHeader(70);

		//ceq
		CSeqHeader cSeqHeader = sipFactory.createHeaderFactory().createCSeqHeader(Long.valueOf(Math.abs(new SecureRandom().nextInt(Integer.MAX_VALUE)) - 1000), Request.INVITE);
		request = sipFactory.createMessageFactory().createRequest(requestLine, Request.INVITE, callIdHeader, cSeqHeader,fromHeader, toHeader, viaHeaders, maxForwards);

		Address concatAddress = sipFactory.createAddressFactory().createAddress(sipFactory.createAddressFactory().createSipURI(sipConfig.getId(), sipConfig.getIp() + ":" + sipConfig.getPort()));
		request.addHeader(sipFactory.createHeaderFactory().createContactHeader(concatAddress));
		// Subject
		SubjectHeader subjectHeader = sipFactory.createHeaderFactory().createSubjectHeader(String.format("%s:%s,%s:%s", channelId, ssrc, sipConfig.getId(), 0));
		request.addHeader(subjectHeader);
		ContentTypeHeader contentTypeHeader = sipFactory.createHeaderFactory().createContentTypeHeader("APPLICATION", "SDP");
		request.setContent(content, contentTypeHeader);
		return request;
	}

}
