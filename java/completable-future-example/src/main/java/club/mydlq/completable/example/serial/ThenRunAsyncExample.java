package club.mydlq.completable.example.serial;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CompletableFuture<Void> thenRunAsync(Runnable action)
 *
 * 按照代码编写顺序串行执行任务，从公共的 ForkJoinPool.commonPool() 线程池或者自定义线程池中，获取一个子线程，
 * 执行指定的任务，并且该方法执行结束后，没有返回值。
 *
 * @author mydlq
 */
public class ThenRunAsyncExample {

    public static void thenRunAsyncExample() {
        // 模拟的远程获取的日期字符粗
        String dateStr = "1995-05-10,2000-03-15";
        // 原子引用类对象
        AtomicReference<List<String>> dateStrList = new AtomicReference<>();
        // LocalDate集合
        List<LocalDate> dateList = new ArrayList<>();

        // 执行 CompletableFuture 任务
        // (1) 第一步，先通过 "," 拆分字符串，然后存入原子引用对象包裹的 List 集合中；
        // (2) 第二步，遍历 List 集合，将拆分后的字符串转换为 LocalDate 对象；
        CompletableFuture
                .runAsync(() -> dateStrList.set(Arrays.asList(dateStr.split(","))))
                .thenRunAsync(() -> dateStrList.get().forEach(v -> dateList.add(LocalDate.parse(v, DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .join();

        // 输出转换结果
        for (LocalDate localDate : dateList) {
            System.out.println(localDate.toString());
        }
    }

    public static void main(String[] args) {
        thenRunAsyncExample();
    }

}
