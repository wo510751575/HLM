package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.MerchantOperationDayReport;
import com.lj.business.st.dto.mec.FindMerchantOperationDayReportPage;
import com.lj.business.st.dto.mec.FindMerchantOperationDayReportPageReturn;

public interface IMerchantOperationDayReportDao {
    int deleteByPrimaryKey(String code);

    int insert(MerchantOperationDayReport record);

    int insertSelective(MerchantOperationDayReport record);

    MerchantOperationDayReport selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MerchantOperationDayReport record);

    int updateByPrimaryKey(MerchantOperationDayReport record);

    /**
     * 查找商户运营日报信息
     * @param findMerchantOperationDayReportPage
     * @return
     *
     * @author 彭俊霖
     * @CreateDate 2018年6月15日上午10:03:46
     */
	int findMerchantOperationDayReportPageCount(FindMerchantOperationDayReportPage findMerchantOperationDayReportPage);

	List<FindMerchantOperationDayReportPageReturn> findMerchantOperationDayReportPage(FindMerchantOperationDayReportPage findMerchantOperationDayReportPage);
}