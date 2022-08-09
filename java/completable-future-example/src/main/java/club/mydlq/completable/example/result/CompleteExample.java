package club.mydlq.completable.example.result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * boolean complete(T value)
 *
 * 设置调用 get() 方法的返回值。不过需要注意的是，如果任务没有执行完成，则可以通过该方法设置 get() 方法
 * 执行时返回配置的值，如果任务已经完成，则无法配置。(任务没有执行完成返回 true，否则返回 false)
 *
 * @author mydlq
 */
public class CompleteExample {

    public static void completeExample() throws ExecutionException, InterruptedException {
        // 执行 CompletableFuture 任务
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "示例-执行完成");

        // 设置或重置 get 方法和与其相关方法的返回的值，任务没有执行完成返回 true，否则返回 false
        boolean setResult = cf.complete("示例-任务没有完成-设置返回值");
        System.out.println("设置返回值为执行结果: " + setResult);

        // 调用 get 方法进行等待，获取执行结果并输出
        String result = cf.get();
        System.out.println(result);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        completeExample();
    }

}
