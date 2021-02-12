/**
 * infrastructrue - 基础设施层，持久化，领域层对外依赖的接口实现
 * > util：工具包，各种工具类
 *      api
 *      driver
 *      eventbus
 *      mq
 *      common
 *          stringUtils
 *          fileUtils
 *          etc ...
 * > config：配置信息
 *      db
 *      redis
 *      mq
 *
 *  注意 common 包也可能是外部一个单独的工程,本工程内部可能是一个 Util 工具类，组合了 stringUtil fileUtil
 *  Util包 主要放跟领域无关的
 *      平台、框架、消息、数据库、缓存、文件、网关、总线、第三方类库等通用的代码，可以供给所有层使
 *      util 层不依赖其它各层
 * <p>
 * DDD: infrastructure 基础实施层  最底层(但与所有层进行交互)
 * 向其他层提供 通用的 技术能力(比如工具类,第三方库类支持,常用基本配置,数据访问底层实现)
 * 基础实施层主要包含以下的内容:
 * 为应用层 传递消息(比如通知)
 * 为领域层 提供持久化机制(最底层的实现)
 * 为用户界面层 提供组件配置
 * 基础设施层还能够通过架构框架来支持四个层次间的交互模式。
 * 注意：持久化层不要放到基础设施层，而应该放到 domain 层 或者单独拉出来叫做 persistence 层
 * 放到 基础设施层会导致基础设施层极为庞大
 *
 *
 */
package com.sf.ddd.infrastructure;