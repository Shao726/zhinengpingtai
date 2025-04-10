package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.TConstructionEntity;
import com.dite.znpt.domain.vo.TConstructionListReq;
import com.dite.znpt.domain.vo.TConstructionResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 施工信息表服务接口
 */
public interface TConstructionService extends IService<TConstructionEntity> {

    /**
     * 功能描述：查询施工信息列表
     *
     * @param tConstructionReq 施工信息
     * @return {@link List }<{@link TConstructionEntity }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    PageInfo<TConstructionResp> selectList(TConstructionListReq tConstructionReq);

    /**
     * 功能描述：查询单条施工信息
     *
     * @param constructionId 施工信息Id
     * @return {@link TConstructionResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    TConstructionResp selectById(String constructionId);

    /**
     * 功能描述：新增施工信息
     *
     * @param tConstruction 施工信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void saveData(TConstructionEntity tConstruction);

    /**
     * 功能描述：更新施工信息
     *
     * @param tConstruction 施工信息
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void updateData(TConstructionEntity tConstruction);

    /**
     * 功能描述：删除施工信息
     *
     * @param constructionId 施工信息Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void deleteById(String constructionId);
}

