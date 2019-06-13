//package com.sf.jkt.k.comp.elasticjob
//
//import com.dangdang.ddframe.job.config.JobCoreConfiguration
//import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration
//import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration
//import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration
//import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter
//import org.springframework.boot.CommandLineRunner
//import org.springframework.context.annotation.Bean
//import com.dangdang.ddframe.job.lite.api.JobScheduler
//import org.springframework.context.annotation.Configuration
//
//@Configuration
//class  MyElasticJobConfig{
//    fun createRegistryCenter(): ZookeeperRegistryCenter {
//        val regCenter = ZookeeperRegistryCenter(ZookeeperConfiguration("127.0.0.1:2181", "elastic-job-demo"))
//        regCenter.init()
//        return regCenter
//    }
//
//    fun createJobConfiguration(): LiteJobConfiguration {
//        val simpleCoreConfig = JobCoreConfiguration.newBuilder("doSimpleJob", "0/15 * * * * ?", 10).build()
//        val simpleJobConfig = SimpleJobConfiguration(simpleCoreConfig, MyElasticJob::class.java.canonicalName)
//        val simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build()
//        return simpleJobRootConfig
//    }
//
//    @Bean
//    fun commandLineRunner(): CommandLineRunner {
//        return object : CommandLineRunner {
//            override fun run(vararg args: String?) {
//                println("MyCommandLineRunner******************************")
//                JobScheduler(createRegistryCenter(), createJobConfiguration()).init()
//            }
//        }
//    }
//}