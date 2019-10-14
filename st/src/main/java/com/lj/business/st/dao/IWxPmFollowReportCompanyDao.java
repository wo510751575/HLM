package com.lj.business.st.dao;

import java.util.List;
import com.lj.business.st.domain.WxPmFollowReportCompany;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompanyPage;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompanyPageReturn;

public interface IWxPmFollowReportCompanyDao {

    int deleteByPrimaryKey(String code);

    int insert(WxPmFollowReportCompany record);

    int insertSelective(WxPmFollowReportCompany record);

    WxPmFollowReportCompany selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WxPmFollowReportCompany record);

    int updateByPrimaryKey(WxPmFollowReportCompany record);

    int findWxPmFollowReportCompanyPageCount(FindWxPmFollowReportCompanyPage findWxPmFollowReportCompanyPage);

    List<FindWxPmFollowReportCompanyPageReturn> findWxPmFollowReportCompanyPage(FindWxPmFollowReportCompanyPage findWxPmFollowReportCompanyPage);
}