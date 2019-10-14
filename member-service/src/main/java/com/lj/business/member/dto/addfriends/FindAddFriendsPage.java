package com.lj.business.member.dto.addfriends;

import java.util.Date;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindAddFriendsPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8940294008423777354L; 

    /**
     * 客户编号 .
     */
    private String memberNo;
    
    /**
     * 客户名称 .
     */
    private String memberName;
    
    /**
     * 手机号
     */
    private String mobile;
    
    /**
     * 添加好友状态：Y通过、N等待验证、F失败、E过期 .
     */
    private String addStatus;
    
    /**
     * QQ号
     */
    private String noQQ;
    
    /**
     * 微信OPENID
     */
    private String wxOpenId;
    
    /**
     * 微信号
     */
    private String noWx;
    
    /**
     * 标识：addWxFriends - null或0标识addFriends，1标识addWxFriends
     */
    private Integer addWxFriends;
    
    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 导购编号 .
     */
    private String memberNoGm;
    
    /**
     * 请求添加时间 - 从
     */
    private Date requestTimeBegin;
    
    /**
     * 商户编号
     */
    private String merchantNo;
    
    /**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子、5接口搜索手机号添加、6接口搜索微信号添加、7接口搜索QQ号添加
     */
    private List<Integer> wxAddTypes;
    
    /**
     * 导购主动添加标识：1是、0否
     */
    private Integer gmAddFlag;

	/**
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * @return the requestTimeBegin
	 */
	public Date getRequestTimeBegin() {
		return requestTimeBegin;
	}

	/**
	 * @param requestTimeBegin the requestTimeBegin to set
	 */
	public void setRequestTimeBegin(Date requestTimeBegin) {
		this.requestTimeBegin = requestTimeBegin;
	}
	
	/**
     * 商户编号
     */
	public String getMerchantNo() {
		return merchantNo;
	}
	/**
     * 商户编号
     */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	/**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子、5接口搜索手机号添加、6接口搜索微信号添加、7接口搜索QQ号添加
     */
	public List<Integer> getWxAddTypes() {
		return wxAddTypes;
	}
	/**
     * 客户新增方式：1导购扫码、2客户扫码、3手动新增、4勾子、5接口搜索手机号添加、6接口搜索微信号添加、7接口搜索QQ号添加
     */
	public void setWxAddTypes(List<Integer> wxAddTypes) {
		this.wxAddTypes = wxAddTypes;
	}
	
	public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddStatus() {
        return addStatus;
    }

    public void setAddStatus(String addStatus) {
        this.addStatus = addStatus;
    }

    public String getNoQQ() {
        return noQQ;
    }

    public void setNoQQ(String noQQ) {
        this.noQQ = noQQ;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Integer getAddWxFriends() {
        return addWxFriends;
    }

    public void setAddWxFriends(Integer addWxFriends) {
        this.addWxFriends = addWxFriends;
    }

    public String getNoWx() {
        return noWx;
    }

    public void setNoWx(String noWx) {
        this.noWx = noWx;
    }
    
    /**
     * 导购主动添加标识：1是、0否
     */
    public Integer getGmAddFlag() {
		return gmAddFlag;
	}
    /**
     * 导购主动添加标识：1是、0否
     */
	public void setGmAddFlag(Integer gmAddFlag) {
		this.gmAddFlag = gmAddFlag;
	}

	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindAddFriendsPage [memberNo=");
        builder.append(memberNo);
        builder.append(", memberName=");
        builder.append(memberName);
        builder.append(", mobile=");
        builder.append(mobile);
        builder.append(", addStatus=");
        builder.append(addStatus);
        builder.append(", noQQ=");
        builder.append(noQQ);
        builder.append(", wxOpenId=");
        builder.append(wxOpenId);
        builder.append(", noWx=");
        builder.append(noWx);
        builder.append(", addWxFriends=");
        builder.append(addWxFriends);
        builder.append(", labelName=");
        builder.append(labelName);
        builder.append(", memberNoGm=");
        builder.append(memberNoGm);
        builder.append(", requestTimeBegin=");
        builder.append(requestTimeBegin);
        builder.append(", merchantNo=");
        builder.append(merchantNo);
        builder.append(", wxAddTypes=");
        builder.append(wxAddTypes);
        builder.append(", gmAddFlag=");
        builder.append(gmAddFlag);
        builder.append("]");
        return builder.toString();
    }

}
