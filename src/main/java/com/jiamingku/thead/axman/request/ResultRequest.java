package com.jiamingku.thead.axman.request;

import com.jiamingku.thead.axman.impl.SmartAxmanServiceImp;
import com.jiamingku.thead.axman.result.FutureResult;
import com.jiamingku.thead.axman.result.Result;

public class ResultRequest extends MethodRequest {
    /**
     * 以下两个只是为了打印消息而设定的。
     */
    private final int count;  
    private final char c;  
  
    public ResultRequest(SmartAxmanServiceImp serviceImp,
            FutureResult future,
            int count,   
            char c) {  
        super(serviceImp, future);  
        this.count = count;  
        this.c = c;  
    }  
  
    public void execute() {
        /**
         * 返回realResult对象
         */
        Result result = serviceImp.resultTest(this.count, this.c);
        this.future.setResult(result);  
    }
}