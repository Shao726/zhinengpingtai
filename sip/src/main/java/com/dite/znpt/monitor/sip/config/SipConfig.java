package com.dite.znpt.monitor.sip.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: huise23
 * @Date: 2022/8/10 16:59
 * @Description:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sip-config")
public class SipConfig {

    String name;

    String ip;

    Integer port;

    String charset;

    String domain;

    String id;

    String password;

    String mediaType = "mp4";

    /**
     * zlm播放地址路由
     */
    String mediaRouter = "/zlm";

}
