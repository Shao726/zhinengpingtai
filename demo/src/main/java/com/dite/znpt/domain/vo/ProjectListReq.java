package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/09 14:39
 * @Description: 项目信息请求实体
 */
@Data
@ApiModel("项目信息列表请求实体")
public class ProjectListReq implements Serializable {

    private static final long serialVersionUID = -18175772339033037L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("项目信息Id")
    private String projectId;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("风场名称")
    private String farmName;

    @ApiModelProperty("风场地址")
    private String farmAddress;

    @ApiModelProperty("${column.comment}")
    private String clientId;

    @ApiModelProperty("xx，字典xx")
    private String methodId;

    @ApiModelProperty("项目规模")
    private String scale;

    @ApiModelProperty("风机型号")
    private String turbineModel;

}

