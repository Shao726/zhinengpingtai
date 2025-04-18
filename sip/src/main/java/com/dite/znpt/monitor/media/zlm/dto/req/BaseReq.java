package com.dite.znpt.monitor.media.zlm.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: huise23
 * @Date: 2022/8/29 10:33
 * @Description: 请求响应基类
 */
@Data
public class BaseReq implements Serializable {
    /**
     * api操作密钥(配置文件配置)，如果操作ip是127.0.0.1，则不需要此参数
     */
    private String secret;
}
