package club.mydlq.pool;

/**
 * 对象
 */
public class Person {

    private Integer id;;

    public Person(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void executeTask(){
        System.out.println("对象" + id + ":" + "执行任务");
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + '}';
    }
}
