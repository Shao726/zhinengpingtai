package com.dite.znpt.monitor.media.zlm.cache;

import com.dite.znpt.monitor.media.zlm.dto.ServerItem;
import com.dite.znpt.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: huise23
 * @Date: 2022/8/30 15:46
 * @Description:
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MediaServerCache {
    private final RedisService redisService;
    public final RedisTemplate<Object, Object> redisTemplate;

    private final String zlm_key = "zlm_media_server";


    public void putLoad(ServerItem serverItem) {
        redisService.setCacheObject(zlm_key, serverItem);
    }

    /**
     * 获取zlm节点
     */
    public ServerItem getLoad() {
        return redisService.getCacheObject(zlm_key);
    }

    public void releaseSsrc(String ssrc) {
        ServerItem item = getLoad();
        item.releaseSsrc(ssrc);
        putLoad(item);
    }

}
