package com.person.kotlintest.锁;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @anthor tr
 * @date 2021/7/22
 * @desc
 */
public class Account {
    /**
     * 账户类
     */
    private static volatile int count = 100;

    public static synchronized void add(int m){
        String name = Thread.currentThread().getName();
        System.out.println("对象锁添加" + m + "钱，" + name + "添加后：" + (count+=m));
    }

    public static synchronized void mul(int m){
        String name = Thread.currentThread().getName();
        System.out.println("对象锁减少" + m + "钱，" + name + "消费后：" + (count-=m));
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4);
        Account suo = new Account();
        Account suo1 = new Account();
        for (int i = 0; i < 4; i++) {
            int n = i + 1;
            int j = n * 3;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * (new Random().nextInt(8)));
                        System.out.println("线程" + n + "准备好了");
                        barrier.await();
                        if (n == 1 || n == 3)
                            suo.add(j);//1,3
                        else
                            suo1.mul(j);//2,4
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, "线程" + i).start();
        }
    }
}
