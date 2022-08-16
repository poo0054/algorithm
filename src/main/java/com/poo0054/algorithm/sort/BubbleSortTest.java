package com.poo0054.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/12 10:14
 */
public class BubbleSortTest {
    @Test
    public void Test() {
        int[] nums = {10, 9, 8, 7, 6, 5, 11, 12};
        bubbleSortDesc(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    // ==============================从小到大

    public void bubbleSort(int[] ints) {
        for (int i = 0; i < ints.length - 1; i++) {
            boolean temple = true;
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[i] > ints[j]) {
                    exchange(ints, i, j);
                    temple = false;
                }
            }
            if (temple) {
                System.out.println("后面的都是有序的了  我直接跳过========" + (++i) + "  值为：" + ints[i]);
                break;
            }
        }
    }

    // ==============================从大到小


    public void bubbleSortDesc(int[] ints) {
        for (int i = 0; i < ints.length - 1; i++) {
            boolean b = true;
            for (int j = i; j < ints.length; j++) {
                if (ints[i] < ints[j]) {
                    exchange(ints, i, j);
                    b = false;
                }
            }
            if (b) {
                System.out.println("后面的都是有序的了  我直接跳过========" + (++i) + "  值为：" + ints[i]);
                return;
            }
        }
    }


    /**
     * 替换
     */
    public static void exchange(int[] ints, int i, int j) {
        int temple = ints[j];
        ints[j] = ints[i];
        ints[i] = temple;
    }
}
