package com.jiamingku.j2se.exception;

/**
 * Created by jiamingku on 2020/6/12.
 */
public class MyException extends RuntimeException {

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
