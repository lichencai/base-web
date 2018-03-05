package com.zriot.ebike.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtil {
    private static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String yyyyMMdd 	= "yyyyMMdd";
    public static final String yyyyMMdd2 = "yyyy-MM-dd";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    private static final Format format = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final String yyyyMMddHHmm 	= "yyyy-MM-dd HH:mm";

    /**
     * 获取日期对象Date
     * @return
     */
    public static Date now(){
        return new Date();
    }
    /**
     * 获取当前时间int，与格林尼治时间差/1000
     * 1487924483
     * @return
     */
    public static int getCurrentTimeByInt(){
        return (int) ((now().getTime())/1000);
    }

    public static int getTimeByInt(Date date){
        return (int) ((date.getTime())/1000);
    }
    /**
     * 获取毫秒
     * @return
     */
    public static long milSeconds() {
        return  now().getTime();
    }
    /**
     * 获取当前时间String类型
     * yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTimeByStr(){
        return sdf.format(now());
    }
    /**
     * 获取今天零点的时间 秒
     * 1492358400
     * @return
     */
    public static int getTodyZeroTimeByInt() {
        long zero = now().getTime()/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();
        return (int) (zero/1000);
    }

    /**
     * 获取今天零点的时间 秒
     * 1492358400
     * @return
     */
    public static String getTodyZeroTimeByStr() {
        long zero = now().getTime()/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();
        Integer time = (int) (zero/1000);
        return sdf.format(intToDate(time));
    }

    /**
     * int类型转String类型
     * @param time
     * @return
     */
    public static String intToStr(int time) {
        String dateStr = sdf.format((long)time * 1000);
        return dateStr;
    }

    /**
     * int类型转换为date类型
     * @param time
     * @return
     */
    public static Date intToDate(int time){
        try {
            return sdf.parse(intToStr(time));
        } catch (ParseException e) {
            logger.error("intToDate时间格式转换错误：{}", e);
            return null;
        }
    }

    /**
     * String类型转换为Date类型
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateStr) throws ParseException {
        return sdf.parse(dateStr);
    }

    /**
     * date类型转换为string类型
     * Fri Feb 24 16:21:23 CST 2017
     * "2017-02-24 16:21:23"
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        return sdf.format(date);
    }

    public static String dateToStr(Date date, String format) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * date转换为int时间戳
     * @param date
     * @return
     */
    public static int dateToInt(Date date) {
        return (int) (date.getTime()/1000);
    }

    /**
     * string转换为int类型
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static int strToInt(String dateStr) throws ParseException {
        Date date = sdf.parse(dateStr);
        return dateToInt(date);
    }

    /**
     * 比较int类型时间戳大小，t1>t2,返回true,否则，返回false
     * @param time1
     * @param time2
     * @return
     */
    public static boolean compareByInt(int time1, int time2){
        if (time1 > time2){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 比较时间大小，string类型，varchar1 > varchar2，返回true
     * @param varchar1
     * @param varchar2
     * @return
     */
    public static boolean compareByStr(String varchar1, String varchar2){
        try {
            Date date1 = sdf.parse(varchar1);
            Date date2 = sdf.parse(varchar2);
            if (date1.getTime() > date2.getTime()){
                return true;
            }else {
                return false;
            }
        } catch (ParseException e) {
            logger.error("时间转换出现异常:{}", e);
            return false;
        }
    }

    /**
     * 比较时间大小，date类型，date1 > date2，返回true
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareByDate (Date date1, Date date2){
        if (date1.getTime() > date2.getTime()){
            return true;
        }else {
            return false;
        }
    }

    public static int getAssignDateZeroTime(int timestamp) {
        Long time = new Long(timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time * 1000 + 24 * 60 * 60 * 1000));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return (int)(calendar.getTime().getTime() / 1000);
    }
    /**
     * int类型转14位String类型
     * @param time
     * @return
     */
    public static String intToWholeStr(int time) {
        String dateStr = format.format((long)time * 1000);
        return dateStr;
    }

    public static int getYear(){
        Calendar a=Calendar.getInstance();
        return a.get(Calendar.YEAR);
    }

    public static String getWeekByInt (int time) {
        //String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(intToDate(time));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    /**
     *
     *
     * @param millis 日期之后的毫秒数
     * @return 当前日期后面多少毫秒的日期，Date
     */
    public static Date getAfterDate(long millis) {
        long curren = System.currentTimeMillis();
        curren += millis;
        return new Date(curren);
    }
    /**
     *
     *
     * @param h 日期之前的小时数
     * @return 日期前面几小时的日期，Date
     */
    public static Date getBeforeDate(double h) {
        long curren = System.currentTimeMillis();
        curren -= h * 60 * 60* 1000;
        return new Date(curren);
    }

    public static Date add(Date date, int num, int field) {
        if (date == null) {
            throw new RuntimeException("日期不能为空.");
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, num);

        return c.getTime();
    }

    public static Date addDays(Date date, int num) {
        return add(date, num, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取交易时间流水
     * yyyyMMddHHmmss
     * 20170815105522
     * @throws ParseException
     */
    public static String getTradeTimeStr() {
        return format.format(new Date());
    }
}
