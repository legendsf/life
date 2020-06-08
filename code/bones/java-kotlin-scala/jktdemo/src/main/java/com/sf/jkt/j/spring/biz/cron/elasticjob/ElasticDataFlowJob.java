package com.sf.jkt.j.spring.biz.cron.elasticjob;

/**
 *
 * https://blog.csdn.net/LOVELONG8808/article/details/80351687
 *  fetchData:
 *         查询 100 条数据
 *  processData:
 *         如果数据的 ID应该被当前结点执行，则执行，否则跳过
 *         if(id % machineNumbers)== machineIndex{
 *             dosth
 *         }
 */
public class ElasticDataFlowJob {
}
