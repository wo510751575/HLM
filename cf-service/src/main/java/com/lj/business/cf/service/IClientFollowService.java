package com.lj.business.cf.service;

import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cf.domain.ClientFollow;
import com.lj.business.cf.dto.FindCForCKLastDateDto;
import com.lj.business.cf.dto.FindClientFollowClientKeep;
import com.lj.business.cf.dto.FindClientFollowClientKeepReturn;
import com.lj.business.cf.dto.NoticeSendMetGenClientFollowDto;
import com.lj.business.cf.dto.clientFollow.AddClientFollow;
import com.lj.business.cf.dto.clientFollow.AddClientFollowReturn;
import com.lj.business.cf.dto.clientFollow.DelClientFollow;
import com.lj.business.cf.dto.clientFollow.DelClientFollowReturn;
import com.lj.business.cf.dto.clientFollow.FindClientFollow;
import com.lj.business.cf.dto.clientFollow.FindClientFollowMap;
import com.lj.business.cf.dto.clientFollow.FindClientFollowPage;
import com.lj.business.cf.dto.clientFollow.FindClientFollowPageReturn;
import com.lj.business.cf.dto.clientFollow.FindClientFollowReturn;
import com.lj.business.cf.dto.clientFollow.UpdateClientFollow;
import com.lj.business.cf.dto.clientFollow.UpdateClientFollowReturn;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.cf.dto.pmAbandon.AbandonCheckDto;
import com.lj.business.cf.dto.pmAbandon.AbandonCheckReturn;
import com.lj.business.cf.dto.taskSetShop.FindTaskSetAndOrder;


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
 * CreateDate: 2017-06-14
 */
public interface IClientFollowService {
	
	/**
	 * 
	 *
	 * 方法说明：添加客户跟踪表信息
	 *
	 * @param addClientFollow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public AddClientFollowReturn addClientFollow(AddClientFollow addClientFollow) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找客户跟踪表信息
	 *
	 * @param findClientFollow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public FindClientFollowReturn findClientFollow(FindClientFollow findClientFollow) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除客户跟踪表信息
	 *
	 * @param delClientFollow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public DelClientFollowReturn delClientFollow(DelClientFollow delClientFollow) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改客户跟踪表信息
	 *
	 * @param updateClientFollow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public UpdateClientFollowReturn updateClientFollow(UpdateClientFollow updateClientFollow)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找客户跟踪表信息
	 *
	 * @param findClientFollowPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public Page<FindClientFollowPageReturn> findClientFollowPage(FindClientFollowPage findClientFollowPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找客户跟踪表信息-APP
	 *
	 * @param FindClientFollow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	public List<FindClientFollowReturn> cfHistory(FindClientFollow findClientFollow) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：添加客户跟踪表信息-成单信息
	 *
	 * @param AddClientFollow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	public AddClientFollowReturn addCFOrder(AddClientFollow addClientFollow,String flag) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：添加客户跟踪表信息-成单信息
	 *
	 * @param addClientFollow
	 * @param flag
	 * @param isCreateCf
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月8日
	 *
	 */
	public AddClientFollowReturn addCFOrder(AddClientFollow addClientFollow,String flag,boolean isCreateCf,boolean isAddIntegal) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：店长审批跟踪放弃记录
	 *
	 * @param abandonCheckDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年7月11日
	 *
	 */
	public AbandonCheckReturn abandonCheck(AbandonCheckDto abandonCheckDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：客户购买信息-APP
	 *
	 * @param FindClientFollow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author rain CreateDate: 2017年7月4日
	 *
	 */
	public List<FindClientFollowReturn> orderHistory(FindClientFollow findClientFollow) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：跟进或者维护列表
	 *
	 * @param findClientFollowClientKeep
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年7月11日
	 *
	 */
	FindClientFollowClientKeepReturn cfOrCkHistory(FindClientFollowClientKeep findClientFollowClientKeep) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询导购今天的订单
	 *
	 * @param findTaskSetAndOrder
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月21日
	 *
	 */
	List<ClientFollow> findTodayOrder(FindTaskSetAndOrder findTaskSetAndOrder);
	
	/**
	 * 
	 *
	 * 方法说明：查询订单月销售
	 *
	 * @param findTaskSetAndOrder
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月21日
	 *
	 */
	Long findMonthOrderMoney(FindTaskSetAndOrder findTaskSetAndOrder);
	
	/**
	 * 
	 *
	 * 方法说明：查找最后一条跟进记录
	 *
	 * @param findCForCKLastDateDto
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月27日
	 *
	 */
	ClientFollow findLastClientFollow(FindCForCKLastDateDto findCForCKLastDateDto)throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：统计跟踪次数
	 *
	 * @param findClientFollowMap
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月3日
	 *
	 */
	public  int findClientFollowCount(FindClientFollowMap findClientFollowMap)throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：统计跟踪总数
	 *
	 * @param findClientFollowMap
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月3日
	 *
	 */
	public  int findClientFollowSum(FindClientFollowMap findClientFollowMap);
	
	/**
	 * 
	 *
	 * 方法说明：跟进次数
	 *
	 * @param findClientFollow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月10日
	 *
	 */
	public int cfCount(FindClientFollow findClientFollow) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：统计每天的客户
	 *
	 * @param findClientFollowMap
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月19日
	 *
	 */
	public int findLClientFollowCountMemberNo(FindClientFollowMap findClientFollowMap);
    /**
     * 
     *
     * 方法说明：统计每天的成单数
     *
     * @param findClientFollowMap
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月19日
     *
     */
   public  int findLClientFollowCountDeal(FindClientFollowMap findClientFollowMap);
   /**
    * 
    *
    * 方法说明：统计客户总数
    *
    * @param findClientFollowMap
    * @return
    *
    * @author 罗书明 CreateDate: 2017年8月19日
    *
    */
  public int findClientFollowSumMemberNo(FindClientFollowMap findClientFollowMap);
  
  /**
   * 
   *
   * 方法说明：提醒发送素材后生成跟进记录
   *
   * @param noticeSendMetGenClientFollowDto
   * @throws TsfaServiceException
   *
   * @author 冯辉 CreateDate: 2017年8月19日
   *
   */
  public void noticeSendMetGenClientFollow(NoticeSendMetGenClientFollowDto noticeSendMetGenClientFollowDto) throws TsfaServiceException;

  /**
   * 
   *
   * 方法说明：统计最后一次跟进时间
   *
   * @param findClientFollowMap
   * @return
   *
   * @author 刘培 CreateDate: 2017年8月21日
   *
   */
  public FindClientFollowReturn findClientFollowByGmAndMember(FindClientFollow findClientFollow);

  /**
   * 
   *
   * 方法说明：商户运营日报表
   *
   * @param map
   * @return
   *
   * @author 梅宏博 CreateDate: 2017年9月28日
   *
   */
  public List<Map<String, Object>> findSumFollowGroupByShop(
		Map<String, Object> map);

  /**
   * 
   *
   * 方法说明：根据条件查询跟踪编号
   *
   * @param findClientFollow
   * @return
   *
   * @author 梅宏博  CreateDate: 2017年10月11日
   *
   */
  public ClientFollow findClientFollowSelect(FindClientFollow findClientFollow);

  /**
   * 
   *
   * 方法说明：根据导购号查询当天客户跟进量
   *
   * @param map
   * @return
   *
   * @author 梅宏博  CreateDate: 2017年10月23日
   *
   */
  public Long findCountNumPmCfDayByGm(Map<String, Object> map);

  /**
   * 
   *
   * 方法说明：根据导购号查询当天客户跟进次数
   *
   * @param map
   * @return
   *
   * @author 梅宏博  CreateDate: 2017年10月23日
   *
   */
  public Long findCountNumCfDayByGm(Map<String, Object> map);
  
  /**
   * 
   *
   * 方法说明：微软CRM-获取客户的跟进记录
   * 由CRM定时调用，以获取客户的跟进记录增量变化数据。
   * @param map
   * lastTime	最后更新时间
   * memberNo	客户编号
   * @return
   *customername	客户姓名
	customerphone	客户手机号
	gmname	导购姓名
	cfno	跟进编号
	follownum	跟进次数
	followtime	跟进时间
	followtype	跟进类型
	followinfo	跟进情况
	notdealreason	未成单原因
	deal	是否成单
	status	跟进状态
	orderamount	订单金额
	nextdate	下次跟进提醒时间
	clientexpnum	客户体验次数
	clientinvitenum	客户邀约次数
	clientguidnum	客户引导次数
   * @author 段志鹏 CreateDate: 2018年3月1日
   *
   */
  public List<Map<String,Object>> getClientFollowInfo(Map<String, Object> map);
}
