package com.middleserver.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class RedisSentinelTest {

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