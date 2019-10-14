package com.lj.business.member.service;

import java.util.List;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.company.AddDealerApply;
import com.lj.business.member.dto.company.AddDealerApplyReturn;
import com.lj.business.member.dto.company.Bank;
import com.lj.business.member.dto.company.DealerApplyAudit;
import com.lj.business.member.dto.company.DealerApplyAuditReturn;
import com.lj.business.member.dto.company.DelDealerApply;
import com.lj.business.member.dto.company.DelDealerApplyReturn;
import com.lj.business.member.dto.company.FindDealerApply;
import com.lj.business.member.dto.company.FindDealerApplyDetailReturn;
import com.lj.business.member.dto.company.FindDealerApplyPage;
import com.lj.business.member.dto.company.FindDealerApplyPageReturn;
import com.lj.business.member.dto.company.FindDealerApplyReturn;
import com.lj.business.member.dto.company.UpdateDealerApply;
import com.lj.business.member.dto.company.UpdateDealerApplyReturn;


/**
 * 
 * 
 * 类说明：经销商申请接口类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
public interface IDealerApplyService {
	
	/**
	 * 
	 *
	 * 方法说明：添加经销商申请信息（如果添加了终端，同时添加对应终端）
	 *
	 * @param addDealerApply
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public AddDealerApplyReturn addDealerApply(AddDealerApply addDealerApply) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找经销商申请信息
	 *
	 * @param findDealerApply
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindDealerApplyReturn findDealerApply(FindDealerApply findDealerApply) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除经销商申请信息
	 *
	 * @param delDealerApply
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public DelDealerApplyReturn delDealerApply(DelDealerApply delDealerApply) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改经销商申请信息
	 *
	 * @param updateDealerApply
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public UpdateDealerApplyReturn updateDealerApply(UpdateDealerApply updateDealerApply)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找经销商申请信息
	 *
	 * @param findDealerApplyPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindDealerApplyPageReturn> findDealerApplyPage(FindDealerApplyPage findDealerApplyPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：获取支持的银行CODE
	 *
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年5月7日
	 *
	 */
//    List<Bank> getBankCodes();
    
    /**
     * 
     *
     * 方法说明：经销商入驻申请详情(含申请的终端)
     *
     * @param dealerApplyCode
     * @return
     * @throws TsfaServiceException
     *
     * @author 许新龙 CreateDate: 2018年5月24日
     *
     */
    FindDealerApplyDetailReturn findDealerApplyDetail(String dealerApplyCode) throws TsfaServiceException;

    /**
     * 
     *
     * 方法说明：经销商入驻申请审核
     *
     * @param dealerApplyAudit
     * @return
     * @throws TsfaServiceException
     *
     * @author 许新龙 CreateDate: 2018年5月28日
     *
     */
    DealerApplyAuditReturn audit(DealerApplyAudit dealerApplyAudit) throws TsfaServiceException;
}
