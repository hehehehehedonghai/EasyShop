package com.shop.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check
 *
 * @author Yarn
 * @create 2017/11/11/10:12
 */
public class Check {
    /**
     * 检查字符串是否符合规则
     * @param str 要检查的字符串
     * @param MATCHING_RULE 传入的正则表达式
     * @return
     */
    public static boolean checkString(String str, String MATCHING_RULE){
        //正则表达式的模式
        Pattern p = Pattern.compile(MATCHING_RULE);
        //正则表达式的匹配器
        Matcher m = p.matcher(str);
        //进行正则匹配
        return m.matches();
    }
}
