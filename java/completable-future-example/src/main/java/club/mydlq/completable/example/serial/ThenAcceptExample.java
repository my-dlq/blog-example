package club.mydlq.completable.example.serial;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CompletionStage<Void> thenAccept(Consumer<? super T> action);
 *
 * 使用执行上一步任务的线程，继续执行当前任务，并且将上一步任务执行的结果，作为当前任务执行时的参数，
 * 当前任务执行结束后没有返回值。
 *
 * @author mydlq
 */
public class ThenAcceptExample {

    public static void thenAcceptExample() {
        // 模拟的远程获取的日期字符粗
        String dateStr = "1995-05-10,2000-03-15";
        // 存储 LocalDate 的集合
        List<LocalDate> dateList = new CopyOnWriteArrayList<>();

        // 执行 CompletableFuture 任务
        CompletableFuture
                .supplyAsync(() -> dateStr.split(","))
                .thenAccept(dateArray -> {
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
        thenAcceptExample();
    }

}
