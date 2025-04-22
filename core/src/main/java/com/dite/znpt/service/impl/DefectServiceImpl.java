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

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 缺陷记录表服务实现类
 */
@Service
@RequiredArgsConstructor
public class DefectServiceImpl extends ServiceImpl<DefectMapper, DefectEntity> implements DefectService {

    /**
     * 功能描述：查询缺陷记录列表
     *
     * @param defectReq 缺陷记录信息
     * @return {@link List }<{@link DefectResp }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public List<DefectResp> selectList(DefectListReq defectReq) {
        PageUtil.startPage();
        List<DefectResp> defectList= this.baseMapper.queryBySelective(defectReq);
        defectList.forEach(resp -> {
            
        });
        return defectList;
    }

    /**
     * 功能描述：查询单条缺陷记录
     *
     * @param defectId 缺陷记录Id
     * @return {@link DefectResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public DefectResp selectById(String defectId) {
        DefectListReq defectReq = new DefectListReq();
        defectReq.setDefectId(defectId);

        List<DefectResp> list = selectList(defectReq);
        return list.isEmpty() ? CollUtil.getFirst(list) : new DefectResp();
    }

    /**
     * 功能描述：新增缺陷记录
     *
     * @param defect 缺陷记录
     * @author huise23
     * @date 2025/04/11 23:17
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
     * @date 2025/04/11 23:17
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
     * @date 2025/04/11 23:17
     **/
    @Override
    public void deleteById(String defectId) {
//            todo 校验
        removeById(defectId);
    }

}
