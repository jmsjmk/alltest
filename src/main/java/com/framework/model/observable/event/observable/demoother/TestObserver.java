package com.framework.model.observable.event.observable.demoother;

public class TestObserver {

    public static void main(String[] args) {

        Teacher teacher=new Teacher();
        com.framework.model.observable.event.observable.demoother.Student zhangSan=new com.framework.model.observable.event.observable.demoother.Student("张三", teacher);
        com.framework.model.observable.event.observable.demoother.Student LiSi=new com.framework.model.observable.event.observable.demoother.Student("李四", teacher);
        com.framework.model.observable.event.observable.demoother.Student WangWu=new com.framework.model.observable.event.observable.demoother.Student("王五", teacher);
        
        teacher.setHomework("第二页第六题");
//        teacher.setHomework("第三页第七题");
//        teacher.setHomework("第五页第八题");
    }
}