package com.dite.znpt.monitor.domain.resp;

import com.dite.znpt.monitor.domain.entity.DeviceVideoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Date:2023/9/7 10:26
 * @Description:
 */
@Data
public class DeviceVideoResp extends DeviceVideoEntity {
    @ApiModelProperty(value = "设备状态label")
    private String statusLabel;
    @ApiModelProperty(value = "流传输模式label")
    private String streamModeLabel;
    @ApiModelProperty(value = "传输模式label")
    private String transportLabel;
}
