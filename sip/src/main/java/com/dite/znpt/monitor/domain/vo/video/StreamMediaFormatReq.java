package com.dite.znpt.monitor.domain.vo.video;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: huise23
 * @Date: 2022/8/11 14:40
 * @Description:
 */
@Data
@ApiModel("流媒体格式请求")
public class StreamMediaFormatReq implements Serializable {

    private static final long serialVersionUID = 6627383994019834279L;

    @ApiModelProperty(value = "流媒体格式")
    private String mediaFormat;

    @ApiModelProperty(value = "端口")
    private Integer port;

    @ApiModelProperty(value = "是否开启TLS,1表示true,0表示false")
    private String openTls;
}
