package club.mydlq.completable.example.exception;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn)
 *
 * 判断上一个任务执行时是否发生异常，如果是则就会执行 exceptionally 方法，并且将上一步异常作为当前方法的参数，
 * 然后对异常进行处理。当然，如果上一阶段执行过程中没有出现异常，则不会执行 exceptionally 方法。
 *
 * @author mydlq
 */
public class ExceptionallyExample {

    public static void exceptionallyExample() throws ExecutionException, InterruptedException {
        // 执行 CompletableFuture 串行任务，并且使用 exceptionally 方法:
        // - 如果 exceptionally 方法的上一阶段执行过程中出现异常，则会执行 exceptionally 阶段；
        // - 如果 exceptionally 方法的上一阶段执行过程中没有出现异常，则不会执行 exceptionally 阶段；
        CompletableFuture<String> cf = CompletableFuture
                // 执行任务，50%概率发生异常，50%概率返回正常值
                .supplyAsync(() -> {
                    if (new Random().nextInt(2) != 0) {
                        throw new RuntimeException("模拟发生异常");
                    }
                    return "正常结束";
                })
                // 处理上一步中抛出的异常
                .exceptionally(Throwable::getMessage);

        // 调用 get 方法进行等待，获取执行结果
        String result = cf.get();
        System.out.println(result);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        exceptionallyExample();
    }

}
