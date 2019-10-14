package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.List;

import com.lj.business.st.dto.CfAnalyze.FindCfAnalyzeReturn;

// TODO: Auto-generated Javadoc
/**
 * The Class findWorkDayGmCfAnalyzeIndexReturn.
 */
public class FindWorkDayGmCfAnalyzeIndexReturn implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7324676908618422925L;
	
	/** 日工作简报. */
	List<FindWorkDayGmIndexReturn> listWorkDay;
	
	/** 跟进分析. */
	List<FindCfAnalyzeReturn> listCfAnalyze;

	/**
	 * Gets the list work day.
	 *
	 * @return the list work day
	 */
	public List<FindWorkDayGmIndexReturn> getListWorkDay() {
		return listWorkDay;
	}

	/**
	 * Sets the list work day.
	 *
	 * @param listWorkDay the new list work day
	 */
	public void setListWorkDay(List<FindWorkDayGmIndexReturn> listWorkDay) {
		this.listWorkDay = listWorkDay;
	}

	/**
	 * Gets the list cf analyze.
	 *
	 * @return the list cf analyze
	 */
	public List<FindCfAnalyzeReturn> getListCfAnalyze() {
		return listCfAnalyze;
	}

	/**
	 * Sets the list cf analyze.
	 *
	 * @param listCfAnalyze the new list cf analyze
	 */
	public void setListCfAnalyze(List<FindCfAnalyzeReturn> listCfAnalyze) {
		this.listCfAnalyze = listCfAnalyze;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("findWorkDayGmCfAnalyzeIndexReturn [listWorkDay=")
				.append(listWorkDay).append(", listCfAnalyze=")
				.append(listCfAnalyze).append("]");
		return builder.toString();
	}
	
}
