package com.jiamingku.thead.axman.custor;

import com.jiamingku.thead.axman.ISmartAxmanService;

public class NoResultInvokeThread extends Thread {
  
    private final ISmartAxmanService service;
  
    public NoResultInvokeThread(String name, ISmartAxmanService service) {  
        super(name);  
        this.service = service;  
    }  
  
    public void run() {  
        try {  
            int i = 0;  
            while (true) {  
                String s = Thread.currentThread().getName() + i++;  
                service.noResultTest(s);  
                Thread.sleep(20);  
            }  
        }  
        catch (Throwable t) {  
        }  
    }  
}  