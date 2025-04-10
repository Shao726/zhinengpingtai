package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.DefectEntity;
import com.dite.znpt.domain.vo.DefectListReq;
import com.dite.znpt.domain.vo.DefectResp;
import com.dite.znpt.service.DefectService;
import com.dite.znpt.mapper.DefectMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 缺陷记录表服务实现类
 */
@Service
@RequiredArgsConstructor
public class DefectServiceImpl extends ServiceImpl<DefectMapper, DefectEntity> implements DefectService {

    /**
     * 功能描述：查询缺陷记录列表
     *
     * @param defectReq 缺陷记录信息
     * @return {@link PageInfo }<{@link DefectResp }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public PageInfo<DefectResp> selectList(DefectListReq defectReq) {
        PageUtil.startPage();
        List<DefectResp> defectList= this.baseMapper.queryBySelective(defectReq);
        defectList.forEach(resp -> {
            
        });
        return new PageInfo<>(defectList);
    }

    /**
     * 功能描述：查询单条缺陷记录
     *
     * @param defectId 缺陷记录Id
     * @return {@link DefectResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public DefectResp selectById(String defectId) {
        DefectListReq defectReq = new DefectListReq();
        defectReq.setDefectId(defectId);

        PageInfo<DefectResp> page = selectList(defectReq);
        return page.hasContent() ? CollUtil.getFirst(page.getList()) : new DefectResp();
    }

    /**
     * 功能描述：新增缺陷记录
     *
     * @param defect 缺陷记录
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void saveData(DefectEntity defect) {
//            todo 校验
        save(defect);
    }

    /**
     * 功能描述：更新缺陷记录
     *
     * @param defect 缺陷记录
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void updateData(DefectEntity defect) {
//            todo 校验
        updateById(defect);
    }

    /**
     * 功能描述：删除缺陷记录
     *
     * @param defectId 缺陷记录Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    @Override
    public void deleteById(String defectId) {
//            todo 校验
        removeById(defectId);
    }

}
