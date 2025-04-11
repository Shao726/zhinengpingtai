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
 * @Description: 机组-项目关联表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("turbine")
@ApiModel(value="TurbineEntity对象", description="机组-项目关联表")
public class TurbineEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -38088185643803512L;

    @ApiModelProperty("机组号")
    @TableId(value = "turbine_id", type = IdType.ASSIGN_ID)
    private String turbineId;

    @ApiModelProperty("项目id")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty("机组名称")
    @TableField("turbine_name")
    private String turbineName;

    @ApiModelProperty("机组描述")
    @TableField("turbine_desc")
    private String turbineDesc;

    @ApiModelProperty("机组厂商")
    @TableField("manufacturer")
    private String manufacturer;

    @ApiModelProperty("机组型号")
    @TableField("model")
    private String model;
}

