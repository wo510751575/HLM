package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.lj.base.core.encryption.MD5;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.enums.BankCode;
//import com.lj.business.mec.common.Constant;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IDealerApplyDao;
import com.lj.business.member.dao.IDealerApplyShopDao;
import com.lj.business.member.domain.DealerApply;
import com.lj.business.member.domain.DealerApplyShop;
import com.lj.business.member.dto.AddGuidMember;
import com.lj.business.member.dto.AddManagerMember;
import com.lj.business.member.dto.AddShop;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.company.AddBranchCompany;
import com.lj.business.member.dto.company.AddBranchCompanyReturn;
import com.lj.business.member.dto.company.AddCompanyBankAccount;
import com.lj.business.member.dto.company.AddDealerApply;
import com.lj.business.member.dto.company.AddDealerApplyReturn;
import com.lj.business.member.dto.company.AddDealerApplyShop;
import com.lj.business.member.dto.company.DealerApplyAudit;
import com.lj.business.member.dto.company.DealerApplyAuditReturn;
import com.lj.business.member.dto.company.DealerApplyShopDto;
import com.lj.business.member.dto.company.DelDealerApply;
import com.lj.business.member.dto.company.DelDealerApplyReturn;
import com.lj.business.member.dto.company.FindDealerApply;
import com.lj.business.member.dto.company.FindDealerApplyDetailReturn;
import com.lj.business.member.dto.company.FindDealerApplyPage;
import com.lj.business.member.dto.company.FindDealerApplyPageReturn;
import com.lj.business.member.dto.company.FindDealerApplyReturn;
import com.lj.business.member.dto.company.UpdateDealerApply;
import com.lj.business.member.dto.company.UpdateDealerApplyReturn;
import com.lj.business.member.emus.CompanyType;
import com.lj.business.member.emus.DealerApplyStatus;
import com.lj.business.member.emus.Gender;
import com.lj.business.member.emus.MecShopType;
import com.lj.business.member.emus.MemberStatus;
import com.lj.business.member.emus.MemberType;
import com.lj.business.member.emus.ShopStatus;
import com.lj.business.member.service.IBranchCompanyService;
import com.lj.business.member.service.ICompanyBankAccountService;
import com.lj.business.member.service.IDealerApplyService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IMerchantService;
//import com.lj.business.sap.dto.mec.FindSapShopCommonPage;
//import com.lj.business.sap.dto.mec.SapShopCommonDto;
//import com.lj.business.sap.emus.DealerTrueEmus;
//import com.lj.business.sap.service.mec.ISapShopCommonService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.AreaHessianService;
import com.lj.oms.service.OfficeHessianService;

/**
 * 
 * 
 * 类说明：经销商申请实现类
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
public class DealerApplyServiceImpl implements IDealerApplyService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(DealerApplyServiceImpl.class);
	
	@Resource
	private IDealerApplyDao dealerApplyDao;
	@Resource
    private IDealerApplyShopDao dealerApplyShopDao;
	@Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
    private IBranchCompanyService branchCompanyService;
	@Resource
	private ICompanyBankAccountService companyBankAccountService;
//	@Resource
//	private IShopService shopService;
	@Resource
	private IManagerMemberService managerMemberService;
	@Resource
    private IMerchantService merchantService;
	@Resource
    private OfficeHessianService officeHessianService;
//	@Autowired
//    private ISapShopCommonService sapShopCommonService;     //SAP终端公共信息服务 
	@Resource
    private AreaHessianService areaHessianService;
	@Resource
    private IGuidMemberService guidMemberService;         //导购
	
	@Override
	public AddDealerApplyReturn addDealerApply(AddDealerApply addDealerApply) throws TsfaServiceException {
		logger.debug("addDealerApply(AddDealerApply addDealerApply={}) - start", addDealerApply);

		AssertUtils.notNull(addDealerApply);
		AssertUtils.notNullAndEmpty(addDealerApply.getDealerCode(), "经销商代码不能为空");
		AssertUtils.notNullAndEmpty(addDealerApply.getDealerName(), "经销商名称不能为空");
		
		try {
			DealerApply dealerApply = new DealerApply();
			
			//1.添加经销商申请数据
			BeanUtils.copyProperties(addDealerApply, dealerApply);
			
			dealerApply.setCode(GUID.generateByUUID());
			dealerApply.setDealerNo(GUID.generateByUUID());
			dealerApply.setApplyStatus((byte)(DealerApplyStatus.APPLY.getCode().intValue()));
			dealerApply.setApplyTime(new Date());
			dealerApply.setCreateDate(dealerApply.getApplyTime());
			dealerApply.setUpdateDate(dealerApply.getApplyTime());
			
			dealerApplyDao.insert(dealerApply);
			
			//2.添加终端
			List<AddDealerApplyShop> shopList = addDealerApply.getShopList();
			if (CollectionUtils.isNotEmpty(shopList)) {
			    DealerApplyShop dealerApplyShop = new DealerApplyShop();
			    for (AddDealerApplyShop addDealerApplyShop : shopList) {
			        //经销商终端申请数据录入
		            BeanUtils.copyProperties(addDealerApplyShop, dealerApplyShop);
		            
		            dealerApplyShop.setCode(GUID.generateByUUID());
//		            dealerApplyShop.setShopNo(GUID.generateByUUID());
		            dealerApplyShop.setDealerNo(dealerApply.getDealerNo());
		            dealerApplyShop.setCreateDate(dealerApply.getApplyTime());
		            dealerApplyShop.setUpdateDate(dealerApply.getApplyTime());
		            
		            dealerApplyShopDao.insert(dealerApplyShop);
                }
            }
			
			//3.return
			AddDealerApplyReturn addDealerApplyReturn = new AddDealerApplyReturn();
			
			logger.debug("addDealerApply(AddDealerApply) - end - return value={}", addDealerApplyReturn);
			return addDealerApplyReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("新增经销商申请信息错误！",e);
			throw new TsfaServiceException(ErrorCode.DEALER_APPLY_ADD_ERROR,"新增经销商申请信息错误！",e);
		}
	}
	
	@Override
	public DelDealerApplyReturn delDealerApply(DelDealerApply delDealerApply) throws TsfaServiceException {
		logger.debug("delDealerApply(DelDealerApply delDealerApply={}) - start", delDealerApply); 

		AssertUtils.notNull(delDealerApply);
		AssertUtils.notNullAndEmpty(delDealerApply.getCode(),"Code不能为空！");
		try {
			dealerApplyDao.deleteByPrimaryKey(delDealerApply.getCode());
			DelDealerApplyReturn delDealerApplyReturn  = new DelDealerApplyReturn();

			logger.debug("delDealerApply(DelDealerApply) - end - return value={}", delDealerApplyReturn); 
			return delDealerApplyReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除经销商申请信息错误！",e);
			throw new TsfaServiceException(ErrorCode.DEALER_APPLY_DEL_ERROR,"删除经销商申请信息错误！",e);
		}
	}

	@Override
	public UpdateDealerApplyReturn updateDealerApply(UpdateDealerApply updateDealerApply) throws TsfaServiceException {
		logger.debug("updateDealerApply(UpdateDealerApply updateDealerApply={}) - start", updateDealerApply); 

		AssertUtils.notNull(updateDealerApply);
		AssertUtils.notNullAndEmpty(updateDealerApply.getCode(),"Code不能为空");
		try {
			DealerApply dealerApply = new DealerApply();
			//update数据录入
			dealerApply.setCode(updateDealerApply.getCode());
			dealerApply.setDealerNo(updateDealerApply.getDealerNo());
			dealerApply.setDealerCode(updateDealerApply.getDealerCode());
			dealerApply.setDealerName(updateDealerApply.getDealerName());
			if(updateDealerApply.getApplyStatus() != null) {
				dealerApply.setApplyStatus(updateDealerApply.getApplyStatus());
			}
			
			dealerApply.setProvinceCode(updateDealerApply.getProvinceCode());
			dealerApply.setProvinceName(updateDealerApply.getProvinceName());
			dealerApply.setCityCode(updateDealerApply.getCityCode());
			dealerApply.setCityName(updateDealerApply.getCityName());
			
			dealerApply.setBusinessLicense(updateDealerApply.getBusinessLicense());
			dealerApply.setLegalPersonName(updateDealerApply.getLegalPersonName());
			dealerApply.setLegalPersonId(updateDealerApply.getLegalPersonId());
			dealerApply.setLegalPersonCardFront(updateDealerApply.getLegalPersonCardFront());
			dealerApply.setLegalPersonCardReverse(updateDealerApply.getLegalPersonCardReverse());
			
			dealerApply.setBusinessPerson(updateDealerApply.getBusinessPerson());
			dealerApply.setDealerResponsiblePerson(updateDealerApply.getDealerResponsiblePerson());
			dealerApply.setDealerResponsibleMobile(updateDealerApply.getDealerResponsibleMobile());
			
			dealerApply.setBankcardNo(updateDealerApply.getBankcardNo());
            dealerApply.setBankCode(updateDealerApply.getBankCode());
            dealerApply.setBankName(updateDealerApply.getBankName());
            dealerApply.setCustName(updateDealerApply.getCustName());
            dealerApply.setBankProvinceCode(updateDealerApply.getBankProvinceCode());
            dealerApply.setBankProvinceName(updateDealerApply.getBankProvinceName());
            dealerApply.setBankCityCode(updateDealerApply.getBankCityCode());
            dealerApply.setBankCityName(updateDealerApply.getBankCityName());
			dealerApply.setBranch(updateDealerApply.getBranch());
			
			//注意：审核时需提交审核人和审核时间
			dealerApply.setAuditNo(updateDealerApply.getAuditNo());
			dealerApply.setAuditName(updateDealerApply.getAuditName());
			dealerApply.setAuditTime(updateDealerApply.getAuditTime());
			
			dealerApply.setUpdateDate(new Date());
			dealerApply.setRemark(updateDealerApply.getRemark());
			dealerApply.setRemark2(updateDealerApply.getRemark2());
			dealerApply.setRemark3(updateDealerApply.getRemark3());
			dealerApply.setRemark4(updateDealerApply.getRemark4());
			AssertUtils.notUpdateMoreThanOne(dealerApplyDao.updateByPrimaryKeySelective(dealerApply));
			UpdateDealerApplyReturn updateDealerApplyReturn = new UpdateDealerApplyReturn();

			logger.debug("updateDealerApply(UpdateDealerApply) - end - return value={}", updateDealerApplyReturn); 
			return updateDealerApplyReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("经销商申请信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.DEALER_APPLY_UPDATE_ERROR,"经销商申请信息更新信息错误！",e);
		}
	}

	@Override
	public FindDealerApplyReturn findDealerApply(FindDealerApply findDealerApply) throws TsfaServiceException {
		logger.debug("findDealerApply(FindDealerApply findDealerApply={}) - start", findDealerApply); 

		AssertUtils.notNull(findDealerApply);
		AssertUtils.notNullAndEmpty(findDealerApply.getCode(),"Code不能为空");
		try {
			DealerApply dealerApply = dealerApplyDao.selectByPrimaryKey(findDealerApply.getCode());
			if(dealerApply == null){
				throw new TsfaServiceException(ErrorCode.DEALER_APPLY_NOT_EXIST_ERROR,"经销商申请信息不存在");
			}
			FindDealerApplyReturn findDealerApplyReturn = new FindDealerApplyReturn();
			//find数据录入
			findDealerApplyReturn.setCode(dealerApply.getCode());
			findDealerApplyReturn.setDealerNo(dealerApply.getDealerNo());
			findDealerApplyReturn.setDealerCode(dealerApply.getDealerCode());
			findDealerApplyReturn.setDealerName(dealerApply.getDealerName());
			findDealerApplyReturn.setApplyStatus(dealerApply.getApplyStatus());
            
            findDealerApplyReturn.setProvinceCode(dealerApply.getProvinceCode());
            findDealerApplyReturn.setProvinceName(dealerApply.getProvinceName());
            findDealerApplyReturn.setCityCode(dealerApply.getCityCode());
            findDealerApplyReturn.setCityName(dealerApply.getCityName());
            
            findDealerApplyReturn.setBusinessLicense(dealerApply.getBusinessLicense());
            findDealerApplyReturn.setLegalPersonName(dealerApply.getLegalPersonName());
            findDealerApplyReturn.setLegalPersonId(dealerApply.getLegalPersonId());
            findDealerApplyReturn.setLegalPersonCardFront(dealerApply.getLegalPersonCardFront());
            findDealerApplyReturn.setLegalPersonCardReverse(dealerApply.getLegalPersonCardReverse());
            
            findDealerApplyReturn.setBusinessPerson(dealerApply.getBusinessPerson());
            findDealerApplyReturn.setDealerResponsiblePerson(dealerApply.getDealerResponsiblePerson());
            findDealerApplyReturn.setDealerResponsibleMobile(dealerApply.getDealerResponsibleMobile());
            
            findDealerApplyReturn.setBankcardNo(dealerApply.getBankcardNo());
            findDealerApplyReturn.setBankCode(dealerApply.getBankCode());
            findDealerApplyReturn.setBankName(dealerApply.getBankName());
            findDealerApplyReturn.setCustName(dealerApply.getCustName());
            findDealerApplyReturn.setBankProvinceCode(dealerApply.getBankProvinceCode());
            findDealerApplyReturn.setBankProvinceName(dealerApply.getBankProvinceName());
            findDealerApplyReturn.setBankCityCode(dealerApply.getBankCityCode());
            findDealerApplyReturn.setBankCityName(dealerApply.getBankCityName());
            findDealerApplyReturn.setBranch(dealerApply.getBranch());
            
            findDealerApplyReturn.setAuditNo(dealerApply.getAuditNo());
            findDealerApplyReturn.setAuditName(dealerApply.getAuditName());
            findDealerApplyReturn.setAuditTime(dealerApply.getAuditTime());
            
            findDealerApplyReturn.setUpdateDate(new Date());
            findDealerApplyReturn.setRemark(dealerApply.getRemark());
            findDealerApplyReturn.setRemark2(dealerApply.getRemark2());
            findDealerApplyReturn.setRemark3(dealerApply.getRemark3());
            findDealerApplyReturn.setRemark4(dealerApply.getRemark4());
			
			logger.debug("findDealerApply(FindDealerApply) - end - return value={}", findDealerApplyReturn); 
			return findDealerApplyReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找经销商申请信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.DEALER_APPLY_FIND_ERROR,"查找经销商申请信息信息错误！",e);
		}
	}

	@Override
	public Page<FindDealerApplyPageReturn> findDealerApplyPage(FindDealerApplyPage findDealerApplyPage) throws TsfaServiceException {
		logger.debug("findDealerApplyPage(FindDealerApplyPage findDealerApplyPage={}) - start", findDealerApplyPage); 

		AssertUtils.notNull(findDealerApplyPage);
		List<FindDealerApplyPageReturn> returnList = null;
		int count = 0;
		try {
			count = dealerApplyDao.findDealerApplyPageCount(findDealerApplyPage);
			if(count > 0) {
				returnList = dealerApplyDao.findDealerApplyPage(findDealerApplyPage);
			}
		}  catch (Exception e) {
			logger.error("经销商申请信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.DEALER_APPLY_FIND_PAGE_ERROR,"经销商申请信息不存在错误.！",e);
		}
		Page<FindDealerApplyPageReturn> returnPage = new Page<FindDealerApplyPageReturn>(returnList, count, findDealerApplyPage);

		logger.debug("findDealerApplyPage(FindDealerApplyPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

//    @Override
//    public List<Bank> getBankCodes() {
//        logger.debug("getBankCodes() - start"); 
//
//        Map<String, String> bankMap = localCacheSystemParams.getSystemParamGroup(SystemAliasName.mec.name(), Constant.MEC_BANK_GROUP);
//        List<Bank> bankList = Lists.newArrayListWithExpectedSize(bankMap.size());
//        
//        Bank bank = null;
//        for (Map.Entry<String, String> bankEntry : bankMap.entrySet()) {
//            bank = new Bank();
//            bank.setBankCode(bankEntry.getKey());
//            bank.setBankName(bankEntry.getValue());
//            
//            bankList.add(bank);
//        }
//        
//        logger.debug("getBankCodes() - end - return value={}", bankList); 
//        return bankList;
//    }

    @Override
    public FindDealerApplyDetailReturn findDealerApplyDetail(String dealerApplyCode) throws TsfaServiceException {
        logger.debug("findDealerApplyDetail(String dealerApplyCode={}) - start", dealerApplyCode); 

        AssertUtils.notNullAndEmpty(dealerApplyCode);
        FindDealerApplyDetailReturn findDealerApplyDetailReturn = null;
        try {
            findDealerApplyDetailReturn = dealerApplyDao.findDealerApplyDetail(dealerApplyCode);
            if (findDealerApplyDetailReturn != null) {
                String attachment = findDealerApplyDetailReturn.getAttachment();
                if (StringUtils.isNotEmpty(attachment) && 
                        (attachment.endsWith("jpg") || attachment.endsWith("jpeg") || attachment.endsWith("png") || attachment.endsWith("gif"))) {
                    findDealerApplyDetailReturn.setAttachmentIsImg(true);
                } else {
                    findDealerApplyDetailReturn.setAttachmentIsImg(false);
                }
            }
        }  catch (Exception e) {
            logger.error("经销商申请信息不存在错误",e);
            throw new TsfaServiceException(ErrorCode.DEALER_APPLY_FIND_PAGE_ERROR,"经销商申请信息不存在错误.！",e);
        }

        logger.debug("findDealerApplyDetail(String) - end - return value={}", findDealerApplyDetailReturn); 
        return findDealerApplyDetailReturn;
    }

    @Override
    public DealerApplyAuditReturn audit(DealerApplyAudit dealerApplyAudit) throws TsfaServiceException {
        logger.debug("audit(DealerApplyAudit dealerApplyAudit={}) - start", dealerApplyAudit); 

        AssertUtils.notNull(dealerApplyAudit);
        AssertUtils.notNullAndEmpty(dealerApplyAudit.getCode(), "经销商申请code不能为空");
        AssertUtils.notNullAndEmpty(dealerApplyAudit.getAuditResult(), "经销商审核意见不能为空");
        
        try {
            FindDealerApplyDetailReturn findDealerApplyDetail = findDealerApplyDetail(dealerApplyAudit.getCode());
            
            //校验经销商代码
            int countByDealerCode = branchCompanyService.findCountByDealerCode(findDealerApplyDetail.getDealerCode());
            if (countByDealerCode > 0) {
                throw new TsfaServiceException(ErrorCode.DEALER_APPLY_AUDIT_ERROR, "该经销商代码已存在！");
            }
            
            Date now = new Date();
            
            UpdateDealerApply updateDealerApply = new UpdateDealerApply();
            updateDealerApply.setCode(dealerApplyAudit.getCode());
            updateDealerApply.setRemark(dealerApplyAudit.getRemark());
            updateDealerApply.setAuditNo(dealerApplyAudit.getAuditNo());
            updateDealerApply.setAuditName(dealerApplyAudit.getAuditName());
            updateDealerApply.setAuditTime(now);
            
            if (!dealerApplyAudit.getAuditResult()) {//审核不通过
                updateDealerApply.setApplyStatus((byte)DealerApplyStatus.REJECT.getCode().intValue());
                updateDealerApply(updateDealerApply);
            } else {//审核通过
                updateDealerApply.setApplyStatus((byte)DealerApplyStatus.AUDIT.getCode().intValue());
                updateDealerApply(updateDealerApply);
                
                String companyNo = GUID.generateByUUID();
                //1.创建机构
                Office office = new Office();
                office.setAddress(findDealerApplyDetail.getProvinceName() + findDealerApplyDetail.getCityName());
                office.setCode(findDealerApplyDetail.getDealerCode());
                Area area = new Area();
                area.setId("1");
                office.setArea(area);
                
                User user = new User();
                user.setId(dealerApplyAudit.getAuditNo());
                user.setName(dealerApplyAudit.getAuditName());
                
                office.setCreateBy(user);
                office.setCreateDate(now);
                office.setCurrentUser(user);
                office.setDelFlag("0");
                office.setGrade("3");
                office.setId(companyNo);
                office.setIsNewRecord(true);
                office.setName(findDealerApplyDetail.getDealerName());
                office.setSort(30);
                office.setUseable("1");
                
                Office parent = new Office();
                parent.setId(findDealerApplyDetail.getMerchantNo());
                office.setParent(parent);
                
                office.setUpdateBy(user);
                office.setUpdateDate(now);
                
                officeHessianService.addOffice(office);
                
                //查询商户信息
                FindMerchantDto findMerchantDto = new FindMerchantDto();
                findMerchantDto.setMerchantNo(findDealerApplyDetail.getMerchantNo());
                FindMerchantReturnDto selectMerchant = merchantService.selectMerchant(findMerchantDto);
                
                //2.创建分公司
                AddBranchCompany addBranchCompany = new AddBranchCompany();
                
                addBranchCompany.setCompanyNo(companyNo);
                addBranchCompany.setDealerCode(findDealerApplyDetail.getDealerCode());
                addBranchCompany.setCompanyName(findDealerApplyDetail.getDealerName());
                addBranchCompany.setCompanyType(CompanyType.DEALER);
                
                addBranchCompany.setProvinceCode(findDealerApplyDetail.getProvinceCode());
                addBranchCompany.setProvinceName(findDealerApplyDetail.getProvinceName());
                addBranchCompany.setCityCode(findDealerApplyDetail.getCityCode());
                addBranchCompany.setCityName(findDealerApplyDetail.getCityName());
                addBranchCompany.setBusinessLicense(findDealerApplyDetail.getBusinessLicense());
                addBranchCompany.setLegalPersonName(findDealerApplyDetail.getLegalPersonName());
                addBranchCompany.setLegalPersonId(findDealerApplyDetail.getLegalPersonId());
                addBranchCompany.setLegalPersonCardFront(findDealerApplyDetail.getLegalPersonCardFront());
                addBranchCompany.setLegalPersonCardReverse(findDealerApplyDetail.getLegalPersonCardReverse());
                addBranchCompany.setBusinessPerson(findDealerApplyDetail.getBusinessPerson());
                addBranchCompany.setDealerResponsiblePerson(findDealerApplyDetail.getDealerResponsiblePerson());
                addBranchCompany.setDealerResponsibleMobile(findDealerApplyDetail.getDealerResponsibleMobile());
                addBranchCompany.setAttachment(findDealerApplyDetail.getAttachment());
                addBranchCompany.setMerchantNo(findDealerApplyDetail.getMerchantNo());
                addBranchCompany.setMerchantName(selectMerchant.getMerchantName());
                AddBranchCompanyReturn addBranchCompanyReturn = branchCompanyService.addBranchCompany(addBranchCompany);
                
                //3.创建分公司银行账户
                AddCompanyBankAccount addCompanyBankAccount = new AddCompanyBankAccount();
                addCompanyBankAccount.setCompanyNo(addBranchCompanyReturn.getCompanyNo());
                addCompanyBankAccount.setCompanyName(findDealerApplyDetail.getDealerName());
                addCompanyBankAccount.setBankcardNo(findDealerApplyDetail.getBankcardNo());
                addCompanyBankAccount.setBankCode(BankCode.valueOf(findDealerApplyDetail.getBankCode()));
                addCompanyBankAccount.setBranch(findDealerApplyDetail.getBranch());
                addCompanyBankAccount.setProvinceCode(findDealerApplyDetail.getBankProvinceCode());
                addCompanyBankAccount.setProvinceName(findDealerApplyDetail.getBankProvinceName());
                addCompanyBankAccount.setCityCode(findDealerApplyDetail.getBankCityCode());
                addCompanyBankAccount.setCityName(findDealerApplyDetail.getBankCityName());
                addCompanyBankAccount.setCustName(findDealerApplyDetail.getCustName());
                addCompanyBankAccount.setIsDefault(true);
                addCompanyBankAccount.setMerchantNo(findDealerApplyDetail.getMerchantNo());
                
                companyBankAccountService.addCompanyBankAccount(addCompanyBankAccount);
                
                //4.逐一创建终端和店长
                List<DealerApplyShopDto> shopList = findDealerApplyDetail.getShopList();
                for (DealerApplyShopDto dealerApplyShopDto : shopList) {
                    //校验终端代码已存在的情况
//                    int countByShopNoMerchant = shopService.findCountByShopNoMerchant(dealerApplyShopDto.getShopNoMerchant());
//                    if (countByShopNoMerchant > 0) {
//                        logger.debug("经销商审核，该终端代码已存在：[{}]", dealerApplyShopDto);
//                        throw new TsfaServiceException(ErrorCode.DEALER_APPLY_AUDIT_ERROR, "终端[" + dealerApplyShopDto.getShopName() + "]所属代码[" + dealerApplyShopDto.getShopNoMerchant() + "]已存在");
//                    }
                    
                    AddShop addShop = new AddShop();
                    addShop.setCompanyNo(addBranchCompanyReturn.getCompanyNo());
                    addShop.setCompanyName(findDealerApplyDetail.getDealerName());
                    addShop.setAddress(dealerApplyShopDto.getDetailAddr());
                    //拼接省市区和详细地址
                    addShop.setAddrInfo(dealerApplyShopDto.getProvinceName() + dealerApplyShopDto.getCityName() + dealerApplyShopDto.getAreaName() + dealerApplyShopDto.getDetailAddr());
                    //大区，如华南
                    List<String> list = areaHessianService.getAreaNameByProvinceCode(dealerApplyShopDto.getProvinceCode());
                    if (CollectionUtils.isNotEmpty(list)) {
                        addShop.setAreaCode(list.get(0));
                        addShop.setAreaName(list.get(1));
                    }
                    
                    addShop.setBizScope(dealerApplyShopDto.getBizScope());
                    addShop.setCityAreaCode(dealerApplyShopDto.getAreaCode());
                    addShop.setCityCode(dealerApplyShopDto.getCityCode());
                    addShop.setCreateDate(now);
                    addShop.setCreateId(dealerApplyAudit.getAuditNo());
                    addShop.setLogoAddr(dealerApplyShopDto.getLogoAddr());
//                    addShop.setMecShopCategory();
                    //设置销售渠道
                    String mecShopChannel = extractShopChannel(dealerApplyShopDto.getBizScope(), dealerApplyAudit.getMecShopChannelMap());
                    addShop.setMecShopChannel(mecShopChannel);
//                    this.setMecShopOrg(addShop, dealerApplyShopDto.getShopNoMerchant());
//                    addShop.setMecShopOrg(mecShopOrg);
                    addShop.setMecShopType(MecShopType.DEALER.name());
                    addShop.setMemberNameMerchant(selectMerchant.getMerchantName());
                    addShop.setMemberNoMerchant(findDealerApplyDetail.getMerchantNo());
                    addShop.setMobile(dealerApplyShopDto.getShopManagerMobile());
                    addShop.setTelephone(dealerApplyShopDto.getShopManagerMobile());
//                    addShop.setOpenDate(now);
                    addShop.setProvinceCode(dealerApplyShopDto.getProvinceCode());
//                    addShop.setShopName(dealerApplyShopDto.getShopName());
//                    addShop.setShopNo(GUID.generateByUUID());
                    addShop.setShopNoMerchant(dealerApplyShopDto.getShopNoMerchant());
                    addShop.setShopType(dealerApplyShopDto.getBizScope());
                    addShop.setStatus(ShopStatus.INDECORATION.name());
                    addShop.setRemark4("update");//此字段标识终端被分配给某个机构且是有效的
                    
//                    shopService.addShop(addShop);
                    
                    //建店长
                    AddManagerMember addManagerMember = new AddManagerMember();
                    addManagerMember.setMemberName(dealerApplyShopDto.getShopManagerName());
                    String shopManagerMobile = dealerApplyShopDto.getShopManagerMobile();
                    
                    addManagerMember.setPwd(MD5.encryptByMD5(shopManagerMobile.substring(shopManagerMobile.length() - 6)));//密码默认为手机号后六位
                    addManagerMember.setMobile(dealerApplyShopDto.getShopManagerMobile());
//                    addManagerMember.setMemberNoShop(addShop.getShopNo());
//                    addManagerMember.setMemberNameShop(addShop.getShopName());
                    addManagerMember.setMemberNoMerchant(selectMerchant.getMerchantNo());
                    addManagerMember.setMemberNameMerchant(selectMerchant.getMerchantName());
                    addManagerMember.setStatus(MemberStatus.NORMAL.toString());
                    addManagerMember.setSex(Gender.UNKNOWN.name());
                    addManagerMember.setWorkDate(now);
                    addManagerMember.setMemberNo(GUID.getPreUUID());
                    addManagerMember.setMemberType(MemberType.SHOP);
                    addManagerMember.setCreateId(dealerApplyAudit.getAuditNo());
                    addManagerMember.setUpdateId(dealerApplyAudit.getAuditNo());
                    addManagerMember.setUpdateDate(now);
                    if (CollectionUtils.isNotEmpty(list)) {
                        addManagerMember.setAreaCode(list.get(0));
                        addManagerMember.setAreaName(list.get(1));
                    }
                    managerMemberService.addManagerMember(addManagerMember);
                    
                    //建店长时，要在导购表也生成一份记录
                    AddGuidMember addGuidMember = new AddGuidMember();
                    addGuidMember.setMemberName(dealerApplyShopDto.getShopManagerName());
                    addGuidMember.setPwd(MD5.encryptByMD5(shopManagerMobile.substring(shopManagerMobile.length() - 6)));    //默认手机后6位
                    addGuidMember.setMobile(dealerApplyShopDto.getShopManagerMobile());
                    
                    addGuidMember.setMerchantNo(selectMerchant.getMerchantNo());
                    addGuidMember.setMerchantName(selectMerchant.getMerchantName());
                    
//                    addGuidMember.setShopNo(addShop.getShopNo());
//                    addGuidMember.setShopName(addShop.getShopName());
                     
                    if (CollectionUtils.isNotEmpty(list)) {
                        addGuidMember.setAreaCode(list.get(0));
                        addGuidMember.setAreaName(list.get(1));
                    }
                    
                    addGuidMember.setStatus(MemberStatus.NORMAL.toString());
                    
                    addGuidMember.setGender(Gender.UNKNOWN.name());
                    addGuidMember.setWorkDate(now);
                    addGuidMember.setMemberNo(GUID.getPreUUID());       
                    addGuidMember.setCreateId(updateDealerApply.getAuditNo());
                    addGuidMember.setUpdateId(updateDealerApply.getAuditNo());
                    
                    guidMemberService.addGuidMember(addGuidMember);
                }
            }
        } catch (TsfaServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("经销商申请审核错误",e);
            throw new TsfaServiceException(ErrorCode.DEALER_APPLY_AUDIT_ERROR,"经销商申请审核错误.！",e);
        }
        
        DealerApplyAuditReturn dealerApplyAuditReturn = new DealerApplyAuditReturn();

        logger.debug("audit(DealerApplyAudit) - end - return value={}", dealerApplyAuditReturn); 
        return dealerApplyAuditReturn;
    }
    
    private String extractShopChannel(String bizScope, Map<String, Object> mecShopChannelMap) {
        StringBuilder sb = new StringBuilder("");
        
        String[] bizArr = bizScope.split(",");
        for (String biz : bizArr) {
            if (mecShopChannelMap.containsKey(biz)) {
                sb.append(mecShopChannelMap.get(biz) + ",");
            }
        }
        //如果至少存在一个biz，删除尾部的逗号
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return sb.toString();
    }

    /**
     * 
     *
     * 方法说明：根据终端代码设置终端销售组织代码
     *
     * @param addShop
     * @param shopNoMerchant
     *
     * @author 许新龙 CreateDate: 2018年6月2日
     *
     */
//    private void setMecShopOrg(AddShop addShop, String shopNoMerchant) {
//        /**
//         * 获取直营店铺
//         * 20180602-改为获取经销商终端
//         */
//        FindSapShopCommonPage findSapShopCommonPage = new FindSapShopCommonPage();
//        SapShopCommonDto param = new SapShopCommonDto();
//        param.setDealerTrue(DealerTrueEmus.YES.getValue());//20180602-改为获取经销商终端
//        param.setWerks(shopNoMerchant);
//        findSapShopCommonPage.setParam(param);
//        List<SapShopCommonDto> commonDtos = sapShopCommonService.findSapShopCommons(findSapShopCommonPage);
//        
//        if (null != commonDtos && commonDtos.size() > 0) {
//            SapShopCommonDto sapShopCommonDto = commonDtos.get(0);
//            addShop.setMecShopOrg(sapShopCommonDto.getBukrs());
//        }
//    }
    
}
