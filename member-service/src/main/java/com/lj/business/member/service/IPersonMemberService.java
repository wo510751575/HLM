package com.lj.business.member.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.AddPersonMember;
import com.lj.business.member.dto.AddPersonMemberAll;
import com.lj.business.member.dto.AddPersonMemberByWx;
import com.lj.business.member.dto.AddPersonMemberByWxReturn;
import com.lj.business.member.dto.AddPersonMemberReturn;
import com.lj.business.member.dto.CountPersonMemberReturn;
import com.lj.business.member.dto.DelPersonMember;
import com.lj.business.member.dto.DelPersonMemberReturn;
import com.lj.business.member.dto.DoRepeatMemberDto;
import com.lj.business.member.dto.EditPersonMember;
import com.lj.business.member.dto.FindCountPersonMember;
import com.lj.business.member.dto.FindGmDistantPageReturn;
import com.lj.business.member.dto.FindGroupedUnContactMemberReturn;
import com.lj.business.member.dto.FindImIndexPage;
import com.lj.business.member.dto.FindImIndexPageReturn;
import com.lj.business.member.dto.FindMemberInfoReturn;
import com.lj.business.member.dto.FindMemberRecord;
import com.lj.business.member.dto.FindNewPmCountDto;
import com.lj.business.member.dto.FindNewPmPage;
import com.lj.business.member.dto.FindNewPmPageReturn;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberAgeStatisticsReturn;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindPersonMemberInterestStatisticsReturn;
import com.lj.business.member.dto.FindPersonMemberPage;
import com.lj.business.member.dto.FindPersonMemberPageReturn;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.FindPersonMemberReturnList;
import com.lj.business.member.dto.FindPersonMemberSexStatisticsReturn;
import com.lj.business.member.dto.FindPmInfoAll;
import com.lj.business.member.dto.FindPmInfoAllReturn;
import com.lj.business.member.dto.FindPmSeachPage;
import com.lj.business.member.dto.FindPmSeachPageReturn;
import com.lj.business.member.dto.FindPmType;
import com.lj.business.member.dto.FindPmTypeIndexPage;
import com.lj.business.member.dto.FindPmTypeIndexPageReturn;
import com.lj.business.member.dto.FindPmWxBpInfo;
import com.lj.business.member.dto.FindPmWxBpInfoReturn;
import com.lj.business.member.dto.FindPmWxInfo;
import com.lj.business.member.dto.FindPmWxInfoReturn;
import com.lj.business.member.dto.FindTodayManageShop;
import com.lj.business.member.dto.FindTodayManageShopReturn;
import com.lj.business.member.dto.FindUnContactMember;
import com.lj.business.member.dto.FindUnContactMemberReturn;
import com.lj.business.member.dto.FindUnchatMemberPage;
import com.lj.business.member.dto.FindUnchatMemberPageReturn;
import com.lj.business.member.dto.FindUrgentMbrPage;
import com.lj.business.member.dto.FindUrgentMbrPageReturn;
import com.lj.business.member.dto.MecMemberDto;
import com.lj.business.member.dto.MecMemberNoReturn;
import com.lj.business.member.dto.PersonMemberDto;
import com.lj.business.member.dto.PersonMemberStsGroupByShop;
import com.lj.business.member.dto.UpdatePersonMember;
import com.lj.business.member.dto.UpdatePersonMemberReturn;
import com.lj.business.member.dto.wxPmFollow.FindWxPmByGm;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 *         CreateDate: 2017-06-14
 */
public interface IPersonMemberService {

	/**
	 * 解绑定
	 * 
	 * @param wxNo
	 * @throws TsfaServiceException
	 */
	void updateCanclePersonMember(String gmNo, String wxNo, String noWxGm) throws TsfaServiceException;

	/**
	 * 转移客户
	 * 
	 * @param sourceGmNo
	 * @param rediectGmNo
	 * @param customerWXNo
	 * @throws TsfaServiceException
	 */
	void updateFriendsWithTransfer(String sourceGmNo, String gmName, String rediectGmNo, String customerWXNo,
			String noWxGm) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找客户所有微信信息
	 *
	 * @param findPmWxInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年7月20日
	 *
	 */
	public List<FindPmWxInfoReturn> findPmWxInfo(FindPmWxInfo findPmWxInfo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找客户所有微信信息及最新动态
	 *
	 * @param findPmWxInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年7月20日
	 *
	 */
	public List<FindPmWxBpInfoReturn> findPmWxBpInfo(FindPmWxBpInfo findPmWxBpInfo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：今日工作-当日新增客户查询_APP
	 *
	 * @param findNewPmPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年7月17日
	 *
	 */
	public Page<FindNewPmPageReturn> findNewPmPage(FindNewPmPage findNewPmPage) throws TsfaServiceException;

	/**
	 * 方法说明：查找客户及其基本信息.
	 *
	 * @param findPmInfoAll the find pm info all
	 * @return the find pm info all return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 段志鹏 CreateDate: 2017年7月3日
	 */
	public FindPmInfoAllReturn findPmInfoAll(FindPmInfoAll findPmInfoAll) throws TsfaServiceException;

	/**
	 * 方法说明：添加客户导购关联表,客户基础表数据.
	 *
	 * @param addPersonMemberAll the add person member all
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年7月12日
	 */
	public void addPersonMemberAll(AddPersonMemberAll addPersonMemberAll) throws TsfaServiceException;

	/**
	 * 方法说明：添加客户表信息.
	 *
	 * @param addPersonMember the add person member
	 * @return the adds the person member return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 段志鹏 CreateDate: 2017年7月3日
	 */
	public AddPersonMemberReturn addPersonMember(AddPersonMember addPersonMember) throws TsfaServiceException;

	/**
	 * 方法说明：查找客户表信息.
	 *
	 * @param findPersonMember the find person member
	 * @return the find person member return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 段志鹏 CreateDate: 2017年7月3日
	 */
	public FindPersonMemberReturn findPersonMember(FindPersonMember findPersonMember) throws TsfaServiceException;

	/**
	 * 方法说明：删除客户表信息.
	 *
	 * @param delPersonMember the del person member
	 * @return the del person member return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 段志鹏 CreateDate: 2017年7月3日
	 */
	public DelPersonMemberReturn delPersonMember(DelPersonMember delPersonMember) throws TsfaServiceException;

	/**
	 * 方法说明：修改客户表信息.
	 *
	 * @param updatePersonMember the update person member
	 * @return the update person member return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 段志鹏 CreateDate: 2017年7月3日
	 */
	public UpdatePersonMemberReturn updatePersonMember(UpdatePersonMember updatePersonMember)
			throws TsfaServiceException;

	/**
	 * 方法说明：客户信息编辑.
	 *
	 * @param editPersonMember the edit person member
	 * @return the update person member return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 冯辉 CreateDate: 2017年7月13日
	 */
	public UpdatePersonMemberReturn editPersonMember(EditPersonMember editPersonMember) throws TsfaServiceException;

	/**
	 * 方法说明：根据客户号和终端微信修改客户表信息.
	 *
	 * @param updatePersonMember the update person member
	 * @return the update person member return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 冯辉 CreateDate: 2017年7月11日
	 */
	public UpdatePersonMemberReturn updatePersonMemberByMGM(UpdatePersonMember updatePersonMember)
			throws TsfaServiceException;

	/**
	 * 方法说明：根据客户号和导购号查询客户.
	 *
	 * @param findPersonMember the find person member
	 * @return the find person member return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 冯辉 CreateDate: 2017年7月11日
	 */
	public FindPersonMemberReturn findPersonMemberByMGM(FindPersonMember findPersonMember) throws TsfaServiceException;

	/**
	 * 方法说明：查找客户表信息.
	 *
	 * @param findPersonMemberPage the find person member page
	 * @return the page< find person member page return>
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 段志鹏 CreateDate: 2017年7月3日
	 */
	public Page<FindPersonMemberPageReturn> findPersonMemberPage(FindPersonMemberPage findPersonMemberPage)
			throws TsfaServiceException;

	/**
	 * 方法说明：客户管理首页：分类信息明细查询.
	 *
	 * @param findPmTypeIndexPage the find pm type index page
	 * @return the page< find pm type index page return>
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年7月10日
	 */
	public Page<FindPmTypeIndexPageReturn> findPmTypeIndexPage(FindPmTypeIndexPage findPmTypeIndexPage)
			throws TsfaServiceException;

	/**
	 * 方法说明：客户管理搜索：分页查询.
	 *
	 * @param findPmSeachPage the find pm seach page
	 * @return the page< find pm seach page return>
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年7月10日
	 */
	public Page<FindPmSeachPageReturn> findPmSeachPage(FindPmSeachPage findPmSeachPage) throws TsfaServiceException;

	/**
	 * 方法说明：分页查询紧急客户信息.
	 *
	 * @param findUrgentMbrPage the find urgent mbr page
	 * @return the page< find urgent mbr page return>
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年7月10日
	 */
//	public Page<FindUrgentMbrPageReturn> findUrgentMbrPage(FindUrgentMbrPage findUrgentMbrPage) throws TsfaServiceException;

	/**
	 *
	 * 方法说明：非邀约型客户-新增，主要针对没有微信的客户
	 *
	 * @param addPersonMemberBase 客户基本信息
	 * @param oldLabels           客户已对应的标签集合
	 * @param userId              当前登录ID
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 李端强 CreateDate: 2017年12月8日
	 *
	 */
//	public int addMemberForNonWxMemberNoInvite(AddPersonMemberBase addPersonMemberBase,List<PmLabelDto> oldLabels,String userId) throws TsfaServiceException;

	/**
	 * 方法说明：查找客户信息根据参数.
	 *
	 * @param param 1. code 客户Code 2. pmTypeType 客户分类类型
	 * @return the by cond
	 * @author 段志鹏 CreateDate: 2017年7月12日
	 */
	public FindPersonMemberPageReturn getByCond(Map<String, String> param);

	/**
	 * 方法说明：从勾子添加客户表信息. 2017-09-25 UPDATE BY LEOPENG :单体数据处理，不抛出错误
	 *
	 * @param info the info
	 * @throws TsfaServiceException the tsfa service exception
	 * @author rain CreateDate: 2017年7月12日
	 */
//	@Deprecated
//	public void addPersonMemberFromHook(String info) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：通过客户微信添加客户信息
	 *
	 * @param addPersonMemberByWx
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月25日
	 *
	 */
	public AddPersonMemberByWxReturn addPersonMemberByWx(AddPersonMemberByWx addPersonMemberByWx)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找该客户是和导购关联过
	 *
	 * @param findPersonMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年7月17日
	 *
	 */
	public int findCountByMemberNo(FindPersonMember findPersonMember) throws TsfaServiceException;

	/**
	 * 分页查询未联系客户
	 *
	 * @param findUnContactMember 未联系客户
	 * @return
	 * @throws TsfaServiceException
	 */
	Page<FindUnContactMemberReturn> findUnContactMemberPage(FindUnContactMember findUnContactMember)
			throws TsfaServiceException;

	/**
	 * 查询分组过的未联系客户
	 *
	 * @param findUnContactMember 未联系客户
	 * @return
	 * @throws TsfaServiceException
	 */
	FindGroupedUnContactMemberReturn findGroupedUnContactMember(FindUnContactMember findUnContactMember)
			throws TsfaServiceException;

	/**
	 * 查询导购下所有的客户数
	 * 
	 * @param merchantNo 商户编号
	 * @param memberNoGm 导购编号
	 * @return 总数
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月24日
	 */
	int findCountByMemberNoGm(String merchantNo, String memberNoGm);

	/**
	 * 查询导购未联系的客户数
	 * 
	 * @param findUnContactMember 导购编号
	 * @return 总数
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月24日
	 */
	int findUnContactMemberCount(FindUnContactMember findUnContactMember);

	/**
	 * 
	 *
	 * 方法说明：查询客户总量
	 *
	 * @param findUrgentMbrPage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月7日
	 *
	 */
	public int findPersonMemberSums(FindUrgentMbrPage findUrgentMbrPage);

	/**
	 * 根据商户编号统计性别
	 * 
	 * @return
	 */
	List<FindPersonMemberSexStatisticsReturn> selectSexStatisticsByShopNo();

	/**
	 * 根据商户编号统计年龄
	 * 
	 * @return
	 */
	List<FindPersonMemberAgeStatisticsReturn> selectAgeStatisticsByShopNo(String beginDate, String endDate);

	/**
	 * 根据商户编号统计兴趣
	 * 
	 * @return
	 */
	List<FindPersonMemberInterestStatisticsReturn> selectInterestStatisticsByShopNo();

	/**
	 * 
	 *
	 * 方法说明：处理交叉客户
	 *
	 * @param doRepeatMemberDto
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月14日
	 *
	 */
	void doRepeatMember(DoRepeatMemberDto doRepeatMemberDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找客户list
	 *
	 * @param doRepeatMemberDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月14日
	 *
	 */
	List<FindPersonMemberReturn> findList(DoRepeatMemberDto doRepeatMemberDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查询客户列表
	 *
	 * @param findPersonMemberPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年8月15日
	 *
	 */
	List<FindPersonMemberPageReturn> findPersonMemberList(FindPersonMemberPage findPersonMemberPage)
			throws TsfaServiceException;

	/**
	 * 方法说明：查询客户列表对外接口调用
	 * 
	 * @param findPersonMemberPage
	 * @return
	 * @throws TsfaServiceException
	 * @author 李端强 CreateDate: 2018年1月8日16:57:09
	 */
	List<FindPersonMemberPageReturn> findPersonMemberListForApi(FindPersonMemberPage findPersonMemberPage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：是否到店
	 *
	 * @param shopLongitude
	 * @param shopLatitude
	 * @param pmLongitude
	 * @param pmLatitude
	 * @param distance
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年8月15日
	 *
	 */

//	boolean isToShop(String shopLongitude, String shopLatitude, String pmLongitude, String pmLatitude, String distance);

	/**
	 * 
	 *
	 * 方法说明：管理工作
	 *
	 * @param findTodayManageShop
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月16日
	 *
	 */
	List<FindTodayManageShopReturn> todayManageShopNew(FindTodayManageShop findTodayManageShop)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：资料完成度
	 *
	 * @param findPersonMemberBaseReturn
	 * @param findPersonMemberReturn
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月17日
	 *
	 */
	void computeRate(FindPersonMemberBaseReturn findPersonMemberBaseReturn) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据导购号和时间查询新增客户
	 *
	 * @param memberNo 导购号
	 * @param date     时间
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年8月18日
	 *
	 */
	public long findCountPmAddByGmDay(String memberNo, Date date);

	/**
	 * 
	 *
	 * 方法说明：客户分组数量最大一条
	 *
	 * @param findPersonMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月18日
	 *
	 */
	public FindPersonMemberReturnList findPersonMemberTypeNum(FindPersonMember findPersonMember)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：统计客户性别
	 *
	 * @param findPersonMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年8月18日
	 *
	 */
	public List<FindUrgentMbrPageReturn> findPersonMemberSexCount(FindPersonMember findPersonMember)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据导购查客户
	 *
	 * @param findPersonMemberByGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 刘培 CreateDate: 2017年8月19日
	 *
	 */
	public List<FindPersonMemberReturn> findPersonMemberByGm(FindPersonMember personMember);

	/**
	 * 
	 *
	 * 方法说明：查找新增客户数量
	 *
	 * @param findNewPmCountDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月18日
	 *
	 */
	int findNewPmCount(FindNewPmCountDto findNewPmCountDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据导购号和客户类型查询客户数量
	 *
	 * @param findPmType
	 * @return int
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年8月19日
	 *
	 */
	public int findCountPmByType(FindPmType findPmType);

	/**
	 * 
	 *
	 * 方法说明：查询客户分类类型数量排行()
	 *
	 * @param findPersonMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月21日
	 *
	 */
	public List<FindPersonMemberReturnList> findPersonMemberTypeList(FindPersonMember findPersonMember);

	/**
	 * 
	 *
	 * 方法说明：统计客户分类客户总数
	 *
	 * @param findPersonMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月21日
	 *
	 */
	public int findPersonMemberTypeCount(FindPersonMember findPersonMember);

	/**
	 * 
	 * 方法说明：根据导购编号和商户号查询客户信息
	 *
	 * @param findMemberRecord
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月5日
	 *
	 */
	public List<FindMemberInfoReturn> findMemberRecord(FindMemberRecord findMemberRecord);

	/**
	 * 
	 * 方法说明：根据时间分组新增客户
	 *
	 * @param findCountPersonMember
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月6日
	 *
	 */
	public List<CountPersonMemberReturn> findGroupCountByDay(FindCountPersonMember findCountPersonMember);

	/**
	 * 
	 *
	 * 方法说明：查询客户数
	 *
	 * @param findPersonMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月9日
	 *
	 */
	public int findPersonMemberCont(FindPersonMember findPersonMember) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：商户运营报表商户客户数
	 *
	 * @param map
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月27日
	 *
	 */
	public List<Map<String, Object>> findShopPmNum(Map<String, Object> map);

	/**
	 * 
	 *
	 * 方法说明：商户运营报表商户意向客户数
	 *
	 * @param map
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月28日
	 *
	 */
	public List<Map<String, Object>> findCountAddIntention(Map<String, Object> map);

	/**
	 * 
	 *
	 * 方法说明：商户运营报表商户非意向客户数
	 *
	 * @param map
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月28日
	 *
	 */
	public List<Map<String, Object>> findCountAddOther(Map<String, Object> baseParam);

	/**
	 * 
	 *
	 * 方法说明：商户运营报表商户成单客户数
	 *
	 * @param map
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月28日
	 *
	 */
	public List<Map<String, Object>> findCountPmOrder(Map<String, Object> baseParam);

	/**
	 * 
	 *
	 * 方法说明：商户运营报表商户暂停客户数
	 *
	 * @param map
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月28日
	 *
	 */
	public List<Map<String, Object>> finCountAddPmAbandon(Map<String, Object> baseParam);

	/**
	 * 
	 *
	 * 方法说明：商户运营报表商户未分组客户数
	 *
	 * @param map
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月28日
	 *
	 */
	public List<Map<String, Object>> findcountAddPmUngroup(Map<String, Object> baseParam);

	/**
	 * 
	 *
	 * 方法说明：客户列表分页查询
	 *
	 * @param findPersonMemberPage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年10月25日
	 *
	 */
	public Page<FindPersonMemberPageReturn> queryPersonMemberPage(FindPersonMemberPage findPersonMemberPage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找客户分类
	 *
	 * @param findPersonMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年10月30日
	 *
	 */
	FindPersonMemberReturnList queryPersonMemberPmType(FindPersonMember findPersonMember) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找导购是否绑定有微信客户
	 *
	 * @param findPersonMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年11月30日
	 *
	 */
	public List<FindPersonMemberReturn> findPersonMemberByNoWx(FindPersonMember findPersonMember)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：客户管理分组，非邀约版
	 *
	 * @param findPmTypeIndexPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2017年12月16日
	 *
	 */
	Page<FindPmTypeIndexPageReturn> findPmTypeIndexPageHc(FindPmTypeIndexPage findPmTypeIndexPage)
			throws TsfaServiceException;

	/**
	 *
	 * 方法说明：根据商户编号查询所有的意向客户的ID集合
	 * 
	 * @param merchantNo 商户编号
	 * @return 客户的ID集合
	 * @author 李端强 CreateDate: 2017年12月16日
	 *
	 */
	public List<String> findIntentionMemberNo(String merchantNo);

	/**
	 * 
	 *
	 * 方法说明：修改客户手机号
	 *
	 * @param editPersonMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月14日
	 *
	 */
	public UpdatePersonMemberReturn updatePersonMemberMobile(EditPersonMember editPersonMember)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改客户名称
	 *
	 * @param editPersonMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月14日
	 *
	 */
	public UpdatePersonMemberReturn updatePersonMemberName(EditPersonMember editPersonMember)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找web客户信息
	 *
	 * @param findImIndex
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月16日
	 *
	 */
	public List<FindImIndexPageReturn> findImIndexList(FindImIndexPage findImIndex);

	/**
	 * 
	 *
	 * 方法说明：查找web客户信息条数
	 *
	 * @param findImIndex
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月16日
	 *
	 */
	public Long findImIndexListCount(FindImIndexPage findImIndex);

	/**
	 * 
	 *
	 * 方法说明：查询导购的客户总数
	 *
	 * @param findPmTypeIndexPage
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2017年12月22日
	 *
	 */
	Integer findAllCustomerCount(FindPmTypeIndexPage findPmTypeIndexPage);

	/**
	 * 方法说明：根据客户编号和导购编号查詢會員信息
	 * 
	 * @param findPersonMember
	 * @return
	 * @author 李端强 CreateDate: 2018年1月18日
	 */
	public FindPersonMemberReturn findPersonMemberByMemberNoAndGM(FindPersonMember findPersonMember);

	/**
	 * 根据微信号与终端号查询客户信息
	 * 
	 * @param noWx
	 * @param shopNo
	 * @return
	 */
	public FindPersonMemberReturn findPersonMemberByNoWxAndShopWx(String noWx, String shopWx);

	/**
	 *
	 * 方法说明：根据商户编号指定标签code查询客户基本信息列表
	 * 
	 * @param                   paramMap=merchantNo商户编号,pmLabelCode标签code
	 * @param merchantNo商户编号
	 * @param pmLabelCode标签code
	 * @return
	 * @author 李端强 CreateDate: 2018年1月9日
	 */
	public List<FindPersonMemberPageReturn> findPmbListByLabelCode(Map<String, Object> paramMap);

	/**
	 * 电商下单创建客户
	 * 
	 * @param mecMemberDto
	 * @return
	 * @throws TsfaServiceException
	 */
	MecMemberNoReturn addPersonAllByMec(MecMemberDto mecMemberDto) throws TsfaServiceException;

	/**
	 * 跟进终端编号查找客户数量
	 * 
	 * @param shopNo
	 * @return
	 */
	int findListByShopNo(String shopNo, String memberNo);

	/**
	 * 根据店铺编号和客户编号查找导购
	 * 
	 * @param shopNo
	 * @param memberNo
	 * @return
	 * @throws TsfaServiceException
	 */
	Page<FindGmDistantPageReturn> findByMemberNoAndShopNo(String shopNo, String memberNo) throws TsfaServiceException;

	/**
	 * 根据店铺编号和导购编号查找导购
	 * 
	 * @param shopNo
	 * @param memberNo
	 * @return
	 * @throws TsfaServiceException
	 */
	List<FindGmDistantPageReturn> findByGuidNoAndShopNo(String shopNo, String guidMemberNo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据客户微信号查询导购微信号
	 *
	 * @param userName
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2018年1月2日
	 *
	 */
	public List<String> findPersonMemberByWx(String noWx);

	/**
	 * 
	 *
	 * 方法说明：20170108非邀约版分支合并到主干，复制findPmTypeIndexPage的一个方法
	 *
	 * @param findPmTypeIndexPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年1月8日
	 *
	 */
	Page<FindPmTypeIndexPageReturn> findPmTypeIndexPageLoho(FindPmTypeIndexPage findPmTypeIndexPage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：导购助手编辑客户资料
	 *
	 * @param editPersonMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年1月24日
	 *
	 */
	UpdatePersonMemberReturn editPersonMemberGMA(EditPersonMember editPersonMember) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：群发优惠券，查询客户列表（分页和不分页）
	 *
	 * @param findImIndex
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月24日
	 *
	 */
	List<FindImIndexPageReturn> findPmListByShopTerminals(FindImIndexPage findImIndex);

	Long findPmListByShopTerminalsCount(FindImIndexPage findImIndex);

	/**
	 * 
	 *
	 * 方法说明：微软CRM获取新增/更新的意向客户信息 文档参考：（敏华crm与杨恩接口文档.docx）
	 * 
	 * @param parmMap getType = 获取方式（ALL:全量 ADD:增量） lastTime = 最后更新时间（yyyy-MM-dd
	 *                HH:mm:ss ） merchantNo = 商户编号
	 * @return customername 客户姓名 gmphone 导购电话 gmname 导购姓名 shopid 终端编码 sex 性别
	 *         customerphone 客户手机号 provinceid 省份编码 cityid 城市编码 regionid 区县编码 address
	 *         地址 houses 楼盘 spruceup 装修进度 urgencypm 紧急客户 bomname 所需产品
	 *         contactdatelast 最近联系时间 clientspecial 客户特质 consumefrequency 消费频率(天)
	 *         exptime 到店时间 updateDate 记录更新时间
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2018年2月28日
	 *
	 */
	List<Map<String, Object>> getCustomerInfo(Map<String, Object> paramMap) throws TsfaServiceException;

	Long findForecastNameIndexListCount(FindImIndexPage findImIndex);

	public FindPersonMemberReturnList findPersonMemberType(FindPersonMember findPersonMember)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找客户分类，不包含（交叉，紧急）
	 *
	 * @param findPersonMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年4月16日
	 *
	 */
	FindPersonMemberReturnList queryPersonMemberPmTypeExceptRepeatAndUrgency(FindPersonMember findPersonMember)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：商户下按终端统计客户情况
	 *
	 * @param merchantNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年6月13日
	 *
	 */
	List<PersonMemberStsGroupByShop> memberStsGroupByShop(String merchantNo, Date beginTime, Date endTime);

	/**
	 *
	 *
	 * 方法说明：根据MemberNo和MerchantNo查询客户
	 *
	 * @param findPersonMember
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018/5/3
	 */
	List<FindPersonMemberReturn> findPersonMemberByMemberNoAndMerchantNo(FindPersonMember findPersonMember);

	/**
	 * 
	 *
	 * 方法说明：查询未联系客户列表：不满足聊天记录的客户
	 *
	 * @param findUnchatMemberPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月17日
	 *
	 */
	public List<FindUnchatMemberPageReturn> findUnchatMemberList(FindUnchatMemberPage findUnchatMemberPage);

	/**
	 * 
	 *
	 * 方法说明：根据导购编号统计相关客户数量
	 *
	 * @param findWxPmByGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年8月7日
	 *
	 */
	Long findWxPmCountByGm(FindWxPmByGm findWxPmByGm) throws TsfaServiceException;

	List<FindPersonMemberReturn> findWxPmByGm(FindWxPmByGm findWxPmByGm);

	public List<FindPersonMemberReturn> findPersonMember(String noWxGm, String gmNo);

	/**
	 * 修改客户分类
	 * 
	 * @param updatePersonMember
	 * @return
	 * @throws TsfaServiceException
	 */
	UpdatePersonMemberReturn updatePmType(UpdatePersonMember updatePersonMember) throws TsfaServiceException;

	/**
	 * 批量修改客户分类
	 * 
	 * @param codePms
	 * @param pmTypeCode
	 * @param pmTypeName
	 * @return
	 */
	UpdatePersonMemberReturn changePmTypeBatch(String[] codePms, String pmTypeCode, String pmTypeName);

	/**
	 * /** 转移成功通知导购
	 * 
	 * @param memberNoGm 导购编号
	 * @param memberNo   客户编号
	 * @param state      状态值：1新增 2移除
	 * @param noWxGm     终端微信
	 */
	public void sendTransSuccess(String memberNoGm, String memberNo, String state, String noWxGm, String noWx);

	/**
	 * 根据手机号及终端微信号查用户
	 * 
	 * @param member
	 * @return
	 * @author lhy 2019.02.18
	 */
	public FindPersonMemberReturn findPersonMemberByMoblieAndShopWx(FindPersonMember member);

	String findPersonMemberCodeByMemberNo(String memberNo);

	/**
	 * @Desc
	 * @param updatePersonMember
	 * @return void
	 * @author 贾光朝
	 * @createDate 2019年5月7日下午3:52:18
	 */
	void delete(UpdatePersonMember updatePersonMember);

	/**
	 * 查找客户信息(查导购指定手机号的客户）。乐莎莎使用.
	 * 
	 * @param personMemberDto
	 * @return
	 * @throws TsfaServiceException
	 * @author lhy 2019.05.13
	 */
	public List<PersonMemberDto> findPersonMemberByMoblies(PersonMemberDto personMemberDto) throws TsfaServiceException;

	/**
	 * @Desc
	 * @param mapPhone
	 * @return
	 * @return int
	 * @author 贾光朝
	 * @createDate 2019年6月6日上午10:12:26
	 */
	int findTotalMemberPhone(Map mapPhone) throws TsfaServiceException;

	/**
	 * @Desc
	 * @param map
	 * @return
	 * @return int
	 * @author 贾光朝
	 * @createDate 2019年6月6日下午4:54:31
	 */
	int findTotalMember(Map map) throws TsfaServiceException;

	/**
	 * 
	 * @Title: 添加标签 @Description: 主要用于，同步通讯录时，同步标签用 @param: @param
	 * labelsName @param: @param merchantNo @param: @param memberNo @param: @param
	 * shopWx @param: @param memberNoGm @param: @param memberNameGm @param: @throws
	 * TsfaServiceException @return: void @throws
	 */
	void addLabels(String labelsName, String merchantNo, String memberNo, String shopWx, String memberNoGm,
			String memberNameGm) throws TsfaServiceException;
}
