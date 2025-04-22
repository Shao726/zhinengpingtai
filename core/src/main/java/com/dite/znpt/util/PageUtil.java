package com.dite.znpt.util;

import com.dite.znpt.domain.page.PageDomain;
import com.dite.znpt.domain.page.TableSupport;
import com.github.pagehelper.PageHelper;

/**
 * @author cuizhibin
 */
public class PageUtil extends PageHelper {

    public static void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }

    public static void clearPage() {
        PageHelper.clearPage();
    }

}
