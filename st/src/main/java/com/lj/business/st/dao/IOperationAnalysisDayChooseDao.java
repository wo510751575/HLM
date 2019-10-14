package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.OperationAnalysisDayChoose;
import com.lj.business.st.dto.*;

public interface IOperationAnalysisDayChooseDao {
    int deleteByPrimaryKey(String code);

    int insert(OperationAnalysisDayChoose record);

    int insertSelective(OperationAnalysisDayChoose record);

    OperationAnalysisDayChoose selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(OperationAnalysisDayChoose record);

    int updateByPrimaryKey(OperationAnalysisDayChoose record);
    
    /**
     * 
     *
     * 方法说明：查询运营分析报表
     *
     * @param findOperationAnalysisDayChoose
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月2日
     *
     */
    List<OperationAnalysisDayChooseDto> findOperationAnalysisDayChoose(FindOperationAnalysisDayChoose  findOperationAnalysisDayChoose);
    

    /**
     * 
     *
     * 方法说明：根据商户编号删除
     *
     * @param merchantNo
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月2日
     *
     */
    int deleteByMerchantNo(String merchantNo);

    List<FindOperateAnalysisReturn> findOperationAnalysisDayChooseList(FindOperateDayReport findOperateDayReport);
}