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
架构：单台服务调用方(cloud-consumer-feign-hystrix-order80) 

单台服务提供方(cloud-provider-hystrix-payment8001) 

Eureka注册中心集群(cloud-eureka-server7001,cloud-eureka-server7002) 

说明：未使用rpc框架，通过Eureka的服务名(有多台主机ip)，结合ribbon的@LoadBalanced注解获取到主机名，然后通过RestTemplate来完成consumer调用provider服务的目的。

同时利用Hystrix实现服务降级已经服务熔断功能，同时集成了hystrix-dashboard(cloud-consumer-hystrix-dashboard9001)可视化监控界面
## v3.0_gateway
架构：

网关服务(cloud-gateway-gateway9527) 

网关管理的服务方集群(cloud-provider-payment8001,cloud-provider-payment8002) 

Eureka注册中心集群(cloud-eureka-server7001,cloud-eureka-server7002)
## v4.0_springcloud-config
架构：配置中心客户端(cloud-config-client3355) 

配置中心服务端(cloud-config-center3344) 

Eureka注册中心集群(cloud-eureka-server7001,cloud-eureka-server7002)

使用的配置仓库：https://github.com/rumwei/springcloud-config
## v5.0_springcloud-bus
架构：配置中心客户端(cloud-config-client3355) 

配置中心服务端(cloud-config-center3344) 

Eureka注册中心集群(cloud-eureka-server7001,cloud-eureka-server7002)

使用的配置仓库：https://github.com/rumwei/springcloud-config

集成消息总线，实现配置的全局通知刷新

注：本分支非可用功能，因为需要预装RabbitMQ，同时本分支相比master新增内容没有推送到master分支中

配置中心服务器(cloud-config-center3344) 配置中心客户端(cloud-config-client3355,cloud-config-client3366) Eureka注册中心集群(cloud-eureka-server7001,cloud-eureka-server7002) 集成SpringCloud Bus实现当配置更新时，通过发送刷新消息给3344，3344能够一次性全部通知所有配置中心客户端(3355和3366) 也能够只通知其中的部分客户端(如只通知3355刷新，忽略3366的刷新)

## v6.0_SpringCloud Sleuth
以服务调用方cloud-consumer-feign-order80，经过Gateway网关cloud-gateway-gateway9527来调用服务提供方集群(cloud-provider-payment8001，cloud-provider-payment8002)，服务注册中心采用Eureka集群(cloud-eureka-server7001，cloud-eureka-server7002)为例来说明链路跟踪功能。

## v7.0_cloud_alibaba_nacos_service_register
引入SpringCloud Alibaba的nacos作为服务注册与发现中心，取代Eureka的作用

服务注册中心：已下载并启动Nacos，主页http://localhost:8848/nacos

服务提供方集群：cloudalibaba-provider-payment9001,cloudalibaba-provider-payment9002

服务调用方：cloudalibaba-consumer-nacos-order83

启动nacos，服务提供方集群以及服务调用方之后，即可通过调用方来访问服务方提供的服务

同时在Nacos页面上配置不同实例不同的权重，则可以看到负载均衡的效果

## v8.0_cloud_alibaba_nacos_config
引入SpringCloud Alibaba的nacos作为配置中心，取代SpringCloud config组件功能

服务注册中心：已下载并启动Nacos，主页http://localhost:8848/nacos

配置中心客户端：cloudalibaba-config-nacos-client3377，可以获取nacos上的配置







