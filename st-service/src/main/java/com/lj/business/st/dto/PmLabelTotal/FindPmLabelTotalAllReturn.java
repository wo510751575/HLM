package com.lj.business.st.dto.PmLabelTotal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lj.business.st.dto.MaterialTotal.FindMaterialTotalReturn;

public class FindPmLabelTotalAllReturn implements Serializable { 

     /**
	     * Generate cron.
	     *
	     * @param
	     * @param
	     * @throws 
	     */
	private static final long serialVersionUID = -1150522238170178895L;
	/** 第一句. */
    private String textInfo1;
    
    /** 第二句. */
    private String textInfo2;
    
    /** 统计数据. */
    private List<FindPmLabelTotalReturnDto> list ;

	public String getTextInfo1() {
		return textInfo1;
	}

	public void setTextInfo1(String textInfo1) {
		this.textInfo1 = textInfo1;
	}

	public String getTextInfo2() {
		return textInfo2;
	}

	public void setTextInfo2(String textInfo2) {
		this.textInfo2 = textInfo2;
	}

	public List<FindPmLabelTotalReturnDto> getList() {
		return list;
	}

	public void setList(List<FindPmLabelTotalReturnDto> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPmLabelTotalAllReturn [textInfo1=")
				.append(textInfo1).append(", textInfo2=").append(textInfo2)
				.append(", list=").append(list).append("]");
		return builder.toString();
	}
}
