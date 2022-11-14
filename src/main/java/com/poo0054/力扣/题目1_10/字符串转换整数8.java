package com.poo0054.力扣.题目1_10;

import org.junit.Test;

/**
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * 注意：
 * <p>
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 * 示例 1：
 * <p>
 * 输入：s = "42"
 * 输出：42
 * 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
 * ^
 * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * ^
 * 第 3 步："42"（读入 "42"）
 * ^
 * 解析得到整数 42 。
 * 由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
 * 示例 2：
 * <p>
 * 输入：s = "   -42"
 * 输出：-42
 * 解释：
 * 第 1 步："   -42"（读入前导空格，但忽视掉）
 * ^
 * 第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
 * ^
 * 第 3 步："   -42"（读入 "42"）
 * ^
 * 解析得到整数 -42 。
 * 由于 "-42" 在范围 [-231, 231 - 1] 内，最终结果为 -42 。
 * 示例 3：
 * <p>
 * 输入：s = "4193 with words"
 * 输出：4193
 * 解释：
 * 第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
 * ^
 * 第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * ^
 * 第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
 * ^
 * 解析得到整数 4193 。
 * 由于 "4193" 在范围 [-231, 231 - 1] 内，最终结果为 4193 。
 * 提示：
 * <p>
 * 0 <= s.length <= 200
 * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/14 11:18
 */
public class 字符串转换整数8 {

    @Test
    public void test() {
        System.out.println(myAtoi("42"));
    }

    public int myAtoi(String s) {
        int length = s.length();
        if (null == s || 0 == length) {
            return 0;
        }

        int index = 0;

        //去除空格
        while (index >= 0 && index < length) {
            char c = s.charAt(index);
            if (' ' == c || '\0' == c) {
                index++;
                continue;
            }
            break;
        }

        //符号位
        boolean b = true;
        if (index < length) {
            char c = s.charAt(index);
            if ('-' == c || '+' == c) {
                if ('-' == c) {
                    b = false;
                }
                index++;
            }
        }

        int number = 0;
        //数字位
        while (index < length) {
            char c = s.charAt(index);
            if (!('0' <= c && '9' >= c)) {
                break;
            }
            int i1 = Integer.parseInt(c + "");
            //数字
            boolean b1 = number > Integer.MAX_VALUE / 10 || (number == Integer.MAX_VALUE / 10 && i1 > Integer.MAX_VALUE % 10);
            if (b1) {
                //超出
                return b ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            number = number * 10 + i1;
            index++;
        }
        return b ? number : -number;
    }
}