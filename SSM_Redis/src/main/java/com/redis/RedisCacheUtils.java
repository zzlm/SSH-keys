package com.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

//静态注入
public class RedisCacheUtils {

	@Autowired
	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
		RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
	}
}
