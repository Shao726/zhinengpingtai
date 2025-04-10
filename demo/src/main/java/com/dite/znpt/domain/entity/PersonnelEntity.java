package com.dite.znpt.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.dite.znpt.domain.AuditableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huise23
 * @date 2025/04/09 14:39
 * @Description: 人员信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("personnel")
@ApiModel(value="PersonnelEntity对象", description="人员信息表")
public class PersonnelEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 351217700568175283L;

    @ApiModelProperty("用户名")
    @TableId(value = "person_id", type = IdType.ASSIGN_ID)
    private String personId;

    @ApiModelProperty("姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty("角色类型，字典role_type")
    @TableField("role_type")
    private String roleType;

    @ApiModelProperty("公司id")
    @TableField("company_id")
    private String companyId;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;
}

