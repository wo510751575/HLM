package com.lj.business.st.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.st.domain.CompanyOperationDayReport;
import com.lj.business.st.dto.mec.FindCompanyOperationDayReportPage;
import com.lj.business.st.dto.mec.FindCompanyOperationDayReportPageReturn;

public interface ICompanyOperationDayReportDao {
    int deleteByPrimaryKey(String code);

    int insert(CompanyOperationDayReport record);

    int insertSelective(CompanyOperationDayReport record);

    CompanyOperationDayReport selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CompanyOperationDayReport record);

    int updateByPrimaryKey(CompanyOperationDayReport record);
    
    /**
     * 
     *
     * 方法说明：统计门店运营日报信息更新所属经销商经营日报
     *
     * @param merchantNo
     * @param reportDate
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年6月14日
     *
     */
    int updateByShopDayReport(@Param("merchantNo") String merchantNo, @Param("reportDate") Date reportDate);
    
    /**
     * 
     *
     * 方法说明：统计商户下所有经销商运营日报数据
     *
     * @param merchantNo
     * @param reportDate
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年6月14日
     *
     */
    CompanyOperationDayReport stsGroupByMerchant(@Param("merchantNo") String merchantNo, @Param("reportDate") Date reportDate);

    /**
     * 方法说明：查找经销商运营日报信息
     * @param findCompanyOperationDayReportPage
     * @return
     *
     * @author 彭俊霖
     * @CreateDate 2018年6月15日下午4:53:11
     */
	int findCompanyOperationDayReportPageCount(FindCompanyOperationDayReportPage findCompanyOperationDayReportPage);

	List<FindCompanyOperationDayReportPageReturn> findCompanyOperationDayReportPage(FindCompanyOperationDayReportPage findCompanyOperationDayReportPage);
}