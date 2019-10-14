package com.lj.business.weixin.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.weixin.domain.WxRedpackDetailInfo;
import com.lj.business.weixin.dto.FindWxRedpackDetailInfoPage;
import com.lj.business.weixin.dto.WxRedpackDetailInfoDto;

public interface IWxRedpackDetailInfoDao {
    int deleteByPrimaryKey(String code);

    int insert(WxRedpackDetailInfo record);

    int insertSelective(WxRedpackDetailInfo record);

    WxRedpackDetailInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WxRedpackDetailInfo record);

    int updateByPrimaryKey(WxRedpackDetailInfo record);
    
    List<WxRedpackDetailInfoDto> findWxRedpackDetailInfos(FindWxRedpackDetailInfoPage wxRedpackDetailInfoDto);
    
    List<WxRedpackDetailInfoDto> findWxRedpackDetailInfoPage(FindWxRedpackDetailInfoPage wxRedpackDetailInfoPage);
    
    Integer findWxRedpackDetailInfoPageCount(FindWxRedpackDetailInfoPage wxRedpackDetailInfoPage);
    
    /**
     * 方法说明：已发红包的年份集合
     * @return 年份集合
     * @author 李端强 CreateDate: 2018年1月31日
     */
    List<String> findWxRedpackDetailYears();
    
    /**
	 *
	 * 方法说明：成功发送红包的总金额
	 * @param findWxRedpackDetailInfoPage
	 * @return
	 * @author 李端强 CreateDate: 2018年2月1日
	 *
	 */
   Long findWxRedpackDetailTotalMoney(FindWxRedpackDetailInfoPage findWxRedpackDetailInfoPage);
    
    /**
     * 获取中控微信今日已发红包金额
     * @param noWx
     * @return
     */
    Long getSumAmtByNoWxToDay(String noWx);
    
    
    WxRedpackDetailInfoDto findWxRedpackDetailinfoByOrderNo(String orderNo);
    
    Long findRedPackCountByNoWxAndType(@Param("noWx")String noWx, @Param("type")Integer type);
    
    /**
     * 
     *
     * 方法说明：查询待轮询的所有红包明细（发送中）
     *
     * @param maxPollCount		最大轮询次数
     * @param sendDateBegin		发送时间-从
     * @param sendDateEnd		发送时间-到
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年4月18日
     *
     */
    public List<WxRedpackDetailInfo> findRedpackDetailByPoll(@Param("maxPollCount")int maxPollCount, @Param("sendDateBegin")Date sendDateBegin, @Param("sendDateEnd")Date sendDateEnd);
    
    
    
    /**
     * 方法说明：根据电话号码和活动批次号查询用户信息
     */
    List<WxRedpackDetailInfoDto> findWxRedpackDetailinfoByPhoneNumber(@Param("phoneNumber")String phoneNumber, @Param("batchCode")String batchCode);
    
    
    /**
     * 方法说明：根据用户微信和活动批次号查询用户信息
     */
    List<WxRedpackDetailInfoDto> findWxRedpackDetailinfoByWX(@Param("noWx")String noWx, @Param("batchCode")String batchCode);
    
    /**
                * 方法说明：查询当天已发红包数
     */
    Integer findWxRedpackInDayCount(@Param("noWxShop")String noWxShop,@Param("batchCode")String batchCode);

    /**
     * 
     *@Desc 查询发送的红包数量:包含发送中+发送成功+发送失败+已领取+已退回总数量
     *@param find
     *@return
     *@return Integer
     *@author 贾光朝
     *@createDate 2019年4月13日下午5:00:05
     */
	Integer findWxRedpackDetailTotalSendCount(FindWxRedpackDetailInfoPage find);
}