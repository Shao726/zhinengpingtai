package com.dite.znpt.domain.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.dite.znpt.domain.AuditableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sakura
 * @date 2025/04/10 13:35
 * @Description: 施工信息表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_construction")
@ApiModel(value="TConstructionEntity对象", description="施工信息表")
public class TConstructionEntity extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 295094962841990277L;

    @ApiModelProperty("施工id")
    @TableId(value = "construction_id", type = IdType.ASSIGN_ID)
    private String constructionId;

    @ApiModelProperty("项目id")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty("机组id")
    @TableField("turbine_code")
    private String turbineCode;

    @ApiModelProperty("作业开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @ApiModelProperty("作业结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty("温度(℃)")
    @TableField("temperature")
    private Double temperature;

    @ApiModelProperty("风速(m/s)")
    @TableField("wind_speed")
    private Double windSpeed;

    @ApiModelProperty("采集图片数量")
    @TableField("image_count")
    private Integer imageCount;

    @ApiModelProperty("天气id")
    @TableField("weather_code")
    private String weatherCode;

    @ApiModelProperty("施工状态")
    @TableField("status_id")
    private String statusId;

    @ApiModelProperty("当前时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
}

