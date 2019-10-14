package com.lj.business.member.dto.service.project;

import java.io.Serializable;
import java.util.List;

import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyAppReturn;


public class FindServiceProjectAppReturn implements Serializable {
	private static final long serialVersionUID = 1697077876484160239L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 项目名称 .
     */
    private String projectName;
    
    /**
     * 属性详情
     */
    private List<FindProjectPropertyAppReturn> propertyList;


    /**
     * 项目名称 .
     *
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 项目名称 .
     *
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

	/**
	 * @return the propertyList
	 */
	public List<FindProjectPropertyAppReturn> getPropertyList() {
		return propertyList;
	}

	/**
	 * @param propertyList the propertyList to set
	 */
	public void setPropertyList(List<FindProjectPropertyAppReturn> propertyList) {
		this.propertyList = propertyList;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindServiceProjectAppReturn [code=");
		builder.append(code);
		builder.append(", projectName=");
		builder.append(projectName);
		builder.append(", propertyList=");
		builder.append(propertyList);
		builder.append("]");
		return builder.toString();
	}

}