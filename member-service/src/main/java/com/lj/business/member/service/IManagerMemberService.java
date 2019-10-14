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
import com.lj.business.member.dto.AddManagerMember;
import com.lj.business.member.dto.AddManagerMemberDto;
import com.lj.business.member.dto.AddManagerMemberReturn;
import com.lj.business.member.dto.DelManagerMember;
import com.lj.business.member.dto.DelManagerMemberReturn;
import com.lj.business.member.dto.FindManagerMember;
import com.lj.business.member.dto.FindManagerMemberPage;
import com.lj.business.member.dto.FindManagerMemberPageReturn;
import com.lj.business.member.dto.FindManagerMemberReturn;
import com.lj.business.member.dto.FindManagersDto;
import com.lj.business.member.dto.FindManagersReturnDto;
import com.lj.business.member.dto.ManagerMemberDto;
import com.lj.business.member.dto.ManagerMemberReturnDto;
import com.lj.business.member.dto.UpdateManagerMember;
import com.lj.business.member.dto.UpdateManagerMemberDto;
import com.lj.business.member.dto.UpdateManagerMemberReturn;
import com.lj.business.member.dto.UpdateManagerOrGuidPwdDto;
import com.lj.business.member.dto.UpdatePwdDto;

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
public interface IManagerMemberService {

	/**
	 * 方法说明：添加商户表信息.
	 *
	 * @param addManagerMember
	 *            the add manager member
	 * @return the adds the manager member return
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public AddManagerMemberReturn addManagerMember(AddManagerMember addManagerMember) throws TsfaServiceException;

	/**
	 * 方法说明：查找商户表信息.
	 *
	 * @param findManagerMember
	 *            the find manager member
	 * @return the find manager member return
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public FindManagerMemberReturn findManagerMember(FindManagerMember findManagerMember) throws TsfaServiceException;

	/**
	 * 方法说明：删除商户表信息.
	 *
	 * @param delManagerMember
	 *            the del manager member
	 * @return the del manager member return
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public DelManagerMemberReturn delManagerMember(DelManagerMember delManagerMember) throws TsfaServiceException;

	/**
	 * 方法说明：修改商户表信息.
	 *
	 * @param updateManagerMember
	 *            the update manager member
	 * @return the update manager member return
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public UpdateManagerMemberReturn updateManagerMember(UpdateManagerMember updateManagerMember) throws TsfaServiceException;

	/**
	 * 方法说明：查找商户表信息.
	 *
	 * @param findManagerMemberPage
	 *            the find manager member page
	 * @return the page< find manager member page return>
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public Page<FindManagerMemberPageReturn> findManagerMemberPage(FindManagerMemberPage findManagerMemberPage) throws TsfaServiceException;

	/**
	 * 方法说明：按条件查询管理人员.
	 *
	 * @param findManagerMemberPage
	 *            the find manager member page
	 * @return the list< find manager member page return>
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 段志鹏 CreateDate: 2017年6月24日
	 */
	public List<FindManagerMemberPageReturn> findManagerMembers(FindManagerMemberPage findManagerMemberPage) throws TsfaServiceException;

	/**
	 * 方法说明：查找个人中心店长信息.
	 *
	 * @param managerMemberDto
	 *            the manager member dto
	 * @return the manager member return dto
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 邹磊 CreateDate: 2017年7月11日
	 */
	public ManagerMemberReturnDto findManager(ManagerMemberDto managerMemberDto) throws TsfaServiceException;

	/**
	 * 方法说明：更新个人中心店长信息.
	 *
	 * @param updateManagerMemberDto
	 *            the update manager member dto
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 邹磊 CreateDate: 2017年7月11日
	 */
	public void updateManager(UpdateManagerMemberDto updateManagerMemberDto) throws TsfaServiceException;

	/**
	 * 方法说明：添加个人中心店长信息.
	 *
	 * @param addManagerMemberDto
	 *            the add manager member dto
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 邹磊 CreateDate: 2017年7月11日
	 */
	public void addManager(AddManagerMemberDto addManagerMemberDto) throws TsfaServiceException;

	/**
	 * 方法说明：查找店长信息列表(通讯录).
	 *
	 * @param findManagerMemberPage
	 *            the find manager member page
	 * @return the list< manager member return dto>
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 邹磊 CreateDate: 2017年7月13日
	 */
	public List<FindManagersReturnDto> findManagers(FindManagersDto findManagersDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：店长数据导出
	 *
	 * @param findManagerMemberPage
	 * @return
	 *
	 * @author 刘培 CreateDate: 2017年8月09日
	 *
	 */
	public List<FindManagerMemberPageReturn> findManagerMemberExport(FindManagerMemberPage findManagerMemberPage);

	/**
	 * 
	 *
	 * 方法说明：更新密码
	 *
	 * @param updateManagerOrGuidPwdDto
	 *
	 * @author 邹磊 CreateDate: 2017年7月26日
	 * @deprecated
	 */
	public void updateManagerForPwd(UpdateManagerOrGuidPwdDto updateManagerOrGuidPwdDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：统计管理人员数量
	 *
	 * @param findManagerMemberPage
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月26日
	 *
	 */
	int findManagerMemberCount(FindManagerMemberPage findManagerMemberPage);

	/**
	 * 
	 *
	 * 方法说明：修改管理人员密码
	 *
	 * @param updatePwdDto
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年8月9日
	 *
	 */
	public void updatePwd(UpdatePwdDto updatePwdDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据终端查询店长信息
	 *
	 * @param shopNo
	 *            终端编号
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年8月22日
	 *
	 */
	public List<ManagerMemberReturnDto> findManagerMemberByShop(String shopNo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：获取该商户总客户数
	 *
	 * @param merchantNo
	 *            商户编号
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年9月01日
	 *
	 */
	public int findCountManagerMember(String merchantNo);

	/**
	 * 
	 *
	 * 方法说明：获取该商户区域经理年龄分段
	 *
	 * @param parmMap
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年9月01日
	 *
	 */
	public Map<String, Object> countByCondition(Map<String, Object> parmMap);

	/**
	 *
	 * 方法说明：根据手机查询管理信息
	 *
	 * @param managerMemberDto
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月9日
	 *
	 */
	public ManagerMemberReturnDto findManagerMemberByMobile(ManagerMemberDto managerMemberDto);
	/**
	 * 
	 *
	 * 方法说明：查询店长编号
	 *
	 * @param findManagerMemberPage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月11日
	 *
	 */
	public List<FindManagerMemberPageReturn> findMemberNoShop(FindManagerMemberPage findManagerMemberPage);
	
	/**
	 * 
	 *
	 * 方法说明：管理人员降职
	 * 1、SHOP店长降职为店员：删除店长信息记录
	 * 2、AREA_MAN经理降职为店长：修改会员类型为SHOP、指定所属终端、初始密码
	 * 3、BOSS老板（总监）降职为经理：修改会员类型为AREA_MAN
	 *
	 * @param managerDemotion
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月4日
	 * @return 
	 *
	 */
//	public ManagerDemotionReturn memberDemotion(ManagerDemotion managerDemotion);
	
	/**
	 * 
	 *
	 * 方法说明：管理人员离职
	 * 1、SHOP店长离职：删除店长信息记录
	 * 2、AREA_MAN经理离职：删除经理信息记录
	 * 3、BOSS老板（总监）离职：删除老板（总监）信息记录
	 *
	 * @param managerDemotion
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月4日
	 * @return 
	 *
	 */
//	public ManagerDimissionReturn memberDimission(ManagerDimission managerDimission);
	
	/**
	 * 
	 *
	 * 方法说明：管理人员升职
	 * 1、SHOP店长升职为经理：修改会员类型为AREA_MAN
	 * 2、AREA_MAN经理升职为老板（总监）：修改会员类型为BOSS
	 *
	 * @param managerPromotion
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月4日
	 *
	 */
//	public ManagerPromotionReturn memberPromotion(ManagerPromotion managerPromotion);
	
	/**
	 * 
	 *
	 * 方法说明：导购升职
	 *
	 * @param addManagerMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年11月6日
	 *
	 */
	public AddManagerMemberReturn guidPromotion(AddManagerMember addManagerMember)throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：获取同微信号不同终端下店员信息
	 *
	 * @param findManagerMemberPage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年11月29日
	 *
	 */
	public List<FindManagerMemberPageReturn> findManagerMemberNoWx(FindManagerMemberPage findManagerMemberPage)throws TsfaServiceException;
}
