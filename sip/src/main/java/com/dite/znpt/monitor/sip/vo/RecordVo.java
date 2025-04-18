package com.dite.znpt.monitor.sip.vo;

import lombok.Data;

import java.util.List;

/**    
 * @Description:设备录像信息
 * @author:
 * @date:
 */
@Data
public class RecordVo {

	private String deviceId;
	
	private String name;
	
	private int sumNum;
	
	private List<RecordItemVo> recordItemVoList;

}
