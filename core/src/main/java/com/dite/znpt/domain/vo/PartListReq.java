package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 请求实体
 */
@Data
@ApiModel("列表请求实体")
public class PartListReq implements Serializable {

    private static final long serialVersionUID = 118135700439578757L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("Id")
    private String partId;

    @ApiModelProperty("项目id")
    private String projectId;

    @ApiModelProperty("机组id")
    private String crewId;

    @ApiModelProperty("名称")
    private String partName;

    @ApiModelProperty("编号")
    private String partCode;

    @ApiModelProperty("类型，字典part_type")
    private String partType;

    @ApiModelProperty("描述")
    private String partDesc;

    @ApiModelProperty("厂商")
    private String manufacturer;

    @ApiModelProperty("型号")
    private String model;

}

