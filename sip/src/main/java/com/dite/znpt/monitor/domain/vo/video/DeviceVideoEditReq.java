package com.dite.znpt.monitor.domain.vo.video;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: huise23
 * @Date: 2022/8/11 18:13
 * @Description:
 */
@Data
@ApiModel("视频设备编辑请求参数")
public class DeviceVideoEditReq implements Serializable {

    private static final long serialVersionUID = -3387666090991548317L;

    @ApiModelProperty(value = "设备名称")
    private String videoName;

    @ApiModelProperty(value = "所属产品")
    private Long productId;

    @ApiModelProperty(value = "说明")
    private String remark;

}
