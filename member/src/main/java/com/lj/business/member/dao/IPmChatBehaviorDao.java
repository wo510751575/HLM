package com.lj.business.member.dao;

import com.lj.business.member.dto.UpdatePmChatBehavior;

public interface IPmChatBehaviorDao {

    int insertOrUpdate(UpdatePmChatBehavior updatePmChatBehavior);

	/**
	 *@Desc 
	 *@param updatePmChatBehavior
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午4:02:07
	 */
	void delete(UpdatePmChatBehavior updatePmChatBehavior);

}