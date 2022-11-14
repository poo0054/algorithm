package com.poo0054.力扣.题目1_10;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/10 15:43
 */
public class 无重复字符的最长子串3 {

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring1("abcaea"));
    }

    /**
     * 中间的循环可以不需要，只需要循环一遍 找到最长的长度
     * 比如 abca 俩个a把前面一个替换左边界从b开始  永远保证中间没有重复值 不断向右边移动
     */
    public int lengthOfLongestSubstring(String s) {
        if (null == s || "".equals(s)) {
            return 0;
        }
        //最大长度
        int length = 1;
        //左边边界
        int left = 0;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>((int) (s.length() / 0.75 + 1));
        for (int i = 0; i < chars.length; i++) {
            //找到前一个重复值  左边界向后移动一位
            if (map.containsKey(chars[i])) {
                left = Math.max(left, map.get(chars[i]) + 1);
            }
            //把前一个替换
            map.put(chars[i], i);
            //取出当前最长长度   left - i - 比如 2-1=1 就是从1到2 总共有2位 需要加一
            length = Math.max(length, i - left + 1);
        }
        return length;
    }


    /**
     * 优化下面， 只需要保证从i开始找到最长的长度就会找到每个字符开始的最长长度
     */
    public int lengthOfLongestSubstring1(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1;
        //当前最长的长度
        int length = 0;
        //循环所有数据
        for (int i = 0; i < n; ++i) {
            //向右边移动 删除前一位
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            //rk 右指针从-1开始
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针 直到找到重复值或者最后一位
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            length = Math.max(length, rk - i + 1);
        }
        return length;
    }

    /**
     * 双层for循环，找到每个值的最长长度 i1会重复查询很多遍
     */
    private static int execSet(String s) {
        if (null == s || "".equals(s)) {
            return 0;
        }
        int length = 1;
        char[] chars = s.toCharArray();
        Set<Character> characters = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            int index = i;
            for (int i1 = i; i1 < chars.length; i1++) {
                index = i1;
                char charAt = chars[i1];
                boolean contains = characters.contains(charAt);
                if (!contains) {
                    characters.add(charAt);
                }
                boolean b = i1 == chars.length - 1;
                if (contains || b) {
                    if (length < characters.size()) {
                        length = characters.size();
                    }
                    if (b) {
                        return length;
                    }
                    break;
                }
            }
            if (index == chars.length - 1 || chars.length - i - 1 <= length) {
                break;
            }
            characters.clear();
        }
        return length;
    }

}