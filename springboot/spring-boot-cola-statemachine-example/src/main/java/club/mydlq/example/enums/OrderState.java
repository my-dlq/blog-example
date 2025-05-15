package club.mydlq.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author mydlq
 */
@Getter
@AllArgsConstructor
public enum OrderState {
    // 已创建
    CREATED("已创建"),
    // 待发货
    PENDING_SHIPMENT("待发货"),
    // 已发货
    SHIPPED("已发货"),
    // 已收货
    RECEIVED("已收货"),
    // 已关闭
    CLOSED("已关闭"),
    ;

    private final String desc;

    @Override
    public String toString() {
        return this.name() + "_" + desc;
    }
}