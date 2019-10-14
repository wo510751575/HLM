package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.member.dto.TmallBonusRecordDto;
import com.lj.business.member.dto.FindTmallBonusRecordPage;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
public interface ITmallBonusRecordService {
	
	/**
	 * 
	 *
	 * 方法说明：添加天猫订单红包记录信息
	 *
	 * @param tmallBonusRecordDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addTmallBonusRecord(TmallBonusRecordDto tmallBonusRecordDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单红包记录信息
	 *
	 * @param findTmallBonusRecord
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public TmallBonusRecordDto findTmallBonusRecord(TmallBonusRecordDto tmallBonusRecordDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询天猫订单红包记录信息
	 *
	 * @param findTmallBonusRecordPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<TmallBonusRecordDto>  findTmallBonusRecords(FindTmallBonusRecordPage findTmallBonusRecordPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改天猫订单红包记录信息
	 *
	 * @param updateTmallBonusRecord
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateTmallBonusRecord(TmallBonusRecordDto tmallBonusRecordDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询天猫订单红包记录信息
	 *
	 * @param findTmallBonusRecordPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<TmallBonusRecordDto> findTmallBonusRecordPage(FindTmallBonusRecordPage findTmallBonusRecordPage) throws TsfaServiceException;
	

	int findTmallBonusRecordPageCount(FindTmallBonusRecordPage findTmallBonusRecordPage) throws TsfaServiceException;
	
	/**
	 * 根据订单号获取
	 * @param orderNo
	 * @return
	 * @throws TsfaServiceException
	 */
	public TmallBonusRecordDto findByOrderNo(String orderNo,String merchantNo) throws TsfaServiceException;
}
