package com.dite.znpt.domain.vo;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/09 14:39
 * @Description: 施工信息请求实体
 */
@Data
@ApiModel("施工信息列表请求实体")
public class TConstructionListReq implements Serializable {

    private static final long serialVersionUID = -21906313443453398L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("施工信息Id")
    private String constructionId;

    @ApiModelProperty("项目id")
    private String projectId;

    @ApiModelProperty("机组id")
    private String turbineCode;

    @ApiModelProperty("作业开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("作业结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("温度(℃)")
    private Double temperature;

    @ApiModelProperty("风速(m/s)")
    private Double windSpeed;

    @ApiModelProperty("采集图片数量")
    private Integer imageCount;

    @ApiModelProperty("天气id")
    private String weatherCode;

    @ApiModelProperty("${column.comment}")
    private String statusId;

    @ApiModelProperty("${column.comment}")
    private LocalDateTime createdAt;

}

