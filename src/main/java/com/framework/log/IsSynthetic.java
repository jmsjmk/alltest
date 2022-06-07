package com.framework.log;

import java.lang.reflect.Constructor;

public class IsSynthetic {
    public static void main(String[] args) {
        Constructor[] constructors = SampleClass.class.getConstructors();

        for (Constructor constructor : constructors) {
            System.out.println("constructor = " + constructor.isSynthetic());

        }
//        System.out.println(constructors[1].isSynthetic());
    }
}

class SampleClass {
    private String sampleField;

    public SampleClass() {
    }

    public SampleClass(String sampleField) {
        this.sampleField = sampleField;
    }

    public String getSampleField() {
        return sampleField;
    }

    public void setSampleField(String sampleField) {
        this.sampleField = sampleField;
    }
}

