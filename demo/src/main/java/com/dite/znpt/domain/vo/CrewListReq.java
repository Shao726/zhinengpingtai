package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 机组信息请求实体
 */
@Data
@ApiModel("机组信息列表请求实体")
public class CrewListReq implements Serializable {

    private static final long serialVersionUID = 322835125942914057L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("机组信息Id")
    private String crewId;

    @ApiModelProperty("项目id")
    private String projectId;

    @ApiModelProperty("机组名称")
    private String crewName;

    @ApiModelProperty("机组描述")
    private String crewDesc;

    @ApiModelProperty("机组厂商")
    private String manufacturer;

    @ApiModelProperty("机组型号")
    private String model;

}

