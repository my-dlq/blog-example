package club.mydlq.completable.example.start;

import java.util.concurrent.CompletableFuture;

/**
 * <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 *
 * 从公共的 ForkJoinPool.commonPool() 线程池或者自定义线程池中，获取一个子线程，执行指定的函数，
 * 并且该方法执行结束后，返回指定类型的返回值。
 *
 * @author mydlq
 */
public class SupplyAsyncExample {

    public static void supplyAsyncExample() {
        // 调用 supplyAsync 方法，异步执行 runnable 中的代码逻辑，模拟获取远程信息，然后返回
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("模拟获取远程信息");
            return "远程信息";
        });

        // 调用 join 方法进行等待，获取执行结果
        String result = cf.join();
        System.out.println(result);
    }

    public static void main(String[] args) {
        supplyAsyncExample();
    }

}
