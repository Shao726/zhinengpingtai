package com.dite.znpt.domain.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 缺陷记录请求实体
 */
@Data
@ApiModel("缺陷记录列表请求实体")
public class DefectListReq implements Serializable {

    private static final long serialVersionUID = 585419070823466048L;

    @ApiModelProperty("查询关键字")
    private String keyword;

    @ApiModelProperty("缺陷记录Id")
    private String defectId;

    @ApiModelProperty("机组id")
    private String turbineCode;

    @ApiModelProperty("叶片号")
    private String bladeCode;

    @ApiModelProperty("损伤面，字典surface_type")
    private String surfaceType;

    @ApiModelProperty("缺陷类型，字典defect_type")
    private String defectType;

    @ApiModelProperty("危重等级，字典defect_level")
    private String defectLevel;

    @ApiModelProperty("轴向长度(mm)")
    private Object axialLength;

    @ApiModelProperty("弦向长度(mm)")
    private Object chordLength;

    @ApiModelProperty("图片路径")
    private String imagePath;

    @ApiModelProperty("图片详情")
    private String imageHash;

}

