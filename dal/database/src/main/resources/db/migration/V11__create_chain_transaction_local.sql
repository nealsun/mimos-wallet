CREATE TABLE IF NOT EXISTS `chain_transaction_local` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键',
  `from` varchar(255) DEFAULT NULL COMMENT '地址',
  `to` varchar(255) DEFAULT NULL COMMENT '地址',
  `amount` decimal(30,0) NOT NULL COMMENT '金额',
  `fee` decimal(18,0) NOT NULL COMMENT '手续费',
  `tx_hash` varchar(255)  COMMENT '交易hash',
  `nonce` int(11)  NOT  null DEFAULT 0 COMMENT 'Noce-eth需要',
  `chain_id` int(11)  NOT  null COMMENT '链Id',
  `status` int(2)  NOT  null COMMENT '状态',
  `forked_count` int(2)  NOT  null DEFAULT 0 COMMENT '分叉次数',
-- 0:初始化 1:用户签名后重新上传(nonce+1) 2 发送到node节点成功 3 链 扫描到此交易 4 失败(被覆盖)
-- '分叉处理逻辑 status 回复到2 forked_count +1'

  `create_zone` INT NOT NULL COMMENT '创建时区',
  `update_zone` INT NOT NULL DEFAULT 0 COMMENT '更新时区',
  `create_time` BIGINT NOT NULL  COMMENT '创建时间',
  `update_time` BIGINT NOT NULL DEFAULT 0 COMMENT '更新时间',
  `create_by` BIGINT NOT NULL DEFAULT 0 COMMENT '创建者',
  `update_by` BIGINT NOT NULL DEFAULT 0 COMMENT '更新者',
  `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
