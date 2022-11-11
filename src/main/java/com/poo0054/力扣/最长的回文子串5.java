package com.poo0054.力扣;

import org.junit.Test;

import java.util.HashMap;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/11 10:23
 */
public class 最长的回文子串5 {

    @Test
    public void test() {
        System.out.println(longestPalindrome("ac"));
    }

    public String longestPalindrome(String s) {
        if (null == s || "".equals(s)) {
            return s;
        }
        if (2 > s.length()) {
            return s;
        }
        String str = "";
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int left = -1;
        int right = -1;
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            //是否有重复的
            if (map.containsKey(chars[i])) {
                //上一个的下标
                Integer oldIndex = map.get(chars[i]);
                //总共有多少个数
                int i1 = i - oldIndex + 1;
                if (i1 <= 3) {
                    //如果是2  就可以已左边为基准 右边为基准 俩个为基准进行查询
                    if (i1 == 2) {
                        //中心为轴
                        String s1 = outsideEq(s, oldIndex, i);
                        if (str.length() < s1.length()) {
                            str = s1;
                        }
                        //左边为轴
                        String leftStr = outsideEq(s, oldIndex - 1, i);
                        if (str.length() < leftStr.length()) {
                            str = leftStr;
                        }

                        //右边为轴
                        String rightStr = outsideEq(s, oldIndex, i + 1);
                        if (str.length() < rightStr.length()) {
                            str = rightStr;
                        }

                    } else if (i1 == 3) {
                        String s1 = outsideEq(s, oldIndex, i);
                        if (str.length() < s1.length()) {
                            str = s1;
                        }
                    }

                } else {
                    //大于3 的 需要先检查内部是否为回文串
                    left = oldIndex;
                    right = i;
                    while (right - left + 1 > 2 && chars[left] == chars[right]) {
                        left++;
                        right--;
                    }
                    //里面都是回文数
                    if (right - left + 1 <= 2) {
                        if (right - left + 1 == 2) {
                            if (!(left >= 0 && right < length && chars[left] == chars[right])) {
                                //替换掉前面的
                                map.put(chars[i], i);
                                continue;
                            }
                        }
                        left = oldIndex - 1;
                        right = i + 1;
                        String s1 = outsideEq(s, left, right);
                        if (str.length() < s1.length()) {
                            str = s1;
                        }
                    }
                    //当前没有回文
                }
            }
            //替换掉前面的
            map.put(chars[i], i);
        }
        if (str.length() == 0) {
            return chars[0] + "";
        }
        return str;
    }

    private static String outsideEq(String s, Integer leftIndex, int rightIndex) {
        int right;
        int left;
        left = leftIndex;
        right = rightIndex;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        //当前的不满足 使用上一个
        int beginIndex = left + 1;
        int endIndex = right - 1;
        return s.substring(beginIndex, endIndex) + s.charAt(endIndex);
    }

}