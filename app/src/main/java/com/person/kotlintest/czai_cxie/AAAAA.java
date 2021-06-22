package com.person.kotlintest.czai_cxie;

import java.util.UUID;

/**
 * @anthor tr
 * @date 2021/6/1
 * @desc
 */
public class AAAAA {

    private int value1 = 0;
    private String value2 = "";

    public void setAA0() {

    }

    public void setAA0(int a) {
        this.value1 = a;
    }

    public void setAA0(String a) {
        this.value2 = a;
    }

    public static String getToken(Child child){
        return UUID.randomUUID()
                .toString()
                .replace("-","")
                + child.hashCode();
    }
}
