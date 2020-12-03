package com.bella.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 @Date: 2020/7/29-10:11
 @Author Genie
 @Description:
 */
public class NewIoServer {


	public static void main(String[] args) throws Exception {

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		/** 默认是阻塞的 true */
		serverSocketChannel.configureBlocking( false );
		serverSocketChannel.socket().bind( new InetSocketAddress(1220) );
		while ( true ) {
			/** 监听一个客户端请求 */
			SocketChannel socketChannel = serverSocketChannel.accept();
			if ( socketChannel != null ) {

				System.out.println( String.format( "客户端 [%s:%s] 接入" , socketChannel.socket().getInetAddress().getHostAddress() , socketChannel.socket().getPort() ) );

				ByteBuffer byteBuffer = ByteBuffer.allocate( 1024 );
				/** 把数据读取到缓存区 */
				socketChannel.read( byteBuffer );
				System.out.println( String.format("收到客户端发送的消息 : %s " , new String ( byteBuffer.array() ) ) );

				ByteBuffer writeBuffer = ByteBuffer.allocate( 1024 );
				writeBuffer.put("客户端你好 , 欢迎接入 ! .".getBytes());
				/** 写出数据 */
				//https://www.cnblogs.com/woshijpf/articles/3723364.html
				writeBuffer.flip();
				/** 写出去 */
				socketChannel.write( writeBuffer );
			} else {
				System.out.println( String.format( "*** 等待客户端接入 , 连接未就绪 %s ***" , LocalDateTime.now().format( DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss") ) ) );
			}

			Thread.sleep( 3000 );
		}


	}
}
