package com.dite.znpt.domain.page;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.Objects;

/**
 * 分页数据
 *
 * @author kyland
 */
@Data
public class PageDomain {
    /**
     * 当前记录起始索引
     */
    private Integer pageNum;

    /**
     * 每页显示记录数
     */
    private Integer pageSize;

    /**
     * 排序列
     */
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    private String isAsc = "asc";

    /**
     * 分页参数合理化
     */
    private Boolean reasonable = true;

    public String getOrderBy() {
        if (StrUtil.isEmpty(orderByColumn)) {
            return "";
        }
        return StrUtil.toUnderlineCase(orderByColumn) + " " + isAsc;
    }

    public void setIsAsc(String isAsc) {
        if (StrUtil.isNotEmpty(isAsc)) {
            // 兼容前端排序类型
            if ("ascending".equals(isAsc)) {
                isAsc = "asc";
            } else if ("descending".equals(isAsc)) {
                isAsc = "desc";
            }
            this.isAsc = isAsc;
        }
    }

    public boolean getReasonable() {
        return BooleanUtil.isTrue(reasonable)|| Objects.isNull(reasonable);
    }

    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
    }
}
