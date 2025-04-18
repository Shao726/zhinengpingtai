package com.dite.znpt.monitor.domain.vo.video;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.dite.znpt.monitor.media.zlm.dto.ServerConfig;
import com.dite.znpt.monitor.media.zlm.enums.MediaFormatType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: huise23
 * @Date: 2022/8/11 10:25
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "StreamMediaFormat对象", description = "流媒体格式")
public class StreamMediaFormat implements Serializable {

    private static final long serialVersionUID = -4177962876536716643L;

    @ApiModelProperty(value = "流媒体格式")
    private String mediaFormat;

    @ApiModelProperty(value = "端口")
    private Integer port;

    @ApiModelProperty(value = "是否开启tls,1表示ture,0表示false")
    private String openTls;

    @TableField(exist = false)
    @ApiModelProperty(value = "WebSocket播放地址")
    private String wsUrl;

    @TableField(exist = false)
    @ApiModelProperty(value = "Http播放地址")
    private String httpUrl;

    @TableField(exist = false)
    @ApiModelProperty(value = "WebSocket播放地址")
    private String wssUrl;

    @TableField(exist = false)
    @ApiModelProperty(value = "Http播放地址")
    private String httpsUrl;

    @TableField(exist = false)
    @ApiModelProperty(value = "相对播放地址")
    private String relativePath;

    public StreamMediaFormat(String mediaFormat,Integer port,String openTls){
        this.mediaFormat = mediaFormat;
        this.port = port;
        this.openTls = openTls;
    }

    public void generateUrl(String host, String streamId, ServerConfig config, String mediaRouter) {
        if("webrtc".equals(this.mediaFormat)){
            this.httpUrl = StrUtil.format("http://{}:{}/index/api/webrtc?app=rtp&stream={}&type=play", host, config.getHttpPort(), streamId);
            this.httpsUrl = StrUtil.format("https://{}:{}/index/api/webrtc?app=rtp&stream={}&type=play", host, config.getHttpSslPort(), streamId);
            this.relativePath = StrUtil.format("{}/index/api/webrtc?app=rtp&stream={}&type=play", mediaRouter, streamId);
            return;
        }
        String suffix = MediaFormatType.getSuffix(this.mediaFormat);
        if (config.getHttpSslPort() != null && config.getHttpSslPort() > 0) {
            this.wssUrl = StrUtil.format("wss://{}:{}/rtp/{}{}", host, config.getHttpSslPort(), streamId, suffix);
            this.httpsUrl = StrUtil.format("https://{}:{}/rtp/{}{}", host, config.getHttpSslPort(), streamId, suffix);
        }
        this.wsUrl = StrUtil.format("ws://{}:{}/rtp/{}{}", host, config.getHttpPort(), streamId, suffix);
        this.httpUrl = StrUtil.format("http://{}:{}/rtp/{}{}", host, config.getHttpPort(), streamId, suffix);
        this.relativePath = StrUtil.format("{}/rtp/{}{}", mediaRouter, streamId, suffix);
    }
}
