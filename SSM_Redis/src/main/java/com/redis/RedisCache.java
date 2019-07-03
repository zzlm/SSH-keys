package com.redis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

public class RedisCache implements Cache{

	private static JedisConnectionFactory jedisConnectionFactory;
	
	private final ReadWriteLock rw = new ReentrantReadWriteLock();
	
	//声明一个id
	private final String id;
	//通过一个构造方法给id赋值
	public RedisCache(final String id){
		
		this.id=id;
	}
	
	//获取id
	public String getId() {
		return this.id;
	}
	
	//把数据存在redis中
	public void putObject(Object key, Object value) {
		//上锁
		rw.writeLock().lock();
		//获得redis连接
		RedisConnection con = jedisConnectionFactory.getConnection();
		RedisSerializer<Object> rs = new JdkSerializationRedisSerializer();
		//序列化操作
		con.set(rs.serialize(key), rs.serialize(value));
		System.out.println("添加mybatis二级缓存成功key："+key+"value:"+value);
		con.close();
		rw.writeLock().unlock();
	}

	public Object getObject(Object key) {
		rw.readLock().lock();
		RedisConnection con = jedisConnectionFactory.getConnection();
		RedisSerializer<Object> rs = new JdkSerializationRedisSerializer();
		
		//反序列化取出数据
		byte[] bs = con.get(rs.serialize(key));
		Object object = rs.deserialize(bs);
		System.out.println("------------------ 命中二级缓存	key："+key+"value:"+object);
		con.flushAll();
		con.close();
		rw.readLock().unlock();
		return object;
	}

	public Object removeObject(Object key) {
		rw.writeLock().lock();
		RedisConnection con = jedisConnectionFactory.getConnection();
		RedisSerializer<Object> rs = new JdkSerializationRedisSerializer();
		//是否删除成功
		Object expire = con.expire(rs.serialize(key), 0);
		con.close();
		rw.writeLock().unlock();
		
		return expire;
	}

	public void clear() {
		rw.readLock().lock();
		RedisConnection con = jedisConnectionFactory.getConnection();
		con.flushDb();
		con.flushAll();
		con.close();
		rw.readLock().unlock();
	}

	//获取缓存数量
	public int getSize() {
		RedisConnection con = jedisConnectionFactory.getConnection();
		Long dbSize = con.dbSize();
		Integer number = Integer.valueOf(dbSize.toString());
		return number;
	}

	public ReadWriteLock getReadWriteLock() {
		
		return rw;
	}
	
	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
		RedisCache.jedisConnectionFactory=jedisConnectionFactory;
	}

}
