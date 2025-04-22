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
 * @Description: 字典表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("combined_dict")
@ApiModel(value="CombinedDictEntity对象", description="字典表")
public class CombinedDictEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -11768336612800273L;

    @ExcelProperty("字典id")
    @ApiModelProperty("字典id")
    @TableId(value = "dict_id", type = IdType.ASSIGN_ID)
    private String dictId;

    @ExcelProperty("字典类型")
    @ApiModelProperty("字典类型")
    @TableField("dict_type")
    private String dictType;

    @ExcelProperty("字典名称")
    @ApiModelProperty("字典名称")
    @TableField("dict_name")
    private String dictName;

    @ExcelProperty("父级id")
    @ApiModelProperty("父级id")
    @TableField("parent_id")
    private Long parentId;

    @ExcelProperty("字典排序")
    @ApiModelProperty("字典排序")
    @TableField("sort_order")
    private Integer sortOrder;

    @ExcelProperty("是否字典终值")
    @ApiModelProperty("是否字典终值")
    @TableField("final_state")
    private Boolean finalState;
}

