package com.lj.business.st.dto.PmTalkTotal;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class FindPmTalkTotalAllReturn.
 */
public class FindPmTalkTotalAllReturn implements Serializable {

	/** Generate cron. */
	private static final long serialVersionUID = 2945654762888311838L;

	/** 第一句. */
	private String textInfo1;

	/** 第二句. */
	private String textInfo2;

	/** The list. */
	private List<FindPmTalkTotalReturn> list;
	
	private List<FindPmTalkTotalReturn> orglist;

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
	public List<FindPmTalkTotalReturn> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 */
	public void setList(List<FindPmTalkTotalReturn> list) {
		this.list = list;
	}
	
	public List<FindPmTalkTotalReturn> getOrglist() {
		return orglist;
	}

	public void setOrglist(List<FindPmTalkTotalReturn> orglist) {
		this.orglist = orglist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPmTalkTotalAllReturn [textInfo1=")
				.append(textInfo1).append(", textInfo2=").append(textInfo2)
				.append(", list=").append(list).append(", orglist=")
				.append(orglist).append("]");
		return builder.toString();
	}

}
