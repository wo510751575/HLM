package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.StDailyPay;
import com.ye.business.hx.dto.FindStDailyPayPage;
import com.ye.business.hx.dto.StDailyPayDto;

public interface IStDailyPayDao {
	int deleteByPrimaryKey(String code);

	int insert(StDailyPay record);

	int insertSelective(StDailyPay record);

	StDailyPay selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(StDailyPay record);

	int updateByPrimaryKey(StDailyPay record);

	List<StDailyPayDto> findStDailyPays(FindStDailyPayPage findStDailyPayPage);

	List<StDailyPayDto> findStDailyPayPage(FindStDailyPayPage findStDailyPayPage);

	int findStDailyPayPageCount(FindStDailyPayPage findStDailyPayPage);
	
	/**
	 * @param stDate 统计日期
	 * @return
	 */
	int batchAddStDailyPay(String stDate);
	
	List<StDailyPayDto> findStDailyPayPageGroupByStDay(FindStDailyPayPage findStDailyPayPage);

	int findStDailyPayPageCountGroupByStDay(FindStDailyPayPage findStDailyPayPage);

	/**
	 * 按支付方式合计金额
	 * 
	 * @param findStDailyPayPage
	 * @return
	 */
	List<StDailyPayDto> stDailyPaySumGroupByPayType(FindStDailyPayPage findStDailyPayPage);
}