package com.dite.znpt.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.dite.znpt.domain.AuditableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 人员信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("personnel")
@ApiModel(value="PersonnelEntity对象", description="人员信息表")
public class PersonnelEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -62273861742734411L;

    @ExcelProperty("用户名")
    @ApiModelProperty("用户名")
    @TableId(value = "person_id", type = IdType.ASSIGN_ID)
    private String personId;

    @ExcelProperty("姓名")
    @ApiModelProperty("姓名")
    @TableField("name")
    private String name;

    @ExcelProperty("角色类型，字典role_type")
    @ApiModelProperty("角色类型，字典role_type")
    @TableField("role_type")
    private String roleType;

    @ExcelProperty("公司id")
    @ApiModelProperty("公司id")
    @TableField("company_id")
    private String companyId;

    @ExcelProperty("密码")
    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ExcelProperty("1正常0禁用")
    @ApiModelProperty("1正常0禁用")
    @TableField("status")
    private Integer status;
}

