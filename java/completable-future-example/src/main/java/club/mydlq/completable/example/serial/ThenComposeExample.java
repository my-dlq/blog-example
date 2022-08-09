package club.mydlq.completable.example.serial;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

/**
 * <U> CompletionStage<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
 *
 * 使用调用当前方法的主线程或者执行上一步任务的线程，执行当前方法，使两个有依赖关系的任务组合在按顺序执行，执行当前任务时，
 * 会将上一步任务执行结果作为当前任务执行时的参数，并且在当前执行任务完成后，会返回并执行一个新的 CompletableFuture 任务。
 *
 * @author mydlq
 */
public class ThenComposeExample {

    public static void thenComposeExample() {
        // 执行 CompletableFuture 任务
        CompletableFuture<String> cf2 = CompletableFuture
                // 模拟获取远程接口获取创建时间，然后得到结果 "2022-06-01"
                .supplyAsync(() -> "2022-06-01")
                // 实现将两个有依赖关系的任务组合在一起，一个用于转换字符串为日期类型，一个用于计算并转换为日期字符串
                .thenCompose(param -> {
                    // 将时间字符串转换为 LocalDate 日期类型
                    LocalDate localDate = LocalDate.parse(param, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    return CompletableFuture.supplyAsync(() -> {
                        // 最后创建一个新的任务，任务中将 LocalDate 日期+10天，并转换回字符串返回
                        return localDate.plusDays(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    });
                });

        // 进入堵塞状态，等待这些阶段执行完成
        String result = cf2.join();
        System.out.println("计算结果:" + result);
    }

    public static void main(String[] args) {
        thenComposeExample();
    }

}
