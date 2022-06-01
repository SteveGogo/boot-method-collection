package com.mylearn.sprbootfunction.Time;


import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author liyuan
 */
public class DateUtil {

    /**
     * 将Date转为yyyy-MM-dd HH:mm:ss格式字符串
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDateTime(Date date) {
        String dateTimeStr = "";
        // 因多线程安全问题，使用时才进行创建
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateTimeStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTimeStr;
    }

    /**
     * 将Date转为yyyy-MM-dd HH:mm:ss格式字符串
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDate(Date date) {

        String dateStr = "";
        // 因多线程安全问题，使用时才进行创建
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式字符串转为Date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(strDate);
    }

    /**
     * 将yyyy-MM-dd格式字符串转为Date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(strDate);
    }

    /**
     * 比较String类型时间
     *
     * @return -1/小于,0/等于,1/大于
     */
    public static int compareDateTimeStr(String dateTime1, String dateTime2) {

        // 若入参为空，默认返回-1/小于
        if (StringUtils.isEmpty(dateTime1) || StringUtils.isEmpty(dateTime2)) {
            return -1;
        }

        Date d1 = null;
        Date d2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            d1 = sdf.parse(dateTime1);
            d2 = sdf.parse(dateTime2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d1.compareTo(d2);
    }

    /**
     * 比较String类型时间
     *
     * @return -1/小于,0/等于,1/大于
     */
    public static int compareDateStr(String date1, String date2) {

        // 若入参为空，默认返回-1/小于
        if (StringUtils.isEmpty(date1) || StringUtils.isEmpty(date2)) {
            return -1;
        }

        Date d1 = null;
        Date d2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            d1 = sdf.parse(date1);
            d2 = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d1.compareTo(d2);
    }

    public static long getLastDayMilli(Date date) {
        if (date != null) {
            return date.getTime();
        }
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).plusDays(-1);
        return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();


    }

    public static long getLastDayForChronoField(Date date, TemporalField field) {
        if (date != null) {
            return date.getTime();
        }
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        int i = localDateTime.get(field);
        int s = 0;
        while (i != 1) {
            i--;
            s++;
        }
        LocalDateTime mon = localDateTime.plusDays(-s);
        return mon.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();

    }

    /**
     * 传入格式为 2022年04月14日
     *
     * @param date
     * @return
     */
    public static LocalDate getLocalDateFromChineseDate(String date) {
        if (date == null || "".equals(date)) {
            return null;
        }
        String[] yearSplit = date.split("年");
        String year = yearSplit[0];
        String[] monthSplit = yearSplit[1].split("月");
        String month = monthSplit[0];
        String day = monthSplit[1].split("日")[0];
        return LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));

    }

    public static LocalDate getLocalDate(long timestamp, ZoneOffset zoneOffset) {
        return getLocalDateTime(timestamp, zoneOffset).toLocalDate();
    }

    public static LocalDate getLocalDate(long timestamp) {
        return getLocalDate(timestamp, ZoneOffset.ofHours(8));
    }

    public static LocalDateTime getLocalDateTime(long timestamp, ZoneOffset zoneOffset) {
        return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, zoneOffset);
    }

    public static LocalDateTime getLocalDateTime(long timestamp) {
        return getLocalDateTime(timestamp, ZoneOffset.ofHours(8));
    }

    public static LocalDate getLocalDate(String date, String format) {

        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    public static LocalDateTime getLocalDatetime(String date, String format) {

        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
    }

    public static Long getTimestamp(LocalDate date) {

        return getTimestamp(LocalDateTime.of(date, LocalTime.MIN), ZoneOffset.ofHours(8));
    }

    public static Long getTimestamp(LocalDate date, ZoneOffset zoneOffset) {

        return getTimestamp(LocalDateTime.of(date, LocalTime.MIN), zoneOffset);
    }

    public static Long getTimestamp(LocalDateTime date, ZoneOffset zoneOffset) {

        return date.toInstant(zoneOffset).toEpochMilli();
    }

    public static Long getTimestamp(LocalDateTime date) {

        return getTimestamp(date, ZoneOffset.ofHours(8));
    }

    /**
     * 获取当前日期所在季度的开始日期和结束日期
     * 季度一年四季， 第一季度：1月-3月， 第二季度：4月-6月， 第三季度：7月-9月， 第四季度：10月-12月
     *
     * @param isFirst true表示查询本季度开始日期  false表示查询本季度结束日期
     * @return
     */
    public static LocalDate getStartOrEndDayOfQuarter(Boolean isFirst, LocalDate today) {
        LocalDate resDate;
        if (today == null) {
            today = LocalDate.now();
        }
        Month month = today.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), firstMonthOfQuarter, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(today.isLeapYear()));
        }
        return resDate;
    }

    public static LocalDate getStartDayOfQuarter(LocalDate today) {

        return getStartOrEndDayOfQuarter(true, today);
    }


    /**
     * 修改为一天的开始时间，例如：2020-02-02 00:00:00,000
     *
     * @param time 日期时间
     * @return 一天的开始时间
     */
    public static LocalDateTime beginOfDay(LocalDateTime time) {
        return time.with(LocalTime.MIN);
    }

    /**
     * 修改为一天的结束时间，例如：2020-02-02 23:59:59,999
     *
     * @param time 日期时间
     * @return 一天的结束时间
     */
    public static LocalDateTime endOfDay(LocalDateTime time) {
        return time.with(LocalTime.MAX);
    }
}
