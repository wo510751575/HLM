package com.lj.business.api.dto.hc;

import java.io.Serializable;

public class ConsumeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2801848380744423913L;

	private String projectJson;

	public String getProjectJson() {
		return projectJson;
	}

	public void setProjectJson(String projectJson) {
		this.projectJson = projectJson;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConsumeDto [projectJson=");
		builder.append(projectJson);
		builder.append("]");
		return builder.toString();
	}
	
}
