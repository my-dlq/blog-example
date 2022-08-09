package club.mydlq.completable.example.status;

import java.util.concurrent.CompletableFuture;

/**
 * boolean isDone()
 *
 * 查看当前阶段任务是否执行完成，如果当前阶段执行完成 (无论是正常完成还是异常完成) 则返回 true，否则返回 false。
 *
 * @author mydlq
 */
public class IsDoneExample {

    public static void isDoneExample() throws InterruptedException {
        // 执行 CompletableFuture 任务
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> System.out.println("任务执行中..."));

        // 调用 isDone 方法查看任务是否执行完成
        System.out.println("任务是否完成: " + cf.isDone());

        // 等待1秒时间
        Thread.sleep(1000L);

        // 调用 isDone 方法再次查看任务是否执行完成
        System.out.println("任务是否完成: " + cf.isDone());
    }

    public static void main(String[] args) throws InterruptedException {
        isDoneExample();
    }

}
