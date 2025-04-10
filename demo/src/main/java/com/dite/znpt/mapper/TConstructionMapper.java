package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.TConstructionEntity;
import com.dite.znpt.domain.vo.TConstructionListReq;
import com.dite.znpt.domain.vo.TConstructionResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 施工信息表数据库访问层
 */
public interface TConstructionMapper extends BaseMapper<TConstructionEntity> {
    List<TConstructionResp> queryBySelective(TConstructionListReq tConstructionReq);
}

