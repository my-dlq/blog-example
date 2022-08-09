package club.mydlq.completable.example.parallel;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * CompletionStage<Void> runAfterEither(CompletionStage<?> other,Runnable action);
 *
 * 从公共的 ForkJoinPool.commonPool() 线程池或者自定义线程池中，获取多个子线程，并行执行两个 CompletableFuture 任务。
 *
 * 两个任务任意一个先执行完成，就会调用当前方法的主线程或者执行上一步任务的线程，直接执行下一步，执行特定的动作。
 *
 * @author mydlq
 */
public class RunAfterEitherExample {

    public static void runAfterEitherExample(){
        // 通道1 (模拟向远程通道1写入数据)
        CompletableFuture<Void> channel1 = CompletableFuture.runAsync(() -> {
            randomSleep(1000);
            System.out.println("阶段1-执行完成");
        });
        // 通道2 (模拟向远程通道2写入数据)
        CompletableFuture<Void> channel2 = CompletableFuture.runAsync(() -> {
            randomSleep(1000);
            System.out.println("阶段2-执行完成");
        });

        // 执行 CompletableFuture 任务
        // 并行执行【任务1】和【任务2】，无论哪个任务先执行完成，直接进行下一步任务，执行特定动作
        channel1.runAfterEither(channel2, () -> System.out.println("任意任务执行完成-执行特定操作")).join();
    }

    /**
     * 指定规定时间内睡眠
     *
     * @param time 随机睡眠时间
     */
    private static void randomSleep(int time) {
        try {
            Thread.sleep(new Random().nextInt(time));
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        runAfterEitherExample();
    }

}
