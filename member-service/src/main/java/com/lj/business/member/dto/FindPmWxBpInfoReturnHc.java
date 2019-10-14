package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindPmWxBpInfoReturn.
 */
public class FindPmWxBpInfoReturnHc implements Serializable {

	private static final long serialVersionUID = 3959779606700052372L;
	
	/**
    * 客户编号  .
    */
   private String memberNo;
   
   /**
    * 客户名称
    */
   private String memberName;
    /**
     * 微信号 .
     */
    private String noWx;
    
    /**
     * 朋友圈ID .
     */
    private String idWx;
    
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
     * 动态描述 .
     */
    private String behaviorDesc;
    /**
     * 行为时间 .
     */
    private Date behaviorDate;
    
    /**
     * 客户分类名称 .
     */
    private String typeName;
    
    /**
     * 客户分类类型
     */
    private String pmTypeType;
	
	/**
	 * Gets the 微信号 .
	 *
	 * @return the 微信号 
	 */
	public String getNoWx() {
		return noWx;
	}
	
	/**
	 * Sets the 微信号 .
	 *
	 * @param noWx the new 微信号 
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}
	
	/**
	 * Gets the 朋友圈ID .
	 *
	 * @return the 朋友圈ID 
	 */
	public String getIdWx() {
		return idWx;
	}
	
	/**
	 * Sets the 朋友圈ID .
	 *
	 * @param idWx the new 朋友圈ID 
	 */
	public void setIdWx(String idWx) {
		this.idWx = idWx;
	}
	
	/**
	 * Gets the 昵称备注_微信 .
	 *
	 * @return the 昵称备注_微信 
	 */
	public String getNickNameRemarkWx() {
		return nickNameRemarkWx;
	}
	
	/**
	 * Sets the 昵称备注_微信 .
	 *
	 * @param nickNameRemarkWx the new 昵称备注_微信 
	 */
	public void setNickNameRemarkWx(String nickNameRemarkWx) {
		this.nickNameRemarkWx = nickNameRemarkWx;
	}
	
	/**
	 * Gets the 昵称_微信 .
	 *
	 * @return the 昵称_微信 
	 */
	public String getNickNameWx() {
		return nickNameWx;
	}
	
	/**
	 * Sets the 昵称_微信 .
	 *
	 * @param nickNameWx the new 昵称_微信 
	 */
	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}
	
	/**
	 * Gets the 头像地址 .
	 *
	 * @return the 头像地址 
	 */
	public String getHeadAddress() {
		return headAddress;
	}
	
	/**
	 * Sets the 头像地址 .
	 *
	 * @param headAddress the new 头像地址 
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}
	
	/**
	 * Gets the 动态描述 .
	 *
	 * @return the 动态描述 
	 */
	public String getBehaviorDesc() {
		return behaviorDesc;
	}
	
	/**
	 * Sets the 动态描述 .
	 *
	 * @param behaviorDesc the new 动态描述 
	 */
	public void setBehaviorDesc(String behaviorDesc) {
		this.behaviorDesc = behaviorDesc;
	}
	
	/**
	 * Gets the 行为时间 .
	 *
	 * @return the 行为时间 
	 */
	public Date getBehaviorDate() {
		return behaviorDate;
	}
	
	/**
	 * Sets the 行为时间 .
	 *
	 * @param behaviorDate the new 行为时间 
	 */
	public void setBehaviorDate(Date behaviorDate) {
		this.behaviorDate = behaviorDate;
	}

	/**
	 * Gets the member no.
	 *
	 * @return the member no
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the member no.
	 *
	 * @param memberNo the new member no
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
	 * Gets the nick name remark local.
	 *
	 * @return the nick name remark local
	 */
	public String getNickNameRemarkLocal() {
		return nickNameRemarkLocal;
	}

	/**
	 * Sets the nick name remark local.
	 *
	 * @param nickNameRemarkLocal the new nick name remark local
	 */
	public void setNickNameRemarkLocal(String nickNameRemarkLocal) {
		this.nickNameRemarkLocal = nickNameRemarkLocal;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the pmTypeType
	 */
	public String getPmTypeType() {
		return pmTypeType;
	}

	/**
	 * @param pmTypeType the pmTypeType to set
	 */
	public void setPmTypeType(String pmTypeType) {
		this.pmTypeType = pmTypeType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPmWxBpInfoReturnHc [memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", idWx=");
		builder.append(idWx);
		builder.append(", nickNameRemarkWx=");
		builder.append(nickNameRemarkWx);
		builder.append(", nickNameRemarkLocal=");
		builder.append(nickNameRemarkLocal);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", behaviorDesc=");
		builder.append(behaviorDesc);
		builder.append(", behaviorDate=");
		builder.append(behaviorDate);
		builder.append(", typeName=");
		builder.append(typeName);
		builder.append(", pmTypeType=");
		builder.append(pmTypeType);
		builder.append("]");
		return builder.toString();
	}

}
