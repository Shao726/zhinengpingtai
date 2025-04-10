package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.PersonnelEntity;
import com.dite.znpt.domain.vo.PersonnelListReq;
import com.dite.znpt.domain.vo.PersonnelResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 人员信息表数据库访问层
 */
public interface PersonnelMapper extends BaseMapper<PersonnelEntity> {
    List<PersonnelResp> queryBySelective(PersonnelListReq personnelReq);
}

