package club.mydlq.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对象池工厂
 */
public class PersonPoolFactory extends BasePooledObjectFactory<Person> {

    // AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减
    private AtomicInteger atomicInteger = new AtomicInteger(1);

    /**
     * 在对象池中创建对象
     *
     * @return 自定义对象
     */
    public Person create() {
        return new Person(atomicInteger.getAndAdd(1));
    }

    /**
     * common-pool2 中创建了 DefaultPooledObject 对象对对象池中对象进行的包装。
     * 将我们自定义的对象放置到这个包装中，工具会统计对象的状态、创建时间、更新时间、返回时间、出借时间、使用时间等等信息进行统计
     *
     * @param person 自定义对象
     * @return 对象池
     */
    public PooledObject<Person> wrap(Person person) {
        return new DefaultPooledObject<>(person);
    }

    /**
     * 销毁对象
     * @param p 对象池
     * @throws Exception 异常
     */
    @Override
    public void destroyObject(PooledObject<Person> p) throws Exception {
        super.destroyObject(p);
    }

    /**
     * 校验对象是否可用
     * @param p 对象池
     * @return 对象是否可用结果，boolean
     */
    @Override
    public boolean validateObject(PooledObject<Person> p) {
        return super.validateObject(p);
    }

    /**
     * 激活钝化的对象系列操作
     * @param p 对象池
     * @throws Exception 异常信息
     */
    @Override
    public void activateObject(PooledObject<Person> p) throws Exception {
        super.activateObject(p);
    }

    /**
     * 钝化未使用的对象
     * @param p 对象池
     * @throws Exception 异常信息
     */
    @Override
    public void passivateObject(PooledObject<Person> p) throws Exception {
        super.passivateObject(p);
    }
}
