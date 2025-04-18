package com.dite.znpt.monitor.domain.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询视频信息返回信息
 *
 * @author huise23
 * @since 2024-12-03 14:03:29
 */
@Data
public class VideoInfoResp implements Serializable {

    @ApiModelProperty(value = "视频设备id")
    private Long videoId;

    @ApiModelProperty(value = "视频设备名称")
    private String videoName;

    @ApiModelProperty(value = "通道id")
    private Long channelId;

    @ApiModelProperty(value = "国标编码")
    private String channelCode;

    @ApiModelProperty(value = "通道名称")
    private String channelName;

    @ApiModelProperty(value = "生产厂商")
    private String manufacture;

    @ApiModelProperty(value = "型号")
    private String model;

    @ApiModelProperty(value = "安装位置")
    private String address;

    @ApiModelProperty("IP地址")
    private String ipAddress;

    @ApiModelProperty("端口号")
    private int port;

    @ApiModelProperty(value = "云台控制label")
    private String ptzControl;

    @ApiModelProperty("经度")
    private double longitude;

    @ApiModelProperty("纬度")
    private double latitude;

    @ApiModelProperty(value = "状态 DeviceStatus")
    private String status;
}
