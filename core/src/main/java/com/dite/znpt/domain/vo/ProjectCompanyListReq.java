package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 项目-公司关联信息请求实体
 */
@Data
@ApiModel("项目-公司关联信息列表请求实体")
public class ProjectCompanyListReq implements Serializable {

    private static final long serialVersionUID = 598388421634125600L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("项目-公司关联信息Id")
    private String projectId;

    @ApiModelProperty("公司id")
    private String companyId;

    @ApiModelProperty("单位类型（检查/委托）")
    private String relationType;

}

