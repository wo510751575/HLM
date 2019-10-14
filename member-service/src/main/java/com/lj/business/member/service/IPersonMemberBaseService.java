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
import com.lj.business.member.domain.PersonMemberBase;
import com.lj.business.member.dto.AddPersonMemberBase;
import com.lj.business.member.dto.AddPersonMemberBaseReturn;
import com.lj.business.member.dto.DelPersonMemberBase;
import com.lj.business.member.dto.DelPersonMemberBaseReturn;
import com.lj.business.member.dto.EditPersonMember;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseList;
import com.lj.business.member.dto.FindPersonMemberBasePage;
import com.lj.business.member.dto.FindPersonMemberBasePageReturn;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindPersonMemberBaseReturnList;
import com.lj.business.member.dto.FindPersonMemberName;
import com.lj.business.member.dto.UpdatePersonMemberBase;
import com.lj.business.member.dto.UpdatePersonMemberBaseRatioClientInfoDto;
import com.lj.business.member.dto.UpdatePersonMemberBaseReturn;


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
public interface IPersonMemberBaseService {
	
	/**
	 * 方法说明：用户设置取消置顶
	 * @param personMemberBase
	 */
	public void updateCancleSetUpUser(PersonMemberBase personMemberBase);

	/**
	 * 方法说明： 设置用户置顶
	 * @param personMemberBase
	 */
	public void updateSetUpUser(PersonMemberBase personMemberBase);
	
	/**
	 * 方法说明：添加客户基础信息.
	 *
	 * @param addPersonMemberBase the add person member base
	 * @return the adds the person member base return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public AddPersonMemberBaseReturn addPersonMemberBase(AddPersonMemberBase addPersonMemberBase) throws TsfaServiceException;
	
	/**
	 * 方法说明：查找客户基础信息.
	 *
	 * @param findPersonMemberBase the find person member base
	 * @return the find person member base return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public FindPersonMemberBaseReturn findPersonMemberBase(FindPersonMemberBase findPersonMemberBase) throws TsfaServiceException;
	
	
	/**
	 * 方法说明：删除客户基础信息.
	 *
	 * @param delPersonMemberBase the del person member base
	 * @return the del person member base return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public DelPersonMemberBaseReturn delPersonMemberBase(DelPersonMemberBase delPersonMemberBase) throws TsfaServiceException;

	/**
	 * 方法说明：修改客户基础信息.
	 *
	 * @param updatePersonMemberBase the update person member base
	 * @return the update person member base return
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public UpdatePersonMemberBaseReturn updatePersonMemberBase(UpdatePersonMemberBase updatePersonMemberBase)throws TsfaServiceException;

	/**
	 * 方法说明：查找客户基础信息.
	 *
	 * @param findPersonMemberBasePage the find person member base page
	 * @return the page< find person member base page return>
	 * @throws TsfaServiceException the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public Page<FindPersonMemberBasePageReturn> findPersonMemberBasePage(FindPersonMemberBasePage findPersonMemberBasePage) throws TsfaServiceException;
	
	/**
	 * 方法说明：根据手机号查找客户基础信息.
	 *
	 * @param mobile the mobile
	 * @return the find person member base return
	 * @author 段志鹏 CreateDate: 2017年7月13日
	 */
	FindPersonMemberBaseReturn findByMobile(FindPersonMemberBase findPersonMemberBase);
	
    /**
     * 
     *
     * 方法说明：根据编码获取客户姓名
     *
     * @param codeList
     * @return
     *
     * @author 武鹏飞 CreateDate: 2017年7月21日
     *
     */
    List<FindPersonMemberName> findByCodeList(List<String> codeList);
    /**
     * 
     *
     * 方法说明：OMS根据客户编号查询
     *
     * @param findPersonMemberBaseList
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月22日
     *
     */
    public  FindPersonMemberBaseReturnList findPersonMemberBaseList(FindPersonMemberBaseList findPersonMemberBaseList)throws TsfaServiceException;
    /***
     * 
     *
     * 方法说明：根据省/区域查询客户数量
     *
     * @param findPersonMemberBaseList
     * @return
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年8月7日
     *
     */
    public  FindPersonMemberBaseReturnList findPersonMemberBaseCounts(FindPersonMemberBaseList findPersonMemberBaseList)throws TsfaServiceException;
    
    /**
     * 
     *
     * 方法说明：修改完成度
     *
     * @param dto
     * @throws TsfaServiceException
     *
     * @author 冯辉 CreateDate: 2017年8月17日
     *
     */
    public void updatePersonMemberBaseRatioClientInfo(UpdatePersonMemberBaseRatioClientInfoDto dto) throws TsfaServiceException;
    
    /**
     * 
     *
     * 方法说明：查询性别分组客户数最多的客户的性别
     *
     * @return
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年8月18日
     *
     */
    public FindPersonMemberBaseReturnList findPersonMemberMax()throws TsfaServiceException;
    /**
     * 
     *
     * 方法说明：查询区域客户数
     *
     * @return
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年8月18日
     *
     */
    public  List<FindPersonMemberBaseList> findPersonMemberBaseMemberCount(FindPersonMemberBase findPersonMemberBase)throws TsfaServiceException;
    /**
     * 
     *
     * 方法说明：新增客户数查询
     *
     * @param findPersonMemberBaseList
     * @return
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年8月28日
     *
     */
    public int findPersonMemberBaseNumAdd(FindPersonMemberBaseList findPersonMemberBaseList)throws TsfaServiceException;
     /**
      * 
      *
      * 方法说明：昵称备注_本地
      *
      * @param findPersonMemberBase
      * @return
      *
      * @author 罗书明 CreateDate: 2017年9月5日
      *
      */
    public FindPersonMemberBaseReturn findPersonMemberBaseParams(FindPersonMemberBase findPersonMemberBase) throws TsfaServiceException ;

    /**
     * 
     *
     * 方法说明：查询客户，根据手机号，客户编号不为该编号时
     *
     * @param findPersonMemberBase
     * @return
     *
     * @author 梅宏博  CreateDate: 2017年10月16日
     *
     */
	public PersonMemberBase findMobileAndCode(
			FindPersonMemberBase findPersonMemberBase);
	/**
	 * 
	 *
	 * 方法说明：查询客户总数与客户分类数量
	 *
	 * @param personMemberBaseList
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年10月17日
	 *
	 */
    public  FindPersonMemberBaseReturnList findPersonMemberBaseNums(FindPersonMemberBaseList personMemberBaseList)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改客户手机号
	 *
	 * @param updatePersonMemberBase
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月2日
	 *
	 */
	public UpdatePersonMemberBaseReturn updatePersonMemberMobile(UpdatePersonMemberBase updatePersonMemberBase)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：根据客户微信号修改客户微信基本信息
	 *
	 * @param updatePersonMemberBase
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月14日
	 *
	 */
	public UpdatePersonMemberBaseReturn updatePersonMemberWxInfoByNoWx(UpdatePersonMemberBase updatePersonMemberBase)throws TsfaServiceException;
	
	/**
	 *
	 * 方法说明：根据客户手机号查询微信信息mobile=15274949965
	 * @param mobile=15274949965 
	 * @return memberName,mobile,noWx
	 *
	 * @author 李端强 CreateDate: 2017年12月13日
	 *
	 */
	public Map<String, Object> getBaseInfoByMobile(Map<String, Object> map);
	
	/**
	 * 方法说明：根据商户的MerchantNo初始化PMB的wxOpenId
	 * @param MerchantNo
	 * @return
	 * @author 李端强 CreateDate: 2018年1月10日
	 */
	public boolean initWxOpenIdByMerchantNo(String merchantNo);
	
	/**
	 * 
	 *
	 * 方法说明：根据微信号或微信别名查询客户基本信息
	 * 1、此方法是在不确定微信号正确的情况下可以按微信别名（微信不为空时唯一）去查询（如真实微信是l-d-q123456，返回时为ldq123456）
	 * 2、如果确定了微信号正确，可以直接只按微信号去查询
	 * 
	 * @param noWx
	 * @param alias
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月13日
	 *
	 */
	public FindPersonMemberBaseReturn findMemberBaseByNoWxOrAlias(String noWx, String alias);

	/**
	 * 
	 *@Desc 查询是否置顶
	 *@param memberNo
	 *@return
	 *@return String
	 *@author 贾光朝
	 *@createDate 2019年4月11日下午2:40:57
	 */
	public String selectSetUp(String memberNo);
    
	/**
	 * 查詢多個客户的微信信息
	 * @param personMemberBase
	 * @return
	 * @throws TsfaServiceException
	 * @author lhy 2019.05.14
	 */
	public List<PersonMemberBase> findMemberBaseByMemberNos(FindPersonMemberBase personMemberBase) throws TsfaServiceException;
	
	/**
	 *@Desc 
	 *@param editPersonMember
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月5日下午7:21:09
	 */
	public PersonMemberBase checkMobile(EditPersonMember editPersonMember)throws TsfaServiceException;
}
