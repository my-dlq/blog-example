package club.mydlq.completable.example.result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * void obtrudeException(Throwable ex)
 *
 * 设置 get() 方法和与其相关的方法后续执行抛出指定异常，无论是否已经完成 (此方法仅用于错误恢复操作)。
 *
 * @author mydlq
 */
public class ObtrudeExceptionExample {

    public static void obtrudeExceptionExample() throws ExecutionException, InterruptedException {
        // 执行 CompletableFuture 任务
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "示例-执行完成");

        // 设置 get 方法和与其相关的方法后续执行抛出指定异常
        cf.obtrudeException(new Exception("未知异常"));

        // 调用 get 方法进行等待，获取执行结果并输出
        String result = cf.get();
        System.out.println(result);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        obtrudeExceptionExample();
    }

}
