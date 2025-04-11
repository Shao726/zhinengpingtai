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
 * @Description: 公司信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_company")
@ApiModel(value="TCompanyEntity对象", description="公司信息表")
public class TCompanyEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -72804777209522073L;

    @ApiModelProperty("公司id")
    @TableId(value = "company_id", type = IdType.ASSIGN_ID)
    private String companyId;

    @ApiModelProperty("公司名称")
    @TableField("company_name")
    private String companyName;

    @ApiModelProperty("公司地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("联系人")
    @TableField("contact")
    private String contact;

    @ApiModelProperty("电话")
    @TableField("phone")
    private String phone;
}

