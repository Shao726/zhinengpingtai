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
 * @Description: 机组信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("crew")
@ApiModel(value="CrewEntity对象", description="机组信息表")
public class CrewEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -18989296759149249L;

    @ExcelProperty("机组id")
    @ApiModelProperty("机组id")
    @TableId(value = "crew_id", type = IdType.ASSIGN_ID)
    private String crewId;

    @ExcelProperty("项目id")
    @ApiModelProperty("项目id")
    @TableField("project_id")
    private String projectId;

    @ExcelProperty("机组名称")
    @ApiModelProperty("机组名称")
    @TableField("crew_name")
    private String crewName;

    @ExcelProperty("机组描述")
    @ApiModelProperty("机组描述")
    @TableField("crew_desc")
    private String crewDesc;

    @ExcelProperty("机组厂商")
    @ApiModelProperty("机组厂商")
    @TableField("manufacturer")
    private String manufacturer;

    @ExcelProperty("机组型号")
    @ApiModelProperty("机组型号")
    @TableField("model")
    private String model;
}

