package com.lj.business.weixin.dao;

import java.util.List;

import com.lj.business.weixin.domain.WxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPage;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPageReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramReturn;
import com.lj.business.weixin.dto.smallprogram.UpdateWxSmallProgram;

public interface IWxSmallProgramDao {
    int deleteByPrimaryKey(String code);

    int insert(WxSmallProgram record);

    int insertSelective(WxSmallProgram record);

    WxSmallProgram selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WxSmallProgram record);

    int updateByPrimaryKeyWithBLOBs(WxSmallProgram record);

    int updateByPrimaryKey(WxSmallProgram record);
    /**
	 * 
	 *
	 * 方法说明：根据appid和终端微信查找微信小程序信息
	 *
	 * @param findWxPublicAccount
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindWxSmallProgramReturn findByAppidAndNoWxZk(FindWxSmallProgram findWxSmallProgram);
	
	/**
	 * 
	 *
	 * 方法说明：查找微信小程序信息
	 *
	 * @param findWxSmallProgramPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public int findWxSmallProgramPageCount(FindWxSmallProgramPage findWxSmallProgramPage);
	
	/**
	 * 
	 *
	 * 方法说明：查找微信小程序信息
	 *
	 * @param findWxSmallProgramPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public List<FindWxSmallProgramPageReturn> findWxSmallProgramPage(FindWxSmallProgramPage findWxSmallProgramPage);

	/**
	 *@Desc 
	 *@param updateWxSmallProgram
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午7:07:03
	 */
	void delete(UpdateWxSmallProgram updateWxSmallProgram);
}