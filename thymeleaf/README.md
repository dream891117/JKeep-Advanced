### SpringBoot 整合 Themeleaf

#### 1. pom.xml 引入 Themeleaf 依赖
```xml
<!--引入thymeleaf依赖-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
#### 2. 实现ThymeleafController
```java
@RequestMapping("/")
public String index(Model model){
	model.addAttribute("name","Thymeleaf官网");
	model.addAttribute("website","https://www.thymeleaf.org/");
	return "index";
}
```
#### 3. 实现 index 页面
* 在 resouces 下创建 templates 文件 (Thymeleaf默认页面存储路径)
* 实现 index.html
```html
<body>
    <div>
        <p>点击下面进入官网</p>
        <a th:href="${website}"><p th:text="${name}"></p></a>
    </div>
</body>
```

#### 4. 启动ThymeleafApplication并访问Action







