package com.dite.znpt.monitor.domain.vo.video;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: huise23
 * @Date: 2022/8/11 18:13
 * @Description:
 */
@Data
@ApiModel("视频设备列表响应")
public class DeviceVideoListResp implements Serializable {

    private static final long serialVersionUID = -5568664011265192343L;

    @ApiModelProperty(value = "主键id")
    private Long videoId;

    @ApiModelProperty(value = "视频设备国标编码")
    private String videoCode;

    @ApiModelProperty(value = "视频设备名称")
    private String videoName;

    @ApiModelProperty(value = "传输模式")
    private String transport;

    @ApiModelProperty(value = "传输模式label")
    private String transportLabel;

    @ApiModelProperty(value = "流传输模式")
    private String streamMode;

    @ApiModelProperty(value = "流传输模式label")
    private String streamModeLabel;

    @ApiModelProperty(value = "通道数量")
    private Integer channelCount;

    @ApiModelProperty(value = "设备状态")
    private String status;

    @ApiModelProperty(value = "设备状态label")
    private String statusLabel;

    @ApiModelProperty(value = "设备ip")
    private String ip;

    @ApiModelProperty(value = "设备端口")
    private String port;

    @ApiModelProperty(value = "设备地址（ip+端口）")
    private String hostAddress;

    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    @ApiModelProperty(value = "备注")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "心跳时间")
    private LocalDateTime keepAliveTime;
}
