package com.redisTest;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    @Test
    public void testJedis(){
        // 1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.操作redis
//        jedis.set("str","str");
        String str = jedis.get("str");
        System.out.println(str);
        // 3.关闭redis
        jedis.close();
    }
}
