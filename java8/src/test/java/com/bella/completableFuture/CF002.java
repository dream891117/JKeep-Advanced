package com.bella.completableFuture;

import java.util.concurrent.CompletableFuture;

import com.bella.common.CommonUtil;

/**
 @Date: 2020/9/15-15:53
 @Author Genie
 @Description:
 */
public class CF002 {

	public static void main(String[] args) throws Exception {

		/**
		 * 如果只是实现了异步回调机制，我们还看不出CompletableFuture相比Future的优势。
		 * CompletableFuture更强大的功能是，多个CompletableFuture可以串行执行，
		 * 例如，定义两个CompletableFuture，第一个CompletableFuture根据证券名称查询证券代码，第二个CompletableFuture根据证券代码查询证券价格
		 */



		// 第一个任务:
		CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> queryCode("中国石油"));
		// cfQuery成功后继续执行下一个任务:
		CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) -> fetchPrice(code));
		// cfFetch成功后打印结果:
		cfFetch.thenAccept((result) -> {
			System.out.println("price: " + result);
		});

		CommonUtil.waitEnd();

	}

	/**
	 * 查询证券代码
	 *
	 * @param name
	 * @return
	 */
	static String queryCode(String name) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		System.out.println("查询证券代码 [ " + name + " ]");
		return "601857";
	}


	/**
	 * 根据证券代码查询证券价格
	 *
	 * @param code
	 * @return
	 */
	static Double fetchPrice(String code) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		System.out.println("根据证券代码查询证券价格 [ " + code + " ]");
		return 5 + Math.random() * 20;
	}


}
