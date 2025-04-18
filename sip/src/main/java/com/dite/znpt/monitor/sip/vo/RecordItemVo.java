package com.dite.znpt.monitor.sip.vo;

import lombok.Data;

/**
 * @Description:设备录像
 * @author:
 * @date:
 */
@Data
public class RecordItemVo {

	private String deviceId;
	
	private String name;
	
	private String filePath;
	
	private String address;
	
	private String startTime;
	
	private String endTime;
	
	private int secrecy;
	
	private String type;
	
	private String recorderId;

}
