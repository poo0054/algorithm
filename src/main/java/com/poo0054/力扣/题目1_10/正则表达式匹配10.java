package com.poo0054.力扣.题目1_10;

import org.junit.Test;


/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 * <p>
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/14 14:35
 */
public class 正则表达式匹配10 {

    @Test
    public void test() {
        System.out.println(isMatch("aa", "a"));
    }


    public boolean isMatch(String s, String p) {
        return match(s, p, 0, 0);
    }


    private boolean match(String s, String p, int sStart, int pStart) {
        int sLength = s.length();
        int pLength = p.length();
        //匹配前面相等的或者.的
        while (sStart < sLength && pStart < pLength) {
            char pChar = p.charAt(pStart);
            char sChar = s.charAt(sStart);
            if (sChar != pChar && '.' != pChar) {
                break;
            }
            sStart++;
            pStart++;
        }
        if (sStart == sLength && pStart == pLength) {
            //匹配成功
            return true;
        }

        if (pLength > pStart) {
            //匹配 * 前面一样的全部已经匹配完了
            char pChar = p.charAt(pStart);
            //当前匹配字符为 *
            if ('*' == pChar) {
                //当前字符为* s需要取前一个 可能*为0
                sStart = Math.max(sStart - 1, 0);
                //匹配的 上一个字符
                char charAt = p.charAt(pStart - 1);
                // * 的个数
                int skip = 0;
                while (sLength - sStart + 1 > skip) {
                    //匹配值后是否还存在值
                    if (pLength <= pStart + 1) {
                        //匹配值后不存在值  创建出skip个charAt进行后面值匹配     跳过0 从1开始
                        if (0 != skip && this.isMatch(s.substring(sStart), createChat(charAt, skip))) {
                            return true;
                        }
                    } else {
                        //   skip 个 charAt是否匹配
                        if (!matchDis(s, sStart, skip, charAt)) {
                            break;
                        }
                        //后面还有值 跳过 skip 个值
                        if (this.match(s, p, sStart + skip, pStart + 1)) {
                            return true;
                        }
                    }
                    skip++;
                }
            } else {
                //判断后面一位是否为 *
                //是否存在后面一位
                if (pLength > pStart + 1) {
                    pChar = p.charAt(pStart + 1);
                    if ('*' == pChar) {
                        //跳过当前字符 *为0
                        return this.match(s, p, sStart, pStart + 2);
                    }
                }
            }
        }
        return false;
    }

    private boolean matchDis(String s, int sIndex, int skip, char charAt) {
        for (int i = 0; i < skip; i++) {
            char charAt1 = s.charAt(sIndex);
            if (charAt1 != charAt && '.' != charAt) {
                return false;
            }
            sIndex++;
        }
        return true;
    }

    private String createChat(char charAt, int skip) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < skip; i++) {
            buffer.append(charAt);
        }
        return buffer.toString();
    }

}