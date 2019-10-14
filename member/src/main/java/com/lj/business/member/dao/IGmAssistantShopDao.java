package com.lj.business.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.member.domain.GmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopByImReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPage;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPageReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.gmAssistantShop.UpdateGmAssistantShop;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;

public interface IGmAssistantShopDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(GmAssistantShop record);

    GmAssistantShop selectByPrimaryKey(String code);
    
    /**
     * 获取单个导购助手数据
     * @param record
     * @return
     */
    GmAssistantShop get(GmAssistantShop record);
    /**
     * 更新最新登录成功时间
     * @param record
     * @return
     */
    int updateLastDate(GmAssistantShop record);
    
    int updateByPrimaryKeySelective(GmAssistantShop record);

	List<FindGmAssistantShopPageReturn> findGmAssistantShopPage(FindGmAssistantShopPage findGmAssistantShopPage);
	
	/**
	 * 關聯終端查出頭像
	 * @param findGmAssistantShopPage
	 * @return
	 */
	List<FindGmAssistantShopPageReturn> findGmAssistantWithTerminalPage(FindGmAssistantShopPage findGmAssistantShopPage);
	

	int findGmAssistantWithTerminalCount(FindGmAssistantShopPage findGmAssistantShopPage);
	
	int findGmAssistantShopPageCount(FindGmAssistantShopPage findGmAssistantShopPage);
	
    List<FindGmAssistantShopReturn> findGmAssistantShopList(FindGmAssistantShop findGmAssistantShop);
    
    /**
     * 根据微信查询已绑定的员工
     * @param findGmAssistantShop
     * @return
     */
    List<GmAssistantShop> findGmAssistantListByWx(FindGmAssistantShop findGmAssistantShop);
    
    List<GmAssistantShop> findGmAssistantNoWxListByLoginName(FindGmAssistantShop findGmAssistantShop);
    
    /**
     * 查询导购助手绑定的微信集合，按英文","分隔返回
     * @param assistantNo
     * @return
     */
    String findGroupConcatByAssNo(@Param("assistantNo") String assistantNo);
    
    /**
     * 获取终端编号集合根据导购助手分组
     * @param assistantNo
     * @param merchantNo
     * @return
     */
    String findTidCodesByAssistantNo(@Param("assistantNo") String assistantNo,@Param("merchantNo") String merchantNo);
    /**
     * 
     *
     * 方法说明：查询导购助手管理的终端列表
     *
     * @param findShopTidFromWeb
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年1月9日
     *
     */
    List<String> findShopNoFromWeb(FindShopTidFromWeb findShopTidFromWeb);
    
    /**
     * 
     *
     * 方法说明：IM导购助手查询包括可用终端终端的终端
     *
     * @param findGmAssistantShop
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年1月22日
     *
     */
	List<FindGmAssistantShopByImReturn> findGmAssistantShopListByIm(FindGmAssistantShop findGmAssistantShop);
	
	/**
	 * 删除记录根据导购助手号
	 * @param assistantNo
	 * @return
	 */
	int deleteByAssistantNo(@Param("assistantNo") String assistantNo,@Param("merchantNo") String merchantNo);
	/**
	 * 同步登录名
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	int synByLoginName(@Param("oldLoginName") String oldLoginName,@Param("loginName")  String loginName);

	/**
	 *@Desc 
	 *@param updateGmAssistantShop
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午3:44:24
	 */
	void delete(UpdateGmAssistantShop updateGmAssistantShop);
	
	/**
	 * 按微信分组
	 * @param findGmAssistantShop
	 * @return
	 */
	List<FindGmAssistantShopReturn> findListGroupByNoWx(FindGmAssistantShop findGmAssistantShop);
}