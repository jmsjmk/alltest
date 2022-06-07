package com.jiamingku.thead.axman.request;

import com.jiamingku.thead.axman.impl.SmartAxmanServiceImp;

public class NoResultRequest extends MethodRequest {
    private String str;  
  
    public NoResultRequest(SmartAxmanServiceImp serviceImp, String str) {
        super(serviceImp, null);  
        this.str = str;  
    }  
  
    public void execute() {  
        this.serviceImp.noResultTest(str);  
    }  
}  