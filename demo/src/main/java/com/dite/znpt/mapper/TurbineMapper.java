package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.TurbineEntity;
import com.dite.znpt.domain.vo.TurbineListReq;
import com.dite.znpt.domain.vo.TurbineResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 机组-项目关联表数据库访问层
 */
public interface TurbineMapper extends BaseMapper<TurbineEntity> {
    List<TurbineResp> queryBySelective(TurbineListReq turbineReq);
}

