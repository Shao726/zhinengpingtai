package com.dite.znpt.monitor.media.zlm.dto.resp;

import com.dite.znpt.monitor.media.zlm.dto.event.BaseEventReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: huise23
 * @Date: 2022/8/29 11:18
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MediaResp extends BaseEventReq {
    /**
     * 本协议观看人数
     */
    private Integer readerCount;
    /**
     * 观看总人数，包括hls/rtsp/rtmp/http-flv/ws-flv
     */
    private Integer totalReaderCount;
    /**
     * 客户端和服务器网络信息，可能为null类型
     */
    private OriginSock originSock;
    /**
     * 产生源类型，包括 unknown = 0, rtmp_push = 1, rtsp_push = 2, rtp_push = 3, pull = 4, ffmpeg_pull = 5, mp4_vod = 6, device_chn = 7
     */
    private Integer originType;
    /**
     * 产生源类型
     */
    private String originTypeStr;
    /**
     * 产生源的url
     */
    private String originUrl;
    /**
     * GMT unix系统时间戳，单位秒
     */
    private Long createStamp;
    /**
     * 存活时间，单位秒
     */
    private Long aliveSecond;
    /**
     * 数据产生速度，单位byte/s
     */
    private Long bytesSpeed;
    /**
     * 音视频轨道
     */
    private List<Track> tracks;

}
