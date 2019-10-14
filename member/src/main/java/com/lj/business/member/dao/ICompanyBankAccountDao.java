package com.lj.business.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.business.member.domain.CompanyBankAccount;
import com.lj.business.member.dto.company.FindCompanyBankAccountPage;
import com.lj.business.member.dto.company.FindCompanyBankAccountPageReturn;
import com.lj.business.member.dto.company.FindCompanyBankAccountReturn;

public interface ICompanyBankAccountDao {
    int deleteByPrimaryKey(String code);

    int insert(CompanyBankAccount record);

    int insertSelective(CompanyBankAccount record);

    CompanyBankAccount selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CompanyBankAccount record);

    int updateByPrimaryKey(CompanyBankAccount record);
    
    /**
     * 
     *
     * 方法说明：查询分公司已添加指定银行卡号的银行账户数量
     *
     * @param companyNo
     * @param bankcardNo
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年4月26日
     *
     */
    int selectCountByCompanyAndCard(@Param("companyNo") String companyNo, @Param("bankcardNo") String bankcardNo);
    
    /**
     * 
     *
     * 方法说明：取消分公司下默认账户
     *
     * @param companyNo
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年4月25日
     *
     */
    int cancelDefaultAccount(String companyNo);
    
    /**
	 * 
	 *
	 * 方法说明：查找分公司银行账户信息记录数
	 *
	 * @param findCompanyBankAccountPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public int findCompanyBankAccountPageCount(FindCompanyBankAccountPage findCompanyBankAccountPage);
	
	/**
	 * 
	 *
	 * 方法说明：查找分公司银行账户信息
	 *
	 * @param findCompanyBankAccountPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public List<FindCompanyBankAccountPageReturn> findCompanyBankAccountPage(FindCompanyBankAccountPage findCompanyBankAccountPage);
	
	/**
	 * 
	 *
	 * 方法说明：查询分公司结算账户：按默认账户、创建时间排序
	 *
	 * @param companyNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月26日
	 *
	 */
	public List<FindCompanyBankAccountReturn> findSettleAccountByCompany(String companyNo);

    int updateByCompanyNo(CompanyBankAccount updateAccount);
}