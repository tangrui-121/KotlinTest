package com.person.kotlintest.czai_cxie;

/**
 * @anthor tr
 * @date 2021/6/1
 * @desc
 */
public class Base {
    public static void funcStatic(String str) {
        System.out.println("Base - funcStatic - String");
    }

    public static void funcStatic(Object obj) {
        System.out.println("Base - funcStatic - Object");
    }

    public void func(String str) {
        System.out.println("Base - func - String");
    }

    public void func(Object obj) {
        System.out.println("Base - func - Object");
    }
}
