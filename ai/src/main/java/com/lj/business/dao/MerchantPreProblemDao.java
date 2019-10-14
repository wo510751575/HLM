package com.lj.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.base.core.pagination.Page;
import com.lj.business.ai.dto.MerchantPreProblemDto;

public interface MerchantPreProblemDao {
    /**
     * 新增预设问题
     *
     * @param merchantPreProblemDto
     */
    void addMerchantPreProblem(MerchantPreProblemDto merchantPreProblemDto);

    /**
     *
     * @param matchMerchantProblemDto
     * @param page
     * @return
     */
    List<MerchantPreProblemDto> matchMerchantPreProblem(@Param("problemCodes")List<String> problemCodes , @Param("page")Page page,@Param("merchantNo")String merchantNo);

    /**
     * 修改商家预设问题
     *
     * @param merchantPreProblemDto
     */
    void updateMerchantPreProblem(MerchantPreProblemDto merchantPreProblemDto);

    MerchantPreProblemDto getMerchantPreProblem(String code);
    
    List<MerchantPreProblemDto> findMerchantPreProblemList(MerchantPreProblemDto merchantPreProblemDto);
    /**
     * 分页查询
     *
     * @param findMerchantPreProblemPage
     * @return
     */
	List<MerchantPreProblemDto> findMerchantPreProblemPage(MerchantPreProblemDto findMerchantPreProblemPage);

	int findMerchantPreProblemPageCount(MerchantPreProblemDto findMerchantPreProblemPage);

	MerchantPreProblemDto selectByPrimaryKey(String code);
	
	List<MerchantPreProblemDto> findIsAllMerchantPreProblemList(MerchantPreProblemDto merchantPreProblemDto);
}
