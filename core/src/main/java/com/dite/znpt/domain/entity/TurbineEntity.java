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
 * @Description: 机组-项目关联表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("turbine")
@ApiModel(value="TurbineEntity对象", description="机组-项目关联表")
public class TurbineEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -46471985197840891L;

    @ExcelProperty("机组号")
    @ApiModelProperty("机组号")
    @TableId(value = "turbine_code", type = IdType.ASSIGN_ID)
    private String turbineCode;

    @ExcelProperty("项目id")
    @ApiModelProperty("项目id")
    @TableField("project_id")
    private String projectId;
}

