package com.lj.business.st.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.st.domain.TodayMemberSummary;
import com.lj.business.st.dto.tms.FindTodayMemberSummaryPage;
import com.lj.business.st.dto.tms.FindTodayMemberSummaryPageReturn;

public interface ITodayMemberSummaryDao {
    int deleteByPrimaryKey(String code);

    int insert(TodayMemberSummary record);

    int insertSelective(TodayMemberSummary record);

    TodayMemberSummary selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(TodayMemberSummary record);

    int updateByPrimaryKey(TodayMemberSummary record);
    
    TodayMemberSummary selectByMemberAndSummaryDate(@Param("memberNoGm") String memberNoGm, @Param("summaryDate") Date summaryDate);
    
    /**
	 * 
	 *
	 * 方法说明：统计今日客户汇总记录数
	 *
	 * @param findTodayMemberSummaryPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public int findTodayMemberSummaryPageCount(FindTodayMemberSummaryPage findTodayMemberSummaryPage);
	
	/**
	 * 
	 *
	 * 方法说明：查找今日客户汇总信息
	 *
	 * @param findTodayMemberSummaryPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public List<FindTodayMemberSummaryPageReturn> findTodayMemberSummaryPage(FindTodayMemberSummaryPage findTodayMemberSummaryPage);
}