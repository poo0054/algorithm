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
        int[] nums = {-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};
        quickSortIterator(nums, 0, nums.length - 1);
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

    private void quickSortIterator(int[] nums, int left, int right) {
        //步长 从1开始
        int k = 1;
        //等于或者大于就只有左边 没有右边  ——》 排序完成
        while (k <= right) {
            mergeIterator(nums, left, right, k);
            //步长每次 * 2
            k = k << 1;
        }
    }

    private void mergeIterator(int[] nums, int left, int right, int k) {
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

}



