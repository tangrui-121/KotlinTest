package com.person.kotlintest.gc;

import android.os.Handler;

/**
 * @anthor tr
 * @date 2021/6/15
 * @desc
 */
public class GCTest {

    boolean checkedout = false;

    GCTest(boolean value) {
        checkedout = value;
    }

    void checkin() {
        checkedout = false;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (checkedout) {
                System.out.println("error: checked out");
            }
        } finally {
            try {
                super.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        GCTest gcTest1 = new GCTest(true);

        gcTest1.checkin();// Proper cleanup:

        new GCTest(true);// Drop the reference, forget to clean up:

        System.gc();// Force garbage collection & finalization:

    }
}
