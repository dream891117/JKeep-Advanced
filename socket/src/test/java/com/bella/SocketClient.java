package com.bella;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.junit.Test;

/**
 @Date: 2020/7/28-15:16
 @Author Genie
 @Description:
 */
public class SocketClient {

	@Test
	public void startSocketClient001() throws Exception {

		Socket socket = new Socket("127.0.0.1",1220);

		PrintWriter pw = new PrintWriter( socket.getOutputStream() ,true );
		pw.println("Hello , 服务器 , 你好 !");
		Thread.sleep( 3000 );
		pw.println(" 【A】 ");
		Thread.sleep( 3000 );
		pw.println(" 【B】 ");
		Thread.sleep( 3000 );
		pw.println(" 【C】 ");

		/** 关闭输出流 , 服务器 ReadLine 才会结束 ，如果不关闭 , 服务器ReadLine 会一直阻塞等待 */
		socket.shutdownOutput();

		/**
		 * 如果 PrintWriter 开启了自动刷新
		 * 那么当PrintWriter调用println，prinlf或format方法时 , 输出流中的数据就会自动刷新出去。
		 *
		 * 如果调用的是 write , 还是需要手动刷新 pw.flush();
		 *
		 * 如果没有开启自动刷新 , 所有输出方法 , 最后都需要 pw.flush();
		 * 当然无论发送多少消息 , 最后执行一次 pw.flush(); 即可 .
		 */

		BufferedReader br = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
		String serverMsg = br.readLine();
		System.out.println("服务器回复 : " + serverMsg);

		br.close();
		pw.close();
		socket.close();
	}

}
