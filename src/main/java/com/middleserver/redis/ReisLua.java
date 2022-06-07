package com.middleserver.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.exceptions.JedisNoScriptException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReisLua {

//    private Logger logger = LoggerFactory.getLogger(RedisDistributeLock.class);

    private static JedisCluster jedisCluster;

    /**
     * lua脚本：判断锁住值是否为当前线程持有，是的话解锁，不是的话解锁失败
     */
    private static final String DISTRIBUTE_LOCK_SCRIPT_UNLOCK_VAL = "if" +
            " redis.call('get', KEYS[1]) == ARGV[1]" +
            " then" +
            " return redis.call('del', KEYS[1])" +
            " else" +
            " return 0" +
            " end";

    private volatile String unlockSha1 = "";

    private static final Long UNLOCK_SUCCESS_CODE = 1L;

    private static final String LOCK_SUCCESS_CODE = "ok";

    public ReisLua(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }


    /**
     * 根据loopTryTime循环重试
     *
     * @param lockKey     锁key
     * @param lockVal     锁值，用于解锁校验
     * @param expiryTime  锁过期时间
     * @param loopTryTime 获取失败时，循环重试获取锁的时长
     * @return 是否获得锁
     */
    public boolean tryLock(String lockKey, String lockVal, long expiryTime, long loopTryTime) {
        Long endTime = System.currentTimeMillis() + loopTryTime;
        while (System.currentTimeMillis() < endTime) {
            if (tryLock(lockKey, lockVal, expiryTime)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据loopTryTime循环重试
     *
     * @param lockKey    锁key
     * @param lockVal    锁值，用于解锁校验
     * @param expiryTime 锁过期时间
     * @param retryTimes 重试次数
     * @param setpTime   每次重试间隔 mills
     * @return 是否获得锁
     */
    public boolean tryLock(String lockKey, String lockVal, long expiryTime, int retryTimes, long setpTime) {
        while (retryTimes > 0) {
            if (tryLock(lockKey, lockVal, expiryTime)) {
                return true;
            }
            retryTimes--;
            try {
                Thread.sleep(setpTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 一次尝试，快速失败。不支持重入
     *
     * @param lockKey    锁key
     * @param lockVal    锁值，用于解锁校验
     * @param expiryTime 锁过期时间 MILLS
     * @return 是否获得锁
     */
    public boolean tryLock(String lockKey, String lockVal, long expiryTime) {
        //相比一般的分布式锁，这里把setNx和setExpiry操作合并到一起，jedis保证原子性，避免连个命令之间出现宕机等问题
        //这里也可以我们使用lua脚本实现
        String result = jedisCluster.set(lockKey, lockVal, "NX", "PX", expiryTime);
        System.out.println("result = " + result);
        return LOCK_SUCCESS_CODE.equalsIgnoreCase(result);
    }

    public static void main(String[] args) {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("10.20.10.100", 6381));
        jedisCluster = new JedisCluster(nodes, 2000, 2000, 3, "qBa39fA&?@-o", new GenericObjectPoolConfig());
        String ss = jedisCluster.set("k3", "v333");
        System.out.println(ss);
        String v3 = jedisCluster.get("k3");
        System.out.println(v3);


        /**
         * unlockSha1 = e9f69f2beb755be68b5e456ee2ce9aadfbc4ebf4
         * slotKey = 20201-cus-test
         */
        // -----------------------------------
        String lockKey = "20201-cus-test";
        ReisLua reisLua = new ReisLua(jedisCluster);
        reisLua.storeScript(lockKey);

        reisLua.tryLock(lockKey, lockKey, 3000 * 20);

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reisLua.tryUnLock(lockKey,lockKey);
    }

    /**
     * 释放分布式锁，释放失败最可能是业务执行时间长于lockKey过期时间，应当结合业务场景调整过期时间
     *
     * @param lockKey 锁key
     * @param lockVal 锁值
     * @return 是否释放成功
     */
    public boolean tryUnLock(String lockKey, String lockVal) {
        List<String> keys = new ArrayList<>();
        keys.add(lockKey);
        List<String> argv = new ArrayList<>();
        argv.add(lockVal);
        try {
            Object result = jedisCluster.evalsha(unlockSha1, keys, argv);
            System.out.println("unlockSh======================a1 = " + result);
            return UNLOCK_SUCCESS_CODE.equals(result);
        } catch (JedisNoScriptException e) {
            //没有脚本缓存时，重新发送缓存
            e.printStackTrace();
            storeScript(lockKey);
            Object result = jedisCluster.evalsha(unlockSha1, keys, argv);
            return UNLOCK_SUCCESS_CODE.equals(result);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 由于使用redis集群，因此每个节点都需要各自缓存一份脚本数据
     *
     * @param slotKey 用来定位对应的slot的slotKey
     */
    public void storeScript(String slotKey) {
        if (!jedisCluster.scriptExists(unlockSha1, slotKey)) {
            //redis支持脚本缓存，返回哈希码，后续可以继续用来调用脚本
            unlockSha1 = jedisCluster.scriptLoad(DISTRIBUTE_LOCK_SCRIPT_UNLOCK_VAL, slotKey);
            System.out.println("unlockSha1 = " + unlockSha1);
            System.out.println("slotKey = " + slotKey);
        }
    }
}
