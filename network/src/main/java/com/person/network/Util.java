package com.person.network;

class Util {
    static boolean isValid(String v) {
        if (v == null || v.equals("null"))
            return false;
        if (v.trim().isEmpty())
            return false;
        return true;
    }
}
