/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.terminalimstatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;

/**
 * 
 * 类说明：终端登录中控服务器响应参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月2日
 */
public class TerminalImLoginResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4679386202533685422L;

	/**
	 * 导购基本信息
	 */
	private FindGuidMemberReturn findGuidMemberReturn;
	/**
	 * 终端基本信息
	 */
	private FindShopTerminalReturn findShopTerminalReturn;
	
	/**
	 * 返回客户端绑定的微信号列表，如中控客户端登录，则在支持微信分身的情况下返回中控终端绑定的微信列表
	 */
	private List<String> noWxList = new ArrayList<String>();
	
	public FindShopTerminalReturn getFindShopTerminalReturn() {
		return findShopTerminalReturn;
	}

	public void setFindShopTerminalReturn(FindShopTerminalReturn findShopTerminalReturn) {
		this.findShopTerminalReturn = findShopTerminalReturn;
	}

	/**
	 * 
	 *
	 * 方法说明：添加微信号列表
	 *
	 * @param noWx
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月9日
	 *
	 */
	public void addNoWx(String noWx) {
		noWxList.add(noWx);
	}
	
	/**
	 * @return the findGuidMemberReturn
	 */
	public FindGuidMemberReturn getFindGuidMemberReturn() {
		return findGuidMemberReturn;
	}

	/**
	 * @param findGuidMemberReturn the findGuidMemberReturn to set
	 */
	public void setFindGuidMemberReturn(FindGuidMemberReturn findGuidMemberReturn) {
		this.findGuidMemberReturn = findGuidMemberReturn;
	}

	/**
	 * @return the noWxList
	 */
	public List<String> getNoWxList() {
		return noWxList;
	}

	/**
	 * @param noWxList the noWxList to set
	 */
	public void setNoWxList(List<String> noWxList) {
		this.noWxList = noWxList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TerminalImLoginResponse [findGuidMemberReturn=");
		builder.append(findGuidMemberReturn);
		builder.append(", findShopTerminalReturn=");
		builder.append(findShopTerminalReturn);
		builder.append(", noWxList=");
		builder.append(noWxList);
		builder.append("]");
		return builder.toString();
	}

}
