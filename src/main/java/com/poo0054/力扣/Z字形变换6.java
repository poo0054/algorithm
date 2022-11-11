package com.poo0054.力扣;

import org.junit.Test;

import java.util.*;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/11 16:38
 */
public class Z字形变换6 {

    @Test
    public void test() {
        System.out.println(convert("0123456789abcde", 3));
    }

    public String convert(String s, int numRows) {
        StringBuffer buffer = new StringBuffer();
        if (1 == numRows) {
            return s;
        }
        List<Stack<Character>> list = new LinkedList<>();
        int length = s.length();
        //用来记录使用过的下标
        Set<Integer> set = new HashSet<>();
        //一个轮回
        int offset = numRows + Math.max(numRows - 2, 1);
        for (int i = 0; i < numRows; i++) {
            //第几列
            int row = 0;
            //当前下标
            int cuntIndex = row;
            while (cuntIndex < length) {
                //当前行
                int cunt = offset * row;
                //前一行
                int lodCunt = Math.max(offset * (row - 1), 0);
                //当前俩行的总数
                int sum = cunt + lodCunt;
                //下一个的下标
                int netIndex = sum - cuntIndex;
                if (cuntIndex < length) {
                    if (!set.contains(cuntIndex)) {
                        buffer.append(s.charAt(cuntIndex));
                        set.add(cuntIndex);
                    }
                }
                if (netIndex < length) {
                    if (!set.contains(netIndex)) {
                        buffer.append(s.charAt(netIndex));
                        set.add(netIndex);
                    }
                }
                //方便计算下一次
                row++;
                cuntIndex += offset;
            }
        }
        return buffer.toString();
    }
}