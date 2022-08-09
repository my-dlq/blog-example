package club.mydlq.completable.example.start;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture<Void> runAsync(Runnable runnable)
 *
 * 从公共的 `ForkJoinPool.commonPool()` 线程池或者 `自定义线程池` 中，获取一个子线程，执行指定的代码逻辑，
 * 并且该方法执行结束后，没有返回值。
 *
 * @author mydlq
 */
public class RunAsyncExample {

    public static void runAsyncExample() {
        // 调用 runAsync 方法，异步执行 runnable 中的代码逻辑，模拟获取远程信息
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> System.out.println("模拟获取远程信息并输出到控制台"));
        // 调用 join 方法进行等待，获取执行结果
        cf.join();
    }


    public static void main(String[] args) {
        runAsyncExample();
    }

}
