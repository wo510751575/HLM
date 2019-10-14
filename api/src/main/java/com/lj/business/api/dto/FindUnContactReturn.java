package com.lj.business.api.dto;

import java.io.Serializable;

/**
 * The Class FindUnContactReturn.
 */
public class FindUnContactReturn implements Serializable {


    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7478474474791509783L;

	/**
     * 客户提醒主BEAN CODE .
     */
    private String code;

    /**
     * 客户提醒类型CODE
     * 1周到1月未联系:yizhouYiyue
     * 1月到3月未联系：yiyueSanyue
     * 3月到6月未联系：sanyueLiuyue
     * 6月以上未联系：liuyueEnd .
     */
    private String codeUnContact;
    
    /**
     * 客户提醒类型名称 .
     */
    private String nameUnContact;

    /**
     * 未联系数量 .
     */
    private Integer num;

	/**
	 * Gets the 客户提醒主BEAN CODE .
	 *
	 * @return the 客户提醒主BEAN CODE 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the 客户提醒主BEAN CODE .
	 *
	 * @param code the new 客户提醒主BEAN CODE 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the 客户提醒类型CODE 1周到1月未联系: 1月到3月未联系 3月到6月未联系 6月以上未联系 .
	 *
	 * @return the 客户提醒类型CODE 1周到1月未联系: 1月到3月未联系 3月到6月未联系 6月以上未联系 
	 */
	public String getCodeUnContact() {
		return codeUnContact;
	}

	/**
	 * Sets the 客户提醒类型CODE 1周到1月未联系: 1月到3月未联系 3月到6月未联系 6月以上未联系 .
	 *
	 * @param codeUnContact the new 客户提醒类型CODE 1周到1月未联系: 1月到3月未联系 3月到6月未联系 6月以上未联系 
	 */
	public void setCodeUnContact(String codeUnContact) {
		this.codeUnContact = codeUnContact;
	}

	/**
	 * Gets the 客户提醒类型名称 .
	 *
	 * @return the 客户提醒类型名称 
	 */
	public String getNameUnContact() {
		return nameUnContact;
	}

	/**
	 * Sets the 客户提醒类型名称 .
	 *
	 * @param nameUnContact the new 客户提醒类型名称 
	 */
	public void setNameUnContact(String nameUnContact) {
		this.nameUnContact = nameUnContact;
	}

	/**
	 * Gets the 未联系数量 .
	 *
	 * @return the 未联系数量 
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * Sets the 未联系数量 .
	 *
	 * @param num the new 未联系数量 
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindUnContactReturn [code=").append(code)
				.append(", codeUnContact=").append(codeUnContact)
				.append(", nameUnContact=").append(nameUnContact)
				.append(", num=").append(num).append("]");
		return builder.toString();
	}

}
