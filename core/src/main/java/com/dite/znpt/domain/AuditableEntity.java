package com.dite.znpt.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 统一定义顶层Entity实体审计 基类
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
public class AuditableEntity implements Serializable {
    private static final long serialVersionUID = 141481953116476081L;

//    @ApiParam(hidden = true)
//    private String createBy;
//
//    @ApiParam(hidden = true)
//    private String updateBy;
//
//    @ApiModelProperty(value = "创建时间", example = "2022-01-22", notes = "创建时间", hidden = true)
//    private Date createTime;
//
//    @ApiModelProperty(hidden = true)
//    private Date updateTime;

    @ExcelIgnore
    @ApiModelProperty(value = "id集合", example = "[]", notes = "id集合")
    private List<String> idList;

    @ExcelIgnore
    @ApiModelProperty(value = "当前页", example = "1", notes = "0")
    int page = 1;

    @ExcelIgnore
    @ApiModelProperty(value = "页大小", example = "10", notes = "10")
    int pageSize = 10;
}
