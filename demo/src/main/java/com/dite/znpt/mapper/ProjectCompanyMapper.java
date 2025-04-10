package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.ProjectCompanyEntity;
import com.dite.znpt.domain.vo.ProjectCompanyListReq;
import com.dite.znpt.domain.vo.ProjectCompanyResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 项目-公司关联信息表数据库访问层
 */
public interface ProjectCompanyMapper extends BaseMapper<ProjectCompanyEntity> {
    List<ProjectCompanyResp> queryBySelective(ProjectCompanyListReq projectCompanyReq);
}

