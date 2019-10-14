package com.lj.business.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.member.domain.AddFriendsTaskDetail;
import com.lj.business.member.dto.AddFriendsTaskDetailDto;
import com.lj.business.member.dto.FindAddFriendsTaskDetailPage;

public interface IAddFriendsTaskDetailDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(AddFriendsTaskDetail record);

    AddFriendsTaskDetail selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(AddFriendsTaskDetail record);
    
    int updateStatus(AddFriendsTaskDetailDto addFriendsTaskDetailDto);

	int findAddFriendsTaskDetailPageCount(FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage);

	List<AddFriendsTaskDetailDto> findAddFriendsTaskDetailPage(FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage);

	List<AddFriendsTaskDetailDto> findAddFriendsTaskDetails(FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage);
	
	AddFriendsTaskDetail selectByCond(AddFriendsTaskDetailDto addFriendsTaskDetailDto);
	/**
	 * 批量插入
	 * @param record
	 * @return
	 */
	int insertForeach(List<AddFriendsTaskDetailDto> record);
	
	/**
	 * 定时任务每天推送的结果集
	 * 
	 * @param num 每个终端推送的数量，默认20
	 * @return
	 */
	List<AddFriendsTaskDetailDto> findJobResult(@Param("num") int num);
}