package com.lj.oms.entity.dto;

import java.io.Serializable;
import java.util.List;

public class WorkTaskChooseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1357345146128649568L;
	private List<String> workTaskLists;
	/**
	 * @return the workTaskLists
	 */
	public List<String> getWorkTaskLists() {
		return workTaskLists;
	}
	/**
	 * @param workTaskLists the workTaskLists to set
	 */
	public void setWorkTaskLists(List<String> workTaskLists) {
		this.workTaskLists = workTaskLists;
	}
	
}
