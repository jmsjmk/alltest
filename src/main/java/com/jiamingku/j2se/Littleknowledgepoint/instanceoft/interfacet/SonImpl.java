package com.jiamingku.j2se.Littleknowledgepoint.instanceoft.interfacet;

/**
 * Created by jiamingku on 16/11/24.
 */
public class SonImpl implements Son {

    @Override
    public void a() {

    }

    public static void main(String[] args) {
        Son p = new SonImpl();

        if (p instanceof SonImpl) {
            System.out.println("is SonImpla");
        }

        if (p instanceof Parent) {
            System.out.println("is Parent");
        }
        if (p instanceof Son) {

            System.out.println("is Sona");
        }
    }
}
