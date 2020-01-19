package com.redisTest;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

public class Service {
    // 控制单元

    public void service(String id){
        Jedis jedis = new Jedis();

        String value = jedis.get("id:"+id);
        try{
            if (value == null){
                jedis.setex("id:"+id,10,Long.MAX_VALUE-10+"");

            } else {
                Long val = jedis.incr("id:" + id);
                business(id,val);
            }
        } catch (JedisDataException e){
            System.out.println("您已到达使用次数上限，请升级");
            return;
        } finally {
            jedis.close();
        }
    }

    // 业务操作
    public void business(String id, Long val){
        val = Long.MAX_VALUE-val+1;
        System.out.println("用户 "+id+" 剩余processing次数 "+val+" 次");
    }

}


class MyThread extends Thread{

    Service service = new Service();
    public void run(){
        while (true){
            service.service("a");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}