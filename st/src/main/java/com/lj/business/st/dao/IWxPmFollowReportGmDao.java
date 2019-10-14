package com.lj.business.st.dao;

import java.util.List;
import com.lj.business.st.domain.WxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmPage;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmPageReturn;

public interface IWxPmFollowReportGmDao {

    int deleteByPrimaryKey(String code);

    int insert(WxPmFollowReportGm record);

    int insertSelective(WxPmFollowReportGm record);

    WxPmFollowReportGm selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WxPmFollowReportGm record);

    int updateByPrimaryKey(WxPmFollowReportGm record);

    int findWxPmFollowReportGmPageCount(FindWxPmFollowReportGmPage findWxPmFollowReportGmPage);

    List<FindWxPmFollowReportGmPageReturn> findWxPmFollowReportGmPage(FindWxPmFollowReportGmPage findWxPmFollowReportGmPage);
}