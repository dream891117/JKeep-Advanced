package com.bella.controller.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;


/**
 @Date: 2020/6/17-15:15
 @Author Genie
 @Description: 宠物信息VO ( @Component 注解只是为了测试反射功能添加而已 ,没有实际意义 )
 */
@Component
@Accessors(chain = true)
@Data
public class PetVo {

	/**
	 * 宠物名字
	 */
	private String name ;

	/**
	 * 宠物性别
	 */
	private String sex;

	/**
	 * 宠物年龄
	 */
	private String age;

}
