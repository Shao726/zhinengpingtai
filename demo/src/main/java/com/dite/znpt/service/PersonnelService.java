package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.PersonnelEntity;
import com.dite.znpt.domain.vo.PersonnelListReq;
import com.dite.znpt.domain.vo.PersonnelResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 人员信息表服务接口
 */
public interface PersonnelService extends IService<PersonnelEntity> {

    /**
     * 功能描述：查询人员信息列表
     *
     * @param personnelReq 人员信息
     * @return {@link List }<{@link PersonnelEntity }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    PageInfo<PersonnelResp> selectList(PersonnelListReq personnelReq);

    /**
     * 功能描述：查询单条人员信息
     *
     * @param personId 人员信息Id
     * @return {@link PersonnelResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    PersonnelResp selectById(String personId);

    /**
     * 功能描述：新增人员信息
     *
     * @param personnel 人员信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void saveData(PersonnelEntity personnel);

    /**
     * 功能描述：更新人员信息
     *
     * @param personnel 人员信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void updateData(PersonnelEntity personnel);

    /**
     * 功能描述：删除人员信息
     *
     * @param personId 人员信息Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void deleteById(String personId);
}

