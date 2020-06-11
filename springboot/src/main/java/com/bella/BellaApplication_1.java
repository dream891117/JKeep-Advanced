package com.bella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 @Date: 2020/6/9-16:40
 @Author Genie
 @Description: 启动类
 */
@SpringBootApplication(scanBasePackages="com.bella")
public class BellaApplication_1 {

	/**
	 * @SpringBootApplication 等同于 (点进去看看源码)
	 * 	-> @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan("当前类(BellaApplication)包路径") + 。。。
	 *
	 * 注意 :
	 * 	-> 如果 @SpringBootApplication 注解的类在最外层 , 那可以不手动设置扫包路径 (因为默认情况下 , 扫包路径就是当前 注解 Class 的包路径,不会注入其他包的Bean)
	 * 	-> 否则需要加入指定扫包路径(保险起见直接加上也可以) -> scanBasePackages={"",""} 或 scanBasePackages=""
	 */

	public static void main(String[] args) {

		/**
		 * SpringBoot容器启动成功之后 返回我们当前容器的上下文 , 通过上下文我们可以做任何事情
		 */
//		ConfigurableApplicationContext applicationContext = SpringApplication.run(BellaApplication.class);
//		HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
//		System.out.println( helloService );

		SpringApplication springApplication = new SpringApplication(BellaApplication_1.class);
//		springApplication.setBanner((environment, sourceClass, out) -> out.println("__________       .__  .__           _______________   _______________   \n" +
//				"\\______   \\ ____ |  | |  | _____    \\_____  \\   _  \\  \\_____  \\   _  \\  \n" +
//				" |    |  _// __ \\|  | |  | \\__  \\    /  ____/  /_\\  \\  /  ____/  /_\\  \\ \n" +
//				" |    |   \\  ___/|  |_|  |__/ __ \\_ /       \\  \\_/   \\/       \\  \\_/   \\\n" +
//				" |______  /\\___  >____/____(____  / \\_______ \\_____  /\\_______ \\_____  /\n" +
//				"        \\/     \\/               \\/          \\/     \\/         \\/     \\/ "));

		ConfigurableApplicationContext applicationContext = springApplication.run(args);

		/**
		 * 【查看源码】
		 *
		 * SpringApplication.run(BellaApplication.class);  --->  new SpringApplication(primarySources).run(args);
		 *
		 * Spring启动流程 :
		 * 1. 创建 SpringApplication 对象
		 * 2. 调用 run 方法
		 *
		 * new SpringApplication(primarySources).run(args); ---> SpringApplication app = new SpringApplication(primarySources); -> app.run(args);
		 */

		//new SpringApplication(BellaApplication.class);

		/**
		 *
		 * Spring 5 最大的特性 : 响应式编程
		 *
		 * this.webApplicationType = WebApplicationType.deduceFromClasspath();  -> 获取当前环境
		 * 			↓	↓	↓	↓	↓	↓	↓	↓	↓
		 *
		 * static WebApplicationType deduceFromClasspath() {
		 * 		if (ClassUtils.isPresent(WEBFLUX_INDICATOR_CLASS, null) && !ClassUtils.isPresent(WEBMVC_INDICATOR_CLASS, null)
		 * 				&& !ClassUtils.isPresent(JERSEY_INDICATOR_CLASS, null)) {
		 * 			return WebApplicationType.REACTIVE;
		 *                }
		 * 		for (String className : SERVLET_INDICATOR_CLASSES) {
		 * 			if (!ClassUtils.isPresent(className, null)) {
		 * 				return WebApplicationType.NONE;
		 *            }
		 *        }
		 * 		return WebApplicationType.SERVLET;* 	}
		 *
		 *  private static final String WEBFLUX_INDICATOR_CLASS = "org." + "springframework.web.reactive.DispatcherHandler";
		 *  DispatcherHandler => 需要引入 spring 的响应式编程 <artifactId>spring-boot-starter-webflux</artifactId>
		 *
		 *
		 *  webApplicationType (enum)
		 *
		 *  NONE : 不会嵌入我们的WEB服务器 最终通过外部tomcat服务器
		 *  SERVLET : 需要使用 servlet 服务器运行
		 *  REACTIVE : 使用响应式WEB启动 , 并启动嵌入响应式WEB服务器
		 *
		 * setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class)); -> 获取当前ApplicationContextInitializer
		 *
		 * 从 spring-boot-xx.jar 中 META-INF 下 spring.factories 寻找 ApplicationContextInitializer (6个)
		 *
		 * setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class)); -> 获取当前ApplicationListener
		 *
		 * 从 spring-boot-xx.jar 中 META-INF 下 spring.factories 寻找 ApplicationListener (9个)
		 *
		 * this.mainApplicationClass = deduceMainApplicationClass(); -> 获取当前启动类
		 *
		 *
		 * 所以 new SpringApplication(primarySources); 其实是获取了配置
		 *
		 */





	}

}
