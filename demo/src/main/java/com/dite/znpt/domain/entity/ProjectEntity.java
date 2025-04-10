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
 * @date 2025/04/10 13:31
 * @Description: 项目信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project")
@ApiModel(value="ProjectEntity对象", description="项目信息表")
public class ProjectEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 676511262353737882L;

    @ApiModelProperty("项目id")
    @TableId(value = "project_id", type = IdType.ASSIGN_ID)
    private String projectId;

    @ApiModelProperty("项目名称")
    @TableField("project_name")
    private String projectName;

    @ApiModelProperty("风场名称")
    @TableField("farm_name")
    private String farmName;

    @ApiModelProperty("风场地址")
    @TableField("farm_address")
    private String farmAddress;

    @ApiModelProperty("委托单位")
    @TableField("client_id")
    private String clientId;

    @ApiModelProperty("检查方式，字典method_id")
    @TableField("method_id")
    private String methodId;

    @ApiModelProperty("项目规模")
    @TableField("scale")
    private String scale;

    @ApiModelProperty("风机型号")
    @TableField("turbine_model")
    private String turbineModel;
}


