package com.dite.znpt.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.monitor.domain.entity.IpConfigEntity;
import com.dite.znpt.monitor.domain.req.MonitorConfigAddReq;

import java.util.List;

/**
 * @Date: 2023/09/05 16:39
 * @Description: 监控设备IP配置表服务接口
 */
public interface IpConfigService extends IService<IpConfigEntity> {

    /**
     * 新增ip配置
     * @param req
     */
    void configAdd(MonitorConfigAddReq req);

    /**
     * 查询ip配置
     * @return
     */
    List<String> configList();
}

