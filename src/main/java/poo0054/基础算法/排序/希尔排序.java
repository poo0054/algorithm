package poo0054.基础算法.排序;

import org.junit.Test;

import java.util.Arrays;

/**
 * 先选定一个整数gap，把待排序文件中所有记录分成gap个组，所有距离为gap的记录分在同一组内，并对每一组内的元素进行排序。
 * 然后将gap逐渐减小重复上述分组和排序的工作。
 * 当到达gap=1时，所有元素在统一组内排好序。
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/15 14:34
 */
public class 希尔排序 {
    @Test
    public void Test() {
        int[] nums = {5, 2, 3, 1, 10, 6, 3, 5};
        shellSortDesc(nums, (nums.length - 1) >> 1);
        Arrays.stream(nums).forEach(System.out::println);
    }


    //=============================================升序

    private void shellSort(int[] nums) {
        //第一次分组 步长
        int gap = nums.length >> 1;
        int end = nums.length - 1;
        while (gap > 0) {
            //获取每一步结束的下标
            for (int i = gap; i <= end; i++) {
                //当前步数结束下标
                int endIndex = i;
                //当前步数开始的下标
                int startIndex = endIndex - gap;
                //不能超过数据下标
                while (startIndex >= 0 && endIndex <= end) {
                    //开始比结束大
                    if (nums[startIndex] > nums[endIndex]) {
                        //替换
                        exchange(nums, startIndex, endIndex);
                        //找前面步长的数据来替换
                        endIndex = startIndex;
                        //下一组开始下标
                        startIndex = endIndex - gap;
                    } else {
                        break;
                    }
                }
            }
            //下一轮
            gap >>= 1;
        }

    }


    public static void exchange(int[] nums, int i, int j) {
        int temple = nums[j];
        nums[j] = nums[i];
        nums[i] = temple;
    }

    //============================================降序


    private void shellSortDesc(int[] nums, int gap) {
        int end = nums.length - 1;
        //最少也得一组
        if (gap > 0) {
            //一组结束开始
            for (int gapEnd = gap; gapEnd <= end; gapEnd++) {
                //当前组结束下标
                int right = gapEnd;
                //当前组开始下标
                int left = right - gap;
                //不能超过数组范围
                while (left >= 0 && left <= end) {
                    // 右边比左边小
                    if (nums[right] > nums[left]) {
                        //交换
                        exchange(nums, right, left);
                        //比较前面步长的值
                        right = left;
                        left = right - gap;
                    } else {
                        break;
                    }
                }
            }
            gap >>= 1;
            shellSortDesc(nums, gap);
        }
    }

}
