package com.person.kotlintest.重载;

/**
 * @anthor tr
 * @date 2021/7/26
 * @desc
 */
public class ParentClass {
    void a() {
    }

    static void b() {
    }
}

interface ITTTTT {
    void c();
}

class ChildClass extends ParentClass implements ITTTTT {
    public ChildClass() {
        super();


    }

    private void test() {
        ParentClass childClass = new ChildClass();
        childClass.a();
        childClass.b();
        ((ITTTTT) childClass).c();
    }


    @Override
    public void c() {

    }
}