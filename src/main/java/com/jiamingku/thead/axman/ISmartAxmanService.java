package com.jiamingku.thead.axman;

import com.jiamingku.thead.axman.result.Result;

/**
 * 服务接口
 */
public interface ISmartAxmanService {
    /**
     * 有返回值
     * @param count
     * @param c
     * @return
     */
    Result resultTest(int count, char c);

    /**
     * 没有返回值
     * @param str
     */
    void noResultTest(String str);  
}  
