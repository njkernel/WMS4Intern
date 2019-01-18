package com.connext.wms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Chao.Sun
 * @Date: 2019/1/18 14:55
 * @Version 1.0
 */
public class Validate {

    //手机号正则表达式
    public static final String STR = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";

    //中文正则表达式
    public static final String REG = "[\\u4e00-\\u9fa5]{4,8}$";

    //验证手机号是否符合规范
    public static Boolean checkPhone(String contactWay){
        Pattern p = Pattern.compile(STR);
        Matcher m = p.matcher(contactWay);
        return m.matches();
    }

    //验证公司名称是否符合规范
    public static Boolean checkName(String expressCompany){
        Pattern p = Pattern.compile(REG);
        Matcher m = p.matcher(expressCompany);
        return m.matches();
    }
}
