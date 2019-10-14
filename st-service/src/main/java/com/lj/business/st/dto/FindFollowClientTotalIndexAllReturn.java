package com.lj.business.st.dto;

import java.io.Serializable;

public class FindFollowClientTotalIndexAllReturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7046730807894271302L;
	
	/**
	 * 完成度
	 */
	private FindFollowClientTotalIndexReturn findFollowClientTotalIndexReturn;
	
	/**
	 * 排名
	 */
	private FindGmIndexReturn findGmIndexReturn;

	public FindFollowClientTotalIndexReturn getFindFollowClientTotalIndexReturn() {
		return findFollowClientTotalIndexReturn;
	}

	public void setFindFollowClientTotalIndexReturn(
			FindFollowClientTotalIndexReturn findFollowClientTotalIndexReturn) {
		this.findFollowClientTotalIndexReturn = findFollowClientTotalIndexReturn;
	}

	public FindGmIndexReturn getFindGmIndexReturn() {
		return findGmIndexReturn;
	}

	public void setFindGmIndexReturn(FindGmIndexReturn findGmIndexReturn) {
		this.findGmIndexReturn = findGmIndexReturn;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(
				"FindFollowClientTotalIndexAllReturn [findFollowClientTotalIndexReturn=")
				.append(findFollowClientTotalIndexReturn)
				.append(", findGmIndexReturn=").append(findGmIndexReturn)
				.append("]");
		return builder.toString();
	}
	
}
