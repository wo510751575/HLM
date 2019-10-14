package com.lj.business.supcon.dto.contacts;

import java.io.Serializable;
import java.util.List;


/**
 * 通过手机号加粉
 * @author 1234
 *
 */
public class AddFriendsByPhoneMessage implements Serializable {
	private static final long serialVersionUID = 8983422716711687292L;
	
	private String wxNoGm;
	
	private List<UserInfo> list =null;

	public List<UserInfo> getList() {
		return list;
	}

	public void setList(List<UserInfo> list) {
		this.list = list;
	}

	public String getWxNoGm() {
		return wxNoGm;
	}

	public void setWxNoGm(String wxNoGm) {
		this.wxNoGm = wxNoGm;
	}

	@Override
	public String toString() {
		return "AddFriendsByPhoneMessage [wxNoGm=" + wxNoGm + ", list=" + list + "]";
	}
	
}
