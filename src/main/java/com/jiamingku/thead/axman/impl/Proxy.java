package com.jiamingku.thead.axman.impl;

import com.jiamingku.thead.axman.ISmartAxmanService;
import com.jiamingku.thead.axman.Scheduler;
import com.jiamingku.thead.axman.request.NoResultRequest;
import com.jiamingku.thead.axman.request.ResultRequest;
import com.jiamingku.thead.axman.result.FutureResult;
import com.jiamingku.thead.axman.result.Result;

public class Proxy implements ISmartAxmanService {

    // 这个调度者就是代理用来控制实现类的调用的
    private final Scheduler scheduler;

    // 这个实现类完成接口方法的真正实现(其实相当于一种组合)
    private final SmartAxmanServiceImp serviceImp;

    public Proxy(Scheduler scheduler, SmartAxmanServiceImp serviceImp) {
        this.scheduler = scheduler;
        this.serviceImp = serviceImp;
    }

    /**
     * 这个方法只是向队列中扔消息
     *
     * @param count
     * @param c
     * @return
     */
    @Override
    public Result resultTest(int count, char c) {

        FutureResult futrue = new FutureResult();
        // 如果弱化代理的调度控制，这里就应该象下面注释掉的代码：  
        // doBeforeImpResultTest();
        // serviceImp.ResultTest();  
        // doAfterImpResultTest();  
        // 这里进行了更复杂的控制，所以把实现和调度传递给ResultRequest来执行。
        /**
         * count ，c 决定真正的打印结果
         */
        this.scheduler.invoke(new ResultRequest(serviceImp, futrue, count, c));
        return futrue;
    }

    /**
     * 代理类：就是将消息扔到调度器中，只是向里面扔消息

     * @param str
     */
    @Override
    public void noResultTest(String str) {
        // 同上
        /**
         * str 就是真正的打印输出结果
         */
        this.scheduler.invoke(new NoResultRequest(this.serviceImp, str));
    }
}  