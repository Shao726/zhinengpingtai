package com.dite.znpt.domain.vo;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dite.znpt.domain.entity.TConstructionEntity;

/**
 * @author huise23
 * @date 2025/04/09 14:39
 * @Description: 施工信息响应实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("施工信息响应实体")
public class TConstructionResp extends TConstructionEntity {
}

