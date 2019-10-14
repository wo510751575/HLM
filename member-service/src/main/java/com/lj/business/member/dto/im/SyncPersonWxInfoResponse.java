/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.im;

import java.io.Serializable;

/**
 * 
 * 类说明：同步客户微信基本信息返回参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月8日
 */
public class SyncPersonWxInfoResponse implements Serializable {

	private static final long serialVersionUID = -374741813562875449L;

	/**
	 * 客户微信基本信息是否有更新
	 */
	private boolean changed;
	
	/**
	 * 客户版本号,客户有更新则返回更新后的客户版本号
	 */
	private Long version;

	/**
	 * @return the changed
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * @param changed the changed to set
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SyncPersonWxInfoResponse [changed=");
		builder.append(changed);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}

}
