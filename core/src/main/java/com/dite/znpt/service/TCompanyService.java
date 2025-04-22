package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.TCompanyEntity;
import com.dite.znpt.domain.vo.TCompanyListReq;
import com.dite.znpt.domain.vo.TCompanyResp;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 公司信息表服务接口
 */
public interface TCompanyService extends IService<TCompanyEntity> {

    /**
     * 功能描述：查询公司信息列表
     *
     * @param tCompanyReq 公司信息
     * @return {@link List }<{@link TCompanyEntity }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    List<TCompanyResp> selectList(TCompanyListReq tCompanyReq);

    /**
     * 功能描述：查询单条公司信息
     *
     * @param companyId 公司信息Id
     * @return {@link TCompanyResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    TCompanyResp selectById(String companyId);

    /**
     * 功能描述：新增公司信息
     *
     * @param tCompany 公司信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void saveData(TCompanyEntity tCompany);

    /**
     * 功能描述：更新公司信息
     *
     * @param tCompany 公司信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void updateData(TCompanyEntity tCompany);

    /**
     * 功能描述：删除公司信息
     *
     * @param companyId 公司信息Id
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    void deleteById(String companyId);
}

