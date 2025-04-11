package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 项目信息请求实体
 */
@Data
@ApiModel("项目信息列表请求实体")
public class ProjectListReq implements Serializable {

    private static final long serialVersionUID = -74121355744234753L;

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

    @ApiModelProperty("检查方式，字典method_id")
    private String methodId;

    @ApiModelProperty("项目规模")
    private String scale;

    @ApiModelProperty("风机型号")
    private String turbineModel;

    @ApiModelProperty("项目状态，字典project_status")
    private String status;

}

