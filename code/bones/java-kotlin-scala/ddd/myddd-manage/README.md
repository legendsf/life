#后台的管理工程
##管理工程是个聚合工程，可能管理多个领域的服务
###管理工程不自己连接领域的数据库，如果需要操作数据库，那么走领域的接口
###影响性能的操作
####1、批量导入导出数据
        1、导出数据读写分离
            第一种还是连接到领域服务，调用导出的接口
            第二种如果领域服务中查询服务单独分离出来了，那么可以采取直接连接查询服务的领域服务
            第三种导出服务在大数据那边，不在领域服务这边，那就可以调用大数据的数据服务
        2、线上服务批量导入数据或者修改配置
            1、一定要在业务非高峰运行
            2、有回滚策略
        注意：后台管理服务最好不要直接连数据库，因为连接领域的数据库，用的要么是从库，要么是同一个主库
             性能的压力并没有减少，并且管理服务性能压力不在 TPS。而在一些特殊的批处理
             那么主领域服务，可以对该批处理进行一个资源隔离
        另外：如果是交易类系统或者非常核心的系统，并且并发量巨高，建议
                查询和 命令 是两个系统分开，这样命令系统不会频繁更新，不频繁发版就减少出错的概率
                如果并发量不高，那么就放到主服务里面，顶多做个读写分离和分库分表
