package club.mydlq;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 使用 Hutool 工具的 Snowflake 工具
 *
 * @author mydlq
 */
public class SnowflakeHutool {

    /**
     * 测试 main 方法
     */
    public static void main(String[] args) {
        // 实例化生成 ID 工具对象
        Snowflake snowflake = IdUtil.getSnowflake(1, 3);
        // 创建用于存储 id 的集合
        List<Long> idList = new ArrayList<>();
        // 标记开始时间
        long start = System.currentTimeMillis();
        // 设置 1000ms 内循环生成 ID
        while (System.currentTimeMillis() - start <= 1000) {
            // 生成 ID 加入集合
            idList.add(snowflake.nextId());
        }
        // 输出生成数量
        System.out.println("生成 ID 总数量：" + idList.size());
        // 输出去重后 ID 的数量
        long setSize = new HashSet<>(idList).size();
        System.out.println("去重后 ID 总数量：" + setSize);
        System.out.println(setSize == idList.size() ? "生成的 ID 没有重复" : "生成的 ID 存在重复");
    }

}
