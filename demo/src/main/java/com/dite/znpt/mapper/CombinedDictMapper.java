package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.CombinedDictEntity;
import com.dite.znpt.domain.vo.CombinedDictListReq;
import com.dite.znpt.domain.vo.CombinedDictResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 字典表数据库访问层
 */
public interface CombinedDictMapper extends BaseMapper<CombinedDictEntity> {
    List<CombinedDictResp> queryBySelective(CombinedDictListReq combinedDictReq);
}

