package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ShopScheduleDto implements Serializable { 

    /** CODE*/
    private String code;

    /** 门诊编号*/
    private String shopNo;

    /** 门诊名称*/
    private String shopName;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 班次名*/
    private String scheduleName;

    /** 上班时间*/
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date workTime;

    /** 下班时间*/
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date offTime;

    /** 是否可约诊appointment（N否 Y是)*/
    private String aptFlag;

    /** 是否删除（N未删除 Y删除）*/
    private String delFlag;

    /** 更新人*/
    private String updateId;

    /** 更新时间*/
    private Date updateDate;

    /** 创建人*/
    private String createId;

    /** 创建时间*/
    private Date createDate;
    
    //////////////////////////////////////////////////
    /** CODE list*/
    private List<String> codes;


    /** 上班时间 格式：HH:mm:ss */
    private String workTimeStr;

    /** 下班时间 格式：HH:mm:ss */
    private String offTimeStr;
    
    /** 查询工作日期 格式：yyyy-MM-dd */
    private String workDateStr;
    
    
    public String getWorkDateStr() {
		return workDateStr;
	}

	public void setWorkDateStr(String workDateStr) {
		this.workDateStr = workDateStr;
	}

	public String getWorkTimeStr() {
		return workTimeStr;
	}

	public void setWorkTimeStr(String workTimeStr) {
		this.workTimeStr = workTimeStr;
	}

	public String getOffTimeStr() {
		return offTimeStr;
	}

	public void setOffTimeStr(String offTimeStr) {
		this.offTimeStr = offTimeStr;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public List<String> getCodes() {
		return codes;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	@Override
	public String toString() {
		return "ShopScheduleDto [code=" + code + ", shopNo=" + shopNo + ", shopName=" + shopName + ", merchantNo="
				+ merchantNo + ", merchantName=" + merchantName + ", scheduleName=" + scheduleName + ", workTime="
				+ workTime + ", offTime=" + offTime + ", aptFlag=" + aptFlag + ", delFlag=" + delFlag + ", updateId="
				+ updateId + ", updateDate=" + updateDate + ", createId=" + createId + ", createDate=" + createDate
				+ ", codes=" + codes + ", workTimeStr=" + workTimeStr + ", offTimeStr=" + offTimeStr + ", workDateStr="
				+ workDateStr + "]";
	}
    
}
