package com.example.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {
    /**
     * 判断字符串是不是double型
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]+[.]{0,1}[0-9]*[dD]{0,1}");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
