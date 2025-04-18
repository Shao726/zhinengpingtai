package com.dite.znpt.monitor.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2023/09/05 16:39
 * @Description: 监控设备IP配置表实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("vs_ip_config")
@ApiModel(value="VsIpConfigEntity对象", description="监控设备IP配置表")
public class IpConfigEntity implements Serializable {

    @ApiModelProperty("${column.comment}")
    @TableId(type = IdType.AUTO)
    private Long configId;

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("ip地址前三位")
    private String ipTopThree;

   
}

