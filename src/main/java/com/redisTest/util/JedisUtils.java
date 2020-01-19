package com.redisTest.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtils {

    private static JedisPool jedisPool = null;
    private static String host = null;
    private static Integer port = 0;
    private static Integer maxTotal = 0;
    private static Integer maxIdle = 0;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis");
        host = resourceBundle.getString("redis.host");
        port = Integer.parseInt(resourceBundle.getString("redis.port"));
        maxTotal = Integer.parseInt(resourceBundle.getString("redis.maxTotal"));
        maxIdle = Integer.parseInt(resourceBundle.getString("redis.maxIdle"));


        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);                             // 设置最大连接数
        jedisPoolConfig.setMaxIdle(maxIdle);                              // 设置最大活动连接数
        jedisPool = new JedisPool(jedisPoolConfig,host,port);
    }

    // 设置连接池
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
