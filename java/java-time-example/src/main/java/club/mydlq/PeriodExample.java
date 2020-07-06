package club.mydlq;

import java.time.LocalDate;
import java.time.Period;

/**
 * 日期差量示例
 *
 * @author mydlq
 */
public class PeriodExample {

    /**
     * 计算日期的差量
     */
    public static void datePeriod() {
        /* 创建两个日期对象 */
        LocalDate localDate1 = LocalDate.of(2018, 6, 16);
        LocalDate localDate2 = LocalDate.of(2020, 8, 18);
        /* 获取由两个日期之间的年数、月数和天数组成的期间 */
        Period period = Period.between(localDate1, localDate2);
        /* 输出 */
        System.out.println("年差量：" + period.getYears());
        System.out.println("月差量：" + period.getMonths());
        System.out.println("日差量：" + period.getDays());
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        // 计算日期的差量
        datePeriod();
    }

}
