CREATE TABLE IF NOT EXISTS `chain_summary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键',
  `fee_rate` double DEFAULT NULL  COMMENT '费率',
  `block_number` bigint(20) NOT NULL COMMENT '区块高度',
  `symbol` int(11) NOT NULL COMMENT '链类别',
  `symbol_name` varchar(255) NOT NULL COMMENT '链名称',
  `chain_id` bigint(20) NOT NULL COMMENT '链（币种）ID',

  `create_zone` INT NOT NULL COMMENT '创建时区',
  `update_zone2` INT NOT NULL COMMENT '更新时区',
  `create_time` BIGINT NOT NULL COMMENT '创建时间',
  `update_time` BIGINT NOT NULL COMMENT '更新时间',
  `create_by` BIGINT NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` BIGINT NOT NULL DEFAULT 0 COMMENT '更新者',
  `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;