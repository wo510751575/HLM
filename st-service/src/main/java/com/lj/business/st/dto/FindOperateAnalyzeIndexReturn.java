package com.lj.business.st.dto;

import com.lj.base.core.util.EnumUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.business.st.emus.UnitType;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class FindWorkDayGmIndexReturn.
 */
public class FindOperateAnalyzeIndexReturn implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -9211057806546811341L;

    /**
     * 销售额（分）
     */
    private Long totalSale;

    /**
     * 销售额（万）
     */
    private String tenThousandSale;

    /**
     * 运营日报
     */
    private List<FindOperateDayReportReturn> operateDayList;

    /**
     * 运营分析
     */
    private List<FindOperateAnalysisReturn> operateAnalysisList;

    public Long getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(Long totalSale) {
        this.totalSale = totalSale;
    }

    public String getTenThousandSale() {
        return tenThousandSale;
    }

    public void setTenThousandSale(String tenThousandSale) {
        this.tenThousandSale = tenThousandSale;
    }

    public List<FindOperateDayReportReturn> getOperateDayList() {
        return operateDayList;
    }

    public void setOperateDayList(List<FindOperateDayReportReturn> operateDayList) {
        this.operateDayList = operateDayList;
    }

    public List<FindOperateAnalysisReturn> getOperateAnalysisList() {
        return operateAnalysisList;
    }

    public void setOperateAnalysisList(List<FindOperateAnalysisReturn> operateAnalysisList) {
        this.operateAnalysisList = operateAnalysisList;
    }
}
