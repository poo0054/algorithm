package com.poo0054.力扣.题目1_10;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * 提示：
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 *
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/10 10:03
 */
public class 俩数之和1 {

    @Test
    public void test() {
        twoSum(new int[]{1, 2, 3}, 5);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>((int) (nums.length / 0.75 + 1));
        for (int i = 0; i < nums.length; i++) {
            int tar = target - nums[i];
            if (map.containsKey(tar)) {
                return new int[]{map.get(tar), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }


    /**
     * 双层for循环
     */
    private static int[] foreach(int[] nums, int target) {
        int[] ints = new int[2];
        for (int i1 = 0; i1 < nums.length; i1++) {
            for (int i = 0; i < nums.length; i++) {
                if ((nums[i1] + nums[i]) == target) {
                    ints[0] = i1;
                    ints[1] = i;
                }
            }
        }
        return ints;
    }


}