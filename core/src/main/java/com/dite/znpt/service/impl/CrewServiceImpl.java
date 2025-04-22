package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.CrewEntity;
import com.dite.znpt.domain.vo.CrewListReq;
import com.dite.znpt.domain.vo.CrewResp;
import com.dite.znpt.service.CrewService;
import com.dite.znpt.mapper.CrewMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 机组信息表服务实现类
 */
@Service
@RequiredArgsConstructor
public class CrewServiceImpl extends ServiceImpl<CrewMapper, CrewEntity> implements CrewService {

    /**
     * 功能描述：查询机组信息列表
     *
     * @param crewReq 机组信息信息
     * @return {@link List }<{@link CrewResp }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public List<CrewResp> selectList(CrewListReq crewReq) {
        PageUtil.startPage();
        List<CrewResp> crewList= this.baseMapper.queryBySelective(crewReq);
        crewList.forEach(resp -> {
            
        });
        return crewList;
    }

    /**
     * 功能描述：查询单条机组信息
     *
     * @param crewId 机组信息Id
     * @return {@link CrewResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public CrewResp selectById(String crewId) {
        CrewListReq crewReq = new CrewListReq();
        crewReq.setCrewId(crewId);

        List<CrewResp> list = selectList(crewReq);
        return list.isEmpty() ? CollUtil.getFirst(list) : new CrewResp();
    }

    /**
     * 功能描述：新增机组信息
     *
     * @param crew 机组信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void saveData(CrewEntity crew) {
//            todo 校验
        save(crew);
    }

    /**
     * 功能描述：更新机组信息
     *
     * @param crew 机组信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void updateData(CrewEntity crew) {
//            todo 校验
        updateById(crew);
    }

    /**
     * 功能描述：删除机组信息
     *
     * @param crewId 机组信息Id
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void deleteById(String crewId) {
//            todo 校验
        removeById(crewId);
    }

}
