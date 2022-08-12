package poo0054.基础算法.二分查找;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个字符串 s 和 p ，其中 p 是 s 的一个 子序列 。同时，给你一个元素 互不相同 且下标 从 0 开始 计数的整数数组 removable ，该数组是 s 中下标的一个子集（s 的下标也 从 0 开始 计数）。
 * 请你找出一个整数 k（0 <= k <= removable.length），选出 removable 中的 前 k 个下标，然后从 s 中移除这些下标对应的 k 个字符。整数 k 需满足：在执行完上述步骤后， p 仍然是 s 的一个 子序列 。更正式的解释是，对于每个 0 <= i < k ，先标记出位于 s[removable[i]] 的字符，接着移除所有标记过的字符，然后检查 p 是否仍然是 s 的一个子序列。
 * 返回你可以找出的 最大 k ，满足在移除字符后 p 仍然是 s 的一个子序列。
 * 字符串的一个 子序列 是一个由原字符串生成的新字符串，生成过程中可能会移除原字符串中的一些字符（也可能不移除）但不改变剩余字符之间的相对顺序。
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/11 15:53
 */
public class 可移除字符的最大数目 {


    @Test
    public void Test() {
        /*
"abcacb"
"ab"
[3,1,0]

         */
        int i = maximumRemovals("abcb", "a", new int[]{0, 1, 2, 3, 4});
        System.out.println(i);
    }

    /**
     * 输入：s = "abcacb", p = "ab", removable = [3,1,0]
     * 输出：2
     * 解释：在移除下标 3 和 1 对应的字符后，"abcacb" 变成 "accb" 。
     * "ab" 是 "accb" 的一个子序列。
     * 如果移除下标 3、1 和 0 对应的字符后，"abcacb" 变成 "ccb" ，那么 "ab" 就不再是 s 的一个子序列。
     * 因此，最大的 k 是 2 。
     *
     * @param s
     * @param p
     * @param removable
     * @return
     */
    public int maximumRemovals(String s, String p, int[] removable) {
        //使用的是向尽量向右边界靠拢 左边需要-1
        int l = 0, r = removable.length - 1;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            //需要减去一
            if (check(s, p, removable, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        //需要一个兜底的方法进行判断 0是否成功
        return l;
    }

    private boolean check(String s, String p, int[] removable, int mid) {
        //删除后的值
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i <= mid; i++) {
            hashSet.add(removable[i]);
        }

        int sStart = 0, pStart = 0,
                sEnd = s.length(), pEnd = p.length();

        while (sStart < sEnd && pStart < pEnd) {
            //由于删除的是源数据，下标不为已删除 并且charAt匹配成功就说明匹配
            if (!hashSet.contains(sStart) && p.charAt(pStart) == s.charAt(sStart)) {
                pStart++;
            }
            sStart++;
        }
        //是length 没有减一
        return pEnd == pStart;
    }
}
