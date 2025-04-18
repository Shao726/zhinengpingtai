package com.dite.znpt.monitor.media.zlm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: huise23
 * @Date: 2022/8/30 10:50
 * @Description: 节点基础信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfo implements Serializable {
    /**
     * 节点地址
     */
    private String apiHost;
    /**
     * 节点端口
     */
    private Integer apiPort;
    /**
     * 节点秘钥
     */
    private String secretKey;
}
