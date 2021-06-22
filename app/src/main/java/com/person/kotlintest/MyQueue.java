package com.person.kotlintest;

import java.util.LinkedList;

/**
 * @anthor tr
 * @date 2021/6/8
 * @desc
 */
//队列定义
//队列（Queue）是只允许在一端进行插入，而在另一端进行删除的运算受限的线性表
//（1）允许删除的一端称为队头（Front）。
//（2）允许插入的一端称为队尾（Rear）。
//（3）当队列中没有元素时称为空队列。
//（4）队列亦称作先进先出（First In First Out）的线性表，简称为FIFO表。

public class MyQueue {
    LinkedList<Object> linkedList = new LinkedList<Object>();

    //队尾插
    public void put(Object o) {
        linkedList.addLast(o);
    }

    // 队头取 取完并删除
    public Object get() {
        if (!linkedList.isEmpty())
            return linkedList.removeFirst();
        else
            return "";
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public int size() {
        return linkedList.size();
    }

    public void clear() {
        linkedList.clear();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.put(1);
        myQueue.put(2);
        myQueue.put(3);
        System.out.println(myQueue.get());
        System.out.println(myQueue.get());
        System.out.println(myQueue.get());
    }
}