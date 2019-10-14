package com.lj.business.member.dao;

import org.apache.ibatis.annotations.Param;

import com.lj.business.member.domain.TerminalLoginLog;

public interface ITerminalLoginLogDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(TerminalLoginLog record);

    TerminalLoginLog selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(TerminalLoginLog record);


	/**
	 *@Desc 
	 *@param merchantNo
	 *@param noWx
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月8日上午10:57:48
	 */
	void delete(@Param("merchantNo") String merchantNo,@Param("noWx") String noWx);

}