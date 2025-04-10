package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.CombinedDictEntity;
import com.dite.znpt.domain.vo.CombinedDictListReq;
import com.dite.znpt.domain.vo.CombinedDictResp;
import com.dite.znpt.service.CombinedDictService;
import com.dite.znpt.mapper.CombinedDictMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 字典表服务实现类
 */
@Service
@RequiredArgsConstructor
public class CombinedDictServiceImpl extends ServiceImpl<CombinedDictMapper, CombinedDictEntity> implements CombinedDictService {

    /**
     * 功能描述：查询字典列表
     *
     * @param combinedDictReq 字典信息
     * @return {@link PageInfo }<{@link CombinedDictResp }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public PageInfo<CombinedDictResp> selectList(CombinedDictListReq combinedDictReq) {
        PageUtil.startPage();
        List<CombinedDictResp> combinedDictList= this.baseMapper.queryBySelective(combinedDictReq);
        combinedDictList.forEach(resp -> {
            
        });
        return new PageInfo<>(combinedDictList);
    }

    /**
     * 功能描述：查询单条字典
     *
     * @param dictId 字典Id
     * @return {@link CombinedDictResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public CombinedDictResp selectById(String dictId) {
        CombinedDictListReq combinedDictReq = new CombinedDictListReq();
        combinedDictReq.setDictId(dictId);

        PageInfo<CombinedDictResp> page = selectList(combinedDictReq);
        return page.hasContent() ? CollUtil.getFirst(page.getList()) : new CombinedDictResp();
    }

    /**
     * 功能描述：新增字典
     *
     * @param combinedDict 字典
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void saveData(CombinedDictEntity combinedDict) {
//            todo 校验
        save(combinedDict);
    }

    /**
     * 功能描述：更新字典
     *
     * @param combinedDict 字典
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void updateData(CombinedDictEntity combinedDict) {
//            todo 校验
        updateById(combinedDict);
    }

    /**
     * 功能描述：删除字典
     *
     * @param dictId 字典Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void deleteById(String dictId) {
//            todo 校验
        removeById(dictId);
    }

}
