package com.dite.znpt.monitor.media.zlm.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.dite.znpt.monitor.media.zlm.ZlmApi;
import com.dite.znpt.monitor.media.zlm.dto.ServerConfig;
import com.dite.znpt.monitor.media.zlm.dto.ServerInfo;
import com.dite.znpt.monitor.media.zlm.dto.req.*;
import com.dite.znpt.monitor.media.zlm.dto.resp.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: huise23
 * @Date: 2022/8/29 10:22
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ZlmApiImpl implements ZlmApi {
    private final HttpServletResponse response;

    public <T extends BaseReq, V extends BaseResp> V post(ServerInfo server, String url, T req, Class<V> clazz) {
        url = StrUtil.format("http://{}:{}/index/api/{}", server.getApiHost(), server.getApiPort(), url);
        log.info("ZLM:" + url);
        log.info("REQ:" + req);
        req.setSecret(server.getSecretKey());
        String respStr = HttpUtil.post(url, JSONUtil.toJsonStr(req));
        V resp = JSONUtil.toBean(respStr, clazz);
        if (resp.isSuccess()) {
            return resp;
        }
        throw new RuntimeException(resp.getMsg());
    }

    public <T extends BaseReq> BaseResp post(ServerInfo server, String url, T req) {
        return post(server, url, req, BaseResp.class);
    }

    public <V extends BaseResp> V post(ServerInfo server, String url, Class<V> clazz) {
        return post(server, url, new BaseReq(), clazz);
    }

    public BaseResp post(ServerInfo server, String url) {
        return post(server, url, new BaseReq());
    }

    @Override
    public List<String> getApiList(ServerInfo server) {
        return post(server, "getApiList").getList(String.class);
    }

    @Override
    public List<ThreadsLoadResp> getThreadsLoad(ServerInfo server) {
        return post(server, "getThreadsLoad").getList(ThreadsLoadResp.class);
    }

    @Override
    public List<ThreadsLoadResp> getWorkThreadsLoad(ServerInfo server) {
        return post(server, "getWorkThreadsLoad").getList(ThreadsLoadResp.class);
    }

    @Override
    public List<ServerConfig> getServerConfig(ServerInfo server) {
        return post(server, "getServerConfig").getList(ServerConfig.class);
    }

//    public static void main(String[] args) {
//        ZlmApi zlmApi = new ZlmApiImpl(null);
//        ServerInfo server = new ServerInfo("10.12.1.41", 8819, "035c73f7-bb6b-4889-a715-d9eb2d1925cc");
//        List<MediaResp> config = zlmApi.getMediaList(server,new StreamReq());
//        System.out.println(JSONUtil.toJsonPrettyStr(config));
//        for (MediaResp mediaResp : config) {
//            zlmApi.closeRtpServer(server,mediaResp.getStream() );
//        }
//    }

    @Override
    public Integer setServerConfig(ServerInfo server, ServerConfig config) {
        BaseResp req = post(server, "setServerConfig", config);
        return req.getChanged();
    }

    @Override
    public Boolean restartServer(ServerInfo server) {
        BaseResp req = post(server, "restartServer");
        return req.isSuccess();
    }

    @Override
    public List<MediaResp> getMediaList(ServerInfo server, StreamReq req) {
        return post(server, "getMediaList", req).getList(MediaResp.class);
    }

    @Override
    public CloseStreamResp closeStreams(ServerInfo server, CloseStreamReq req) {
        return post(server, "close_streams", req, CloseStreamResp.class);
    }

    @Override
    public List<SessionResp> getAllSession(ServerInfo server, GetAllSessionReq req) {
        return post(server, "getAllSession", req).getList(SessionResp.class);
    }

    @Override
    public Boolean kickSession(ServerInfo server, Long id) {
        return post(server, "kick_session", new IdReq(id)).isSuccess();
    }

    @Override
    public Integer kickSession(ServerInfo server, GetAllSessionReq req) {
        return post(server, "kick_sessions", req, CloseStreamResp.class).getCountHit();
    }

    @Override
    public String addStreamProxy(ServerInfo server, StreamProxyReq req) {
        return post(server, "addStreamProxy", req).getData(Dict.class).getStr("key");
    }

    @Override
    public Boolean delStreamProxy(ServerInfo server, String key) {
        return post(server, "delStreamProxy", new KeyReq(key)).getData(Dict.class).getBool("flag");
    }

    @Override
    public String addFfMpegSource(ServerInfo server, FFmpegSourceReq req) {
        return post(server, "addFFmpegSource", req).getData(Dict.class).getStr("key");
    }

    @Override
    public Boolean delFfMpegSource(ServerInfo server, String key) {
        return post(server, "delFFmpegSource", new KeyReq(key)).getData(Dict.class).getBool("flag");
    }

    @Override
    public RtpInfoResp getRtpInfo(ServerInfo server, String streamId) {
        return post(server, "getRtpInfo", new StreamIdReq(streamId), RtpInfoResp.class);
    }

    @Override
    public Mp4RecordFileResp getMp4RecordFile(ServerInfo server, GetMp4RecordFileReq req) {
        return post(server, "getMp4RecordFile", req).getData(Mp4RecordFileResp.class);
    }

    @Override
    public Boolean startRecord(ServerInfo server, RecordReq req) {
        return BooleanUtil.toBoolean(post(server, "startRecord", req).getResult());
    }

    @Override
    public Boolean stopRecord(ServerInfo server, RecordReq req) {
        return BooleanUtil.toBoolean(post(server, "stopRecord", req).getResult());
    }

    @Override
    public Boolean isRecording(ServerInfo server, RecordReq req) {
        return post(server, "isRecording", req).getStatus();
    }

    @Override
    public void getSnap(ServerInfo server, SnapReq req) throws IOException {
        String url = StrUtil.format("http://{}:{}/index/api/getSnap", server.getApiHost(), server.getApiPort());
        req.setSecret(server.getSecretKey());
        url += "?" + HttpUtil.toParams(BeanUtil.beanToMap(req));
        HttpUtil.download(url, response.getOutputStream(), true);
    }

    @Override
    public Integer openRtpServer(ServerInfo server, RtpServerReq req) {
        return post(server, "openRtpServer", req).getPort();
    }

    @Override
    public Boolean closeRtpServer(ServerInfo server, String streamId) {
        BaseResp closeRtpServer = post(server, "closeRtpServer", new StreamIdReq(streamId));
        return closeRtpServer.getHit() == 1;
    }

    @Override
    public List<RtpServerResp> listRtpServer(ServerInfo server) {
        return post(server, "listRtpServer").getList(RtpServerResp.class);
    }

    @Override
    public Integer startSendRtp(ServerInfo server, SendRtpReq req) {
        return post(server, "startSendRtp", req, RtpInfoResp.class).getLocalPort();
    }

    @Override
    public Integer startSendRtpPassive(ServerInfo server, SendRtpReq req) {
        return post(server, "startSendRtpPassive", req, RtpInfoResp.class).getLocalPort();
    }

    @Override
    public Boolean stopSendRtp(ServerInfo server, SendRtpReq req) {
        return post(server, "stopSendRtp", req).isSuccess();
    }

    @Override
    public StatisticResp getStatistic(ServerInfo server) {
        return post(server, "getStatistic").getData(StatisticResp.class);
    }

    @Override
    public String addStreamPusherProxy(ServerInfo server, StreamPusherProxyReq req) {
        return post(server, "addStreamPusherProxy", req).getData(Dict.class).getStr("key");
    }

    @Override
    public Boolean delStreamPusherProxy(ServerInfo server, String key) {
        return post(server, "delStreamPusherProxy", new KeyReq(key)).getData(Dict.class).getBool("flag");
    }
}
