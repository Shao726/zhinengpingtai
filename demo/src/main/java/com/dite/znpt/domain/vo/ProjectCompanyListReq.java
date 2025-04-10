package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/09 14:39
 * @Description: 项目-公司关联信息请求实体
 */
@Data
@ApiModel("项目-公司关联信息列表请求实体")
public class ProjectCompanyListReq implements Serializable {

    private static final long serialVersionUID = -62483123283940291L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("项目-公司关联信息Id")
    private String projectId;

    @ApiModelProperty("公司id")
    private String companyId;

    @ApiModelProperty("关联类型")
    private String relationType;

}

