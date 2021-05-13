package com.person.kotlintest.Lambda;

/**
 * @anthor tr
 * @date 2021/5/11
 * @desc
 */
public class LambdaTest2{

    public static void main(String[] args) {
        Runnable r = new Runnable(){
            @Override
            public void run(){
                System.out.println("hello, lambda");
            }
        };
        r.run();
    }

}