package club.mydlq.completable.example.status;

import java.util.concurrent.CompletableFuture;

/**
 * boolean isCompletedExceptionally()
 *
 * 查看当前阶段任务是否以异常的方式执行完成。比如取消任务、突然终止任务或者执行过程出现异常等，
 * 都属于异常方式执行完成，如果是以异常方式完成则返回 true，否则返回 false。
 *
 * @author mydlq
 */
public class IsCompletedExceptionallyExample {

    public static void isCompletedExceptionallyExample() throws InterruptedException {
        // 执行 CompletableFuture 任务
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            System.out.println("执行中...");
            // 模拟发生异常
            System.out.println(1/0);
        });

        // 等待1秒确保任务执行完成
        Thread.sleep(1000L);

        // 调用 isCompletedExceptionally 方法验证当前阶段是否异常完成
        System.out.println("是否异常完成: " + cf.isCompletedExceptionally());
    }

    public static void main(String[] args) throws InterruptedException {
        isCompletedExceptionallyExample();
    }

}
