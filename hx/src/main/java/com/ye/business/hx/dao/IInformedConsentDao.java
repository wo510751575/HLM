package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.InformedConsent;
import com.ye.business.hx.dto.FindInformedConsentPage;
import com.ye.business.hx.dto.InformedConsentDto;

public interface IInformedConsentDao {
    int deleteByPrimaryKey(String code);

    int insert(InformedConsent record);

    int insertSelective(InformedConsent record);

    InformedConsent selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(InformedConsent record);

    int updateByPrimaryKeyWithBLOBs(InformedConsent record);

    int updateByPrimaryKey(InformedConsent record);

	/**   
	 * @Title: findInformedConsents   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findInformedConsentPage
	 * @param: @return      
	 * @return: List<InformedConsentDto>      
	 * @throws   
	 */
	List<InformedConsentDto> findInformedConsents(FindInformedConsentPage findInformedConsentPage);

	/**   
	 * @Title: findInformedConsentPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findInformedConsentPage
	 * @param: @return      
	 * @return: List<InformedConsentDto>      
	 * @throws   
	 */
	List<InformedConsentDto> findInformedConsentPage(FindInformedConsentPage findInformedConsentPage);

	/**   
	 * @Title: findInformedConsentPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findInformedConsentPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findInformedConsentPageCount(FindInformedConsentPage findInformedConsentPage);
}