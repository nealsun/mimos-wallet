CREATE TABLE IF NOT EXISTS `chain_transaction_local` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键',
  `from` varchar(255) DEFAULT NULL COMMENT '地址',
  `to` varchar(255) DEFAULT NULL COMMENT '地址',
  `amount` decimal(30,0) NOT NULL COMMENT '金额',
  `fee` decimal(18,0) NOT NULL COMMENT '手续费',
  `tx_hash` varchar(255) NOT NULL COMMENT '交易hash',
  `chain_id` int(11)  NOT  null COMMENT '链Id',
  `status` int(2)  NOT  null COMMENT '状态',

  `create_zone` INT NOT NULL COMMENT '创建时区',
  `update_zone` INT NOT NULL DEFAULT 0 COMMENT '更新时区',
  `create_time` BIGINT NOT NULL  COMMENT '创建时间',
  `update_time` BIGINT NOT NULL DEFAULT 0 COMMENT '更新时间',
  `create_by` BIGINT NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` BIGINT NOT NULL DEFAULT 0 COMMENT '更新者',
  `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
