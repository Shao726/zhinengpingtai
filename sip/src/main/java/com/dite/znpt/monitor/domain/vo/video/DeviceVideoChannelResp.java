package com.dite.znpt.monitor.domain.vo.video;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: huise23
 * @Date: 2022/8/11 18:12
 * @Description:
 */
@Data
@ApiModel("视频通道响应")
public class DeviceVideoChannelResp implements Serializable {

    private static final long serialVersionUID = 1140851083577845760L;

    @ApiModelProperty(value = "主键id")
    private Long channelId;

    @ApiModelProperty(value = "通道国标编码")
    private String channelCode;

    @ApiModelProperty(value = "通道名称")
    private String channelName;

    @ApiModelProperty(value = "安装位置")
    private String address;

    @ApiModelProperty(value = "摄像头类型")
    private String cameraType;

    @ApiModelProperty(value = "云台控制，Y表示是,N表示否")
    private String ptzControl;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "描述")
    private String remark;
}
