package com.lj.business.st.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.st.domain.EmployeeAnalyze;
/**
 * 
 * 
 * 类说明：员工画像统计
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月31日
 */
public interface IEmployeeAnalyzeDao {
  
    /**
     * 
     *
     * 方法说明：新增员工画像
     *
     * @param record
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月31日
     *
     */
    int insertSelective(EmployeeAnalyze record);
     /**
      * 
      *
      * 方法说明：查询员工画像年龄分布统计
      *
      * @param map
      * @return
      *
      * @author 罗书明 CreateDate: 2017年7月31日
      *
      */
    List<Map<String,Object>> findEmployeeAnalyzeList(Map<String,Object> map);

}