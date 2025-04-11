package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 人员信息请求实体
 */
@Data
@ApiModel("人员信息列表请求实体")
public class PersonnelListReq implements Serializable {

    private static final long serialVersionUID = -10363935468256543L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("人员信息Id")
    private String personId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("角色类型，字典role_type")
    private String roleType;

    @ApiModelProperty("公司id")
    private String companyId;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("1正常0禁用")
    private Integer status;

}

