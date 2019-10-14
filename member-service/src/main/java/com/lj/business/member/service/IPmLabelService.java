package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.AddPmLabel;
import com.lj.business.member.dto.AddPmLabelReturn;
import com.lj.business.member.dto.ChangePmLabel;
import com.lj.business.member.dto.DelPmLabel;
import com.lj.business.member.dto.DelPmLabelReturn;
import com.lj.business.member.dto.FindPmLabel;
import com.lj.business.member.dto.FindPmLabelPage;
import com.lj.business.member.dto.FindPmLabelPageReturn;
import com.lj.business.member.dto.FindPmLabelReturn;
import com.lj.business.member.dto.FindPmLabelReturnList;
import com.lj.business.member.dto.PmLabelDto;
import com.lj.business.member.dto.UpdatePmLabel;
import com.lj.business.member.dto.UpdatePmLabelReturn;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 *         CreateDate: 2017-06-14
 */
public interface IPmLabelService {

	/**
	 * 方法说明：添加客户标签信息.
	 *
	 * @param addPmLabel the add pm label
	 * @return the adds the pm label return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public AddPmLabelReturn addPmLabel(AddPmLabel addPmLabel) throws TsfaServiceException;

	/**
	 * 方法说明：查找客户标签信息.
	 *
	 * @param findPmLabel the find pm label
	 * @return the find pm label return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public FindPmLabelReturn findPmLabel(FindPmLabel findPmLabel) throws TsfaServiceException;

	/**
	 * 方法说明：删除客户标签信息.
	 *
	 * @param delPmLabel the del pm label
	 * @return the del pm label return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public DelPmLabelReturn delPmLabel(DelPmLabel delPmLabel) throws TsfaServiceException;

	/**
	 * 方法说明：修改客户标签信息.
	 *
	 * @param updatePmLabel the update pm label
	 * @return the update pm label return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public UpdatePmLabelReturn updatePmLabel(UpdatePmLabel updatePmLabel) throws TsfaServiceException;

	/**
	 * 方法说明：查找客户标签信息.
	 *
	 * @param findPmLabelPage the find pm label page
	 * @return the page< find pm label page return>
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public Page<FindPmLabelPageReturn> findPmLabelPage(FindPmLabelPage findPmLabelPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据商户编号统计商户下所有导购客户标签(导购维度)
	 *
	 * @param findPmLabel
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月4日
	 *
	 */
	public List<FindPmLabelReturnList> findPmlabelGuidMember() throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据商户编号统计商户下所有导购客户标签(商户维度)
	 *
	 * @param findPmLabel
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月4日
	 *
	 */
	public List<FindPmLabelReturnList> findPmlabelMerchantNo() throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据商户编号统计商户下所有导购客户标签(终端维度)
	 *
	 * @param findPmLabel
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月4日
	 *
	 */
	public List<FindPmLabelReturnList> findPmlabelShop() throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据商户编号统计商户下所有导购客户标签(区域维度)
	 *
	 * @param findPmLabel
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月4日
	 *
	 */
	public List<FindPmLabelReturnList> findPmlabelAreaCode() throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据商户号查询标签列表
	 *
	 * @param merchantNo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2017年12月8日
	 *
	 */
	List<PmLabelDto> findPmLabelByMerchantNo(String merchantNo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：更新客户标签，先删除，再插入
	 *
	 * @param changePmLabel
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2017年12月8日
	 *
	 */
	void changePmLabel(ChangePmLabel changePmLabel) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据客户号和商户号查询该客户的标签
	 *
	 * @param findPmLabel
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2017年12月11日
	 *
	 */
	public List<PmLabelDto> findPmLabelByMemberNoAndMerchantNo(FindPmLabel findPmLabel) throws TsfaServiceException;

	/**
	 * 方法说明：根据商户号/上次更新时间查询标签列表
	 * 
	 * @param merchantNo
	 * @param lastTime   上次最后更新时间
	 * @author 李端强 CreateDate: 2018年1月8日
	 */
	public List<PmLabelDto> findPmLabelByLastTime(Map<String, Object> paramMap) throws TsfaServiceException;

	/**
	 * 根据商户号And终端微信获取标签列表（包含统计数和客户基本信息）
	 * 
	 * @param parmap
	 * @return
	 */
	List<Map<String, String>> findPmLabelByMerchantNoAndShopWx(Map<String, String> parmap) throws TsfaServiceException;

	/**
	 * 删除客户的标签
	 * 
	 * @return
	 */
	int deletePmLabelByMemberNo(String memberNo, String pmLabelCode, String shopWx) throws TsfaServiceException;

	/**
	 * 给客户打标签
	 * 
	 * @param memberNo
	 * @param pmLabelCode
	 * @return
	 */
	int addPmLabelByMemberNo(String memberNo, String pmLabelCode, String pmLabelName, String shopWx, int type)
			throws TsfaServiceException;

	/**
	 * @Desc
	 * @param addPmLabel
	 * @return
	 * @return int
	 * @author 贾光朝
	 * @createDate 2019年5月29日下午12:25:40
	 */
	public int selectCountByMerchantNo(AddPmLabel addPmLabel) throws TsfaServiceException;

}
