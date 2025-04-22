package com.dite.znpt.domain.vo;

import java.math.BigDecimal;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 天气类型请求实体
 */
@Data
@ApiModel("天气类型列表请求实体")
public class WeatherTypeListReq implements Serializable {

    private static final long serialVersionUID = 902447691868906681L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("天气类型Id")
    private String weatherCode;

    @ApiModelProperty("天气类型中文描述")
    private String chineseName;

    @ApiModelProperty("对施工进度的影响系数")
    private BigDecimal impactFactor;

}

