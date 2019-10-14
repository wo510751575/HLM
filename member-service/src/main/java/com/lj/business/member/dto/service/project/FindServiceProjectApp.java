package com.lj.business.member.dto.service.project;

import java.io.Serializable;

public class FindServiceProjectApp implements Serializable {

	private static final long serialVersionUID = 5624551403333595443L;

    /**
     * 商户编号
     */
    private String merchantNo;
    
    
    /**
     * 项目名称
     */
    private String projectName;

    
    
    public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindServiceProjectApp [merchantNo=");
		builder.append(merchantNo);
		builder.append(", projectName=");
		builder.append(projectName);
		builder.append("]");
		return builder.toString();
	}
}
