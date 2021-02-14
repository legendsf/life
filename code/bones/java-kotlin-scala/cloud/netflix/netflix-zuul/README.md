#1
### 
    双层 zuul: 
    https://blog.csdn.net/sanyaoxu_2/article/details/84037762?utm_medium=distribute.pc_relevant_download.none-task-blog-baidujs-1.nonecase&depth_1-utm_source=distribute.pc_relevant_download.none-task-blog-baidujs-1.nonecase
    zuul集群 可以配合 eureka 集群使用
    https://blog.csdn.net/hyl999/article/details/88884426
    zuul 单独使用：https://developer.aliyun.com/ask/136321?spm=a2c6h.13159736
###
    zuul配置 url
    https://blog.csdn.net/chengqiuming/article/details/80790166

###
    路由规则
    https://zhuanlan.zhihu.com/p/81720177

### 动态路由配置
    https://blog.csdn.net/u013815546/article/details/68944039
    https://lovnx.blog.csdn.net/article/details/84595350?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control
    https://www.iteye.com/blog/baobeituping-2427390
### zuul 负载均衡用的是 ribbon
        推荐：使用 eureka来做注册中心
        方式 1、和 eureka 配置读取服务配置
        方式 2、不和 eureka配合，直接写 listOfServers: http://localhost:8001,http://localhost:8002
    https://www.cnblogs.com/jizhong/p/11431554.html
### 使用 nacos 来做负载均衡
    https://blog.csdn.net/weixin_35562751/article/details/113690069

###
    zuul 限流
    https://segmentfault.com/a/1190000012252677
###
    zuul 整合 hystrix ribbon
    https://blog.csdn.net/oqqaKun1/article/details/85718993