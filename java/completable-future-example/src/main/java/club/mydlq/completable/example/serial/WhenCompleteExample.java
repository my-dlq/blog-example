package club.mydlq.completable.example.serial;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)
 *
 * 使用调用当前方法的主线程，执行当前方法，并且将上一步任务执行的结果，或者上一步抛出的异常，作为当前任务执行时的参数，
 * 然后执行当前任务，并且当前任务执行结束后没有返回值。
 *
 * - 如果上一阶段中正常执行结束，则该方法的结果参数不为 null；
 * - 如果上一阶段中抛出异常，则该方法的异常参数不为 null；
 *
 * @author mydlq
 */
public class WhenCompleteExample {

    public static void whenCompleteExample() {
        // 模拟的远程获取的日期字符粗
        String dateStr = "1995-05-10,2000-03-15";
        // 存储 LocalDate 的集合
        List<LocalDate> dateList = new ArrayList<>();

        // 执行 CompletableFuture 任务
        CompletableFuture
                .supplyAsync(() -> {
                    int random = new Random().nextInt(2);
                    if (random == 1){
                        throw new RuntimeException("模拟发生异常");
                    }
                    return dateStr.split(",");
                })
                .whenComplete((dateArray, exception) -> {
                    // 如果上一步执行过程没有发生异常，则将日期字符串转换为日期对象
                    if (dateArray != null){
                        for (String s : dateArray) {
                            dateList.add(LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        }
                    }
                })
                .join();

        // 输出转换结果
        for (LocalDate localDate : dateList) {
            System.out.println(localDate.toString());
        }
    }

    public static void main(String[] args) {
        whenCompleteExample();
    }

}
