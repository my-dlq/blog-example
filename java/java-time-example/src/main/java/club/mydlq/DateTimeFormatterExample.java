package club.mydlq;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 时间日期格式化示例
 *
 * @author mydlq
 */
public class DateTimeFormatterExample {

    /**
     * 时间对象转为字符串(无时区)
     */
    public static void dateToString() {
        /* 创建无时区的时间日期实例 */
        LocalDateTime localDateTime = LocalDateTime.now();
        /* 创建时间格式化对象并指定"时间转换格式"，然后对时间日期对象进行转换*/
        // 创建时间格式化对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        // 将时间对象转换为字符串
        String localDateTimeString = formatter.format(localDateTime);
        /* 输出 */
        System.out.println("时间对象转为字符串(无时区)：" + localDateTimeString);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 时间对象转为字符串(带时区)
     */
    public static void zonedDateToString() {
        /* 创建带时区的时间日期实例 */
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        /* 创建时间格式化对象并指定"时间转换格式"与"时区"，然后对时间日期对象进行转换 */
        // 创建时间格式化对象
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        // 将时间对象转换为字符串
        String zonedDateTimeString = dateTimeFormatter.format(zonedDateTime);
        /* 输出 */
        System.out.println("时间对象转为字符串(带时区)：" + zonedDateTimeString);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 字符串转为时间对象(无时区)
     */
    public static void stringToDate() {
        /* 创建时间字符串 */
        String dateString = "2020-06-20T10:20:30";
        /* 创建时间格式化对象并指定"时间转换格式"，然后将字符串转换为时间日期对象 */
        // 创建时间格式化对象，并指定时间转换格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        // 将字符串转换为时间对象
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        /* 输出 */
        System.out.println("字符串转为时间对象(无时区)：" + localDateTime);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 字符串转为时间对象(带时区)
     */
    public static void stringToZonedDate() {
        /* 创建时间字符串 */
        String dateString = "2020-06-20T10:20:30+0800";
        /* 创建时间格式化对象并指定"时间转换格式"，然后将字符串转换为时间日期对象，并指定时区 */
        // 创建时间格式化对象，并指定时间转换格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        // 设置时区ID
        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
        // 将字符串转换为时间对象，并指定时区
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter).withZoneSameInstant(zoneId);
        /* 输出 */
        System.out.println("字符串转为时间对象(带时区)：" + zonedDateTime);
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        // 时间对象转为字符串(无时区)
        dateToString();
        // 时间对象转为字符串(带时区)
        zonedDateToString();
        // 字符串转为时间对象(无时区)
        stringToDate();
        // 字符串转为时间对象(带时区)
        stringToZonedDate();
    }

}