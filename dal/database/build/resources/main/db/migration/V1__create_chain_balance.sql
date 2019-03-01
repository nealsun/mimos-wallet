CREATE TABLE IF NOT EXISTS `chain_balcance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `balance_after` decimal(19,2) DEFAULT NULL COMMENT '修改后余额',
  `balance_befor` decimal(19,2) DEFAULT NULL COMMENT '修改前余额',
  `block_hash` varchar(255) DEFAULT NULL  COMMENT '区块Hash',
  `contract_id` varchar(255) DEFAULT NULL  COMMENT '合约ID',
  `height` bigint(20) NOT NULL COMMENT '区块高度',
  `is_token` bit(1) NOT NULL COMMENT '是否为Token',
  `obsoleted` bit(1) NOT NULL COMMENT '是否废弃',
  `chain_id` bigint(20) NOT NULL COMMENT '链（币种）ID',
  `tx_action_id` bigint(20) NOT NULL COMMENT 'TransactionAction 流水ID',
  `tx_hash` varchar(255) NOT NULL COMMENT '交易Hash',

   `create_zone` INT NOT NULL COMMENT '创建时区',
  `update_zone2` INT NOT NULL COMMENT '更新时区',
  `create_time` BIGINT NOT NULL COMMENT '创建时间',
  `update_time` BIGINT NOT NULL COMMENT '更新时间',
  `create_by` BIGINT NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` BIGINT NOT NULL DEFAULT 0 COMMENT '更新者',
  `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;