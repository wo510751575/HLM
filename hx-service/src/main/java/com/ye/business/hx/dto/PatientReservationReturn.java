package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约记录
 * 
 * @author sjiying
 *
 */
public class PatientReservationReturn implements Serializable {
	
	/** CODE*/
    private String code;

	/** 员工编号*/
    private String memberNoGuid;

    /** 员工姓名*/
    private String memberNameGuid;
    
    /** 门诊编号*/
    private String shopNo;

    /** 门诊名称*/
    private String shopName;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 日期*/
    private Date workDate;

    /** 星期几(1是星期一，依次类推7是星期日)*/
    private Integer dayNum;

    /** 班次code*/
    private String scheduleCode;

    /** 班次名*/
    private String scheduleName;

    /** 上班时间*/
    private Date workTime;

    /** 下班时间*/
    private Date offTime;

    /** 是否可约诊appointment（N否 Y是)*/
    private String aptFlag;

    /** 类型(当周WEEK、固定FIXED)*/
    private String type;
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMemberNoGuid() {
        return memberNoGuid;
    }

    public void setMemberNoGuid(String memberNoGuid) {
        this.memberNoGuid = memberNoGuid == null ? null : memberNoGuid.trim();
    }

    public String getMemberNameGuid() {
        return memberNameGuid;
    }

    public void setMemberNameGuid(String memberNameGuid) {
        this.memberNameGuid = memberNameGuid == null ? null : memberNameGuid.trim();
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public String getScheduleCode() {
        return scheduleCode;
    }

    public void setScheduleCode(String scheduleCode) {
        this.scheduleCode = scheduleCode == null ? null : scheduleCode.trim();
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName == null ? null : scheduleName.trim();
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public Date getOffTime() {
        return offTime;
    }

    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    public String getAptFlag() {
        return aptFlag;
    }

    public void setAptFlag(String aptFlag) {
        this.aptFlag = aptFlag == null ? null : aptFlag.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

	@Override
	public String toString() {
		return "PatientReservationReturn [code=" + code + ", memberNoGuid=" + memberNoGuid + ", memberNameGuid="
				+ memberNameGuid + ", shopNo=" + shopNo + ", shopName=" + shopName + ", merchantNo=" + merchantNo
				+ ", merchantName=" + merchantName + ", workDate=" + workDate + ", dayNum=" + dayNum + ", scheduleCode="
				+ scheduleCode + ", scheduleName=" + scheduleName + ", workTime=" + workTime + ", offTime=" + offTime
				+ ", aptFlag=" + aptFlag + ", type=" + type + "]";
	}

}
