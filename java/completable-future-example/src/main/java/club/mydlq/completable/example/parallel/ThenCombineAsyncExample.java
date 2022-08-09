package club.mydlq.completable.example.parallel;

import java.util.concurrent.CompletableFuture;

/**
 * <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
 *
 * 从公共的 `ForkJoinPool.commonPool()` 线程池或者 `自定义线程池` 中，获取多个子线程，并行执行两个 CompletableFuture 任务。
 *
 * 等到两个任务都执行完成后，调用当前方法的主线程或者执行上一步任务的线程，返回并执行一个新的 CompletableFuture 任务，并且将之前
 * 两个任务执行结果作为该任务的参数。
 *
 * @author mydlq
 */
public class ThenCombineAsyncExample {

    public static void thenCombineAsyncExample() {
        // 模拟调用远程接口获取【姓名】
        CompletableFuture<String> nameCf = CompletableFuture.supplyAsync(() -> "mydlq");
        // 模拟调用远程接口获取【岁数】
        CompletableFuture<Integer> ageCf = CompletableFuture.supplyAsync(() -> 18);

        // 执行 CompletableFuture 任务
        // 并行执行，模拟调用远程接口获取【姓名】，以及获取【岁数】
        // 然后将获取的【姓名】和【岁数】拼在一起，之后返回新字符串
        CompletableFuture<String> cf = nameCf.thenCombineAsync(ageCf, (name, age) -> "姓名:" + name + ", 岁数:" + age);

        // 进入堵塞状态，等待这些阶段执行完成后获取结果
        String info = cf.join();
        System.out.println(info);
    }

    public static void main(String[] args) {
        thenCombineAsyncExample();
    }

}
