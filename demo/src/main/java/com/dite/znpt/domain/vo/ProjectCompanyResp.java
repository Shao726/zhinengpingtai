package com.dite.znpt.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dite.znpt.domain.entity.ProjectCompanyEntity;

/**
 * @author huise23
 * @date 2025/04/09 14:39
 * @Description: 项目-公司关联信息响应实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("项目-公司关联信息响应实体")
public class ProjectCompanyResp extends ProjectCompanyEntity {
}

