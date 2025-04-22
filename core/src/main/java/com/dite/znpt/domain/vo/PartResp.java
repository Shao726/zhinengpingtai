package com.dite.znpt.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dite.znpt.domain.entity.PartEntity;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 响应实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("响应实体")
public class PartResp extends PartEntity {
}

