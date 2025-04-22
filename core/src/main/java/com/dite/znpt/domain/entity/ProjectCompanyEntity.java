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
 * @Description: 项目-公司关联信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project_company")
@ApiModel(value="ProjectCompanyEntity对象", description="项目-公司关联信息表")
public class ProjectCompanyEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -98876147355655939L;

    @ExcelProperty("项目id")
    @ApiModelProperty("项目id")
    @TableId(value = "project_id", type = IdType.ASSIGN_ID)
    private String projectId;

    @ExcelProperty("公司id")
    @ApiModelProperty("公司id")
    @TableField("company_id")
    private String companyId;

    @ExcelProperty("单位类型（检查/委托）")
    @ApiModelProperty("单位类型（检查/委托）")
    @TableField("relation_type")
    private String relationType;
}

