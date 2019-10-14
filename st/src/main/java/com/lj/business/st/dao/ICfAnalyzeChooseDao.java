package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.CfAnalyzeChoose;
import com.lj.business.st.dto.CfAnalyzeChooseDto;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyze;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyzeReturn;
/**
 * 
 * 
 * 类说明：跟进分析选择表
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年8月1日
 */
public interface ICfAnalyzeChooseDao {
    int deleteByPrimaryKey(String code);

    int insert(CfAnalyzeChoose record);

    int insertSelective(CfAnalyzeChoose record);

    CfAnalyzeChoose selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CfAnalyzeChoose record);

    int updateByPrimaryKey(CfAnalyzeChoose record);
    
    
    List<FindCfAnalyzeReturn> findCfAnalyze(FindCfAnalyze findCfAnalyze);
    
    
    List<CfAnalyzeChooseDto> findCfAnalyzeChoose(FindCfAnalyze findCfAnalyze);
}