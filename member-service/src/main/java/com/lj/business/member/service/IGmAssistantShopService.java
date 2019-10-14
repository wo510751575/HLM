package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.GmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.AddGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.AddGmAssistantShopReturn;
import com.lj.business.member.dto.gmAssistantShop.DelGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.DelGmAssistantShopReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopByImReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPage;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPageReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.gmAssistantShop.UpdateGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.UpdateGmAssistantShopReturn;


/**
 * 
 * 
 * 类说明：接口类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年12月2日
 */
public interface IGmAssistantShopService {
	
	/**
	 * 
	 *
	 * 方法说明：添加导购助手管理表信息
	 *
	 * @param addGmAssistantShop
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author  罗书明  CreateDate: 2017年12月2日
	 *
	 */
	public AddGmAssistantShopReturn addGmAssistantShop(AddGmAssistantShop addGmAssistantShop) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购助手管理表信息
	 *
	 * @param findGmAssistantShop
	 * @return
	 * @throws TsfaServiceException
	 *
	 *@author  罗书明  CreateDate: 2017年12月2日
	 *
	 */
	public FindGmAssistantShopReturn findGmAssistantShop(FindGmAssistantShop findGmAssistantShop) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除导购助手管理表信息
	 *
	 * @param delGmAssistantShop
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author  罗书明  CreateDate: 2017年12月2日
	 *
	 */
	public DelGmAssistantShopReturn delGmAssistantShop(DelGmAssistantShop delGmAssistantShop) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购助手管理表信息
	 *
	 * @param updateGmAssistantShop
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author  罗书明  CreateDate: 2017年12月2日
	 *
	 */
	public UpdateGmAssistantShopReturn updateGmAssistantShop(UpdateGmAssistantShop updateGmAssistantShop)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找导购助手管理表信息
	 *
	 * @param findGmAssistantShopPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author  罗书明  CreateDate: 2017年12月2日
	 *
	 */
	public Page<FindGmAssistantShopPageReturn> findGmAssistantShopPage(FindGmAssistantShopPage findGmAssistantShopPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购助手终端
	 *
	 * @param findGmAssistantShop
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年12月2日
	 *
	 */
	List<FindGmAssistantShopReturn> findGmAssistantShopList(FindGmAssistantShop findGmAssistantShop)throws TsfaServiceException;
	
	/**
	 * 查找导购助手终端列表，按微信分组
	 * @param findGmAssistantShop
	 * @return
	 * @throws TsfaServiceException
	 */
	List<FindGmAssistantShopReturn> findListGroupByNoWx(FindGmAssistantShop findGmAssistantShop)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：IM导购助手查询包括可用终端终端的终端
	 *
	 * @param findGmAssistantShop
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月22日
	 *
	 */
	List<FindGmAssistantShopByImReturn> findGmAssistantShopListByIm(FindGmAssistantShop findGmAssistantShop) throws TsfaServiceException;
	

	/**
	 * 获取终端编号集合根据导购助手分组
	 * @param assistantNo
	 * @param merchantNo
	 * @return
	 * @throws TsfaServiceException
	 */
	String findTidCodesByAssistantNo(String assistantNo,String merchantNo) throws TsfaServiceException;
	
	/**
	 * 获取数量
	 * @param findGmAssistantShopPage
	 * @return
	 * @throws TsfaServiceException
	 */
	int findGmAssistantShopPageCount(FindGmAssistantShopPage findGmAssistantShopPage) throws TsfaServiceException;
	
	/**
	 * 删除记录根据导购助手号
	 * @param assistantNo
	 * @return
	 */
	int deleteByAssistantNo(String assistantNo,String merchantNo);
	/**
     * 查询导购助手绑定的微信集合，按英文","分隔返回
     * @param assistantNo
     * @return
     */
    String findGroupConcatByAssNo(String assistantNo);
    
    
    /**
     * 根据微信查询已绑定的员工
     */
    List<GmAssistantShop> findGmAssistantListByWx(FindGmAssistantShop findGmAssistantShop);
    /**
     * 關聯終端查出頭像
     */
    public Page<FindGmAssistantShopPageReturn> findGmAssistantWithTerminalPage(
			FindGmAssistantShopPage findGmAssistantShopPage)
			throws TsfaServiceException;
    
    /**
     * 同步登录名
     * @param oldLoginName
     * @param loginName
     * @throws TsfaServiceException
     */
    void synByLoginName(String oldLoginName,String loginName)throws TsfaServiceException;
    
    /**
     * 根据终端微信和导购助手编号获取
     * @param noWx
     * @param assistantNo
     * @return
     * @throws TsfaServiceException
     */
    FindGmAssistantShopReturn findGmByWxAndNo(String noWxGm,String assistantNo)throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param updateGmAssistantShop
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午3:43:33
	 */
	public void delete(UpdateGmAssistantShop updateGmAssistantShop);
}
