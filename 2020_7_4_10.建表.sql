


-- '商家优惠券表'
CREATE TABLE IF NOT EXISTS `sj_youhuiquan`(
	`sj_id` INT COMMENT '商家编号(外)',
	`youhuiquan_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '优惠券编号',
	`youhui_money` FLOAT(10) NOT NULL COMMENT '优惠金额',
	`jidan_least_count` INT(10) NOT NULL DEFAULT 0 COMMENT '集单要求数',
	`youhuiquan_begin_time` DATETIME NOT NULL COMMENT '起始日期',
	`youhuiquan_end_time` DATETIME NOT NULL COMMENT '结束日期',
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商家优惠券表';

-- '商家信息表'(主商家内容)
CREATE TABLE IF NOT EXISTS `sj_data`(
	`sj_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '商家编号',
	`sj_name` VARCHAR(20) UNIQUE NOT NULL COMMENT '商家名',
	`sj_xinji` INT(2) NOT NULL DEFAULT 1 COMMENT '商家星级',
	`avg_consume` FLOAT(10) COMMENT '人均消费',
	`total_consume` FLOAT(20) COMMENT '总销量'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商家信息表';

-- '满减方案表'
CREATE TABLE IF NOT EXISTS `sj_manjian`(
	`sj_id` INT COMMENT '商家编号(外)',
	`mj_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '满减编号',
	`mj_top_money` FLOAT(10) NOT NULL COMMENT '满减金额',
	`mj_discount_money` FLOAT(10) NOT NULL COMMENT '优惠金额',
	`if_add_youhuiquan` BIT(1) NOT NULL COMMENT '是否可与优惠券叠加',
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT'满减方案表';

-- '商品类别表'
CREATE TABLE IF NOT EXISTS `sp_leibie`(
	`sj_id` INT COMMENT '商家编号(外)',
	`leibie_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '分类编号',
	`leibie_name` VARCHAR(20) UNIQUE NOT NULL COMMENT '分类栏目名',
	`sp_count` INT(10) NOT NULL COMMENT '商品数量',
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品类别表';

-- '商品详情表'
CREATE TABLE IF NOT EXISTS `sp_data`(
	`sj_id` INT COMMENT '商家编号(外)',
	`sp_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '商品编号',
	`sp_belong_leibie_id` INT COMMENT'所属分类编号(外)',
	`sp_name` VARCHAR(40) UNIQUE NOT NULL COMMENT '商品名',
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
	`sp_evaluate_content` VARCHAR(100) NOT NULL COMMENT '评价内容',
	`sp_evaluate_time` DATETIME NOT NULL COMMENT '评价时间',
	`sp_evaluate_spxinji` INT(1) NOT NULL DEFAULT 5 COMMENT '商品评价星级',
	`sp_evaluate_qsxinji` INT(1) PRIMARY KEY NOT NULL DEFAULT 5 COMMENT '骑手评价星级',
	FOREIGN KEY (`sp_id`) REFERENCES `sp_data`(`sp_id`),
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`),
	FOREIGN KEY (`user_id`) REFERENCES `user_data`(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品评价表';



-- '骑手信息表'(主骑手内容)
CREATE TABLE IF NOT EXISTS `qs_data`(
	`qs_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '骑手编号',
	`qs_name` VARCHAR(20) UNIQUE NOT NULL COMMENT '骑手姓名',
	`qs_join_date` DATETIME NOT NULL COMMENT '入职日期',
	`qs_grade` VARCHAR(20) NOT NULL DEFAULT '新手' COMMENT '骑手等级'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '骑手信息表';

-- '骑手入账表'
CREATE TABLE IF NOT EXISTS `qs_bill`(
	`qs_id` INT COMMENT '骑手编号(外)',
	`order_id` INT COMMENT '订单编号(外)',
	`qs_getmoney_time` DATETIME NOT NULL COMMENT '骑手入账时间',-- 按照骑手送达时间算
	`qs_getmoney` FLOAT(10) NOT NULL COMMENT '骑手入账金额',
	`sp_evaluate_qsxinji` INT COMMENT '骑手评价星级',
	FOREIGN KEY (`qs_id`) REFERENCES `qs_data`(`qs_id`),
	FOREIGN KEY (`order_id`) REFERENCES `order_data`(`order_id`),
	FOREIGN KEY (`sp_evaluate_qsxinji`) REFERENCES `sp_evaluate`(`sp_evaluate_qsxinji`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '骑手入账表';






-- '用户信息表'(主用户内容)
CREATE TABLE IF NOT EXISTS `user_data`(
	`user_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '用户编号',
	`user_name` VARCHAR(20) UNIQUE NOT NULL COMMENT '姓名',
	`user_sex` BIT(1) NOT NULL COMMENT '性别',-- 1为男，0为女
	`user_pwd` VARCHAR(20) NOT NULL COMMENT '密码',
	`user_phonenum` VARCHAR(12) NOT NULL UNIQUE COMMENT '手机号码',
	`user_email` VARCHAR(40) NOT NULL COMMENT '邮箱',
	`user_city` VARCHAR(20) NOT NULL COMMENT '所在城市',
	`user_register_time` DATETIME NOT NULL COMMENT '注册时间',
	`user_vip_end_time`DATETIME DEFAULT NULL COMMENT '会员截止时间'-- 根据会员截止时间，若为null或时间早于现在，为非会员或过期会员
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户信息表';

-- '用户优惠券持有表'
CREATE TABLE IF NOT EXISTS `user_youhuiquan_get`(
	`user_id` INT COMMENT '用户编号(外)',
	`youhuiquan_id` INT COMMENT '优惠券编号(外)',
	`sj_id` INT COMMENT '优惠券所属商家编号(外)',
	`youhui_money` FLOAT(10) NOT NULL COMMENT '优惠金额',
	`youhuiquan_count` INT(10) NOT NULL COMMENT '优惠券数量',
	`youhuiquan_end_time` DATETIME NOT NULL COMMENT '优惠券截止时间',
	FOREIGN KEY (`user_id`) REFERENCES `user_data`(`user_id`),
	FOREIGN KEY (`youhuiquan_id`) REFERENCES `sj_youhuiquan`(`youhuiquan_id`),
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户优惠券持有表';

-- '用户集单送券表'
CREATE TABLE IF NOT EXISTS `user_youhuiquan_jding`(
	`user_id` INT COMMENT '用户编号(外)',
	`youhuiquan_id` INT COMMENT '优惠券编号(外)',
	`sj_id` INT COMMENT '优惠券所属商家编号(外)',
	`jidan_least_count` INT(10) NOT NULL DEFAULT 0 COMMENT '集单要求数',
	`order_count` INT(10) NOT NULL DEFAULT 0 COMMENT '已购订单数',
	FOREIGN KEY (`user_id`) REFERENCES `user_data`(`user_id`),
	FOREIGN KEY (`youhuiquan_id`) REFERENCES `sj_youhuiquan`(`youhuiquan_id`),
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户集单送表';

-- '用户配送地址表'
CREATE TABLE IF NOT EXISTS `user_address`(
	`user_address_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '用户地址编号',
	`user_id` INT COMMENT '用户编号(外)',
	`user_province` VARCHAR(20) NOT NULL COMMENT '省',
	`user_city` VARCHAR(20) NOT NULL COMMENT '市',
	`user_area` VARCHAR(20) NOT NULL COMMENT '区',
	`user_address_detail` VARCHAR(40) NOT NULL COMMENT '具体地址',
	`user_ad_name` VARCHAR(20) NOT NULL COMMENT '该地址姓名',
	`user_ad_phonenum` VARCHAR(12) NOT NULL COMMENT '该地址电话',
	FOREIGN KEY (`user_id`) REFERENCES `user_data`(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户配送地址表';

-- '商品订单表'
CREATE TABLE IF NOT EXISTS `order_data`(
	`order_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '订单编号',
	`sj_id` INT COMMENT '商家编号(外)',
	`user_id` INT COMMENT '用户编号(外)',
	`qs_id` INT COMMENT '骑手编号(外)',
	`order_origin_money` FLOAT(10) NOT NULL COMMENT '订单原始金额',
	`order_final_money` FLOAT(10) NOT NULL COMMENT '订单最终金额',
	`mj_id` INT COMMENT '使用满减编号(外)',
	`youhuiquan_id` INT COMMENT '优惠券编号(外)',
	`order_set_time` DATETIME NOT NULL COMMENT '下单时间',
	`order_set_arrive_time` DATETIME NOT NULL COMMENT '期望送达时间',
	`user_address_id` INT COMMENT '用户地址编号',
	`order_state` VARCHAR(20) NOT NULL COMMENT '订单状态', -- 包含配送：transporting,超时：out_of_time,已送达：arrived,取消订单：cancel
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`),
	FOREIGN KEY (`user_id`) REFERENCES `user_data`(`user_id`),
	FOREIGN KEY (`qs_id`) REFERENCES `qs_data`(`qs_id`),
	FOREIGN KEY (`mj_id`) REFERENCES `sj_manjian`(`mj_id`),
	FOREIGN KEY (`youhuiquan_id`) REFERENCES `sj_youhuiquan`(`youhuiquan_id`),
	FOREIGN KEY (`user_address_id`) REFERENCES `user_address`(`user_address_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品订单表';

-- '订单内容表'
CREATE TABLE IF NOT EXISTS `order_content`(
	`sj_id` INT COMMENT '商家编号(外)',
	`order_id` INT COMMENT '订单编号(外)',
	`sp_id` INT COMMENT '商品编号(外)',
	`order_count` INT(10) NOT NULL COMMENT '单品数量',
	`order_one_money` FLOAT(10) NOT NULL COMMENT '单品金额',
	`order_one_discount` FLOAT(10) NOT NULL COMMENT '单品优惠金额',
	FOREIGN KEY (`sj_id`) REFERENCES `sj_data`(`sj_id`),
	FOREIGN KEY (`order_id`) REFERENCES `order_data`(`order_id`),
	FOREIGN KEY (`sp_id`) REFERENCES `sp_data`(`sp_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '订单内容表';





-- '管理员信息表'
CREATE TABLE IF NOT EXISTS `cmd_data`(
	`cmd_id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
	`cmd_name`VARCHAR(20) NOT NULL COMMENT '管理员姓名',
	`cmd_pwd`VARCHAR(20) NOT NULL COMMENT '管理员密码'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '管理员信息表';





