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
 * @Description: 表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("part")
@ApiModel(value="PartEntity对象", description="表")
public class PartEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -53853862365306266L;

    @ExcelProperty("部件id")
    @ApiModelProperty("部件id")
    @TableId(value = "part_id", type = IdType.ASSIGN_ID)
    private String partId;

    @ExcelProperty("项目id")
    @ApiModelProperty("项目id")
    @TableField("project_id")
    private String projectId;

    @ExcelProperty("机组id")
    @ApiModelProperty("机组id")
    @TableField("crew_id")
    private String crewId;

    @ExcelProperty("名称")
    @ApiModelProperty("名称")
    @TableField("part_name")
    private String partName;

    @ExcelProperty("编号")
    @ApiModelProperty("编号")
    @TableField("part_code")
    private String partCode;

    @ExcelProperty("类型，字典part_type")
    @ApiModelProperty("类型，字典part_type")
    @TableField("part_type")
    private String partType;

    @ExcelProperty("描述")
    @ApiModelProperty("描述")
    @TableField("part_desc")
    private String partDesc;

    @ExcelProperty("厂商")
    @ApiModelProperty("厂商")
    @TableField("manufacturer")
    private String manufacturer;

    @ExcelProperty("型号")
    @ApiModelProperty("型号")
    @TableField("model")
    private String model;
}

