package com.lj.oms.service;

import com.lj.business.member.dto.FindMerchant;
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.UpdateMerchant;
import com.lj.oms.entity.dto.kuaitui.KuaiTuiDto;
import com.lj.oms.entity.dto.kuaitui.KuaiTuiDtoReturn;

/**
 * 
 * 
 *  
 * 
 * <p>
 * 详细描述：快推接口
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2018年1月29日
 */
public interface KuaiTuiHessianService {
	
	/**
	 * 
	 *
	 * 方法说明：创建商户
	 * 建立商户，OMS商户管理员账号，账号授权防止重复调用
	 *
	 * @param maike51Dto
	 *
	 * @author 段志鹏 CreateDate: 2018年1月29日
	 *
	 */
	public KuaiTuiDtoReturn createUserToKen(KuaiTuiDto kuaiTui51Dto);
	
	
	/**
	 * 获取商户信息
	 * @param findMerchant
	 * @return
	 */
	public KuaiTuiDtoReturn getMerchant(FindMerchantPage findMerchantPage);
	
	
	/**
	 * 修改商户状态
	 * @param findMerchant
	 * @return
	 */
	public KuaiTuiDtoReturn editMerchantStatus(UpdateMerchant updateMerchant);
	
}
