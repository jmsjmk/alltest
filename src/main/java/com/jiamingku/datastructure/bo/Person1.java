package com.jiamingku.datastructure.bo;

public class Person1 implements Comparable<Person1> {
    private int age ;
    private String name ;

    public Person1(int age, String name) {
       this . age = age;
       this . name = name;
    }

    public int compareTo(Person1 person) {
       int cop = age - person.getAge();
       if (cop != 0)
           return cop;
       else
           return name .compareTo(person. name );
    }

    public int getAge() {
       return age ;
    }

    public String getName() {
       return name ;
    }

    public int hashCode() {
       int result = 17;
       result = 37 * result + age ;
       result = 37 * result + name .hashCode();
       return result;
    }

    public boolean equals(Object o) {
       if (!(o instanceof Person1))
           return false ;
       Person1 person = (Person1) o;
       return ( age == person. age ) && ( name .equals(person. name ));
    }

    public String toString() {
       return ( age + "{" + name + "}" );
    }
}