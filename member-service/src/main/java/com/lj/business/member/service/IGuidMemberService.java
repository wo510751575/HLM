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
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.dto.AddGuidMember;
import com.lj.business.member.dto.AddGuidMemberDto;
import com.lj.business.member.dto.AddGuidMemberReturn;
import com.lj.business.member.dto.DelGuidMember;
import com.lj.business.member.dto.DelGuidMemberReturn;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindShopGmDto;
import com.lj.business.member.dto.FindShopGmDtoReturn;
import com.lj.business.member.dto.FindShopGmReturn;
import com.lj.business.member.dto.GuidInfoShop;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.UpdateGuidMember;
import com.lj.business.member.dto.UpdateGuidMemberReturn;
import com.lj.business.member.dto.UpdatePwdDto;
import com.lj.business.member.dto.addfriends.FindAllotGuidMember;
import com.lj.business.member.dto.addfriends.FindAllotGuidMemberReturn;
import com.lj.business.member.dto.addfriends.FindOtherAllotGuidMember;
import com.lj.business.member.dto.im.FindGuidByShopAndMember;
import com.lj.business.member.dto.im.FindGuidByShopAndMemberReturn;

/**
 * 类说明：接口类 导购
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 *         CreateDate: 2017-06-14
 */
public interface IGuidMemberService {

	/**
	 * 方法说明：添加导购表信息.
	 *
	 * @param addGuidMember
	 *            the add guid member
	 * @return the adds the guid member return
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public AddGuidMemberReturn addGuidMember(AddGuidMember addGuidMember) throws TsfaServiceException;

	public List<FindShopGmReturn> findShopGmPy(FindShopGmDto findShopGmDto) throws TsfaServiceException;
	
	/**
	 * 方法说明：查找导购表信息.
	 *
	 * @param findGuidMember
	 *            the find guid member
	 * @return the find guid member return
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public FindGuidMemberReturn findGuidMember(FindGuidMember findGuidMember) throws TsfaServiceException;

	/**
	 * 方法说明：删除导购表信息.
	 *
	 * @param delGuidMember
	 *            the del guid member
	 * @return the del guid member return
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public DelGuidMemberReturn delGuidMember(DelGuidMember delGuidMember) throws TsfaServiceException;

	/**
	 * 方法说明：修改导购表信息.
	 *
	 * @param updateGuidMember
	 *            the update guid member
	 * @return the update guid member return
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public UpdateGuidMemberReturn updateGuidMember(UpdateGuidMember updateGuidMember) throws TsfaServiceException;

	/**
	 * 方法说明：查找导购表信息.
	 *
	 * @param findGuidMemberPage
	 *            the find guid member page
	 * @return the page< find guid member page return>
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭阳 CreateDate: 2017年6月9日
	 */
	public Page<FindGuidMemberPageReturn> findGuidMemberPage(FindGuidMemberPage findGuidMemberPage) throws TsfaServiceException;

	/**
	 * 方法说明：导出店员Excel.
	 *
	 * @param findGuidMemberPage
	 *            the find guid member page
	 * @return the page< find guid member page return>
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 刘培 CreateDate: 2017年08月10日
	 */
	public List<FindGuidMemberPageReturn> findGuidMemberExport(FindGuidMemberPage findGuidMemberPage) throws TsfaServiceException;

	/**
	 * 方法说明：查询导购列表.
	 *
	 * @param findGuidMemberPage
	 *            the find guid member page
	 * @return the list< find guid member page return>
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 段志鹏 CreateDate: 2017年6月23日
	 */
	public List<FindGuidMemberPageReturn> findGuidMembers(FindGuidMemberPage findGuidMemberPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查询导购列表.
	 *
	 * @param findGuidMemberPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月22日
	 *
	 */
	public List<FindGuidMemberPageReturn> findGuidMembersNoSelf(FindGuidMemberPage findGuidMemberPage) throws TsfaServiceException;

	/**
	 * 方法说明：查找导购信息(个人中心).
	 *
	 * @param findGuidMemberDto
	 *            the find guid member dto
	 * @return the guid member return dto
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 邹磊 CreateDate: 2017年7月11日
	 */
	public GuidMemberReturnDto findGuid(FindGuidMemberDto findGuidMemberDto) throws TsfaServiceException;

	/**
	 * 方法说明：添加个人中心导购信息.
	 *
	 * @param addGuidMemberDto
	 *            the add guid member dto
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 邹磊 CreateDate: 2017年7月11日
	 */
	public void addGuidMember(AddGuidMemberDto addGuidMemberDto) throws TsfaServiceException;

	/***
	 * 
	 *
	 * 方法说明：按主键查询信息
	 *
	 * @param updateGuidMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月18日
	 *
	 */
	public GuidMemberReturnDto findGuidMemberByCode(UpdateGuidMember updateGuidMember);

	/**
	 * 
	 *
	 * 方法说明：根据商户号和分店编号查找导购列表
	 *
	 * @param findGuidMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年7月20日
	 *
	 */
	public List<GuidMemberReturnDto> findGuidMemberList(FindGuidMemberDto findGuidMemberDto) throws TsfaServiceException;

	/**
	 * 方法说明：分页获取有效的导购
	 *
	 * @param currentPage
	 *            当前页
	 * @param limit
	 *            显示条数
	 * @return
	 * @throws TsfaServiceException
	 * @author 武鹏飞 CreateDate: 2017年7月25日
	 */
	Page<FindGuidMemberPageReturn> findGuidMemberPage(int currentPage, int limit);

	/**
	 * 
	 *
	 * 方法说明：统计导购数量
	 *
	 * @param findGuidMemberPage
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月26日
	 *
	 */
	int findGuidMemberCount(FindGuidMemberPage findGuidMemberPage);

	/**
	 * 
	 *
	 * 方法说明：统计导购数量根据商户号
	 *
	 * @param findGuidMemberPage
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年8月2日
	 *
	 */
	int findGuidMemberByMerchantNo(FindGuidMemberPage findGuidMemberPage);

	/**
	 * 
	 *
	 * 方法说明：查找导购与终端信息
	 *
	 * @param findGuidMemberDto
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月4日
	 *
	 */
	public List<GuidInfoShop> findGuidInfoShop(FindGuidMemberDto findGuidMemberDto);

	/**
	 * 
	 *
	 * 方法说明：查找分店下的导购排除自己
	 *
	 * @param findShopGmDto
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年8月8日
	 *
	 */
	public List<FindShopGmReturn> findShopGm(FindShopGmDto findShopGmDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购密码
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
	 * 方法说明：查询所有的导购
	 *
	 * @param guidMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月7日
	 *
	 */
	public List<GuidMember> findGuidMember(GuidMember guidMember) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：获取每个顾问未分组的客户数量
	 *
	 * @param guidMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月7日
	 *
	 */
	public int findPersonUngroupCount(GuidMember guidMember) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：未分配客户查询导购信息
	 *
	 * @param findAllotGuidMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月14日
	 *
	 */
	public List<FindAllotGuidMemberReturn> findAllotGuidMember(FindAllotGuidMember findAllotGuidMember)  throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询终端下已添加指定微信客户的所有导购信息
	 *
	 * @param findGuidByShopAndMember
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月14日
	 *
	 */
	public List<FindGuidByShopAndMemberReturn> findGuidByShopAndMember(FindGuidByShopAndMember findGuidByShopAndMember);

	/**
	 * 
	 *
	 * 方法说明：客户查询其他导购信息
	 *
	 * @param findOtherAllotGuidMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月24日
	 *
	 */
	public List<FindAllotGuidMemberReturn> findOtherAllotGuidMember(FindOtherAllotGuidMember findOtherAllotGuidMember) throws TsfaServiceException;
     /**
      * 
      *
      * 方法说明：终端外唯一微信判断
      *
      * @param findGuidMemberPage
      * @return
      * @throws TsfaServiceException
      *
      * @author 罗书明 CreateDate: 2017年11月29日
      *
      */
	public List<FindGuidMemberPageReturn> findGuidMemberNoWx(FindGuidMemberPage findGuidMemberPage)throws TsfaServiceException;

	/**
	 * 方法说明：获取店长
	 * @param shopNo
	 */
	public FindShopGmDtoReturn findGmDto(String shopNo) throws TsfaServiceException;
	
	/**
	 * 查找所有导购，根据终端微信
	 * @param findShopGmDto
	 * @return
	 */
	public List<FindGuidMemberPageReturn> findGuidMembersByShopWx(FindShopGmDto findShopGmDto);

	/**
	 * 批量删除
	 * @param ids
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月21日上午10:51:39
	 */
	public void batchDelete(String[] ids);

	/**
	 * 根据memberNo删除员工信息
	 * @param id
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月21日上午11:37:54
	 */
	public void delGuidMemberByMemberNo(String memberNo);

	/**
	 * 
	 *
	 * 方法说明：根据条件查询导购列表
	 *
	 * @param findGuidMemberDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年8月6日
	 *
	 */
    List<GuidMemberReturnDto> findGuidMemberSelective(FindGuidMemberDto findGuidMemberDto) throws TsfaServiceException;
	
    /**
     * 方法说明：根据导购编号查询导购信息
     * @param gmNo
     * @return
     * @throws TsfaServiceException
     */
    public FindShopGmDtoReturn findGmDtoByGmNo(String gmNo) throws TsfaServiceException;
    
    /**
	 * 查询导购
	 * @param guidMember
	 * @return
	 * @throws TsfaServiceException
	 */
	GuidMember findSingleGuidMember(GuidMember guidMember) throws TsfaServiceException;
}
