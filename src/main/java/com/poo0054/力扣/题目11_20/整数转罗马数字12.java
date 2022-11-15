package com.poo0054.力扣.题目11_20;

import org.junit.Test;

import java.util.HashMap;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给你一个整数，将其转为罗马数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 3
 * 输出: "III"
 * 示例 2:
 * <p>
 * 输入: num = 4
 * 输出: "IV"
 * 示例 3:
 * <p>
 * 输入: num = 9
 * 输出: "IX"
 * 示例 4:
 * <p>
 * 输入: num = 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 * <p>
 * 输入: num = 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * 提示：
 * <p>
 * 1 <= num <= 3999
 * Related Topics
 * 哈希表
 * 数学
 * 字符串
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/15 14:14
 */
public class 整数转罗马数字12 {
    @Test
    public void test() {
        System.out.println(intToRoman(1994));
    }


    public String intToRoman(int num) {
        RewriteGetHashMap map = new RewriteGetHashMap();
        StringBuffer buffer = new StringBuffer();
        //位数
        int digit = 0;
        while (num > 0) {
            int i = num % 10;
            int i1 = i % 5;
            //是否大于五
            if (i >= 5) {
                if (4 == i1) {
                    //9   一个1 加上一个10
                    buffer.insert(0, map.getRoman(digit + 1));
                    buffer.insert(0, map.getRoman(digit));
                } else {
                    for (int i2 = 0; i2 < i1; i2++) {
                        buffer.insert(0, map.getRoman(digit));
                    } //6-8
                    buffer.insert(0, map.getRoman(digit, 5));
                }
            } else {
                //小于五
                if (4 == i1) {
                    //4
                    buffer.insert(0, map.getRoman(digit, 5));
                    buffer.insert(0, map.getRoman(digit));
                } else {
                    //1-3
                    for (int i2 = 0; i2 < i1; i2++) {
                        buffer.insert(0, map.getRoman(digit));
                    }
                }
            }
            digit++;
            num /= 10;
        }
        return buffer.toString();
    }

    static class RewriteGetHashMap extends HashMap<Integer, String> {
        {
            super.put(1, "I");
            super.put(5, "V");
            super.put(10, "X");
            super.put(50, "L");
            super.put(100, "C");
            super.put(500, "D");
            super.put(1000, "M");
        }


        public String getRoman(Integer sum, Integer unit) {
            return this.get(this.getRomanDigit(sum, unit));
        }

        public String getRoman(Integer sum) {
            return this.get(this.getRomanDigit(sum));
        }

        public Integer getRomanDigit(int sum) {
            return getRomanDigit(sum, null);
        }

        public Integer getRomanDigit(int sum, Integer unit) {
            unit = Math.max(Math.min(9, null == unit ? 1 : unit), 0);
            while (0 < sum) {
                unit *= 10;
                sum--;
            }
            return unit;
        }
    }


}