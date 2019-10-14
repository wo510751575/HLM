package com.lj.business.member.dao;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.TerminalImStatus;
import com.lj.business.member.dto.terminalimstatus.FindTerminalBatteryLevelPage;
import com.lj.business.member.dto.terminalimstatus.FindTerminalBatteryLevelPageReturn;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusPage;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusPageReturn;
import com.lj.business.member.dto.terminalimstatus.UpdateTerminalImStatus;

public interface ITerminalImStatusDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(TerminalImStatus record);

    TerminalImStatus selectByPrimaryKey(String code);
    
    TerminalImStatus selectBySelective(TerminalImStatus record);

    int updateByPrimaryKeySelective(TerminalImStatus record);

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
	public int findTerminalImStatusPageCount(FindTerminalImStatusPage findTerminalImStatusPage);
	
	/**
	 * 
	 *
	 * 方法说明：查找终端IM状态信息
	 *
	 * @param findTerminalImStatusPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public List<FindTerminalImStatusPageReturn> findTerminalImStatusPage(FindTerminalImStatusPage findTerminalImStatusPage);

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
	List<FindTerminalBatteryLevelPageReturn> findTerminalBatteryLevelPage(FindTerminalBatteryLevelPage findTerminalBatteryLevelPage);

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
	int findTerminalBatteryLevelPageCount(FindTerminalBatteryLevelPage findTerminalBatteryLevelPage);

	/**
	 *@Desc 
	 *@param updateTerminalImStatus
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午4:14:53
	 */
	void delete(UpdateTerminalImStatus updateTerminalImStatus);
	
}