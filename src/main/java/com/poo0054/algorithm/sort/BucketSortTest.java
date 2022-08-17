package com.poo0054.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 桶排序是计数排序的扩展版本，计数排序可以看成每个桶只存储相同元素，而桶排序每个桶存储一定范围的元素，通过映射函数，
 * 将待排序数组中的元素映射到各个对应的桶中，对每个桶中的元素进行排序，最后将非空桶中的元素逐个放入原序列中。
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/17 9:23
 */
public class BucketSortTest {

    @Test
    public void Test() {
        int[] nums = {10, 9, -8, 7, 6, 5, 11, -12, -3};
        bucketSort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    private void bucketSort(int[] nums) {
        int length = nums.length - 1;
        int max = 0;
        int min = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        int bucketLen = 2;
        //根据数组长度使用不同的桶
        switch (length) {
            case 10000:
                bucketLen = 10;
                break;
            case 1000:
                bucketLen = 5;
            default:
                break;
        }
        //平均每个桶存放的数据  需要+1  下标从0开始
        int cont = (max - min) / bucketLen + 1;
        //桶
        int[][] bucket = new int[bucketLen][0];
        //分配桶
        for (int num : nums) {
            //第几个桶
            int i = (num - min) / cont;
            //桶中添加数据
            bucket[i] = addInt(bucket[i], num);
        }
        int index = 0;
        for (int[] ints : bucket) {
            //排序每个桶
            if (null != ints) {
                //排序 可以使用任意排序算法
                quickSort(ints, 0, ints.length - 1);
                //排序完成直接添加  从小到大-
                for (int anInt : ints) {
                    nums[index++] = anInt;
                }
            }
        }
    }

    /**
     * 内部使用快排
     */
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

    private int[] addInt(int[] nums, int num) {
        nums = Arrays.copyOf(nums, nums.length + 1);
        nums[nums.length - 1] = num;
        return nums;
    }

}
