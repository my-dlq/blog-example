package club.mydlq.completable.example.parallel;

import java.util.concurrent.CompletableFuture;

/**
 * <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
 *
 * 从公共的 ForkJoinPool.commonPool() 线程池或者自定义线程池中，获取多个子线程，并行执行两个 CompletableFuture 任务。
 *
 * 等到两个任务都执行完成后，会接着从 ForkJoinPool.commonPool() 线程池或者自定义线程池中，获取一个线程，返回并执行一个新的
 * 不带返回值的 CompletableFuture 任务，并且将之前两个任务执行结果作为该任务的参数。
 *
 * @author mydlq
 */
public class ThenAcceptBothAsyncExample {

    public static void thenAcceptBothAsyncExample() {
        // 模拟调用远程接口获取【姓名】
        CompletableFuture<String> nameCf = CompletableFuture.supplyAsync(() -> "mydlq");
        // 模拟调用远程接口获取【岁数】
        CompletableFuture<Integer> ageCf = CompletableFuture.supplyAsync(() -> 18);

        // 执行 CompletableFuture 任务
        // 并行执行，模拟调用远程接口获取【姓名】，以及获取【岁数】
        // 然后将获取的【姓名】和【岁数】输出
        CompletableFuture<Void> cf = nameCf.thenAcceptBothAsync(ageCf,
                (name, age) -> System.out.println("姓名:" + name + ", 岁数:" + age));

        // 进入堵塞状态等待各阶段执行完成
        cf.join();
    }

    public static void main(String[] args) {
        thenAcceptBothAsyncExample();
    }

}
