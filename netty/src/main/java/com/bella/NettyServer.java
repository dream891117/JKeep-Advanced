package com.bella;

import com.bella.channel.ServerChannelInitializer;
import com.bella.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import lombok.Data;

/**
 @Date: 2020/7/29-14:26
 @Author Genie
 @Description:
 */
@Data
public class NettyServer {

	private final String SERVER_NAME = "Bella·Netty-Server";

	public NettyServer(int serverPort) {
		this.serverPort = serverPort;
	}

	/** 服务器端口号 */
	private int serverPort;

	/** 服务器运行状态 */
	/** https://www.cnblogs.com/zhengbin/p/5654805.html */
	private volatile boolean isRunning = false;

	/** 主线程组 : 处理Accept连接事件的线程，这里线程数设置为1即可，netty处理链接事件默认为单线程，过度设置反而浪费cpu资源 */
	private final EventLoopGroup boss = new NioEventLoopGroup(1);

	/** 工作线程组 : 处理hadnler的工作线程，其实也就是处理IO读写 。线程数据默认为 CPU 核心数乘以2 */
	private final EventLoopGroup work = new NioEventLoopGroup();

	public void init() throws Exception{

		System.out.println("正在启动TCP服务器 ...");

		/** 引导对象 :  创建ServerBootstrap实例 （服务器引导类 , 对ServerBootstrap的实例化就是创建netty服务器的入口) */
		ServerBootstrap serverBootstrap=new ServerBootstrap();
		/** 配置工作线程组 */
		serverBootstrap.group(boss, work);
		/** 配置为NIO的socket通道 */
		serverBootstrap.channel(NioServerSocketChannel.class);
		/** 在ServerChannelInitializer中初始化ChannelPipeline责任链，并添加到serverBootstrap中 */
		serverBootstrap.childHandler(new ServerChannelInitializer());
		/** 标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度 */
		serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
		/** 是否启用心跳保活机机制 */
		serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		/** 绑定端口后，开启监听 */
		ChannelFuture channelFuture = serverBootstrap.bind(serverPort).sync();
		if( channelFuture.isSuccess() ){
			System.out.println("*** TCP服务启动成功 ***");
			isRunning = true ;
		}
	}

	/**
	 * 启动服务
	 */
	public synchronized void startServer() {
		try {
			this.init();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}


	/**
	 * 服务关闭
	 */
	public synchronized void stopServer() {
		System.out.println("准备关闭TCP服务器");
		if ( ! this.isRunning ) {
			throw new IllegalStateException( String.format( " %s 未启动 。" , SERVER_NAME ));
		}
		this.isRunning = false;
		try {
			Future<?> future = this.work.shutdownGracefully().await();
			if ( !future.isSuccess() ) {
				System.out.println( "work 无法正常停止: " + future.cause() );
			}
			future = this.boss.shutdownGracefully().await();
			if ( !future.isSuccess() ) {
				System.out.println("boss 无法正常停止: " + future.cause());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("TCP服务已经停止...");
	}

	public static void main(String[] args) throws Exception {

		NettyServer nettyServer = new NettyServer(20206 );
		nettyServer.startServer();


		Thread.sleep( 15000 );
		int size = ServerHandler.channelGroup.size();
		System.out.println("当前连接客户端数量 : " + size );

		ChannelGroupFuture future = ServerHandler.channelGroup.writeAndFlush("{ " +
				" \"code\": \"START_CHECK\" " +
				"}");

		System.out.println( future.isSuccess() );
		System.out.println( future.isPartialSuccess() );
		System.out.println( future.isPartialFailure() );

		System.out.println("通知图像识别开始进行检测 !");


	}

}
