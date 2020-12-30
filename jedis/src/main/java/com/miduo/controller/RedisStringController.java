//package com.miduo.controller;
//
//import net.bytebuddy.asm.Advice;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// @Date: 2020/12/29-13:56
// @Author Genie
// @Description:
// */
//@RequestMapping("/api/redis/string")
//@RestController
//public class RedisStringController {
//
//	@Autowired
//	JedisPool jedisPool;
//
//	@RequestMapping("/set")
//	public String set(@RequestParam String key , @RequestParam String value){
//		Jedis jedis = jedisPool.getResource();
//		if ( jedis != null ) {
//			jedis.set(key,value);
//			return jedis.get(key);
//		} else {
//			return "无法获取Redis资源!";
//		}
//	}
//
//
//
//
//}
