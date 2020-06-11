package com.bella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 @Date: 2020/6/10-15:17
 @Author Genie
 @Description: SpringBoot如何加载 IOC 容器 ? 如何加载我们定义的SpringMVC ? 或者其他Bean ?
 */
@SpringBootApplication(scanBasePackages = {"com.bella"})
public class BellaApplication_2 {

	/**
	 * ####################################################################################
	 * 		SpringBoot如何加载 IOC 容器 ? 如何加载我们定义的SpringMVC ? 或者其他Bean ?
	 * ####################################################################################
	 */

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(BellaApplication_2.class);
		ConfigurableApplicationContext applicationContext = application.run();

		/**
		 * @SpringBootApplication 注解内引入的注解
		 *
		 *  -> @SpringBootConfiguration -> @Configuration
		 *  -> @EnableAutoConfiguration -> @Import(AutoConfigurationImportSelector.class)
		 *  -> @ComponentScan
		 */

		/**
		 * @EnableAutoConfiguration -> AutoConfigurationImportSelector -> process() -> getCandidateConfigurations()
		 * 启动前将多个Bean注册到IOC容器中 (初始化)
		 *
		 * List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);
		 * 其实就是获取 spring-boot-autoconfigure:2.1.8.RELEASE jar中 META-INF/spring.factories 中定义的
		 * org.springframework.boot.autoconfigure.EnableAutoConfiguration=117个Class (SpringBoot当前版本初始化需要加载的Bean)
		 *
		 * 需要更详细的了解 SpringBoot 装载 IOC 流程
		 * 注解怎么起作用的 ?
		 */

		System.out.println( "当前 Spring IOC 容器 Bean 的数量 : " + applicationContext.getBeanDefinitionCount() );

		System.out.println("-------------------------------------------------");
		for ( String bean : applicationContext.getBeanDefinitionNames() ) {
			System.out.println( "Bean : \t " + bean );
		}
		System.out.println("-------------------------------------------------");


	}

}
