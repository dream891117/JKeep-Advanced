package com.bella;

import org.junit.Test;

import org.springframework.util.StopWatch;

/**
 @Date: 2020/6/10-10:56
 @Author Genie
 @Description: StopWatch 测试
 */
public class StopWatchJunit {

	@Test
	public void test001(){

		StopWatch stopWatch = new StopWatch();
		stopWatch.start("测试StopWatch");

		try {
			Thread.sleep(3000 );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		stopWatch.stop();
		System.out.println("LastTaskTimeMillis : " + stopWatch.getLastTaskTimeMillis());
		System.out.println(stopWatch.prettyPrint());

	}
}
