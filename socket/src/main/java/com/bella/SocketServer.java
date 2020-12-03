package com.bella;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 @Date: 2020/7/27-10:57
 @Author Genie
 @Description:
 */
public class SocketServer {

	public static void main(String[] args) {

		try {
			/** 1.创建一个服务器socket ,即 ServerSocket */
			ServerSocket serverSocket = new ServerSocket(1117 );

			System.out.println("*** Socket服务器已启动，等待客户端的连接 ***");

			/** 2.调用accept（）方法开始监听，等待客户端连接 */
			/** 连接阻塞 */
			Socket socket = serverSocket.accept();

			String hostAddress = socket.getInetAddress().getHostAddress();
			int port = socket.getPort();
			System.out.println( String.format( "客户端 [%s:%s] 接入服务器 " , hostAddress , port ) );

			/** 3.获取输入流，并获取客户端信息 (字节输入流)*/
			InputStream is = socket.getInputStream();
			/** 将字节流转换成字符流 */
			InputStreamReader isr = new InputStreamReader(is);
			/** 为输入流添加缓冲 */
			BufferedReader br = new BufferedReader(isr);
			StringBuffer clientMsg = new StringBuffer();
			String info  ;
			while( ( info = br.readLine() ) != null ){
				System.out.println( info );
				clientMsg.append( info );
			}
			System.out.println( "*** 客户端消息接收完毕 ***" );
			socket.shutdownInput();//关闭输入流
			/** 4.获取输出流，响应客户端请求 (字节输出流) */
			OutputStream os = socket.getOutputStream();
//			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
//			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//
//			bufferedWriter.write("欢迎你 !");
//			bufferedWriter.flush();
//			bufferedWriter.close();

			/** 将输出流包装为打印流 */
			PrintWriter pw = new PrintWriter( os ,true);
			pw.write("欢迎你");

			//5.关闭资源
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
