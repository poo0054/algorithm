package 算法提升专题.基础算法.二分查找;

import org.junit.Test;

/**
 * 二分查找:
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/10 15:14
 */
public class 查找元素的第一个和最后一个位置 {
    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     * 示例 1：
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * <p>
     * 示例 2：
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     */
    @Test
    public void Test() {
        int[] nums = new int[]{1, 2};
        int target = 1;
        int[] ints = searchRange(nums, target);
        System.out.println(ints);
    }

    public int[] searchRange(int[] nums, int target) {
        if (0 == nums.length) {
            return new int[]{-1, -1};
        }
        //找出左边值
        int r = searchLeft(nums, target);
        if (nums[r] == target) {
            //左边不为空右边肯定不能为空
            int l = searchRight(nums, target);
            return new int[]{r, l};
        }
        return new int[]{-1, -1};
    }

    /**
     * 找出target的下标，不存在则找出最接近的值
     * <p>
     * 二分查找法， 这里使用的是 <= 如果目标值比中间值小或者相等，右边边界值为中间值。如果大，左边边界等于边界值+1。如果相等就是排除右边值。慢慢往左边靠拢
     *
     * @param nums   需要寻找的数组
     * @param target 目标值
     * @return 下标
     */
    private static int searchLeft(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            //中间值
            int mid = (r + l) >> 1;
            if (target <= nums[mid]) {
                r = mid;
            } else {
                l = ++mid;
            }
        }
        return r;
    }

    /**
     * 该方法是与上相反。找出相等最右边的值。
     * <p>
     * 结论：等于和大于在一起就会排除左边的值。等于和小于在一起，就会排除大于的值（右边）
     * 注意：如果排除左边的值。 nums = [1,2] target = [1]使用下面方法会造成死循环，需要在min的值加1 每次比较右边的值才能打破循环
     */
    private static int searchRight(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            //中间值
            int min = (r + l + 1) >> 1;
            if (target >= nums[min]) {
                l = min;
            } else {
                r = --min;
            }
        }
        return r;
    }

}
