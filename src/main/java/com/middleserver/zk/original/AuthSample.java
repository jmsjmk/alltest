package com.middleserver.zk.original;

import com.middleserver.zk.ZkConstance;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * ZK的权限是node维度
 * https://www.cnblogs.com/qlqwjy/p/10517231.html
 * <p>
 * setAcl /t6  digest:user:6DY5WhzOfGsWQ1XFuIyzxkpwdPo=:crwda  #这种授权方式
 * 1)addauth digest user:123456                                #这样是可以访问
 * <p>
 * setAcl /t4 auth:qlq:cdwra　　                                #授予权限
 * 1)addauth digest qlq:111222                                 #这样也可以访问
 * <p>
 * 加密串授权与明文授权都可以::::认证时候指定名文的密码就行
 * <p>
 * https://blog.csdn.net/hekf2010/article/details/78844609 ===关于权限的配置客户端使用权限进行连接
 */
public class AuthSample {
    final static String PATH = "/test1-test1/t4";

    public static void main(String[] args) throws Exception {
        ZooKeeper zookeeper = new ZooKeeper(ZkConstance.CONNECTION_STR, 50000, null);
        /**
         * 这句话保证你节点的数据在访问的时候需要权限认证：addauth digest foo:true
         */
        zookeeper.addAuthInfo("digest", "foo:true".getBytes());
        zookeeper.create(PATH, "init".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
        Thread.sleep(Integer.MAX_VALUE);
    }
}