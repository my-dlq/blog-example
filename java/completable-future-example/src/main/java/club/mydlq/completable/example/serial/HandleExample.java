package club.mydlq.completable.example.serial;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)
 *
 * 使用执行上一步任务的线程，继续执行当前任务，并且将上一步任务执行的结果，或者上一步抛出的异常，
 * 作为当前任务执行时的参数，然后执行当前任务，并且当前任务执行结束后没有返回值。
 *
 * @author mydlq
 */
public class HandleExample {

    public static void handleExample() {
        // 执行 CompletableFuture 串行任务:
        // 第一步，获取远程日期字符串，然后返回；
        // 第二步，接收上一步中的日期字符串，将其转换为 LocalDate 日期对象；
        CompletableFuture<LocalDate> cf = CompletableFuture
                .supplyAsync(() -> {
                    int random = new Random().nextInt(2);
                    // 50% 概率发生异常
                    if (random != 0) {
                        throw new RuntimeException("模拟发生异常");
                    }
                    // 50% 概率返回正常值
                    return "2022-06-01";
                })
                .handle((param, exception) -> {
                    // 如果上一步结果为异常，则返回现在的日期，否则将上一步获取的日期字符串转换为日期对象
                    if (exception != null) {
                        return LocalDate.now();
                    }
                    return LocalDate.parse(param, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                });

        // 调用 join 方法进入堵塞状态，直至获取任务执行结果
        LocalDate result = cf.join();
        System.out.println(result);
    }

    public static void main(String[] args) {
        handleExample();
    }

}
