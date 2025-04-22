package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.DefectEntity;
import com.dite.znpt.domain.vo.DefectListReq;
import com.dite.znpt.domain.vo.DefectResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 缺陷记录表数据库访问层
 */
public interface DefectMapper extends BaseMapper<DefectEntity> {
    List<DefectResp> queryBySelective(DefectListReq defectReq);
}

