package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.TCompanyEntity;
import com.dite.znpt.domain.vo.TCompanyListReq;
import com.dite.znpt.domain.vo.TCompanyResp;
import com.dite.znpt.service.TCompanyService;
import com.dite.znpt.mapper.TCompanyMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 公司信息表服务实现类
 */
@Service
@RequiredArgsConstructor
public class TCompanyServiceImpl extends ServiceImpl<TCompanyMapper, TCompanyEntity> implements TCompanyService {

    /**
     * 功能描述：查询公司信息列表
     *
     * @param tCompanyReq 公司信息信息
     * @return {@link List }<{@link TCompanyResp }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public List<TCompanyResp> selectList(TCompanyListReq tCompanyReq) {
        PageUtil.startPage();
        List<TCompanyResp> tCompanyList= this.baseMapper.queryBySelective(tCompanyReq);
        tCompanyList.forEach(resp -> {
            
        });
        return tCompanyList;
    }

    /**
     * 功能描述：查询单条公司信息
     *
     * @param companyId 公司信息Id
     * @return {@link TCompanyResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public TCompanyResp selectById(String companyId) {
        TCompanyListReq tCompanyReq = new TCompanyListReq();
        tCompanyReq.setCompanyId(companyId);

        List<TCompanyResp> list = selectList(tCompanyReq);
        return list.isEmpty() ? CollUtil.getFirst(list) : new TCompanyResp();
    }

    /**
     * 功能描述：新增公司信息
     *
     * @param tCompany 公司信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void saveData(TCompanyEntity tCompany) {
//            todo 校验
        save(tCompany);
    }

    /**
     * 功能描述：更新公司信息
     *
     * @param tCompany 公司信息
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void updateData(TCompanyEntity tCompany) {
//            todo 校验
        updateById(tCompany);
    }

    /**
     * 功能描述：删除公司信息
     *
     * @param companyId 公司信息Id
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void deleteById(String companyId) {
//            todo 校验
        removeById(companyId);
    }

}
