//package com.miduo.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//
///**
// @Date: 2020/12/25-17:01
// @Author Genie
// @Description: Redis配置 - Jedis使用
// */
//@Configuration
//public class JedisConfig {
//
//	Logger logger = LoggerFactory.getLogger(JedisConfig.class);
//
//	@Value("${spring.redis.host}")
//	private String host;
//
//	@Value("${spring.redis.port}")
//	private int port;
//
//	@Value("${spring.redis.password}")
//	private String password;
//
//	@Value("${spring.redis.timeout}")
//	private int timeout;
//
//	@Value("${spring.redis.jedis.pool.max-active}")
//	private int maxActive;
//
//	@Value("${spring.redis.jedis.pool.max-idle}")
//	private int maxIdle;
//
//	@Value("${spring.redis.jedis.pool.min-idle}")
//	private int minIdle;
//
//	@Value("${spring.redis.jedis.pool.max-wait}")
//	private long maxWaitMillis;
//
//
//	@Bean
//	public JedisPoolConfig jedisPoolConfig() {
//		/**
//		 * JedisPoolConfig 的详细配置
//		 */
//		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
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
////		jedisPoolConfig.setMaxIdle(maxIdle);
////		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
////		jedisPoolConfig.setMaxTotal(maxActive);
////		jedisPoolConfig.setMinIdle(minIdle);
//		return jedisPoolConfig;
//	}
//
//	@Bean
//	public JedisPool jedisPoolFactory(JedisPoolConfig jedisPoolConfig){
//		if (StringUtils.isEmpty(password)) {
//			return new JedisPool(jedisPoolConfig,host,port);
//		} else {
//			return new JedisPool(jedisPoolConfig,host,port,timeout,password);
//		}
//	}
//
//
////	@Value("${spring.redis.cluster.nodes}")
////	private String clusterNodes;
////
////	@Value("${spring.redis.cluster.password}")
////	private String clusterPassword;
////
////	@Bean
////	public JedisCluster jedisClusterFactory(JedisPoolConfig jedisPoolConfig){
////		String[] nodes = clusterNodes.split(",");
////		Set<HostAndPort> nodeSet = new HashSet<>();
////		for(String node : nodes){
////			String[] hp = node.split(":");
////			nodeSet.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
////		}
////		JedisCluster jedisCluster = new JedisCluster(nodeSet,20000,5000,3,clusterPassword, jedisPoolConfig);
////		return jedisCluster;
////	}
//
//
//}
