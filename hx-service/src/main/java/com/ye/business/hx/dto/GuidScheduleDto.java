package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GuidScheduleDto implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

    /** 类型(当周WEEK、固定FIXED)*/
    private String type;

    /** 星期几(1是星期一，依次类推7是星期日)*/
    private Integer dayNum;

    /** 班次code*/
    private String scheduleCode;
    
    /** 班次code集合，多个则用英文逗号分割*/
    private String scheduleCodes;
    
    /** 班次json集合*/
    private String scheduleJson;
    /** 创建人*/
    private String createId;

    /** 创建时间*/
    private Date createDate;
    
    /** 上班时间*/
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date workTime;

    /** 下班时间*/
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date offTime;
    
    
	/** 查询所属角色 */
	private String roleEnname;
	/** 查询员工集合 */
	private List<String> memberNoGuids;
	   /** 班次名*/
    private String scheduleName;
    
    /** 查询是否可约诊appointment（N否 Y是)*/
    private String aptFlag;
    /** 查询上班时间 格式：HH:mm:ss */
    private String workTimeStr;

    /** 查询下班时间 格式：HH:mm:ss */
    private String offTimeStr;
    
    /** 查询工作日期 格式：yyyy-MM-dd */
    private String workDateStr;

    
    /** 查询开始日期String：YYYY-MM-DD*/
    private String startDateStr;
    
    /** 查询结束日期String：YYYY-MM-DD*/
    private String endDateStr;
    
    /** 查询开始日期*/
    private Date startDate;
    
    /** 查询结束日期*/
    private Date endDate;
    /** 日期*/
    private Date workDate;
    
    
    public String getScheduleJson() {
		return scheduleJson;
	}

	public void setScheduleJson(String scheduleJson) {
		this.scheduleJson = scheduleJson;
	}

	public String getScheduleCodes() {
		return scheduleCodes;
	}

	public void setScheduleCodes(String scheduleCodes) {
		this.scheduleCodes = scheduleCodes;
	}

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

	public String getRoleEnname() {
		return roleEnname;
	}

	public void setRoleEnname(String roleEnname) {
		this.roleEnname = roleEnname;
	}

	public List<String> getMemberNoGuids() {
		return memberNoGuids;
	}

	public void setMemberNoGuids(List<String> memberNoGuids) {
		this.memberNoGuids = memberNoGuids;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String getAptFlag() {
		return aptFlag;
	}

	public void setAptFlag(String aptFlag) {
		this.aptFlag = aptFlag;
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

	public String getWorkDateStr() {
		return workDateStr;
	}

	public void setWorkDateStr(String workDateStr) {
		this.workDateStr = workDateStr;
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

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	@Override
	public String toString() {
		return "GuidScheduleDto [code=" + code + ", memberNoGuid=" + memberNoGuid + ", memberNameGuid=" + memberNameGuid
				+ ", shopNo=" + shopNo + ", shopName=" + shopName + ", merchantNo=" + merchantNo + ", merchantName="
				+ merchantName + ", type=" + type + ", dayNum=" + dayNum + ", scheduleCode=" + scheduleCode
				+ ", createId=" + createId + ", createDate=" + createDate + ", workTime=" + workTime + ", offTime="
				+ offTime + ", roleEnname=" + roleEnname + ", memberNoGuids=" + memberNoGuids + ", scheduleName="
				+ scheduleName + ", aptFlag=" + aptFlag + ", workTimeStr=" + workTimeStr + ", offTimeStr=" + offTimeStr
				+ ", workDateStr=" + workDateStr + ", startDateStr=" + startDateStr + ", endDateStr=" + endDateStr
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", workDate=" + workDate + "]";
	}
    
    
}
