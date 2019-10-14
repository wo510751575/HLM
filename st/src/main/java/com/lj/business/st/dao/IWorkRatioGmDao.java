package com.lj.business.st.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.domain.WorkRatioGm;
import com.lj.business.st.dto.FindFollowClientTotalIndex;
import com.lj.business.st.dto.FindOperateDayReport;
import com.lj.business.st.dto.FindWorkDayGmIndex;
import com.lj.business.st.dto.FindWorkRatioGmPage;
import com.lj.business.st.dto.FindWorkRatioGmPageReturn;
import com.lj.business.st.dto.FindWrgTotal;
import com.lj.business.st.dto.FindWrgTotalReturn;

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
public interface IWorkRatioGmDao {
	
	/**
	 * 
	 *
	 * 方法说明：获取导购工作统计信息_H5用
	 *
	 * @param findWrgTotal
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月31日
	 *
	 */
	 List<FindWrgTotalReturn> findWrgTotal(FindWrgTotal findWrgTotal);
	 
	/**
	 * 
	 *
	 * 方法说明：运营日报表选择删除方法
	 *
	 * @param code
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
    int deleteByPrimaryKey(String code);
     /**
      * 
      *
      * 方法说明：运营日报表选择新增方法
      *
      * @param record
      * @return
      *
      * @author 罗书明 CreateDate: 2017年7月14日
      *
      */
    int insert(WorkRatioGm record);
    /**
     * 
     *
     * 方法说明：按字段新增方法
     *
     * @param record
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    int insertSelective(WorkRatioGm record);
    /**
     * 
     *
     * 方法说明：按主键查询
     *
     * @param code
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    WorkRatioGm selectByPrimaryKey(String code);
    /**
     * 
     *
     * 方法说明：查询方法
     *
     * @param workRatioGm
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    WorkRatioGm selectByParams(WorkRatioGm workRatioGm);
    /**
     * 
     *
     * 方法说明：更新方法
     *
     * @param record
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    int updateByPrimaryKeySelective(WorkRatioGm record);
    /**
     * 
     *
     * 方法说明：按主键更新
     *
     * @param record
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    int updateByPrimaryKey(WorkRatioGm record);
     /**
      * 
      *
      * 方法说明：分页查询
      *
      * @param findWorkRatioGmPage
      * @return
      *
      * @author 罗书明 CreateDate: 2017年7月14日
      *
      */
    List<FindWorkRatioGmPageReturn> findWorkRatioGmPage(FindWorkRatioGmPage findWorkRatioGmPage);
    /**
     * 
     *
     * 方法说明：查询数量
     *
     * @param findWorkRatioGmPage
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
   	int findWorkRatioGmPageCount(FindWorkRatioGmPage findWorkRatioGmPage);
   	
   	/**
   	 * 
   	 *
   	 * 方法说明：查询导购工作统计
   	 *
   	 * @param findFollowClientTotalIndex
   	 * @return
   	 *
   	 * @author 冯辉 CreateDate: 2017年7月31日
   	 *
   	 */
   	List<WorkRatioGm> findWorkRatioGmList(FindFollowClientTotalIndex findFollowClientTotalIndex);
   	
   	/**
   	 * 
   	 *
   	 * 方法说明：查询导购排名信息
   	 *
   	 * @param findFollowClientTotalIndex
   	 * @return
   	 *
   	 * @author 冯辉 CreateDate: 2017年7月31日
   	 *
   	 */
   	Integer findGmIndex(FindFollowClientTotalIndex findFollowClientTotalIndex);
   	
   	/**
   	 * 
   	 *
   	 * 方法说明：按天查询导购完成统计
   	 *
   	 * @param findWorkDayGmIndex
   	 * @return
   	 *
   	 * @author 冯辉 CreateDate: 2017年7月31日
   	 *
   	 */
   	WorkRatioGm findWorkRatioGmByGmStDate(FindWorkDayGmIndex findWorkDayGmIndex);

	List<WorkRatioGm> findWorkRatioGmParams(FindOperateDayReport findOperateDayReport);

	/**
	 * 
	 *
	 * 方法说明：根据时间从导购工作统计中查询门店工作统计
	 *
	 * @param preday
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年8月21日
	 *
	 */
	List<WorkRatioGm> findWorkRatioShopByDay(Date preday);

	/**
	 * 
	 *
	 * 方法说明：根据时间和维度从导购工作统计中查询门店工作统计
	 *
	 * @param map
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年9月04日
	 *
	 */
	List<WorkRatioGm> findWorkRatioByDimDay(Map<String, Object> map);
}