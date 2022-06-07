package javax.jms;

import java.util.*;

public class TreeMapDemo {
    public static void main(String[] args) {
// creating tree map

        TreeMap treemap = new TreeMap();

// populating tree map

        treemap.put(2, "two");

        treemap.put(1, "one");

        treemap.put(3, "three");

        treemap.put(6, "six");

        treemap.put(5, "five");


        System.out.println(treemap);
        System.out.println(" ===== " );

// putting values in navigable map

        NavigableMap nmap = treemap.descendingMap();

        System.out.println("Checking value");

        System.out.println("Navigable map values: " + nmap);

    }

}
