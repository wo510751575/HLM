package com.lj.business.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.weixin.domain.ImChatInfoTemp;

public interface IImChatInfoTempDao {
    int deleteByPrimaryKey(String code);

    int insert(ImChatInfoTemp record);

    int insertSelective(ImChatInfoTemp record);

    ImChatInfoTemp selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ImChatInfoTemp record);

    int updateByPrimaryKeyWithBLOBs(ImChatInfoTemp record);

    int updateByPrimaryKey(ImChatInfoTemp record);
    
    /**
     * 
     *
     * 方法说明：查询终端微信新增客户所有临时聊天记录(按聊天时间顺序排序)
     *
     * @param shopNo
     * @param noWxShop
     * @param noWx
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2017年12月22日
     *
     */
    public List<ImChatInfoTemp> findImChatInfoTempByNewPerson(@Param("shopNo") String shopNo, @Param("noWxShop") String noWxShop, @Param("noWx") String noWx);

	/**
	 *@Desc 
	 *@param imChatInfoTemp
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午5:46:26
	 */
	void delete(ImChatInfoTemp imChatInfoTemp);

	/**
	 *@Desc 
	 *@param merchantNo
	 *@param noWx
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月8日上午11:16:08
	 */
	void delete(@Param("merchantNo") String merchantNo,@Param("noWx") String noWx);
}