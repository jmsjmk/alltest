package com.jiamingku.thead.axman.impl;

import com.jiamingku.thead.axman.ISmartAxmanService;
import com.jiamingku.thead.axman.result.RealResult;
import com.jiamingku.thead.axman.result.Result;

public class SmartAxmanServiceImp implements ISmartAxmanService {

    /**
     * 获取结果
     * @param count
     * @param c
     * @return
     */
    public Result resultTest(int count, char c) {
        char[] buf = new char[count];  
        for (int i = 0; i < count; i++) {  
            buf[i] = c;  
            try {  
                Thread.sleep(100); // 模拟耗时操作  
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }  
        return new RealResult(new String(buf));
    }  
  
    public void noResultTest(String str) {  
        try {  
            System.out.println("displayString :" + str);  
            Thread.sleep(10);  
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }  
}  