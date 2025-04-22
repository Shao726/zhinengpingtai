package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.CrewEntity;
import com.dite.znpt.domain.vo.CrewListReq;
import com.dite.znpt.domain.vo.CrewResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 机组信息表数据库访问层
 */
public interface CrewMapper extends BaseMapper<CrewEntity> {
    List<CrewResp> queryBySelective(CrewListReq crewReq);
}

