package com.person.kotlintest.leetcode;

/**
 * @anthor tr
 * @date 2021/5/19
 * @desc
 */
public class Code2 {

//    给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
//    请你将两个数相加，并以相同形式返回一个表示和的链表。
//
//    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

//    输入：l1 = [2,4,3], l2 = [5,6,4]
//    输出：[7,0,8]
//    解释：342 + 465 = 807.
//
//    输入：l1 = [0], l2 = [0]
//    输出：[0]
//
//    输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//    输出：[8,9,9,9,0,0,0,1]

    public static void main(String[] args) {

    }

    int carry = 0;//记录进位

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //定义终止条件，当l1 l2 指针都为null，且进位为0  返回null
        if (l1 == null && l2 == null && carry == 0) return null;

        //当有一条链表为null 且 进位为0，next指针直接指向另外一条链表返回
        if (l1 != null && l2 == null && carry == 0) {
            return l1;
        } else if (l2 != null && l1 == null && carry == 0) {
            return l2;
        }

        //两链表指针位置上的数字相加 再加上进位
        int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
        //计算进位
        carry = sum / 10;
        //计算链表的value
        int value = sum % 10;
        ListNode node = new ListNode(value);

        //递归相加计算node
        node.next = addTwoNumbers((l1 == null ? null : l1.next), (l2 == null ? null : l2.next));

        return node;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        return null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
