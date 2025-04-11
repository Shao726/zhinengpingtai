package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.PartEntity;
import com.dite.znpt.domain.vo.PartListReq;
import com.dite.znpt.domain.vo.PartResp;
import com.dite.znpt.service.PartService;
import com.dite.znpt.mapper.PartMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 表服务实现类
 */
@Service
@RequiredArgsConstructor
public class PartServiceImpl extends ServiceImpl<PartMapper, PartEntity> implements PartService {

    /**
     * 功能描述：查询列表
     *
     * @param partReq 信息
     * @return {@link List }<{@link PartResp }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public List<PartResp> selectList(PartListReq partReq) {
        PageUtil.startPage();
        List<PartResp> partList= this.baseMapper.queryBySelective(partReq);
        partList.forEach(resp -> {
            
        });
        return partList;
    }

    /**
     * 功能描述：查询单条
     *
     * @param partId Id
     * @return {@link PartResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public PartResp selectById(String partId) {
        PartListReq partReq = new PartListReq();
        partReq.setPartId(partId);

        List<PartResp> list = selectList(partReq);
        return list.isEmpty() ? CollUtil.getFirst(list) : new PartResp();
    }

    /**
     * 功能描述：新增
     *
     * @param part 
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void saveData(PartEntity part) {
//            todo 校验
        save(part);
    }

    /**
     * 功能描述：更新
     *
     * @param part 
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void updateData(PartEntity part) {
//            todo 校验
        updateById(part);
    }

    /**
     * 功能描述：删除
     *
     * @param partId Id
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void deleteById(String partId) {
//            todo 校验
        removeById(partId);
    }

}
