package club.mydlq.example.mapper;

import club.mydlq.example.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单 Mapper
 *
 * @author mydlq
 */
@Mapper
public interface OrderMapper {
    /**
     * 根据ID查询订单信息
     *
     * @param orderId 订单ID
     * @return 执行结果
     */
    Order selectByOrderId(Long orderId);

    /**
     * 保存订单信息
     *
     * @param order 订单信息
     * @return 执行结果
     */
    int save(Order order);

    /**
     * 更新订单信息
     *
     * @param order 订单信息
     * @return 执行结果
     */
    int update(Order order);
}
