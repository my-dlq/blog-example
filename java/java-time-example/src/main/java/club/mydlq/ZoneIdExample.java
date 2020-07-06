package club.mydlq;

import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * 时区示例
 *
 * @author mydlq
 */
public class ZoneIdExample {

    /**
     * 获取 ZoneId
     */
    public static void getZoneId() {
        /* 获取时区ID */
        ZoneId zoneId = ZoneId.systemDefault();
        // 从时区 ID 获取 ZoneId 的实例
        ZoneId zoneIdOf = ZoneId.of("+08:00");
        // 获取包含偏移量的 ZoneId 实例
        ZoneId zoneIdOfOffset = ZoneId.ofOffset("GMT", OffsetDateTime.now().getOffset());
        /* 输出 */
        System.out.println("获取系统默认的时区：" + zoneId.getId());
        System.out.println("获取此ID的时区规则以执行计算：" + zoneId.getRules());
        System.out.println("从时区ID获取 ZoneId 的实例：" + zoneIdOf);
        System.out.println("从时区ID获取 ZoneId 的实例：" + zoneIdOfOffset);
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        // 获取 ZoneId
        getZoneId();
    }

}
