package com.dite.znpt.monitor.media.zlm.cache;

import cn.hutool.core.util.StrUtil;
import com.dite.znpt.monitor.media.zlm.dto.MediaItem;
import com.dite.znpt.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: huise23
 * @Date: 2022/8/30 15:46
 * @Description:
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MediaServerChannelCache {
    private final RedisService redisService;

    private String getKey(String deviceCode, String channelCode) {
        return StrUtil.format("zlm_media_server_channel:{}:{}", deviceCode, channelCode);
    }

    private String getStreamKey(String mediaServerId, String streamId) {
        return StrUtil.format("zlm_media_server_channel_stream_key:{}:{}", mediaServerId, streamId);
    }

    public boolean has(String deviceCode, String channelCode) {
        return redisService.hasKey(getKey(deviceCode, channelCode));
    }

    public MediaItem get(String deviceCode, String channelCode) {
        return redisService.getCacheObject(getKey(deviceCode, channelCode));
    }

    public void put(String deviceCode, String channelCode, MediaItem media) {
        String key = getKey(deviceCode, channelCode);
        redisService.setCacheObject(key, media);
        redisService.setCacheObject(getStreamKey(media.getConfig().getGeneralMediaServerId(), media.getStreamId()), key);
    }

    public void delete(MediaItem media) {
        redisService.deleteObject(getKey(media.getDeviceCode(), media.getChannelCode()));
        redisService.deleteObject(getStreamKey(media.getConfig().getGeneralMediaServerId(), media.getStreamId()));
    }

    public MediaItem getByStream(String mediaServerId, String streamId) {
        String key = redisService.getCacheObject(getStreamKey(mediaServerId, streamId));
        if (StrUtil.isNotBlank(key)) {
            return redisService.getCacheObject(key);
        }
        return null;
    }
}
