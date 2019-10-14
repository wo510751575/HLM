package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.TerminalLoginLog;
import com.lj.business.member.dto.terminalimstatus.AddTerminalImStatus;
import com.lj.business.member.dto.terminalimstatus.AddTerminalImStatusReturn;
import com.lj.business.member.dto.terminalimstatus.FindTerminalBatteryLevelPage;
import com.lj.business.member.dto.terminalimstatus.FindTerminalBatteryLevelPageReturn;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatus;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusPage;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusPageReturn;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusReturn;
import com.lj.business.member.dto.terminalimstatus.TerminalImLoginRequest;
import com.lj.business.member.dto.terminalimstatus.TerminalImLoginResponse;
import com.lj.business.member.dto.terminalimstatus.TerminalImLogoutRequest;
import com.lj.business.member.dto.terminalimstatus.TerminalImLogoutResponse;
import com.lj.business.member.dto.terminalimstatus.UpdateTerminalImStatus;
import com.lj.business.member.dto.terminalimstatus.UpdateTerminalImStatusReturn;


/**
 * 
 * 
 * 类说明：接口类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月1日
 */
public interface ITerminalImStatusService {
	
	/**
	 * 
	 *
	 * 方法说明：添加终端IM状态信息
	 *
	 * @param addTerminalImStatus
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public AddTerminalImStatusReturn addTerminalImStatus(AddTerminalImStatus addTerminalImStatus) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找终端IM状态信息
	 *
	 * @param findTerminalImStatus
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public FindTerminalImStatusReturn findTerminalImStatus(FindTerminalImStatus findTerminalImStatus) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：修改终端IM状态信息
	 *
	 * @param updateTerminalImStatus
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public UpdateTerminalImStatusReturn updateTerminalImStatus(UpdateTerminalImStatus updateTerminalImStatus)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找终端IM状态信息
	 *
	 * @param findTerminalImStatusPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public Page<FindTerminalImStatusPageReturn> findTerminalImStatusPage(FindTerminalImStatusPage findTerminalImStatusPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：登录
	 *
	 * @param request
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月2日
	 *
	 */
	public TerminalImLoginResponse login(TerminalImLoginRequest request);
	
	/**
	 * 
	 *
	 * 方法说明：登出
	 *
	 * @param request
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月2日
	 *
	 */
	public TerminalImLogoutResponse logout(TerminalImLogoutRequest request);
	
	/**
	 * 
	 *
	 * 方法说明：修改终端IM状态信息(按终端类型)
	 *
	 * @param updateTerminalImStatus
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public UpdateTerminalImStatusReturn updateTerminalImStatusByType(UpdateTerminalImStatus updateTerminalImStatus) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查询终端手机电量信息
	 *
	 * @param findTerminalBatteryLevelPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月03日
	 *
	 */
	public Page<FindTerminalBatteryLevelPageReturn> findTerminalBatteryLevelPage(FindTerminalBatteryLevelPage findTerminalBatteryLevelPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param updateTerminalImStatus
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午4:14:02
	 */
	public void delete(UpdateTerminalImStatus updateTerminalImStatus);


	/**
	 *@Desc 
	 *@param merchantNo
	 *@param noWx
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月8日上午10:56:40
	 */
	public void deleteLoginLog(String merchantNo, String noWx);
	
}
