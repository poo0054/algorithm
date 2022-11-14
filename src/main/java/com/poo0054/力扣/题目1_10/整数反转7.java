package com.poo0054.力扣.题目1_10;

import org.junit.Test;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * 示例 1：
 * <p>
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 * <p>
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 * <p>
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 * <p>
 * 输入：x = 0
 * 输出：0
 * 提示：
 * <p>
 * -231 <= x <= 231 - 1
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/14 10:22
 */
public class 整数反转7 {
    @Test
    public void test() {
        System.out.println(reverse(1463847412));
    }

    public int reverse(int x) {
        int number = 0;
        //取出绝对值
        int abs = Math.abs(x);
        while (0 < abs) {
            //最后一位
            int i = abs % 10;
            //还有值需要加
            boolean b = (number > Integer.MAX_VALUE / 10) || (number == Integer.MAX_VALUE / 10 && i > Integer.MAX_VALUE % 10);
            if (b) {
                return 0;
            }

            //加上当前位数 其余向前移动
            number = number * 10 + i;
            //去掉最后一位
            abs = abs / 10;
        }
        //加上符号
        return x > 0 ? number : -number;
    }

    public int reverse1(int x) {
        String s = x + "";
        StringBuffer buffer = new StringBuffer();
        for (int i = s.length() - 1; i > 0; i--) {
            buffer.append(s.charAt(i));
        }
        char c = s.charAt(0);
        if ('-' == c || '+' == c) {
            buffer.insert(0, c);
        } else {
            buffer.append(c);
        }
        try {
            return Integer.parseInt(buffer.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}