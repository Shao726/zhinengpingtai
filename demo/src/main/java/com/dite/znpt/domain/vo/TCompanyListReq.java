package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/09 14:39
 * @Description: 公司信息请求实体
 */
@Data
@ApiModel("公司信息列表请求实体")
public class TCompanyListReq implements Serializable {

    private static final long serialVersionUID = 948606394919949641L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("公司信息Id")
    private String companyId;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司地址")
    private String address;

}

