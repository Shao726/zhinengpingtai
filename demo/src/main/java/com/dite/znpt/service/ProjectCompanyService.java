package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.ProjectCompanyEntity;
import com.dite.znpt.domain.vo.ProjectCompanyListReq;
import com.dite.znpt.domain.vo.ProjectCompanyResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 项目-公司关联信息表服务接口
 */
public interface ProjectCompanyService extends IService<ProjectCompanyEntity> {

    /**
     * 功能描述：查询项目-公司关联信息列表
     *
     * @param projectCompanyReq 项目-公司关联信息
     * @return {@link List }<{@link ProjectCompanyEntity }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    PageInfo<ProjectCompanyResp> selectList(ProjectCompanyListReq projectCompanyReq);

    /**
     * 功能描述：查询单条项目-公司关联信息
     *
     * @param projectId 项目-公司关联信息Id
     * @return {@link ProjectCompanyResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    ProjectCompanyResp selectById(String projectId);

    /**
     * 功能描述：新增项目-公司关联信息
     *
     * @param projectCompany 项目-公司关联信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void saveData(ProjectCompanyEntity projectCompany);

    /**
     * 功能描述：更新项目-公司关联信息
     *
     * @param projectCompany 项目-公司关联信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void updateData(ProjectCompanyEntity projectCompany);

    /**
     * 功能描述：删除项目-公司关联信息
     *
     * @param projectId 项目-公司关联信息Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void deleteById(String projectId);
}

