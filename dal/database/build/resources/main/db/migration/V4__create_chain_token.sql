CREATE TABLE IF NOT EXISTS `chain_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键',
  `contract_address` varchar(255) DEFAULT NULL COMMENT '合约地址',
  `contract_poto` varchar(255) DEFAULT NULL COMMENT '合约协议',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `percision` int(11)  NOT  null COMMENT '精度 一般18位',
  `safe_confirmation` int(11) not NULL  '安全确认数' ,
  `symbol` varchar(255) NOT NULL COMMENT 'token name',

  `create_zone` INT NOT NULL COMMENT '创建时区',
  `update_zone2` INT NOT NULL COMMENT '更新时区',
  `create_time` BIGINT NOT NULL COMMENT '创建时间',
  `update_time` BIGINT NOT NULL COMMENT '更新时间',
  `create_by` BIGINT NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` BIGINT NOT NULL DEFAULT 0 COMMENT '更新者',
  `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INE=InnoDB DEFAULT CHARSET=utf8