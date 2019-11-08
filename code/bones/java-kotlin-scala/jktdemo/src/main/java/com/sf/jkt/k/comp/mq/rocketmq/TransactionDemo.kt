package com.sf.jkt.k.comp.mq.rocketmq

import org.apache.rocketmq.client.producer.LocalTransactionState
import org.apache.rocketmq.client.producer.TransactionListener
import org.apache.rocketmq.common.message.Message
import org.apache.rocketmq.common.message.MessageExt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.DefaultTransactionDefinition
import org.springframework.transaction.support.TransactionTemplate

@Autowired
lateinit var tm: PlatformTransactionManager


class TransactionListenerImpl() : TransactionListener {
    override fun executeLocalTransaction(msg: Message, arg: Any): LocalTransactionState {
        var def = DefaultTransactionDefinition()
        var status = tm.getTransaction(def)
        try {
            var script =
                    """
        select * from tbl_account where accountNo="123" for update
        // got new balance
        update tbl_account set balance=newBalance where account="123"
        insert into tbl_trans(id,orderNo,transNo,transType,account,deltaMoney,incomeType,operator)values ...
        """.trimIndent()

            tm.commit(status)
            //如果成功执行本地事务，则返回提交消息
            return LocalTransactionState.COMMIT_MESSAGE
        } catch (e: Exception) {
            //log exception
            try {
                tm.rollback(status)//
                return LocalTransactionState.ROLLBACK_MESSAGE
            } catch (e: Exception) {
                //回滚失败，回滚时发现原事务不存在了，有可能被mysql提交了，也有可能是超时关闭了
                return LocalTransactionState.UNKNOW
            }

        }
    }

    override fun checkLocalTransaction(msg: MessageExt): LocalTransactionState {
        var result = 0;
        """
            TBL_TRANS_MODEL tmodle= msg.getBody().parse to object
            msgTransNo=tmodel.getTransNo
          result=   select * from tbl_trans where transNo=msgTransNo
        """.trimIndent()
        if (result == 0) {
            return LocalTransactionState.ROLLBACK_MESSAGE
        } else if (result == 1) {
            return LocalTransactionState.COMMIT_MESSAGE
        } else {
            return LocalTransactionState.UNKNOW
        }
    }
}