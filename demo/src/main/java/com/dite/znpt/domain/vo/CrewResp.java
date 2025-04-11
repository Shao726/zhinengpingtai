package com.dite.znpt.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dite.znpt.domain.entity.CrewEntity;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 机组信息响应实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("机组信息响应实体")
public class CrewResp extends CrewEntity {
}

