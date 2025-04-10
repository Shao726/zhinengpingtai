package com.dite.znpt.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dite.znpt.domain.entity.TCompanyEntity;

/**
 * @author huise23
 * @date 2025/04/09 14:39
 * @Description: 公司信息响应实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("公司信息响应实体")
public class TCompanyResp extends TCompanyEntity {
}

