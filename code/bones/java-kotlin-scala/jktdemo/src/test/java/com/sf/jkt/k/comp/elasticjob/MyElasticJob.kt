//package com.sf.jkt.k.comp.elasticjob
//
//import com.dangdang.ddframe.job.api.ShardingContext
//import com.dangdang.ddframe.job.api.simple.SimpleJob
//
//class MyElasticJob : SimpleJob {
//    override fun execute(contxt: ShardingContext) {
//        val item = contxt.shardingItem
//        when {
//            item == 0 -> {
//                println("MyJob-0")
//            }
//            item==1->{
//               println("MyJob-1")
//            }
//            item==2->{
//                println("MyJob-2")
//            }
//            else -> println("MyJob-default")
//        }
//    }
//}