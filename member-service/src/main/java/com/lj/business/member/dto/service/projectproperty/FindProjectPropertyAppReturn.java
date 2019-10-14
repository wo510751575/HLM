package com.lj.business.member.dto.service.projectproperty;

import java.io.Serializable;


public class FindProjectPropertyAppReturn implements Serializable{
	private static final long serialVersionUID = -1029351636661040017L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 属性名称 .
     */
    private String propertyName;

    /**
     * 取值范围，多个用英文逗号隔开 .
     */
    private String valueRange;

    /**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 属性名称 .
     *
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 属性名称 .
     *
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
    }

    /**
     * 取值范围，多个用英文逗号隔开 .
     *
     */
    public String getValueRange() {
        return valueRange;
    }

    /**
     * 取值范围，多个用英文逗号隔开 .
     *
     */
    public void setValueRange(String valueRange) {
        this.valueRange = valueRange == null ? null : valueRange.trim();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindProjectPropertyAppReturn [code=");
		builder.append(code);
		builder.append(", propertyName=");
		builder.append(propertyName);
		builder.append(", valueRange=");
		builder.append(valueRange);
		builder.append("]");
		return builder.toString();
	}

}