package club.mydlq;

import club.mydlq.pool.Person;
import club.mydlq.pool.PersonPoolFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试对象池的 main 方法
 */
public class Application {

    public static void main(String[] args) {
        // =====================创建线程池=====================
        ExecutorService excutor = Executors.newFixedThreadPool(10);
        // =====================创建对象池=====================
        // 对象池工厂
        PersonPoolFactory personPoolFactory = new PersonPoolFactory();
        // 对象池配置
        GenericObjectPoolConfig<Person> objectPoolConfig = new GenericObjectPoolConfig<>();
        objectPoolConfig.setMaxTotal(5);
        // 对象池
        GenericObjectPool<Person> personPool = new GenericObjectPool<>(personPoolFactory, objectPoolConfig);
        // =====================测试对象池=====================
        // 循环100次，从线程池中取多个多线程执行任务，来测试对象池
        for (int i = 0; i < 15; i++) {
            excutor.submit(new Thread(() -> {
                // 模拟从对象池取出对象，执行任务
                Person person = null;
                try {
                    // 从对象池取出对象
                    person = personPool.borrowObject();
                    // 让对象工作
                    person.executeTask();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 回收对象到对象池
                    if (person != null) {
                        personPool.returnObject(person);
                    }
                }
            }));
        }
    }

}
