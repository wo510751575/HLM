package com.lj.business.st.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.st.domain.CountAddFriendsEntity;

public interface ICountAddFriendsDao {

	/**
	 * 批量插入数据
	 * @param record
	 * @return
	 */
    int insertList(List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> list);

	/**
	 *  查询昨天的新增数
	 * @param merchantNo 商户编号
	 * @return
	 */
	List<CountAddFriendsEntity> getYesterday(@Param("merchantNo")String merchantNo,@Param("type") String type,@Param("noWx")String noWx);
	
	
	List<CountAddFriendsEntity> getInterval7(@Param("merchantNo")String merchantNo,@Param("type") String type,@Param("noWx")String noWx);

	
	List<CountAddFriendsEntity> getInterval30(@Param("merchantNo")String merchantNo,@Param("type") String type,@Param("noWx")String noWx);
	/**
	 *  查询昨天的新增数
	 * @param merchantNo 商户编号
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	List<CountAddFriendsEntity> getStartAndEndTime(@Param("startTime")String startTime,
			@Param("endTime")String endTime, @Param("merchantNo")String merchantNo,@Param("type") String type,@Param("noWx")String noWx);
	/**
	 *  查询具体某一天的新增数
	 * @param merchantNo 商户编号
	 * @param startTime 开始时间
	 * @return
	 */
	List<CountAddFriendsEntity> getOneDay(@Param("startTime")String startTime, @Param("merchantNo")String merchantNo,@Param("type") String type,@Param("noWx")String noWx);
	
	/**
	 * 查询昨天数据有没执行
	 * @param startTime
	 * @return
	 */
	List<CountAddFriendsEntity> getLastDay();
	
	/**
	 * 查询所有数据总数据
	 * @return
	 */
	Integer getAllDataCountNum(@Param("type")String type);

}
