package com.dite.znpt.monitor.media.zlm;

import com.dite.znpt.monitor.media.zlm.dto.ServerConfig;
import com.dite.znpt.monitor.media.zlm.dto.event.*;
import com.dite.znpt.monitor.media.zlm.impl.ZlmHookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huise23
 * @Date: 2022/8/29 10:22
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/index/hook")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ZlmHook {
    private final ZlmHookService service;

    /**
     * 流量统计事件，播放器或推流器断开时并且耗用流量超过特定阈值时会触发此事件，
     * 阈值通过配置文件general.flowThreshold配置；此事件对回复不敏感。
     */
    @PostMapping(value = "/on_flow_report")
    public BaseEventResp onFlowReport(@RequestBody FlowReportReq req) {
        return service.onFlowReport(req);
    }

    /**
     * 访问http文件服务器上hls之外的文件时触发。
     */
    @PostMapping(value = "/on_http_access")
    public HttpAccessResp onHttpAccess(@RequestBody HttpAccessReq req) {
        return service.onHttpAccess(req);
    }

    /**
     * 播放器鉴权事件，rtsp/rtmp/http-flv/ws-flv/hls的播放都将触发此鉴权事件；
     * 如果流不存在，那么先触发on_play事件然后触发on_stream_not_found事件。
     * 播放rtsp流时，如果该流启动了rtsp专属鉴权(on_rtsp_realm)那么将不再触发on_play事件。
     */
    @PostMapping(value = "/on_play")
    public BaseEventResp onPlay(@RequestBody PlayReq req) {
        return service.onPlay(req);
    }

    /**
     * rtsp/rtmp/rtp推流鉴权事件。
     */
    @PostMapping(value = "/on_publish")
    public PublishResp onPublish(@RequestBody PublishReq req) {
        return service.onPublish(req);
    }


    /**
     * 录制mp4完成后通知事件；此事件对回复不敏感。
     */
    @PostMapping(value = "/on_record_mp4")
    public BaseEventResp onRecordMp4(@RequestBody RecordMp4Req req) {
        return service.onRecordMp4(req);
    }

    /**
     * 该rtsp流是否开启rtsp专用方式的鉴权事件，开启后才会触发on_rtsp_auth事件。
     * 需要指出的是rtsp也支持url参数鉴权，它支持两种方式鉴权。
     */
    @PostMapping(value = "/on_rtsp_realm")
    public BaseEventResp onRtspRealm(@RequestBody RtspRealmReq req) {
        return service.onRtspRealm(req);
    }

    /**
     * rtsp专用的鉴权事件，先触发on_rtsp_realm事件然后才会触发on_rtsp_auth事件。
     */
    @PostMapping(value = "/on_rtsp_auth")
    public RtspAuthResp onRtspAuth(@RequestBody RtspAuthReq req) {
        return service.onRtspAuth(req);
    }

    /**
     * shell登录鉴权，ZLMediaKit提供简单的telnet调试方式
     * 使用telnet 127.0.0.1 9000能进入MediaServer进程的shell界面。
     */
    @PostMapping(value = "/on_shell_login")
    public BaseEventResp onShellLogin(@RequestBody ShellLoginReq req) {
        return service.onShellLogin(req);
    }

    /**
     * rtsp/rtmp流注册或注销时触发此事件；此事件对回复不敏感。
     */
    @PostMapping(value = "/on_stream_changed")
    public BaseEventResp onStreamChanged(@RequestBody StreamChangedReq req) {
        return service.onStreamChanged(req);
    }

    /**
     * 流无人观看时事件，用户可以通过此事件选择是否关闭无人看的流。
     */
    @PostMapping(value = "/on_stream_none_reader")
    public BaseEventResp onStreamNoneReader(@RequestBody StreamNoneReaderReq req) {
        return service.onStreamNoneReader(req);
    }

    /**
     * 流未找到事件，用户可以在此事件触发时，立即去拉流，这样可以实现按需拉流；此事件对回复不敏感。
     */
    @PostMapping(value = "/on_stream_not_found")
    public BaseEventResp onStreamNotFound(@RequestBody StreamNotFoundReq req) {
        return service.onStreamNotFound(req);
    }

    /**
     * 服务器启动事件，可以用于监听服务器崩溃重启；此事件对回复不敏感。
     */
    @PostMapping(value = "/on_server_started")
    public BaseEventResp onServerStarted(@RequestBody ServerConfig req) {
        return service.onServerStarted(req);
    }

    /**
     * 服务器定时上报时间，上报间隔可配置，默认10s上报一次
     */
    @PostMapping(value = "/on_server_keepalive")
    public BaseEventResp onServerKeepalive(@RequestBody ServerKeepaliveReq req) {
        return service.onServerKeepalive(req);
    }
}
