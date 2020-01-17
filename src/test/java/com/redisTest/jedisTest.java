package com.redisTest;

import redis.clients.jedis.Jedis;

public class jedisTest {
    public void testJedis(){
        // 1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.操作redis

        // 3.关闭redis
    }
}
