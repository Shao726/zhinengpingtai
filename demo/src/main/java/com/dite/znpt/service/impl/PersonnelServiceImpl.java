package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.PersonnelEntity;
import com.dite.znpt.domain.vo.PersonnelListReq;
import com.dite.znpt.domain.vo.PersonnelResp;
import com.dite.znpt.service.PersonnelService;
import com.dite.znpt.mapper.PersonnelMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 人员信息表服务实现类
 */
@Service
@RequiredArgsConstructor
public class PersonnelServiceImpl extends ServiceImpl<PersonnelMapper, PersonnelEntity> implements PersonnelService {

    /**
     * 功能描述：查询人员信息列表
     *
     * @param personnelReq 人员信息信息
     * @return {@link PageInfo }<{@link PersonnelResp }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public PageInfo<PersonnelResp> selectList(PersonnelListReq personnelReq) {
        PageUtil.startPage();
        List<PersonnelResp> personnelList= this.baseMapper.queryBySelective(personnelReq);
        personnelList.forEach(resp -> {
            
        });
        return new PageInfo<>(personnelList);
    }

    /**
     * 功能描述：查询单条人员信息
     *
     * @param personId 人员信息Id
     * @return {@link PersonnelResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public PersonnelResp selectById(String personId) {
        PersonnelListReq personnelReq = new PersonnelListReq();
        personnelReq.setPersonId(personId);

        PageInfo<PersonnelResp> page = selectList(personnelReq);
        return page.hasContent() ? CollUtil.getFirst(page.getList()) : new PersonnelResp();
    }

    /**
     * 功能描述：新增人员信息
     *
     * @param personnel 人员信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void saveData(PersonnelEntity personnel) {
//            todo 校验
        save(personnel);
    }

    /**
     * 功能描述：更新人员信息
     *
     * @param personnel 人员信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void updateData(PersonnelEntity personnel) {
//            todo 校验
        updateById(personnel);
    }

    /**
     * 功能描述：删除人员信息
     *
     * @param personId 人员信息Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void deleteById(String personId) {
//            todo 校验
        removeById(personId);
    }

}
