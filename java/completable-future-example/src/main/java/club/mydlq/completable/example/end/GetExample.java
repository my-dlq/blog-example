package club.mydlq.completable.example.end;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * T get()
 *
 * 方法执行后会使线程进入堵塞状态，直至任务执行完成，返回执行结果，或者达到指定的超时时间，抛出 (运行时) 异常。
 *
 * @author mydlq
 */
public class GetExample {

    public static void getExample() throws ExecutionException, InterruptedException {
        // 执行 CompletableFuture 任务
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "执行结果");

        // 调用 get 方法进行等待，获取执行结果
        cf.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        getExample();
    }

}
