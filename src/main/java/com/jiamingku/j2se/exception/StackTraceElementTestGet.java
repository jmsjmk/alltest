package com.jiamingku.j2se.exception;

public class StackTraceElementTestGet {

    private StackTraceElementBase stackTraceElementBase;

    public StackTraceElementBase getStackTraceElementBase() {
        return stackTraceElementBase;
    }

    public void setStackTraceElementBase(StackTraceElementBase stackTraceElementBase) {
        this.stackTraceElementBase = stackTraceElementBase;
    }

    public static void main(String[] args) {

        StackTraceElementTestGet stackTraceElementTestGet = new StackTraceElementTestGet();
        StackTraceElementBase stackTraceElementBase = new StackTraceElementBase();
        stackTraceElementTestGet.setStackTraceElementBase(stackTraceElementBase);
        /**
         * 仔细观察者 是方法的两成调用但是stack信息少了一方法，因为在get之后，就方法结素，跳出了一个栈信息
         */
        stackTraceElementTestGet.getStackTraceElementBase().a();
    }
}
