package com.dite.znpt.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.dite.znpt.domain.AuditableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huise23
 * @date 2025/04/09 14:39
 * @Description: 天气类型表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("weather_type")
@ApiModel(value="WeatherTypeEntity对象", description="天气类型表")
public class WeatherTypeEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 736371527640486906L;

    @ApiModelProperty("天气类型id")
    @TableId(value = "weather_code", type = IdType.ASSIGN_ID)
    private String weatherCode;

    @ApiModelProperty("天气类型中文描述")
    @TableField("chinese_name")
    private String chineseName;

    @ApiModelProperty("对施工进度的影响系数")
    @TableField("impact_factor")
    private Double impactFactor;
}

