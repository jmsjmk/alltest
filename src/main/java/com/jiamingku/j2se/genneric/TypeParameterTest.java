package com.jiamingku.j2se.genneric;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 这个属于实战的东西. 理解看懂就行了.
 * <p>
 * https://www.cnblogs.com/xiaomiganfan/p/5390252.html
 */
public class TypeParameterTest {
    //第一种声明：简单，灵活性低
    public static <T extends Comparable<T>> void mySort1(List<T> list) {
        Collections.sort(list);
    }

    //第二种声明：复杂，灵活性高
    public static <T extends Comparable<? super T>> void mySort2(List<T> list) {
        Collections.sort(list);
    }

    public static void main(String[] args) {
        //在这个方法中要创建一个 Animal List 和一个 Dog List，然后分别调用两个排序方法。
    }

    @Test
    public void test1() {
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal(25));
        animals.add(new Dog(35));

        List<Dog> dogs = new ArrayList<Dog>();
        dogs.add(new Dog(5));
        dogs.add(new Dog(18));

        mySort1(animals);
        // ---
        // mySort1(dogs);
        mySort2(dogs);
    }
}

class Animal implements Comparable<Animal> {
    protected int age;

    public Animal(int age) {
        this.age = age;
    }

    //使用年龄与另一实例比较大小
    @Override
    public int compareTo(Animal other) {
        return this.age - other.age;
    }
}

class Dog extends Animal {
    public Dog(int age) {
        super(age);
    }
}