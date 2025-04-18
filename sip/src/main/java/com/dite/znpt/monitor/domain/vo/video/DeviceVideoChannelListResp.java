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
@ApiModel("视频通道列表响应")
public class DeviceVideoChannelListResp implements Serializable {

    private static final long serialVersionUID = -8053965410352257803L;

    @ApiModelProperty(value = "主键id")
    private Long channelId;

    @ApiModelProperty(value = "所属产品id")
    private Long productId;

    @ApiModelProperty(value = "通道国标编码")
    private String channelCode;

    @ApiModelProperty(value = "通道名称")
    private String channelName;

    @ApiModelProperty(value = "安装位置")
    private String address;

    @ApiModelProperty(value = "摄像头类型label")
    private String cameraType;

    @ApiModelProperty(value = "摄像头类型")
    private String cameraTypeLabel;

    @ApiModelProperty(value = "云台控制label")
    private String ptzControl;

    @ApiModelProperty(value = "云台控制，Y表示是,N表示否")
    private String ptzControlLabel;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "状态label")
    private String statusLabel;

    @ApiModelProperty(value = "描述")
    private String remark;
}
