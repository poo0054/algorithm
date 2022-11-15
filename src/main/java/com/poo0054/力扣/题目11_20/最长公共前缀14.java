package com.poo0054.力扣.题目11_20;

import org.junit.Test;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * 提示：
 * <p>
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 * Related Topics
 * 字符串
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/15 17:21
 */
public class 最长公共前缀14 {

    @Test
    public void test() {
        System.out.println(longestCommonPrefix(new String[]{"adog", "aracecar", "acar"}));
    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder stringBuffer = new StringBuilder();
        int index = -1;
        while (true) {
            index++;
            int strsIndex = 0;
            char charAt = '*';
            while (strsIndex < strs.length) {
                if (strs[strsIndex].length() <= index) {
                    return stringBuffer.toString();
                }
                if ('*' == charAt) {
                    charAt = strs[strsIndex].charAt(index);
                } else {
                    if (charAt != strs[strsIndex].charAt(index)) {
                        return stringBuffer.toString();
                    }
                }
                strsIndex++;
            }
            stringBuffer.append(charAt);
        }
    }
}