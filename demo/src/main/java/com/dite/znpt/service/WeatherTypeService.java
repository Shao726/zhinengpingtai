package com.dite.znpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dite.znpt.domain.entity.WeatherTypeEntity;
import com.dite.znpt.domain.vo.WeatherTypeListReq;
import com.dite.znpt.domain.vo.WeatherTypeResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 天气类型表服务接口
 */
public interface WeatherTypeService extends IService<WeatherTypeEntity> {

    /**
     * 功能描述：查询天气类型列表
     *
     * @param weatherTypeReq 天气类型
     * @return {@link List }<{@link WeatherTypeEntity }>
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    PageInfo<WeatherTypeResp> selectList(WeatherTypeListReq weatherTypeReq);

    /**
     * 功能描述：查询单条天气类型
     *
     * @param weatherCode 天气类型Id
     * @return {@link WeatherTypeResp }
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    WeatherTypeResp selectById(String weatherCode);

    /**
     * 功能描述：新增天气类型
     *
     * @param weatherType 天气类型
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void saveData(WeatherTypeEntity weatherType);

    /**
     * 功能描述：更新天气类型
     *
     * @param weatherType 天气类型
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void updateData(WeatherTypeEntity weatherType);

    /**
     * 功能描述：删除天气类型
     *
     * @param weatherCode 天气类型Id
     * @author huise23
     * @date 2025/04/09 11:48
     **/
    void deleteById(String weatherCode);
}

