package com.lj.business.weixin.dao;

import java.util.List;

import com.lj.business.weixin.domain.WxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPage;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPageReturn;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.dto.publicaccount.UpdateWxPublicAccount;

public interface IWxPublicAccountDao {
    int deleteByPrimaryKey(String code);

    int insert(WxPublicAccount record);

    int insertSelective(WxPublicAccount record);

    WxPublicAccount selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WxPublicAccount record);

    int updateByPrimaryKeyWithBLOBs(WxPublicAccount record);

    int updateByPrimaryKey(WxPublicAccount record);
    /**
	 * 
	 *
	 * 方法说明：根据公众号和终端微信查找微信公众号信息
	 *
	 * @param findWxPublicAccount
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindWxPublicAccountReturn findByUsernameAndNoWxZk(FindWxPublicAccount findWxPublicAccount);
	
	/**
	 * 
	 *
	 * 方法说明：查找微信公众号信息
	 *
	 * @param findWxPublicAccountPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public int findWxPublicAccountPageCount(FindWxPublicAccountPage findWxPublicAccountPage);
	
	/**
	 * 
	 *
	 * 方法说明：查找微信公众号信息
	 *
	 * @param findWxPublicAccountPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public List<FindWxPublicAccountPageReturn> findWxPublicAccountPage(FindWxPublicAccountPage findWxPublicAccountPage);

	/**
	 *@Desc 
	 *@param updateWxPublicAccount
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午6:43:12
	 */
	void delete(UpdateWxPublicAccount updateWxPublicAccount);
}