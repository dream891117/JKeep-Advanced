package com.miduo.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 @Date: 2020/12/30-13:03
 @Author Genie
 @Description:
 */
public class JedisClusterTest {

	JedisCluster jc;

	JedisPoolConfig jedisPoolConfig ;

	String clusterPassword = "";

	@Before
	public void jedisClusterInit() {
		Set<HostAndPort> clusterNodes = new HashSet<>();
		/** Jedis Cluster will attempt to discover cluster nodes automatically */
//		clusterNodes.add(new HostAndPort("111.229.7.244", 9201));
		clusterNodes.add(new HostAndPort("111.229.7.244", 9202));
//		clusterNodes.add(new HostAndPort("111.229.7.244", 9203));
		if (StringUtils.isBlank(clusterPassword)) {
			jc = new JedisCluster(clusterNodes);
		} else {
			initJedisPoolConfig();
			System.out.println( jedisPoolConfig.toString() );
			jc = new JedisCluster(clusterNodes,20000,5000,3,clusterPassword, jedisPoolConfig);
		}
	}

	@Test
	public void test(){
		jc.set("miduo", "我爱你");
		String value = jc.get("miduo");
		System.out.println( value );
	}

	/**
	 * 初始化线程池
	 */
	public void initJedisPoolConfig(){
		jedisPoolConfig = new JedisPoolConfig();
//		jedisPoolConfig.setMaxTotal(1000);
//		jedisPoolConfig.setMaxIdle(1000);
//		jedisPoolConfig.setMinIdle(10);
//		jedisPoolConfig.setBlockWhenExhausted(true);
//		jedisPoolConfig.setMaxWaitMillis(10000);
//		jedisPoolConfig.setTestOnBorrow(true);
//		jedisPoolConfig.setTestOnReturn(true);
//		jedisPoolConfig.setTestWhileIdle(true);
//		jedisPoolConfig.setNumTestsPerEvictionRun(10);
//		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
//		jedisPoolConfig.setMinEvictableIdleTimeMillis(5 * 60000);
	}

}
