package com.lj.business.st.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.st.domain.ShopOperationDayReport;
import com.lj.business.st.dto.mec.FindShopOperationDayReportPage;
import com.lj.business.st.dto.mec.FindShopOperationDayReportPageReturn;

public interface IShopOperationDayReportDao {
    int deleteByPrimaryKey(String code);

    int insert(ShopOperationDayReport record);

    int insertSelective(ShopOperationDayReport record);

    ShopOperationDayReport selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ShopOperationDayReport record);

    int updateByPrimaryKey(ShopOperationDayReport record);
    
    /**
     * 
     *
     * 方法说明：按经销商统计门店运营日报数据
     *
     * @param merchantNo
     * @param reportDate
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年6月14日
     *
     */
    List<ShopOperationDayReport> stsGroupByCompany(@Param("merchantNo") String merchantNo, @Param("reportDate") Date reportDate);

    /**
     * 方法说明：查找门店运营日报信息
     * @param findShopOperationDayReportPage
     * @return
     *
     * @author 彭俊霖
     * @CreateDate 2018年6月16日上午10:53:42
     */
	int findShopOperationDayReportPageCount(FindShopOperationDayReportPage findShopOperationDayReportPage);

	List<FindShopOperationDayReportPageReturn> findShopOperationDayReportPage(FindShopOperationDayReportPage findShopOperationDayReportPage);
}