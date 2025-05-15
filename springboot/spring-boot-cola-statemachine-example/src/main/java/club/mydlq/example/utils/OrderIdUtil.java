package club.mydlq.example.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单工具类
 *
 * @author mydlq
 */
public class OrderIdUtil {
    private OrderIdUtil() {
    }

    /**
     * 定义一个原子整型变量用于维护同一秒内的订单序列号
     */
    private static final AtomicInteger SEQUENCE = new AtomicInteger(1);
    /**
     * 定义一个格式化日期的工具
     */
    private static final DateTimeFormatter SDF = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 生成订单ID
     *
     * @return 订单ID
     */
    public static Long generateOrderId() {
        /*
         * 订单ID组成: 【日期+时间戳+同一秒内的单号】
         * - 第1部分: 下单的日期+时间戳。比如 2024年8月13日，则表示 20240813
         * - 第2部分: 下单的时间戳。比如 01:20:30，转换为时间戳后则表示 04830
         * - 第3部分: 同一秒内下的第几单，并且认为不会超过10000单。比如在同一秒内第1001单，则表示第 1001 单
         */

        // 获取当前日期和时间
        LocalDateTime now = LocalDateTime.now();

        // 格式化年月日
        String dateStr = now.format(SDF);

        // 获取当前时间戳
        long timestamp = Instant.now().toEpochMilli();

        // 取时间戳的后五位
        int timestampPart = (int) (timestamp % 100000);

        // 同一秒内的序列号
        int sequenceNumber = SEQUENCE.getAndIncrement();

        // 认为每秒订单量不会超过10000，所以如果序列号超过9999，则说明已经到了下一秒，就需要重置序列号
        if (sequenceNumber >= 10000) {
            // 重置序列号
            SEQUENCE.set(0);
            sequenceNumber = SEQUENCE.getAndIncrement();
        }

        // 构造订单ID
        return Long.parseLong(dateStr +
                String.format("%05d", timestampPart) +
                String.format("%04d", sequenceNumber));
    }

}

