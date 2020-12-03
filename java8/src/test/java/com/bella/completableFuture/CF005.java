package com.bella.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import com.bella.common.CommonUtil;

/**
 @Date: 2020/9/15-17:17
 @Author Genie
 @Description:
 */
public class CF005 {

	public static void main(String[] args) {

		CompletableFuture<String> cf001 = CompletableFuture.supplyAsync(() -> task001());
		CompletableFuture<String> cf002 = CompletableFuture.supplyAsync(() -> task002());
		CompletableFuture<String> cf003 = CompletableFuture.supplyAsync(() -> task003());
		CompletableFuture<String> cf004 = CompletableFuture.supplyAsync(() -> task004());
		CompletableFuture<String> cf005 = CompletableFuture.supplyAsync(() -> task005());

		CompletableFuture<Void> cfResult = CompletableFuture.allOf(cf001,cf002,cf003,cf004,cf005);

		try {
			cfResult.get();
		}  /** 任务的异常捕捉 */
		catch (InterruptedException e) {
			System.out.println("######################## 任务出错 1 [" +  e.getCause().getMessage() + "]");
			throw new RuntimeException(e.getCause().getMessage());
		} /** 任务的异常捕捉 */
		catch (ExecutionException e) {
			System.out.println("######################## 任务出错 2 [" +  e.getCause().getMessage() + "]");
			throw new RuntimeException(e.getCause().getMessage());
		}

		System.out.println("###################### 所有任务已结束 ######################");

		AtomicReference<String> result1 = new AtomicReference();
		AtomicReference<String> result2 = new AtomicReference();
		AtomicReference<String> result3 = new AtomicReference();
		AtomicReference<String> result4 = new AtomicReference();
		AtomicReference<String> result5 = new AtomicReference();

		/** 如果这个放到外面执行 , cf005 结果会丢失 . why ? */
		cf001.thenAccept( (result) -> result1.set( result ) );
		cf002.thenAccept( (result) -> result2.set( result ) );
		cf003.thenAccept( (result) -> result3.set( result ) );
		cf004.thenAccept( (result) -> result4.set( result ) );
		cf005.thenAccept( (result) -> result5.set( result ) );

		System.out.println( "Result 1 : " + result1.get() );
		System.out.println( "Result 2 : " + result2.get() );
		System.out.println( "Result 3 : " + result3.get() );
		System.out.println( "Result 4 : " + result4.get() );
		System.out.println( "Result 5 : " + result5.get() );

		CommonUtil.waitEnd();

	}

	static String task001(){
		try {
			Thread.sleep(1000 );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("###################### 任务一 执行完毕");
		return "task001-ok";
	}

	static String task002(){
		try {
			Thread.sleep(2000 );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("###################### 任务二 执行完毕");
		return "task002-ok";
	}

	static String task003(){
		try {
			Thread.sleep(3000 );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("###################### 任务三 执行完毕");
		throw new RuntimeException("用户信息不存在，查询有误!");
		//return "task003-ok";
	}

	static String task004(){
		try {
			Thread.sleep(4000 );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("###################### 任务四 执行完毕");
		return "task004-ok";
	}


	static String task005(){
		try {
			Thread.sleep(5000 );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("###################### 任务五 执行完毕");
		return "task005-ok";
	}



}
