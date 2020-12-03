package com.bella;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 @Date: 2020/7/27-10:25
 @Author Genie
 @Description:
 */
public class SocketClient {

	public static void main(String[] args) {

		try {
			//1.创建一个客户端socket ,指定服务器地址和端口
			Socket socket = new Socket("127.0.0.1",1117);
			//2.获取输出流，向服务器端发送信息
			//字节输出流
			//将输出流包装为打印流
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os,true);
			pw.println("我是客户端,发送一个消息");
			pw.println("我是客户端,我又发送一个消息");
			pw.println("我是客户端,我又又发送一个消息");
			//pw.write( "我是客户端,发送一个消息 \n" );

//			String quit = "quit;";
//			while ( true  ) {
//				System.out.println("*** 请输入您要对服务器发送的消息 ***");
//				Scanner scanner = new Scanner( System.in );
//				inputValue = scanner.next();
//				if ( inputValue.equals( quit ) ) {
//					/** 如果接收到的是 quit; 就直接跳出断开服务器连接 */
//					break;
//				}
//				pw.write( inputValue );
//			}

			/** 关闭输出流 , 服务器 ReadLine 才会结束 ，如果不关闭 , 服务器ReadLine 会一直阻塞等待 */
			socket.shutdownOutput();


			/** 3.获取输入流，并读取服务端的响应信息 */
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info ;

			/** IO阻塞 */
			while( ( info = br.readLine() ) != null ){
				System.out.println("我是客户端 , 服务端说："+info);
			}

			/** 4.关闭资源 */
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}

	}

}
