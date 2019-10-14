package com.lj.business.st.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.business.st.dto.FindWorkDayGmIndex;
import com.lj.business.st.dto.FindWorkDayGmIndexListReturn;
import com.lj.business.st.dto.WorkBrDayChooseDto;
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
public interface IWorkBrDayChooseService {

	/**
	 * 
	 *
	 * 方法说明：新增
	 *  
	 * @param workBrDayChoose
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月1日
	 *
	 */
	public WorkBrDayChooseDto insertSelective(WorkBrDayChooseDto workBrDayChooseDto);

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
    public WorkBrDayChooseDto selectByPrimaryKey(String code);

    /**
     * 
     *
     * 方法说明：编辑
     *
     * @param workBrDayChoose
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月1日
     *
     */
    public WorkBrDayChooseDto updateByPrimaryKeySelective(WorkBrDayChooseDto workBrDayChooseDto);
    
 

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
     * 方法说明：根据商户编号删除
     *
     * @param str
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月2日
     *
     */
    public int deleteByPrimaryKey(String str);
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
   public List<FindWorkDayGmIndexListReturn> findWorkBrDayChooseList(FindWorkDayGmIndex findWorkDayGmIndex);
}
