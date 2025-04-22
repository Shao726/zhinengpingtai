package com.dite.znpt.monitor.media.zlm.dto;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.dite.znpt.exception.ServiceException;
import com.dite.znpt.monitor.domain.vo.video.StreamMediaFormat;
import com.dite.znpt.monitor.media.zlm.config.StreamMediaServerConfig;
import com.dite.znpt.monitor.media.zlm.dto.resp.MediaResp;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: huise23
 * @Date: 2022/8/30 10:50
 * @Description: 节点信息
 */
@Data
@NoArgsConstructor
public class ServerItem implements Serializable {

    private static final long serialVersionUID = 2460404295026548536L;
    /**
     * 播流最大并发个数
     */
    public static final Integer MAX_PLAY_COUNT = 10000;
    /**
     * 节点信息ID
     */
    private String configId;
    /**
     * 流ID前缀
     */
    private String streamPrefix;
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
     * 当前流信息
     */
    private List<MediaResp> media;
    /**
     * 节点状态是否正常
     */
    private Boolean status;
    /**
     * 流媒体服务器已用的会话句柄
     */
    private Set<Integer> usedSn;

    public ServerItem(StreamMediaServerConfig server, List<StreamMediaFormat> formatList) {
        this.streamPrefix = server.getStreamPrefix();
        this.server = new ServerInfo(server.getApiHost(), server.getApiPort(), server.getSecretKey());
        this.formatList = formatList;
        this.status = false;
        this.usedSn = new HashSet<>();
    }

    public String genPlaySsrc(String channelCode) {
        if (this.usedSn.size() >= MAX_PLAY_COUNT) {
            throw new ServiceException("ssrc已经用完!");
        }
        int sn;
        for (sn = 0; sn < MAX_PLAY_COUNT; sn++) {
            if (!this.usedSn.contains(sn)) {
                this.usedSn.add(sn);
                break;
            }
        }
        //return StrUtil.format("0{}{}", StrUtil.blankToDefault(streamPrefix, channelCode.substring(3, 8)), NumberUtil.decimalFormat("0000", sn));
        return channelCode;
    }

    public void releaseSsrc(String ssrc) {
        try {
            Integer sn = NumberUtil.parseInt(ssrc.substring(6));
            usedSn.remove(sn);
        } catch (Exception ignored) {
        }
    }

    public void setMedia(List<MediaResp> media) {
        this.media = media;
        if (CollUtil.isNotEmpty(media)) {
            media.forEach(item -> {
                try {
                    Integer sn = NumberUtil.parseInt(StrUtil.isBlank(streamPrefix) ? item.getStream().substring(6) : item.getStream().replace("0" + streamPrefix, ""));
                    usedSn.add(sn);
                } catch (Exception ignored) {
                }
            });
        }
    }
}
