package com.poo0054.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序
 * <p>
 * 把待排序的记录按其关键码值的大小逐个插入到一个已经排好序的有序序列中，直到所有的记录插入完为止，得到一个新的有序序列 。
 * 实际中我们玩扑克牌时，就用了插入排序的思想
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/15 14:14
 */
public class InsertSortTest {

    @Test
    public void Test() {
        int[] nums = {10, 1, 5};
        insertSort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    //============================================升序

    public void insertSort(int[] nums) {
        //从第二个元素开始进行插入排序
        for (int i = 1; i < nums.length; i++) {
            //当前需要操作的元素
            int temple = nums[i];
            //与前面所有数字进行比较 直到找到位置
            for (int j = i - 1; j >= 0; j--) {
                //当前元素比操作元素大 向后移动一位
                if (nums[j] >= temple) {
                    exchange(nums, j, j + 1);
                } else {
                    break;
                }
            }
        }
    }

    public static void exchange(int[] nums, int i, int j) {
        int temple = nums[j];
        nums[j] = nums[i];
        nums[i] = temple;
    }
    
}
