package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.PartEntity;
import com.dite.znpt.domain.vo.PartListReq;
import com.dite.znpt.domain.vo.PartResp;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 表服务接口
 */
public interface PartService extends IService<PartEntity> {

    /**
     * 功能描述：查询列表
     *
     * @param partReq 
     * @return {@link List }<{@link PartEntity }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    List<PartResp> selectList(PartListReq partReq);

    /**
     * 功能描述：查询单条
     *
     * @param partId Id
     * @return {@link PartResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    PartResp selectById(String partId);

    /**
     * 功能描述：新增
     *
     * @param part 
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void saveData(PartEntity part);

    /**
     * 功能描述：更新
     *
     * @param part 
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void updateData(PartEntity part);

    /**
     * 功能描述：删除
     *
     * @param partId Id
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void deleteById(String partId);
}

