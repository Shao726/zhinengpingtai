package com.dite.znpt.monitor.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: huise23
 * @Date: 2022/8/11 10:24
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("vs_device_video")
@ApiModel(value="DeviceVideoEntity", description="视频设备表")
public class DeviceVideoEntity implements Serializable {

    private static final long serialVersionUID = -182441901641147882L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "video_id", type = IdType.AUTO)
    private Long videoId;

    @ApiModelProperty(value = "视频设备国标编码")
    private String videoCode;

    @ApiModelProperty(value = "视频设备名称")
    private String videoName;

    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    @ApiModelProperty(value = "型号")
    private String model;

    @ApiModelProperty(value = "固件版本")
    private String firmware;

    @ApiModelProperty(value = "传输协议（UDP/TCP），默认UDP")
    private String transport;

    @ApiModelProperty(value = "数据流传输模式(默认UDP)")
    private String streamMode;

    @ApiModelProperty(value = "设备状态")
    private String status;

    @ApiModelProperty(value = "注册时间")
    private LocalDateTime registerTime;

    @ApiModelProperty(value = "心跳时间")
    private LocalDateTime KeepaliveTime;

    @ApiModelProperty("通道个数")
    private int channelCount;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "端口")
    private Integer port;

    @ApiModelProperty(value = "地址")
    private String hostAddress;

    @ApiModelProperty(value = "注册有效期")
    private Integer expires;

    @ApiModelProperty(value = "符集, 支持 UTF-8 与 GB2312")
    private String charset;

    @ApiModelProperty(value = "产品id")
    private Long productId;

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
