package com.lj.business.st.dto.readDetail;

import java.io.Serializable;

public class AddReadDetailReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 867938803433732302L;

	
	 /**
     * 访问量 .
     */
    private Long visitNum;
    
    /**
     * 访问人数（一人只能统计一次） .
     */
    private Long visitNumPerson;


	public Long getVisitNum() {
		return visitNum;
	}


	public void setVisitNum(Long visitNum) {
		this.visitNum = visitNum;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddReadDetailReturn [visitNum=").append(visitNum)
				.append(", visitNumPerson=").append(visitNumPerson).append("]");
		return builder.toString();
	}


	public Long getVisitNumPerson() {
		return visitNumPerson;
	}


	public void setVisitNumPerson(Long visitNumPerson) {
		this.visitNumPerson = visitNumPerson;
	}

	
	
}
