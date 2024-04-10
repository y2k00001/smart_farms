DROP TABLE IF EXISTS t_farmland_lessee;
CREATE TABLE t_farmland_lessee(
    `id` VARCHAR(90) NOT NULL  COMMENT 'id' ,
    `farmland_lessee_id` VARCHAR(255)   COMMENT '农田租赁合同ID' ,
    `farmland_id` VARCHAR(255)   COMMENT '农田id' ,
    `lessee_id` VARCHAR(255)   COMMENT '租户id' ,
    `lessee_price` DECIMAL(24,6)   COMMENT '租赁单价;单位：元/天' ,
    `lessee_amount` DECIMAL(24,6)   COMMENT '租赁总金额;单位：元' ,
    `lessee_day` INT   COMMENT '租赁天数;单位:天' ,
    `lessee_start_date` DATETIME   COMMENT '租赁开始时间' ,
    `lessee_end_date` DATETIME   COMMENT '租赁结束时间' ,
    `status` INT   COMMENT '租赁状态;0，待支付，1，已生效，2已结束，3违约' ,
    `lessee_files` VARCHAR(255)   COMMENT '合同附件id集合' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '农田租赁信息表';

DROP TABLE IF EXISTS t_lessee;
CREATE TABLE t_lessee(
    `id` VARCHAR(40) NOT NULL  COMMENT 'id' ,
    `user_id` VARCHAR(255)   COMMENT '系统用户ID' ,
    `lessee_id` VARCHAR(255)   COMMENT '租户ID' ,
    `lessee_name` VARCHAR(255)   COMMENT '租户姓名' ,
    `lessee_phone` INT(20)   COMMENT '租户手机号' ,
    `remark` VARCHAR(255)   COMMENT '备注' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` VARCHAR(1) NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '租户信息表';

DROP TABLE IF EXISTS t_farmland;
CREATE TABLE t_farmland(
    `id` VARCHAR(90) NOT NULL  COMMENT 'id' ,
    `farmland_id` VARCHAR(255)   COMMENT 'farmland_id' ,
    `area` VARCHAR(255)   COMMENT '面积单位;平方米' ,
    `lat` VARCHAR(255)   COMMENT '维度' ,
    `long` VARCHAR(255)   COMMENT '经度' ,
    `location` VARCHAR(255)   COMMENT '位置' ,
    `status` VARCHAR(255)   COMMENT '农田状态;0.未上架；1，待租赁，2，已租赁' ,
    `lease_price` DECIMAL(24,4)   COMMENT '租赁价格;单位：元/天' ,
    `description` TEXT   COMMENT '描述' ,
    `file_ids` VARCHAR(255)   COMMENT '附件ID集合;逗号分割' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` VARCHAR(1) NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '农田信息表';

DROP TABLE IF EXISTS t_seed;
CREATE TABLE t_seed(
    `id` VARCHAR(50) NOT NULL  COMMENT 'id' ,
    `seed_id` VARCHAR(100)   COMMENT '种子ID' ,
    `seed_name` VARCHAR(255)   COMMENT '种子名称' ,
    `seed_type` INT   COMMENT '种子类型' ,
    `origin` VARCHAR(255)   COMMENT '种子产地' ,
    `growth_cycle` INT   COMMENT '生长周期;单位天' ,
    `yield` DECIMAL(24,6)   COMMENT '产量;单位吨/亩' ,
    `climate` TEXT(255)   COMMENT '适宜气候描述' ,
    `planting_technique` TEXT(255)   COMMENT '种植技术描述' ,
    `remarks` VARCHAR(255)   COMMENT '备注' ,
    `unit_price` DECIMAL(24,6)   COMMENT '单价;单位元/斤' ,
    `stock_total` VARCHAR(255)   COMMENT '库存数量' ,
    `status` VARCHAR(255)   COMMENT '商品状态;0待上架，10在售，20已下架' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '种子信息表';

DROP TABLE IF EXISTS t_growth;
CREATE TABLE t_growth(
    `id` VARCHAR(50) NOT NULL  COMMENT 'id' ,
    `growth_id` VARCHAR(100)   COMMENT '生长周期ID' ,
    `farmland_id` VARCHAR(255)   COMMENT '农田ID' ,
    `seed_id` VARCHAR(255)   COMMENT '种子ID' ,
    `farmland_snap` VARCHAR(255)   COMMENT '农田信息快照' ,
    `seed_snap` VARCHAR(255)   COMMENT '种子信息快照' ,
    `farmlan_name` VARCHAR(255)   COMMENT '农田昵称' ,
    `crop_name` VARCHAR(255)   COMMENT '农作物名称' ,
    `scientific_name` VARCHAR(255)   COMMENT '学名' ,
    `growth_stage` VARCHAR(255)   COMMENT '生长阶段;（如：播种期、生长期、成熟期等）' ,
    `growth_time` VARCHAR(255)   COMMENT '生长时间;（以天为单位）' ,
    `start_date` VARCHAR(255)   COMMENT '生长阶段的起始日期' ,
    `end_date` VARCHAR(255)   COMMENT '生长阶段的结束日期' ,
    `growth_conditions` TEXT(900)   COMMENT '生长条件;（如土壤湿度、光照需求、温度范围等）' ,
    `fertilizer_requirements` TEXT(255)   COMMENT '施肥要求;（肥料种类、施肥时间、施肥量等）' ,
    `irrigation_needs` TEXT(255)   COMMENT '灌溉需求;（灌溉方式、灌溉量、灌溉频率等）' ,
    `pest_control_measures` TEXT(255)   COMMENT '病虫害防治措施;（如农药使用、生物防治等）' ,
    `climate_suitability` TEXT(255)   COMMENT '气候适应性;（描述作物对气候的敏感性和适应性）' ,
    `soil_type` VARCHAR(255)   COMMENT '土壤类型;（如砂土、壤土、粘土等）' ,
    `growth_phase_details` TEXT(255)   COMMENT '生长阶段详细信息;（每个阶段的具体特点、注意事项等）' ,
    `yield_expectation` DECIMAL(24,6)   COMMENT '预期产量;（单位面积或单棵作物的预期产量）' ,
    `variety_description` TEXT(255)   COMMENT '品种描述;（包括特性、优势等）' ,
    `remarks` VARCHAR(255)   COMMENT '' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '农作物生长周期信息表';

DROP TABLE IF EXISTS t_order_pay;
CREATE TABLE t_order_pay(
    `id` VARCHAR(255) NOT NULL  COMMENT 'id' ,
    `order_id` VARCHAR(255)   COMMENT '账单ID' ,
    `order_type` VARCHAR(255)   COMMENT '账单类型;1.租赁合同；2购买种子' ,
    `pay_id` VARCHAR(255)   COMMENT '支付单ID' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '账单支付单关联表';

DROP TABLE IF EXISTS t_pay;
CREATE TABLE t_pay(
    `id` VARCHAR(255) NOT NULL  COMMENT 'id' ,
    `pay_id` VARCHAR(255)   COMMENT '支付记录ID' ,
    `third_trade_no` VARCHAR(255)   COMMENT '第三方订单号' ,
    `subject` VARCHAR(255)   COMMENT '支付记录标题' ,
    `body` VARCHAR(255)   COMMENT '支付商品描述信息' ,
    `pay_source` INT   COMMENT '支付来源;10.小程序，20，APP，30，H5' ,
    `pay_user_id` VARCHAR(255)   COMMENT '支付用户ID' ,
    `pay_channel` INT   COMMENT '支付渠道;10.微信；20支付宝；30现金' ,
    `pay_amount` DECIMAL(24,6)   COMMENT '支付金额' ,
    `update_amount` DECIMAL(24,6)   COMMENT '后台修改的金额' ,
    `pay_status` INT   COMMENT '支付状态;00,待支付；10，支付中；20，支付成功；30-支付失败, 40-已撤销, 50-已退款, 60-订单关闭' ,
    `pay_time` DATETIME   COMMENT '支付时间' ,
    `expired_time` DATETIME   COMMENT '支付单过期时间' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '支付记录表';

DROP TABLE IF EXISTS t_refund;
CREATE TABLE t_refund(
    `id` VARCHAR(255) NOT NULL  COMMENT 'id' ,
    `refund_id` VARCHAR(255)   COMMENT '退款记录ID' ,
    `pay_id` VARCHAR(255)   COMMENT '支付记录ID' ,
    `third_refund_no` VARCHAR(255)   COMMENT '第三方退款交易号' ,
    `third_trade_no` VARCHAR(255)   COMMENT '第三方支付交易号' ,
    `pay_amount` DECIMAL(24,6)   COMMENT '支付金额' ,
    `update_amount` DECIMAL(24,6)   COMMENT '后台修改金额' ,
    `refund_amount` DECIMAL(24,6)   COMMENT '退款金额' ,
    `refund_reason` VARCHAR(900)   COMMENT '退款原因' ,
    `refund_status` INT   COMMENT '退款状态;00-退款记录生成；10-退款中；20退款成功；30-退款失败；40-退款关闭' ,
    `refund_time` DATETIME   COMMENT '退款时间' ,
    `expire_time` DATETIME   COMMENT '退款过期时间' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '退款记录表';

DROP TABLE IF EXISTS t_farmland_lessee_order;
CREATE TABLE t_farmland_lessee_order(
    `id` VARCHAR(255) NOT NULL  COMMENT 'id' ,
    `farmland_lessee_id` VARCHAR(255)   COMMENT '农田租赁信息ID' ,
    `order_id` VARCHAR(255)   COMMENT '账单ID' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` VARCHAR(1) NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '租赁信息和订单关联表';

DROP TABLE IF EXISTS t_seed_order;
CREATE TABLE t_seed_order(
    `id` VARCHAR(255) NOT NULL  COMMENT 'id' ,
    `seed_id` VARCHAR(255)   COMMENT '种子ID' ,
    `order_id` VARCHAR(255)   COMMENT '账单ID' ,
    `order_amount` DECIMAL(24,6)   COMMENT '订单金额' ,
    `pay_amount` DECIMAL(24,6)   COMMENT '实付金额' ,
    `buy_user_id` VARCHAR(255)   COMMENT '购买的用户id' ,
    `order_status` INT   COMMENT '订单状态' ,
    `buy_count` INT   COMMENT '购买数量' ,
    `unit_price` DECIMAL(24,6)   COMMENT '购买单价' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '种子购买订单关联表';

DROP TABLE IF EXISTS t_express;
CREATE TABLE t_express(
    `id` VARCHAR(255) NOT NULL  COMMENT 'id' ,
    `express_id` VARCHAR(255)   COMMENT '快递信息ID' ,
    `order_id` VARCHAR(255)   COMMENT '账单ID' ,
    `express_name` VARCHAR(255)   COMMENT '物流公司名称' ,
    `express_code` VARCHAR(255)   COMMENT '物流公司编码' ,
    `express_no` VARCHAR(255)   COMMENT '快递编号' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '快递信息表';

DROP TABLE IF EXISTS t_field_management_record;
CREATE TABLE t_field_management_record(
    `id` VARCHAR(255) NOT NULL  COMMENT 'id' ,
    `field_management_record_id` VARCHAR(255)   COMMENT '记录ID' ,
    `growth_id` VARCHAR(255)   COMMENT '生长周期ID' ,
    `operation_type` INT   COMMENT '操作类型;00 播种前准备，10，播种 20浇水；30，施肥，40除草，50 除虫，60防病，70，收获' ,
    `operation_time` DATETIME   COMMENT '操作时间' ,
    `operation_status` INT   COMMENT '操作状态' ,
    `operation_result` INT   COMMENT '操作结果' ,
    `operation_mode` INT   COMMENT '操作方式;10，线上代理；20线下预约' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '田间管理记录表';

DROP TABLE IF EXISTS t_field_management_record_files;
CREATE TABLE t_field_management_record_files(
    `id` VARCHAR(255) NOT NULL  COMMENT 'id' ,
    `field_management_record_id` VARCHAR(255)   COMMENT '记录ID' ,
    `file_id` VARCHAR(255)   COMMENT '附件id' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '田间管理记录附件表';

DROP TABLE IF EXISTS t_storage_files;
CREATE TABLE t_storage_files(
    `id` VARCHAR(255) NOT NULL  COMMENT 'id' ,
    `file_id` VARCHAR(255)   COMMENT '文件ID' ,
    `file_name` VARCHAR(255)   COMMENT '文件名' ,
    `original_name` VARCHAR(255)   COMMENT '原始文件名' ,
    `file_suffix` VARCHAR(255)   COMMENT '文件后缀' ,
    `url` VARCHAR(255)   COMMENT 'url 地址' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '存储资源文件';

DROP TABLE IF EXISTS t_booking_record;
CREATE TABLE t_booking_record(
    `id` VARCHAR(255) NOT NULL  COMMENT 'id' ,
    `booking_record_id` VARCHAR(255)   COMMENT '预约记录ID' ,
    `field_management_record_id` VARCHAR(255)   COMMENT '田间管理记录ID' ,
    `booking_time` DATETIME   COMMENT '预约时间' ,
    `booking_status` INT   COMMENT '预约状态;00，生成；10-预约成功；20-预约失败' ,
    `booking_remarks` VARCHAR(255)   COMMENT '描述' ,
    `traffic_mode` INT   COMMENT '交通方式;10-自驾；20-公共交通' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '线下活动预约记录';

DROP TABLE IF EXISTS t_comment;
CREATE TABLE t_comment(
    `id` VARCHAR(255) NOT NULL  COMMENT 'id' ,
    `field_management_record_id` VARCHAR(255)   COMMENT '田间管理记录表ID' ,
    `comment_id` VARCHAR(255)   COMMENT '评价记录ID' ,
    `score` INT   COMMENT '评分;10,1星；20,2星；30，3星；40,4星；50,5星' ,
    `content` TEXT   COMMENT '评论内容' ,
    `is_picture` INT   COMMENT '是否图片;1，是，0，否' ,
    `picture_ids` VARCHAR(255)   COMMENT '图片ID集合' ,
    `sort` INT   COMMENT '排序序号' ,
    `is_show` INT   COMMENT '是否显示;1，显示，0隐藏' ,
    `create_by_name` VARCHAR(255)   COMMENT '创建人姓名' ,
    `create_by` VARCHAR(255)   COMMENT '创建人' ,
    `create_time` DATETIME   COMMENT '创建时间' ,
    `update_time` DATETIME   COMMENT '更新时间' ,
    `is_deleted` INT NOT NULL  COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '田间管理评价表';

