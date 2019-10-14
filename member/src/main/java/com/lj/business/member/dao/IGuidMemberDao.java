package com.lj.business.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindShopGmDto;
import com.lj.business.member.dto.FindShopGmDtoReturn;
import com.lj.business.member.dto.FindShopGmReturn;
import com.lj.business.member.dto.GuidInfoShop;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.UpdateGuidMember;
import com.lj.business.member.dto.UpdateManagerOrGuidPwdDto;
import com.lj.business.member.dto.addfriends.FindAllotGuidMember;
import com.lj.business.member.dto.addfriends.FindAllotGuidMemberReturn;
import com.lj.business.member.dto.addfriends.FindOtherAllotGuidMember;
import com.lj.business.member.dto.im.FindGuidByShopAndMember;
import com.lj.business.member.dto.im.FindGuidByShopAndMemberReturn;

public interface IGuidMemberDao {
	int deleteByPrimaryKey(String code);
	
	int deleteByMemberNo(String memberNo);

	int insertSelective(GuidMember record);

	GuidMember selectByPrimaryKey(String code);

	GuidMember selectByParams(GuidMember guidMember);
	
	List<GuidMember> selectListByParams(GuidMember guidMember);
	
	int selectCountByParams(GuidMember guidMember);

	int updateByPrimaryKeySelective(GuidMember record);

	List<FindGuidMemberPageReturn> findGuidMemberPage(FindGuidMemberPage findGuidMemberPage);

	List<FindGuidMemberPageReturn> findGuidMemberExport(FindGuidMemberPage findGuidMemberPage);

	int findGuidMemberPageCount(FindGuidMemberPage findGuidMemberPage);

	int findGuidMemberByMerchantNo(FindGuidMemberPage findGuidMemberPage);

	GuidMember findGuidMember(GuidMember guidMember);

	List<FindGuidMemberPageReturn> findGuidMembers(FindGuidMemberPage findGuidMemberPage);

	List<FindGuidMemberPageReturn> findGuidMembersNoSelf(FindGuidMemberPage findGuidMemberPage);

	/**
	 * 
	 *
	 * 方法说明：查找个人中心导购信息
	 *
	 * @param findGuidMemberDto
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年7月11日
	 *
	 */
	GuidMemberReturnDto findGuid(FindGuidMemberDto findGuidMemberDto);

	/**
	 * 
	 *
	 * 方法说明：更新个人中心导购信息
	 *
	 * @param record
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年7月11日
	 *
	 */
	int updateGuid(GuidMember record);

	/**
	 * 
	 *
	 * 方法说明：添加个人中心导购信息
	 *
	 * @param record
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年7月11日
	 *
	 */
	int addGuid(GuidMember record);

	/**
	 * 
	 *
	 * 方法说明：根据商户号和分店编号查找导购列表
	 *
	 * @param findGuidMemberDto
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月20日
	 *
	 */
	List<GuidMemberReturnDto> findGuidMemberList(FindGuidMemberDto findGuidMemberDto);
	
	/**
	 * 
	 *
	 * 方法说明：根据条件查询导购列表
	 *
	 * @param findGuidMemberDto
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年8月6日
	 *
	 */
	List<GuidMemberReturnDto> findGuidMemberSelective(FindGuidMemberDto findGuidMemberDto);

	/**
	 * 
	 *
	 * 方法说明：查询所有的导购
	 *
	 * @param GuidMember
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月7日
	 *
	 */
	List<GuidMember> findGuidMemberAllList(GuidMember GuidMember);

	/**
	 * 
	 *
	 * 方法说明：更新导购表密码
	 *
	 * @param updateManagerOrGuidPwdDto
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年7月26日
	 *
	 */
	int updateGuidForPwd(UpdateManagerOrGuidPwdDto updateManagerOrGuidPwdDto);

	/**
	 * 
	 *
	 * 方法说明：查询店员信息_H5报表
	 *
	 * @param guidMember
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年8月1日
	 *
	 */
	List<GuidInfoShop> selectGuidInfoShop(GuidMember guidMember);

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
	List<GuidInfoShop> findGuidInfoShop(FindGuidMemberDto findGuidMemberDto);

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
	List<FindShopGmReturn> findShopGm(FindShopGmDto findShopGmDto);
	 
	List<FindGuidMemberPageReturn> findGuidMembersByShopWx(FindShopGmDto findShopGmDto);
	
	List<FindShopGmReturn> findShopGmPy(FindShopGmDto findShopGmDto);

	/**
	 * 
	 *
	 * 方法说明：获取每个顾问未分组的客户数量
	 *
	 * @param guidMember
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月7日
	 *
	 */
	int findPersonUngroupCount(GuidMember guidMember);

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
	List<FindAllotGuidMemberReturn> findAllotGuidMember(FindAllotGuidMember findAllotGuidMember);
	
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
	List<FindAllotGuidMemberReturn> findOtherAllotGuidMember(FindOtherAllotGuidMember findOtherAllotGuidMember);

	String findWxByMemberNo(String memberNo);
	/**
	 * 
	 *
	 * 方法说明：终端外唯一
	 *
	 * @param findGuidMemberPage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年11月29日
	 *
	 */
	List<FindGuidMemberPageReturn> findGuidMemberNoWx(FindGuidMemberPage findGuidMemberPage);
	
	/**
	 * 
	 *
	 * 方法说明：根据终端编号更新终端名称，冗余字段更新
	 *
	 * @param guidMember
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2017年12月16日
	 *
	 */
	int updateShopNameByShopNo(GuidMember guidMember);
	
	/**
	 * 
	 *
	 * 方法说明：查询商户下包含导购名称的绑定的微信列表
	 *
	 * @param merchantName
	 * @param memberName
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	public List<String> findNoWxByMemberName(@Param("merchantNo") String merchantNo, @Param("memberName") String memberName);

	/**
	 * 
	 *
	 * 方法说明：根据终端编号更新导购表
	 *
	 * @param updateGuidMember
	 *
	 * @author 梅宏博  CreateDate: 2017年12月15日
	 *
	 */
	void updateGuidByShopNo(UpdateGuidMember updateGuidMember);

	/**
	 * 
	 *
	 * 方法说明：根据微信号更新二维码
	 *
	 * @param updateGuidMember
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月19日
	 *
	 */
	void updateQcordByNoWx(UpdateGuidMember updateGuidMember);
	
	String findGmShopNoByGmNo(@Param("merchantNoGm") String merchantNoGm);
	
	FindShopGmDtoReturn findGmDto(@Param("shopNo") String shopNo);

	/**
	 * 批量删除
	 * @param ids
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月21日上午10:59:25
	 */
	void batchDelete(String[] ids);
	
	/**
	 * 根据导购编号查询导购信息
	 * @param gmNo
	 * @return
	 */
	FindShopGmDtoReturn findGmDtoByGmNo(@Param("memberNoGm")String gmNo);

}