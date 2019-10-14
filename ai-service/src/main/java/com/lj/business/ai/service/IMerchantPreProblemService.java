package com.lj.business.ai.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.ai.dto.MerchantPreProblemDto;

public interface IMerchantPreProblemService {


    /**
     * 新增商户预设问题
     *
     * @param merchantPreProblemDto
     */
	MerchantPreProblemDto addMerchantPreProblemService(MerchantPreProblemDto merchantPreProblemDto);

    /**
     * 匹配关键词问题
     *
     * @param merchantPreProblemDto
     * @return
     */
    List<MerchantPreProblemDto> matchPreProblem(MerchantPreProblemDto merchantPreProblemDto,Page page);


    /**
     * 修改商家预设问题
     * @param merchantPreProblemDto
     */
    void updateMerchantPreProblem(MerchantPreProblemDto merchantPreProblemDto);

    /**
     * 分页查询预设问题
     * @param page
     * @param merchantPreProblemDto
     * @return
     */
    Page<MerchantPreProblemDto>  findMerchantPreProblemPage(MerchantPreProblemDto findMerchantPreProblemPage) throws TsfaServiceException;
    
    
    MerchantPreProblemDto getMerchantPreProblem(String code);

    /**
	 * 
	 *
	 * 方法说明：查找预设问题
	 *
	 * @param findMerchantPreProblem
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public MerchantPreProblemDto findMerchantPreProblem(MerchantPreProblemDto findMerchantPreProblem) throws TsfaServiceException;
	
}
