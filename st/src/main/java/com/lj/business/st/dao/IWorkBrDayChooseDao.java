package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.WorkBrDayChoose;
import com.lj.business.st.dto.FindWorkDayGmIndex;
import com.lj.business.st.dto.FindWorkDayGmIndexListReturn;
import com.lj.business.st.dto.WorkBrDayChoosePage;

/**
 * 
 * 
 * 类说明：日工作简报选择表（基础）
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
public interface IWorkBrDayChooseDao {
   
	/**
	 * 
	 *
	 * 方法说明：新增插入
	 *
	 * @param workBrDayChoose
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
    int insertSelective(WorkBrDayChoose workBrDayChoose);

    /**
     * 
     *
     * 方法说明：主键查询方法
     *
     * @param code
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    WorkBrDayChoose selectByPrimaryKey(String code);

    /**
     * 
     *
     * 方法说明：更新修改方法
     *
     * @param workBrDayChoose
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    int updateByPrimaryKeySelective(WorkBrDayChoose workBrDayChoose);

    /**
     * 
     *
     * 方法说明：查询数量
     *
     * @param workBrDayChoosePage
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
    int findWorkBrDayPageCount(WorkBrDayChoosePage workBrDayChoosePage);
    
    /**
     * 
     *
     * 方法说明：根据商户号查询导购日工作简报选择项
     *
     * @param findWorkDayGmIndex
     * @return
     *
     * @author 冯辉 CreateDate: 2017年7月31日
     *
     */
    List<FindWorkDayGmIndexListReturn> findWorkDayGmIndexByMNo(FindWorkDayGmIndex findWorkDayGmIndex);
    
    /**
     * 
     *
     * 方法说明：根据商户编号或者主键删除
     *
     * @param str
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月2日
     *
     */
    int deleteByPrimaryKey(String str);
   
    /**
     * 
     *
     * 方法说明：根据商户编号查询
     *
     * @param findWorkDayGmIndex
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月2日
     *
     */
    List<FindWorkDayGmIndexListReturn> findWorkBrDayChooseList(FindWorkDayGmIndex findWorkDayGmIndex);
}