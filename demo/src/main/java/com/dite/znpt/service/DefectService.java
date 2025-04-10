package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.DefectEntity;
import com.dite.znpt.domain.vo.DefectListReq;
import com.dite.znpt.domain.vo.DefectResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 缺陷记录表服务接口
 */
public interface DefectService extends IService<DefectEntity> {

    /**
     * 功能描述：查询缺陷记录列表
     *
     * @param defectReq 缺陷记录
     * @return {@link List }<{@link DefectEntity }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    PageInfo<DefectResp> selectList(DefectListReq defectReq);

    /**
     * 功能描述：查询单条缺陷记录
     *
     * @param defectId 缺陷记录Id
     * @return {@link DefectResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    DefectResp selectById(String defectId);

    /**
     * 功能描述：新增缺陷记录
     *
     * @param defect 缺陷记录
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void saveData(DefectEntity defect);

    /**
     * 功能描述：更新缺陷记录
     *
     * @param defect 缺陷记录
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void updateData(DefectEntity defect);

    /**
     * 功能描述：删除缺陷记录
     *
     * @param defectId 缺陷记录Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void deleteById(String defectId);
}

