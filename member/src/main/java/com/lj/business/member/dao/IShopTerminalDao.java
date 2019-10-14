package com.lj.business.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.ShopTerminal;
import com.lj.business.member.dto.couponmultipush.FindShopTerminalByWxList;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPage;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPageReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.dto.shopterminal.UpdateShopTerminal;

public interface IShopTerminalDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(ShopTerminal record);

    ShopTerminal selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ShopTerminal record);

    int updateShopTerminalShopName(ShopTerminal shopTerminal);

	/**
	 * 
	 *
	 * 方法说明：查找终端终端信息
	 *
	 * @param findShopTerminalPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public int findShopTerminalPageCount(FindShopTerminalPage findShopTerminalPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找终端终端信息
	 *
	 * @param findShopTerminalPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	public List<FindShopTerminalPageReturn> findShopTerminalPage(FindShopTerminalPage findShopTerminalPage) throws TsfaServiceException;
    
    ShopTerminal selectByWx(@Param("noWx") String noWx);
    /**
	 * 
	 * 方法说明：查找终端终端信息所有微信号
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月20日
	 *
	 */
	List<String> findAllNoWx();

	/**
	 * 
	 * 方法说明：查找终端终端信息所有手机串号
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月20日
	 *
	 */
	List<String> findAllImei();
	
	/**
	 * 
	 *
	 * 方法说明：查询终端终端数量
	 *
	 * @param shopNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月24日
	 *
	 */
	public int findTerminalCountByShop(String shopNo);

	/**
	 *
	 * 方法说明：查找终端终端信息微信号是否重复
	 *
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月27日
	 * @param string 
	 *
	 */
	int hasNoWx(String noWx);

	/**
	 *
	 * 方法说明：查找终端终端信息手机串号是否重复
	 *
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月27日
	 * @param string 
	 *
	 */
	int hasImei(String imei);
	
	/**
	 * 
	 *
	 * 方法说明：查询商户下包含微信昵称的终端微信列表（有效终端）
	 *
	 * @param merchantNo
	 * @param wxNickname
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	public List<String> findNoWxByWxNickname(@Param("merchantNo") String merchantNo, @Param("wxNickname") String wxNickname);
	
	/**
	 * 
	 *
	 * 方法说明：查询指定终端列表和微信列表下的有效终端（微信）列表
	 *
	 * @param shopNoList
	 * @param noWxList
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	public List<FindShopTidFromWebReturn> findShopTidByShopNoAndWx(@Param("shopNoList") List<String> shopNoList, @Param("noWxList") List<String> noWxList);

	/**
	 * 
	 *
	 * 方法说明：查找终端终端信息
	 *
	 * @param findShopTerminal
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年12月11日
	 *
	 */
	List<FindShopTerminalReturn> findShopTerminalSelect(
			FindShopTerminal findShopTerminal);

	/**
	 * 
	 *
	 * 方法说明：根据shopNo查找终端终端信息
	 *
	 * @param findShopTerminal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月9日
	 *
	 */
	List<FindShopTerminalReturn> findShopTerminal();
	
	
	
	
	/**
	 * 查询微信绑定正常状态终端
	 * @param noWx
	 * @return
	 */
	FindShopTerminalReturn findShopTerminalNormal(String noWx);
	
	/**
	 * 
	 *
	 * 方法说明：查询所有待检查版本的有效终端
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月10日
	 *
	 */
	List<ShopTerminal> findAllShopTerminalByCheckVersion();
	
	/**
	 * 
	 *
	 * 方法说明：查询导购助手管理的终端终端列表
	 *
	 * @param findShopTidFromWeb
	 * @param noWxList
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月22日
	 *
	 */
	public List<FindShopTidFromWebReturn>  findShopTerminalFromWeb(@Param("param") FindShopTidFromWeb findShopTidFromWeb, @Param("noWxList") List<String> noWxList);

	/**
	 * 
	 * 方法说明：查询所有未绑定的终端
	 *
	 * @param @return    设定文件 
	 * @return List<ShopTerminal>    返回类型 
	 * @throws Exception
	 *
	 * @author 林进权
	 *         CreateDate: 2018年1月22日
	 */
	List<FindShopTerminalPageReturn> findShopTerminalsByNotBind(FindShopTerminalPage findShopTerminalPage);

	/**
	 * 
	 * 方法说明：查询所有已绑定的终端
	 *
	 * @param @return    设定文件 
	 * @return List<ShopTerminal>    返回类型 
	 * @throws Exception
	 *
	 * @author 林进权
	 *         CreateDate: 2018年1月22日
	 */
	List<FindShopTerminalPageReturn> findShopTerminalsByBind(FindShopTerminalPage findShopTerminalPage);
	
	/**
	 * 
	 *
	 * 方法说明：更新终端微信账户余额（根据CODE或noWx更新）
	 *
	 * @param updateShopTerminal
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月26日
	 *
	 */
	public int updateWxAccountBalance(UpdateShopTerminal updateShopTerminal);

	/**
	 * 
	 *
	 * 方法说明：根据选中的多个终端微信查询终端编号集合
	 *
	 * @param findShopTerminalByWxList
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月29日
	 *
	 */
    List<String> findShopTerminalByWxList(FindShopTerminalByWxList findShopTerminalByWxList);
    
    /**
     * 
     *
     * 方法说明：根据终端微信列表，筛选出有微信好友预报名的终端微信
     *
     * @param noWxList
     * @return
     *
     * @author 许新龙 CreateDate: 2018年3月21日
     *
     */
    List<String> findShopTerminalForecastName(@Param("noWxList") List<String> noWxList);
	
	
	
	
	/**
     * 
     *
     * 方法说明：查找商户指定招聘类型的中控机
     *
     * @param findShopTerminal
     * @return
     *
     * @author 罗书明 CreateDate: 2018年3月26日
     *
     */
    List<FindShopTerminalReturn> findAllShopTerminalByWeiXin(FindShopTerminal findShopTerminal);

    /**
     * 根据商户和导购微信查询终端信息
     * @param merchantNo
     * @param noWxGm
     * @return
     */
    public FindShopTerminalReturn findShopTerminalByMerchantNoAndNoWxGm(@Param("merchantNo")String merchantNo,@Param("noWxGm") String noWxGm);
	/**
	 * 
	 *
	 * 方法说明：根据商户查找终端信息
	 *
	 * @param findShopTerminal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月9日
	 *
	 */
	List<FindShopTerminalReturn> findShopTerminalByMerchantNo(@Param("merchantNo")String merchantNo);
	
	
	
	FindShopTerminalReturn findShopTerminalByWx(FindShopTerminal findShopTerminal);
	
	/**
     * 
     *
     * 方法说明：根据商户编号 memberNo 统计微信总数
     *
     * @param findShopTerminal
     * @return
     *
     * @author 罗书明 CreateDate: 2018年12月18日
     *
     */
	Integer getShopTerminalCount(ShopTerminal shopTerminal);
}