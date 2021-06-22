package com.person.kotlintest;

import java.util.LinkedList;

/**
 * @anthor tr
 * @date 2021/6/8
 * @desc
 */
public class StackDemo {
    LinkedList linkList = new LinkedList<Object>();

    public void push(Object object) {
        linkList.addFirst(object);
    }

    public boolean isEmpty() {
        return linkList.isEmpty();
    }

    public void clear() {
        linkList.clear();
    }

    // 移除并返回此列表的第一个元素
    public Object pop() {
        if (!linkList.isEmpty())
            return linkList.removeFirst();
        return "栈内无元素";
    }

    public int getSize() {
        return linkList.size();
    }

    public static void main(String[] args) {
        StackDemo myStack = new StackDemo();
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }
}
