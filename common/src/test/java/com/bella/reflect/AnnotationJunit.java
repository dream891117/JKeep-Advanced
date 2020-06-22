package com.bella.reflect;

import java.util.Arrays;
import com.bella.controller.PetController;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 @Date: 2020/6/17-17:46
 @Author Genie
 @Description: 通过JAVA反射获取注解功能
 */
public class AnnotationJunit {

	@Test
	public void test001(){

		Class<?> clazz = PetController.class;

		RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);

		System.out.println("RequestMapping : " + requestMapping);
		System.out.println("RequestMapping value : " + Arrays.toString( requestMapping.value() ) );

	}

}
