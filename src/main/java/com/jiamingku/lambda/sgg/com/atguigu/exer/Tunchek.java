package com.jiamingku.lambda.sgg.com.atguigu.exer;

import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/**
 * Created by jiamingku on 2019/3/25.
 */
public class Tunchek {

    @SuppressWarnings("unchecked")
    public static void reverse(List<?> list) {
        int size = list.size();
        if (size < 100000 || list instanceof RandomAccess) {
            for (int i = 0, mid = size >> 1, j = size - 1; i < mid; i++, j--) {
            }
//				swap(list, i, j);
        } else {
            // instead of using a raw type here, it's possible to capture
            // the wildcard but it will require a call to a supplementary
            // private method
            ListIterator fwd = list.listIterator();
            ListIterator rev = list.listIterator(size);
            for (int i = 0, mid = list.size() >> 1; i < mid; i++) {
                Object tmp = fwd.next();
                fwd.set(rev.previous());
                rev.set(tmp);
            }
        }
    }
}
