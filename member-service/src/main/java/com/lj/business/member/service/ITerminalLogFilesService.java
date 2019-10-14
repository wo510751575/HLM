package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.terminallogfiles.AddTerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.AddTerminalLogFilesReturn;
import com.lj.business.member.dto.terminallogfiles.DelTerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.DelTerminalLogFilesReturn;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesPage;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesPageReturn;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesReturn;
import com.lj.business.member.dto.terminallogfiles.UpdateTerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.UpdateTerminalLogFilesReturn;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭俊霖
 * 
 * 
 * CreateDate: 2017-11-01
 */
public interface ITerminalLogFilesService {
	
	/**
	 * 
	 *
	 * 方法说明：添加终端日志文件信息
	 *
	 * @param addTerminalLogFiles
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public AddTerminalLogFilesReturn addTerminalLogFiles(AddTerminalLogFiles addTerminalLogFiles) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找终端日志文件信息
	 *
	 * @param findTerminalLogFiles
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public FindTerminalLogFilesReturn findTerminalLogFiles(FindTerminalLogFiles findTerminalLogFiles) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除终端日志文件信息
	 *
	 * @param delTerminalLogFiles
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public DelTerminalLogFilesReturn delTerminalLogFiles(DelTerminalLogFiles delTerminalLogFiles) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改终端日志文件信息
	 *
	 * @param updateTerminalLogFiles
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public UpdateTerminalLogFilesReturn updateTerminalLogFiles(UpdateTerminalLogFiles updateTerminalLogFiles)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找终端日志文件信息
	 *
	 * @param findTerminalLogFilesPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public Page<FindTerminalLogFilesPageReturn> findTerminalLogFilesPage(FindTerminalLogFilesPage findTerminalLogFilesPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：通过日志文件名 查找终端日志文件信息
	 *
	 * @param logFileName
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月27日
	 *
	 */
	public FindTerminalLogFilesReturn findLogByFileName(String logFileName) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：获取上次当前终端日志的最后一个日志文件的时间
	 *
	 * @param noWx
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月28日
	 *
	 */
	public String getLastFileDate(String noWx);
	

}
