package com.lj.business.weixin.service;

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
import com.lj.business.weixin.domain.WxRedpackDetailInfo;
import com.lj.business.weixin.dto.FindWxRedpackDetailInfoPage;
import com.lj.business.weixin.dto.UpdateRedpackDetailByPoll;
import com.lj.business.weixin.dto.WxJobRedPackInfoDto;
import com.lj.business.weixin.dto.WxRedpackDetailInfoDto;
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
 * CreateDate: 2017-08-22
 */
public interface IWxRedpackDetailInfoService {
	
	/**
	 * 
	 *
	 * 方法说明：添加微信红包任务明细信息
	 *
	 * @param wxRedpackDetailInfoDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addWxRedpackDetailInfo(WxRedpackDetailInfoDto wxRedpackDetailInfoDto) throws TsfaServiceException;
	
	
	public Integer findWxRedpackDetailTotalCount(FindWxRedpackDetailInfoPage findWxRedpackDetailInfoPage);
	public void addWxRedpackDetailInfoBatch(List<String> noWxs,WxJobRedPackInfoDto wxJobRedPackInfoDto) throws TsfaServiceException;
	
	WxRedpackDetailInfo addWxRedpackDetailInfo2(WxRedpackDetailInfoDto wxRedpackDetailInfoDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找微信红包任务明细信息
	 *
	 * @param findWxRedpackDetailInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public WxRedpackDetailInfoDto findWxRedpackDetailInfo(WxRedpackDetailInfoDto wxRedpackDetailInfoDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询微信红包任务明细信息
	 *
	 * @param findWxRedpackDetailInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<WxRedpackDetailInfoDto>  findWxRedpackDetailInfos(FindWxRedpackDetailInfoPage findWxRedpackDetailInfoPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改微信红包任务明细信息
	 *
	 * @param updateWxRedpackDetailInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateWxRedpackDetailInfo(WxRedpackDetailInfoDto wxRedpackDetailInfoDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询微信红包任务明细信息
	 *
	 * @param findWxRedpackDetailInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<WxRedpackDetailInfoDto> findWxRedpackDetailInfoPage(FindWxRedpackDetailInfoPage findWxRedpackDetailInfoPage) throws TsfaServiceException;
	
	/**
	 *
	 * 方法说明：成功发送红包的总金额
	 * @param findWxRedpackDetailInfoPage
	 * @return
	 * @author 李端强 CreateDate: 2018年2月1日
	 *
	 */
	public Long findWxRedpackDetailTotalMoney(FindWxRedpackDetailInfoPage findWxRedpackDetailInfoPage)throws TsfaServiceException;
	
	
	public Long getSumAmtByNoWxToday(String noWx)throws TsfaServiceException;
	

	/**
	 *
	 * 方法说明：已发红包记录的年份集合
	 * @return 年份集合
	 * @author 李端强 CreateDate: 2018年1月31日
	 */
	public List<String> findWxRedpackDetailYears()throws TsfaServiceException;
	
	
	/**
	 * 根据商户单号查询红包信息
	 * @param orderNo
	 * @return
	 */
	public WxRedpackDetailInfoDto findWxRedpackDetailinfoByOrderNo(String orderNo)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：根据商户配置发送红包
	 *
	 *
	 * @author 许新龙 CreateDate: 2018年3月19日
	 * @param merchantNo
	 *
	 */
	void sendRedPackAfterAddWxFriends(Map<String, String> paramsMap, String noWx, String wxNoShop, String merchantNo, String shopNo, String nickName)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据中控返回轮询红包结果更新红包信息
	 *
	 * @param updateRedpackDetailByPoll
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月18日
	 *
	 */
	public void updateRedpackDetailByPoll(UpdateRedpackDetailByPoll updateRedpackDetailByPoll)throws TsfaServiceException;
	
	
	/**
	 * 方法说明：根据活动给用户实时发红包
	 * @return
	 * @throws TsfaServiceException
	 */
	public  Map<String, String> addWxRedpackDetailInfoByActivity(WxRedpackDetailInfoDto wxRedpackDetailInfoDto)throws TsfaServiceException;
	
	/**
	 * 发送普通红包（IM使用）
	 * @param wxRedpackDetailInfoDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public  void sendWxRedpackByIm(WxRedpackDetailInfoDto wxRedpackDetailInfoDto, String messageType, String source)throws TsfaServiceException;


	/**
	 * 
	 *@Desc 查询发送的红包数量:包含发送中+发送成功+发送失败+已领取+已退回总数量
	 *@param find
	 *@return
	 *@return Integer
	 *@author 贾光朝
	 *@createDate 2019年4月13日下午4:56:18
	 */
	public Integer findWxRedpackDetailTotalSendCount(FindWxRedpackDetailInfoPage find)throws TsfaServiceException;
}
