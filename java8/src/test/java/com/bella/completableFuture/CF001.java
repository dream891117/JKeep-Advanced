package com.bella.completableFuture;

import java.util.concurrent.CompletableFuture;

import com.bella.common.CommonUtil;

/**
 @Date: 2020/9/15-15:08
 @Author Genie
 @Description: CF -> CompletableFuture
 */
public class CF001 {

	public static void main(String[] args) throws Exception {


		/**
		 * 从Java 8开始引入了CompletableFuture，它针对Future做了改进，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
		 *
		 * 主线程不需要等待结果 , 回调不影响主线程业务。
		 *
		 * 异步任务结束时，会自动回调某个对象的方法；
		 * 异步任务出错时，会自动回调某个对象的方法；
		 * 主线程设置好回调后，不再关心异步任务的执行。
		 */


		// 创建异步执行任务:
		CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CF001::fetchPrice);
		// 如果执行成功:
		cf.thenAccept((result) -> System.out.println("####### 接收到股票价格 ####### : " + result));
		// 如果执行异常:
		cf.exceptionally((e) -> {
			System.out.println("########################获取股票价格异常########################");
			e.printStackTrace();
			return null;
		});

		CommonUtil.waitEnd();

	}


	/**
	 * 获取价格
	 *
	 * @return
	 */
	static Double fetchPrice() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		if (Math.random() < 0.3) {
			throw new RuntimeException("Fetch price failed!");
		}
		return 5 + Math.random() * 20;
	}


}
