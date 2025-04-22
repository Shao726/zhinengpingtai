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
 * @Description: 公司信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_company")
@ApiModel(value="TCompanyEntity对象", description="公司信息表")
public class TCompanyEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 348031306551320799L;

    @ExcelProperty("公司id")
    @ApiModelProperty("公司id")
    @TableId(value = "company_id", type = IdType.ASSIGN_ID)
    private String companyId;

    @ExcelProperty("公司名称")
    @ApiModelProperty("公司名称")
    @TableField("company_name")
    private String companyName;

    @ExcelProperty("公司地址")
    @ApiModelProperty("公司地址")
    @TableField("address")
    private String address;

    @ExcelProperty("联系人")
    @ApiModelProperty("联系人")
    @TableField("contact")
    private String contact;

    @ExcelProperty("电话")
    @ApiModelProperty("电话")
    @TableField("phone")
    private String phone;
}

