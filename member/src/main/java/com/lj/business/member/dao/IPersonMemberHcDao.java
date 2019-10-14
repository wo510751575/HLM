/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.dto.FindClientPmTypeIndex;
import com.lj.business.member.dto.FindClientPmTypeIndexReturn;
import com.lj.business.member.dto.FindNewPmPage;
import com.lj.business.member.dto.FindNewPmPageReturn;
import com.lj.business.member.dto.FindPmWxBpInfoHcPage;
import com.lj.business.member.dto.FindPmWxBpInfoReturnHc;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月24日
 */
public interface IPersonMemberHcDao {
	
    /**
	 * 
	 *
	 * 方法说明：客情管理-分类客户明细查询列表
	 *
	 * @param findClientPmTypeIndex
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月23日
	 *
	 */
	public List<FindClientPmTypeIndexReturn> findClientPmTypeIndex(FindClientPmTypeIndex findClientPmTypeIndex);
	
	/**
	 * 
	 *
	 * 方法说明：新增顾客-列表(理发)
	 *
	 * @param findNewPmPage
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月17日
	 *
	 */
	public List<FindNewPmPageReturn> findNewPmPageHc(FindNewPmPage findNewPmPage);
	
	/**
	 * 
	 *
	 * 方法说明：微信提醒-列表查询-统计
	 *
	 * @param findPmWxBpInfoHcPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月24日
	 *
	 */
	int findPmWxBpInfoHcPageCount(FindPmWxBpInfoHcPage findPmWxBpInfoHcPage);
	
	/**
	 * 
	 *
	 * 方法说明：微信提醒-列表查询-列表
	 *
	 * @param findPmWxBpInfoHcPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月24日
	 *
	 */
	List<FindPmWxBpInfoReturnHc> findPmWxBpInfoHcPageList(FindPmWxBpInfoHcPage findPmWxBpInfoHcPage);

}
