package club.mydlq.completable.example.parallel;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs);
 *
 * 传入多个 CompletableFuture<?> 阶段作为参数，然后并行执行这些 CompletableFuture<?> 阶段，
 * 当这些 CompletableFuture<?> 任意阶段执行完成时，就会返回一个新的 CompletableFuture<Object>，
 * 执行后续计算操作。
 *
 * 不过这里需要注意，如果传入的全部 CompletableFuture<?> 阶段都没有完成前，任意一个阶段执行过程
 * 出现异常并没有处理，也就是说该阶段执行过程异常完成，那么返回的 CompletableFuture<Object> 也
 * 异常完成，并将此异常作为异常原因。
 *
 * @author mydlq
 */
public class AnyOfExample {

    public static void anyOfExample() {
        Supplier<String> supplier1 = () -> {
            System.out.println("通道1");
            return "通道1-成功获取信息";
        };
        Supplier<String> supplier2 = () -> {
            System.out.println("通道2");
            return "通道2-成功获取信息";
        };
        Supplier<String> supplier3 = () -> {
            System.out.println("通道3");
            return "通道3-成功获取信息";
        };

        // 执行多个 CompletableFuture，只要任意一个执行完成就执行下一步
        CompletableFuture<Object> cf = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(supplier1),
                CompletableFuture.supplyAsync(supplier2),
                CompletableFuture.supplyAsync(supplier3)
        );

        // 进入堵塞状态，等待执行完成，输出获取的信息
        Object result = cf.join();
        System.out.println(result);
    }

    /**
     * 随机睡眠指定时间
     *
     * @param time 睡眠时间
     */
    public static void randomTimeSleep(int time){
        try {
            Thread.sleep(new Random().nextInt(time));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        anyOfExample();
    }

}
