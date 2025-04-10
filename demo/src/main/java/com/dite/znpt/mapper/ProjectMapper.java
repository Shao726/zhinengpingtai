package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.ProjectEntity;
import com.dite.znpt.domain.vo.ProjectListReq;
import com.dite.znpt.domain.vo.ProjectResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 项目信息表数据库访问层
 */
public interface ProjectMapper extends BaseMapper<ProjectEntity> {
    List<ProjectResp> queryBySelective(ProjectListReq projectReq);
}

