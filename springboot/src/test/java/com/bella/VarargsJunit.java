package com.bella;

import org.junit.Test;

/**
 @Date: 2020/6/11-16:16
 @Author Genie
 @Description: 可变形参 Varargs , JDK1.5之后新特性. This facility eliminates the need for manually boxing up argument lists into an array when invoking methods that accept variable-length argument lists.
 */
public class VarargsJunit {

	/**
	 *
	 * 编译器会创建一个数组才存储所有不确定的参数，由于数量不确定，所以可变参数只能有一个，且要放在参数列表末尾
	 *
	 * 可变参数可传入一个或多个同类型的参数 (String... args , int... args, boolean... 等等)，或者传入一个数组
	 *
	 * String... args -> 是一个不定长度的参数 (可变参数,可以不传递)
	 *
	 * String... args 必须作为函数形参的【最后一个参数】 (否则编译器报错)
	 *
	 * String... args 只能作为形参 , 无法作为Class或函数变量定义 (编译器报错)
	 *
	 * @param args
	 */
	public static void printArgs(String... args){
		System.out.println("########################START########################");
		System.out.println( "Args Length : " + args.length );
		for (String str : args){
			System.out.println(String.format("Arg : %s",str));
		}
		System.out.println("#########################END#########################");
	}

	/**
	 * String [] array 是一个数组
	 *
	 * @param array
	 */
	public static void printArray(String [] array){
		System.out.println("************************START************************");
		System.out.println("Array Length : " + array.length);
		for (String str : array){
			System.out.println(String.format("Array : %s",str));
		}
		System.out.println("*************************END*************************");
	}


	/**
	 * Array 与 args 类型比较 (非值)
	 *
	 * @param array
	 * @param args
	 */
	public static void compare(String [] array,String... args){
		System.out.println( args instanceof String [] ? "类型一致 [A]" : "类型不一致 [A]");
		System.out.println( args.getClass() == array.getClass() ? "类型一致 [B]" : "类型不一致 [B]");
	}

	@Test
	public void test001(){

		/**
		 * 形参数组的使用方式
		 */
		VarargsJunit.printArray(new String[]{"1","2","3"});

		/**
		 * 形参 Varargs 的使用方式 (更加灵活和简便)
		 * String... args 是一个不定长度的参数 , 此形参可以不传
		 */
		VarargsJunit.printArgs();
		VarargsJunit.printArgs("1","2","3");
		VarargsJunit.printArgs(new String[]{"1","2","3"});

	}

	@Test
	public void test002(){
		/**
		 * String[] args 和 String... args 本质没有区别
		 * String... args 最后还是转化为 String [] args , 只是String... args作为形参更加灵活和简便
		 */
		VarargsJunit.compare(new String[]{"123"},"456");
	}

}
