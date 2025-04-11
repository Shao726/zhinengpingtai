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
 * @Description: 项目信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project")
@ApiModel(value="ProjectEntity对象", description="项目信息表")
public class ProjectEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -38332266915294799L;

    @ExcelProperty("项目id")
    @ApiModelProperty("项目id")
    @TableId(value = "project_id", type = IdType.ASSIGN_ID)
    private String projectId;

    @ExcelProperty("项目名称")
    @ApiModelProperty("项目名称")
    @TableField("project_name")
    private String projectName;

    @ExcelProperty("风场名称")
    @ApiModelProperty("风场名称")
    @TableField("farm_name")
    private String farmName;

    @ExcelProperty("风场地址")
    @ApiModelProperty("风场地址")
    @TableField("farm_address")
    private String farmAddress;

    @ExcelProperty("检查方式，字典method_id")
    @ApiModelProperty("检查方式，字典method_id")
    @TableField("method_id")
    private String methodId;

    @ExcelProperty("项目规模")
    @ApiModelProperty("项目规模")
    @TableField("scale")
    private String scale;

    @ExcelProperty("风机型号")
    @ApiModelProperty("风机型号")
    @TableField("turbine_model")
    private String turbineModel;

    @ExcelProperty("项目状态，字典project_status")
    @ApiModelProperty("项目状态，字典project_status")
    @TableField("status")
    private String status;
}

