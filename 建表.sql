-- ～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～
-- 商品信息表集

-- '商家优惠券表'
CREATE TABLE IF NOT EXISTS `sj_youhuiquan`(
	`sj_id` INT COMMENT '商家编号(外)',
	`youhuiquan_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '优惠券编号',
	`youhui_money` FLOAT(10) NOT NULL COMMENT '优惠金额',
	`jidan_least_count` INT(11) NOT NULL DEFAULT 0 COMMENT '集单要求数',
	`youhuiquan_begin_time` DATETIME NOT NULL COMMENT '起始日期',
	`youhuiquan_end_time` DATETIME NOT NULL COMMENT '结束日期',
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商家优惠券表';

-- '商家信息表'(主商家内容)
CREATE TABLE IF NOT EXISTS `sj_data`(
	`sj_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '商家编号',
	`sj_name` VARCHAR(50) UNIQUE NOT NULL COMMENT '商家名',
	`sj_xinji` INT(2) NOT NULL DEFAULT 1 COMMENT '商家星级',
	`avg_consume` FLOAT(10) COMMENT '人均消费',
	`total_consume` FLOAT(20) COMMENT '总销量'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商家信息表';

-- '满减方案表'
CREATE TABLE IF NOT EXISTS `sj_manjian`(
	`sj_id` INT COMMENT '商家编号(外)',
	`mj_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '满减编号',
	`mj_top_money` FLOAT(10) NOT NULL COMMENT '满减金额',
	`mj_count_money` FLOAT(10) NOT NULL COMMENT '优惠金额',
	`if_add_youhuiquan` BIT(1) NOT NULL COMMENT '是否可与优惠券叠加',
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT'满减方案表';

-- '商品类别表'
CREATE TABLE IF NOT EXISTS `sp_leibie`(
	`sj_id` INT COMMENT '商家编号(外)',
	`leibie_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '分类编号',
	`leibie_name` VARCHAR(50) UNIQUE NOT NULL COMMENT '分类栏目名',
	`sp_count` int(10) NOT NULL COMMENT '商品数量',
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品类别表';

-- '商品详情表'
CREATE TABLE IF NOT EXISTS `sp_data`(
	`sj_id` INT COMMENT '商家编号(外)',
	`sp_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '商品编号',
	`sp_belong_leibie_id` INT COMMENT'所属分类编号(外)',
	`sp_name` VARCHAR(50) UNIQUE NOT NULL COMMENT '商品名',
	`sp_price` FLOAT(10) NOT NULL COMMENT '商品价格',
	`sp_final_price` FLOAT(10) DEFAULT NULL COMMENT '商品优惠后价格',
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`),
	FOREIGN KEY (`sp_belong_leibie_id`) REFERENCES `sp_leibie`(`leibie_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品详情表';

-- '商品评价表'
CREATE TABLE IF NOT EXISTS `sp_evaluate`(
	`sp_id` INT COMMENT '商品编号(外)',
	`sj_id` INT COMMENT '商家编号(外)',
	`user_id` INT COMMENT '用户编号(外)',
	`sp_evaluate_content` VARCHAR(50) NOT NULL COMMENT '评价内容',
	`sp_evaluate_time` DATETIME NOT NULL COMMENT '评价时间',
	`sp_evaluate_xinji` INT(2) NOT NULL DEFAULT 1 COMMENT '评价星级',
	FOREIGN KEY (`sp_id`) REFERENCES `sp_data`(`sp_id`),
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`),
	FOREIGN KEY (`user_id`) REFERENCES `user_data`(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品评价表';
-- FOREIGN KEY (``) REFERENCES ``(``)
-- ～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～
-- 骑手信息表集

-- '骑手信息表'(主骑手内容)
CREATE TABLE IF NOT EXISTS `qs_data`(
	`qs_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '骑手编号',
	`qs_name` VARCHAR(50) UNIQUE NOT NULL COMMENT '骑手姓名',
	`qs_join_date` DATETIME NOT NULL COMMENT '入职日期',
	`qs_grade` VARCHAR(50) NOT NULL DEFAULT '新手' COMMENT '骑手等级'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '骑手信息表';

-- '骑手入账表'
CREATE TABLE IF NOT EXISTS ``(
	`qs_id` INT COMMENT '骑手编号(外)',
	`qs_order_id` INT COMMENT '订单编号(外)',
	`qs_order_time` DATETIME COMMENT '订单时间(外)',
	``
	
	
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '骑手入账表';

-- ～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～
-- 用户信息表集

-- '用户信息表'(主用户内容)
CREATE TABLE IF NOT EXISTS `user_data`(
	`user_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '用户编号',
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户信息表';

-- '用户优惠券持有表'
CREATE TABLE IF NOT EXISTS ``(
	
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户优惠券持有表';

-- '用户集单送券表'
CREATE TABLE IF NOT EXISTS ``(
	
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户集单送券表';

-- '用户信息表'
CREATE TABLE IF NOT EXISTS ``(
	
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户信息表';

-- '用户信息表'
CREATE TABLE IF NOT EXISTS ``(
	
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户信息表';

-- '用户信息表'
CREATE TABLE IF NOT EXISTS ``(
	
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户信息表';

-- '用户信息表'
CREATE TABLE IF NOT EXISTS ``(
	
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户信息表';
-- ～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～
-- 管理员信息表






