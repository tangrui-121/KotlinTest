package com.person.kotlintest.算法.递归;

/**
 * @anthor tr
 * @date 2021/11/4
 * @desc 1、1、2、3、5、8、13、21、34....，
 */
public class 斐波那契数列 {

    /**
     * 1.定义递归函数功能
     * f是求第n项的值
     */
    static int f(int n) {
        /**
         * 2.找出递归结束条件
         * f(1) = f(2) = 1
         */
        if (n <= 2) {
            return 1;
        }

        /**
         * 3.寻找等价条件
         * f(n) = f(n-1) + f(n-2)
         */
        return f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(f(9));
    }
}
