package com.miduo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 @Date: 2020/12/29-23:28
 @Author Genie
 @Description:
 */
@RequestMapping("/api/redis/properties")
@RestController
public class RedisPropertiesController {

	@Autowired
	RedisProperties redisProperties;

	@RequestMapping("/detail")
	public RedisProperties redisProperties(){
		return redisProperties;
	}

}
