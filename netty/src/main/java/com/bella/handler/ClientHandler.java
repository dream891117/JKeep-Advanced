package com.bella.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 @Date: 2020/7/31-14:47
 @Author Genie
 @Description:
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
		System.out.println(String.format("接收到服务器响应数据 : %s",msg));
	}

}
