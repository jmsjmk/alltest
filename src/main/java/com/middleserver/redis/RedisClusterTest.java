package com.middleserver.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * 集群模式的连接
 */
public class RedisClusterTest {

    @Test
    public void testJedisCluster() throws Exception {
        // 第一步：使用JedisCluster对象。需要一个Set<HostAndPort>参数。Redis节点的列表。
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.25.153", 7001));
        nodes.add(new HostAndPort("192.168.25.153", 7002));
        nodes.add(new HostAndPort("192.168.25.153", 7003));
        nodes.add(new HostAndPort("192.168.25.153", 7004));
        nodes.add(new HostAndPort("192.168.25.153", 7005));
        nodes.add(new HostAndPort("192.168.25.153", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        // 第二步：直接使用JedisCluster对象操作redis。在系统中单例存在。
        jedisCluster.set("hello", "100");
        String result = jedisCluster.get("hello");
        // 第三步：打印结果
        System.out.println(result);
        // 第四步：系统关闭前，关闭JedisCluster对象。
        jedisCluster.close();
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

        try {
            Set<String> sentinels = new HashSet<>();
            String hostAndPort1 = "122.51.100.64:26379";
            sentinels.add(hostAndPort1);

            String clusterName = "myredis";
            String password = null;

            JedisSentinelPool redisSentinelJedisPool = new JedisSentinelPool(clusterName, sentinels, password);

            Jedis jedis = null;
            try {
                jedis = redisSentinelJedisPool.getResource();
                jedis.set("key", "value");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                redisSentinelJedisPool.returnBrokenResource(jedis);
            }

            redisSentinelJedisPool.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        String s = "192.168.10.205:26379";
        final HostAndPort hap = HostAndPort.parseString(s);

        System.out.println("hap.getHost() = " + hap.getHost());

    }

}