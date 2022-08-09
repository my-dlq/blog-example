package club.mydlq.completable.example.parallel;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * CompletionStage<Void> acceptEither(CompletionStage<? extends T> other,Consumer<? super T> action);
 *
 * 从公共的 ForkJoinPool.commonPool() 线程池或者自定义线程池中，获取多个子线程，并行执行两个 CompletableFuture 任务。
 *
 * 两个任务任意一个先执行完成，就会调用当前方法的主线程或者执行上一步任务的线程，直接执行下一步，并且将先执行完成任务的结果作
 * 为下一步的参数，任务执行完成后没有返回值。
 *
 * @author mydlq
 */
public class AcceptEitherExample {

    public static void acceptEitherExample() {
        // 通道1 (模拟获取远程信息)
        CompletableFuture<String> channel1 = CompletableFuture.supplyAsync(() -> {
            randomSleep(1000);
            return "阶段1-从通道1成功获取结果";
        });
        // 通道2 (模拟获取远程信息)
        CompletableFuture<String> channel2 = CompletableFuture.supplyAsync(() -> {
            randomSleep(1000);
            return "阶段2-从通道2成功获取结果";
        });

        // 执行 CompletableFuture 任务
        // 并行执行【任务1】和【任务2】，分别从【通道1】或者【通道2】中获取结果，
        // 哪个通道先获取结果成功，就使用该结果作为任务的返回结果，也作为下一步任
        // 务要执行的参数，然后执行下一步操作，执行完成后没有返回值。
        channel1.acceptEither(channel2, result -> System.out.println("获取的结果: " + result)).join();
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
        acceptEitherExample();
    }

}
