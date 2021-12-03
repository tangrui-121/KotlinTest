package com.person.kotlintest.算法.递归;

/**
 * @anthor tr
 * @date 2021/11/4
 * @desc 例如链表为：1->2->3->4。反转后为 4->3->2->1
 */
public class 反转单链表 {

    static class Node {
        int date;
        Node next;

        public Node(int date) {
            this.date = date;
        }
    }

    /**
     * 1：定义递归函数功能
     * reverseList：反转单链表
     * head：链表头节点
     */
    static Node reverseList(Node head) {
        /**
         * 2：寻找结束条件
         * 最小原则
         * 当链表只有一个节点或者链表为空则直接返回head
         */
        if (head == null || head.next == null) {
            return head;
        }

        /**
         * 3：寻找等价关系
         * 反转下面的节点
         * 4>3>2<1
         * 将2/1指向转化就行了
         */
        // 递归反转 反转子链表
        Node newList = reverseList(head.next);
        // 获取第二个节点
        Node t1 = head.next;
        // 改变1/2节点的指向
        t1.next = head;
        head.next = null;
        // 返回新链表
        return newList;
    }


    public static void main(String[] args) {
        int[] arr = {1, 4, 8, 3, 2};
        Node head = create(arr, 5);
        printList(reverseList(head));
    }

    // 创建链表
    public static Node create(int arr[], int n) {
        if(n == 0 ) {
            return null;
        }
        // 将数组的第一个元素赋给头指针
        Node head =  new Node(arr[0]);
        Node curNode = head;
        // 依次赋值
        for(int  i = 1; i < n;i++) {
            curNode.next = new Node(arr[i]);
            curNode = curNode.next;
        }
        return head;
    }

    // 打印链表
    public static void printList(Node head) {
        Node curNode = head;
        while (curNode != null) {
            System.out.print(curNode.date + "->");
            curNode = curNode.next;
        }
        System.out.print("NULL");
    }
}
