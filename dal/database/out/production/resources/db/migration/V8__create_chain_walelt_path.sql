CREATE TABLE IF NOT EXISTS `chain_wallet_path` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键',

  `chain_id` bigint(20) DEFAULT NULL COMMENT '链id',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id WalletRoot',
  `pub_key` varchar(255) NOT NULL comment '公钥',
  `wallet_path` varchar(255) NOT NULL comment '路径',

  `create_zone` INT NOT NULL COMMENT '创建时区',
  `update_zone` INT NOT NULL DEFAULT 0 COMMENT '更新时区',
  `create_time` BIGINT NOT NULL  COMMENT '创建时间',
  `update_time` BIGINT NOT NULL DEFAULT 0 COMMENT '更新时间',
  `create_by` BIGINT NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` BIGINT NOT NULL DEFAULT 0 COMMENT '更新者',
  `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
