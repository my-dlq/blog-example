package club.mydlq;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 时间段示例
 *
 * @author mydlq
 */
public class DurationExample {

    /**
     * 计算时间段
     */
    public static void dateDuration() {
        /* 创建两个时间对象 */
        LocalDateTime time1 = LocalDateTime.of(2018, 6, 18, 10, 20, 30);
        LocalDateTime time2 = LocalDateTime.of(2020, 8, 22, 12, 20, 30);
        // 创建时间段对象
        Duration duration = Duration.between(time1, time2);
        /* 输出 */
        System.out.println("小时时间段：" + duration.toHours());
        System.out.println("分钟时间段：" + duration.toMinutes());
        System.out.println("秒时间段：" + duration.getSeconds());
        System.out.println("毫秒时间段：" + duration.toMillis());
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        // 计算时间段
        dateDuration();
    }

}
