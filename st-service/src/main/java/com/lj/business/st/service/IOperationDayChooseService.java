package com.lj.business.st.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.business.st.dto.*;

/**
 * 
 * 
 * 类说明：运营日报表选择表（基础）
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月1日
 */
public interface IOperationDayChooseService {

	   
	  /**
	   * 
	   *
	   * 方法说明：插入
	   *
	   * @param operationDayChoose
	   * @return
	   *
	   * @author 罗书明 CreateDate: 2017年7月1日
	   *
	   */
	  public OperationDayChooseDto insertSelectAll(OperationDayChooseDto operationDayChooseDto);
	   
      /**
       * 
       *
       * 方法说明：查询运营日报
       *
       * @param findOperationDayChoose
       * @return
       *
       * @author 罗书明 CreateDate: 2017年8月2日
       *
       */
	  public List<OperationDayChooseDto> findOperationDayChoose(FindOperationDayChoose findOperationDayChoose);
	  
	  /**
	   * 
	   *
	   * 方法说明：根据商户编号或code删除
	   *
	   * @param merchantNo and  code
	   * @return
	   *
	   * @author 罗书明 CreateDate: 2017年8月2日
	   *
	   */
	  public int deleteByPrimaryKey(String str);

	/**
	 * 查询运营日报
	 * @param findOperateDayReport
	 * @return
	 */
	List<FindOperateDayReportReturn> findOperationDayChooseList(FindOperateDayReport findOperateDayReport);
}
