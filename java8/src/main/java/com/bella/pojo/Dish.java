package com.bella.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 @Date: 2020/8/7-11:05
 @Author Genie
 @Description: 菜肴
 */
@Accessors(chain = true)
@Data
public class Dish {


	/**
	 * 菜名 eg. 土豆丝
	 */
	private String name;


	private boolean vegetarian;

	/**
	 * 热量 ( 单位 : cal) -> eg. 350 cal
	 */
	private int calories;

	/**
	 * 菜肴类别
	 */
	private Type type;


	/**
	 * MEAT : 肉食
	 * FISH : 鱼
	 * OTHER : 其他
	 */
	public enum Type { MEAT, FISH, OTHER }

}
