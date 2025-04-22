package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 字典请求实体
 */
@Data
@ApiModel("字典列表请求实体")
public class CombinedDictListReq implements Serializable {

    private static final long serialVersionUID = -71621812451720363L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("字典Id")
    private String dictId;

    @ApiModelProperty("字典类型")
    private String dictType;

    @ApiModelProperty("字典名称")
    private String dictName;

    @ApiModelProperty("父级id")
    private Long parentId;

    @ApiModelProperty("字典排序")
    private Integer sortOrder;

    @ApiModelProperty("是否字典终值")
    private Boolean finalState;

}

