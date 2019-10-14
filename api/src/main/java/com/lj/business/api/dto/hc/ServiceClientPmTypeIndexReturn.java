package com.lj.business.api.dto.hc;

import java.io.Serializable;
import java.util.List;

import com.lj.business.member.dto.FindClientPmTypeIndexReturn;
import com.lj.business.member.dto.FindPmTypeIndexReturn;

/**
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 * 
 * CreateDate: 2017年7月1日
 */
public class ServiceClientPmTypeIndexReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3189595484632661559L;
	
	/** 分类信息. */
	private FindPmTypeIndexReturn pmTye;
	
	/** 所属顾客明细. */
	private List<FindClientPmTypeIndexReturn> detail;

	/**
	 * Gets the 分类信息.
	 *
	 * @return the 分类信息
	 */
	public FindPmTypeIndexReturn getPmTye() {
		return pmTye;
	}

	/**
	 * Sets the 分类信息.
	 *
	 * @param pmTye the new 分类信息
	 */
	public void setPmTye(FindPmTypeIndexReturn pmTye) {
		this.pmTye = pmTye;
	}

	/**
	 * @return the detail
	 */
	public List<FindClientPmTypeIndexReturn> getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(List<FindClientPmTypeIndexReturn> detail) {
		this.detail = detail;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceClientPmTypeIndexReturn [pmTye=");
		builder.append(pmTye);
		builder.append(", detail=");
		builder.append(detail);
		builder.append("]");
		return builder.toString();
	}
}
