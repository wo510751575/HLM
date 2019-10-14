package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.business.member.dto.guidCard.AddGuidCard;
import com.lj.business.member.dto.guidCard.DelGuidCard;
import com.lj.business.member.dto.guidCard.FindGuidCard;
import com.lj.business.member.dto.guidCard.FindGuidCardPage;
import com.lj.business.member.dto.guidCard.FindGuidCardPageReturn;
import com.lj.business.member.dto.guidCard.FindGuidCardReturn;
import com.lj.business.member.dto.guidCard.UpdateGuidCard;
import com.lj.business.member.dto.guidCardCustomer.GuidCardAddNumDto;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 梅宏博
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IGuidCardService {
	
	/**
	 * 
	 *
	 * 方法说明：添加导购名片表信息
	 *
	 * @param addGuidCard
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年07月10日
	 *
	 */
	public void addGuidCard(AddGuidCard addGuidCard) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购名片表信息
	 *
	 * @param findGuidCard
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年07月10日
	 *
	 */
	public FindGuidCardReturn findGuidCard(FindGuidCard findGuidCard) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除导购名片表信息
	 *
	 * @param delGuidCard
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年07月10日
	 *
	 */
	public void delGuidCard(DelGuidCard delGuidCard) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购名片表信息
	 *
	 * @param updateGuidCard
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年07月10日
	 *
	 */
	public void updateGuidCard(UpdateGuidCard updateGuidCard)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购名片表信息
	 *
	 * @param findGuidCardPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindGuidCardPageReturn> findGuidCardPage(FindGuidCardPage findGuidCardPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据导购号查询导购名片
	 *
	 * @param findGuidCard
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月3日
	 *
	 */
	public FindGuidCardReturn findGuidCardByGm(FindGuidCard findGuidCard);

	/**
	 * 
	 *
	 * 方法说明：增加名片人气、点赞和保存量
	 *
	 * @param guidCardAddNumDto
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月3日
	 *
	 */
	public boolean addGuidCardNum(GuidCardAddNumDto guidCardAddNumDto);

	/**
	 * 
	 *
	 * 方法说明：查找导购名片
	 *
	 * @param guidCardAddNumDto
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月3日
	 *
	 */
	public List<FindGuidCardReturn> findGuidCards(
			GuidCardAddNumDto guidCardAddNumDto);

	/**
	 * 
	 *
	 * 方法说明：删除客户保存的导购名片
	 *
	 * @param guidCardAddNumDto
	 *
	 * @author 梅宏博  CreateDate: 2017年11月8日
	 *
	 */
	public void delGuidCardSave(GuidCardAddNumDto guidCardAddNumDto);

	/**
	 * 
	 *
	 * 方法说明：获取openId签名
	 *
	 * @param jsCode
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月8日
	 *
	 */
	public String getSignature(String jsCode);

	/**
	 * 
	 *
	 * 方法说明：根据终端修改导购名片
	 *
	 * @param updateGuidCard
	 *
	 * @author 梅宏博  CreateDate: 2017年11月13日
	 *
	 */
	public void updateGuidCardByShop(UpdateGuidCard updateGuidCard);
	

}
