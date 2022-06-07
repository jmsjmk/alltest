package com.framework.model.observable.event.propertyeditorsupporttest;

import com.jiamingku.j2se.casttype.B;
import com.jiamingku.j2se.refelct.bo.DDDDD;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BDDDDDD extends PropertyEditorSupport {

    public BDDDDDD() {

        super();
        System.out.println(" = ");
    }
    
    private String a;

    public BDDDDDD(String a) {
        this.a = a;
    }

    public static void main(String[] args) throws Exception {


        Constructor<BDDDDDD> declaredConstructor = BDDDDDD.class.getDeclaredConstructor();
       
        declaredConstructor.newInstance();

        
        
    }



}
