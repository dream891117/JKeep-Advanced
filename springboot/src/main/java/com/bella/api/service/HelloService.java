package com.bella.api.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 @Date: 2020/6/9-14:27
 @Author Genie
 @Description:
 */
@RestController
@EnableAutoConfiguration
@ComponentScan("com.genie.api.service")
public class HelloService {

	/**
	 * @RestController 表示当前类所有定义的方法 统一都返回 json
	 *
	 * 原理 : 通过@Controller + @ResponseBody组合 (查看 @RestController 源码)
	 */
	@GetMapping("/hello")
	public String hello(){
		return "你好,今天天气不错!";
	}

	/**
	 * 启动
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * @EnableAutoConfiguration 作用实际上就是  启动SpringMVC,启动Tomcat (查看依赖里面 tomcat.jar)
		 * @EnableAutoConfiguration 的扫包范围只有当前类 (如果只加了这个注解,启动后只能访问当前类的 Action)
		 *
		 *
		 * @ComponentScan("com.genie.api.service") -> 添加扫包注解,这样 IndexClass 才会被注入到SpringMVC里面,启动的时候才会被加载
		 * 如果不加扫包注解 , 只有当前 Class 会被加载
		 *
		 * @SpringBootApplication 看他的源码 , 里面引用了很多其他注解 (N多注解的集合)
		 */
		SpringApplication.run(HelloService.class,args);
	}
}
