package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.TurbineEntity;
import com.dite.znpt.domain.vo.TurbineListReq;
import com.dite.znpt.domain.vo.TurbineResp;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 机组-项目关联表服务接口
 */
public interface TurbineService extends IService<TurbineEntity> {

    /**
     * 功能描述：查询机组-项目关联列表
     *
     * @param turbineReq 机组-项目关联
     * @return {@link List }<{@link TurbineEntity }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    List<TurbineResp> selectList(TurbineListReq turbineReq);

    /**
     * 功能描述：查询单条机组-项目关联
     *
     * @param turbineCode 机组-项目关联Id
     * @return {@link TurbineResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    TurbineResp selectById(String turbineCode);

    /**
     * 功能描述：新增机组-项目关联
     *
     * @param turbine 机组-项目关联
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void saveData(TurbineEntity turbine);

    /**
     * 功能描述：更新机组-项目关联
     *
     * @param turbine 机组-项目关联
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void updateData(TurbineEntity turbine);

    /**
     * 功能描述：删除机组-项目关联
     *
     * @param turbineCode 机组-项目关联Id
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void deleteById(String turbineCode);
}

