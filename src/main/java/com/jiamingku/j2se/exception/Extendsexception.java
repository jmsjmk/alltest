package com.jiamingku.j2se.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * test
 * Created by jiamingku on 2018/5/22.
 */
public class Extendsexception {

    public void a() throws Exception {
        new NullPointerException("DD");
    }

    public void a1() throws Exception {
        new NullPointerException("DD");
    }

    public void a2() {
        new NullPointerException("DD");
    }

    /**
     * 1.子类抛出异常大于父类的话，会出现错误
     * <p>
     * 2.子类可以不抛出异常，重写与异常没啥关系
     *
     * @throws IOException
     */
    public void a3() throws IOException {

    }

    /**
     * 子类抛出异常大于父类的话，会出现错误
     *
     * @throws IOException
     */
    public void a4() throws FileNotFoundException {
        // 不通大于IOException
        // throw new IOException("d");
    }
}
