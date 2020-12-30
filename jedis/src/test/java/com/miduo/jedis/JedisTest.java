package com.miduo.jedis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 @Date: 2020/12/29-10:30
 @Author Genie
 @Description:
 */
public class JedisTest {

	Jedis jedis ;

	@Before
	public void jedisInit(){
		String host = "111.229.7.244";
		jedis = new Jedis(host);
	}

	@Test
	public void redisStringTest() {
		jedis.set("name","Miduo");
		jedis.set("age","3");
		System.out.println( jedis.get("name") );
	}



}
