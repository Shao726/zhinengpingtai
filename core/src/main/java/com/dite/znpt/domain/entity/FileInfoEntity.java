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
 * @Description: 文件信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("file_info")
@ApiModel(value="FileInfoEntity对象", description="文件信息表")
public class FileInfoEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -14287111699285655L;

    @ExcelProperty("文件id")
    @ApiModelProperty("文件id")
    @TableId(value = "file_id", type = IdType.ASSIGN_ID)
    private Long fileId;

    @ExcelProperty("业务id")
    @ApiModelProperty("业务id")
    @TableField("business_id")
    private String businessId;

    @ExcelProperty("minio相对路径")
    @ApiModelProperty("minio相对路径")
    @TableField("minio_path")
    private String minioPath;

    @ExcelProperty("业务类型，字典file_business_type")
    @ApiModelProperty("业务类型，字典file_business_type")
    @TableField("business_type")
    private String businessType;
}

