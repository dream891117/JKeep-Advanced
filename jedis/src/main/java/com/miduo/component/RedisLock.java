//package com.miduo.component;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.params.SetParams;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// @Date: 2020/12/25-16:14
// @Author Genie
// @Description:
// */
//@Component
//public class RedisLock {
//
//	Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	/**
//	 * 锁键
//	 */
//	private final String LOCK_KEY = "redis_lock";
//
//	/**
//	 * 锁过期时间
//	 */
//	protected long EXPIRE_TIME = 30000;
//
//	/**
//	 * 获取锁的超时时间
//	 */
//	private long timeout = 999999;
//
//	/**
//	 * SET命令的参数
//	 */
//	SetParams params = SetParams.setParams().nx().px(EXPIRE_TIME);
//
//	@Autowired
//	JedisPool jedisPool;
//
//	/**
//	 * 加锁
//	 * @param id
//	 * @return
//	 */
//	public boolean lock(String id){
//		Jedis jedis = jedisPool.getResource();
//		Long start = System.currentTimeMillis();
//		try{
//			for(;;){
//				//SET命令返回OK ，则证明获取锁成功
//				String lock = jedis.set(LOCK_KEY, id, params);
//				if("OK".equals(lock)){
//					return true;
//				}
//				//否则循环等待，在timeout时间内仍未获取到锁，则获取失败
//				long l = System.currentTimeMillis() - start;
//				if (l>=timeout) {
//					return false;
//				}
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}finally {
//			jedis.close();
//		}
//	}
//
//}
