package com.dite.znpt.monitor.media.zlm.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.dite.znpt.exception.ServiceException;
import com.dite.znpt.monitor.domain.vo.video.StreamMediaFormat;
import com.dite.znpt.monitor.media.zlm.ZlmApi;
import com.dite.znpt.monitor.media.zlm.ZlmService;
import com.dite.znpt.monitor.media.zlm.cache.MediaServerCache;
import com.dite.znpt.monitor.media.zlm.cache.MediaServerChannelCache;
import com.dite.znpt.monitor.media.zlm.config.StreamMediaServerConfig;
import com.dite.znpt.monitor.media.zlm.dto.MediaItem;
import com.dite.znpt.monitor.media.zlm.dto.ServerConfig;
import com.dite.znpt.monitor.media.zlm.dto.ServerItem;
import com.dite.znpt.monitor.media.zlm.dto.req.RtpServerReq;
import com.dite.znpt.monitor.media.zlm.dto.req.StreamReq;
import com.dite.znpt.monitor.media.zlm.dto.resp.MediaResp;
import com.dite.znpt.monitor.media.zlm.dto.resp.RtpInfoResp;
import com.dite.znpt.monitor.service.StreamMediaFormatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dite.znpt.monitor.config.MediaFormatConfig.streamMediaFormatList;

/**
 * @Author: huise23
 * @Date: 2022/8/30 10:40
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableScheduling
public class ZlmServiceImpl implements ZlmService, ApplicationRunner {
    private final StreamMediaFormatService formatService;
    private final ZlmApi zlmApi;
    private final MediaServerCache serverCache;
    private final MediaServerChannelCache channelCache;
    private final Environment environment;
    private final StreamMediaServerConfig zlmConfig;

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;
    @Value("${server.host:''}")
    private String serverHost;
    @Value("${server.port:''}")
    private String serverPort;

    private String profile = "dev";

    /**
     * 系统启动加载服务器
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //TODO 媒体格式注入
        List<StreamMediaFormat> formatList = streamMediaFormatList();
        ServerItem item = new ServerItem(zlmConfig, formatList);
        try {
            // 开发环境不去修改多媒体配置
//            if (!StrUtil.equalsIgnoreCase(activeProfile, profile)) {
//                String host = StrUtil.blankToDefault(serverHost, IpUtils.getHostIp());
//                String port = StrUtil.blankToDefault(serverPort, environment.getProperty("server.port"));
//                ServerConfig config = new ServerConfig();
//                config.refreshHook(host, port, zlmConfig);
//                zlmApi.setServerConfig(item.getServer(), config);
//            }
            List<ServerConfig> configList = zlmApi.getServerConfig(item.getServer());
            item.setConfig(configList.get(0));
            item.setStatus(true);
        } catch (Exception e) {
            log.error("流服务器节点配置错误：", e);
            item.setStatus(false);
        }
        //初始化参数
        serverCache.putLoad(item);
    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void heartbeat1() {
        log.debug("开始心跳检查...");
        ServerItem item = serverCache.getLoad();
        // 获取服务器流信息
        try {
            List<MediaResp> media = zlmApi.getMediaList(item.getServer(), new StreamReq());
            item.setMedia(media);
            item.setStatus(true);
        } catch (Exception e) {
            log.error("流服务器节点丢失：", e);
            item.setStatus(false);
        }
        serverCache.putLoad(item);
    }

    @Override
    public MediaItem play(String deviceCode, String channelCode) {
        // 获取通道信息
        MediaItem media;
        if (channelCache.has(deviceCode, channelCode)) {
            media = channelCache.get(deviceCode, channelCode);
        } else {
            media = new MediaItem().setDeviceCode(deviceCode).setChannelCode(channelCode);
        }
        media.setIsCache(true);
        // 检查节点是否正常
        if (!checkServer(media.getConfigId())) {
            // 获取负载最小的节点
            ServerItem server = serverCache.getLoad();
            if (!server.getStatus()) {
                throw new ServiceException("无正常的流媒体节点可用！");
            }
            media.setSsrc(server.genPlaySsrc(channelCode));
            media.setConfigId(server.getConfigId());
            media.setConfig(server.getConfig());
            media.setServer(server.getServer());
            media.setFormatList(server.getFormatList());
            media.setIsCache(false);
            serverCache.putLoad(server);
        }
        // 检查播放流是否正常
        if (StrUtil.isNotBlank(media.getStreamId())) {
            RtpInfoResp rtp = zlmApi.getRtpInfo(media.getServer(), media.getStreamId());
            if (rtp.getExist()) {
                media.setRtp(rtp);
                media.setIsCache(true);
                return media;
            }
        }
        // 不正常，需要重新拉流
        Integer rtpPort = zlmApi.openRtpServer(media.getServer(), new RtpServerReq(media.getSsrc()));
        media.setRtpPort(rtpPort);
        media.setStreamId(media.getSsrc());
        // 缓存链接信息
        channelCache.put(deviceCode, channelCode, media);
        media.setIsCache(false);
        return media;
    }

    @Override
    public void release(String deviceCode, String channelCode) {
        // 获取通道信息
        MediaItem media = channelCache.get(deviceCode, channelCode);
        release(media);
    }

    @Override
    public void release(MediaItem media) {
        if (ObjectUtil.isNull(media)) {
            return;
        }
        try {
            List<MediaResp> list = zlmApi.getMediaList(media.getServer(), new StreamReq().setStream(media.getStreamId()));
            if (!CollUtil.isEmpty(list) && list.get(0).getTotalReaderCount() > 0) {
                // 当前还有观看者 不释放资源
                return;
            }
            zlmApi.closeRtpServer(media.getServer(), media.getStreamId());
        } catch (Exception e) {
            log.error("流媒体服务器调用失败：", e);
        }
        // 释放SSRC句柄
        serverCache.releaseSsrc(media.getSsrc());
        // 删除链接信息
        channelCache.delete(media);
    }

    @Override
    public void display(String mediaServerId, String streamId) {
        // 获取通道信息
        MediaItem media = channelCache.getByStream(mediaServerId, streamId);
        release(media);
    }

    /**
     * 检查节点是否正常
     */
    private boolean checkServer(String configId) {
        if (ObjectUtil.isNull(configId)) {
            return false;
        }
        ServerItem item = serverCache.getLoad();
        return ObjectUtil.isNotNull(item) && item.getStatus();
    }
}
