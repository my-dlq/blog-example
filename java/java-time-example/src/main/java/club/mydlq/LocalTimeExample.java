package club.mydlq;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间对象（不记录时区）示例
 *
 * @author mydlq
 */
public class LocalTimeExample {

    /**
     * 时间转换
     */
    public static void localTimeTransition() {
        /* 在默认时区中从系统时钟获取当前时间 */
        LocalTime localTime = LocalTime.now();
        /* 时间转换 */
        // 使用指定的格式化程序格式化此时间
        String formatLocalTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        // 指定时、分参数获取时间实例
        LocalTime hmLocalTime = LocalTime.of(12, 30);
        // 指定时、分、秒参数获取时间实例
        LocalTime hmsLocalTime = LocalTime.of(12, 30, 20);
        // 指定时、分、秒、纳秒参数获取时间实例
        LocalTime hmsnLocalTime = LocalTime.of(12, 30, 20, 10000);
        // 指定秒参数获取时间实例
        LocalTime secondOfDayLocalTime = LocalTime.ofSecondOfDay(3600);
        /* 输出 */
        System.out.println("在默认时区中从系统时钟获取当前时间：" + localTime);
        System.out.println("使用指定的格式化程序格式化此时间：" + formatLocalTime);
        System.out.println("指定时、分参数获取时间实例：" + hmLocalTime);
        System.out.println("指定时、分、秒参数获取时间实例：" + hmsLocalTime);
        System.out.println("指定时、分、秒、纳秒参数获取时间实例：" + hmsnLocalTime);
        System.out.println("指定秒参数获取时间实例：" + secondOfDayLocalTime);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 时间比较
     */
    public static void localTimeCompare() {
        /* 创建两个时间实例 */
        LocalTime localTime1 = LocalTime.of(12, 30);
        LocalTime localTime2 = LocalTime.of(12, 25);
        /* 时间比较 */
        // 比较两个 LocalTime 时间，如果返回是正数则大于，否则相反
        int difference = localTime1.compareTo(localTime2);
        // 检查此时间是否在指定的时间之后
        boolean isAfter = localTime1.isAfter(localTime2);
        boolean isBefore = localTime1.isBefore(localTime2);
        /* 输出 */
        System.out.println("比较 LocalTime 对象的差值：" + difference);
        System.out.println("检查此时间是否在指定的时间之后：" + isAfter);
        System.out.println("检查此时间是否在指定的时间之前：" + isBefore);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 时间计算
     */
    public static void localTimeCalculation() {
        /* 在默认时区中从系统时钟获取当前时间 */
        LocalTime localTime = LocalTime.now();
        /* 时间计算 */
        // 获取在当前时间基础(增加)指定(秒)的时间
        LocalTime plusSecondsLocalTime = localTime.plusSeconds(10);
        // 获取在当前时间基础(增加)指定(分钟)的时间
        LocalTime plusMinutesLocalTime = localTime.plusMinutes(10);
        // 获取在当前时间基础(增加)指定(小时)的时间
        LocalTime plusHoursLocalTime = localTime.plusHours(10);
        // 获取在当前时间基础(减少)指定(秒)的时间
        LocalTime minusSecondsLocalTime = localTime.minusSeconds(10);
        // 获取在当前时间基础(减少)指定(分钟)的时间
        LocalTime minusMinutesLocalTime = localTime.minusMinutes(10);
        // 获取在当前时间基础(减少)指定(小时)的时间
        LocalTime minusHoursLocalTime = localTime.minusHours(10);
        /* 输出 */
        System.out.println("获取在当前时间基础(增加)指定(秒)的时间：" + plusSecondsLocalTime);
        System.out.println("获取在当前时间基础(增加)指定(分钟)的时间：" + plusMinutesLocalTime);
        System.out.println("获取在当前时间基础(增加)指定(小时)的时间：" + plusHoursLocalTime);
        System.out.println("获取在当前时间基础(减少)指定(秒)的时间：" + minusSecondsLocalTime);
        System.out.println("获取在当前时间基础(减少)指定(分钟)的时间：" + minusMinutesLocalTime);
        System.out.println("获取在当前时间基础(减少)指定(小时)的时间：" + minusHoursLocalTime);
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        /* 时间转换 */
        localTimeTransition();
        /* 时间比较 */
        localTimeCompare();
        /* 时间计算 */
        localTimeCalculation();
    }

}
