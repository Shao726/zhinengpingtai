package com.dite.znpt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dite.znpt.domain.entity.WeatherTypeEntity;
import com.dite.znpt.domain.vo.WeatherTypeListReq;
import com.dite.znpt.domain.vo.WeatherTypeResp;
import com.dite.znpt.service.WeatherTypeService;
import com.dite.znpt.mapper.WeatherTypeMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import com.dite.znpt.util.PageUtil;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 天气类型表服务实现类
 */
@Service
@RequiredArgsConstructor
public class WeatherTypeServiceImpl extends ServiceImpl<WeatherTypeMapper, WeatherTypeEntity> implements WeatherTypeService {

    /**
     * 功能描述：查询天气类型列表
     *
     * @param weatherTypeReq 天气类型信息
     * @return {@link List }<{@link WeatherTypeResp }>
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public List<WeatherTypeResp> selectList(WeatherTypeListReq weatherTypeReq) {
        PageUtil.startPage();
        List<WeatherTypeResp> weatherTypeList= this.baseMapper.queryBySelective(weatherTypeReq);
        weatherTypeList.forEach(resp -> {
            
        });
        return weatherTypeList;
    }

    /**
     * 功能描述：查询单条天气类型
     *
     * @param weatherCode 天气类型Id
     * @return {@link WeatherTypeResp }
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public WeatherTypeResp selectById(String weatherCode) {
        WeatherTypeListReq weatherTypeReq = new WeatherTypeListReq();
        weatherTypeReq.setWeatherCode(weatherCode);

        List<WeatherTypeResp> list = selectList(weatherTypeReq);
        return list.isEmpty() ? CollUtil.getFirst(list) : new WeatherTypeResp();
    }

    /**
     * 功能描述：新增天气类型
     *
     * @param weatherType 天气类型
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void saveData(WeatherTypeEntity weatherType) {
//            todo 校验
        save(weatherType);
    }

    /**
     * 功能描述：更新天气类型
     *
     * @param weatherType 天气类型
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void updateData(WeatherTypeEntity weatherType) {
//            todo 校验
        updateById(weatherType);
    }

    /**
     * 功能描述：删除天气类型
     *
     * @param weatherCode 天气类型Id
     * @author huise23
     * @date 2025/04/11 23:17
     **/
    @Override
    public void deleteById(String weatherCode) {
//            todo 校验
        removeById(weatherCode);
    }

}
