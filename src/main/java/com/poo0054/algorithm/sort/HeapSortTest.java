package com.poo0054.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 堆排序
 * <p>
 * 每个结点的值都大于其左孩子和右孩子结点的值，称之为大根堆；每个结点的值都小于其左孩子和右孩子结点的值，称之为小根堆。
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/16 11:41
 */
public class HeapSortTest {

    @Test
    public void Test() {
        int[] nums = {5, 2, 3, 1};
        heapSort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    //==========================大顶堆    升序

    public void heapSort(int[] nums) {
        int length = nums.length - 1;
        //构建大顶堆
        buildMaxHeap(nums, length);
        //构建升序数组 每次取出根节点 然后重新构建大顶堆 一直重复
        for (int i = length; i >= 0; ) {
            //取出第一个数 放在末尾 然后i--
            exchange(nums, 0, i);
            //只需要把根节点重新构造一下就好了
            i--;
            sort(nums, 0, i);
        }
    }

    private void buildMaxHeap(int[] nums, int length) {
        //找到最后一位的父节点 使用 -- 慢慢向上寻找 直到找到最后一位 根节点
        for (int i = (length - 1) >> 1; i >= 0; i--) {
            //i的子节点是否比i还要大
            sort(nums, i, length);
        }
    }

    private void sort(int[] nums, int i, int length) {
        int left = (i << 1) + 1;
        int right = (i << 1) + 2;
        //需要交换的值
        int change = i;
        if (left <= length && nums[left] > nums[change]) {
            change = left;
        }

        if (right <= length && nums[right] > nums[change]) {
            change = right;
        }

        //i已经改变过了
        if (change != i) {
            exchange(nums, i, change);
            //改变过了 就要判断改变过的值是否能在该节点
            sort(nums, change, length);
        }

    }

    public void exchange(int[] nums, int i, int j) {
        int temple = nums[j];
        nums[j] = nums[i];
        nums[i] = temple;
    }


}
