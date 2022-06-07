package com.jiamingku.jvm.weakreference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class TestPhamtomReference {
    private static final List<Object> list = new ArrayList<>();
    private static final ReferenceQueue<MMM> queue = new ReferenceQueue<>();

    public static void main(String[] args) throws Exception {
        //PhantomReference<MMM> mmmPhamtomReference = new PhantomReference<MMM>(new MMM(), queue);
        WeakReference<MMM> mmmPhamtomReference = new WeakReference<MMM>(new MMM(), queue);
        System.out.println("mmmPhamtomReference = " + mmmPhamtomReference);
        System.out.println(mmmPhamtomReference.get());

        new Thread(() -> {
            while(true) {
                list.add(new byte[1024 * 1024]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("mmmPhamtomReference = " + mmmPhamtomReference.get());
            }
        }).start();

        new Thread(() -> {
            while(true) {
                Reference<? extends MMM> r = queue.poll();
                if (r != null) {
                    System.out.println("----虚拟引用对象-----jvm回收了 " + r);
                    System.out.println("----虚拟引用对象-----jvm回收了 " + r.get());
                }
            }
        }).start();
    }

}


 class MMM {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize MMMMM");
    }
}

