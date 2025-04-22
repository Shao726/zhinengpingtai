package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.TCompanyEntity;
import com.dite.znpt.domain.vo.TCompanyListReq;
import com.dite.znpt.domain.vo.TCompanyResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 公司信息表数据库访问层
 */
public interface TCompanyMapper extends BaseMapper<TCompanyEntity> {
    List<TCompanyResp> queryBySelective(TCompanyListReq tCompanyReq);
}

