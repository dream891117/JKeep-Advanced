package com.bella;

import com.bella.channel.ClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 @Date: 2020/7/31-14:46
 @Author Genie
 @Description: Netty 客户端
 */
public class NettyClient {

	private final String host;
	private final int port;
	private Channel channel;

	public NettyClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	private final EventLoopGroup group = new NioEventLoopGroup();

	/**
	 * 启动客户端
	 *
	 * @throws Exception
	 */
	public void startClient() throws Exception {

		Bootstrap clientBootstrap = new Bootstrap();

		clientBootstrap.group(group);
		clientBootstrap.channel(NioSocketChannel.class);
		clientBootstrap.handler(new ClientChannelInitializer());

		/** 发起异步连接请求，绑定连接端口和host信息 */
		final ChannelFuture future = clientBootstrap.connect(host, port).sync();

		if ( future.isSuccess() ) {
			System.out.println("连接服务器成功");
			this.channel = future.channel();
		} else {
			System.out.println("连接服务器失败");
			future.cause().printStackTrace();
			group.shutdownGracefully(); //关闭线程组
		}

//		future.addListener(new ChannelFutureListener() {
//			@Override
//			public void operationComplete(ChannelFuture arg0) throws Exception {
//				if (future.isSuccess()) {
//					System.out.println("连接服务器成功");
//
//				} else {
//					System.out.println("连接服务器失败");
//					future.cause().printStackTrace();
//					group.shutdownGracefully(); //关闭线程组
//				}
//			}
//		});

	}

	/**
	 * 发送消息给服务器
	 *
	 * @param msg
	 */
	public void sendMsgToServer(String msg){
		if ( channel != null ) {
			System.out.println("发送消息 : " + msg );
			channel.writeAndFlush( msg );
		}
	}


	public static void main(String[] args) throws Exception {

		NettyClient nettyClient = new NettyClient("127.0.0.1",20206);
		nettyClient.startClient();

		Thread.sleep(20000 );

		

		/** 通知断送电服务器收到消息 */
		nettyClient.sendMsgToServer("{\n" +
				"\"code\": \"RECEIVE_CHECK\"\n" +
				"}");

		Thread.sleep(10000 );

		/** 通知断送电服务器图片识别结果 */
		nettyClient.sendMsgToServer("{\n" +
				" \"state\": \"6\"\n" +
				"}");

	}


}
