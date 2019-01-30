package com.connext.wms.api.util;

/**
 * @Author: Marcus
 * @Date: 2019/1/29 14:00
 * @Version 1.0
 */
public class APIDataCheck {
    private static final int MIN_LENGTH = 0;
    private static final int MAX_LENGTH = 30;

    public static boolean check(String inRepoId, String orderId, String channelId, String expressId, String expressCompany, String detailDTOS) {
        return ((inRepoId.length() <= MIN_LENGTH || inRepoId.length() > MAX_LENGTH)&&(orderId.length() <= MIN_LENGTH || orderId.length() > MAX_LENGTH)&&(channelId.length() <= MIN_LENGTH || channelId.length() > MAX_LENGTH)&&(expressId.length() <= MIN_LENGTH || expressId.length() > MAX_LENGTH)&&(expressCompany.length() <= MIN_LENGTH || expressCompany.length() > MAX_LENGTH) &&(detailDTOS.length() <= 2));
    }
}
