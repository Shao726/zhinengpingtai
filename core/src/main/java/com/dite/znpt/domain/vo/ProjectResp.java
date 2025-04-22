package com.dite.znpt.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dite.znpt.domain.entity.ProjectEntity;

/**
 * @author huise23
 * @date 2025/04/11 23:17
 * @Description: 项目信息响应实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("项目信息响应实体")
public class ProjectResp extends ProjectEntity {
}

