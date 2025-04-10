package com.dite.znpt.domain.entity;

import java.math.BigDecimal;
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
 * @Description: 缺陷记录表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("defect")
@ApiModel(value="DefectEntity对象", description="缺陷记录表")
public class DefectEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -74938532938992308L;

    @ApiModelProperty("缺陷id")
    @TableId(value = "defect_id", type = IdType.ASSIGN_ID)
    private String defectId;

    @ApiModelProperty("机组id")
    @TableField("turbine_code")
    private String turbineCode;

    @ApiModelProperty("叶片号")
    @TableField("blade_code")
    private String bladeCode;

    @ApiModelProperty("损伤面，字典surface_type")
    @TableField("surface_type")
    private String surfaceType;

    @ApiModelProperty("缺陷类型，字典defect_type")
    @TableField("defect_type")
    private String defectType;

    @ApiModelProperty("危重等级，字典defect_level")
    @TableField("defect_level")
    private String defectLevel;

    @ApiModelProperty("轴向长度(mm)")
    @TableField("axial_length")
    private BigDecimal axialLength;

    @ApiModelProperty("弦向长度(mm)")
    @TableField("chord_length")
    private BigDecimal chordLength;

    @ApiModelProperty("处理建议")
    @TableField("image_path")
    private String imagePath;

    @ApiModelProperty("图片详情")
    @TableField("image_hash")
    private String imageHash;
}

