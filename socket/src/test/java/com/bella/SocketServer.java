package com.bella;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 @Date: 2020/7/28-14:55
 @Author Genie
 @Description:
 */
public class SocketServer {


	/**
	 * 启动一个 ServerSocket , 接收一个 Socket 。
	 *
	 * @throws Exception
	 */
	@Test
	public void startSocketServer() throws Exception {

		ServerSocket serverSocket = new ServerSocket(1220 );
		System.out.println("*** Socket服务器已启动，等待客户端的连接 ***");
		/** 连接阻塞 */
		Socket socket = serverSocket.accept();

		System.out.println( String.format( "客户端 [%s:%s] 接入服务器 " , socket.getInetAddress().getHostAddress() , socket.getPort() ) );

		BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream()) );

		/** IO阻塞 */
		String clientMsg ;
		while ( ( clientMsg = br.readLine() ) != null ) {
			System.out.println( clientMsg );
		}

		/** 只接收一行数据 */
//		String clientMsg = br.readLine() ;
//		System.out.println( "客户端说 : " + clientMsg );

		PrintWriter pw = new PrintWriter( socket.getOutputStream() , true );
		pw.println("欢迎你");

		pw.close();
		br.close();
		socket.close();
		serverSocket.close();

	}


	/**
	 * 启动一个 ServerSoket , 接收多个 Socket 。
	 *
	 * 单线程方式实现实现接收多个 Socket 是会发生阻塞IO的  ( 阻塞IO (BIO) :  1 . 连接阻塞 2. IO阻塞 )
	 * 发送阻塞的情况 :
	 * 	比如 :  A客户端在写入的消息的时候加入 Sleep 的操作 或者写入的速度很慢 . 服务器 br.readLine() 时候就会等待 (IO阻塞) , 那么后面接入进来的客户端就要等待 A 客户端的操作结束后才可以接入进来.
	 *  无论是服务器处理的慢 , 还是客户端处理的慢 . 后面的客户端都要等待 。
	 *
	 *
	 * 所以在现实业务中我们需要多线程方式实现 。
	 *
	 *
	 *
	 * @throws Exception
	 */
	@Test
	public void startSocketServer002() throws Exception {

		ServerSocket serverSocket = new ServerSocket(1220 );
		System.out.println("*** Socket服务器已启动，等待客户端的连接 ***");

		while ( true ) {

			Socket socket = serverSocket.accept();

			System.out.println( String.format( "客户端 [%s:%s] 接入服务器 " , socket.getInetAddress().getHostAddress() , socket.getPort() ) );

			BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream()) );

			String clientMsg ;
			while ( ( clientMsg = br.readLine() ) != null ) {
				System.out.println( clientMsg );
			}

			PrintWriter pw = new PrintWriter( socket.getOutputStream() , true );
			pw.println("欢迎你");

			pw.close();
			br.close();
			socket.close();
			/** serverSoket 不能关闭 , 否则无法接受新的 Socket 。 */
			//serverSocket.close();
		}

	}





}
