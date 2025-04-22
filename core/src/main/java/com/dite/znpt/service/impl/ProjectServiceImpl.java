package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.ProjectEntity;
import com.dite.znpt.domain.vo.ProjectListReq;
import com.dite.znpt.domain.vo.ProjectResp;
import com.dite.znpt.service.ProjectService;
import com.dite.znpt.mapper.ProjectMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 项目信息表服务实现类
 */
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectEntity> implements ProjectService {

    /**
     * 功能描述：查询项目信息列表
     *
     * @param projectReq 项目信息信息
     * @return {@link List }<{@link ProjectResp }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public List<ProjectResp> selectList(ProjectListReq projectReq) {
        PageUtil.startPage();
        List<ProjectResp> projectList= this.baseMapper.queryBySelective(projectReq);
        projectList.forEach(resp -> {
            
        });
        return projectList;
    }

    /**
     * 功能描述：查询单条项目信息
     *
     * @param projectId 项目信息Id
     * @return {@link ProjectResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public ProjectResp selectById(String projectId) {
        ProjectListReq projectReq = new ProjectListReq();
        projectReq.setProjectId(projectId);

        List<ProjectResp> list = selectList(projectReq);
        return list.isEmpty() ? CollUtil.getFirst(list) : new ProjectResp();
    }

    /**
     * 功能描述：新增项目信息
     *
     * @param project 项目信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void saveData(ProjectEntity project) {
//            todo 校验
        save(project);
    }

    /**
     * 功能描述：更新项目信息
     *
     * @param project 项目信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void updateData(ProjectEntity project) {
//            todo 校验
        updateById(project);
    }

    /**
     * 功能描述：删除项目信息
     *
     * @param projectId 项目信息Id
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void deleteById(String projectId) {
//            todo 校验
        removeById(projectId);
    }

}
