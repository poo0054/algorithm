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
        int[] nums = {6, 5, 3, 2, 9, 5, 9, 11, 1, 3};
        quickSortDesc(nums, 0, nums.length - 1);
        Arrays.stream(nums).forEach(System.out::println);
    }

    //==========================从小到大

    public void quickSort(int[] nums, int left, int right) {
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

    public void quickSortDesc(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        //中间值
        int mid = (left + right) >> 1;
        int l = left;
        int r = right;
        int baseValue = nums[mid];
        exchange(nums, r, mid);
        //循环所有数
        while (l < r) {
            //大的放左边 小的放右边
            while (nums[l] >= baseValue && l < r) {
                l++;
            }
            while (nums[r] <= baseValue && l < r) {
                r--;
            }
            //交换数据
            if (l < r) {
                exchange(nums, r, l);
            }
        }
        //把baseValue放到 下标l位置 左边就比base大  右边小
        exchange(nums, l, right);
        quickSortDesc(nums, left, l - 1);
        quickSortDesc(nums, r + 1, right);
    }
}

