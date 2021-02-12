/**
 * Assembler：实现DTO与领域对象之间的相互转换和数据交换。理论上Assembler总是与DTO一同被使用。
 * Dto：数据传输的载体，内部不存在任何业务逻辑，通过DTO把内部的领域对象与外界隔离。
 * Facade：提供较粗粒度的调用接口，将用户请求委派给一个或多个应用服务进行处理。
 * VO: 如果是 BFF 层，或者直接对接前端，则可能还有 VO，否则如果纯粹后端服务那么只有 DTO
 * 注：ASSEMLBLER 就是 CONVERTOR, 转换 DTO 和 ENTITY
 * 越简洁越好：
 *    从 APPLICATION 层到 DOMAIN层 都只操作 ENTITY（BO 和 PO 统一起来，不要搞很多O,太啰嗦了）
 *    甚至如果业务非常简单，那么 DTO 也可以直接用 ENTITY（不推荐）
 */
package com.sf.ddd.interfaces;