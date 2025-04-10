package com.dite.znpt.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.dite.znpt.domain.AuditableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sakura
 * @date 2025/04/10 13:42
 * @Description: 项目-公司关联信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project_company")
@ApiModel(value="ProjectCompanyEntity对象", description="项目-公司关联信息表")
public class ProjectCompanyEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 128834719624205451L;

    @ApiModelProperty("项目id")
    @TableId(value = "project_id", type = IdType.ASSIGN_ID)
    private String projectId;

    @ApiModelProperty("公司id")
    @TableField("company_id")
    private String companyId;

    @ApiModelProperty("单位类型（检查/委托）")
    @TableField("relation_type")
    private String relationType;
}