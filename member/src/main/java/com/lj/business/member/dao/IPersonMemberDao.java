package com.lj.business.member.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.PersonMember;
import com.lj.business.member.dto.CountPersonMemberReturn;
import com.lj.business.member.dto.DoRepeatMemberDto;
import com.lj.business.member.dto.FindCountPersonMember;
import com.lj.business.member.dto.FindGmDistantPageReturn;
import com.lj.business.member.dto.FindImIndexPage;
import com.lj.business.member.dto.FindImIndexPageReturn;
import com.lj.business.member.dto.FindMemberInfoReturn;
import com.lj.business.member.dto.FindMemberRecord;
import com.lj.business.member.dto.FindNewPmCountDto;
import com.lj.business.member.dto.FindNewPmPage;
import com.lj.business.member.dto.FindNewPmPageReturn;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberPage;
import com.lj.business.member.dto.FindPersonMemberPageReturn;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.FindPersonMemberReturnList;
import com.lj.business.member.dto.FindPmSeachPage;
import com.lj.business.member.dto.FindPmSeachPageReturn;
import com.lj.business.member.dto.FindPmType;
import com.lj.business.member.dto.FindPmTypeIndexPage;
import com.lj.business.member.dto.FindPmTypeIndexPageReturn;
import com.lj.business.member.dto.FindPmWxBpInfo;
import com.lj.business.member.dto.FindPmWxBpInfoReturn;
import com.lj.business.member.dto.FindPmWxInfo;
import com.lj.business.member.dto.FindPmWxInfoReturn;
import com.lj.business.member.dto.FindPmWxInfos;
import com.lj.business.member.dto.FindTodayManageShop;
import com.lj.business.member.dto.FindTodayManageShopReturn;
import com.lj.business.member.dto.FindUnContactMember;
import com.lj.business.member.dto.FindUnContactMemberReturn;
import com.lj.business.member.dto.FindUnchatMemberPage;
import com.lj.business.member.dto.FindUnchatMemberPageReturn;
import com.lj.business.member.dto.FindUrgentMbrPage;
import com.lj.business.member.dto.FindUrgentMbrPageReturn;
import com.lj.business.member.dto.MemberNoAndShopNoPageDto;
import com.lj.business.member.dto.PersonMemberDto;
import com.lj.business.member.dto.PersonMemberStsGroupByShop;
import com.lj.business.member.dto.UpdatePersonMember;
import com.lj.business.member.dto.wxPmFollow.FindWxPmByGm;

public interface IPersonMemberDao {
	int deleteByPrimaryKey(String code);

	int insertSelective(PersonMember record);

	PersonMember selectByPrimaryKey(String code);

	PersonMember selectByParamKey(PersonMember personMember);

	int updateByPrimaryKeySelective(PersonMember record);

	int updateByMGM(PersonMember record);
	
	int updatePersonMemberByCond(PersonMember record);

	List<FindPersonMemberPageReturn> findPersonMemberPage(FindPersonMemberPage findPersonMemberPage);

	int findPersonMemberPageCount(FindPersonMemberPage findPersonMemberPage);

	/**
	 * 取消客户绑定
	 * @param wxNo
	 * @throws TsfaServiceException
	 */
	public void updateCanclePersonMember(Map map)throws TsfaServiceException;
	
	/**
	 * 转移认领客户
	 * @throws zlh TsfaServiceException
	 */
	public void  updateFriendsWithTransfer(Map param)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找客户所有微信信息及最新动态
	 *
	 * @param findPmWxBpInfo
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月21日
	 *
	 */
	List<FindPmWxBpInfoReturn> findPmWxBpInfo(FindPmWxBpInfo findPmWxBpInfo);

	/**
	 *
	 *
	 * 方法说明：查找客户所有微信信息
	 *
	 * @param findPmWxInfo
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月21日
	 *
	 */
	List<FindPmWxInfoReturn> findPmWxInfo(FindPmWxInfo findPmWxInfo);
	
	/**
	 * 方法说明：查找商户下客户所有微信信息
	 * @param findPmWxInfo
	 * @author 李端强 CreateDate: 2018年1月10日15:02:54
	 */
	List<FindPmWxInfoReturn> findPmWxInfos(FindPmWxInfos findPmWxInfos);

	/**
	 * 
	 *
	 * 方法说明：
	 *
	 * @param param
	 *            1. code 客户Code 2. pmTypeType 客户分类类型
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月12日
	 *
	 */
	FindPersonMemberPageReturn getByCond(Map<String, String> param);

	List<FindPmTypeIndexPageReturn> findPmTypeIndexPage(FindPmTypeIndexPage findPmTypeIndexPage);

	int findPmTypeIndexPageCount(FindPmTypeIndexPage findPmTypeIndexPage);

	/**
	 * 
	 *
	 * 方法说明：查询交叉数量
	 *
	 * @param findPmTypeIndexPage
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年8月10日
	 *
	 */
	int findPmTypeRepeatCount(PersonMember record);

	List<FindPmSeachPageReturn> findPmSeachPage(FindPmSeachPage findPmSeachPage);

	int findPmSeachPageCount(FindPmSeachPage findPmSeachPage);
	
	/**
	 * 
	 *
	 * 方法说明：非邀约版
	 *
	 * @param findPmSeachPage
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月9日
	 *
	 */
	List<FindPmSeachPageReturn> findPmSeachPageHc(FindPmSeachPage findPmSeachPage);

	/**
	 * 
	 *
	 * 方法说明：非邀约版
	 *
	 * @param findPmSeachPage
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月9日
	 *
	 */
    int findPmSeachPageCountHc(FindPmSeachPage findPmSeachPage);

	List<FindUrgentMbrPageReturn> findUrgentMbrPage(FindUrgentMbrPage findUrgentMbrPage);

	int findUrgentMbrPageCount(FindUrgentMbrPage findUrgentMbrPage);

	PersonMember selectByMGM(FindPersonMember findPersonMember);
	
	int findCountByMemberNo(FindPersonMember findPersonMember);

	List<FindNewPmPageReturn> findNewPmPage(FindNewPmPage findNewPmPage);

	int findNewPmPageCount(FindNewPmPage findNewPmPage);

	/**
	 *
	 * 方法说明：查询未联系的客户
	 *
	 * @param findUnContactMember
	 *            未联系的客户参数
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月22日
	 *
	 */
	List<FindUnContactMemberReturn> findUnContactMember(FindUnContactMember findUnContactMember);
	
	/**
	 * 
	 *
	 * 方法说明：根据分组查询顾客信息，包含标签
	 *
	 * @param findUnContactMember
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2017年12月7日
	 *
	 */
	List<FindUnContactMemberReturn> findUnContactMemberByPmType(FindUnContactMember findUnContactMember);

	/**
	 *
	 * 方法说明：查询未联系的客户总数
	 *
	 * @param findUnContactMember
	 *            未联系的客户参数
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月22日
	 *
	 */
	int findUnContactMemberCount(FindUnContactMember findUnContactMember);

	/**
	 * 查询导购下所有的客户数
	 * 
	 * @param merchantNo
	 *            商户编号
	 * @param memberNoGm
	 *            导购编号
	 * @return 总数
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月24日
	 */
	int findCountByMemberNoGm(@Param("merchantNo") String merchantNo, @Param("memberNoGm") String memberNoGm);

	int findPersonMemberSums(FindUrgentMbrPage findUrgentMbrPage);

	/**
	 * 性别统计
	 * 
	 * @return
	 */
	List<Map<String, Object>> selectSexStatisticsByShopNo();

	/**
	 * 年龄统计
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	List<Map<String, Object>> selectAgeStatisticsByShopNo(@Param("beginDate") String beginDate, @Param("endDate") String endDate);


	/**
	 * 兴趣统计
	 * 
	 * @return
	 */
	List<Map<String, Object>> selectInterestStatisticsByShopNo();

	/**
	 * 
	 *
	 * 方法说明：查找客户list
	 *
	 * @param doRepeatMemberDto
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年8月14日
	 *
	 */
	List<FindPersonMemberReturn> findList(DoRepeatMemberDto doRepeatMemberDto);

	/**
	 * 
	 *
	 * 方法说明：查询客户列表
	 *
	 * @param findPersonMemberPage
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月15日
	 *
	 */
	List<FindPersonMemberPageReturn> findPersonMemberList(FindPersonMemberPage findPersonMemberPage);

	/**
	 * 
	 *
	 * 方法说明：管理工作
	 *
	 * @param findTodayManageShop
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年8月16日
	 *
	 */
	List<FindTodayManageShopReturn> todayManageShopNew(FindTodayManageShop findTodayManageShop);

	/**
	 * 方法说明: 查询导购下的客户
	 * 
	 * @return
	 */
	List<FindPersonMemberReturn> findPersonMemberByGm(FindPersonMember personMember);

	/**
	 * 
	 *
	 * 方法说明：根据导购号和时间查询新增客户
	 *
	 * @param memberNo
	 *            导购号
	 * @param date
	 *            时间
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年8月18日
	 *
	 */
	long findCountPmAddByGmDay(@Param("memberNo") String memberNo, @Param("date") Date date);

	/**
	 * 
	 *
	 * 方法说明：查询客户分组数量最大一条
	 *
	 * @param findPersonMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月18日
	 *
	 */
	FindPersonMemberReturnList findPersonMemberTypeNum(FindPersonMember findPersonMember);

	/**
	 * 
	 *
	 * 方法说明：客户性别统计
	 *
	 * @param findPersonMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月18日
	 *
	 */
	List<FindUrgentMbrPageReturn> findPersonMemberSexCount(FindPersonMember findPersonMember);

	/**
	 * 
	 *
	 * 方法说明：计算新增导购数
	 *
	 * @param findNewPmCountDto
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年8月18日
	 *
	 */
	int findNewPmCount(FindNewPmCountDto findNewPmCountDto);

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
	int findCountPmByType(FindPmType findPmType);

	/**
	 * 
	 *
	 * 方法说明：查询客户分类类型数量排行(商户)
	 *
	 * @param findPersonMember
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月21日
	 *
	 */
	List<FindPersonMemberReturnList> findPersonMemberTypeList(FindPersonMember findPersonMember);
	
	FindPersonMemberReturnList findPersonMemberType(FindPersonMember findPersonMember);

	int findPersonMemberTypeCount(FindPersonMember findPersonMember);

	/**
	 * 
	 *
	 * 方法说明：根据导购编号和商户号查询客户信息
	 *
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月5日
	 *
	 */
	List<FindMemberInfoReturn> findMemberRecord(FindMemberRecord findMemberRecord);

	/**
	 * 
	 * 方法说明：根据时间分组新增客户
	 *
	 * @param map
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
    int findPersonMemberCont(FindPersonMember findPersonMember);

    /**
	 * 
	 *
	 * 方法说明：商户运营报表商户客户数
	 *
	 * @param merchantCode
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月27日
	 *
	 */
    List<Map<String, Object>> findShopPmNum(Map<String, Object> map);

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
	List<Map<String, Object>> findCountAddIntention(Map<String, Object> map);

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
	List<Map<String, Object>> findCountAddOther(Map<String, Object> map);

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
	List<Map<String, Object>> findCountPmOrder(Map<String, Object> map);

	/**
	 * 
	 *
	 * 方法说明：商户运营报表商户成暂停客户数
	 *
	 * @param map
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月28日
	 *
	 */
	List<Map<String, Object>> finCountAddPmAbandon(Map<String, Object> map);

	/**
	 * 
	 *
	 * 方法说明：商户运营报表商户成未分组客户数
	 *
	 * @param map
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年9月28日
	 *
	 */
	List<Map<String, Object>> findcountAddPmUngroup(Map<String, Object> map);
	
	/**
	 * 
	 *
	 * 方法说明：客户列表分页查询(OMS拆分客户列表)
	 *
	 * @param findPersonMemberPage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年10月25日
	 *
	 */
	List<FindPersonMemberPageReturn> queryPersonMemberPage(FindPersonMemberPage findPersonMemberPage);
	/**
	 *   
	 *
	 * 方法说明：查询分页数量
	 *
	 * @param findPersonMemberPage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月8日
	 *
	 */
	int queryPersonMemberPageCount(FindPersonMemberPage findPersonMemberPage); 
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
	FindPersonMemberReturnList queryPersonMemberPmType(FindPersonMember findPersonMember);
	
	/**
	 * 
	 *
	 * 方法说明：查找客户分类，不包含（交叉，紧急）
	 *
	 * @param findPersonMember
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年4月16日
	 *
	 */
	FindPersonMemberReturnList queryPersonMemberPmTypeExceptRepeatAndUrgency(FindPersonMember findPersonMember);
	
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
	List<FindPersonMemberReturn> findPersonMemberByNoWx(FindPersonMember findPersonMember);

	/**
	 * 
	 *
	 * 方法说明：查找web客户信息
	 *
	 * @param findImIndex
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年12月16日
	 *
	 */
	List<FindImIndexPageReturn> findImIndexList(FindImIndexPage findImIndex);
	
	/**
	 * 
	 *
	 * 方法说明：群发优惠券，选中的终端微信下客户列表查询
	 *
	 * @param findImIndex
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月25日
	 *
	 */
	List<FindImIndexPageReturn> findPmListByShopTerminals(FindImIndexPage findImIndex);
	
	/**
	 * 
	 *
	 * 方法说明：群发优惠券，选中的终端微信下客户列表数量查询
	 *
	 * @param findImIndex
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月25日
	 *
	 */
	Long findPmListByShopTerminalsCount(FindImIndexPage findImIndex);
	
	/**
	 * 方法说明：根据商户编号查询所有的意向客户的ID集合
	 * @param merchantNo 商户编号
	 * @return 客户的ID集合
	 * @author 李端强 CreateDate: 2017年12月16日
	 */
	public List<String> findIntentionMemberNo(String merchantNo);

	/**
	 * 
	 *
	 * 方法说明：查找web客户信息条数
	 *
	 * @param findImIndex
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年12月16日
	 *
	 */
	Long findImIndexListCount(FindImIndexPage findImIndex);
	
	
	FindPersonMemberReturn findPersonMemberByNo(String memberNo);
	
	/**
	 * 方法说明：根据客户编号和导购编号查询客户关系
	 * @param findPersonMember
	 * @return
	 * @author 李端强 CreateDate: 2018年1月18日
	 */
	FindPersonMemberReturn findPersonMemberByNoAndGM(FindPersonMember findPersonMember);

	/**
	 * 
	 *
	 * 方法说明：查询导购的所有客户数量
	 *
	 * @param findPmTypeIndexPage
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2017年12月22日
	 *
	 */
	Integer findAllCustomerCount(FindPmTypeIndexPage findPmTypeIndexPage);
	
	/**
     * 
     *
     * 方法说明：根据客户微信查询导购微信
     *
     * @param noWx
     * @return
     *
     * @author 梅宏博  CreateDate: 2018年1月2日
     *
     */
    List<String> findPersonMemberByWx(String noWx);
    
    /**
     * 根据客户微信号和终端微信号查询客户信息
     * @param noWx
     * @param shopNo
     * @return
     */
    FindPersonMemberReturn findPersonMemberByNoWxAndShopWx(@Param("noWx")String noWx,@Param("shopWx")String shopWx);
	
	/**
	 *
	 * 方法说明：根据商户编号指定标签code查询客户基本信息列表
	 * @param paramMap=merchantNo商户编号,pmLabelCode标签code
	 * @return
	 * @author 李端强 CreateDate: 2018年1月9日
	 */
	List<FindPersonMemberPageReturn> findPmbListByLabelCode(Map<String, Object> paramMap);
	
	/**
	 * 
	 *
	 * 方法说明：更新客户基础数据关联的所有导购客户关联数据PM表
	 *
	 * @param memberNo
	 * @param version
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月12日
	 *
	 */
	int updateVersionAllMember(@Param("memberNo") String memberNo, @Param("version") long version);
	/**
	 * 跟进终端编号查找客户数量
	 * @param shopNo
	 * @return
	 */
	int findListByShopNo(@Param("shopNo") String shopNo,@Param("memberNo") String memberNo);
	
	/**
	 * 
	 * @param memberNoAndShopNoPageDto
	 * @return
	 */
	List<FindGmDistantPageReturn> findPageByMemberNoAndShopNo(MemberNoAndShopNoPageDto memberNoAndShopNoPageDto);

	/**
	 * 
	 *
	 * 方法说明：微软CRM获取新增/更新的意向客户信息
     * 文档参考：（敏华crm与杨恩接口文档.docx）
	 *
	 * @param paramMap
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年2月28日
	 *
	 */
	List<Map<String,Object>> getCustomerInfo(Map<String,Object> paramMap);
	
	/**
	 * 
	 *
	 * 方法说明：预报名客户查询
	 *
	 * @param findImIndex
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年3月21日
	 *
	 */
	List<FindImIndexPageReturn> findForecastNameIndexList(FindImIndexPage findImIndex);

    Long findForecastNameIndexListCount(FindImIndexPage findImIndex);
    
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
    List<PersonMemberStsGroupByShop> memberStsGroupByShop(@Param("merchantNo") String merchantNo, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime); 
    
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
    public int findUnchatMemberCount(FindUnchatMemberPage findUnchatMemberPage);
    
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

    
    Long findWxPmCountByGm(FindWxPmByGm findWxPmByGm);
    
    List<FindPersonMemberReturn> findWxPmByGm(FindWxPmByGm findWxPmByGm);

    
    public List<PersonMember> findPersonMemberList(PersonMember personMember);

    /**
     * 更新所有客户根据分类
     * @param merchantNo
     * @param pmTypeCode
     * @param pmTypeName
     * @return
     */
    int updatePmTypeAllMember(@Param("merchantNo") String merchantNo,@Param("pmTypeCode") String pmTypeCode, @Param("pmTypeName") String pmTypeName);
    
    /**
     * 批量分组
     * @param codePms
     * @param pmTypeCode
     * @param pmTypeName
     * @return
     */
    int changePmTypeBatch(@Param("codePms") String[] codePms,@Param("pmTypeCode") String pmTypeCode, @Param("pmTypeName") String pmTypeName);
    

    /**
     * 更新该导购所有客户版本号
     * @param memberNoGm
     * @return
     */
    int updateVersionAllMemberByGm(@Param("memberNoGm") String memberNoGm);
    
	/**
	 * 根据手机号及终端微信号查用户
	 * 
	 * @param member
	 * @return
	 */
    public FindPersonMemberReturn  findPersonMemberByMoblieAndShopWx(FindPersonMember member);
    /**
	 * 方法说明：根据memberNo查询code
	 * @param findPersonMember
	 * @return
	 * @author 李端强 CreateDate: 2018年1月18日
	 */
	String findPersonMemberCodeByMemberNo(String memberNo);

	/**
	 *@Desc 
	 *@param updatePersonMember
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午3:53:21
	 */
	void delete(UpdatePersonMember updatePersonMember);
	
	/**
	 * 查找客户信息(查导购指定手机号的客户）。乐莎莎使用.
	 * @param personMemberDto
	 * @return
	 * @author lhy 2019.05.13
	 */
	List<PersonMemberDto> findPersonMemberByMoblies(PersonMemberDto personMemberDto);

	/**
	 *@Desc 
	 *@param mapPhone
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月6日上午10:15:02
	 */
	int findTotalMemberPhone(@Param("mapPhone") Map mapPhone);

	/**
	 *@Desc 
	 *@param map
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月6日下午4:56:02
	 */
	int findTotalMember(@Param("map") Map map);
}