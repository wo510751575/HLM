/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.dto.contacts;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 类说明：发送客户认领消息入参
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月16日
 */
public class MemberClaimMessage implements Serializable {

	private static final long serialVersionUID = -7446371469037112351L;
	
	/**
	 * 待通知的导购编号列表
	 */
	private List<String> memberNoGmList;
	
	/**
	 * 待认领记录code，非空
	 */
	private String code;
	
	/**
	 * 客户微信，非空
	 */
	private String noWx;
	
	/**
	 * 客户微信别名
	 */
	private String alias;
	
	/**
	 * 微信昵称
	 */
	private String nickNameWx;
	
	/**
	 * 微信昵称备注
	 */
	private String nickNameRemarkWx;
	
	/**
	 * 微信头像地址
	 */
	private String headAddress;

    /**
     * 性别:
             男：male
             女：female
             未知：unknown .
     */
    private String sex;
    
    /**
     * 添加时间,客户扫码添加时间戳
     */
    private Long createTime;

	/**
	 * @return the memberNoGmList
	 */
	public List<String> getMemberNoGmList() {
		return memberNoGmList;
	}

	/**
	 * @param memberNoGmList the memberNoGmList to set
	 */
	public void setMemberNoGmList(List<String> memberNoGmList) {
		this.memberNoGmList = memberNoGmList;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
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
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the createTime
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberClaimMessage [memberNoGmList=");
		builder.append(memberNoGmList);
		builder.append(", code=");
		builder.append(code);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", nickNameRemarkWx=");
		builder.append(nickNameRemarkWx);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

}
