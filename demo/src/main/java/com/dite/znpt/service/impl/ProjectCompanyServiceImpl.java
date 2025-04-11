package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.ProjectCompanyEntity;
import com.dite.znpt.domain.vo.ProjectCompanyListReq;
import com.dite.znpt.domain.vo.ProjectCompanyResp;
import com.dite.znpt.service.ProjectCompanyService;
import com.dite.znpt.mapper.ProjectCompanyMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 项目-公司关联信息表服务实现类
 */
@Service
@RequiredArgsConstructor
public class ProjectCompanyServiceImpl extends ServiceImpl<ProjectCompanyMapper, ProjectCompanyEntity> implements ProjectCompanyService {

    /**
     * 功能描述：查询项目-公司关联信息列表
     *
     * @param projectCompanyReq 项目-公司关联信息信息
     * @return {@link List }<{@link ProjectCompanyResp }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public List<ProjectCompanyResp> selectList(ProjectCompanyListReq projectCompanyReq) {
        PageUtil.startPage();
        List<ProjectCompanyResp> projectCompanyList= this.baseMapper.queryBySelective(projectCompanyReq);
        projectCompanyList.forEach(resp -> {
            
        });
        return projectCompanyList;
    }

    /**
     * 功能描述：查询单条项目-公司关联信息
     *
     * @param projectId 项目-公司关联信息Id
     * @return {@link ProjectCompanyResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public ProjectCompanyResp selectById(String projectId) {
        ProjectCompanyListReq projectCompanyReq = new ProjectCompanyListReq();
        projectCompanyReq.setProjectId(projectId);

        List<ProjectCompanyResp> list = selectList(projectCompanyReq);
        return list.isEmpty() ? CollUtil.getFirst(list) : new ProjectCompanyResp();
    }

    /**
     * 功能描述：新增项目-公司关联信息
     *
     * @param projectCompany 项目-公司关联信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void saveData(ProjectCompanyEntity projectCompany) {
//            todo 校验
        save(projectCompany);
    }

    /**
     * 功能描述：更新项目-公司关联信息
     *
     * @param projectCompany 项目-公司关联信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void updateData(ProjectCompanyEntity projectCompany) {
//            todo 校验
        updateById(projectCompany);
    }

    /**
     * 功能描述：删除项目-公司关联信息
     *
     * @param projectId 项目-公司关联信息Id
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void deleteById(String projectId) {
//            todo 校验
        removeById(projectId);
    }

}
