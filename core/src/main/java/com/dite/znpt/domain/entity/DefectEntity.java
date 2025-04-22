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
 * @Description: 缺陷记录表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("defect")
@ApiModel(value="DefectEntity对象", description="缺陷记录表")
public class DefectEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 272430541345740192L;

    @ExcelProperty("缺陷id")
    @ApiModelProperty("缺陷id")
    @TableId(value = "defect_id", type = IdType.ASSIGN_ID)
    private String defectId;

    @ExcelProperty("机组id")
    @ApiModelProperty("机组id")
    @TableField("turbine_code")
    private String turbineCode;

    @ExcelProperty("叶片号")
    @ApiModelProperty("叶片号")
    @TableField("blade_code")
    private String bladeCode;

    @ExcelProperty("损伤面，字典surface_type")
    @ApiModelProperty("损伤面，字典surface_type")
    @TableField("surface_type")
    private String surfaceType;

    @ExcelProperty("缺陷类型，字典defect_type")
    @ApiModelProperty("缺陷类型，字典defect_type")
    @TableField("defect_type")
    private String defectType;

    @ExcelProperty("危重等级，字典defect_level")
    @ApiModelProperty("危重等级，字典defect_level")
    @TableField("defect_level")
    private String defectLevel;

    @ExcelProperty("轴向长度(mm)")
    @ApiModelProperty("轴向长度(mm)")
    @TableField("axial_length")
    private Object axialLength;

    @ExcelProperty("弦向长度(mm)")
    @ApiModelProperty("弦向长度(mm)")
    @TableField("chord_length")
    private Object chordLength;

    @ExcelProperty("图片路径")
    @ApiModelProperty("图片路径")
    @TableField("image_path")
    private String imagePath;

    @ExcelProperty("图片详情")
    @ApiModelProperty("图片详情")
    @TableField("image_hash")
    private String imageHash;
}

