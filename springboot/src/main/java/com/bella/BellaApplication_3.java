package com.bella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 @Date: 2020/6/10-15:42
 @Author Genie
 @Description: SprinBoot如何启动Tomcat ?
 */
@SpringBootApplication
public class BellaApplication_3 {


	/**
	 * ####################################################################################
	 * 								SprinBoot如何启动Tomcat ?
	 * ####################################################################################
	 */

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(BellaApplication_3.class);
		springApplication.run();

		/**
		 * SpringBoot中内嵌的三种WEB服务器
		 *
		 * 1. Tomcat (默认) -> Tomcat 是一个开源的轻量级Web应用服务器
		 * 2. Jetty -> Jetty 是一个开源的servlet容器，它为基于Java的web容器
		 * 3. Undertow -> Undertow 是红帽公司开发的一款基于 NIO 的高性能 Web 嵌入式服务器
		 */


		/**
		 *
		 * @EnableAutoConfiguration 加载我们第三方配置的启动
		 *
		 * org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration,\
		 * org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration,\
		 *
		 */



		/**
		 * 获取 DispatcherServletAutoConfiguration
		 *
		 * DispatcherServletAutoConfiguration -> Bean : DispatcherServlet
		 *
		 *
		 * 获取Tomcat
		 *
		 * ServletWebServerFactoryAutoConfiguration -> Bean : ServletWebServerFactoryCustomizer -> Bean : TomcatServletWebServerFactoryCustomizer
		 * @Import({ ServletWebServerFactoryAutoConfiguration.BeanPostProcessorsRegistrar.class,
		 * 		ServletWebServerFactoryConfiguration.EmbeddedTomcat.class,
		 * 		ServletWebServerFactoryConfiguration.EmbeddedJetty.class,
		 * 		ServletWebServerFactoryConfiguration.EmbeddedUndertow.class })
		 *
		 * ServletWebServerFactoryConfiguration -> Bean : TomcatServletWebServerFactory - > getWebServer();
		 *
		 *
		 * 猜测执行流程 : (需要确认)
		 *
		 * @EnableAutoConfiguration 加载第三方 Bean
		 * 当加载到 org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration 的时候就会去执行 DispatcherServletAutoConfiguration
		 * 当加载到 org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration 的时候就会去创建 Tomcat
		 */

	}

}
