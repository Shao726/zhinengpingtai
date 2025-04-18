package com.dite.znpt.monitor.sip.transmit.request.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dite.znpt.monitor.constant.dict.DeviceStatus;
import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import com.dite.znpt.monitor.service.DeviceVideoService;
import com.dite.znpt.monitor.service.IpConfigService;
import com.dite.znpt.monitor.sip.config.SipConfig;
import com.dite.znpt.monitor.sip.transmit.SipProcessorFactoryImpl;
import com.dite.znpt.monitor.sip.transmit.cmd.ISipDeviceCommander;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessor;
import com.dite.znpt.monitor.sip.transmit.request.ISipRequestProcessorAbstract;
import com.dite.znpt.monitor.sip.utils.DigestServerAuthenticationHelper;
import gov.nist.javax.sip.address.AddressImpl;
import gov.nist.javax.sip.address.SipUri;
import gov.nist.javax.sip.header.Expires;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.header.*;
import javax.sip.message.Request;
import javax.sip.message.Response;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @Author: huise23
 * @Date: 2022/8/10 16:53
 * @Description:
 */
@Slf4j
@Component
public class RegisterRequestProcessorImpl extends ISipRequestProcessorAbstract implements InitializingBean, ISipRequestProcessor {

    public final String METHOD = "REGISTER";

    @Resource
    private SipProcessorFactoryImpl sipProcessorFactory;

    @Resource
    private SipConfig sipConfig;

    @Resource
    private DeviceVideoService deviceVideoService;

    @Resource
    private ISipDeviceCommander sipDeviceCommander;

    @Resource
    private IpConfigService ipConfigService;

    @Override
    public void afterPropertiesSet() {
        sipProcessorFactory.addRequestProcessor(METHOD, this);
    }

    @Override
    public synchronized void process(RequestEvent evt) {
        try {
            log.info("收到注册请求，开始处理");
            Request request = evt.getRequest();
            Response response;
            // 注册标志
            boolean registerFlag = false;
            AuthorizationHeader authorHeader = (AuthorizationHeader) request.getHeader(AuthorizationHeader.NAME);

            String password = sipConfig.getPassword();
            if (StrUtil.isNotBlank(password)) {
                // 未携带授权头或者密码错误 回复401
                if (authorHeader == null && StrUtil.isNotEmpty(sipConfig.getPassword())) {
                    log.info("未携带授权头或者密码错误，回复401");
                    response = getMessageFactory().createResponse(Response.UNAUTHORIZED, request);
                    new DigestServerAuthenticationHelper().generateChallenge(getHeaderFactory(), response, sipConfig.getDomain());
                    getServerTransaction(evt).sendResponse(response);
                    return;
                }
                // 校验密码是否正确
                boolean passwordCorrect = new DigestServerAuthenticationHelper().doAuthenticatePlainTextPassword(request, sipConfig.getPassword());
                if (!passwordCorrect) {
                    response = getMessageFactory().createResponse(Response.FORBIDDEN, request);
                    response.setReasonPhrase("wrong password");
                    log.info("[注册请求] 密码/SIP服务器ID错误, 回复403");
                    getServerTransaction(evt).sendResponse(response);
                    return;
                }
            }
            // 携带授权头并且密码正确
            response = getMessageFactory().createResponse(Response.OK, request);
            // 添加date头
            response.addHeader(getHeaderFactory().createDateHeader(Calendar.getInstance(Locale.ENGLISH)));
            ExpiresHeader expiresHeader = (ExpiresHeader) request.getHeader(Expires.NAME);
            // 添加Contact头
            response.addHeader(request.getHeader(ContactHeader.NAME));
            // 添加Expires头
            response.addHeader(request.getExpires());
            // 获取到通信地址等信息
            FromHeader fromHeader = (FromHeader) request.getHeader(FromHeader.NAME);
            ViaHeader viaHeader = (ViaHeader) request.getHeader(ViaHeader.NAME);
            AddressImpl address = (AddressImpl) fromHeader.getAddress();
            SipUri uri = (SipUri) address.getURI();
            String videoCode = uri.getUser();
            DeviceVideoEntity entity = deviceVideoService.getByCode(videoCode);
            log.info("entity:"+JSONUtil.toJsonStr(entity));
            if (expiresHeader != null && expiresHeader.getExpires() == 0) {
                // 注册失败
                registerFlag = false;
            } else {
                // 注册成功
                registerFlag = true;
                if (entity == null) {
                    entity = new DeviceVideoEntity();
                    entity.setStreamMode("UDP");
                    entity.setVideoCode(videoCode);
                    entity.setVideoCode(DeviceStatus.OFFLINE.getValue());
                }
                entity.setVideoCode(videoCode);
                entity.setExpires(expiresHeader.getExpires());

                String ip = viaHeader.getHost();
                int rPort = viaHeader.getRPort();
                // 解析本地地址替代
                if (ObjectUtils.isEmpty(ip) || rPort == -1) {
                    ip = viaHeader.getHost();
                    rPort = viaHeader.getPort();
                }
                entity.setIp(ip);
                entity.setPort(rPort);
                entity.setHostAddress(ip.concat(":").concat(String.valueOf(rPort)));
                // 判断TCP还是UDP
                ViaHeader reqViaHeader = (ViaHeader) request.getHeader(ViaHeader.NAME);
                entity.setTransport("TCP".equals(reqViaHeader.getTransport()) ? "TCP" : "UDP");
                entity.setCharset("GB2312");
            }
            getServerTransaction(evt).sendResponse(response);
            if(registerFlag){
                entity.setRegisterTime(LocalDateTime.now());
                deviceVideoService.online(entity);
            }else{
                deviceVideoService.offline(videoCode);
            }
        } catch (SipException | InvalidArgumentException | NoSuchAlgorithmException | ParseException e) {
            e.printStackTrace();
        }
    }

}
