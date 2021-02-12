/**
 * event 是用来处理领域事件
 * 比如 ：ORDERCREATE-EVENT
 *       ORDERUPDATE-EVENT
 * 如果是 EVENT-SOURCING 或者 CQRS 采用事件驱动的架构可以用这种
 * 事件驱动（这种概念的实现方式有两种）
 *  1、callback
 *  2、消息
 *  3、observer
 *  4、guava eventBus https://zhuanlan.zhihu.com/p/164895790
 */
package com.sf.ddd.application.event;
