package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.TurbineEntity;
import com.dite.znpt.domain.vo.TurbineListReq;
import com.dite.znpt.domain.vo.TurbineResp;
import com.dite.znpt.service.TurbineService;
import com.dite.znpt.mapper.TurbineMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 机组-项目关联表服务实现类
 */
@Service
@RequiredArgsConstructor
public class TurbineServiceImpl extends ServiceImpl<TurbineMapper, TurbineEntity> implements TurbineService {

    /**
     * 功能描述：查询机组-项目关联列表
     *
     * @param turbineReq 机组-项目关联信息
     * @return {@link PageInfo }<{@link TurbineResp }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public PageInfo<TurbineResp> selectList(TurbineListReq turbineReq) {
        PageUtil.startPage();
        List<TurbineResp> turbineList= this.baseMapper.queryBySelective(turbineReq);
        turbineList.forEach(resp -> {
            
        });
        return new PageInfo<>(turbineList);
    }

    /**
     * 功能描述：查询单条机组-项目关联
     *
     * @param turbineCode 机组-项目关联Id
     * @return {@link TurbineResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public TurbineResp selectById(String turbineCode) {
        TurbineListReq turbineReq = new TurbineListReq();
        turbineReq.setTurbineCode(turbineCode);

        PageInfo<TurbineResp> page = selectList(turbineReq);
        return page.hasContent() ? CollUtil.getFirst(page.getList()) : new TurbineResp();
    }

    /**
     * 功能描述：新增机组-项目关联
     *
     * @param turbine 机组-项目关联
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void saveData(TurbineEntity turbine) {
//            todo 校验
        save(turbine);
    }

    /**
     * 功能描述：更新机组-项目关联
     *
     * @param turbine 机组-项目关联
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void updateData(TurbineEntity turbine) {
//            todo 校验
        updateById(turbine);
    }

    /**
     * 功能描述：删除机组-项目关联
     *
     * @param turbineCode 机组-项目关联Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void deleteById(String turbineCode) {
//            todo 校验
        removeById(turbineCode);
    }

}
