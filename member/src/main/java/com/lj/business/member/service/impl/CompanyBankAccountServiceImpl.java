package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.ICompanyBankAccountDao;
import com.lj.business.member.domain.CompanyBankAccount;
import com.lj.business.member.dto.company.AddCompanyBankAccount;
import com.lj.business.member.dto.company.AddCompanyBankAccountReturn;
import com.lj.business.member.dto.company.DelCompanyBankAccount;
import com.lj.business.member.dto.company.DelCompanyBankAccountReturn;
import com.lj.business.member.dto.company.FindCompanyBankAccount;
import com.lj.business.member.dto.company.FindCompanyBankAccountPage;
import com.lj.business.member.dto.company.FindCompanyBankAccountPageReturn;
import com.lj.business.member.dto.company.FindCompanyBankAccountReturn;
import com.lj.business.member.dto.company.UpdateCompanyBankAccount;
import com.lj.business.member.dto.company.UpdateCompanyBankAccountReturn;
import com.lj.business.member.service.ICompanyBankAccountService;

/**
 * 
 * 
 * 类说明：分公司银行账户实现类
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
@Service
public class CompanyBankAccountServiceImpl implements ICompanyBankAccountService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CompanyBankAccountServiceImpl.class);
	
	@Resource
	private ICompanyBankAccountDao companyBankAccountDao;
	
	@Override
	public AddCompanyBankAccountReturn addCompanyBankAccount(AddCompanyBankAccount addCompanyBankAccount) throws TsfaServiceException {
		logger.debug("addCompanyBankAccount(AddCompanyBankAccount addCompanyBankAccount={}) - start", addCompanyBankAccount); 

		AssertUtils.notNull(addCompanyBankAccount);
		try {
			// 检查分公司是否已添加该账户
			int count = companyBankAccountDao.selectCountByCompanyAndCard(addCompanyBankAccount.getCompanyNo(), addCompanyBankAccount.getBankcardNo());
			if(count > 0) {
				throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_ADD_EXIST_ERROR, "该账户已存在！");
			}
			
			// 设置为默认账户，则需先更新原默认账户为非默认账户
			if(addCompanyBankAccount.getIsDefault()) {
				count = companyBankAccountDao.cancelDefaultAccount(addCompanyBankAccount.getCompanyNo());
				logger.debug("已取消分公司{}默认银行账户数：{}", addCompanyBankAccount.getCompanyNo(), count);
			}
			
			CompanyBankAccount companyBankAccount = new CompanyBankAccount();
			companyBankAccount.setCode(GUID.generateByUUID());
			companyBankAccount.setCompanyNo(addCompanyBankAccount.getCompanyNo());
			companyBankAccount.setCompanyName(addCompanyBankAccount.getCompanyName());
			companyBankAccount.setBankcardNo(addCompanyBankAccount.getBankcardNo());
			companyBankAccount.setBankCode(addCompanyBankAccount.getBankCode().name());
			companyBankAccount.setBankName(addCompanyBankAccount.getBankCode().getText());
			companyBankAccount.setCustName(addCompanyBankAccount.getCustName());
			companyBankAccount.setProvinceCode(addCompanyBankAccount.getProvinceCode());
			companyBankAccount.setProvinceName(addCompanyBankAccount.getProvinceName());
			companyBankAccount.setCityCode(addCompanyBankAccount.getCityCode());
			companyBankAccount.setCityName(addCompanyBankAccount.getCityName());
			companyBankAccount.setBranch(addCompanyBankAccount.getBranch());
			companyBankAccount.setIsDefault(addCompanyBankAccount.getIsDefault() ? CommonConstant.I_YES : CommonConstant.I_NO);
			companyBankAccount.setMerchantNo(addCompanyBankAccount.getMerchantNo());
			companyBankAccount.setCreateDate(new Date());
			companyBankAccount.setUpdateDate(companyBankAccount.getCreateDate());
//			companyBankAccount.setRemark(addCompanyBankAccount.getRemark());
//			companyBankAccount.setRemark2(addCompanyBankAccount.getRemark2());
//			companyBankAccount.setRemark3(addCompanyBankAccount.getRemark3());
//			companyBankAccount.setRemark4(addCompanyBankAccount.getRemark4());
			companyBankAccountDao.insert(companyBankAccount);
			
			AddCompanyBankAccountReturn addCompanyBankAccountReturn = new AddCompanyBankAccountReturn();
			addCompanyBankAccountReturn.setCode(companyBankAccount.getCode());
			logger.debug("addCompanyBankAccount(AddCompanyBankAccount) - end - return value={}", addCompanyBankAccountReturn); 
			return addCompanyBankAccountReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增分公司银行账户信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_ADD_ERROR,"新增分公司银行账户信息错误！",e);
		}
	}
	
	@Override
	public DelCompanyBankAccountReturn delCompanyBankAccount(DelCompanyBankAccount delCompanyBankAccount) throws TsfaServiceException {
		logger.debug("delCompanyBankAccount(DelCompanyBankAccount delCompanyBankAccount={}) - start", delCompanyBankAccount); 

		AssertUtils.notNull(delCompanyBankAccount);
		AssertUtils.notNullAndEmpty(delCompanyBankAccount.getCode(),"Code不能为空！");
		try {
			companyBankAccountDao.deleteByPrimaryKey(delCompanyBankAccount.getCode());
			DelCompanyBankAccountReturn delCompanyBankAccountReturn  = new DelCompanyBankAccountReturn();

			logger.debug("delCompanyBankAccount(DelCompanyBankAccount) - end - return value={}", delCompanyBankAccountReturn); 
			return delCompanyBankAccountReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除分公司银行账户信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_DEL_ERROR,"删除分公司银行账户信息错误！",e);
		}
	}

	@Override
	public UpdateCompanyBankAccountReturn updateCompanyBankAccount(UpdateCompanyBankAccount updateCompanyBankAccount) throws TsfaServiceException {
		logger.debug("updateCompanyBankAccount(UpdateCompanyBankAccount updateCompanyBankAccount={}) - start", updateCompanyBankAccount); 

		AssertUtils.notNull(updateCompanyBankAccount);
		AssertUtils.notNullAndEmpty(updateCompanyBankAccount.getCode(),"Code不能为空");

		CompanyBankAccount companyBankAccount = this.findCompanyBankAccountByCode(updateCompanyBankAccount.getCode());
		
		try {
			int count = 0;
			if(!companyBankAccount.getBankcardNo().equals(updateCompanyBankAccount.getBankcardNo())) {
				// 检查分公司是否已添加该账户
				count = companyBankAccountDao.selectCountByCompanyAndCard(companyBankAccount.getCompanyNo(), updateCompanyBankAccount.getBankcardNo());
				if(count > 0) {
					throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_ADD_EXIST_ERROR, "银行账户已存在");
				}
			}
			
			// 改为默认账户，则需先更新原默认账户为非默认账户
			if(updateCompanyBankAccount.setIsDefault() && CommonConstant.I_NO == companyBankAccount.getIsDefault()) {
				count = companyBankAccountDao.cancelDefaultAccount(companyBankAccount.getCompanyNo());
				logger.debug("已取消分公司{}默认银行账户数：{}", companyBankAccount.getCompanyNo(), count);
			}
			
			CompanyBankAccount updateAccount = new CompanyBankAccount();
			updateAccount.setCode(updateCompanyBankAccount.getCode());
			updateAccount.setBankcardNo(updateCompanyBankAccount.getBankcardNo());
			updateAccount.setBankCode(updateCompanyBankAccount.getBankCode().name());
			updateAccount.setBankName(updateCompanyBankAccount.getBankCode().getText());
			updateAccount.setCustName(updateCompanyBankAccount.getCustName());
			updateAccount.setProvinceCode(updateCompanyBankAccount.getProvinceCode());
			updateAccount.setProvinceName(updateCompanyBankAccount.getProvinceName());
			updateAccount.setCityCode(updateCompanyBankAccount.getCityCode());
			updateAccount.setCityName(updateCompanyBankAccount.getCityName());
			updateAccount.setBranch(updateCompanyBankAccount.getBranch());
			updateAccount.setIsDefault(updateCompanyBankAccount.setIsDefault() ? CommonConstant.I_YES : CommonConstant.I_NO);
			updateAccount.setUpdateDate(new Date());
//			updateAccount.setRemark(updateCompanyBankAccount.getRemark());
//			updateAccount.setRemark2(updateCompanyBankAccount.getRemark2());
//			updateAccount.setRemark3(updateCompanyBankAccount.getRemark3());
//			updateAccount.setRemark4(updateCompanyBankAccount.getRemark4());
			AssertUtils.notUpdateMoreThanOne(companyBankAccountDao.updateByPrimaryKeySelective(updateAccount));
			UpdateCompanyBankAccountReturn updateCompanyBankAccountReturn = new UpdateCompanyBankAccountReturn();

			logger.debug("updateCompanyBankAccount(UpdateCompanyBankAccount) - end - return value={}", updateCompanyBankAccountReturn); 
			return updateCompanyBankAccountReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("分公司银行账户信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_UPDATE_ERROR,"分公司银行账户信息更新信息错误！",e);
		}
	}
	
	@Override
    public UpdateCompanyBankAccountReturn updateCompanyBankAccountByCompanyNo(UpdateCompanyBankAccount updateCompanyBankAccount) throws TsfaServiceException {

        AssertUtils.notNull(updateCompanyBankAccount);
        AssertUtils.notNullAndEmpty(updateCompanyBankAccount.getCompanyNo(),"CompanyNo不能为空");

        try {
            CompanyBankAccount updateAccount = new CompanyBankAccount();
            updateAccount.setCompanyNo(updateCompanyBankAccount.getCompanyNo());
            updateAccount.setCompanyName(updateCompanyBankAccount.getCompanyName());
            updateAccount.setBankcardNo(updateCompanyBankAccount.getBankcardNo());
            if (updateCompanyBankAccount.getBankCode() != null) {
                updateAccount.setBankCode(updateCompanyBankAccount.getBankCode().name());
                updateAccount.setBankName(updateCompanyBankAccount.getBankCode().getText());
            }
            updateAccount.setCustName(updateCompanyBankAccount.getCustName());
            updateAccount.setProvinceCode(updateCompanyBankAccount.getProvinceCode());
			updateAccount.setProvinceName(updateCompanyBankAccount.getProvinceName());
			updateAccount.setCityCode(updateCompanyBankAccount.getCityCode());
			updateAccount.setCityName(updateCompanyBankAccount.getCityName());
            updateAccount.setBranch(updateCompanyBankAccount.getBranch());
            updateAccount.setIsDefault(updateCompanyBankAccount.setIsDefault() ? CommonConstant.I_YES : CommonConstant.I_NO);
            updateAccount.setUpdateDate(new Date());
            AssertUtils.notUpdateMoreThanOne(companyBankAccountDao.updateByCompanyNo(updateAccount));
            UpdateCompanyBankAccountReturn updateCompanyBankAccountReturn = new UpdateCompanyBankAccountReturn();

            return updateCompanyBankAccountReturn;
        }catch (TsfaServiceException e) {
            logger.error(e.getMessage(),e);
            throw e;
        } catch (Exception e) {
            logger.error("分公司银行账户信息更新信息错误！",e);
            throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_UPDATE_ERROR,"分公司银行账户信息更新信息错误！",e);
        }
    }

	@Override
	public FindCompanyBankAccountReturn findCompanyBankAccount(FindCompanyBankAccount findCompanyBankAccount) throws TsfaServiceException {
		logger.debug("findCompanyBankAccount(FindCompanyBankAccount findCompanyBankAccount={}) - start", findCompanyBankAccount); 

		AssertUtils.notNull(findCompanyBankAccount);
		AssertUtils.notNullAndEmpty(findCompanyBankAccount.getCode(),"Code不能为空");
		
		CompanyBankAccount companyBankAccount = this.findCompanyBankAccountByCode(findCompanyBankAccount.getCode());
		try {
			FindCompanyBankAccountReturn findCompanyBankAccountReturn = new FindCompanyBankAccountReturn();
			//find数据录入
			findCompanyBankAccountReturn.setCode(companyBankAccount.getCode());
			findCompanyBankAccountReturn.setCompanyNo(companyBankAccount.getCompanyNo());
			findCompanyBankAccountReturn.setCompanyName(companyBankAccount.getCompanyName());
			findCompanyBankAccountReturn.setBankcardNo(companyBankAccount.getBankcardNo());
			findCompanyBankAccountReturn.setBankCode(companyBankAccount.getBankCode());
			findCompanyBankAccountReturn.setBankName(companyBankAccount.getBankName());
			findCompanyBankAccountReturn.setCustName(companyBankAccount.getCustName());
			findCompanyBankAccountReturn.setProvinceCode(companyBankAccount.getProvinceCode());
			findCompanyBankAccountReturn.setProvinceName(companyBankAccount.getProvinceName());
			findCompanyBankAccountReturn.setCityCode(companyBankAccount.getCityCode());
			findCompanyBankAccountReturn.setCityName(companyBankAccount.getCityName());
			findCompanyBankAccountReturn.setBranch(companyBankAccount.getBranch());
			findCompanyBankAccountReturn.setIsDefault(companyBankAccount.getIsDefault());
			findCompanyBankAccountReturn.setMerchantNo(companyBankAccount.getMerchantNo());
			findCompanyBankAccountReturn.setCreateDate(companyBankAccount.getCreateDate());
			findCompanyBankAccountReturn.setUpdateDate(companyBankAccount.getUpdateDate());
			findCompanyBankAccountReturn.setRemark(companyBankAccount.getRemark());
			findCompanyBankAccountReturn.setRemark2(companyBankAccount.getRemark2());
			findCompanyBankAccountReturn.setRemark3(companyBankAccount.getRemark3());
			findCompanyBankAccountReturn.setRemark4(companyBankAccount.getRemark4());
			
			logger.debug("findCompanyBankAccount(FindCompanyBankAccount) - end - return value={}", findCompanyBankAccountReturn); 
			return findCompanyBankAccountReturn;
//		}catch (TsfaServiceException e) {
//			throw e;
		} catch (Exception e) {
			logger.error("查找分公司银行账户信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_FIND_ERROR,"查找分公司银行账户信息信息错误！",e);
		}
	}

	@Override
	public Page<FindCompanyBankAccountPageReturn> findCompanyBankAccountPage(FindCompanyBankAccountPage findCompanyBankAccountPage) throws TsfaServiceException {
		logger.debug("findCompanyBankAccountPage(FindCompanyBankAccountPage findCompanyBankAccountPage={}) - start", findCompanyBankAccountPage); 

		AssertUtils.notNull(findCompanyBankAccountPage);
		List<FindCompanyBankAccountPageReturn> returnList = null;
		int count = 0;
		try {
			count = companyBankAccountDao.findCompanyBankAccountPageCount(findCompanyBankAccountPage);
			if(count > 0) {
				returnList = companyBankAccountDao.findCompanyBankAccountPage(findCompanyBankAccountPage);
			}
		} catch (Exception e) {
			logger.error("分公司银行账户信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_FIND_PAGE_ERROR,"分公司银行账户信息不存在错误.！",e);
		}
		Page<FindCompanyBankAccountPageReturn> returnPage = new Page<FindCompanyBankAccountPageReturn>(returnList, count, findCompanyBankAccountPage);

		logger.debug("findCompanyBankAccountPage(FindCompanyBankAccountPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

	@Override
	public void updateDefaultAccount(String code) {
		logger.debug("updateDefaultAccount(String code={}) - start", code); 

		AssertUtils.notNullAndEmpty(code,"Code不能为空");
		CompanyBankAccount companyBankAccount = this.findCompanyBankAccountByCode(code);
		
		try {
			if(CommonConstant.I_NO == companyBankAccount.getIsDefault()) {
				// 取消原默认账户
				int count = companyBankAccountDao.cancelDefaultAccount(companyBankAccount.getCompanyNo());
				logger.debug("已取消分公司{}默认银行账户数：{}", companyBankAccount.getCompanyNo(), count);
				
				// 设置为默认账户
				CompanyBankAccount update = new CompanyBankAccount();
				update.setCode(code);
				update.setIsDefault(CommonConstant.I_YES);
				update.setUpdateDate(new Date());
				companyBankAccountDao.updateByPrimaryKeySelective(update);
			}
			logger.debug("updateDefaultAccount(String) - end - return"); 
//		}catch (TsfaServiceException e) {
//			throw e;
		} catch (Exception e) {
			logger.error("查找分公司银行账户信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_FIND_ERROR,"查找分公司银行账户信息信息错误！",e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据code查询银行账户
	 *
	 * @param code
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月26日
	 *
	 */
	private CompanyBankAccount findCompanyBankAccountByCode(String code) {
		CompanyBankAccount companyBankAccount = null;
		try {
			companyBankAccount = companyBankAccountDao.selectByPrimaryKey(code);
		} catch (Exception e) {
			logger.error("查找分公司银行账户信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_FIND_ERROR,"查找分公司银行账户信息信息错误！",e);
		}
		if(companyBankAccount == null){
			throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_NOT_EXIST_ERROR,"分公司银行账户信息不存在");
		}
		return companyBankAccount;
	}

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
	@Override
	public List<FindCompanyBankAccountReturn> findSettleAccountByCompany(String companyNo) {
		logger.debug("findSettleAccountByCompany(String companyNo={}) - start", companyNo); 

		AssertUtils.notNullAndEmpty(companyNo,"分公司编号不能为空");
		
		List<FindCompanyBankAccountReturn> returnList = null;
		try {
			returnList = companyBankAccountDao.findSettleAccountByCompany(companyNo);
		} catch (Exception e) {
			logger.error("查找分公司银行账户信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_FIND_ERROR,"查找分公司银行账户信息信息错误！",e);
		}
		
		if(returnList == null) {
			logger.error("分公司{}没有找结算银行账户", companyNo);
			throw new TsfaServiceException(ErrorCode.COMPANY_BANK_ACCOUNT_NOT_EXIST_ERROR,"分公司没有找结算银行账户");
		}
		
		logger.debug("findSettleAccountByCompany(String) - end - return value={}", returnList); 
		return returnList;
	}
	
}
