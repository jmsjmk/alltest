package com.jiamingku.datastructure.bo;

public class Person implements Comparable<Person> {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int compareTo(Person person) {
        int cop = age - person.getAge();
        if (cop != 0)
            return cop;
        else
            return name.compareTo(person.name);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + age;
        result = 37 * result + name.hashCode();
        return result;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Person))
            return false;
        Person person = (Person) o;
        return (age == person.age) && (name.equals(person.name));
    }

    public String toString() {
        return (age + "{" + name + "}");
    }
}