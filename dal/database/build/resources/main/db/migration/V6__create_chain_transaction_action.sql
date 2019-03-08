CREATE TABLE IF NOT EXISTS `chain_transaction_action` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `address` varchar(255) NOT NULL COMMENT '地址',
  `amount` decimal(30,0) NOT NULL COMMENT '金额',
  `block_hash` varchar(255) NOT NULL COMMENT '区块hash',
  `block_number` bigint(20) NOT NULL COMMENT '区块高度',
  `contract_id` varchar(255) NOT NULL COMMENT '合约地址',
  `action_idex` int(11) NOT NULL COMMENT 'index',
  `is_income` bit(1) NOT NULL COMMENT 'true 收入,false 转出',
  `is_token` bit(1) NOT NULL COMMENT 'ture token 交易,false Native',
  `obsoleted` bit(1) NOT NULL DEFAULT 0 COMMENT 'ture 废弃,false 正常',
  `chain_id` bigint(20) NOT NULL COMMENT '链ID',
  `tx_hash` varchar(255) NOT NULL COMMENT '交易hash',
  `type` int(1) NOT NULL COMMENT '交易类型(暂时没用)',

  `create_zone` INT NOT NULL COMMENT '创建时区',
  `update_zone` INT NOT NULL DEFAULT 0 COMMENT '更新时区',
  `create_time` BIGINT NOT NULL  COMMENT '创建时间',
  `update_time` BIGINT NOT NULL DEFAULT 0 COMMENT '更新时间',
  `create_by` BIGINT NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` BIGINT NOT NULL DEFAULT 0 COMMENT '更新者',
  `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8