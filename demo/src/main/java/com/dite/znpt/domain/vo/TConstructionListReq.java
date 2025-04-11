package com.dite.znpt.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 施工信息请求实体
 */
@Data
@ApiModel("施工信息列表请求实体")
public class TConstructionListReq implements Serializable {

    private static final long serialVersionUID = -20191616975838303L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("施工信息Id")
    private String constructionId;

    @ApiModelProperty("项目id")
    private String projectId;

    @ApiModelProperty("机组id")
    private String turbineCode;

    @ApiModelProperty("作业开始时间")
    private Date startTime;

    @ApiModelProperty("作业结束时间")
    private Date endTime;

    @ApiModelProperty("温度(℃)")
    private BigDecimal temperature;

    @ApiModelProperty("风速(m/s)")
    private BigDecimal windSpeed;

    @ApiModelProperty("采集图片数量")
    private Integer imageCount;

    @ApiModelProperty("天气id")
    private String weatherCode;

    @ApiModelProperty("施工状态")
    private String statusId;

    @ApiModelProperty("当前时间")
    private Date createdAt;

}

