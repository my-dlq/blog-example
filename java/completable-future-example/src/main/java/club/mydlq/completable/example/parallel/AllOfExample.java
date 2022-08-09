package club.mydlq.completable.example.parallel;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
 *
 * 传入多个 CompletableFuture<?> 阶段作为参数，然后并行执行这些 CompletableFuture<?> 阶段，
 * 当这些 CompletableFuture<?> 都成功执行完成时，就会返回一个新的 CompletableFuture<Void>，
 * 执行后续计算操作。
 *
 * 不过这里需要注意，如果传入的 CompletableFuture<?> 中的其中一个阶段异常完成时，那么返回的
 * CompletableFuture<Void> 也异常完成，并将此异常作为异常原因。
 *
 * @author mydlq
 */
public class AllOfExample {

    public static void allOfExample() {
        // 创建聚合数据的 Map 集合
        Map<String, String> userMap = new ConcurrentHashMap<>(3);

        // 创建待执行的 Runnable 参数
        Runnable runnable1 = () -> {
            System.out.println("任务1-成功获取用户基本信息");
            userMap.put("userInfo", "{name: mydlq, age: 18}");
        };
        Runnable runnable2 = () -> {
            System.out.println("任务2-成功获取用户头像");
            userMap.put("avatar", "http://www.xxx.com/avatar");
        };
        Runnable runnable3 = () -> {
            System.out.println("任务3-成功获取用户余额");
            userMap.put("balance", "1000");
        };

        // 执行多个 CompletableFuture，需要等待全部完成
        CompletableFuture<Void> cf = CompletableFuture.allOf(
                CompletableFuture.runAsync(runnable1),
                CompletableFuture.runAsync(runnable2),
                CompletableFuture.runAsync(runnable3)
        );

        // 进入堵塞状态，等待执行完成
        cf.join();

        // 输出用户信息
        System.out.println("获取的用户信息:");
        for (Map.Entry<String, String> entry : userMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        allOfExample();
    }

}
