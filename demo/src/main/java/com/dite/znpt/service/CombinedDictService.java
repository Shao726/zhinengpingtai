package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.CombinedDictEntity;
import com.dite.znpt.domain.vo.CombinedDictListReq;
import com.dite.znpt.domain.vo.CombinedDictResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 字典表服务接口
 */
public interface CombinedDictService extends IService<CombinedDictEntity> {

    /**
     * 功能描述：查询字典列表
     *
     * @param combinedDictReq 字典
     * @return {@link List }<{@link CombinedDictEntity }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    PageInfo<CombinedDictResp> selectList(CombinedDictListReq combinedDictReq);

    /**
     * 功能描述：查询单条字典
     *
     * @param dictId 字典Id
     * @return {@link CombinedDictResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    CombinedDictResp selectById(String dictId);

    /**
     * 功能描述：新增字典
     *
     * @param combinedDict 字典
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void saveData(CombinedDictEntity combinedDict);

    /**
     * 功能描述：更新字典
     *
     * @param combinedDict 字典
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void updateData(CombinedDictEntity combinedDict);

    /**
     * 功能描述：删除字典
     *
     * @param dictId 字典Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void deleteById(String dictId);
}

