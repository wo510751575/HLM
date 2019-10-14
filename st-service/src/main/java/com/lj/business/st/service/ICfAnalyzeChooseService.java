package com.lj.business.st.service;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.CfAnalyzeChooseDto;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyze;

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

public interface ICfAnalyzeChooseService {

     /**
      * 
      *
      * 方法说明：新增插入方法
      *
      * @param record
      *
      * @author 罗书明 CreateDate: 2017年8月1日
      *
      */
    public void insertSelective(CfAnalyzeChooseDto record)throws TsfaServiceException ;
    /**
     * 
     *
     * 方法说明：按主键查询
     *
     * @param code
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月1日
     *
     */
    public CfAnalyzeChooseDto selectByPrimaryKey(String code)throws TsfaServiceException ;
    
    /**
     * 
     *
     * 方法说明：按商户查询返回list
     *
     * @param findCfAnalyze
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月1日
     *
     */
    public List<CfAnalyzeChooseDto> findCfAnalyzeChoose(FindCfAnalyze findCfAnalyze)throws TsfaServiceException ;

    /**
     * 
     *
     * 方法说明：删除
     *
     * @param merchantNo
     *
     * @author 罗书明 CreateDate: 2017年8月1日
     *
     */
    public int deleteByPrimaryKey(String merchantNo);

}
