CREATE TABLE IF NOT EXISTS `chain_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `path_id` bigint(20) DEFAULT NULL COMMENT '生成此地址的Path ID',
  `chain_id` int(11)  NOT  null COMMENT '链Id',

  `create_zone` INT NOT NULL COMMENT '创建时区',
  `update_zone` INT NOT NULL DEFAULT 0 COMMENT '更新时区',
  `create_time` BIGINT NOT NULL  COMMENT '创建时间',
  `update_time` BIGINT NOT NULL DEFAULT 0 COMMENT '更新时间',
  `create_by` BIGINT NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` BIGINT NOT NULL DEFAULT 0 COMMENT '更新者',
  `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
