package com.dite.znpt.monitor.domain.vo.video;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: huise23
 * @Date: 2022/8/11 14:16
 * @Description:
 */
@Data
@ApiModel("流媒体服务配置请求")
public class StreamMediaServerConfigReq implements Serializable {

    private static final long serialVersionUID = -1228005085084886474L;

    @NotNull(message = "流媒体名称不能为空")
    @ApiModelProperty(value = "流媒体名称")
    private String mediaName;

    @ApiModelProperty(value = "流媒体服务")
    private String mediaService;

    @ApiModelProperty(value = "公网 HOST")
    private String publicHost;

    @ApiModelProperty(value = "API HOST")
    private String apiHost;

    @ApiModelProperty(value = "API 端口")
    private Integer apiPort;

    @ApiModelProperty(value = "密钥")
    private Integer secretKey;

    @ApiModelProperty(value = "流ID前缀")
    private String streamPrefix;

    @ApiModelProperty(value = "RTP IP")
    private String rtpHost;

    @ApiModelProperty(value = "RTP 端口")
    private Integer rtpPort;

    @ApiModelProperty(value = "动态端口起始值")
    private Integer dynamicPortStart;

    @ApiModelProperty(value = "动态端口结束值")
    private Integer dynamicPortEnd;

    @ApiModelProperty(value = "流媒体格式")
    private List<StreamMediaFormatReq> streamMediaFormatReqList;

}
