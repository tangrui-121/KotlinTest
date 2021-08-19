package com.person.kotlintest.重载;

/**
 * @anthor tr
 * @date 2021/7/19
 * @desc
 */
public class Test {
    public static void main(String[] args) {
        Object obj = new Object();
        Object str = new String();
        

        Base base = new Base();
        Base child1 = new Child();
        Child child2 = new Child();

        base.funcStatic(obj);//Base - funcStatic - Object
        child1.funcStatic(obj);//Child - funcStatic - Object
        child2.funcStatic(obj);//Child - funcStatic - Object

        base.func(str);//Base - func - String
        child1.func(str);//Child - func - String
        child2.func(str);//Child - func - String

//        输出
//        Base - funcStatic - Object
//        Base - funcStatic - Object
//        Child - funcStatic - Object
//        Base - func - Object
//        Child - func - Object
//        Child - func - Object

        //Base为变量base1的静态类型，Child为实际类型
        //静态类型：引用变量的类型，在编译期确定，无法改变
        //实际类型：实例对象的类型，在编译期无法确定，需在运行期确定，可以改变
        Base base1 = new Child();
    }
}

class Base {
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

class Child extends Base {

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
