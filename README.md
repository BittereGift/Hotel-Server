# Hotel-Server
Hotel-Server
# 某某酒店微服务系统

## 一、微服务

- `hotel-common` 共享的微服务，其中没有启动类，主要用于微服务中公共对象的声明
- `hotel-collect` 收藏微服务
- `hotel-eval` 评价微服务
- `hotel-hotel` 酒店微服务，这里是对酒店对象的操作，房间对象由 `hotel-room` 进行管理
- `hotel-manager` 管理员微服务
- `hotel-msg` 消息微服务，管理通知事务
- `hotel-order` 订单微服务
- `hotel-point` 积分微服务
- `hotel-room` 房间微服务，管理房间及其状态
- `hotel-user` 用户微服务
- `api-doc` 通过 `knife4j` 来快速生成接口文档



## 二、使用的框架和工具包

- `Spring Cloud Alibaba` 微服务框架
- `Spring Boot` `Spring` 主体框架
- `Mybatis` 用于和数据库之间进行数据沟通，简化 `JDBC` 开发
- `druid` 数据库连接池
- `lombok` 简化持久层对象开发，还用于日志记录
- `nacos` 注册中心，用于服务的注册、发现和心跳监测
- `Ribbon` 实现负载均衡
- `Feign` 快捷调用远程服务
- `Seata` 分布式事务管理
- `knife4j` 用于通过注解快速生成接口文档
- `hutool` 用于简化数据间的处理和转换
- `fastjson2` 用于将对象快速转换为 `json` 对象
