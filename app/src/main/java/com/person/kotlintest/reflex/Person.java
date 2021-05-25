package com.person.kotlintest.reflex;

/**
 * @anthor tr
 * @date 2021/5/20
 * @desc
 */
public class Person {
    public int age;
    private String name;

    public Person() {
    }

    public Person(int age) {
        this.age = age;
    }

    private Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}