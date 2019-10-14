package com.lj.business.st.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.business.st.dto.*;

/**
 * 
 * 
 * 类说明：
 *  运营分析报表选择表Service接口
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月1日
 */
public interface IOperationAnalysisDayChooseService {
  
	  /**
	   * 
	   *
	   * 方法说明：新增插入
	   *
	   * @param operationAnalysisDayChoose
	   * @return
	   *
	   * @author 罗书明 CreateDate: 2017年7月1日
	   *
	   */
	 public OperationAnalysisDayChooseDto insertSelective(OperationAnalysisDayChooseDto operationAnalysisDayChoose);
	  /**
	   *  
	   *
	   * 方法说明：主键查询
	   *   
	   * @param code
	   * @return
	   *
	   * @author 罗书明 CreateDate: 2017年7月1日
	   *
	   */
	 public  OperationAnalysisDayChooseDto selectByPrimaryKey(String code);

	 
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
	   public  List<OperationAnalysisDayChooseDto> findOperationAnalysisDayChoose(FindOperationAnalysisDayChoose  findOperationAnalysisDayChoose);
	
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
	   public int deleteByMerchantNo(String merchantNo);

	List<FindOperateAnalysisReturn> findOperationAnalysisChooseList(FindOperateDayReport findOperateDayReport);
}
