package com.middleserver.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * Created by jiamingku on 2019/4/15.
 * 池技术里面用到了pool2这个结构
 */
@SuppressWarnings("all")
public class RedisJedisPoolTest {
    private static JedisPool redisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 初始化redis连接池配置
//		jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(50);
        // 设置最小空闲数
        jedisPoolConfig.setMinIdle(20);
//		jedisPoolConfig.setMaxWaitMillis(3000);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setTestOnReturn(true);
        // Idle时进行连接扫描
        jedisPoolConfig.setTestWhileIdle(true);
        // 表示idle object evitor两次扫描之间要sleep的毫秒数
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(10_000);
        // 表示idle object evitor每次扫描的最多的对象数
        jedisPoolConfig.setNumTestsPerEvictionRun(10);
        // 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        jedisPoolConfig.setMinEvictableIdleTimeMillis(30_000);

        String host = "122.51.100.64";
        int port = 16379;
        int db = 11;
        redisPool = new JedisPool(jedisPoolConfig, host, port, 3000, null, db);
    }

    @Test
    public void testSetReturn() {
        Jedis aws = redisPool.getResource();
        String s = aws.set("tkey", "tvalue1");
        System.out.println("s = " + s);

        long a = aws.del("tkey");

        redisPool.returnResource(aws);
        System.out.println("a = " + a);
    }

    @Test
    public void testGetReturn() {
        Jedis aws = redisPool.getResource();
        String s = aws.get("tkey1s112");
        System.out.println("s = " + s);
    }

    /**
     * 是否存在  命令返回-- 0，1
     * 是否存在  java 返回true false
     */
    @Test
    public void testExistsReturn() {
        Jedis aws = redisPool.getResource();
        boolean b = aws.exists("tkey");
        System.out.println("s = " + b);
    }

    @Test
    public void testGetAllKey() {
        Jedis aws = redisPool.getResource();
        Set<String> s = aws.keys("*");
        System.out.println("s = " + s.size());
        for (String s1 : s) {
            System.out.println("s1 = " + s1);
        }
    }

    //  return  // set key value EX 1296000 NX
    // 存在的时候返回null
    @Test
    public void testSetNx() {
        Jedis jedis = redisPool.getResource();
        String o = jedis.set("kkkk", "vvvvv", "NX", "EX", 11111111L);
        System.out.println("o = " + o);
    }

}
