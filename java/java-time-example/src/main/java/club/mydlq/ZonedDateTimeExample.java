package club.mydlq;

import java.time.*;

/**
 * 日期时间对象(记录时区)示例
 *
 * @author mydlq
 */
public class ZonedDateTimeExample {

    /**
     * ZonedDateTime 转换
     */
    public static void zoneTimeTransition() {
        /* 在默认时区中从系统时钟获取当前日期时间 */
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        /* ZonedDateTime 常用方法 */
        // 获取时区信息
        ZoneId zoneId = zonedDateTime.getZone();
        // 转换为 LocalDate 对象
        LocalDate localDate = zonedDateTime.toLocalDate();
        // 转换为 LocalDateTime 对象
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        // 转换为 OffsetDateTime 对象
        OffsetDateTime offsetDateTime = zonedDateTime.toOffsetDateTime();
        // 转换为 Instant 对象
        Instant instant = zonedDateTime.toInstant();
        // 指定年、月、日、时、分、秒参数获取日期时间实例
        ZonedDateTime zonedDateTimeZone = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("GMT+8"));
        // 指定年、月、日、时、分参数获取日期时间实例
        ZonedDateTime ymdhmsnZonedDateTimeZone = ZonedDateTime.of(2020, 6, 18, 12, 30, 1, 0, ZoneId.of("GMT+8"));
        /* 输出 */
        System.out.println("在默认时区中从系统时钟获取当前日期时间：" + zonedDateTime);
        System.out.println("获取时区信息：" + zoneId);
        System.out.println("转换为 LocalDate 对象：" + localDate);
        System.out.println("转换为 LocalDateTime 对象：" + localDateTime);
        System.out.println("转换为 OffsetDateTime 对象：" + offsetDateTime);
        System.out.println("转换为 Instant 对象：" + instant);
        System.out.println("指定年、月、日、时、分、秒参数获取日期时间实例：" + zonedDateTimeZone);
        System.out.println("指定年、月、日、时、分、秒参数获取日期时间实例：" + ymdhmsnZonedDateTimeZone);
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        // ZonedDateTime 转换
        zoneTimeTransition();
    }

}
