package com.poo0054.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 基数排序是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/17 15:16
 */
public class CardinalitySortTest {

    @Test
    public void Test() {
        int[] nums = {10, 9, -8, 7, 6, 5, 11, -12, -3};
        cardinalitySort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    private void cardinalitySort(int[] nums) {
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
        //求出最大值是几位数
        int maxLen = getMaxLen(max - min);
        //最大位数 从1位数开始
        for (int i = 1; i <= maxLen; i++) {
            int len = getLen(i);
            //根据位数排序
            sort(nums, len, min);
        }
    }

    /**
     * 负数采用每个数-负数的最大数
     */
    public void sort(int[] nums, int remainder, int min) {
        int[][] ints = new int[10][0];
        for (int i = 0; i < nums.length; i++) {
            //当前remainder的位数数字
            int num = (nums[i] - min) / remainder % 10;
            System.out.printf("当前值：%s 转换后的值为：%s 当前处理位数为：%s 尾数为：%s \n", nums[i], nums[i] - min, remainder, num);
            ints[num] = addInt(ints[num], nums[i]);
        }
        System.out.println();
        int tem = 0;
        //按照顺序加入
        for (int i = 0; i < ints.length; i++) {
            int[] anInt = ints[i];
            if (null != anInt) {
                for (int j = 0; j < anInt.length; j++) {
                    nums[tem++] = anInt[j];
                }
            }
        }

    }

    private int getLen(int i) {
        int len = 1;
        for (int j = 1; j < i; j++) {
            len *= 10;
        }
        return len;
    }


    /**
     * 几位数
     */
    private int getMaxLen(int num) {
        int i = 0;
        if (num > 0) {
            i++;
        }
        do {
            if (num > 0) {
                i++;
            }
            num /= 10;
        }
        while (num > 9);
        return i;
    }


    private int[] addInt(int[] nums, int num) {
        nums = Arrays.copyOf(nums, nums.length + 1);
        nums[nums.length - 1] = num;
        return nums;
    }
}
