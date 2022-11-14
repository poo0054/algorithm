package com.poo0054.力扣.题目1_10;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 例如，121 是回文，而 123 不是。
 * 示例 1：
 * <p>
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 * <p>
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 * 提示：
 * <p>
 * -231 <= x <= 231 - 1
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/14 14:16
 */
public class 回文数9 {

    @Test
    public void test() {
        System.out.println(isPalindrome(424));
    }

    public boolean isPalindrome(int x) {
        //负数不是
        if (x < 0) {
            return false;
        }
        Deque<Integer> queue = new LinkedList<>();
        int sum = x;
        while (sum > 0) {
            //取出最后一位
            int i = sum % 10;
            queue.add(i);
            sum /= 10;
        }

        while (2 <= queue.size()) {
            if (!queue.removeFirst().equals(queue.removeLast())) {
                return false;
            }
        }
        return true;
    }

}