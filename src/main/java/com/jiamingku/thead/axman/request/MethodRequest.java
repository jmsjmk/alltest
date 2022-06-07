package com.jiamingku.thead.axman.request;

import com.jiamingku.thead.axman.impl.SmartAxmanServiceImp;
import com.jiamingku.thead.axman.result.FutureResult;

public abstract class MethodRequest {
  
    protected final SmartAxmanServiceImp serviceImp;
    protected final FutureResult future;
  
    protected MethodRequest(SmartAxmanServiceImp serviceImp, FutureResult future) {  
        this.serviceImp = serviceImp;  
        this.future = future;  
    }  
  
    public abstract void execute();  
  
}  
  

  
