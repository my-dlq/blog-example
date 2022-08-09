package club.mydlq.completable.example.status;

import java.util.concurrent.CompletableFuture;

/**
 * boolean cancel(boolean mayInterruptIfRunning)
 *
 * 查看执行了 cancel 方法取消任务的 CompletableFuture 任务是否成功取消，如果任务在完成之前被取消则返回 true，否则返回 false。
 *
 * @author mydlq
 */
public class IsCancelledExample {

    public static void isCancelledExample() throws InterruptedException {
        // 执行 CompletableFuture 任务
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> System.out.println("执行 CompletableFuture 任务"));

        // 调用 cancel 方法取消任务
        cf.cancel(true);

        // 调用 isCancelled 方法，查询任务是否成功被取消
        System.out.println("是否取消任务: " + cf.isCancelled());
    }

    public static void main(String[] args) throws InterruptedException {
        isCancelledExample();
    }

}
