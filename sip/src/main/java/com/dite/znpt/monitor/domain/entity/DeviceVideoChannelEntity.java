package com.dite.znpt.monitor.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: huise23
 * @Date: 2022/8/11 17:29
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("vs_device_video_channel")
@ApiModel(value="DeviceVideoChannelEntity", description="视频通道表")
public class DeviceVideoChannelEntity implements Serializable {

    private static final long serialVersionUID = -4175177624487756818L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "channel_id", type = IdType.AUTO)
    private Long channelId;

    @ApiModelProperty(value = "视频设备id")
    private Long videoId;

    @ApiModelProperty(value = "通道国标编号")
    private String channelCode;

    @ApiModelProperty(value = "通道名")
    private String channelName;

    @ApiModelProperty(value = "生产厂商")
    private String manufacture;

    @ApiModelProperty(value = "型号")
    private String model;

    @ApiModelProperty(value = "设备归属")
    private String owner;

    @ApiModelProperty(value = "行政区域")
    private String civilCode;

    @ApiModelProperty(value = "警区")
    private String block;

    @ApiModelProperty(value = "安装位置")
    private String address;

    @ApiModelProperty(value = "是否有子设备 1有, 0没有")
    private int parental;

    @ApiModelProperty(value = "父级id")
    private String parentId;

    @ApiModelProperty(value = "信令安全模式  缺省为0; 0:不采用; 2: S/MIME签名方式; 3: S/ MIME加密签名同时采用方式; 4:数字摘要方式")
    private int safetyWay;

    @ApiModelProperty(value = "注册方式 缺省为1;1:符合IETFRFC3261标准的认证注册模 式; 2:基于口令的双向认证注册模式; 3:基于数字证书的双向认证注册模式")
    private int registerWay;

    @ApiModelProperty("证书序列号")
    private String certNum;

    @ApiModelProperty("证书有效标识 缺省为0;证书有效标识:0:无效1: 有效")
    private int certifiable;

    @ApiModelProperty("证书无效原因码")
    private int errCode;

    @ApiModelProperty( "证书终止有效期")
    private String endTime;

    @ApiModelProperty("保密属性 缺省为0; 0:不涉密, 1:涉密")
    private String secrecy;

    @ApiModelProperty("IP地址")
    private String ipAddress;

    @ApiModelProperty("端口号")
    private int port;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("摄像头类型")
    private String cameraType;

    @ApiModelProperty("云台控制")
    private String ptzControl;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty("经度")
    private double longitude;

    @ApiModelProperty("纬度")
    private double latitude;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人id")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

}
