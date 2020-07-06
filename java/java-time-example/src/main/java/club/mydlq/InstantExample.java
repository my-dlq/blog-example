package club.mydlq;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 时刻示例
 *
 * @author mydlq
 */
public class InstantExample {

    /**
     * 获取时刻并进行转换
     */
    public static void instantTransition() {
        /* 获取时刻 */
        // 获取当前时刻
        Instant instant = Instant.now();
        // 获取当前时刻的纳秒
        long microsecond = instant.getNano();
        // 获取当前时刻按纪元时转换后的毫秒(从1970-01-01T00:00:00Z.起)
        long millisecond = instant.toEpochMilli();
        // 获取当前时刻按纪元时转换后的秒(从1970-01-01T00:00:00Z.起)
        long second = instant.getEpochSecond();
        /* 输出 */
        System.out.println("获取当前时刻：" + instant);
        System.out.println("获取当前时间的纳秒：" + microsecond);
        System.out.println("获取当前时刻按纪元时转换后的毫秒：" + millisecond);
        System.out.println("获取当前时刻按纪元时转换后的秒：" + second);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 时刻比较
     */
    public static void instantCompare() {
        /* 根据毫秒创建两个时刻 */
        Instant instant1 = Instant.ofEpochMilli(1591113400000L);
        Instant instant2 = Instant.ofEpochMilli(1591113410000L);
        /* 两个时刻间比较 */
        // 比较两个 Instant 时刻，如果返回是正数则大于，否则相反
        int difference = instant1.compareTo(instant2);
        // 比较 instant1 是否在 instant2 时刻之后
        boolean isAfter = instant1.isAfter(instant2);
        // 比较 instant1 是否在 instant2 时刻之前
        boolean isBefore = instant1.isBefore(instant2);
        /* 输出 */
        System.out.println("比较 instant1 是否在 instant2 时刻之后：" + isAfter);
        System.out.println("比较 instant1 是否在 instant2 时刻之前：" + isBefore);
        System.out.println("比较 Instant 对象的差值：" + difference);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 时刻计算
     */
    public static void instantCalculation() {
        /* 获取当前时刻 */
        Instant instant = Instant.now();
        /* 时刻计算 */
        // 计算3天前的时间
        Instant instantPlus = instant.plus(3, ChronoUnit.DAYS);
        // 计算6天前的时间
        Instant instantMinus = instant.minus(6, ChronoUnit.DAYS);
        System.out.println("计算3天后的时刻：" + instantPlus);
        System.out.println("计算6天前的时刻：" + instantMinus);
        /* 根据毫秒创建两个时刻 */
        Instant instant1 = Instant.ofEpochMilli(1591113400000L);
        Instant instant2 = Instant.ofEpochMilli(1591113410000L);
        // 计算两个 Instant 之间的秒数（如果返回负数则大于，返回负数则小于）
        long diffAsMinutes = ChronoUnit.SECONDS.between(instant1, instant2);
        /* 输出 */
        System.out.println("计算两个 Instant 之间的分钟数：" + diffAsMinutes);
        System.out.println("-------------------------------------------------");
    }

    /**
     * Instant 用于时刻转换的静态方法
     */
    public static void instantStaticMethod() {
        /* 时刻的静态转换方法 */
        // 通过纪元时(秒)获取时刻（从1970-01-01T00:00:00Z.起）
        Instant epochSecond = Instant.ofEpochSecond(1591113458L);
        // 通过纪元时(秒)获取时刻并设置纳秒（从1970-01-01T00:00:00Z.起）
        Instant epochSecondNano = Instant.ofEpochSecond(1591113458L, 100);
        // 通过纪元时(毫秒)获取时刻（从1970-01-01T00:00:00Z.起）
        Instant epochMilli = Instant.ofEpochMilli(1591113458000L);
        // 通过纪元时(毫秒)将 Date 转换为 Instant 对象
        Date date = new Date();
        Instant dateInstant = Instant.ofEpochMilli(date.getTime());
        // 通过纪元时(毫秒)将 Instant 转换为 Date 对象
        Date instantDate = new Date(Instant.now().toEpochMilli());
        /* 输出 */
        System.out.println("通过纪元时(秒)获取时刻：" + epochSecond);
        System.out.println("通过纪元时(秒)获取时刻并设置纳秒：" + epochSecondNano);
        System.out.println("通过纪元时(毫秒)获取时刻" + epochMilli);
        System.out.println("Date 转换 Instant 对象：" + dateInstant);
        System.out.println("Instant 转换 Date 对象：" + instantDate);
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        // 获取时刻并进行转换
        instantTransition();
        // 时刻比较
        instantCompare();
        // 时刻计算
        instantCalculation();
        // Instant 用于时刻转换的静态方法
        instantStaticMethod();
    }

}
