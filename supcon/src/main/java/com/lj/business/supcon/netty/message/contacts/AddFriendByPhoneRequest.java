package com.lj.business.supcon.netty.message.contacts;

import java.util.List;

import com.lj.business.supcon.dto.contacts.UserInfo;
import com.lj.business.supcon.netty.message.BaseRequest;

public class AddFriendByPhoneRequest extends BaseRequest {

	private static final long serialVersionUID = -7392045532342137682L;
	
	/**
	 * 加粉集合
	 * mobile = 手机号
	 * validation = 验证话术
	 * nickRemark = 客户昵称备注
	 */
	List<UserInfo> list =null;

	public List<UserInfo> getList() {
		return list;
	}

	public void setList(List<UserInfo> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "AddFriendByPhoneRequest [list=" + list + "]";
	}
	
}
