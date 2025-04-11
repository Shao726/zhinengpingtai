package com.dite.znpt.domain.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.dite.znpt.domain.AuditableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 天气类型表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("weather_type")
@ApiModel(value="WeatherTypeEntity对象", description="天气类型表")
public class WeatherTypeEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -74787545166894805L;

    @ExcelProperty("天气类型id")
    @ApiModelProperty("天气类型id")
    @TableId(value = "weather_code", type = IdType.ASSIGN_ID)
    private String weatherCode;

    @ExcelProperty("天气类型中文描述")
    @ApiModelProperty("天气类型中文描述")
    @TableField("chinese_name")
    private String chineseName;

    @ExcelProperty("对施工进度的影响系数")
    @ApiModelProperty("对施工进度的影响系数")
    @TableField("impact_factor")
    private BigDecimal impactFactor;
}

