package com.jiamingku.jvm.weakreference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 回收队列中：是整个对象
 */
public class TestPhamtomReference1 {
    private static final List<Object> list = new ArrayList<>();
    private static final ReferenceQueue<MMM1> queue = new ReferenceQueue<>();

    public static void main(String[] args) throws Exception {

        MMM1 M = new MMM1();
        MMT<MMM1> mmmPhamtomReference = new MMT<MMM1>(M, queue);
        System.out.println("mmmPhamtomReference = " + mmmPhamtomReference);
        System.out.println(mmmPhamtomReference.get());
        M = null;
        new Thread(() -> {
            while (true) {
                list.add(new byte[1024 * 1024*4]);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("mmmPhamtomReference = " + mmmPhamtomReference.get());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends MMM1> r = queue.poll();
                if (r != null) {
                    System.out.println("----虚拟引用对象-----jvm回收了 " + r);
                    System.out.println("----虚拟引用对象-----jvm回收了 " + r.get());
                    System.out.println("r = " + r);
                }
            }
        }).start();

//        System.out.println(M);
        System.in.read();
    }

}


class MMM1 {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize MMMMM");
    }
}

class MMT<MMM1> extends WeakReference<MMM1> {
    private String name = "1100";
    private String age = "age";


    public MMT(String name, String age, MMM1 mm1) {
        super(mm1);
        this.name = name;
        this.age = age;
    }
    public MMT(MMM1 referent) {
        super(referent);
    }

    public MMT(MMM1 referent, ReferenceQueue<? super MMM1> q) {
        super(referent, q);
    }

    @Override
    public String toString() {
        return "MMT{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}