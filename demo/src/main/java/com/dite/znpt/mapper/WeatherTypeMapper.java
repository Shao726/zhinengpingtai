package com.dite.znpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dite.znpt.domain.entity.WeatherTypeEntity;
import com.dite.znpt.domain.vo.WeatherTypeListReq;
import com.dite.znpt.domain.vo.WeatherTypeResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huise23
 * @date 2025/04/09 11:48
 * @Description: 天气类型表数据库访问层
 */
public interface WeatherTypeMapper extends BaseMapper<WeatherTypeEntity> {
    List<WeatherTypeResp> queryBySelective(WeatherTypeListReq weatherTypeReq);
}

