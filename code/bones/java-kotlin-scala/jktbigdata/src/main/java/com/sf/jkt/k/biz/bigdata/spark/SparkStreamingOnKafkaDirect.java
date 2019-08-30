//package com.sf.jkt.k.biz.bigdata.spark;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import kafka.serializer.StringDecoder;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.function.FlatMapFunction;
//import org.apache.spark.api.java.function.Function2;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.streaming.Durations;
//import org.apache.spark.streaming.api.java.JavaDStream;
//import org.apache.spark.streaming.api.java.JavaPairDStream;
//import org.apache.spark.streaming.api.java.JavaPairInputDStream;
//import org.apache.spark.streaming.api.java.JavaStreamingContext;
//
//import org.apache.spark.streaming.kafka.KafkaUtils;
//import scala.Tuple2;
//
///**
// *  spark-streaming-kafka-0-8_2.11
// * */
//public class SparkStreamingOnKafkaDirect {
//
//    public static void main(String[] args) {
///*      第一步：配置SparkConf：
//        1，至少两条线程因为Spark Streaming应用程序在运行的时候至少有一条线程用于
//        不断地循环接受程序，并且至少有一条线程用于处理接受的数据（否则的话有线程用于处理数据，随着时间的推移内存和磁盘都会
//        不堪重负）
//        2，对于集群而言，每个Executor一般肯定不止一个线程，那对于处理SparkStreaming
//        应用程序而言，每个Executor一般分配多少Core比较合适？根据我们过去的经验，5个左右的Core是最佳的
//        （一个段子分配为奇数个Core表现最佳，例如3个，5个，7个Core等）
//*/
//        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("SparStreamingOnKafkaReceiver");
///*      SparkConf conf = new SparkConf().setMaster("spark：//Master:7077").setAppName("SparStreamingOnKafkaReceiver");
//        第二步：创建SparkStreamingContext,
//        1，这个是SparkStreaming应用春香所有功能的起始点和程序调度的核心
//        SparkStreamingContext的构建可以基于SparkConf参数也可以基于持久化的SparkStreamingContext的内容
//        来恢复过来（典型的场景是Driver崩溃后重新启动，由于SparkStreaming具有连续7*24
//        小时不间断运行的特征，所以需要Driver重新启动后继续上一次的状态，此时的状态恢复需要基于曾经的Checkpoint））
//        2，在一个Sparkstreaming 应用程序中可以创建若干个SparkStreaming对象，使用下一个SparkStreaming
//        之前需要把前面正在运行的SparkStreamingContext对象关闭掉，由此，我们获取一个重大的启发
//        我们获得一个重大的启发SparkStreaming也只是SparkCore上的一个应用程序而已，只不过SparkStreaming框架想运行的话需要
//        spark工程师写业务逻辑
//*/
//        @SuppressWarnings("resource")
//        JavaStreamingContext jsc = new JavaStreamingContext(conf,Durations.seconds(10));
//
///*      第三步：创建SparkStreaming输入数据来源input Stream
//        1，数据输入来源可以基于File,HDFS,Flume,Kafka-socket等
//        2,在这里我们指定数据来源于网络Socket端口，SparkStreaming连接上该端口并在运行时候一直监听
//        该端口的数据（当然该端口服务首先必须存在，并且在后续会根据业务需要不断地数据产生当然对于SparkStreaming
//        应用程序的而言，有无数据其处理流程都是一样的）;
//        3,如果经常在每个5秒钟没有数据的话不断地启动空的Job其实会造成调度资源的浪费，因为并没有数据发生计算
//        所以实际的企业级生成环境的代码在具体提交Job前会判断是否有数据，如果没有的话就不再提交数据
//    在本案例中具体参数含义：
//        第一个参数是StreamingContext实例，
//        第二个参数是zookeeper集群信息（接受Kafka数据的时候会从zookeeper中获取Offset等元数据信息）
//        第三个参数是Consumer Group
//        第四个参数是消费的Topic以及并发读取Topic中Partition的线程数
//*/
//        Map<String,String> kafkaParameters = new HashMap<String,String>();
//        kafkaParameters.put("meteadata.broker.list",
//                "Master:9092;Worker1:9092,Worker2:9092");
//
//        Set<String> topics =new HashSet<String>();
//         topics.add("SparkStreamingDirected");
//
//        JavaPairInputDStream<String,String> lines = KafkaUtils.createDirectStream(jsc,
//                String.class, String.class,
//                StringDecoder.class, StringDecoder.class,
//                kafkaParameters,
//                topics);
//    /*
//     * 第四步：接下来就像对于RDD编程一样，基于DStream进行编程！！！原因是Dstream是RDD产生的模板（或者说类
//     * ），在SparkStreaming发生计算前，其实质是把每个Batch的Dstream的操作翻译成RDD的操作
//     * 对初始的DTStream进行Transformation级别处理
//     * */
//        JavaDStream<String> words = lines.flatMap(new FlatMapFunction<Tuple2<String, String>,String>(){ //如果是Scala，由于SAM装换，可以写成val words = lines.flatMap{line => line.split("　＂)}
//
//            @Override
//            public Iterable<String> call(Tuple2<String,String> tuple) throws Exception {
//
//                return Arrays.asList(tuple._2.split(" "));//将其变成Iterable的子类
//            }
//        });
////      第四步：对初始DStream进行Transformation级别操作
//        //在单词拆分的基础上对每个单词进行实例计数为1，也就是word => (word ,1 )
//        JavaPairDStream<String,Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
//
//            @Override
//            public Tuple2<String, Integer> call(String word) throws Exception {
//                return new Tuple2<String, Integer>(word,1);
//            }
//
//        });
//        //对每个单词事例技术为1的基础上对每个单词在文件中出现的总次数
//
//         JavaPairDStream<String,Integer> wordsCount = pairs.reduceByKey(new Function2<Integer,Integer,Integer>(){
//
//            /**
//             *
//             */
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public Integer call(Integer v1, Integer v2) throws Exception {
//                // TODO Auto-generated method stub
//                return v1 + v2;
//            }
//        });
//        /*
//         * 此处的print并不会直接出发Job的支持，因为现在一切都是在SparkStreaming的框架控制之下的
//         * 对于spark而言具体是否触发真正的JOb运行是基于设置的Duration时间间隔的
//         * 诸位一定要注意的是Spark Streaming应用程序要想执行具体的Job，对DStream就必须有output Stream操作
//         * output Stream有很多类型的函数触发，类print，savaAsTextFile,scaAsHadoopFiles等
//         * 其实最为重要的一个方法是foreachRDD,因为SparkStreaming处理的结果一般都会放在Redis,DB
//         * DashBoard等上面，foreach主要就是用来完成这些功能的，而且可以自定义具体的数据放在哪里！！！
//         * */
//         wordsCount.print();
//
////       SparkStreaming 执行引擎也就是Driver开始运行，Driver启动的时候位于一条新线程中的，当然
////       其内部有消息接受应用程序本身或者Executor中的消息
//         jsc.start();
//         jsc.close();
//    }
//
//}