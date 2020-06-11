package com.bella;

import org.springframework.util.StopWatch;

/**
 @Date: 2020/6/10-10:56
 @Author Genie
 @Description:
 */
public class StopWatchJunit {

	public static void main(String[] args) {

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
