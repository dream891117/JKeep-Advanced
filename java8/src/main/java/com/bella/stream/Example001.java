package com.bella.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.bella.pojo.Dish;
import lombok.Data;

/**
 @Date: 2020/8/7-11:03
 @Author Genie
 @Description: 流的引用
 */
@Data
public class Example001 {

	/**
	 * 菜单
	 */
	private List<Dish> menu;


	private final int range = 400;

	/**
	 * java7 方式实现  [for 循环外部迭代]
	 *
	 * @return
	 */
	public List<String> java7(){
		List<Dish> lowCaloricDishes = new ArrayList<>();
		/** 用累加器筛选元素 */
		for(Dish d: menu){
			if(d.getCalories() < range){
				lowCaloricDishes.add(d);
			}
		}
		/** 用匿名类对菜肴排序 */
		Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
			@Override
			public int compare(Dish d1, Dish d2){
				return Integer.compare(d1.getCalories(), d2.getCalories());
			}
		});
		/** 处理排序后的菜名列表 */
		List<String> lowCaloricDishesName = new ArrayList<>();
		for(Dish d: lowCaloricDishes){
			lowCaloricDishesName.add(d.getName());
		}
		return lowCaloricDishesName;
	}


	/**
	 * java8 方式单线程实现 [Stream API 内部迭代]
	 *
	 * @return
	 */
	public List<String> java8(){
		List<String> lowCaloricDishesName = menu.stream().filter(d -> d.getCalories() < range )
				.sorted( Comparator.comparing(Dish::getCalories) )
				.map(Dish::getName)
				.collect(Collectors.toList());
		return lowCaloricDishesName;
	}

	/**
	 * java8 方式多线程实现 [[Stream API 并行迭代]]
	 *
	 * @return
	 */
	public List<String> java8ByThread(){
		List<String> lowCaloricDishesName = menu.parallelStream().filter(d -> d.getCalories() < range )
				.sorted( Comparator.comparing(Dish::getCalories) )
				.map(Dish::getName)
				.collect(Collectors.toList());
		return lowCaloricDishesName;
	}

	public static void main(String[] args) {


		/**
		 * https://github.com/CarpenterLee/JavaLambdaInternals
		 */

		Example001 example = new Example001();
		List<Dish> menu = new ArrayList<>();
		menu.add(new Dish().setName("土豆丝").setCalories(500));
		menu.add(new Dish().setName("生菜").setCalories(200));
		menu.add(new Dish().setName("西蓝花").setCalories(150));
		menu.add(new Dish().setName("猪蹄膀").setCalories(990));
		menu.add(new Dish().setName("羊排").setCalories(800));
		menu.add(new Dish().setName("鱼香肉丝").setCalories(600));
		menu.add(new Dish().setName("鲈鱼").setCalories(260));
		menu.add(new Dish().setName("糖醋里脊").setCalories(550));
		menu.add(new Dish().setName("酸辣臊子蹄筋").setCalories(300));
		menu.add(new Dish().setName("鲜花豆腐").setCalories(280));
		menu.add(new Dish().setName("参麦团鱼").setCalories(150));
		menu.add(new Dish().setName("糖焖莲子").setCalories(370));
		menu.add(new Dish().setName("拍黄瓜").setCalories(100));
		menu.add(new Dish().setName("冰镇酸辣蜇头").setCalories(350));
		menu.add(new Dish().setName("糖酥排骨").setCalories(750));
		menu.add(new Dish().setName("炝冬笋").setCalories(250));
		menu.add(new Dish().setName("龙须菜").setCalories(170));
		menu.add(new Dish().setName("拌海蜇").setCalories(350));
		example.setMenu( menu );

		System.out.println("###########################################");

		long startTime7 = System.currentTimeMillis();
		List<String> dishNames = example.java7();
		System.out.println( String.format("Java7 处理耗时 : %s" , System.currentTimeMillis() - startTime7 ) );

		System.out.println("Result : " + dishNames.toString() );

		System.out.println("###########################################");

		long startTime8 = System.currentTimeMillis();
		dishNames = example.java8();
		System.out.println( String.format("Java8 单线程处理耗时 : %s" , System.currentTimeMillis() - startTime8 ) );

		System.out.println("Result : " + dishNames.toString() );

		System.out.println("###########################################");

		long startTime8Thread = System.currentTimeMillis();
		dishNames = example.java8ByThread();
		System.out.println( String.format("Java8 多线程处理耗时 : %s" , System.currentTimeMillis() - startTime8Thread ) );

		System.out.println("Result : " + dishNames.toString() );

		System.out.println("###########################################");

	}

}
