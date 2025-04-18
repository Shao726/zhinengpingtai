package com.dite.znpt.monitor.domain.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询视频信息参数
 *
 * @author huise23
 * @since 2024-12-03 14:03:29
 */
@Data
public class VideoInfoReq implements Serializable {

    @ApiModelProperty(value = "视频对接方式 1.摄像头直连 2.级联")
    private Integer videoConnection;

    @ApiModelProperty(value = "级联分隔符(默认/)")
    private String cascadeSeparator = "/";

}
