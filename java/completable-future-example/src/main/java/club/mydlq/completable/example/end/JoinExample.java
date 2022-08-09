package club.mydlq.completable.example.end;

import java.util.concurrent.CompletableFuture;

/**
 * T join()
 *
 * 如果正常完成则返回结果值，如果异常完成、或执行过程中引发异常，这时就会抛出 (未经检查) 异常。
 *
 * @author mydlq
 */
public class JoinExample {

    public static void joinExample() {
        // 执行 CompletableFuture 任务
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "执行结果");

        // 调用 join 方法进行等待，获取执行结果
        cf.join();
    }

    public static void main(String[] args) {
        joinExample();
    }

}
