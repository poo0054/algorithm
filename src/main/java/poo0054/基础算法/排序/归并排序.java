package poo0054.基础算法.排序;

import org.junit.Test;

import java.util.Arrays;

/**
 * 分解（Divide）：将n个元素分成个含n/2个元素的子序列。
 * 解决（Conquer）：用合并排序法对两个子序列递归的排序。
 * 合并（Combine）：合并两个已排序的子序列已得到排序结果。
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/12 16:33
 */
public class 归并排序 {

    @Test
    public void Test() {
        int[] nums = {1, 2, 0, 0, 3, 5};
        quickSortDesc(nums, 0, nums.length - 1);
        Arrays.stream(nums).forEach(System.out::println);
    }


    // ==============================从小到大

    //===============递归
    private void quickSort(int[] nums, int left, int right) {
        int l = left, r = right, mid = (left + right) >> 1;
        if (l < r) {
            //左边组
            quickSort(nums, l, mid);
            //右边组
            quickSort(nums, mid + 1, r);
            merge(nums, l, mid, r);
        }
    }

    private void merge(int[] nums, int l, int mid, int r) {
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

    private void quickSortDesc(int[] nums, int left, int right) {
        //步长 从1开始
        int k = 1;
        //等于或者大于就只有左边 没有右边  ——》 排序完成
        while (k < right) {
            mergeDesc(nums, left, right, k);
            //步长每次*2
            k = k << 1;
        }
    }

    private void mergeDesc(int[] nums, int left, int right, int k) {
        //先按照k一步步拆分

        int lLeft, lRight, rLeft, rRight;
        //循环当前所有元素
        for (int i = left; i <= right; ) {
            //第一组和第二组比较 以此类推
            //第一组开始
            lLeft = i;
            //第二组开始
            rLeft = i + k;
            //第一组结束
            lRight = rLeft - 1;
            //第二组结束  第三组开始的后面一位
            rRight = lRight + k - 1;
            //TODO
        }

    }


    // ==============================从大到小

}
