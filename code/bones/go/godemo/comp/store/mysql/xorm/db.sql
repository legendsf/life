
CREATE table if not exists  `person`(
    `id` int(11) not null auto_increment,
    `name` varchar(50) default '' comment '姓名',
    PRIMARY  key (`id`)
)ENGINE=InnodB auto_increment=1 default charset=utf8mb4 comment='人员表';

CREATE table if not exists  `doctor_tb`(
    `id` int(11) not null auto_increment,
    `name` varchar(50) default '' comment '姓名',
    `age` int(11) default '0' comment '年龄',
    `sex` int(11) default '0' comment '性别',
    `addTime` datetime default null,
    PRIMARY  key (`id`)
)ENGINE=InnodB auto_increment=1 default charset=utf8mb4 comment='医生表';

CREATE table if not exists  `user_tb`(
    `id` int(11) not null auto_increment,
    `name` varchar(50) default '' comment '姓名',
    `age` int(11) default '0' comment '年龄',
    `sex` int(11) default '0' comment '性别',
    `addTime` datetime default null,
    PRIMARY  key (`id`)
)ENGINE=InnodB auto_increment=1 default charset=utf8mb4 comment='用户表';
