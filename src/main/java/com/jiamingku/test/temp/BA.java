package com.jiamingku.test.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface BA {
}

class B1 implements BA, Comparable<B1> {

    @Override
    public int compareTo(B1 o) {
        return 0;
    }
}


class B2 extends B1 {

    public static void main(String[] args) {

        List<B2> list = new ArrayList<>();

        Collections.sort(list);

        List<B3> list1 = new ArrayList<>();

        Collections.sort(list1);
    }
}
class B3 extends B1 implements Comparable<B1> {

}