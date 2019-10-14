/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.chat;

import java.util.List;

import com.lj.business.supcon.netty.message.BaseRequest;
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfoGroup;

/**
 * 
 * 类说明：推送离线聊天记录参数
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
public class OfflineChatInfo extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5769768855919321748L;
	
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
		builder.append("OfflineChatInfo [total=");
		builder.append(total);
		builder.append(", groupList=");
		builder.append(groupList);
		builder.append("]");
		return builder.toString();
	}
}
