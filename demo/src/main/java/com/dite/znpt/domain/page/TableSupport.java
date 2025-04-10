package com.dite.znpt.domain.page;

import cn.hutool.core.convert.Convert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 表格数据处理
 *
 * @author kyland
 */
public class TableSupport {
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 分页参数合理化
     */
    public static final String REASONABLE = "reasonable";

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(Convert.toInt(getRequest().getParameter(PAGE_NUM), 1));
        pageDomain.setPageSize(Convert.toInt(getRequest().getParameter(PAGE_SIZE), 10));
        pageDomain.setOrderByColumn(getRequest().getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(getRequest().getParameter(IS_ASC));
        pageDomain.setReasonable(Convert.toBool(getRequest().getParameter(REASONABLE)));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes)attributes;
        } catch (Exception var1) {
            return null;
        }
    }

    public static HttpServletRequest getRequest() {
        try {
            return getRequestAttributes().getRequest();
        } catch (Exception var1) {
            return null;
        }
    }
}
