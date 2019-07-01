package com.cp.app.core.comm.uitl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName DateUtil
 * @Description TODO 时间工具类
 * @createdate 2019/6/20 星期四 10:01
 */
public class DateUtil {
    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";
    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String CST_TIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";
    /**
     * 时间转换
     * @param date
     * @param dateFormatType
     * @return
     */
    public static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatType, Locale.CHINA);
        return simpleDateFormat.format(date);
    }
    /**
     * 时间转换
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CST_TIME_PATTERN, Locale.US);
        Date usDate = simpleDateFormat.parse(date);
        return DateUtil.getDateFormat(usDate, format);
    }
    /**
     * 获取当前时间搓
     * @return
     */
    public static long getNowTime(){
        Calendar calendar =  Calendar.getInstance();
        return  calendar.getTimeInMillis();
    }
    /**
     * 获取当前时间
     * @param format 转换格式
     * @return
     */
    public static String getNowTime(String format){
        Calendar calendar =  Calendar.getInstance();
        Date date = calendar.getTime();
        if(null==format||"".equals(format)) format = DateUtil.FULL_TIME_SPLIT_PATTERN;
        return  getDateFormat(date,format);
    }
}
