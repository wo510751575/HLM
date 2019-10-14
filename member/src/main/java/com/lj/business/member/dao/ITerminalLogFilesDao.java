package com.lj.business.member.dao;

import java.util.Date;
import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.TerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesPage;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesPageReturn;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesReturn;

public interface ITerminalLogFilesDao {
    int deleteByPrimaryKey(String code);

    int insert(TerminalLogFiles record);

    int insertSelective(TerminalLogFiles record);

    TerminalLogFiles selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(TerminalLogFiles record);

    int updateByPrimaryKey(TerminalLogFiles record);

	FindTerminalLogFilesReturn selectByLogFileName(String logFileName);

	/**
	 * 
	 *
	 * 方法说明：查找终端日志文件信息
	 *
	 * @param findTerminalLogFilesPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月27日
	 *
	 */
	List<FindTerminalLogFilesPageReturn> findTerminalLogFilesPage(FindTerminalLogFilesPage findTerminalLogFilesPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找终端日志文件信息
	 *
	 * @param findTerminalLogFilesPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月27日
	 *
	 */
	int findTerminalLogFilesPageCount(FindTerminalLogFilesPage findTerminalLogFilesPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：获取上次当前终端日志的最后一个日志文件的时间
	 *
	 * @param imei
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月28日
	 *
	 */
	Date getLastFileDate(String imei);
}