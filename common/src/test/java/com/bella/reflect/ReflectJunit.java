package com.bella.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import com.bella.controller.vo.PetVo;
import org.junit.Test;

/**
 @Date: 2020/6/17-15:06
 @Author Genie
 @Description: 测试所有反射的功能
 */
public class ReflectJunit {

	@Test
	public void test001(){

		Class<?> clazz = PetVo.class;

		Method[] methods = clazz.getDeclaredMethods();

		System.out.println("------------------------所有注解------------------------");
		/**
		 * 注意 : Lombok的注解貌似被编译后就不存在了 , 这里获取不到Lombok的注解
		 */
		Annotation[] annotations = clazz.getDeclaredAnnotations();
		for (Annotation annotation : annotations){
			System.out.println("Annotation : " + annotation );
		}

		System.out.println("------------------------所有变量------------------------");
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields){
			System.out.println("Field : " + field.getName() );
		}


		System.out.println("------------------------所有方法------------------------");
		for (Method method : methods){
			System.out.println( "Method : " + method.getName() );
		}

	}

}
