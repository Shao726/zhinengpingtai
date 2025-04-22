package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.CrewEntity;
import com.dite.znpt.domain.vo.CrewListReq;
import com.dite.znpt.domain.vo.CrewResp;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 机组信息表服务接口
 */
public interface CrewService extends IService<CrewEntity> {

    /**
     * 功能描述：查询机组信息列表
     *
     * @param crewReq 机组信息
     * @return {@link List }<{@link CrewEntity }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    List<CrewResp> selectList(CrewListReq crewReq);

    /**
     * 功能描述：查询单条机组信息
     *
     * @param crewId 机组信息Id
     * @return {@link CrewResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    CrewResp selectById(String crewId);

    /**
     * 功能描述：新增机组信息
     *
     * @param crew 机组信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void saveData(CrewEntity crew);

    /**
     * 功能描述：更新机组信息
     *
     * @param crew 机组信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void updateData(CrewEntity crew);

    /**
     * 功能描述：删除机组信息
     *
     * @param crewId 机组信息Id
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void deleteById(String crewId);
}

