package com.dite.znpt.monitor.media.zlm.dto;

import cn.hutool.core.util.StrUtil;
import com.dite.znpt.monitor.domain.vo.video.StreamMediaFormat;
import com.dite.znpt.monitor.media.zlm.dto.resp.RtpInfoResp;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: huise23
 * @Date: 2022/8/29 15:41
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MediaItem implements Serializable {
    /**
     * 设备编码
     */
    private String deviceCode;
    /**
     * 通道编码
     */
    private String channelCode;
    /**
     * 节点信息ID
     */
    private String configId;
    /**
     * 节点信息ID
     */
    private ServerInfo server;
    /**
     * 节点格式信息
     */
    private List<StreamMediaFormat> formatList;
    /**
     * 节点配置信息
     */
    private ServerConfig config;
    /**
     * 流ID
     */
    private String streamId;
    /**
     * 播放流信息
     */
    private RtpInfoResp rtp;
    /**
     * Rtp服务监听端口
     */
    private Integer rtpPort;
    /**
     * SSRC源地址
     */
    private String ssrc;
    /**
     * rtmp播放地址
     */
    private String rtmpUrl;
    /**
     * rtmpSsl播放地址
     */
    private String rtmpSslUrl;
    /**
     * rtsp播放地址
     */
    private String rtspUrl;
    /**
     * rtspSsl播放地址
     */
    private String rtspSslUrl;

    /**
     * rtc流地址
     */
    private String rtc;

    /**
     * rtcs流地址
     */
    private String rtcs;

    /**
     * 是否缓存
     */
    private Boolean isCache;

    public List<StreamMediaFormat> getFormatList(String mediaRouter) {
        if (StrUtil.isNotBlank(streamId)) {
            formatList.forEach(item -> item.generateUrl(server.getApiHost(), streamId, config, mediaRouter));
        }
        return formatList;
    }

    public String getRtmpUrl() {
        if (StrUtil.isBlank(streamId)) {
            return "";
        }
        return StrUtil.format("rtmp://{}:{}/rtp/{}", server.getApiHost(), config.getRtmpPort(), streamId);
    }

    public String getRtmpSslUrl() {
        if (StrUtil.isBlank(streamId)) {
            return "";
        }
        return config.getRtspSslPort() > 0 ? StrUtil.format("rtmps://{}:{}/rtp/{}", server.getApiHost(), config.getRtspSslPort(), streamId) : "";
    }


    public String getRtspUrl() {
        if (StrUtil.isBlank(streamId)) {
            return "";
        }
        return StrUtil.format("rtsp://{}:{}/rtp/{}", server.getApiHost(), config.getRtspPort(), streamId);
    }

    public String getRtspSslUrl() {
        if (StrUtil.isBlank(streamId)) {
            return "";
        }
        return config.getRtspSslPort() > 0 ? StrUtil.format("rtsps://{}:{}/rtp/{}", server.getApiHost(), config.getRtspSslPort(), streamId) : "";
    }
}
