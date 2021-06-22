package com.person.kotlintest.czai_cxie;

/**
 * @anthor tr
 * @date 2021/6/1
 * @desc
 */
public class Child extends Base {

    public static void funcStatic(String str) {
        System.out.println("Child - funcStatic - String");
    }

    public static void funcStatic(Object obj) {
        System.out.println("Child - funcStatic - Object");
    }

    @Override
    public void func(String str) {
        System.out.println("Child - func - String");
    }

    @Override
    public void func(Object obj) {
        System.out.println("Child - func - Object");
    }
}
