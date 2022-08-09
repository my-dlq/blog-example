package club.mydlq.completable.example.serial;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
 *
 * 从公共的 ForkJoinPool.commonPool() 线程池或者自定义线程池中，获取一个子线程，将上一步任务执行的结果，
 * 作为当前任务执行时的参数，然后按照代码编写顺序串行执行，任务执行结束后没有返回值。
 *
 * @author mydlq
 */
public class ThenAcceptAsyncExample {

    public static void thenAcceptAsyncExample() {
        // 模拟的远程获取的日期字符粗
        String dateStr = "1995-05-10,2000-03-15";
        // 存储 LocalDate 的集合
        List<LocalDate> dateList = new ArrayList<>();

        // 执行 CompletableFuture 任务
        CompletableFuture
                .supplyAsync(() -> dateStr.split(","))
                .thenAcceptAsync(dateArray -> {
                    for (String s : dateArray) {
                        dateList.add(LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    }
                })
                .join();

        // 输出转换结果
        for (LocalDate localDate : dateList) {
            System.out.println(localDate.toString());
        }
    }

    public static void main(String[] args) {
        thenAcceptAsyncExample();
    }

}
