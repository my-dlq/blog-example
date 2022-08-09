package club.mydlq.completable.example.serial;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

/**
 * <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
 *
 * 从公共的 ForkJoinPool.commonPool() 线程池或者自定义线程池中，获取一个子线程，将上一步任务执行的结果，
 * 作为当前任务执行时的参数，然后按照代码编写顺序串行执行，任务执行结束后将返回指定类型结果。
 *
 * @author mydlq
 */
public class ThenApplyAsyncExample {

    public static void thenApplyAsyncExample() {
        // 执行 CompletableFuture 串行任务
        CompletableFuture<String> cf = CompletableFuture
                // 第一步，先模拟通过远程结果获取日期字符串；
                .supplyAsync(() -> "2022-06-01")
                // 第二步，将日期字符串转换为 LocalDate 日期对象，便于进行逻辑计算；
                .thenApplyAsync(param -> LocalDate.parse(param, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                // 第三步，操作日期对象进行计算，在当前日期的基础上+10天，然后将日期对象转换为字符串返回；
                .thenApplyAsync(param -> param.plusDays(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // 调用 join 方法进入堵塞状态，直至获取任务执行结果
        String result = cf.join();
        System.out.println(result);
    }

    public static void main(String[] args) {
        thenApplyAsyncExample();
    }

}
