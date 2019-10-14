package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.GuidCard;
import com.lj.business.member.dto.guidCard.FindGuidCard;
import com.lj.business.member.dto.guidCard.FindGuidCardPage;
import com.lj.business.member.dto.guidCard.FindGuidCardPageReturn;
import com.lj.business.member.dto.guidCard.FindGuidCardReturn;
import com.lj.business.member.dto.guidCard.UpdateGuidCard;
import com.lj.business.member.dto.guidCardCustomer.GuidCardAddNumDto;

public interface IGuidCardDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(GuidCard record);

    GuidCard selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(GuidCard record);

	List<FindGuidCardPageReturn> findGuidCardPage(
			FindGuidCardPage findGuidCardPage);

	int findGuidCardPageCount(FindGuidCardPage findGuidCardPage);

	/**
	 * 
	 *
	 * 方法说明：根据导购号查询导购名片
	 *
	 * @param findGuidCard
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月3日
	 *
	 */
	GuidCard findGuidCardByGm(FindGuidCard findGuidCard);

	/**
	 * 
	 *
	 * 方法说明：增加人气、点赞或者分享量
	 *
	 * @param guidCardAddNumDto
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月3日
	 *
	 */
	int addGuidCardTypeNum(GuidCardAddNumDto guidCardAddNumDto);

	/**
	 * 
	 *
	 * 方法说明：根据CODE查询导购名片
	 *
	 * @param guidCardCodes
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月3日
	 *
	 */
	List<FindGuidCardReturn> findGuidCardByCode(List<String> guidCardCodes);

	/**
	 * 
	 *
	 * 方法说明：根据类型删除导购名片明细记录
	 *
	 * @param guidCardAddNumDto
	 *
	 * @author 梅宏博  CreateDate: 2017年11月13日
	 *
	 */
	void redGuidCardTypeNum(GuidCardAddNumDto guidCardAddNumDto);

	/**
	 * 
	 *
	 * 方法说明：根据终端号更新导购名片
	 *
	 * @param updateGuidCard
	 *
	 * @author 梅宏博  CreateDate: 2017年11月13日
	 *
	 */
	void updateGuidCardByShop(UpdateGuidCard updateGuidCard);
}