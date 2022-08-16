package com.poo0054.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 选择排序
 * <p>
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 重复第二步，直到所有元素均排序完毕。
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/15 18:04
 */
public class selectSortTest {
    @Test
    public void Test() {
        int[] nums = {5, 2, 3, 1};
        selectSort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    //=====================升序

    private void selectSort(int[] nums) {
        //跳过0 从1开始
        for (int i = 0; i < nums.length; i++) {
            //首先找到最小的数
            ///假设为最开始的数
            int num = nums[i];
            int index = i;
            //从后往前面找
            for (int j = nums.length - 1; j > i; j--) {
                int temple = nums[j];
                if (temple < num) {
                    //找到下标
                    index = j;
                    num = temple;
                }
            }
            //交换值
            exchange(nums, i, index);
        }
    }


    public static void exchange(int[] nums, int i, int j) {
        int temple = nums[j];
        nums[j] = nums[i];
        nums[i] = temple;
    }
}
