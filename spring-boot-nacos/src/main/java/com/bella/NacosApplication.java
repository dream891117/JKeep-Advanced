package com.bella;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 @Date: 2020/6/23-23:49
 @Author Genie
 @Description:
 *
 * @NacosPropertySource(dataId = "spring-boot-nacos",autoRefreshed = true)
 *
 * autoRefreshed = false -> 当 nacos 更新spring-boot-nacos内容时不通知当前项目 , 当前项目所用到的所有配置只有重启项目时才能重新更新
 * autoRefreshed = true -> 当 nacos 更新spring-boot-nacos内容时通知当前项目 , 当前项目所用到的所有配置都可以自动更新
 *
 */
@NacosPropertySource(dataId = "spring-boot-nacos",autoRefreshed = true)
@SpringBootApplication
public class NacosApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosApplication.class,args);
	}

}
