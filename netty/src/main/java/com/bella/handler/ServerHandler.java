package com.bella.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 @Date: 2020/7/29-17:07
 @Author Genie
 @Description:
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

	/** 保留所有与服务器建立连接的channel对象，这边的GlobalEventExecutor在写博客的时候解释一下，看其doc */
	public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	/**
	 * 服务器端收到任何一个客户端的消息都会触发这个方法
	 * 连接的客户端向服务器端发送消息，那么其他客户端都收到此消息，自己收到【自己】+消息
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

		System.out.println("收到客户端消息 : \n " + msg);

		if ( msg.contains("state") ) {
			Channel channel = ctx.channel();
			channel.writeAndFlush("{ " +
					"\"code\":\"RECEIVE_RESULT\" " +
					"}");
		}

//		Channel channel = ctx.channel();
//		for (Channel c : channelGroup) {
//			if( channel != c ){
//				c.writeAndFlush(channel.remoteAddress() +" 发送的消息:" +msg+" \n");
//			} else {
//				c.writeAndFlush("你好,消息已收到! 谢谢!" );
//			}
//		}
	}


	/**
	 * 服务端与客户端连接建立
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		/** 其实相当于一个connection */
		Channel channel = ctx.channel();
		/**
		 * 调用channelGroup的writeAndFlush其实就相当于channelGroup中的每个channel都writeAndFlush
		 *
		 * 先去广播，再将自己加入到channelGroup中
		 */
		channelGroup.writeAndFlush(" 【服务器】 -" +channel.remoteAddress() +" 加入\n");
		channelGroup.add(channel);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush(" 【服务器】 -" +channel.remoteAddress() +" 离开\n");

		//验证一下每次客户端断开连接，连接自动地从channelGroup中删除调。
		System.out.println(channelGroup.size());
		//当客户端和服务端断开连接的时候，下面的那段代码netty会自动调用，所以不需要人为的去调用它
		//channelGroup.remove(channel);
	}

	/**
	 * 连接处于活动状态
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress() +" 上线了");
		System.out.println("当前客户端连接数为 : " + channelGroup.size());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress() +" 下线了");
		System.out.println("当前客户端连接数为 : " + channelGroup.size());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
