package com.dite.znpt.monitor.media.zlm.impl;

import com.dite.znpt.monitor.media.zlm.ZlmService;
import com.dite.znpt.monitor.media.zlm.dto.ServerConfig;
import com.dite.znpt.monitor.media.zlm.dto.event.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: huise23
 * @Date: 2022/8/30 10:32
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ZlmHookService {
    private final ZlmService zlmService;

    /**
     * TODO 流量统计事件，播放器或推流器断开时并且耗用流量超过特定阈值时会触发此事件，
     * 阈值通过配置文件general.flowThreshold配置；此事件对回复不敏感。
     */
    public BaseEventResp onFlowReport(FlowReportReq req) {
        log.debug("[ZLM] onFlowReport : {}", req);
        return BaseEventResp.success();
    }

    /**
     * TODO 访问http文件服务器上hls之外的文件时触发。
     */
    public HttpAccessResp onHttpAccess(HttpAccessReq req) {
        log.debug("[ZLM] onHttpAccess : {}", req);
        return HttpAccessResp.success();
    }

    /**
     * TODO 播放器鉴权事件，rtsp/rtmp/http-flv/ws-flv/hls的播放都将触发此鉴权事件；
     * 如果流不存在，那么先触发on_play事件然后触发on_stream_not_found事件。
     * 播放rtsp流时，如果该流启动了rtsp专属鉴权(on_rtsp_realm)那么将不再触发on_play事件。
     */
    public BaseEventResp onPlay(PlayReq req) {
        log.debug("[ZLM] onPlay : {}", req);
        return BaseEventResp.success();
    }

    /**
     * TODO rtsp/rtmp/rtp推流鉴权事件。
     */
    public PublishResp onPublish(PublishReq req) {
        System.out.println("[ZLM] 接收到推流信息");
        log.info("[ZLM] onPublish : {}", req);
        return PublishResp.success();
    }

    /**
     * TODO 录制mp4完成后通知事件；此事件对回复不敏感。
     */
    public BaseEventResp onRecordMp4(RecordMp4Req req) {
        log.debug("[ZLM] onRecordMp4 : {}", req);
        return BaseEventResp.success();
    }

    /**
     * TODO 该rtsp流是否开启rtsp专用方式的鉴权事件，开启后才会触发on_rtsp_auth事件。
     * 需要指出的是rtsp也支持url参数鉴权，它支持两种方式鉴权。
     */
    public BaseEventResp onRtspRealm(RtspRealmReq req) {
        log.debug("[ZLM] onRtspRealm : {}", req);
        return BaseEventResp.success();
    }

    /**
     * TODO rtsp专用的鉴权事件，先触发on_rtsp_realm事件然后才会触发on_rtsp_auth事件。
     */
    public RtspAuthResp onRtspAuth(RtspAuthReq req) {
        log.debug("[ZLM] onRtspAuth : {}", req);
        return RtspAuthResp.success();
    }

    /**
     * TODO shell登录鉴权，ZLMediaKit提供简单的telnet调试方式
     * 使用telnet 127.0.0.1 9000能进入MediaServer进程的shell界面。
     */
    public BaseEventResp onShellLogin(ShellLoginReq req) {
        log.debug("[ZLM] onShellLogin : {}", req);
        return BaseEventResp.success();
    }

    /**
     * TODO rtsp/rtmp流注册或注销时触发此事件；此事件对回复不敏感。
     */
    public BaseEventResp onStreamChanged(StreamChangedReq req) {
        log.info("[ZLM] onStreamChanged 流信息 : {}", req);
        return BaseEventResp.success();
    }

    /**
     * 流无人观看时事件，用户可以通过此事件选择是否关闭无人看的流。
     */
    public BaseEventResp onStreamNoneReader(StreamNoneReaderReq req) {
        log.debug("[ZLM] onStreamNoneReader : {}", req);
        zlmService.display(req.getMediaServerId(), req.getStream());
        return BaseEventResp.success().setClose(false);
    }

    /**
     * TODO 流未找到事件，用户可以在此事件触发时，立即去拉流，这样可以实现按需拉流；此事件对回复不敏感。
     */
    public BaseEventResp onStreamNotFound(StreamNotFoundReq req) {
        log.debug("[ZLM] onStreamNotFound : {}", req);
        return BaseEventResp.success();
    }

    /**
     * TODO 服务器启动事件，可以用于监听服务器崩溃重启；此事件对回复不敏感。
     */
    public BaseEventResp onServerStarted(ServerConfig req) {
        log.debug("[ZLM] onServerStarted : {}", req);
        return BaseEventResp.success();
    }

    /**
     * TODO 服务器定时上报时间，上报间隔可配置，默认10s上报一次
     */
    public BaseEventResp onServerKeepalive(ServerKeepaliveReq req) {
        log.debug("[ZLM] onServerKeepalive : {}", req);
        return BaseEventResp.success();
    }
}
