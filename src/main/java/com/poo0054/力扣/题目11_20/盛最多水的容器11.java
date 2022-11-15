package com.poo0054.力扣.题目11_20;

import org.junit.Test;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * <p>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 返回容器可以储存的最大水量。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 * <p>
 * 输入：height = [1,1]
 * 输出：1
 * 提示：
 * <p>
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 * Related Topics
 * 贪心
 * 数组
 * 双指针
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/15 9:16
 */
public class 盛最多水的容器11 {

    @Test
    public void test() {
        System.out.println(maxArea(new int[]{1, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    public int maxArea(int[] height) {
        int length = height.length - 1;
        int l = 0, r = length, sum = 0;
        while (r > l) {
            int i = Math.min(height[r], height[l]) * (r - l);
            sum = Math.max(i, sum);
            if (height[r] > height[l]) {
                l++;
            } else {
                r--;
            }
        }
        return sum;
    }
}