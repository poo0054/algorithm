package com.poo0054.yidian.storage;

import cn.hutool.core.util.ObjectUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/9/29 15:38
 */
public class StoragesCount {

    /**
     * 那些月份需要附加费
     */
    public static List<Integer> peak = new ArrayList<>();

    public static void main(String[] args) {
        List<OmsQuoteFeeStorage> init = init();
        count(LocalDate.of(2000, 3, 1), LocalDate.of(2000, 9, 2), init);
    }

    /**
     * 初始化
     */
    private static List<OmsQuoteFeeStorage> init() {
        List<OmsQuoteFeeStorage> feeStorages = new ArrayList<>();
        OmsQuoteFeeStorage feeStorage = new OmsQuoteFeeStorage();
        feeStorage.setBeginDays(1);
        feeStorage.setEndDays(10);
        feeStorage.setPrice(10.0);
        feeStorage.setPeakSeasonPrice(10.0);

        OmsQuoteFeeStorage feeStorage1 = new OmsQuoteFeeStorage();
        feeStorage1.setBeginDays(10);
        feeStorage1.setEndDays(20);
        feeStorage1.setPrice(20.0);
        feeStorage1.setPeakSeasonPrice(10.0);

        OmsQuoteFeeStorage feeStorage2 = new OmsQuoteFeeStorage();
        feeStorage2.setBeginDays(20);
        feeStorage2.setEndDays(30);
        feeStorage2.setPrice(30.0);
        feeStorage2.setPeakSeasonPrice(10.0);

        OmsQuoteFeeStorage feeStorage3 = new OmsQuoteFeeStorage();
        feeStorage3.setBeginDays(30);
        feeStorage3.setEndDays(40);
        feeStorage3.setPrice(50.0);
        feeStorage3.setPeakSeasonPrice(10.0);

        feeStorages.add(feeStorage);
        feeStorages.add(feeStorage1);
        feeStorages.add(feeStorage2);
        feeStorages.add(feeStorage3);

        peak.add(8);
        peak.add(11);
        return feeStorages;
    }

    public static void count(LocalDate start, LocalDate end, List<OmsQuoteFeeStorage> feeStorages) {
        //根据开始时间进行排序
        if (ObjectUtil.isEmpty(feeStorages)) {
            System.out.println("计算参数不能为空");
        }

        if (ObjectUtil.isAllNotEmpty(start, end)) {
            System.out.println("开始时间和结束时间不能为空");
        }
        feeStorages = feeStorages.stream().sorted(Comparator.comparing(OmsQuoteFeeStorage::getBeginDays)).collect(Collectors.toList());
        feeStorages.get(0).setBeginDays(feeStorages.get(0).getBeginDays() - 1);
        for (OmsQuoteFeeStorage storage : feeStorages) {
            System.out.printf(" %s - %s 天  - 总共%s天  标准：%s/天 附加费：%s/天  \n", storage.getBeginDays(), storage.getEndDays(), storage.getEndDays() - storage.getBeginDays(), storage.getPrice(), storage.getPeakSeasonPrice());
        }
        int size = feeStorages.size();
        //总钱数
        BigDecimal sumMoney = new BigDecimal(0);
        //当前阶段的开始时间
        LocalDate startDate = start;

        int index = 0;
        while (startDate.compareTo(end) < 0) {
            //当前阶段
            OmsQuoteFeeStorage feeStorage = feeStorages.get(index);
            //当前阶段的结束时间  - 下阶段的结束时间   开始时间不包括 -不需要减一
            LocalDate endDate = startDate.plusDays(feeStorage.getEndDays() - feeStorage.getBeginDays());
            sumMoney = sumMoney.add(sumCurrent(startDate, endDate, end, feeStorage));

            startDate = endDate;
            if (index < size - 1) {
                index++;
            }
        }
        System.out.println("总共花费：" + sumMoney);
    }

    /**
     * 计算当前阶段的费用
     *
     * @param startDate  当前阶段开始时间
     * @param endDate    当前阶段结束时间
     * @param end        总结束时间
     * @param feeStorage 当前阶段
     */
    private static BigDecimal sumCurrent(LocalDate startDate, LocalDate endDate, LocalDate end, OmsQuoteFeeStorage feeStorage) {
        BigDecimal bigDecimal = new BigDecimal(0);
        //下阶段开始时间与总结束时间比较
        if (endDate.compareTo(end) > 0) {
            //不能到达下个阶段此阶段就结束了
            bigDecimal = bigDecimal.add(currentMoney(startDate, end, feeStorage));
        } else {
            //还有下一个阶段
            bigDecimal = bigDecimal.add(currentMoney(startDate, endDate, feeStorage));
        }
        return bigDecimal;
    }

    /**
     * 计算指定时间的价格并打印
     *
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param feeStorage 计算参数
     * @return 总费用
     */
    private static BigDecimal currentMoney(LocalDate startDate, LocalDate endDate, OmsQuoteFeeStorage feeStorage) {
        BigDecimal bigDecimal = new BigDecimal(0);
        //标准价格
        BigDecimal price = BigDecimal.valueOf(feeStorage.getPrice());
        //附加费
        BigDecimal peakPrice = BigDecimal.valueOf(feeStorage.getPeakSeasonPrice());

        //用来校验上个月是否与下个月一样 false: 标准  true：旺季
        Boolean b = null;
        //这个阶段开始时间
        LocalDate stageStart = null;
        //这个阶段标准费用
        BigDecimal stageMoney = new BigDecimal(0);
        //这个阶段附加费
        BigDecimal stagePeakMoney = new BigDecimal(0);

        // 开始时间
        while (startDate.compareTo(endDate) <= 0) {
            //月初
            LocalDate nextMonths = startDate.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
            //如果月份相等  当前价格需要加上附加费
            if (peak.contains(startDate.getMonthValue())) {
                //旺季
                if (null != b && !b) {
                    //首先算出标准费用  从阶段开始时间到上个月底
                    //这个阶段的结束时间为上个月的月底
                    LocalDate endExclusive = startDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
                    long days = stageStart.until(endExclusive, ChronoUnit.DAYS);
                    stageMoney = price.multiply(new BigDecimal(days));
                    //上个月是标准  打印标准费用的价格
                    sout(stageStart, endExclusive, stageMoney, stagePeakMoney, price, peakPrice);
                    bigDecimal = bigDecimal.add(stageMoney);
                    stageMoney = new BigDecimal(0);
                    stageStart = endExclusive;
                }
                if (null == b) {
                    stageStart = startDate;
                }
                //不存在下个月
                if (endDate.compareTo(nextMonths) < 0) {
                    //当前不存在下个月
                    long days = stageStart.until(endDate, ChronoUnit.DAYS);
//                    int days = LocalDateTimeUtil.betweenPeriod(stageStart, endDate).getDays();
                    //标准价格
                    stageMoney = price.multiply(new BigDecimal(days));
                    stagePeakMoney = peakPrice.multiply(new BigDecimal(days));
                    //当前
                    sout(stageStart, endDate, stageMoney, stagePeakMoney, price, peakPrice);
                    return bigDecimal.add(stageMoney).add(stagePeakMoney);
                }
                b = true;
            } else {
                //上个月是旺季需要打印
                if (null != b && b) {
                    //首先算出旺季费用
                    LocalDate endExclusive = startDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
                    long days = stageStart.until(endExclusive, ChronoUnit.DAYS);
                    //标准价格
                    stageMoney = price.multiply(new BigDecimal(days));
                    stagePeakMoney = peakPrice.multiply(new BigDecimal(days));

                    sout(stageStart, endExclusive, stageMoney, stagePeakMoney, price, peakPrice);
                    bigDecimal = bigDecimal.add(stageMoney).add(stagePeakMoney);
                    //清零
                    stageMoney = new BigDecimal(0);
                    stagePeakMoney = new BigDecimal(0);
                    stageStart = endExclusive;
                }
                //非旺季
                if (null == b) {
                    stageStart = startDate;
                }

                //不存在下个月
                if (endDate.compareTo(nextMonths) < 0) {
                    //当前不存在下个月
                    long days = stageStart.until(endDate, ChronoUnit.DAYS);
                    //标准价格
                    stageMoney = stageMoney.add(price.multiply(new BigDecimal(days)));
                    //打印
                    sout(stageStart, endDate, stageMoney, stagePeakMoney, price, peakPrice);
                    return bigDecimal.add(stageMoney).add(stagePeakMoney);
                }
                //当前距离月底多少天
                b = false;

            }
            //设置为这个月的月底
            startDate = nextMonths;
        }
        return bigDecimal;
    }

    /**
     * 打印
     *
     * @param stageStart     开始时间
     * @param endDate        结束时间
     * @param stageMoney     基本费用
     * @param stagePeakMoney 附加费用
     * @param money          基本费用单价
     * @param peakPrice      附加费用单价
     */
    private static void sout(LocalDate stageStart, LocalDate endDate, BigDecimal stageMoney, BigDecimal stagePeakMoney, BigDecimal money, BigDecimal peakPrice) {
        System.out.printf("当前从%s到%s，标准费用：%s,附加费：%s 总共：%s ,单价：%s/一天  ， 附加费：%s/天 \n", stageStart, endDate, stageMoney, stagePeakMoney, stageMoney.add(stagePeakMoney), money, peakPrice);
    }

}
