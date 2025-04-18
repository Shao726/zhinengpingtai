package com.dite.znpt.monitor.config;

import cn.hutool.core.collection.CollUtil;
import com.dite.znpt.monitor.domain.vo.video.StreamMediaFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 媒体格式配置
 *
 * @author huise23
 * @since 2023-07-31 09:08:05
 */
@Configuration
public class MediaFormatConfig {

    @Bean
    public static List<StreamMediaFormat> streamMediaFormatList() {
        List<StreamMediaFormat> formatList = CollUtil.newArrayList();
        formatList.add(new StreamMediaFormat("flv",null,"1"));
        formatList.add(new StreamMediaFormat("mp4",null,"0"));
        formatList.add(new StreamMediaFormat("hls",null,"0"));
        formatList.add(new StreamMediaFormat("webrtc",null,"1"));
        return formatList;
    }


}
