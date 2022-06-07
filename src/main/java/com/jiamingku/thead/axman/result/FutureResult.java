package com.jiamingku.thead.axman.result;

/**
 * 未来结果,线程不能立马获取结果，
 */
public class FutureResult extends Result {

    /**
     * 针对于本例子，他返回的是realResut
     */
    private Result result;  
    private boolean completed;  
  
    public synchronized void setResult(Result result) {  
        this.result = result;  
        this.completed = true;  
        this.notifyAll();  
    }

    @Override
    public synchronized Object getResultValue() {  
        while (!this.completed) {  
            try {  
                this.wait();  
            } catch (Throwable t) {}  
        }  
        return this.result.getResultValue();  
    }  
}  