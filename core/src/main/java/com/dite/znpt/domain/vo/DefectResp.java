package com.dite.znpt.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dite.znpt.domain.entity.DefectEntity;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 缺陷记录响应实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("缺陷记录响应实体")
public class DefectResp extends DefectEntity {
}

