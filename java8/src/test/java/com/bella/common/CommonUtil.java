package com.bella.common;

/**
 @Date: 2020/9/15-16:06
 @Author Genie
 @Description:
 */
public class CommonUtil {

	public static void waitEnd() {
		int waitTime = 10 ;
		int waitIndex = 0 ;
		while ( true ) {
			try {
				Thread.sleep( 1000 );
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			waitIndex ++ ;
			System.out.println(String.format("主线程已等待 [ %s 秒 ] ! ",waitIndex) );
			if ( waitIndex >= waitTime ) {
				break;
			}
		}

		System.out.println("主线程结束");
	}
}
