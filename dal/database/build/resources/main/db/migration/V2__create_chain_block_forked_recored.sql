CREATE TABLE IF NOT EXISTS `chain_block_forked_recored` (
  `id` bigint(20) NOT NULL  AUTO_INCREMENT  COMMENT '主键',
  `block_hash` varchar(255) DEFAULT NULL  COMMENT '区块Hash',
  `block_number` bigint(20) NOT NULL COMMENT '区块高度',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '0:待处理，1:处理成功',
  `chain_id` bigint(20) NOT NULL COMMENT '链（币种）ID',

  `create_zone` INT NOT NULL COMMENT '创建时区',
  `update_zone` INT NOT NULL DEFAULT 0 COMMENT '更新时区',
  `create_time` BIGINT NOT NULL  COMMENT '创建时间',
  `update_time` BIGINT NOT NULL DEFAULT 0 COMMENT '更新时间',
  `create_by` BIGINT NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` BIGINT NOT NULL DEFAULT 0 COMMENT '更新者',
  `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
