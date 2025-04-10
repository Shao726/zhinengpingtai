package com.dite.znpt.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dite.znpt.domain.entity.TurbineEntity;

/**
 * @author huise23
 * @date 2025/04/09 14:39
 * @Description: 机组-项目关联响应实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("机组-项目关联响应实体")
public class TurbineResp extends TurbineEntity {
}

