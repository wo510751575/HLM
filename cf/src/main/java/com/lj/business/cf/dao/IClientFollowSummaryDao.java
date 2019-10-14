package com.lj.business.cf.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.cf.domain.ClientFollowSummary;
import com.lj.business.cf.dto.clientFollowSummary.FindAbandonRecordCountReturn;
import com.lj.business.cf.dto.clientFollowSummary.FindBuyRecordCountReturn;
import com.lj.business.cf.dto.clientFollowSummary.FindBuyRecordPage;
import com.lj.business.cf.dto.clientFollowSummary.FindBuyRecordPageReturn;
import com.lj.business.cf.dto.clientFollowSummary.FindClientFollowSummary;
import com.lj.business.cf.dto.clientFollowSummary.FindClientFollowSummaryPage;
import com.lj.business.cf.dto.clientFollowSummary.FindClientFollowSummaryPageReturn;
import com.lj.business.cf.dto.clientFollowSummary.FindImClientFollowReturn;
import com.lj.business.cf.dto.clientFollowSummary.FindSuccessNum;

public interface IClientFollowSummaryDao {
    int deleteByPrimaryKey(String code);

    int insert(ClientFollowSummary record);

    int insertSelective(ClientFollowSummary record);

    ClientFollowSummary selectByPrimaryKey(String code);
    
    ClientFollowSummary selectByCfNo(String cfNo);

    int updateByPrimaryKeySelective(ClientFollowSummary record);
    
    int updateByCfNoSelective(ClientFollowSummary record);

    int updateByPrimaryKey(ClientFollowSummary record);
    
    List<FindClientFollowSummaryPageReturn> findClientFollowSummaryPage(FindClientFollowSummaryPage findClientFollowSummaryPage);

    int findClientFollowSummaryPageCount(FindClientFollowSummaryPage findClientFollowSummaryPage);
    
    ClientFollowSummary findClientFollowSummaryLast(FindClientFollowSummary findClientFollowSummary);
    
    List<FindBuyRecordPageReturn> findBuyRecordPage(FindBuyRecordPage findBuyRecordPage);
    
    List<FindBuyRecordCountReturn> findBuyRecordCount(FindBuyRecordPage findBuyRecordPage);
    
    List<FindAbandonRecordCountReturn> findAbandonRecordCount(FindBuyRecordPage findBuyRecordPage);

    int findBuyRecordPageCount(FindBuyRecordPage findBuyRecordPage);
     
    int findBuySuccessNum(FindSuccessNum findSuccessNum);
    
    long sumAmountByShop(List<String> memberNoGmList);
    
    long sumAmountByMerchantNo(Map<String,Object> parmMap);

	long findNumSaleByGm(Map<String, Object> map);

	int findCountOrderByGm(Map<String, Object> map);
	
	/**
	 * 
	 *
	 * 方法说明：根据导购编号查询导购成单客户数量
	 * @param memberNoGm 导购编号
	 * 	      beginTime 开始时间
	 * 		  endTime 结束时间
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年8月19日
	 *
	 */
	public Integer findCountPmOrderByGm(Map<String, Object> map);

	Long findCountPmCfByGm(String memberNo);

	Double findSaleByShop(Map<String, Object> map);

	Integer findMaxSumFollow(Map<String, Object> map);

	/**
	 * 
	 *
	 * 方法说明：商户运营日报表门店成单客户数
	 *
	 * @param map
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月31日
	 *
	 */
	Integer findCountPmOrder(Map<String, Object> map);

	/**
	 *
	 * 方法说明：查询客户反馈信息
	 *
	 * @param findClientFollowSummary
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年04月16日
	 *
	 */
	FindImClientFollowReturn findImClientFollow(FindClientFollowSummary summary);
}