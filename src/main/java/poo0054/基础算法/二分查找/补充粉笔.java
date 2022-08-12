package poo0054.基础算法.二分查找;

import org.junit.Test;

/**
 * 题目描述
 * 一个班级里有 n 个学生，编号为 0 到 n - 1 。每个学生会依次回答问题，编号为 0 的学生先回答，然后是编号为 1 的学生，以此类推，直到编号为 n - 1 的学生，然后老师会重复这个过程，重新从编号为 0 的学生开始回答问题。
 * 给你一个长度为 n 且下标从 0 开始的整数数组 chalk 和一个整数 k 。一开始粉笔盒里总共有 k 支粉笔。当编号为 i 的学生回答问题时，他会消耗 chalk[i] 支粉笔。如果剩余粉笔数量 严格小于 chalk[i] ，那么学生 i 需要 补充 粉笔。
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/11 11:30
 */
public class 补充粉笔 {

    @Test
    public void Test() {
        int i = chalkReplacer2(new int[]{1, 2, 3, 4},
                30);
        System.out.println(i);
    }


    public int chalkReplacer(int[] chalk, int k) {
        //首先求出一轮需要多少粉笔
        long sum = 0L;
        for (int i : chalk) {
            sum += i;
        }
        //剩余的粉笔  遍历一遍累加就能找到了
        long l1 = k % sum;

        for (int i = 0; i < chalk.length; i++) {
            //到底了  已经用完了
            if (l1 - chalk[i] < 0) {
                return i;
            }
            //当前人用了多少直接减去
            l1 -= chalk[i];
        }
        throw new RuntimeException("未找到");
    }


    public int chalkReplacer2(int[] chalk, int k) {
        int n = chalk.length;
        //下标从1开始  下面都需要+1
        long[] longs = new long[n + 1];
        //第一个值不用管
        for (int i = 0; i < n; ++i) {
            //当前值是上一个加上当前的值
            longs[i + 1] = longs[i] + chalk[i];
        }
        //求余  下标从1开始
        k %= longs[n];
        //使用二分查
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            //不能使用等于 如果如果当前人刚好用完 需要下一个人补充
            if (k < longs[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

}
