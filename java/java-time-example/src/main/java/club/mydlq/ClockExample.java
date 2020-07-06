package club.mydlq;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

/**
 * 时钟示例
 *
 * @author mydlq
 */
public class ClockExample {

    /**
     * 获取时钟
     */
    public static void getClock() {
        /* 获取时钟 */
        // 获取"系统时间"的时钟，并使用"UTC时区"对日期和时间进行转换
        Clock utcClock = Clock.systemUTC();
        // 获取"系统时间"的时钟，并使用"系统时区"对日期和时间进行转换
        Clock systemZoneClock = Clock.systemDefaultZone();
        // 获取"系统时间"的时钟，并使用"指定时区"对日期和时间进行转换
        Clock zoneClock = Clock.system(ZoneId.of("Asia/Shanghai"));
        // 获取"固定时刻"的时钟，并"指定时区"对日期和时间进行转换
        Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.of("Asia/Shanghai"));
        /* 输出 */
        System.out.println("获取\"系统时间\"的时钟，并使用\"UTC时区\"对日期和时间进行转换：" + utcClock);
        System.out.println("获取\"系统时间\"的时钟，并使用\"系统时区\"对日期和时间进行转换：" + systemZoneClock);
        System.out.println("获取\"系统时间\"的时钟，并使用\"指定时区\"对日期和时间进行转换：" + zoneClock);
        System.out.println("获取\"固定时刻\"的时钟，并\"指定时区\"对日期和时间进行转换：" + fixedClock);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 时钟实例中的方法
     */
    public static void clockMethod() {
        /* 获取系统时钟 */
        Clock clock = Clock.systemDefaultZone();
        /* 时钟常用方法 */
        // 获取当前时钟的时刻
        Instant instant = clock.instant();
        // 获取当前时钟的当前毫秒时刻
        long millis = clock.millis();
        // 获取当前时钟的时区
        ZoneId zoneId = clock.getZone();
        // 获取当前时钟并指定时区
        Clock zoneClock = clock.withZone(ZoneId.of("Asia/Shanghai"));
        /* 输出 */
        System.out.println("获取当前时间的时钟，并使用UTC时区对日期和时间进行转换：" + clock);
        System.out.println("获取时钟的当前时刻：" + instant);
        System.out.println("获取时钟的当前毫秒瞬间：" + millis);
        System.out.println("获取用于创建日期和时间的时区：" + zoneId);
        System.out.println("返回带有不同时区的此时钟的副本：" + zoneClock);
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        // 获取时钟实例
        getClock();
        // 时钟实例中的方法
        clockMethod();
    }

}
