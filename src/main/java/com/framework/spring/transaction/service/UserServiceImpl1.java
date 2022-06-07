package com.framework.spring.transaction.service;

import com.framework.spring.transaction.t1.User;
import com.framework.spring.transaction.t1.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by jiamingku on 2020/4/6.
 */
//@Service
//@SuppressWarnings("all")
public class UserServiceImpl1 implements UserService1 {

//    @Resource
    UserMapper userMapper;


    /**
     * 确实开启了新的事物===同样sqlsession也跟着变换了。相当于打开了一个新的连接。
     *
     * 如果不是new 他们公用一个session 也就是一个数据库连接
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int insertUser(User user) {

//        if (true) {
//            throw  new NullPointerException("DD");
//        }
        return userMapper.insert(user);
    }
}
