package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 文件信息请求实体
 */
@Data
@ApiModel("文件信息列表请求实体")
public class FileInfoListReq implements Serializable {

    private static final long serialVersionUID = 574018420246881535L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("文件信息Id")
    private Long fileId;

    @ApiModelProperty("业务id")
    private String businessId;

    @ApiModelProperty("minio相对路径")
    private String minioPath;

    @ApiModelProperty("业务类型，字典file_business_type")
    private String businessType;

}

