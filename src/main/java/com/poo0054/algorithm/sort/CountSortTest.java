package com.poo0054.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 计数排序
 * <p>
 * 通过统计序列中各个元素出现的次数，完成对整个序列的升序或降序排序，这样的排序算法称为计数排序算法。
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/16 16:20
 */
public class CountSortTest {
    @Test
    public void Test() {
        int[] nums = {10, -9, -10, -5, -1, -9};
        countSort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    public void countSort(int[] nums) {
        int length = nums.length - 1;
        //获取最大值
        int max = 0;
        int min = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        //存放所有数据的数组
        int[] ints = new int[(max - min) + 1];

        for (int i = 0; i <= length; i++) {
            //当前值
            int num = nums[i];
            num -= min;
            //下标为值的数字加一
            ints[num] += 1;
        }

        int i = 0;
        for (int j = 0; j <= length; j++) {
            //如果是0就跳过
            while (ints[i] == 0) {
                i++;
            }
            //nums的值为下标
            nums[j] = i + min;
            //使用过了 就减去
            ints[i]--;
        }
    }


}
