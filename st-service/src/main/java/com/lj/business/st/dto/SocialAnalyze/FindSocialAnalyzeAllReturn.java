package com.lj.business.st.dto.SocialAnalyze;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class FindSocialAnalyzeAllReturn.
 */
public class FindSocialAnalyzeAllReturn implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3130054594595761834L;
	
	/** 第一句. */
    private String textInfo1;
    
    /** 第二句. */
    private String textInfo2;
    
    /** 统计数据. */
    private List<FindSocialAnalyzeReturn> list ;

	/**
	 * Gets the text info 1.
	 *
	 * @return the text info 1
	 */
	public String getTextInfo1() {
		return textInfo1;
	}

	/**
	 * Sets the text info 1.
	 *
	 * @param textInfo1 the new text info 1
	 */
	public void setTextInfo1(String textInfo1) {
		this.textInfo1 = textInfo1;
	}

	/**
	 * Gets the text info 2.
	 *
	 * @return the text info 2
	 */
	public String getTextInfo2() {
		return textInfo2;
	}

	/**
	 * Sets the text info 2.
	 *
	 * @param textInfo2 the new text info 2
	 */
	public void setTextInfo2(String textInfo2) {
		this.textInfo2 = textInfo2;
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<FindSocialAnalyzeReturn> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 */
	public void setList(List<FindSocialAnalyzeReturn> list) {
		this.list = list;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindSocialAnalyzeAllReturn [textInfo1=")
				.append(textInfo1).append(", textInfo2=").append(textInfo2)
				.append(", list=").append(list).append("]");
		return builder.toString();
	}
    
}
