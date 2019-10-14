package com.lj.business.weixin.dao;

import java.util.List;

import com.lj.business.weixin.domain.ImGroupChatInfo;
import com.lj.business.weixin.dto.FindImGroupChatInfoPage;
import com.lj.business.weixin.dto.ImGroupChatInfoDto;

public interface IImGroupChatInfoDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(ImGroupChatInfo record);

    ImGroupChatInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ImGroupChatInfo record);

	List<ImGroupChatInfoDto> findImGroupChatInfoPage(FindImGroupChatInfoPage findImGroupChatInfoPage);

	int findImGroupChatInfoPageCount(FindImGroupChatInfoPage findImGroupChatInfoPage);

	List<ImGroupChatInfoDto> findImGroupChatInfos(FindImGroupChatInfoPage findImGroupChatInfoPage);

	/**
	 *@Desc 
	 *@param imGroupChatInfoDto
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午6:09:32
	 */
	void delete(ImGroupChatInfoDto imGroupChatInfoDto);

}