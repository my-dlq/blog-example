package club.mydlq.completable.example.end;

import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * boolean cancel(boolean mayInterruptIfRunning)
 *
 * 用于取消任务，如果任务尚未执行成功调用 cancel 方法成功取消任务时，则返回 true，否则返回 false。
 * 并且任务取消成功后，通过 get 方法获取结果时，会抛出 CancellationException 异常。
 *
 * @author mydlq
 */
public class CancelExample {

    public static void cancelExample() throws ExecutionException, InterruptedException {
        // 执行 CompletableFuture 任务
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            // 随机睡眠1~10毫秒
            sleep(new Random().nextInt(10));
            System.out.println("示例-执行任务完成");
        });

        // 随机睡眠1~10毫秒
        sleep(new Random().nextInt(10));

        // 调用 cancel 方法取消任务
        boolean cancelResult = cf.cancel(true);
        System.out.println("取消任务: " + cancelResult);

        // 调用 get 方法获取执行结果，如果取消任务将抛出 CancellationException 异常，这里对该异常进行处理
        try {
            cf.get();
        } catch (CancellationException e) {
            System.out.println("获取任务失败，任务已经被取消");
        }
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        cancelExample();
    }

}
