package com.framework.log.logback;


import java.util.Objects;

/**
 * 异常聚合tag
 * @author yanpengfang
 * create 2019-08-23 1:51 PM
 */
public class ExceptionTag {


    private String traceId;

    private String msg;

    private String exceptionStacks;

    public ExceptionTag(String traceId, String msg, String exceptionStacks) {
        this.traceId = traceId;
        this.msg = msg;
        this.exceptionStacks = exceptionStacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionTag tag = (ExceptionTag) o;
        return Objects.equals(msg, tag.msg) &&
                Objects.equals(exceptionStacks, tag.exceptionStacks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msg, exceptionStacks);
    }
}
