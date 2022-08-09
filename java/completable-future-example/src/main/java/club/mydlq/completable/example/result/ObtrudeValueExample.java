package club.mydlq.completable.example.result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * void obtrudeValue(T value)
 *
 * 设置或重置 get() 方法和与其相关方法的返回的值，无论任务是否已经完成 (此方法仅用于错误恢复操作)。
 *
 * @author mydlq
 */
public class ObtrudeValueExample {

    public static void obtrudeValueExample() throws ExecutionException, InterruptedException {
        // 执行 CompletableFuture 任务
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "示例-执行完成");

        // 设置或重置 get 方法和与其相关方法的返回的值
        cf.obtrudeValue("示例-强制设置返回值-无论成功失败");

        // 调用 get 方法进行等待，获取执行结果并输出到控制台
        String result = cf.get();
        System.out.println(result);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        obtrudeValueExample();
    }

}
