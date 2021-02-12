/**
 * domain - 领域层，聚集所有业务逻辑
 * 两种模式：
 *    模式 1、不同的聚合根一个目录
 *    aggragate00
 *      event
 *      entity
 *      service
 *      repository
 *    aggragate01
 *      event
 *      entity
 *      service
 *      repository
 *   模式2:
 *      domain
 *        event
 *        entity
 *        service
 *        repository
 *
 *   优缺点：
 *      模式 1，根据聚合来分，后续好拆分组合，缺点建了太多的包啰嗦
 *      模式 2，平铺型
 *      决策标准：如果可以预见领域简单，那么采取模式 2；
 *      如果一个微服务内部多个领域，或者初期采用单体架构，后期可能重构，那么久用模式 1，方便后续的重构
 *   event 跟领域事件相关，如果不是事件驱动的那么，可以没有 event 包
 *   另外到底要不要 interface 和 impl
 *      决策：
 *          对外的 api 接口层肯定是要的
 *          领域内部就不要搞那么多 interface impl,很啰嗦，并且你也遇不到要重新更换一个 impl 接口这种需求
 *
 *
 */
package com.sf.ddd.domain;