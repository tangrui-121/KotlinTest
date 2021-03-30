package com.person.kotlintest;

import android.text.TextUtils;

public class JavaForTranslate {

    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][358]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }
}
