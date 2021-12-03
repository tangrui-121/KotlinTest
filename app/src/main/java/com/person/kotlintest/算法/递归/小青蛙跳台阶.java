package com.person.kotlintest.算法.递归;

/**
 * @anthor tr
 * @date 2021/11/4
 * @desc 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class 小青蛙跳台阶 {

    /**
     * 1.定义递归函数功能
     * f是跳上n级台阶有多少种跳法
     */
    static int f(int n) {
        /**
         * 2.找出递归结束的条件
         * 当只有一级的时候只有一种跳法
         */
//        if (n == 1) {
//            return 1;
//        }
        if (n <= 2) {
            return 1;
        }

        /**
         * 3.寻找函数的等价关系式
         * 一次可以跳一个或者两个。所以在跳转一次之后，则有n-1或者n-2种跳法
         * 则所有跳法是n-1 + n-2
         *
         * but n-2 时 会有f(0)，则进入死循环
         * so 改变结束递归条件
         */
        return f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(f(8));
    }
}
