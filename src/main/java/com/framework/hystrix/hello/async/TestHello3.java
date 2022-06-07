package com.framework.hystrix.hello.async;

import com.framework.hystrix.hello.HelloWorldHystrixCommand;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiamingku on 2018/12/21.
 */
@SuppressWarnings("all")
public class TestHello3 {

    public static void main(String[] args) throws Exception {


        HelloWorldHystrixCommand helloWorldHystrixCommand = new HelloWorldHystrixCommand("World");
        //注册观察者事件拦截
        Observable<String> fs = new HelloWorldHystrixCommand("World").observe();
        //注册结果回调事件
        fs.subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                //执行结果处理,result 为HelloWorldCommand返回的结果
                //用户对结果做二次处理.
                System.out.println("result = " + result);
            }
        });
        //注册完整执行生命周期事件
        fs.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // onNext/onError完成之后最后回调
                System.out.println("execute onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                // 当产生异常时回调
                System.out.println("onError " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                // 获取结果后回调
                System.out.println("onNext: " + v);
            }
        });

//        fs.subscribe()
        System.out.println("fs = " );

        helloWorldHystrixCommand.execute();



    }
}
