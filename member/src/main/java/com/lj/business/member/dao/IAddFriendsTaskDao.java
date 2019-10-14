package com.lj.business.member.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.member.domain.AddFriendsTask;
import com.lj.business.member.dto.AddFriendsTaskCountDto;
import com.lj.business.member.dto.AddFriendsTaskDto;
import com.lj.business.member.dto.FindAddFriendsTaskPage;

public interface IAddFriendsTaskDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(AddFriendsTask record);

    AddFriendsTask selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(AddFriendsTask record);

	int findAddFriendsTaskPageCount(FindAddFriendsTaskPage findAddFriendsTaskPage);

	List<AddFriendsTaskDto> findAddFriendsTaskPage(FindAddFriendsTaskPage findAddFriendsTaskPage);

	List<AddFriendsTaskDto> findAddFriendsTasks(FindAddFriendsTaskPage findAddFriendsTaskPage);

	AddFriendsTaskCountDto selectAddFriendsTaskDetailCount(AddFriendsTaskDto addFriendsTaskDto);
	/**
	 * 按终端微信分组
	 * @return
	 */
	String selectDistinctGroupByNoWxGms(AddFriendsTaskDto addFriendsTaskDto);
}