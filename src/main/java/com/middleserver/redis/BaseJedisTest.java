package com.middleserver.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * jedis(源码分析)
 * 真正操作redis都是二进制的操作,所以有jedis->BinaryJedis 真正操作的是BJ进行操作的
 * 1)命令分类
 * JedisCommands,
 * MultiKeyCommands,
 * AdvancedJedisCommands,
 * ScriptingCommands,
 * BasicCommands
 * <p></p>
 * <p>
 * <p>
 * 2)并且这些命令都有对应的二进制版本
 * BinaryJedisCommands,
 * MultiKeyBinaryCommands
 * AdvancedBinaryJedisCommands
 * BinaryScriptingCommands
 * <p></p>
 * <p>
 * 3)jedis 操作都是通过client 进行操作的，这个属性在 BinaryJedis ，里面的一个属性，其实里面就是socket
 * <p>
 * 4)所有发送redis的命令包括网络命令等都是通过二进制的方式发送的，
 * <p>
 * 5) 事物与pipelined 只是对client 一个包装，从代码上面看，实现是一样的
 */
public class BaseJedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("122.51.100.64", 9527);
        String s = jedis.ping();
        jedis.multi();
        String r = jedis.set("jedis", "你好");
        System.out.println(r);
        byte[] b = "你好".getBytes(StandardCharsets.UTF_8);
        for (byte b1 : b) {
            System.out.println(b1);
        }
    }


    public void test() {
        Jedis jedis = new Jedis("122.51.100.64", 9527);
        String s = jedis.ping();
        jedis.multi();
        String r = jedis.set("jedis", "你好");
        System.out.println(r);
        byte[] b = "你好".getBytes(StandardCharsets.UTF_8);
        for (byte b1 : b) {
            System.out.println(b1);
        }
    }




    /**
     * jedis:想使用事物，就一定要开启：jedis.multi();
     * 这时候使用的是:transaction 里面就不检查了
     * <p>
     * jedis:想使用pipeline，就必须的pipelined.然后使用
     * 返回的Pipeline 进行操作
     * <p>
     * 正常使用jedis 会检查这两个状态.
     * <p>
     * mutil 发送时候也是一起发送的。
     */
    @Test
    public void redisMutilTest1() {
        Jedis jedis = new Jedis("122.51.100.64", 9527);
        Transaction transaction = jedis.multi();
        try {
            Response<String> r = transaction.set("1", "1");
            transaction.set("1", "1");
            transaction.set("k", "1");
            transaction.set("k1", "k2");
            transaction.set("3", "1");
            transaction.incr("k");
            transaction.incr("k1");
            List<Object> list = transaction.exec();
//            System.out.println(list);
            for (Object o : list) {
                System.out.println(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testPipeline() {
        Jedis jedis = new Jedis("122.51.100.64", 9527);
        try {
            Pipeline pipelined = jedis.pipelined();
            pipelined.set("addr0", "chongqing");
            pipelined.set("addr1", "chongqing");
            pipelined.set("addr2", "chongqing");
            pipelined.set("addr3", "chongqing");
            pipelined.set("addr4", "chongqing");
            List<Object> list = pipelined.syncAndReturnAll();
            for (Object object : list) {
                System.out.println(object);
            }
        } finally {

        }
    }
}
