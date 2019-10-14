package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.BranchCompany;
import com.lj.business.member.dto.company.FindBranchCompanyDetailReturn;
import com.lj.business.member.dto.company.FindBranchCompanyPage;
import com.lj.business.member.dto.company.FindBranchCompanyPageReturn;
import com.lj.business.member.dto.company.FindBranchCompanyReturn;

public interface IBranchCompanyDao {
    int deleteByPrimaryKey(String code);

    int insert(BranchCompany record);

    int insertSelective(BranchCompany record);

    BranchCompany selectByPrimaryKey(String code);
    
    int updateByPrimaryKeySelective(BranchCompany record);

    int updateByPrimaryKey(BranchCompany record);


    BranchCompany selectByCompanyNo(String companyNo);
    
	/**
	 * 
	 *
	 * 方法说明：查询商户下所有分公司
	 *
	 * @param merchantNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年5月1日
	 *
	 */
	public List<FindBranchCompanyReturn> findBranchCompanyByMerchantNo(String merchantNo);

	/**
	 * 分页查询
	 * @param findBranchCompanyPage
	 * @return
	 */
	int findBranchCompanyPageCount(FindBranchCompanyPage findBranchCompanyPage);

	List<FindBranchCompanyPageReturn> findBranchCompanyPage(FindBranchCompanyPage findBranchCompanyPage);

	/**
	 * 
	 *
	 * 方法说明：根据code查询分公司及分公司账号信息
	 *
	 * @param code
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年5月31日
	 *
	 */
    FindBranchCompanyDetailReturn findBranchCompanyDetail(String code);

    /**
     * 
     *
     * 方法说明：根据经销商代码查询数量
     *
     * @param dealerCode
     * @return
     *
     * @author 许新龙 CreateDate: 2018年6月4日
     *
     */
    int findCountByDealerCode(String dealerCode);
    
    /**
     * 
     *
     * 方法说明：根据经销商代码查询经销商列表
     *
     * @param dealerCode
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年6月20日
     *
     */
    public List<FindBranchCompanyReturn> findBranchCompanyByDealerCode(String dealerCode);

    List<FindBranchCompanyReturn> findBranchCompanySelective(FindBranchCompanyPage findBranchCompanyPage);
}