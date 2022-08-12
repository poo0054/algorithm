package poo0054.基础算法.二分查找;

import org.junit.Test;

/**
 * 给你一个浮点数 hour ，表示你到达办公室可用的总通勤时间。要到达办公室，你必须按给定次序乘坐 n 趟列车。另给你一个长度为 n 的整数数组 dist ，其中 dist[i] 表示第 i 趟列车的行驶距离（单位是千米）。<p>
 * 每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。
 * 例如，第 1 趟列车需要 1.5 小时，那你必须再等待 0.5 小时，搭乘在第 2 小时发车的第 2 趟列车。
 * 返回能满足你准时到达办公室所要求全部列车的 最小正整数 时速（单位：千米每小时），如果无法准时到达，则返回 -1 。
 * 生成的测试用例保证答案不超过 107 ，且 hour 的 小数点后最多存在两位数字 。
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/11 11:30
 */
public class 列车时间 {

    @Test
    public void Test() {
        int i = minSpeedOnTime(new int[]{1, 3, 2}, 6);
        System.out.println(i);
    }


    /**
     * 根据时间二分查找
     *
     * @param dist 列车集合
     * @param hour 总共时间
     * @return 返回能满足你准时到达办公室所要求全部列车的 最小正整数 时速
     */
    public int minSpeedOnTime(int[] dist, double hour) {
        if (0 == dist.length) {
            return -1;
        }

        //时间二分查找，最小正整数  0不能算
        int l = 1, r = (int) Math.pow(10, 7);
        while (l < r) {
            //中间值  最小正整数
            int min = l + r >> 1;
            //是取最小值  尽量排除右边值  ———————— 取最大值则相反
            if (checkOut(dist, hour, min)) {
                r = min;
            } else {
                //不满足 >
                l = min + 1;
            }
        }
        return checkOut(dist, hour, r) ? r : -1;
    }

    /**
     * 是否能够满足
     *
     * @param dist 列车车次
     * @param hour 总时长
     * @param min  速度
     * @return 是否满足
     */
    private boolean checkOut(int[] dist, double hour, int min) {
        double sum = 0.0;
        for (int i = 0; i < dist.length - 1; i++) {
            //当前消耗时间
            int v = dist[i] / min;
            sum += (v + (dist[i] % min > 0 ? 1 : 0));
        }
        //最后一段路
        sum += (dist[dist.length - 1] * 1.0 / min);

        return (hour - sum) >= 0;
    }

    /**
     * 示例
     */
    private int extracted(int[] dist, double hour) {
        int left = 1, right = (int) 1e7;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(dist, mid, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return check(dist, left, hour) ? left : -1;
    }

    /**
     * 示例
     *
     * @param dist  列车集合
     * @param speed 当前时间的中间值
     * @param hour  总共时间
     * @return 是否能够达到
     */
    private boolean check(int[] dist, int speed, double hour) {
        double res = 0;
        for (int i = 0; i < dist.length; ++i) {
            double cost = dist[i] * 1.0 / speed;
            res += (i == dist.length - 1 ? cost : Math.ceil(cost));
        }
        return res <= hour;
    }

}
