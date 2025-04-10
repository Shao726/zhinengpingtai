package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.TConstructionEntity;
import com.dite.znpt.domain.vo.TConstructionListReq;
import com.dite.znpt.domain.vo.TConstructionResp;
import com.dite.znpt.service.TConstructionService;
import com.dite.znpt.mapper.TConstructionMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 施工信息表服务实现类
 */
@Service
@RequiredArgsConstructor
public class TConstructionServiceImpl extends ServiceImpl<TConstructionMapper, TConstructionEntity> implements TConstructionService {

    /**
     * 功能描述：查询施工信息列表
     *
     * @param tConstructionReq 施工信息信息
     * @return {@link PageInfo }<{@link TConstructionResp }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public PageInfo<TConstructionResp> selectList(TConstructionListReq tConstructionReq) {
        PageUtil.startPage();
        List<TConstructionResp> tConstructionList= this.baseMapper.queryBySelective(tConstructionReq);
        tConstructionList.forEach(resp -> {
            
        });
        return new PageInfo<>(tConstructionList);
    }

    /**
     * 功能描述：查询单条施工信息
     *
     * @param constructionId 施工信息Id
     * @return {@link TConstructionResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public TConstructionResp selectById(String constructionId) {
        TConstructionListReq tConstructionReq = new TConstructionListReq();
        tConstructionReq.setConstructionId(constructionId);

        PageInfo<TConstructionResp> page = selectList(tConstructionReq);
        return page.hasContent() ? CollUtil.getFirst(page.getList()) : new TConstructionResp();
    }

    /**
     * 功能描述：新增施工信息
     *
     * @param tConstruction 施工信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void saveData(TConstructionEntity tConstruction) {
//            todo 校验
        save(tConstruction);
    }

    /**
     * 功能描述：更新施工信息
     *
     * @param tConstruction 施工信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void updateData(TConstructionEntity tConstruction) {
//            todo 校验
        updateById(tConstruction);
    }

    /**
     * 功能描述：删除施工信息
     *
     * @param constructionId 施工信息Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void deleteById(String constructionId) {
//            todo 校验
        removeById(constructionId);
    }

}
