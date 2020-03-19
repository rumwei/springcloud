# springcloud
a demo project of spring cloud

# branch doc
## v1.0_eureka
架构：
单台服务调用方(cloud-consumer-order80)
服务提供方集群(cloud-provider-payment8001,cloud-provider-payment8002)
Eureka注册中心集群(cloud-eureka-server7001,cloud-eureka-server7002)
说明：未使用rpc框架，通过Eureka的服务名(有多台主机ip)，结合ribbon的@LoadBalanced注解获取到主机名，然后通过RestTemplate来完成consumer调用provider服务的目的
## v5.0_springcloud_bus
配置中心服务器(cloud-config-center3344)
配置中心客户端(cloud-config-client3355,cloud-config-client3366)
Eureka注册中心集群(cloud-eureka-server7001,cloud-eureka-server7002)
集成SpringCloud Bus实现当配置更新时，通过发送刷新消息给3344，3344能够一次性全部通知所有配置中心客户端(3355和3366)
也能够只通知其中的部分客户端(如只通知3355刷新，忽略3366的刷新)
