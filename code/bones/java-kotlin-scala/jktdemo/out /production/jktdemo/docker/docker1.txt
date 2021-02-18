#install redis
https://www.jianshu.com/p/fef5e1a7bd0e
docker run --name redis -d -p 6379:6379 redis

#install zookeeper
https://my.oschina.net/u/3375733/blog/1591974
docker run --name zookeeper -d -p 2181:2181 -p 3888:3888 zookeeper

#install rabbitmq
https://www.cnblogs.com/yufeng218/p/9452621.html
docker run -d --name rabbitmq --publish 5671:5671  --publish 5672:5672 --publish 4369:4369 --publish 25672:25672 --publish 15671:15671 --publish 15672:15672 -e RABBITMQ_DEFAULT_VHOST=my_vhost  -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest rabbitmq:3.7.7-management

#install kafka
https://blog.csdn.net/lblblblblzdx/article/details/80548294
docker run  -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=127.0.0.1:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -t wurstmeister/kafka





