CREATE TABLE `order`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `order_id`    bigint(20) NOT NULL COMMENT '订单ID',
    `order_state` enum('CREATED','PENDING_SHIPMENT','SHIPPED','RECEIVED','CANCELED','CLOSED') NOT NULL COMMENT '订单状态',
    `create_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='订单表';