package club.mydlq;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 手动实现 Snowflake 生成 ID 逻辑
 *
 * @author mydlq
 */
public class Snowflake {

    /** 机器id（5位）*/
    private final long machineId;
    /** 数据中心id（5位）*/
    private final long datacenterId;
    /** 序列号（12位） */
    private long sequence = 0L;

    /** 初始时间戳 */
    private final long INIT_TIMESTAMP = 1288834974657L;
    /** 机器id位数 */
    private final long MAX_MACHINE_ID_BITS = 5L;
    /** 数据中心id位数 */
    private final long DATACENTER_ID_BITS = 5L;

    /** 机器id最大值 */
    private final long MAX_MACHINE_Id = -1L ^ (-1L << MAX_MACHINE_ID_BITS);
    /** 数据中心id最大值 */
    private final long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_ID_BITS);
    /** 序列号id最大值 */
    private final long SEQUENCE_BITS = 12L;
    /** 序列号最大值 */
    private final long sequenceMask = -1L ^ (-1L << SEQUENCE_BITS);

    /** workerid需要左移的位数（12位） */
    private final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    /** 数据id需要左移位数(12序列号)+(5机器id)共17位 */
    private final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + MAX_MACHINE_ID_BITS;
    /** 时间戳需要左移位数(12序列号)+(5机器id)+(5数据中心id)共22位 */
    private final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + MAX_MACHINE_ID_BITS + DATACENTER_ID_BITS;

    /** 上次时间戳，初始值为负数 */
    private long lastTimestamp = -1L;

    /**
     * 构造方法，进行初始化检测
     *
     * @param machineId    机器ID
     * @param datacenterId 数据ID
     */
    public Snowflake(long machineId, long datacenterId) {
        // 检查数(机器ID)是否大于5或者小于0
        if (machineId > MAX_MACHINE_Id || machineId < 0) {
            throw new IllegalArgumentException(String.format("机器id不能大于 %d 或者小于 0", MAX_MACHINE_Id));
        }
        // 检查数(据中心ID)是否大于5或者小于0
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("数据中心id不能大于 %d 或者小于 0", MAX_DATACENTER_ID));
        }
        // 配置参数
        this.machineId = machineId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获取下一个生成的分布式 ID
     *
     * @return 分布式 ID
     */
    public synchronized long nextId() {
        // 获取当前时间戳
        long currentTimestamp = timeGen();
        //获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常
        if (currentTimestamp < lastTimestamp) {
            // 等待 10ms，如果时间回拨时间短，能在 10ms 内恢复，则正常生产 ID，否则抛出异常
            long offset = lastTimestamp - currentTimestamp;
            if (offset <= 10) {
                try {
                    wait(offset << 1);
                    if (currentTimestamp < lastTimestamp) {
                        throw new RuntimeException("系统时间被回调，无法生成ID");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("系统时间被回调，无法生成ID，且等待中断");
                }
            }
        }
        // 判断当前时间戳是否等于上次生成ID的时间戳（同1ms内），是则进行序列号递增+1，如果递增到设置的最大值（默认4096）则等待
        if (lastTimestamp == currentTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                currentTimestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 如果当前时间戳大于上次生成ID的时间戳，说明已经进入下一毫秒，则设置序列化ID为0
        else {
            sequence = 0;
        }
        // 设置最后时间戳为当前时间戳
        lastTimestamp = currentTimestamp;
        // 生成 ID 并返回结果：
        // (currStamp - INIT_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT     时间戳部分
        // datacenterId << DATACENTER_ID_SHIFT                      数据中心部分
        // machineId << WORKER_ID_SHIFT                             机器标识部分
        // sequence                                                 序列号部分
        return ((currentTimestamp - INIT_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT) |
                (datacenterId << DATACENTER_ID_SHIFT) |
                (machineId << WORKER_ID_SHIFT) |
                sequence;
    }

    /**
     * 当某一毫秒时间内产生的ID数超过最大值则进入等待，
     * 循环判断当前时间戳是否已经变更到下一毫秒，
     * 是则返回最新的时间戳
     *
     * @param lastTimestamp 待比较的时间戳
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取系统当前时间
     *
     * @return 系统当前时间（毫秒）
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 测试 main 方法
     */
    public static void main(String[] args) {
        // 实例化生成 ID 工具对象
        Snowflake worker = new Snowflake(1, 3);
        // 创建用于存储 id 的集合
        List<Long> idList = new ArrayList<>();
        // 标记开始时间
        long start = System.currentTimeMillis();
        // 设置 1000ms 内循环生成 ID
        while (System.currentTimeMillis() - start <= 1000) {
            // 生成 ID 加入集合
            idList.add(worker.nextId());
        }
        // 输出1s内生成ID的数量
        System.out.println("生成 ID 总数量：" + idList.size());
        // 输出去重后 ID 的数量
        long setSize = new HashSet<>(idList).size();
        System.out.println("去重后 ID 总数量：" + setSize);
        System.out.println(setSize == idList.size() ? "生成的 ID 没有重复" : "生成的 ID 存在重复");
    }

}
