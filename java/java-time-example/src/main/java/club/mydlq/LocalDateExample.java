package club.mydlq;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期对象（不记录时区）示例
 *
 * @author mydlq
 */
public class LocalDateExample {

    /**
     * 日期转换
     */
    public static void localDateTransition() {
        /* 在默认时区中从系统时钟获取当前日期 */
        LocalDate localDate = LocalDate.now();
        /* 日期转换 */
        // 使用指定的格式化程序格式化此日期
        String formatLocalDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        // 指定年、月、日参数获取日期实例
        LocalDate ymdLocalDate = LocalDate.of(2020, 6, 18);
        // 指定年和年中的天数参数获取日期实例
        LocalDate ydLocalDate = LocalDate.ofYearDay(2020, 100);
        // 通过纪元时(天)获取日期实例（从1970-01-01起）
        LocalDate epochDayLocalDate = LocalDate.ofEpochDay(10);
        /* 输出 */
        System.out.println("在默认时区中从系统时钟获取当前日期：" + localDate);
        System.out.println("使用指定的格式化程序格式化此日期：" + formatLocalDate);
        System.out.println("根据设置年、月、日参数获取日期实例：" + ymdLocalDate);
        System.out.println("指定年和年中的天数参数获取日期实例：" + ydLocalDate);
        System.out.println("通过纪元时(天)获取日期实例：" + epochDayLocalDate);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 日期比较
     */
    public static void localDateCompare() {
        /* 创建两个日期实例 */
        LocalDate localDate1 = LocalDate.of(2020, 6, 18);
        LocalDate localDate2 = LocalDate.of(2020, 6, 17);
        /* LocalDate 日期比较  */
        // 比较两个 LocalDate 日期，如果返回是正数则大于，否则相反
        int difference = localDate1.compareTo(localDate2);
        // 检查此日期是否在指定的日期之后
        boolean isAfter = localDate1.isAfter(localDate2);
        boolean isBefore = localDate1.isBefore(localDate2);
        /* 输出 */
        System.out.println("比较 LocalDate 对象的差值：" + difference);
        System.out.println("检查此日期是否在指定的日期之后：" + isAfter);
        System.out.println("检查此日期是否在指定的日期之前：" + isBefore);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 日期计算
     */
    public static void localDateCalculation() {
        /* 在默认时区中从系统时钟获取当前日期 */
        LocalDate localDate = LocalDate.now();
        /* 日期计算 */
        // 获取在当前日期基础(增加)指定(天数)的日期
        LocalDate plusLocalDate = localDate.plusDays(10);
        // 获取在当前日期基础(减少)指定(天数)的日期
        LocalDate minusLocalDate = localDate.minusDays(10);
        /* 输出 */
        System.out.println("获取在当前日期基础(增加)指定(天数)的日期：" + plusLocalDate);
        System.out.println("获取在当前日期基础(减少)指定(天数)的日期：" + minusLocalDate);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 检查是否为闰年
     */
    public static void checkLeapYear() {
        LocalDate localDate = LocalDate.now();
        boolean isLeapYear = localDate.isLeapYear();
        System.out.println(isLeapYear ? "今年是闰年" : "今年不是闰年");
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        // 日期转换
        localDateTransition();
        // 日期比较
        localDateCompare();
        // 日期计算
        localDateCalculation();
        // 检查是否为闰年
        checkLeapYear();
    }

}
