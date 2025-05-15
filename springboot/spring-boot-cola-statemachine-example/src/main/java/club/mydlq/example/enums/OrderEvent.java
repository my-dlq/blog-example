package club.mydlq.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单事件枚举
 *
 * @author mydlq
 */
@Getter
@AllArgsConstructor
public enum OrderEvent {
    // 支付
    PAY("支付" ),
    // 发货
    SHIP("发货" ),
    // 收货
    RECEIVE("收货" ),
    // 取消
    CANCEL("取消" ),
    ;

    private final String desc;

    @Override
    public String toString() {
        return this.name() + "_" + desc;
    }
}