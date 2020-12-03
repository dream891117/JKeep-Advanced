package com.bella.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 @Date: 2020/7/29-10:44
 @Author Genie
 @Description:
 */
public class NewIoClient {

	public static void main(String[] args) throws Exception {

		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("127.0.0.1",1220));

		ByteBuffer byteBuffer = ByteBuffer.allocate( 1024 );
		byteBuffer.put("Hello , 我是客户端 .".getBytes());

		byteBuffer.flip();
		socketChannel.write( byteBuffer );

		/** 清除数据 */
		byteBuffer.clear();

		int size = socketChannel.read(byteBuffer);
		if ( size > 0 ) {
			System.out.println( "收到服务器数据 : " + new String( byteBuffer.array() ));
		} else {
			System.out.println("没有收到服务器的任何数据");
		}

	}

}
