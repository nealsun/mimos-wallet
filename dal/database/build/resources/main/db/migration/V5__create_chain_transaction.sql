CREATE TABLE IF NOT EXISTS `chain_transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `block_hash` varchar(255) NOT NULL COMMENT '区块hash',
  `block_number` bigint(20) NOT NULL COMMENT '区块高度',
  `chain_id` bigint(20) NOT NULL COMMENT '链ID',
  `tx_hash` varchar(255) NOT NULL COMMENT '交易hash',
  `tx_time` bigint(20) DEFAULT NULL COMMENT '交易发生时间',
  `obsoleted` bit(1) NOT NULL DEFAULT 0 COMMENT '是否废弃',

  `create_zone` INT NOT NULL COMMENT '创建时区',
  `update_zone` INT NOT NULL DEFAULT 0 COMMENT '更新时区',
  `create_time` BIGINT NOT NULL  COMMENT '创建时间',
  `update_time` BIGINT NOT NULL DEFAULT 0 COMMENT '更新时间',
  `create_by` BIGINT NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` BIGINT NOT NULL DEFAULT 0 COMMENT '更新者',
  `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;