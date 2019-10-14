package com.lj.business.weixin.dao;

import com.lj.business.weixin.domain.WxJobRedPackInfo;
import com.lj.business.weixin.dto.WxJobRedPackInfoDto;

public interface IWxJobRedPackInfoDao {
    int deleteByPrimaryKey(String code);

    int insert(WxJobRedPackInfo record);

    int insertSelective(WxJobRedPackInfo record);

    WxJobRedPackInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WxJobRedPackInfo record);

    int updateByPrimaryKey(WxJobRedPackInfo record);

	/**
	 *@Desc 
	 *@param wxJobRedPackInfoDto
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午6:31:07
	 */
	void delete(WxJobRedPackInfoDto wxJobRedPackInfoDto);
}