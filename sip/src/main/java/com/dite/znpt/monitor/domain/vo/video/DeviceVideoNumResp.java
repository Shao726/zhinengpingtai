package com.dite.znpt.monitor.domain.vo.video;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: huise23
 * @Date: 2022/8/16 9:34
 * @Description:
 */
@Data
@ApiModel("视频设备数量响应")
public class DeviceVideoNumResp {

    @ApiModelProperty(value = "设备总数量")
    private Long allDevice;

    @ApiModelProperty(value = "设备在线数量")
    private Long onlineDevice;

    @ApiModelProperty(value = "设备离线数量")
    private Long offlineDevice;
}
