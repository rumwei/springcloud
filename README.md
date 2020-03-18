# springcloud
a demo project of spring cloud

# branch doc
## v1.0_eureka
架构：
单台服务调用方(cloud-consumer-order80)
服务提供方集群(cloud-provider-payment8001,cloud-provider-payment8002)
Eureka注册中心集群(cloud-eureka-server7001,cloud-eureka-server7002)
说明：未使用rpc框架，通过Eureka的服务名(有多台主机ip)，结合ribbon的@LoadBalanced注解获取到主机名，然后通过RestTemplate来完成consumer调用provider服务的目的
## v2.0_hystrix_eureka
架构：单台服务调用方(cloud-consumer-feign-hystrix-order80) 单台服务提供方(cloud-provider-hystrix-payment8001) Eureka注册中心集群(cloud-eureka-server7001,cloud-eureka-server7002) 说明：未使用rpc框架，通过Eureka的服务名(有多台主机ip)，结合ribbon的@LoadBalanced注解获取到主机名，然后通过RestTemplate来完成consumer调用provider服务的目的。
同时利用Hystrix实现服务降级已经服务熔断功能，同时集成了hystrix-dashboard(cloud-consumer-hystrix-dashboard9001)可视化监控界面
