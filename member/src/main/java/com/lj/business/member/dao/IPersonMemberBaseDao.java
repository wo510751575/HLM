package com.lj.business.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.PersonMemberBase;
import com.lj.business.member.dto.EditPersonMember;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseList;
import com.lj.business.member.dto.FindPersonMemberBasePage;
import com.lj.business.member.dto.FindPersonMemberBasePageReturn;
import com.lj.business.member.dto.FindPersonMemberBaseReturnList;
import com.lj.business.member.dto.FindPersonMemberBaseReturnMap;
import com.lj.business.member.dto.FindPersonMemberName;
import com.lj.business.member.dto.UpdatePersonMemberBase;
import com.lj.business.member.dto.UpdatePersonMemberBaseRatioClientInfoDto;

public interface IPersonMemberBaseDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(PersonMemberBase record);

    PersonMemberBase selectByPrimaryKey(String code);
    
    int updateSetUpUser(PersonMemberBase record);
    
    int updateCancleSetUpUser(PersonMemberBase record);

    int updateByPrimaryKeySelective(PersonMemberBase record);

	List<FindPersonMemberBasePageReturn> findPersonMemberBasePage(
			FindPersonMemberBasePage findPersonMemberBasePage);

	int findPersonMemberBasePageCount(
			FindPersonMemberBasePage findPersonMemberBasePage);

    PersonMemberBase findByMobile(FindPersonMemberBase findPersonMemberBase);
    
    FindPersonMemberBaseReturnMap findByMobiles(FindPersonMemberBase findPersonMemberBase);

    int updateByPrimaryKey(PersonMemberBase record);
    
    PersonMemberBase selectByParams(FindPersonMemberBase findPersonMemberBase);
    
    int selectCountByParams(PersonMemberBase personMemberBase);

    /**
     * 
     *
     * 方法说明：根据编码获取客户
     *
     * @param codeList
     * @return
     *
     * @author 武鹏飞 CreateDate: 2017年7月21日
     *
     */
    List<FindPersonMemberName> findByCodeList(@Param("codeList") List<String> codeList);
   /**
    * 
    *
    * 方法说明：OMS专用（根据客户编号或客户名称查询）
    *
    * @param findPersonMemberBaseList
    * @return
    *
    * @author 罗书明 CreateDate: 2017年7月22日
    *
    */
    FindPersonMemberBaseReturnList findPersonMemberBaseList(FindPersonMemberBaseList findPersonMemberBaseList);
    /**
     * 
     *
     * 方法说明：统计省/区域客户数量
     *
     * @param findPersonMemberBaseList
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月7日
     *
     */
    FindPersonMemberBaseReturnList findPersonMemberBaseCounts(FindPersonMemberBaseList findPersonMemberBaseList);
    
    /**
     * 
     *
     * 方法说明：修改完成度
     *
     * @param dto
     *
     * @author 冯辉 CreateDate: 2017年8月17日
     *
     */
    void updateRatioClientInfoByMemberNo(UpdatePersonMemberBaseRatioClientInfoDto dto) ;
    /**
     * 
     *
     * 方法说明：查询性别分组客户数最多的客户的性别
     *
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月18日
     *
     */
    FindPersonMemberBaseReturnList findPersonMemberMax();
    /**
     * 
     *
     * 方法说明：查询区域客户数
     *
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月18日
     *
     */
	List<FindPersonMemberBaseList> findPersonMemberBaseMemberCount(FindPersonMemberBase findPersonMemberBase);
	/**
	 * 
	 *
	 * 方法说明：新增客户数
	 *
	 * @param findPersonMemberBaseList
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月28日
	 *
	 */
	int findPersonMemberBaseNumAdd(FindPersonMemberBaseList findPersonMemberBaseList);
	
	
	PersonMemberBase findPersonMemberBaseParams(FindPersonMemberBase findPersonMemberBase);

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
	PersonMemberBase findMobileAndCode(FindPersonMemberBase findPersonMemberBase);
	
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
    List<FindPersonMemberBaseReturnList> findPersonMemberBaseNums(FindPersonMemberBaseList personMemberBaseList);
    
    /**
     * 
     *
     * 方法说明：修改客户手机号
     *
     * @param updatePersonMemberBase
     *
     * @author 曾垂瑜 CreateDate: 2017年11月2日
     *
     */
    public void updateMobile(UpdatePersonMemberBase updatePersonMemberBase);
	
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
	public int updatePersonMemberWxInfoByNoWx(UpdatePersonMemberBase updatePersonMemberBase)throws TsfaServiceException;
	
	/**
	 *
	 * 方法说明：根据客户手机号查询微信信息mobile=15274949965
	 * @param map mobile=15274949965
	 * @return memberName,mobile,noWx
	 *
	 * @author 李端强 CreateDate: 2017年12月13日
	 *
	 */
	public Map<String,Object> getBaseInfoByMobile(Map<String, Object> map);
	
	/**
	 * 
	 *
	 * 方法说明：根据微信号或微信别名查询客户基本信息
	 * 1、此方法是在不确定微信号正确的情况下按微信别名（微信不为空时唯一）去查询（如真实微信是l-d-q123456，返回时为ldq123456）
	 * 2、如果确定了微信号正确，可以直接只按微信号去查询
	 * 
	 * @param noWx
	 * @param alias
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月13日
	 *
	 */
	public PersonMemberBase findMemberBaseByNoWxOrAlias(@Param("noWx") String noWx, @Param("alias") String alias);

	String selectSetUp(String memberNo);
	
	public List<PersonMemberBase> selectByMemberNos(FindPersonMemberBase personMemberBase);

	/**
	 *@Desc 
	 *@param editPersonMember
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月5日下午7:24:23
	 */
	PersonMemberBase checkMobile(EditPersonMember editPersonMember);
}