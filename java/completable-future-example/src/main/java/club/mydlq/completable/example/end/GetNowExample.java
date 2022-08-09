package club.mydlq.completable.example.end;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * T getNow(T valueIfAbsent)
 *
 * 立即获取执行结果，如果 Future 执行完成则返回执行后的结果值，如果 Future 没有执行完成，则返回指定的 valueIfAbsent 值。
 *
 * @author mydlq
 */
public class GetNowExample {

    public static void getNowExample() {
        // 执行 CompletableFuture 任务
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            // 睡眠5毫秒
            sleep(5);
            return "执行结果";
        });

        // 随机睡眠1~10毫秒
        sleep(new Random().nextInt(10));

        // 调用 getNow 方法获取执行结果，如果任务未执行完成则输出设置的默认值
        String result = cf.getNow("默认值");
        System.out.println(result);
    }

    /**
     * 线程睡眠
     *
     * @param millis 睡眠时间(单位:毫秒)
     */
    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getNowExample();
    }

}
