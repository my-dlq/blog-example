package club.mydlq;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.ValueRange;
import java.util.Date;

/**
 * 日期时间对象（不记录时区）示例
 *
 * @author mydlq
 */
public class LocalDateTimeExample {

    /**
     * 时间转换
     */
    public static void localDateTimeTransition() {
        /* 在默认时区中从系统时钟获取当前时间日期 */
        LocalDateTime localDateTime = LocalDateTime.now();
        /* 在指定时区中从系统时钟获取当前时间日期*/
        LocalDateTime localDateTimeZone = LocalDateTime.now(ZoneId.of("+08:00"));
        /* 时间转换 */
        // 获取该日期时间是属于该(年)的第几天
        int dayOfYear = localDateTime.getDayOfYear();
        // 获取该日期时间是属于该(月)的第几天
        int dayOfMonth = localDateTime.getDayOfMonth();
        // 获取该日期时间是属于该(周)的第几天
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        // 从时间日期中获取(年)信息
        int year = localDateTime.getYear();
        // 从时间日期中获取(月)信息
        Month month = localDateTime.getMonth();
        // 从时间日期中获取(小时)信息
        int hour = localDateTime.getHour();
        // 从时间日期中获取(分钟)信息
        int minute = localDateTime.getMinute();
        // 从时间日期中获取(秒)信息
        int second = localDateTime.getSecond();
        // 从时间日期中获取(纳秒)信息
        int nano = localDateTime.getNano();
        // 使用指定的格式化程序格式化时间日期
        String formatLocalDateTimeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 使用制度的格式格式化字符串到时间日期
        LocalDateTime fromatLocalDateTime = LocalDateTime.parse("2020-09-18 10:30:50",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 指定年、月、日、时、分参数获取日期时间实例
        LocalDateTime ymdhmLocalDateTime = LocalDateTime.of(2020, 6, 18, 12, 30);
        // 指定年、月、日、时、分、秒参数获取日期时间实例
        LocalDateTime ymdhmsLocalDateTime = LocalDateTime.of(2020, 6, 18, 12, 30, 20);
        // 指定使用纪元时(秒)参数获取时间实例
        LocalDateTime epochSecondLocalDateTime = LocalDateTime.ofEpochSecond(30 * 366 * 24 * 60 * 60, 0, ZoneOffset.UTC);
        /* 输出 */
        System.out.println("在默认时区中从系统时钟获取当前时间日期：" + localDateTime);
        System.out.println("在指定时区中从系统时钟获取当前时间日期：" + localDateTimeZone);
        System.out.println("获取该日期时间是属于该(年)的第几天：" + dayOfYear);
        System.out.println("获取该日期时间是属于该(月)的第几天：" + dayOfMonth);
        System.out.println("获取该日期时间是属于该(周)信息,名：" + dayOfWeek.name() + "天值" + dayOfWeek.getValue());
        System.out.println("从时间日期中获取(年)信息：" + year);
        System.out.println("从时间日期中获取(月份)信息,名：" + month.name() + "月份值" + month.getValue());
        System.out.println("从时间日期中获取(小时)信息：" + hour);
        System.out.println("从时间日期中获取(分钟)信息：" + minute);
        System.out.println("从时间日期中获取(秒)信息：" + second);
        System.out.println("从时间日期中获取(纳秒)信息：" + nano);
        System.out.println("在默认时区中从系统时钟获取当前日期时间：" + localDateTime);
        System.out.println("使用指定的格式化程序格式化时间日期：" + formatLocalDateTimeStr);
        System.out.println("使用制度的格式格式化字符串到时间日期：" + fromatLocalDateTime);
        System.out.println("指定年、月、日、时、分参数获取日期时间实例：" + ymdhmLocalDateTime);
        System.out.println("指定年、月、日、时、分、秒参数获取日期时间实例：" + ymdhmsLocalDateTime);
        System.out.println("指定使用纪元时(秒)参数获取时间实例：" + epochSecondLocalDateTime);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 日期时间比较
     */
    public static void localDateTimeCompare() {
        /* 创建两个日期时间实例 */
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 6, 18, 11, 30);
        LocalDateTime localDateTime2 = LocalDateTime.of(2020, 6, 18, 12, 30);
        /* 日期时间比较 */
        // 比较两个 LocalDateTime 日期时间，如果返回是正数则大于，否则相反
        int difference = localDateTime1.compareTo(localDateTime2);
        // 检查此日期时间是否在指定的日期之后
        boolean isAfter = localDateTime1.isAfter(localDateTime2);
        boolean isBefore = localDateTime1.isBefore(localDateTime2);
        // 以指定单位计算直到另一个日期时间的时间量并指定时间单位
        long until = localDateTime1.until(localDateTime2, ChronoUnit.SECONDS);
        /* 输出 */
        System.out.println("比较 LocaDatelTime 对象的差值：" + difference);
        System.out.println("检查此日期时间是否在指定的时间之后：" + isAfter);
        System.out.println("检查此日期时间是否在指定的时间之前：" + isBefore);
        System.out.println("以指定单位计算直到另一个日期时间的时间量并指定时间单位：" + until);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 日期时间计算
     */
    public static void localDateTimeCalculation() {
        /* 获取日期时间对象 */
        // 在默认时区中从系统时钟获取当前日期时间
        LocalDateTime localDateTime = LocalDateTime.now();
        // 获取在当前日期时间基础(增加)指定(秒)的日期时间
        LocalDateTime plusSecondsLocalDateTime = localDateTime.plusSeconds(30);
        // 获取在当前日期时间基础(增加)指定(分钟)的日期时间
        LocalDateTime plusMinutesLocalDateTime = localDateTime.plusMinutes(10);
        // 获取在当前日期时间基础(增加)指定(小时)的日期时间
        LocalDateTime plusHoursLocalDateTime = localDateTime.plusHours(10);
        // 获取在当前日期时间基础(增加)指定(天)的日期时间
        LocalDateTime plusDaysLocalDateTime = localDateTime.plusDays(10);
        // 获取在当前日期时间基础(增加)指定(周)的日期时间
        LocalDateTime plusWeeksLocalTime = localDateTime.plusWeeks(1);
        // 获取在当前日期时间基础(增加)指定(年)的日期时间
        LocalDateTime plusYearsLocalTime = localDateTime.plusYears(1);
        // 获取在当前日期时间基础(减少)指定(秒)的日期时间
        LocalDateTime minusSecondsLocalDateTime = localDateTime.minusSeconds(30);
        // 获取在当前日期时间基础(减少)指定(分钟)的日期时间
        LocalDateTime minusMinutesLocalTime = localDateTime.minusMinutes(30);
        // 获取在当前日期时间基础(减少)指定(小时)的日期时间
        LocalDateTime minusHoursLocalTime = localDateTime.minusHours(10);
        // 获取在当前日期时间基础(减少)指定(天)的日期时间
        LocalDateTime minusDaysLocalTime = localDateTime.minusDays(10);
        // 获取在当前日期时间基础(减少)指定(周)的日期时间
        LocalDateTime minusWeeksLocalTime = localDateTime.minusWeeks(1);
        // 获取在当前日期时间基础(减少)指定(年)的日期时间
        LocalDateTime minusYearsLocalTime = localDateTime.minusYears(1);
        /* 输出 */
        System.out.println("获取在当前日期时间基础(增加)指定(秒)的日期时间：" + plusSecondsLocalDateTime);
        System.out.println("获取在当前日期时间基础(增加)指定(分钟)的日期时间：" + plusMinutesLocalDateTime);
        System.out.println("获取在当前日期时间基础(增加)指定(小时)的日期时间：" + plusHoursLocalDateTime);
        System.out.println("获取在当前日期时间基础(增加)指定(天)的日期时间：" + plusDaysLocalDateTime);
        System.out.println("获取在当前日期时间基础(增加)指定(周)的日期时间：" + plusWeeksLocalTime);
        System.out.println("获取在当前日期时间基础(增加)指定(年)的日期时间：" + plusYearsLocalTime);
        System.out.println("获取在当前日期时间基础(减少)指定(秒)的日期时间：" + minusSecondsLocalDateTime);
        System.out.println("获取在当前日期时间基础(减少)指定(分钟)的日期时间：" + minusMinutesLocalTime);
        System.out.println("获取在当前日期时间基础(减少)指定(小时)的日期时间：" + minusHoursLocalTime);
        System.out.println("获取在当前日期时间基础(减少)指定(天)的日期时间：" + minusDaysLocalTime);
        System.out.println("获取在当前日期时间基础(减少)指定(周)的日期时间：" + minusWeeksLocalTime);
        System.out.println("获取在当前日期时间基础(减少)指定(年)的日期时间：" + minusYearsLocalTime);
        System.out.println("-------------------------------------------------");
    }

    /**
     * Instance 转换为 LocalDateTime 转换
     */
    public static void instantToLocalDateTime() {
        /* 创建 Instant 对象 */
        Instant instant = Instant.now();
        /* Instant 和 LocalDateTime 对象互转 */
        // 将 Instant 对象转换为 LocalDateTime 对象并设置时区
        LocalDateTime zoneLocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
        // 将 LocalDateTime 对象转换为 Instant
        Instant localDateTaimeToInstant = zoneLocalDateTime.toInstant(ZoneOffset.UTC);
        /* 输出 */
        System.out.println("将 Instant 对象转换为 LocalDateTime 对象并设置时区：" + zoneLocalDateTime);
        System.out.println("将 LocalDateTime 对象转换为 Instant：" + localDateTaimeToInstant);
        System.out.println("-------------------------------------------------");
    }

    /**
     * LocalTime 与 LocalDateTime 转换
     */
    public static void localTime() {
        /* 获取本地日期时间对象 */
        LocalDateTime localDateTime = LocalDateTime.now();
        /* 获取指定字段的有效值范围 */
        // 获取指定字段的有效值范围(天/年)
        ValueRange valueRangeDayOfYear = localDateTime.range(ChronoField.DAY_OF_YEAR);
        // 获取指定字段的有效值范围(天/星期)
        ValueRange valueRangeDayOfWeek = localDateTime.range(ChronoField.DAY_OF_WEEK);
        /* 输出 */
        System.out.println("获取指定字段的有效值范围，今年天数最大值：" + valueRangeDayOfYear.getMaximum());
        System.out.println("获取指定字段的有效值范围，今年天数最小值：" + valueRangeDayOfYear.getMinimum());
        System.out.println("获取指定字段的有效值范围，这星期数最大值：" + valueRangeDayOfWeek.getMaximum());
        System.out.println("获取指定字段的有效值范围，这星期数最小值：" + valueRangeDayOfWeek.getMinimum());
        System.out.println("-------------------------------------------------");
    }


    /**
     * LocalDateTime 与 Date 的相互转换
     */
    public static void localDateTimeAndDate() {
        // Date 转换为 LocalDateTime
        Date date1 = new Date();
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault());
        // LocalDateTime 转换为 Date
        LocalDateTime localDateTime2 = LocalDateTime.now();
        Date date2 = Date.from(localDateTime2.atZone(ZoneId.systemDefault()).toInstant());
        /* 输出 */
        System.out.println("Date 转换为 LocalDateTime：" + localDateTime1);
        System.out.println("LocalDateTime转换为Date ：" + date2);
    }

    public static void main(String[] args) {
        // 日期时间转换
        localDateTimeTransition();
        // 日期时间比较
        localDateTimeCompare();
        // 日期时间计算
        localDateTimeCalculation();
        // instant 转换为 LocalDateTime 对象
        instantToLocalDateTime();
        // LocalTime 与 LocalDateTime 转换
        localTime();
        // LocalDateTime 与 Date 的相互转换
        localDateTimeAndDate();
    }

}
