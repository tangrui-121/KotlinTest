package com.person.kotlintest.czai_cxie;

/**
 * @anthor tr
 * @date 2021/6/1
 * @desc
 */
public class Test{
    public static void main(String[] args){
        Object obj = new Object();
        Object str = new String();

        Base base = new Base();
        //Base为变量child1的静态类型，Child为实际类型
        //静态类型：引用变量的类型，在编译期确定，无法改变
        //实际类型：实例对象的类型，在编译期无法确定，需在运行期确定，可以改变
        Base child1 = new Child();
        Child child2 = new Child();

        base.funcStatic(obj);//"Base - funcStatic - Object"
        child1.funcStatic(obj);//"Base - funcStatic - Object"
        child2.funcStatic(obj);//"Child - funcStatic - Object"

        base.func(str);//"Base - func - String"
        child1.func(str);//"Child - func - Object"
        child2.func(str);//"Child - func - Object"
    }
}
