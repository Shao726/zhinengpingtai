package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.PartEntity;
import com.dite.znpt.domain.vo.PartListReq;
import com.dite.znpt.domain.vo.PartResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 表数据库访问层
 */
public interface PartMapper extends BaseMapper<PartEntity> {
    List<PartResp> queryBySelective(PartListReq partReq);
}

