package com.dite.znpt.monitor.sip.session;

import org.springframework.stereotype.Component;

import javax.sip.ClientTransaction;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: huise23
 * @Date: 2022/8/29 16:52
 * @Description: 视频流session管理器，管理视频预览、预览回放的通信句柄
 */
@Component
public class StreamSessionManager {

	private ConcurrentHashMap<String, ClientTransaction> sessionMap = new ConcurrentHashMap<>();

	public void put(String ssrc, ClientTransaction transaction){
		sessionMap.put(ssrc, transaction);
	}
	
	public ClientTransaction get(String ssrc){
		return sessionMap.get(ssrc);
	}

}
