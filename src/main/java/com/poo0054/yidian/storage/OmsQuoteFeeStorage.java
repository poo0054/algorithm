package com.poo0054.yidian.storage;

import lombok.Data;

/**
 * 报价单-仓储费用计算
 *
 * @author ZhangZhi
 * @date 2022/09/02
 */
@Data
public class OmsQuoteFeeStorage {

    /**
     * 主键
     */
    private Long id;

    /**
     * 报价模板代码
     */
    private String templateCode;

    /**
     * 产品代码
     */
    private String productCode;

    /**
     * 报价单主体ID
     */
    private Long templateId;

    /**
     * 规格型号
     */
    private String specCode;

    /**
     * 开始天数
     */
    private Integer beginDays;

    /**
     * 结算天数
     */
    private Integer endDays;

    /**
     * 最小体积
     */
    private Double minVolume;

    /**
     * 标准价格；1-9月份
     */
    private Double price;

    /**
     * 附加费用；10-12月份
     */
    private Double peakSeasonPrice;

    /**
     * 备注
     */
    private String remark;


}