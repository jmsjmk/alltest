package com.jiamingku.thead.axman.custor;

import com.jiamingku.thead.axman.ISmartAxmanService;
import com.jiamingku.thead.axman.result.Result;

public class ResultInvokeThread extends Thread {
    private final ISmartAxmanService service;
    private final char c;  
  
    public ResultInvokeThread(String name, ISmartAxmanService service) {  
        this.service = service;  
        this.c = name.charAt(0);  
    }  
  
    public void run() {  
        try {  
            int i = 0;  
            while (true) {
                // 代理类返回的对象
                Result result = this.service.resultTest(i++, c);
                Thread.sleep(10);  
                String value = (String) result.getResultValue();  
                System.out.println("------------------------------------------  "+Thread.currentThread().getName()
                        + " value = " + value);  
            }  
        } catch (Throwable t) {}  
    }  
  
}  
