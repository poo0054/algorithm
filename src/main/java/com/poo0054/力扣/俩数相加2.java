package com.poo0054.力扣;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例 1：
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * 提示：
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/10 10:37
 */
public class 俩数相加2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        //超出的
        int overflow = sum / 10;
        //个位数
        sum %= 10;
        ListNode listNode = new ListNode(sum);
        ListNode currentNode = listNode;
        l1 = l1.next;
        l2 = l2.next;
        while (null != l1 || null != l2) {
            sum = 0;
            boolean net1 = null != l1;
            if (net1) {
                sum += l1.val;
            }

            boolean net2 = null != l2;
            if (net2) {
                sum += l2.val;
            }
            //需要加上 溢出的
            sum += overflow;
            int netSum = sum % 10;
            ListNode nextNode = new ListNode(netSum);
            currentNode.next = nextNode;
            //当前值溢出的
            overflow = sum / 10;
            currentNode = nextNode;
            if (net1) {
                l1 = l1.next;
            }
            if (net2) {
                l2 = l2.next;
            }

        }

        if (0 != overflow) {
            currentNode.next = new ListNode(overflow);
        }
        return listNode;
    }


    static class ListNode {
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

