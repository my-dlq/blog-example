package club.mydlq;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.zone.ZoneRules;

/**
 * 时区偏移量示例
 *
 * @author mydlq
 */
public class ZoneOffsetExample {

    /**
     * 获取现在日期时间的时区偏移量
     */
    public static void getNowZoneOffset() {
        /* 获取 ZoneOffset 实例 */
        ZoneOffset zoneOffsetNow = OffsetDateTime.now().getOffset();
        /* 获取此偏移量相关时区的规则 */
        ZoneRules zoneRules = zoneOffsetNow.getRules();
        /* 输出 */
        System.out.println("获取当前日期时间的【ZoneOffset】对象：" + zoneOffsetNow);
        System.out.println("获取此偏移量相关时区的规则：" + zoneRules);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 创建时区偏移量
     */
    public static void createZoneOffset() {
        /* 创建时区偏移量 */
        // 使用【偏移量ID】获取【ZoneOffset】对象（最大支持的范围是从±18：00）
        ZoneOffset zoneOffsetOf = ZoneOffset.of("+08:00");
        // 使用以【秒】为【偏移量ID】获取【ZoneOffset】对象（最大支持的范围是从±64800）
        ZoneOffset zoneOffsetSeconds = ZoneOffset.ofTotalSeconds(28800);
        // 使用以【小时】为【偏移量ID】获取【ZoneOffset】对象（最大支持的范围是从±18）
        ZoneOffset zoneOffsetByHours = ZoneOffset.ofHours(8);
        // 使用以【小时和分钟】为【偏移量ID】获取【ZoneOffset】对象（小时最大支持的范围是从±18，分钟的范围为±59）
        ZoneOffset zoneOffsetByHoursMinites = ZoneOffset.ofHoursMinutes(8, 30);
        // 使用以【小时、分钟和秒】为【偏移量ID】获取【ZoneOffset】对象（小时最大支持的范围是从±18，分钟的范围为±59，秒的范围为±59）
        ZoneOffset zoneOffsetByHoursMinutesSeconds = ZoneOffset.ofHoursMinutesSeconds(8, 30, 10);
        /* 输出 */
        System.out.println("使用【偏移量ID】获取【ZoneOffset】对象：" + zoneOffsetOf);
        System.out.println("使用以【秒】为【偏移量ID】获取【ZoneOffset】对象：" + zoneOffsetSeconds);
        System.out.println("使用以【小时】为【偏移量ID】获取【ZoneOffset】对象：" + zoneOffsetByHours);
        System.out.println("使用以【小时与分钟】为【偏移量ID】获取【ZoneOffset】对象：" + zoneOffsetByHoursMinites);
        System.out.println("使用以【小时、分钟和秒】为【偏移量ID】获取【ZoneOffset】对象：" + zoneOffsetByHoursMinutesSeconds);
        System.out.println("-------------------------------------------------");
    }

    /**
     * 时区偏移量比较
     */
    public static void compareZoneOffset() {
        /* 创建两个 ZoneOffset 实例 */
        ZoneOffset zoneOffsetOf1 = ZoneOffset.of("+08:00");
        ZoneOffset zoneOffsetOf2 = ZoneOffset.of("+09:00");
        /* 时区偏移量比较 */
        // 将此偏移量与另一个偏移量按降序比较(返回相差秒数)
        int seconds = zoneOffsetOf1.compareTo(zoneOffsetOf2);
        /* 输出 */
        System.out.println("将此偏移量与另一个偏移量按降序比较：" + seconds);
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        // 获取现在日期时间的时区偏移量
        getNowZoneOffset();
        // 创建时区偏移量
        createZoneOffset();
        // 时区偏移量比较
        compareZoneOffset();
    }

}
