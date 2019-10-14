package com.lj.oms.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

/**
 * 批量取消认领参数bean
 * @author wo510
 *
 */
/**
 * @author wo510
 *
 */
public class CancleBingFriendsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Map<String,String>> list =Lists.newArrayList();

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CancleBingFriendsDto [list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}
	
}
