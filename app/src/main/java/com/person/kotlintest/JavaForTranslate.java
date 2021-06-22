package com.person.kotlintest;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JavaForTranslate {

    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][358]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    private void sss() {

    }

//    public static void main(String[] args) {
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello, lambda");
//            }
//        };
//        r.run();
//    }

//    public static void main(String[] args) {
//        Runnable r = () -> {
//            System.out.println("hello, lambda");
//        };
//        r.run();
//    }

    public static float getSameRate(String str, String target) {
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0 || m == 0) {
            return 0;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + temp);
            }
        }

        return (1 - (float) d[n][m] / Math.max(str.length(), target.length())) * 100F;
    }

//    public static void main(String[] args) {
//        float aaa = getSameRate("凉山", "凉山彝族自治州");
//        System.out.println(aaa);
//
//        System.out.println("凉州彝族自治州".contains("凉州"));
//    }

    static boolean stopRequested = false;
//    public static void main(String[] args) throws InterruptedException {
//        Thread backgroundThread = new Thread(() -> {
//            int i = 0;
//            while (!stopRequested) {
//                i++;
//            }
//        }) ;
//        backgroundThread.start();
//        TimeUnit.MICROSECONDS.sleep(10);
//        stopRequested = true ;
//    }

    public static void main(String[] args) {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();
        System.out.println("classStringArrayList" + classStringArrayList.toString());
        System.out.println("classIntegerArrayList" + classIntegerArrayList.toString());
        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("泛型测试,类型相同");
        }
    }
}
