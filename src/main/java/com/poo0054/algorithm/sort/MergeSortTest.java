package com.poo0054.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 归并排序
 * <p>
 * 分解（Divide）：将n个元素分成个含n/2个元素的子序列。
 * 解决（Conquer）：用合并排序法对两个子序列递归的排序。
 * 合并（Combine）：合并两个已排序的子序列已得到排序结果。
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/12 16:33
 */
public class MergeSortTest {

    @Test
    public void Test() {
//        int[] nums = {-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};
        int[] nums = {0, 5, 1, 10, 2};
        mergeSortIteratorDesc(nums, 0, nums.length - 1);
        Arrays.stream(nums).forEach(System.out::println);
    }


    // ==============================从小到大

    //===============递归
    public void mergeSort(int[] nums, int left, int right) {
        int l = left, r = right, mid = (left + right) >> 1;
        if (l < r) {
            //左边组
            mergeSort(nums, l, mid);
            //右边组
            mergeSort(nums, mid + 1, r);
            merge(nums, l, mid, r);
        }
    }

    public void merge(int[] nums, int l, int mid, int r) {
        //右边开始位置
        int rIndex = mid + 1;
        //临时数据
        int[] temple = new int[r - l + 1];
        //临时数组的起始位置
        int t = l;
        //临时数组当前操作位置
        int temI = 0;
        while (l <= mid && rIndex <= r) {
            if (nums[l] <= nums[rIndex]) {
                //左边小
                temple[temI++] = nums[l++];
            } else {
                //右边小
                temple[temI++] = nums[rIndex++];
            }
        }
        //左边数组还有剩余
        while (l <= mid) {
            temple[temI++] = nums[l++];
        }
        //右边边数组还有剩余
        while (rIndex <= r) {
            temple[temI++] = nums[rIndex++];
        }

        //临时数组覆盖掉
        for (int i = 0; i < temple.length; i++) {
            nums[t++] = temple[i];
        }
    }

    //===============迭代器

    public void mergeSortIterator(int[] nums, int left, int right) {
        //步长 从1开始
        int k = 1;
        //等于或者大于就只有左边 没有右边  ——》 排序完成
        while (k <= right) {
            mergeIterator(nums, left, right, k);
            //步长每次 * 2
            k = k << 1;
        }
    }

    public void mergeIterator(int[] nums, int left, int right, int k) {
        //先按照k一步步拆分
        int lStart, lEnd, rStart, rEnd;
        //循环当前所有元素
        for (int i = left; i < right; ) {
            //第一组和第二组比较 以此类推 k<right  步长最长比长度 -1
            //第一组开始
            lStart = i;
            //第一组结束
            lEnd = i + k - 1;
            //只有一组了  直接返回
            if (lEnd >= right) {
                return;
            }
            //第二组开始 最长限制
            rStart = Math.min(lEnd + 1, right);
            //第二组结束  第三组开始 -1  最长限制
            rEnd = Math.min(rStart + k - 1, right);

            //当前操作开始下标
            int index = lStart;

            //当前容器最大长度  都是使用下标进行操作  需要加一
            int[] ints = new int[rEnd - lStart + 1];
            int intsIndex = 0;

            //比较
            while (lStart <= lEnd && rStart <= rEnd) {
                // TODO 如果需要从大到小 改为 >=就好了
                //左边小
                if (nums[lStart] <= nums[rStart]) {
                    ints[intsIndex++] = nums[lStart++];
                } else {
                    //右边小
                    ints[intsIndex++] = nums[rStart++];
                }
            }

            //剩余的直接添加
            while (lStart <= lEnd) {
                ints[intsIndex++] = nums[lStart++];
            }
            while (rStart <= rEnd) {
                ints[intsIndex++] = nums[rStart++];
            }

            //把排序好的直接添加入数组
            for (int i1 = 0; i1 < ints.length; i1++) {
                nums[index++] = ints[i1];
            }

            //下一组开始位置
            i = rEnd + 1;

        }
    }

    // ==============================从大到小

    //===============递归
    public void mergeSortDesc(int[] nums, int left, int right) {
        int l = left, r = right, mid = (r + l) >> 1;
        if (left < right) {
            mergeSortDesc(nums, l, mid);
            //向右边偏移  left < right 控制不越界
            mergeSortDesc(nums, mid + 1, r);
            //这里有一个前提条件 left < right
            mergeDesc(nums, l, mid, r);
        }
    }

    private void mergeDesc(int[] nums, int l, int mid, int r) {
        //左右俩组进行比较 上面使用的是 /2 如果0 1 向左偏移 这里使用 rStart = mid + 1  如果上面(r + l+1) >> 1; 这里需要使用 lEnd=mid -1
        int lStart = l, lEnd = mid, rStart = mid + 1, rEnd = r;
        // = 是因为最后一位也需要处理
        int[] ints = new int[rEnd - lStart + 1];
        int index = lStart;
        int intsIndex = 0;

        //比较
        while (lStart <= lEnd && rStart <= rEnd) {
            if (nums[lStart] >= nums[rStart]) {
                //左边大
                ints[intsIndex++] = nums[lStart++];
            } else {
                //右边大
                ints[intsIndex++] = nums[rStart++];
            }
        }

        //剩余的  按照顺序添加
        while (lStart <= lEnd) {
            ints[intsIndex++] = nums[lStart++];
        }
        while (rStart <= rEnd) {
            ints[intsIndex++] = nums[rStart++];
        }

        //复制过去
        for (int i = 0; i < ints.length; i++) {
            nums[index++] = ints[i];
        }
    }

    //===============迭代器
    public void mergeSortIteratorDesc(int[] nums, int left, int right) {
        //先确认步长
        int k = 1;
        //保证最少要有一组
        while (k <= right) {
            mergeIteratorDesc(nums, left, right, k);
            //步长的增长
            k <<= 1;
        }
    }

    public void mergeIteratorDesc(int[] nums, int left, int right, int k) {
        //先按照k一步步拆分
        int lStart, lEnd, rStart, rEnd;
        //i 当前开始下标
        for (int i = left; i < right; ) {
            lStart = i;
            //下一组开始的前一个  最长不能超过right
            lEnd = Math.min(lStart + k - 1, right);
            rStart = Math.min(lStart + k, right);
            rEnd = Math.min(rStart + k - 1, right);
            //只剩一组不进行比较  —————— 最后可能一组都不足了
            if (lEnd >= right) {
                return;
            }
            //下一组开始下标
            i = rEnd + 1;

            int[] ints = new int[rEnd - lStart + 1];
            int index = lStart;
            int intsIndex = 0;

            while (lStart <= lEnd && rStart <= rEnd) {
                if (nums[lStart] >= nums[rStart]) {
                    ints[intsIndex++] = nums[lStart++];
                } else {
                    ints[intsIndex++] = nums[rStart++];
                }
            }

            //剩余的  按照顺序添加
            while (lStart <= lEnd) {
                ints[intsIndex++] = nums[lStart++];
            }
            while (rStart <= rEnd) {
                ints[intsIndex++] = nums[rStart++];
            }

            //复制过去
            for (int i1 = 0; i1 < ints.length; i1++) {
                nums[index++] = ints[i1];
            }
        }


    }

}



