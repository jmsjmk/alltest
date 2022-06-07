package com.middleserver.rpc;

/**
 * 结果通用方法
 * code    ： 返回的状态码 1：正常返回
 * message :  返回有错误的时候的提示信息
 * obj     :  结果值
 * @param <T>
 */
public class RpcResult<T> {

    public final static RpcResult<String> ERROR;

    static {
        ERROR = new RpcResult<>();
        ERROR.setCode("0");
        ERROR.setMessage("请求参数OrderId is null");
        ERROR.setObj(null);
    }

    /**
     * 1 正常--数据可以正常返回
     *
     * 0 参数--请求参数为null,获取订单记录不存在
     *
     */
    private String code;

    /**
     * 返回结果消息
     */
    private String message;

    private T obj;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
//        int ii = 11888888888888888888;
//        Integer i = 11888888888888888888;


    }
}