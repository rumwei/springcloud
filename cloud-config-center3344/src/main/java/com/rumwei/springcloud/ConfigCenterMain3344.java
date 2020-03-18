package com.rumwei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 1.首先在github上新建名为springcloud-config的新仓库，获取仓库地址为git@github.com:rumwei/springcloud-config.git
 * 2.将远程仓库clone到本地，假设在/Users/guwei/work/java/springcloud-config目录下
 * 3.在目录下新建以下三个文件，并推送到远程仓库
 * config-dev.yml
 * config-test.yml
 * config-prod.yml
 * 文件内容：
 * server:
 *   port: 3344
 * 4.新建服务端应用(即本应用cloud-config-center3344)
 * 5.启动应用，访问http://localhost:3344/master/config-dev.yml即可拿到配置内容.（延时较高，本地测试6s左右）
 * 读取规则：label:分支，application:服务名，profiles:环境
 * /{label}/{application}-{profiles}.yml  即分支+配置文件名
 *          http://localhost:3344/master/config-dev.yml(master分支)
 *          http://localhost:3344/dev/config-dev.yml(dev分支)
 * /{application}-{profiles}.yml 即默认分支缺省不写，默认本应用中的application.yml中label配置的分支
 * /{application}/{profiles}/[{label}] 即分支放后面
 *          http://localhost:3344/config/dev/master(master分支) 读取master分支的config-dev.yml文件
 *          http://localhost:3344/config/test/dev(dev分支) 读取master分支的config-test.yml文件
 * */

@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class,args);
    }
}
