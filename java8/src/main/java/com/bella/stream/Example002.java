package com.bella.stream;

import java.util.Arrays;
import java.util.List;

import com.bella.pojo.Dish;

/**
 @Date: 2020/8/10-15:21
 @Author Genie
 @Description:
 */
public class Example002 {

	List<Dish> menu = Arrays.asList(
			new Dish().setName("pork").setVegetarian(false).setCalories(800).setType(Dish.Type.MEAT) ,
			new Dish().setName("beef").setVegetarian(false).setCalories(700).setType(Dish.Type.MEAT) ,
			new Dish().setName("chicken").setVegetarian(false).setCalories(400).setType(Dish.Type.MEAT) ,
			new Dish().setName("french fries").setVegetarian(true).setCalories(530).setType(Dish.Type.OTHER) ,
			new Dish().setName("rice").setVegetarian(true).setCalories(350).setType(Dish.Type.OTHER) ,
			new Dish().setName("season fruit").setVegetarian(true).setCalories(120).setType(Dish.Type.OTHER) ,
			new Dish().setName("pizza").setVegetarian(true).setCalories(550).setType(Dish.Type.OTHER) ,
			new Dish().setName("prawns").setVegetarian(false).setCalories(300).setType(Dish.Type.FISH) ,
			new Dish().setName("salmon").setVegetarian(false).setCalories(450).setType(Dish.Type.FISH) );

}
