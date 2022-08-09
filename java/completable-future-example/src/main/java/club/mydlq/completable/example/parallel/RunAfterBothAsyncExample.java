package club.mydlq.completable.example.parallel;

import java.util.concurrent.CompletableFuture;

/**
 * CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action);
 *
 * 从公共的 ForkJoinPool.commonPool() 线程池或者自定义线程池中，获取多个子线程，并行执行两个 CompletableFuture 任务。
 *
 * 等到两个任务都执行完成后，会接着从 ForkJoinPool.commonPool() 线程池或者自定义线程池中，执行一个新的特定的 CompletableFuture 任务。
 *
 * @author mydlq
 */
public class RunAfterBothAsyncExample {

    public static void runAfterBothAsyncExample() {
        // CompletableFuture 任务 - 步骤1
        CompletableFuture<Void> step1 = CompletableFuture.runAsync(() -> System.out.println("阶段1"));
        // CompletableFuture 任务 - 步骤2
        CompletableFuture<Void> step2 = CompletableFuture.runAsync(() -> System.out.println("阶段2"));

        // 当【阶段1】和【阶段2】并行执行完成后，则执行特定任务
        step1.runAfterBothAsync(step2, ()-> System.out.println("全部阶段完成，执行特定任务"));
    }

    public static void main(String[] args) {
        runAfterBothAsyncExample();
    }

}
