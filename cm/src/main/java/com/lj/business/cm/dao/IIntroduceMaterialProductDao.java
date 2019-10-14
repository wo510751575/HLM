package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.domain.IntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.DelIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProductPage;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProductPageReturn;

public interface IIntroduceMaterialProductDao {
    int deleteByPrimaryKey(String code);

    int insert(IntroduceMaterialProduct record);

    int insertSelective(IntroduceMaterialProduct record);

    IntroduceMaterialProduct selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(IntroduceMaterialProduct record);

    int updateByPrimaryKey(IntroduceMaterialProduct record);

	List<FindIntroduceMaterialProductPageReturn> findIntroduceMaterialProductPage(
			FindIntroduceMaterialProductPage findIntroduceMaterialProductPage);

	int findIntroduceMaterialProductPageCount(
			FindIntroduceMaterialProductPage findIntroduceMaterialProductPage);

	/**
	 * 
	 *
	 * 方法说明：根据素材编号查找个人素材介绍产品关联表信息
	 *
	 * @param findIntroduceMaterialProduct
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月11日
	 *
	 */
	List<IntroduceMaterialProduct> finfindIntroduceMaterialProductByMaterCode(
			FindIntroduceMaterialProduct findIntroduceMaterialProduct);

	/**
	 * 
	 *
	 * 方法说明：根据素材编号删除个人素材介绍产品关联表信息
	 *
	 * @param findIntroduceMaterialProduct
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月11日
	 *
	 */
	void delIntroduceMaterialProductByMaterCode(
			DelIntroduceMaterialProduct delIntroduceMaterialProduct);
}