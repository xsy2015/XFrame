package com.xsy.xframe.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description描述:
 * @Author作者: xsy
 * @Date日期: 2018/11/14
 */

public class DateUtils {
    public static String getCurrentDate(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    public static String dateToString(java.util.Date dateDate) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(dateDate);
            return dateString;
         }
}
