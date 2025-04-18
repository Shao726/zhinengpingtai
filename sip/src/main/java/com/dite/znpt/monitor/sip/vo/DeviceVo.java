package com.dite.znpt.monitor.sip.vo;

import lombok.Data;

import java.util.Map;

@Data
public class DeviceVo {

	/**
	 * 设备Id
	 */
	private String deviceId;

	/**
	 * 设备名
	 */
	private String name;
	
	/**
	 * 生产厂商
	 */
	private String manufacturer;
	
	/**
	 * 型号
	 */
	private String model;
	
	/**
	 * 固件版本
	 */
	private String firmware;

	/**
	 * 传输协议
	 * UDP/TCP
	 */
	private String transport;

	/**
	 * wan地址
	 */
	private HostVo hostVo;
	
	/**
	 * 在线
	 */
	private int online;

	/**
	 * 通道列表
	 */
	private Map<String, DeviceChannelVo> channelMap;

}
