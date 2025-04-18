package com.dite.znpt.monitor.media.zlm.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "zlm-config")
public class StreamMediaServerConfig {

    @ApiModelProperty(value = "'流媒体名称'")
    private String mediaName;

    @ApiModelProperty(value = "流媒体服务商")
    private String mediaService;

    @ApiModelProperty(value = "公网ip")
    private String publicHost;

    @ApiModelProperty(value = "接口ip")
    private String apiHost;

    @ApiModelProperty(value = "接口端口")
    private Integer apiPort;

    @ApiModelProperty(value = "密钥")
    private String secretKey;

    @ApiModelProperty(value = "流id前缀")
    private String streamPrefix;

    @ApiModelProperty(value = "rtp ip")
    private String rtpHost;

    @ApiModelProperty(value = "rtp 端口")
    private Integer rtpPort;

    @ApiModelProperty(value = "动态端口起始值")
    private String dynamicPortStart;

    @ApiModelProperty(value = "动态端口结束值")
    private String dynamicPortEnd;

}
