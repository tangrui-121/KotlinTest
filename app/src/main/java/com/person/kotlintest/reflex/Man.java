package com.person.kotlintest.reflex;

/**
 * @anthor tr
 * @date 2021/5/20
 * @desc
 */
public class Man extends Person {

    private String address;
    public int phoneNum;

    public Man() {
    }

    public Man(String address) {
        this.address = address;
    }

    private Man(String address, int phoneNum) {
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    private void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }
}
