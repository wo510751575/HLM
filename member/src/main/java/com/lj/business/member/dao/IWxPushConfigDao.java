package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.WxPushConfig;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPage;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPageReturn;
import com.lj.business.member.dto.pushconfig.UpdateWxPushConfig;

public interface IWxPushConfigDao {
    int deleteByPrimaryKey(String code);

    int insert(WxPushConfig record);

    int insertSelective(WxPushConfig record);

    WxPushConfig selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WxPushConfig record);

    int updateByPrimaryKey(WxPushConfig record);
    
    /**
	 * 
	 *
	 * 方法说明：查找微信推送配置信息
	 *
	 * @param findWxPushConfigPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public int findWxPushConfigPageCount(FindWxPushConfigPage findWxPushConfigPage);
	
	/**
	 * 
	 *
	 * 方法说明：查找微信推送配置信息
	 *
	 * @param findWxPushConfigPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public List<FindWxPushConfigPageReturn> findWxPushConfigPage(FindWxPushConfigPage findWxPushConfigPage);
    
    /**
	 * 
	 *
	 * 方法说明：查询终端待推送配置列表，以机构类型按终端、商户为次序进行排序
	 *
	 * @param findWxPushConfigPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月7日
	 *
	 */
	public List<FindWxPushConfigPageReturn> findWxPushConfigToPush(FindWxPushConfigPage findWxPushConfigPage);

	/**
	 *@Desc 
	 *@param updateWxPushConfig
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午5:20:14
	 */
	void delete(UpdateWxPushConfig updateWxPushConfig);
}