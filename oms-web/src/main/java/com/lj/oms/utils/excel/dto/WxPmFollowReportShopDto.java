package com.lj.oms.utils.excel.dto;

import java.io.Serializable;
import java.util.Date;
import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明：终端微信客户跟踪日报
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年8月13日
 */
public class WxPmFollowReportShopDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4538432806450726985L;
    
    /**
     * 日期 .
     */
    @ExcelField(title="日期", align=2, sort=1)
    private Date reportDate;
    /**
     * 终端代码 .
     */
    @ExcelField(title="终端代码", align=2, sort=5)
    private String shopCode;
    
    /**
     * 经销商代码
     */
    @ExcelField(title="经销商代码", align=2, sort=9)
    private String dealerCode;
    /**
     * 经销商名称
     */
    @ExcelField(title="经销商名称", align=2, sort=11)
    private String companyName;
    /**
     * 新增客户数
     */
    @ExcelField(title="新增客户数", align=3, sort=13)
    private Long numPmNew;
    /**
     * 未跟踪新增客户数
     */
    @ExcelField(title="未跟踪新增客户数", align=3, sort=15)
    private Long numPmNewNotFollow;
    /**
     * 已跟踪新增客户数
     */
    @ExcelField(title="已跟踪新增客户数", align=3, sort=17)
    private Long numPmNewFollow;
    
    /**
     * 老客户数
     */
    @ExcelField(title="老客户数", align=3, sort=19)
    private Long numPmOld;
    /**
     * 未跟踪老客户数
     */
    @ExcelField(title="未跟踪老客户数", align=3, sort=21)
    private Long numPmOldNotFollow;
    /**
     * 已跟踪老客户数
     */
    @ExcelField(title="已跟踪老客户数", align=3, sort=23)
    private Long numPmOldFollow;
    
    /**
     * 客户总数
     */
    @ExcelField(title="客户总数", align=3, sort=25)
    private Long numPmTotal;

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getNumPmNew() {
        return numPmNew;
    }

    public void setNumPmNew(Long numPmNew) {
        this.numPmNew = numPmNew;
    }

    public Long getNumPmNewNotFollow() {
        return numPmNewNotFollow;
    }

    public void setNumPmNewNotFollow(Long numPmNewNotFollow) {
        this.numPmNewNotFollow = numPmNewNotFollow;
    }

    public Long getNumPmNewFollow() {
        return numPmNewFollow;
    }

    public void setNumPmNewFollow(Long numPmNewFollow) {
        this.numPmNewFollow = numPmNewFollow;
    }

    public Long getNumPmOld() {
        return numPmOld;
    }

    public void setNumPmOld(Long numPmOld) {
        this.numPmOld = numPmOld;
    }

    public Long getNumPmOldNotFollow() {
        return numPmOldNotFollow;
    }

    public void setNumPmOldNotFollow(Long numPmOldNotFollow) {
        this.numPmOldNotFollow = numPmOldNotFollow;
    }

    public Long getNumPmOldFollow() {
        return numPmOldFollow;
    }

    public void setNumPmOldFollow(Long numPmOldFollow) {
        this.numPmOldFollow = numPmOldFollow;
    }

    public Long getNumPmTotal() {
        return numPmTotal;
    }

    public void setNumPmTotal(Long numPmTotal) {
        this.numPmTotal = numPmTotal;
    }

}
