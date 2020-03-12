-- ----------------------------
-- 20200312 createDataBase
-- ----------------------------
CREATE DATABASE IF NOT EXISTS db2019 DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- 20200312 payment
-- ----------------------------
use db2019;
CREATE TABLE `payment` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`serial` varchar(200) DEFAULT '支付流水号',
PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

