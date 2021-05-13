package com.person.kotlintest.Lambda;

/**
 * @anthor tr
 * @date 2021/5/11
 * @desc
 */
public class LambdaTest{

    public static void main(String[] args) {
        Runnable r = ()->{
            System.out.println("hello, lambda");
        };
        r.run();
    }

}