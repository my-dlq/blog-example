CREATE TABLE `sys_oper_log`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `module`          varchar(50)         DEFAULT NULL COMMENT '系统模块',
    `oper_desc`       varchar(200)        DEFAULT NULL COMMENT '操作描述',
    `request_uri`     varchar(255)        DEFAULT NULL COMMENT '请求URL',
    `request_ip`      varchar(50)         DEFAULT NULL COMMENT '请求IP',
    `request_method`  varchar(20)         DEFAULT NULL COMMENT '请求方式',
    `request_param`   varchar(2000)       DEFAULT NULL COMMENT '请求参数',
    `response_result` varchar(2000)       DEFAULT NULL COMMENT '响应结果',
    `oper_type`       tinyint(2)          DEFAULT '0' COMMENT '业务类型 (0查询 1新增 2修改 3删除 4上传 5下载)',
    `oper_status`     tinyint(2)          DEFAULT NULL COMMENT '操作状态 (0失败 1成功)',
    `error_info`      varchar(2000)       DEFAULT NULL COMMENT '错误信息',
    `operator`        varchar(50)         DEFAULT NULL COMMENT '操作人',
    `oper_time`       datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统操作日志';