package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.ProjectEntity;
import com.dite.znpt.domain.vo.ProjectListReq;
import com.dite.znpt.domain.vo.ProjectResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 项目信息表服务接口
 */
public interface ProjectService extends IService<ProjectEntity> {

    /**
     * 功能描述：查询项目信息列表
     *
     * @param projectReq 项目信息
     * @return {@link List }<{@link ProjectEntity }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    PageInfo<ProjectResp> selectList(ProjectListReq projectReq);

    /**
     * 功能描述：查询单条项目信息
     *
     * @param projectId 项目信息Id
     * @return {@link ProjectResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    ProjectResp selectById(String projectId);

    /**
     * 功能描述：新增项目信息
     *
     * @param project 项目信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void saveData(ProjectEntity project);

    /**
     * 功能描述：更新项目信息
     *
     * @param project 项目信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void updateData(ProjectEntity project);

    /**
     * 功能描述：删除项目信息
     *
     * @param projectId 项目信息Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void deleteById(String projectId);
}

