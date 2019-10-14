package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * 类说明：客情管理-列表查询-回参
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月23日
 */
public class FindClientPmTypeIndexReturn implements Serializable {

	private static final long serialVersionUID = -8864799345460937991L;

	/**
     * 客户编号  .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 微信号 .
     */
    private String noWx;
    
    /**
     * 昵称备注_微信 .
     */
    private String nickNameRemarkWx;
    
    
    /** 昵称备注_本地. */
    private String nickNameRemarkLocal;
    
    /**
     * 昵称_微信 .
     */
    private String nickNameWx;
    
    /**
     * 头像地址 .
     */
    private String headAddress;
    
    /**
     * 客户客户分类关联表CODE .
     */
    private String codePmTypePm;
    
    /**
     * 手机号 .
     */
    private String mobile;
    
    /** 完成百分比. */
    private Double ratioClientInfo;
    
    /** 创建时间. */
    private Date createDate;
    
    /**
     * 消费频率(天)
     */
    private Integer consumeFrequency;
    
    /**
     * 最后一次邀约状态
             应邀：Y
             未应邀：N
     */
    private String lastInviteStatus;
    
    /**
     * 最后一次邀约时间
	     * 邀请成功则为到店时间
	     * 未应约则为预计到店时间
     */
    private Date lastActionTime;
    
    /**
     * 消费次数
     */
    private int consumeCount;
    
    /**
     * 是否可以继续邀约
     */
    private boolean canInvite;
    
    /**
     * 显示文本内容
     */
    private String textName;
    
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
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * @return the nickNameRemarkWx
	 */
	public String getNickNameRemarkWx() {
		return nickNameRemarkWx;
	}

	/**
	 * @param nickNameRemarkWx the nickNameRemarkWx to set
	 */
	public void setNickNameRemarkWx(String nickNameRemarkWx) {
		this.nickNameRemarkWx = nickNameRemarkWx;
	}

	/**
	 * @return the nickNameRemarkLocal
	 */
	public String getNickNameRemarkLocal() {
		return nickNameRemarkLocal;
	}

	/**
	 * @param nickNameRemarkLocal the nickNameRemarkLocal to set
	 */
	public void setNickNameRemarkLocal(String nickNameRemarkLocal) {
		this.nickNameRemarkLocal = nickNameRemarkLocal;
	}

	/**
	 * @return the nickNameWx
	 */
	public String getNickNameWx() {
		return nickNameWx;
	}

	/**
	 * @param nickNameWx the nickNameWx to set
	 */
	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}

	/**
	 * @return the headAddress
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * @param headAddress the headAddress to set
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
	 * @return the codePmTypePm
	 */
	public String getCodePmTypePm() {
		return codePmTypePm;
	}

	/**
	 * @param codePmTypePm the codePmTypePm to set
	 */
	public void setCodePmTypePm(String codePmTypePm) {
		this.codePmTypePm = codePmTypePm;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the ratioClientInfo
	 */
	public Double getRatioClientInfo() {
		return ratioClientInfo;
	}

	/**
	 * @param ratioClientInfo the ratioClientInfo to set
	 */
	public void setRatioClientInfo(Double ratioClientInfo) {
		this.ratioClientInfo = ratioClientInfo;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the consumeFrequency
	 */
	public Integer getConsumeFrequency() {
		return consumeFrequency;
	}

	/**
	 * @param consumeFrequency the consumeFrequency to set
	 */
	public void setConsumeFrequency(Integer consumeFrequency) {
		this.consumeFrequency = consumeFrequency;
	}

	/**
	 * @return the lastInviteStatus
	 */
	public String getLastInviteStatus() {
		return lastInviteStatus;
	}

	/**
	 * @param lastInviteStatus the lastInviteStatus to set
	 */
	public void setLastInviteStatus(String lastInviteStatus) {
		this.lastInviteStatus = lastInviteStatus;
	}

	/**
	 * @return the lastActionTime
	 */
	public Date getLastActionTime() {
		return lastActionTime;
	}

	/**
	 * @param lastActionTime the lastActionTime to set
	 */
	public void setLastActionTime(Date lastActionTime) {
		this.lastActionTime = lastActionTime;
	}

	/**
	 * @return the consumeCount
	 */
	public int getConsumeCount() {
		return consumeCount;
	}

	/**
	 * @param consumeCount the consumeCount to set
	 */
	public void setConsumeCount(int consumeCount) {
		this.consumeCount = consumeCount;
	}

	/**
	 * @return the canInvite
	 */
	public boolean isCanInvite() {
		return canInvite;
	}

	/**
	 * @param canInvite the canInvite to set
	 */
	public void setCanInvite(boolean canInvite) {
		this.canInvite = canInvite;
	}

	/**
	 * @return the textName
	 */
	public String getTextName() {
		return textName;
	}

	/**
	 * @param textName the textName to set
	 */
	public void setTextName(String textName) {
		this.textName = textName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindClientPmTypeIndexReturn [memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", nickNameRemarkWx=");
		builder.append(nickNameRemarkWx);
		builder.append(", nickNameRemarkLocal=");
		builder.append(nickNameRemarkLocal);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", codePmTypePm=");
		builder.append(codePmTypePm);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", ratioClientInfo=");
		builder.append(ratioClientInfo);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", consumeFrequency=");
		builder.append(consumeFrequency);
		builder.append(", lastInviteStatus=");
		builder.append(lastInviteStatus);
		builder.append(", lastActionTime=");
		builder.append(lastActionTime);
		builder.append(", consumeCount=");
		builder.append(consumeCount);
		builder.append(", canInvite=");
		builder.append(canInvite);
		builder.append(", textName=");
		builder.append(textName);
		builder.append("]");
		return builder.toString();
	}

}
