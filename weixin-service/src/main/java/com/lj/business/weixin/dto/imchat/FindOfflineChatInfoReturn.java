/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 类说明：导购手机客户端查询离线聊天记录返回参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月4日
 */
public class FindOfflineChatInfoReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9026666690160363895L;

	/**
	 * 离线聊天总数
	 */
	private int total;
	
	/**
	 * 按客户分组聊天记录明细
	 */
	private List<FindOfflineChatInfoGroup> groupList;

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the groupList
	 */
	public List<FindOfflineChatInfoGroup> getGroupList() {
		return groupList;
	}

	/**
	 * @param groupList the groupList to set
	 */
	public void setGroupList(List<FindOfflineChatInfoGroup> groupList) {
		this.groupList = groupList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindOfflineChatInfoReturn [total=");
		builder.append(total);
		builder.append(", groupList=");
		builder.append(groupList);
		builder.append("]");
		return builder.toString();
	}

}
