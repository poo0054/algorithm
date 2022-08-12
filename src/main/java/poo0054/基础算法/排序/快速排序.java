package poo0054.基础算法.排序;

import org.junit.Test;

import java.util.Arrays;

/**
 * 先从数列中取出一个数作为基准数进行排序
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/12 10:33
 */
public class 快速排序 {
    @Test
    public void Test() {
        int[] nums = {1, 2, 0, 0};
        quickSort(nums, 0, nums.length - 1);
        Arrays.stream(nums).forEach(System.out::println);
    }

    //==========================从小到大

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        exchange(nums, left, left + right >> 1);
        int baseValue = nums[left];
        int l = left, r = right;
        while (l < r) {
            while (nums[r] >= baseValue && l < r) {
                r--;
            }
            while (nums[l] <= baseValue && l < r) {
                l++;
            }
            if (l < r) {
                exchange(nums, r, l);
            }
        }
        exchange(nums, l, left);
        quickSort(nums, left, l - 1);
        quickSort(nums, l + 1, right);
    }

    public static void exchange(int[] nums, int i, int j) {
        int temple = nums[j];
        nums[j] = nums[i];
        nums[i] = temple;
    }

    //==========================从大到小

}

