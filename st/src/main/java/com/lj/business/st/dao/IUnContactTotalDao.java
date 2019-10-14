package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.UnContactTotal;
import com.lj.business.st.dto.FindUnContactTotalInfo;
import com.lj.business.st.dto.FindUnContactTotalInfoReturn;
import com.lj.business.st.dto.FindUnContactTotalPage;
import com.lj.business.st.dto.FindUnContactTotalPageReturn;
import com.lj.business.st.dto.FindUnContactTotalReturn;
/**
 * 
 * 
 * 类说明：未联系客户统计DAO
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月14日
 */
public interface IUnContactTotalDao {
	/**
	 * 
	 *
	 * 方法说明：未联系客户统计删除
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
     * 方法说明：未联系客户统计新增
     *
     * @param record
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    int insert(UnContactTotal record);
    /**
     * 
     *
     * 方法说明：未联系客户统计新增（按字段新增）
     *
     * @param record
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    int insertSelective(UnContactTotal record);
    /**
     * 
     *
     * 方法说明：未联系客户统计按主键查询
     *
     * @param code
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    UnContactTotal selectByPrimaryKey(String code);
    /**
     * 
     *
     * 方法说明：未联系客户统计更新
     *
     * @param record
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    int updateByPrimaryKeySelective(UnContactTotal record);
    /**
     * 
     *
     * 方法说明：未联系客户统计更新
     *
     * @param record
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    int updateByPrimaryKey(UnContactTotal record);
    /**
     * 
     *
     * 方法说明：未联系客户统计查询方法（list）
     * 
     * @param findUnContactTotalPage
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    List<FindUnContactTotalPageReturn> findUnContactTotalPage(FindUnContactTotalPage findUnContactTotalPage);
    /**
     * 
     *
     * 方法说明：查询数量
     *
     * @param findUnContactTotalPage
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
	int findUnContactTotalPageCount(FindUnContactTotalPage findUnContactTotalPage);
	/**
	 * 
	 *
	 * 方法说明：导购编号查询
	 *
	 * @param findUnContactTotalInfo
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	 FindUnContactTotalInfoReturn findUnContactTotalInfo(FindUnContactTotalInfo findUnContactTotalInfo);
	 
	 /**
	  * 
	  *
	  * 方法说明：
	  *
	  * @return
	  *
	  * @author 冯辉 CreateDate: 2017年8月10日
	  *
	  */
	 List<FindUnContactTotalReturn> findList();
	 
	
}