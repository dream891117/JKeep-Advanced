package com.bella.completableFuture;

import java.util.concurrent.CompletableFuture;

import com.bella.common.CommonUtil;

/**
 @Date: 2020/9/15-16:02
 @Author Genie
 @Description:
 */
public class CF003 {

	public static void main(String[] args){

		// 两个CompletableFuture执行异步查询:
		CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> queryCode("新浪", "https://finance.sina.com.cn/code/"));
		CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> queryCode("163", "https://money.163.com/code/"));

		// 用anyOf合并为一个新的CompletableFuture:  任意一个返回结果,就进行下一步查询价格
		CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);

		// 两个CompletableFuture执行异步查询:
		CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> fetchPrice((String) code, "https://finance.sina.com.cn/price/"));
		CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> fetchPrice((String) code, "https://money.163.com/price/"));

		// 用anyOf合并为一个新的CompletableFuture: 任意一个返回结果 , 就结束
		CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);

		// 最终结果:
		cfFetch.thenAccept((result) -> {
			System.out.println("最终获取的结果 Price : " + result);
		});

		CommonUtil.waitEnd();

	}

	static String queryCode(String name, String url){
		if ( url.contains( "sina" )) {
			try {
				Thread.sleep(2000 );
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if ( url.contains( "163" )) {
			try {
				Thread.sleep(9000 );
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Query Code from [ " + name + " ]");
		try {
			Thread.sleep((long) (Math.random() * 100));
		} catch (InterruptedException e) {
		}
		return name + "601857";
	}

	static Double fetchPrice(String code , String url ) {
		if ( url.contains( "sina" )) {
			try {
				Thread.sleep(2000 );
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if ( url.contains( "163" )) {
			try {
				Thread.sleep(5000 );
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Query Price from < " + code + " >");
		return 5 + Math.random() * 20;
	}

}
