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
