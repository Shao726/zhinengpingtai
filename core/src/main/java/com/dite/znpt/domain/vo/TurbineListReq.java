package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 机组-项目关联请求实体
 */
@Data
@ApiModel("机组-项目关联列表请求实体")
public class TurbineListReq implements Serializable {

    private static final long serialVersionUID = 141552424945497346L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("机组-项目关联Id")
    private String turbineCode;

    @ApiModelProperty("项目id")
    private String projectId;

}

