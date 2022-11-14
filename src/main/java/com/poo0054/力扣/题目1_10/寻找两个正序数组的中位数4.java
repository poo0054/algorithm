package com.poo0054.力扣.题目1_10;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 提示：
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/11 9:48
 */
public class 寻找两个正序数组的中位数4 {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return query(nums1, nums2);
    }

    private static double query(int[] nums1, int[] nums2) {
        int[] ints = new int[nums1.length + nums2.length];
        int start = 0;
        int nums1Index = 0;
        int nums2Index = 0;

        while (nums2Index < nums2.length && nums1Index < nums1.length) {
            if (nums1[nums1Index] <= nums2[nums2Index]) {
                ints[start] = nums1[nums1Index];
                nums1Index++;
            } else {
                ints[start] = nums2[nums2Index];
                nums2Index++;
            }
            start++;
        }

        while (nums2Index < nums2.length) {
            ints[start] = nums2[nums2Index];
            nums2Index++;
            start++;
        }

        while (nums1Index < nums1.length) {
            ints[start] = nums1[nums1Index];
            nums1Index++;
            start++;
        }

        //中间有值
        int i1 = ints.length >> 1;
        if (ints.length % 2 == 0) {
            return (ints[i1] + ints[i1 - 1]) / 2.0;
        }
        return ints[i1];
    }
}