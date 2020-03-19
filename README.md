# springcloud
a demo project of spring cloud

# branch doc
## v5.0_springcloud_bus

架构：配置中心客户端(cloud-config-client3355) 配置中心服务端(cloud-config-center3344) Eureka注册中心集群(cloud-eureka-server7001,cloud-eureka-server7002)

使用的配置仓库：https://github.com/rumwei/springcloud-config

集成消息总线，实现配置的全局通知刷新

注：本分支非可用功能，因为需要预装RabbitMQ，同时本分支相比master新增内容没有推送到master分支中
配置中心服务器(cloud-config-center3344)
配置中心客户端(cloud-config-client3355,cloud-config-client3366)
Eureka注册中心集群(cloud-eureka-server7001,cloud-eureka-server7002)
集成SpringCloud Bus实现当配置更新时，通过发送刷新消息给3344，3344能够一次性全部通知所有配置中心客户端(3355和3366)
也能够只通知其中的部分客户端(如只通知3355刷新，忽略3366的刷新)
