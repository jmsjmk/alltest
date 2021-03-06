package com.jiamingku.thead.axman.result;

/**
 * 获取真正的结果
 */
public class RealResult extends Result {
  
    private final Object resultValue;  
  
    public RealResult(Object resultValue) {  
        this.resultValue = resultValue;  
    }  

    @Override
    public Object getResultValue() {  
        return this.resultValue;  
    }  
}  