package com.lj.business.st.dao;

import java.util.List;
import com.lj.business.st.domain.WxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopPage;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopPageReturn;

public interface IWxPmFollowReportShopDao {

    int deleteByPrimaryKey(String code);

    int insert(WxPmFollowReportShop record);

    int insertSelective(WxPmFollowReportShop record);

    WxPmFollowReportShop selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WxPmFollowReportShop record);

    int updateByPrimaryKey(WxPmFollowReportShop record);

    int findWxPmFollowReportShopPageCount(FindWxPmFollowReportShopPage findWxPmFollowReportShopPage);

    List<FindWxPmFollowReportShopPageReturn> findWxPmFollowReportShopPage(FindWxPmFollowReportShopPage findWxPmFollowReportShopPage);
    
}