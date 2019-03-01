CREATE TABLE IF NOT EXISTS `chain_transaction_raw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chain_id` bigint(20) NOT NULL COMMENT '链ID',
  `tx_hash` varchar(255) NOT NULL COMMENT '交易hash',
  `statue` bigint(1) NOT NULL COMMENT '0=默认，1=发送给node，2=node已经返回结果',
  `is_suc` bit(1) NOT NULL COMMENT 'node处理是否成功',
  `sequnce` bit(11) NOT NULL COMMENT '发送次数',
  `tx_raw` varchar(355) NOT NULL COMMENT '签名数据',

  `create_zone` INT NOT NULL COMMENT '创建时区',
  `update_zone2` INT NOT NULL COMMENT '更新时区',
  `create_time` BIGINT NOT NULL COMMENT '创建时间',
  `update_time` BIGINT NOT NULL COMMENT '更新时间',
  `create_by` BIGINT NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` BIGINT NOT NULL DEFAULT 0 COMMENT '更新者',
  `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8