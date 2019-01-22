package com.connext.wms.util;

import java.util.List;

/**
 * @Author: Yogurt7_
 * @Date: 2019/1/17 14:59
 */
public class PageSet {
    public static Page setPage(List list, Integer currPage, Long lon) {
        Page page = new Page();
        if (currPage == null) {
            currPage = 1;
        }
        page.setTotalCount(lon);
        page.setCurrPage(currPage);
        page.init();
        page.setData(list);
        return page;
    }
}
