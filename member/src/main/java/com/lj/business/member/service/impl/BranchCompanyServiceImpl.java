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
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IBranchCompanyDao;
import com.lj.business.member.dao.IMerchantDao;
import com.lj.business.member.domain.BranchCompany;
import com.lj.business.member.domain.Merchant;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.company.AddBranchCompany;
import com.lj.business.member.dto.company.AddBranchCompanyReturn;
import com.lj.business.member.dto.company.DelBranchCompany;
import com.lj.business.member.dto.company.DelBranchCompanyReturn;
import com.lj.business.member.dto.company.FindBranchCompany;
import com.lj.business.member.dto.company.FindBranchCompanyDetailReturn;
import com.lj.business.member.dto.company.FindBranchCompanyPage;
import com.lj.business.member.dto.company.FindBranchCompanyPageReturn;
import com.lj.business.member.dto.company.FindBranchCompanyReturn;
import com.lj.business.member.dto.company.UpdateBranchCompany;
import com.lj.business.member.dto.company.UpdateBranchCompanyReturn;
import com.lj.business.member.dto.company.UpdateCompanyBankAccount;
import com.lj.business.member.emus.Status;
import com.lj.business.member.service.IBranchCompanyService;
import com.lj.business.member.service.ICompanyBankAccountService;

/**
 * 
 * 
 * 类说明：分公司实现类
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
public class BranchCompanyServiceImpl implements IBranchCompanyService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BranchCompanyServiceImpl.class);
	
	@Resource
	private IBranchCompanyDao branchCompanyDao;
	
	@Resource
	private IMerchantDao merchantDao;
	
	@Resource
    private ICompanyBankAccountService companyBankAccountService;
	
	@Override
	public AddBranchCompanyReturn addBranchCompany(AddBranchCompany addBranchCompany) throws TsfaServiceException {
		logger.debug("addBranchCompany(AddBranchCompany addBranchCompany={}) - start", addBranchCompany); 

		AssertUtils.notNull(addBranchCompany);
		try {
			String companyNo = addBranchCompany.getCompanyNo();
			if(StringUtils.isNotEmpty(companyNo)) {
				BranchCompany company = branchCompanyDao.selectByCompanyNo(companyNo);
				if(company != null) {
					throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_HAS_EXIST_ERROR, "分公司已存在");
				}
			} else {
				companyNo = GUID.generateByUUID();
			}
			
			String dealerCode = addBranchCompany.getDealerCode();
			if(StringUtils.isNotEmpty(dealerCode)) {
				int count = branchCompanyDao.findCountByDealerCode(dealerCode);
				if(count != 0) {
					throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_HAS_EXIST_ERROR, "分公司已存在");
				}
			}
			
			
			BranchCompany branchCompany = new BranchCompany();
			//add数据录入
			branchCompany.setCode(GUID.generateByUUID());
			branchCompany.setCompanyNo(companyNo);
			branchCompany.setDealerCode(addBranchCompany.getDealerCode());
			branchCompany.setCompanyName(addBranchCompany.getCompanyName());
			branchCompany.setStatus(Status.NORMAL.name());
			if(addBranchCompany.getCompanyType() != null){
				branchCompany.setCompanyType(addBranchCompany.getCompanyType().getCode());
			}
			branchCompany.setProvinceCode(addBranchCompany.getProvinceCode());
			branchCompany.setProvinceName(addBranchCompany.getProvinceName());
			branchCompany.setCityCode(addBranchCompany.getCityCode());
			branchCompany.setCityName(addBranchCompany.getCityName());
			branchCompany.setBusinessLicense(addBranchCompany.getBusinessLicense());
			branchCompany.setLegalPersonName(addBranchCompany.getLegalPersonName());
			branchCompany.setLegalPersonId(addBranchCompany.getLegalPersonId());
			branchCompany.setLegalPersonCardFront(addBranchCompany.getLegalPersonCardFront());
			branchCompany.setLegalPersonCardReverse(addBranchCompany.getLegalPersonCardReverse());
			branchCompany.setBusinessPerson(addBranchCompany.getBusinessPerson());
			branchCompany.setDealerResponsiblePerson(addBranchCompany.getDealerResponsiblePerson());
			branchCompany.setDealerResponsibleMobile(addBranchCompany.getDealerResponsibleMobile());
			branchCompany.setAttachment(addBranchCompany.getAttachment());
			branchCompany.setMerchantNo(addBranchCompany.getMerchantNo());
			if(StringUtils.isNotEmpty(addBranchCompany.getMerchantName())) {
				branchCompany.setMerchantName(addBranchCompany.getMerchantName());	
			} else {
				FindMerchantDto findMerchantDto = new FindMerchantDto();
				findMerchantDto.setMerchantNo(addBranchCompany.getMerchantNo());
				Merchant merchant = merchantDao.selectMerchant(findMerchantDto);
				branchCompany.setMerchantName(merchant.getMerchantName());	
			}
			branchCompany.setRegisterTime(new Date());
			branchCompany.setCreateDate(branchCompany.getRegisterTime());
			branchCompany.setUpdateDate(branchCompany.getRegisterTime());
			branchCompany.setRemark(addBranchCompany.getRemark());
			branchCompany.setRemark2(addBranchCompany.getRemark2());
			branchCompany.setRemark3(addBranchCompany.getRemark3());
			branchCompany.setRemark4(addBranchCompany.getRemark4());
			branchCompanyDao.insert(branchCompany);
			
			AddBranchCompanyReturn addBranchCompanyReturn = new AddBranchCompanyReturn();
			addBranchCompanyReturn.setCompanyNo(branchCompany.getCompanyNo());
			logger.debug("addBranchCompany(AddBranchCompany) - end - return value={}", addBranchCompanyReturn); 
			return addBranchCompanyReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增分公司信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_ADD_ERROR,"新增分公司信息错误！",e);
		}
	}
	
	@Override
	public DelBranchCompanyReturn delBranchCompany(DelBranchCompany delBranchCompany) throws TsfaServiceException {
		logger.debug("delBranchCompany(DelBranchCompany delBranchCompany={}) - start", delBranchCompany); 

		AssertUtils.notNull(delBranchCompany);
		AssertUtils.notNull(delBranchCompany.getCode(),"Code不能为空！");
		try {
			branchCompanyDao.deleteByPrimaryKey(delBranchCompany.getCode());
			DelBranchCompanyReturn delBranchCompanyReturn  = new DelBranchCompanyReturn();

			logger.debug("delBranchCompany(DelBranchCompany) - end - return value={}", delBranchCompanyReturn); 
			return delBranchCompanyReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除分公司信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_DEL_ERROR,"删除分公司信息错误！",e);
		}
	}

	@Override
	public UpdateBranchCompanyReturn updateBranchCompany(UpdateBranchCompany updateBranchCompany) throws TsfaServiceException {
		logger.debug("updateBranchCompany(UpdateBranchCompany updateBranchCompany={}) - start", updateBranchCompany); 

		AssertUtils.notNull(updateBranchCompany);
		AssertUtils.notAllNullAndEmpty(updateBranchCompany.getCode(), updateBranchCompany.getCompanyNo(), "Code和分公司编号不能同时为空");
		try {
			BranchCompany branchCompany = new BranchCompany();
			//update数据录入
			branchCompany.setCode(updateBranchCompany.getCode());
			branchCompany.setCompanyNo(updateBranchCompany.getCompanyNo());
			branchCompany.setDealerCode(updateBranchCompany.getDealerCode());
			branchCompany.setCompanyName(updateBranchCompany.getCompanyName());
			branchCompany.setStatus(updateBranchCompany.getStatus());
			if(updateBranchCompany.getCompanyType() != null){
				branchCompany.setCompanyType(updateBranchCompany.getCompanyType().getCode());
			}
			branchCompany.setUpdateDate(new Date());
			branchCompany.setRemark(updateBranchCompany.getRemark());
			branchCompany.setRemark2(updateBranchCompany.getRemark2());
			branchCompany.setRemark3(updateBranchCompany.getRemark3());
			branchCompany.setRemark4(updateBranchCompany.getRemark4());
			
			branchCompany.setBusinessLicense(updateBranchCompany.getBusinessLicense());
			branchCompany.setLegalPersonName(updateBranchCompany.getLegalPersonName());
			branchCompany.setLegalPersonId(updateBranchCompany.getLegalPersonId());
			branchCompany.setLegalPersonCardFront(updateBranchCompany.getLegalPersonCardFront());
			branchCompany.setLegalPersonCardReverse(updateBranchCompany.getLegalPersonCardReverse());
			branchCompany.setBusinessPerson(updateBranchCompany.getBusinessPerson());
			
			branchCompany.setDealerResponsiblePerson(updateBranchCompany.getDealerResponsiblePerson());
			branchCompany.setDealerResponsibleMobile(updateBranchCompany.getDealerResponsibleMobile());
			branchCompany.setProvinceCode(updateBranchCompany.getProvinceCode());
			branchCompany.setProvinceName(updateBranchCompany.getProvinceName());
			branchCompany.setCityCode(updateBranchCompany.getCityCode());
			branchCompany.setCityName(updateBranchCompany.getCityName());
			
			AssertUtils.notUpdateMoreThanOne(branchCompanyDao.updateByPrimaryKeySelective(branchCompany));
			UpdateBranchCompanyReturn updateBranchCompanyReturn = new UpdateBranchCompanyReturn();
			
			if (StringUtils.isNotEmpty(updateBranchCompany.getCompanyName())) {//修改经销商名称，同步账户表中的经销商名称
			    UpdateCompanyBankAccount updateCompanyBankAccount = new UpdateCompanyBankAccount();
			    updateCompanyBankAccount.setCompanyNo(updateBranchCompany.getCompanyNo());
			    updateCompanyBankAccount.setCompanyName(updateBranchCompany.getCompanyName());
                companyBankAccountService.updateCompanyBankAccountByCompanyNo(updateCompanyBankAccount);
            }

			logger.debug("updateBranchCompany(UpdateBranchCompany) - end - return value={}", updateBranchCompanyReturn); 
			return updateBranchCompanyReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("分公司信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_UPDATE_ERROR,"分公司信息更新信息错误！",e);
		}
	}

	@Override
	public FindBranchCompanyReturn findBranchCompany(FindBranchCompany findBranchCompany) throws TsfaServiceException {
		logger.debug("findBranchCompany(FindBranchCompany findBranchCompany={}) - start", findBranchCompany); 

		AssertUtils.notNull(findBranchCompany);
		AssertUtils.notAllNull(findBranchCompany.getCode(),"Code不能为空");
		try {
			BranchCompany branchCompany = branchCompanyDao.selectByPrimaryKey(findBranchCompany.getCode());
			if(branchCompany == null){
				throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_NOT_EXIST_ERROR,"分公司信息不存在");
			}
			FindBranchCompanyReturn findBranchCompanyReturn = new FindBranchCompanyReturn();
			//find数据录入
			findBranchCompanyReturn.setCode(branchCompany.getCode());
			findBranchCompanyReturn.setCompanyNo(branchCompany.getCompanyNo());
			findBranchCompanyReturn.setDealerCode(branchCompany.getDealerCode());
			findBranchCompanyReturn.setCompanyName(branchCompany.getCompanyName());
			findBranchCompanyReturn.setStatus(branchCompany.getStatus());
			findBranchCompanyReturn.setCompanyType(branchCompany.getCompanyType());
			findBranchCompanyReturn.setMerchantNo(branchCompany.getMerchantNo());
			findBranchCompanyReturn.setMerchantName(branchCompany.getMerchantName());
			findBranchCompanyReturn.setRegisterTime(branchCompany.getRegisterTime());
			findBranchCompanyReturn.setCreateDate(branchCompany.getCreateDate());
			findBranchCompanyReturn.setUpdateDate(branchCompany.getUpdateDate());
			findBranchCompanyReturn.setRemark(branchCompany.getRemark());
			findBranchCompanyReturn.setRemark2(branchCompany.getRemark2());
			findBranchCompanyReturn.setRemark3(branchCompany.getRemark3());
			findBranchCompanyReturn.setRemark4(branchCompany.getRemark4());
			
			logger.debug("findBranchCompany(FindBranchCompany) - end - return value={}", findBranchCompanyReturn); 
			return findBranchCompanyReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找分公司信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_FIND_ERROR,"查找分公司信息信息错误！",e);
		}
	}
	
	@Override
	public FindBranchCompanyReturn findBranchCompanyByCompanyNo(FindBranchCompany findBranchCompany) throws TsfaServiceException {
		logger.debug("findBranchCompanyByCompanyNo(FindBranchCompany findBranchCompany={}) - start", findBranchCompany); 
		
		AssertUtils.notNull(findBranchCompany);
		AssertUtils.notNullAndEmpty(findBranchCompany.getCompanyNo(),"经销商编号不能为空");
		try {
			BranchCompany branchCompany = branchCompanyDao.selectByCompanyNo(findBranchCompany.getCompanyNo());
			if(branchCompany == null){
				return null;
			}
			FindBranchCompanyReturn findBranchCompanyReturn = new FindBranchCompanyReturn();
			//find数据录入
			findBranchCompanyReturn.setCode(branchCompany.getCode());
			findBranchCompanyReturn.setCompanyNo(branchCompany.getCompanyNo());
			findBranchCompanyReturn.setDealerCode(branchCompany.getDealerCode());
			findBranchCompanyReturn.setCompanyName(branchCompany.getCompanyName());
			findBranchCompanyReturn.setStatus(branchCompany.getStatus());
			findBranchCompanyReturn.setCompanyType(branchCompany.getCompanyType());
			findBranchCompanyReturn.setMerchantNo(branchCompany.getMerchantNo());
			findBranchCompanyReturn.setMerchantName(branchCompany.getMerchantName());
			findBranchCompanyReturn.setRegisterTime(branchCompany.getRegisterTime());
			findBranchCompanyReturn.setCreateDate(branchCompany.getCreateDate());
			findBranchCompanyReturn.setUpdateDate(branchCompany.getUpdateDate());
			findBranchCompanyReturn.setRemark(branchCompany.getRemark());
			findBranchCompanyReturn.setRemark2(branchCompany.getRemark2());
			findBranchCompanyReturn.setRemark3(branchCompany.getRemark3());
			findBranchCompanyReturn.setRemark4(branchCompany.getRemark4());
			findBranchCompanyReturn.setProvinceNo(branchCompany.getProvinceCode());
			findBranchCompanyReturn.setProvinceName(branchCompany.getProvinceName());
			findBranchCompanyReturn.setCityName(branchCompany.getCityName());
			
			logger.debug("findBranchCompany(FindBranchCompany) - end - return value={}", findBranchCompanyReturn); 
			return findBranchCompanyReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找分公司信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_FIND_ERROR,"查找分公司信息信息错误！",e);
		}
	}

	@Override
	public Page<FindBranchCompanyPageReturn> findBranchCompanyPage(FindBranchCompanyPage findBranchCompanyPage) throws TsfaServiceException {
		logger.debug("findBranchCompanyPage(FindBranchCompanyPage findBranchCompanyPage={}) - start", findBranchCompanyPage); 

		AssertUtils.notNull(findBranchCompanyPage);
		List<FindBranchCompanyPageReturn> returnList = null;
		int count = 0;
		try {
			count = branchCompanyDao.findBranchCompanyPageCount(findBranchCompanyPage);
			if(count > 0) {
				returnList = branchCompanyDao.findBranchCompanyPage(findBranchCompanyPage);
			}
		}  catch (Exception e) {
			logger.error("分公司信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_FIND_PAGE_ERROR,"分公司信息不存在错误.！",e);
		}
		Page<FindBranchCompanyPageReturn> returnPage = new Page<FindBranchCompanyPageReturn>(returnList, count, findBranchCompanyPage);

		logger.debug("findBranchCompanyPage(FindBranchCompanyPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

	/**
	 * 
	 *
	 * 方法说明：查询商户下所有分公司
	 *
	 * @param merchantNo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年5月1日
	 *
	 */
	@Override
	public List<FindBranchCompanyReturn> findBranchCompanyByMerchantNo(String merchantNo) throws TsfaServiceException {
		logger.debug("findBranchCompanyByMerchantNo(String merchantNo={}) - start", merchantNo); 
		
		AssertUtils.notNullAndEmpty(merchantNo, "商户编号不能为空");
		List<FindBranchCompanyReturn> returnList = null;
		try {
			returnList = branchCompanyDao.findBranchCompanyByMerchantNo(merchantNo);
		}  catch (Exception e) {
			logger.error("查询商户下所有分公司错误",e);
			throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_FIND_PAGE_ERROR,"查询商户下所有分公司错误！",e);
		}
		
		logger.debug("findBranchCompanyByMerchantNo(String) - end - return value={}", returnList); 
		return returnList;
	} 
	
	@Override
    public FindBranchCompanyDetailReturn findBranchCompanyDetail(FindBranchCompany findBranchCompany) throws TsfaServiceException {
        logger.debug("findBranchCompanyDetail(FindBranchCompany findBranchCompany={}) - start", findBranchCompany); 

        AssertUtils.notNull(findBranchCompany);
        AssertUtils.notAllNull(findBranchCompany.getCode(),"Code不能为空");
        try {
            FindBranchCompanyDetailReturn findBranchCompanyDetailReturn = branchCompanyDao.findBranchCompanyDetail(findBranchCompany.getCode());
            if(findBranchCompanyDetailReturn == null){
                throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_NOT_EXIST_ERROR,"分公司信息不存在");
            }
            
            logger.debug("findBranchCompanyDetail(FindBranchCompany) - end - return value={}", findBranchCompanyDetailReturn); 
            return findBranchCompanyDetailReturn;
        } catch (Exception e) {
            logger.error("查找分公司详情错误！",e);
            throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_FIND_ERROR,"查找分公司信息信息错误！",e);
        }
    }

    @Override
    public int findCountByDealerCode(String dealerCode) throws TsfaServiceException {
        logger.debug("findCountByDealerCode(String dealerCode={}) - start", dealerCode); 

        AssertUtils.notAllNull(dealerCode,"经销商代码不能为空");
        try {
            int count = branchCompanyDao.findCountByDealerCode(dealerCode);

            logger.debug("findCountByDealerCode(String) - end - return value={}", count); 
            return count;
        } catch (Exception e) {
            logger.error("根据经销商代码查询数量错误！",e);
            throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_FIND_ERROR,"根据经销商代码查询数量错误！",e);
        }
    }

    /**
     * 
     *
     * 方法说明：根据经销商代码查询经销商列表
     *
     * @param dealerCode
     * @return
     * @throws TsfaServiceException
     *
     * @author 曾垂瑜 CreateDate: 2018年6月20日
     *
     */
	@Override
	public List<FindBranchCompanyReturn> findBranchCompanyByDealerCode(String dealerCode) throws TsfaServiceException {
		logger.debug("findBranchCompanyByDealerCode(String dealerCode={}) - start", dealerCode); 

        AssertUtils.notAllNull(dealerCode,"经销商代码不能为空");
        try {
        	List<FindBranchCompanyReturn> companyList = branchCompanyDao.findBranchCompanyByDealerCode(dealerCode);
            
            logger.debug("findBranchCompanyByDealerCode(String) - end - return value={}", companyList); 
            return companyList;
        } catch (Exception e) {
            logger.error("根据经销商代码查询经销商列表错误！",e);
            throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_FIND_ERROR,"根据经销商代码查询经销商列表错误！",e);
        }
	}

    @Override
    public List<FindBranchCompanyReturn> findBranchCompanySelective(FindBranchCompanyPage findBranchCompanyPage) throws TsfaServiceException {
        try {
            List<FindBranchCompanyReturn> companyList = branchCompanyDao.findBranchCompanySelective(findBranchCompanyPage);
            
            return companyList;
        } catch (Exception e) {
            logger.error("查询所有经销商列表错误！",e);
            throw new TsfaServiceException(ErrorCode.BRANCH_COMPANY_FIND_ERROR,"查询所有经销商列表错误！",e);
        }
    }
}
