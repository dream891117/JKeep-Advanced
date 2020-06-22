package com.bella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 @Date: 2020/6/11-9:03
 @Author Genie
 @Description:
 */
@SpringBootApplication
public class ThymeleafApplication {

	public static void main(String[] args) {

		System.out.println("启动类为 : " + ThymeleafApplication.class );
		SpringApplication.run(ThymeleafApplication.class);

	}

}
