package com.lj.business.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.textInfo.AddTextInfo;
import com.lj.business.cm.service.ITextInfoService;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IMerchantDao;
import com.lj.business.member.domain.Merchant;
import com.lj.business.member.dto.AddMerchant;
import com.lj.business.member.dto.AddMerchantReturn;
import com.lj.business.member.dto.AddPmType;
import com.lj.business.member.dto.DelMerchant;
import com.lj.business.member.dto.DelMerchantReturn;
import com.lj.business.member.dto.FindMerchant;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.dto.FindMerchantReturn;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.UpdateMerchant;
import com.lj.business.member.dto.UpdateMerchantReturn;
import com.lj.business.member.dto.integralSet.AddIntegralSet;
import com.lj.business.member.emus.HcPmTypeType;
import com.lj.business.member.emus.IntegralType;
import com.lj.business.member.service.IIntegralSetService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPmTypeService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 *         CreateDate: 2017-06-14
 */
@Service
public class MerchantServiceImpl implements IMerchantService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

	/** The merchant dao. */
	@Resource
	private IMerchantDao merchantDao;


	@Resource
	private ITextInfoService textInfoService;

	@Resource
	private IPmTypeService pmTypeService;

	@Resource
	private IIntegralSetService integralSetService;
	
//	@Resource
//	private IStListService stListService;

	
	@Cacheable(value="commonName")
    public String getMessage(String name) {
        System.out.println("=============Executing HelloServiceImpl" +
                        ".getHelloMessage(\"" + name + "\")");

        return "你好 " + name + "!";
    }
	
	//@Cacheable(value="commonStr", key="#name + 'test_py'")
	@Cacheable(value="commonStr")
    public String getMessageStr(String name) {
        System.out.println("=============Executing HelloServiceImpl" +
                        ".getMessageStr(\"" + name + "\")");

        return "你好 " + name + "! Str";
    }
	
	@Cacheable(value="common")
    public AddMerchant getMessageObject(AddMerchant addMerchant) {
        System.out.println("=============Executing HelloServiceImpl" +
                        ".getHelloMessage(\"" + addMerchant + "\")");

        AddMerchant checkAccountInfoReturn =  new AddMerchant();
        checkAccountInfoReturn.setAddress(addMerchant.getAddress());
        return checkAccountInfoReturn;
    }
	
	
	//@CacheEvict(value="commonStr", key="#name + 'test_py'")
	@CacheEvict(value="commonStr")
    public String resetMessageStr(String name) {
        System.out.println("=============Executing HelloServiceImpl" +
                        ".getMessageStr(\"" + name + "\")");

        return "你好 " + name + "! Str";
    }
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IMerchantService#addMerchant(com.lj.business
	 * .member.dto.AddMerchant)
	 */
	@Override
	public AddMerchantReturn addMerchant(AddMerchant addMerchant)
			throws TsfaServiceException {
		logger.debug("addMerchant(AddMerchant addMerchant={}) - start",addMerchant);
			AssertUtils.notNull(addMerchant);
			
			try {
				Merchant merchant = new Merchant();
				// add数据录入   
				merchant.setCode(GUID.generateCode());
				if(StringUtils.isEmpty(addMerchant.getMerchantNo()))//兼容OMS同步数据
					merchant.setMerchantNo(GUID.generateByUUID());
				else{
					FindMerchantDto findMerchantDto=new FindMerchantDto();
					findMerchantDto.setMerchantNo(addMerchant.getMerchantNo());
					Merchant merchanReturn = merchantDao.selectMerchant(findMerchantDto);
					// 排除同步重复商户
					if(merchanReturn != null){
						AddMerchantReturn addMerchantReturn = new AddMerchantReturn();
						return addMerchantReturn;
					}
					merchant.setMerchantNo(addMerchant.getMerchantNo());//兼容OMS同步数据
					
					
				}
				merchant.setMerchantName(addMerchant.getMerchantName());
				merchant.setStatus(addMerchant.getStatus());
				merchant.setAddress(addMerchant.getAddress());
				merchant.setEmail(addMerchant.getEmail());
				merchant.setBusinessType(addMerchant.getBusinessType());
				merchant.setLogoAddr(addMerchant.getLogoAddr());
				merchant.setWebsiteUrl(addMerchant.getWebsiteUrl());
				merchant.setTelephone(addMerchant.getTelephone());
				merchant.setBeginProbationTime(addMerchant.getBeginProbationTime());
				merchant.setEndProbationTime(addMerchant.getEndProbationTime());
				merchant.setProbationStatus(addMerchant.getProbationStatus());
				merchant.setCreateId(addMerchant.getCreateId());
				merchant.setRemark(addMerchant.getRemark());
				merchantDao.insert(merchant);
				
					logger.debug("邀约型商户初始化列表：");
					
					logger.debug("话术");
					initTextInfo(merchant);
					
					logger.debug("客户分类表（基础表）");
//					initPmType(merchant);
					
					logger.debug("积分设置");
					initIntegralSet(merchant);
					
					logger.debug("统计中心");
//					InitStListByMerchant initStListByMerchant = new InitStListByMerchant();
//					initStListByMerchant.setMerchantNo(merchant.getMerchantNo());
//					stListService.initStListByMerchant(initStListByMerchant);
				
				AddMerchantReturn addMerchantReturn = new AddMerchantReturn();
				logger.debug("addMerchant(AddMerchant) - end - return value={}",addMerchantReturn);
				return addMerchantReturn;
			} catch (TsfaServiceException e) {
				logger.error(e.getMessage(), e);
				throw e;
			} catch (Exception e) {
				logger.error("新增商户表信息错误！", e);
				throw new TsfaServiceException(ErrorCode.MERCHANT_ADD_ERROR,
						"新增商户表信息错误！", e);
			}
	}
	

	/**
	 * 
	 *
	 * 方法说明：积分设置
	 *
	 * @param merchant
	 *
	 * @author 彭阳 CreateDate: 2017年9月7日
	 *
	 */
	private void initIntegralSet(Merchant merchant) {
		try {
			IntegralType integralTypeAr [] = IntegralType.values();
			for (IntegralType integralType : integralTypeAr) {
				AddIntegralSet addIntegralSet = new AddIntegralSet();
				//add数据录入
				addIntegralSet.setMerchantNo(merchant.getMerchantNo());
				addIntegralSet.setMerchantName(merchant.getMerchantName());
//				addIntegralSet.setShopNo(null);
//				addIntegralSet.setShopName(null);
				addIntegralSet.setAreaCode(null);
				addIntegralSet.setAreaName(null);
				addIntegralSet.setIntegralType(integralType.toString());
				addIntegralSet.setIntegralName(integralType.getName());
				if(integralType.equals(IntegralType.COM_TASK)){
					addIntegralSet.setIntegralScore(1.0);
					addIntegralSet.setIntegralUp(25.0);
					addIntegralSet.setIntegralDown(null);
				}else if(integralType.equals(IntegralType.NEW)){
					addIntegralSet.setIntegralScore(null);
					addIntegralSet.setIntegralUp(40.0);
					addIntegralSet.setIntegralDown(null);
				}else if(integralType.equals(IntegralType.XIAO_SHOU)){
					addIntegralSet.setIntegralScore(null);
					addIntegralSet.setIntegralUp(60.0);
					addIntegralSet.setIntegralDown(null);
				}else if(integralType.equals(IntegralType.NOTICE)){
					addIntegralSet.setIntegralScore(1.0);
					addIntegralSet.setIntegralUp(40.0);
					addIntegralSet.setIntegralDown(null);
				}else if(integralType.equals(IntegralType.SOCIAL)){
					addIntegralSet.setIntegralScore(1.0);
					addIntegralSet.setIntegralUp(10.0);
					addIntegralSet.setIntegralDown(null);
				}else if(integralType.equals(IntegralType.ACTIVE)){
					addIntegralSet.setIntegralScore(1.0);
					addIntegralSet.setIntegralUp(1.0);
					addIntegralSet.setIntegralDown(null);
				}else if(integralType.equals(IntegralType.ASK)){
					addIntegralSet.setIntegralScore(1.0);
					addIntegralSet.setIntegralUp(1.0);
					addIntegralSet.setIntegralDown(null);
				}else if(integralType.equals(IntegralType.NEW_MET)){
					addIntegralSet.setIntegralScore(1.0);
					addIntegralSet.setIntegralUp(4.0);
					addIntegralSet.setIntegralDown(null);
				}else if(integralType.equals(IntegralType.SEND_MET)){
					addIntegralSet.setIntegralScore(1.0);
					addIntegralSet.setIntegralUp(5.0);
					addIntegralSet.setIntegralDown(null);
				}
				addIntegralSet.setStatus("Y");
				addIntegralSet.setUpdateId(null);
				addIntegralSet.setUpdateDate(null);
				addIntegralSet.setCreateId("system");
				integralSetService.addIntegralSet(addIntegralSet );
			}
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
		}
	}

	/*private void initPmType(Merchant merchant) {
		try {
			PmTypeType pmTypeAr [] = PmTypeType.values();
			for (PmTypeType pmTypeType : pmTypeAr) {
				AddPmType addPmType = new AddPmType();
				//add数据录入
				addPmType.setMerchantNo(merchant.getMerchantNo());
				addPmType.setMemberNo(null);
				addPmType.setMemberName(null);
				addPmType.setTypeName(pmTypeType.getName());
				addPmType.setPmTypeType(pmTypeType.toString());

				if(pmTypeType.equals(PmTypeType.URGENCY)){
					addPmType.setFreValue("0");
					addPmType.setSeq(10);
				}else if(pmTypeType.equals(PmTypeType.REPEAT)){
					addPmType.setFreValue("0");
					addPmType.setSeq(20);
				}else if(pmTypeType.equals(PmTypeType.INTENTION)){
					addPmType.setFreValue("0");
					addPmType.setSeq(40);
				}else if(pmTypeType.equals(PmTypeType.INTENTION_N)){
					addPmType.setFreValue("0");
					addPmType.setSeq(50);
				}else if(pmTypeType.equals(PmTypeType.OTHER)){
					addPmType.setFreValue("0");
					addPmType.setSeq(60);
				}else if(pmTypeType.equals(PmTypeType.SUCCESS)){
					addPmType.setFreValue(PublicConstants.FRE_VALUE);
					addPmType.setSeq(70);
				}else if(pmTypeType.equals(PmTypeType.GIVE_UP)){
					addPmType.setFreValue(PublicConstants.FRE_VALUE);
					addPmType.setSeq(80);
				}
				addPmType.setStatus("Y");
				addPmType.setCreateId(null);
				addPmType.setRemark(null);
				addPmType.setRemark2(null);
				addPmType.setRemark3(null);
				addPmType.setRemark4(null);
				pmTypeService.addPmType(addPmType);
			}
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
		}
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：美容美发默认分组
	 *
	 * @param merchant
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月22日
	 * @param flag 
	 *
	 */
	private void initPmTypeHc(Merchant merchant, String flag) {
		try {
			//如果需要初始化
			if("1".equals(flag)){
				HcPmTypeType pmTypeAr [] = HcPmTypeType.values();
				for (HcPmTypeType pmTypeType : pmTypeAr) {
					AddPmType addPmType = new AddPmType();
					//add数据录入
					addPmType.setMerchantNo(merchant.getMerchantNo());
					addPmType.setMemberNo(null);
					addPmType.setMemberName(null);
					addPmType.setTypeName(pmTypeType.getName());
					addPmType.setPmTypeType(pmTypeType.toString());
					addPmType.setFreValue("0");
					addPmType.setSeq(10 * (pmTypeType.ordinal() + 1));
					
					addPmType.setStatus("Y");
					addPmType.setCreateId(null);
					addPmType.setRemark(null);
					addPmType.setRemark2(null);
					addPmType.setRemark3(null);
					addPmType.setRemark4(null);
					pmTypeService.addPmType(addPmType);
				}
			}else{
				//否则初始化一个未分组客户
				AddPmType addPmType = new AddPmType();
				//add数据录入
				addPmType.setMerchantNo(merchant.getMerchantNo());
				addPmType.setMemberNo(null);
				addPmType.setMemberName(null);
				addPmType.setTypeName("未分组客户");
				addPmType.setPmTypeType("UNGROUP");
				addPmType.setFreValue("0");
				addPmType.setSeq(10);
				
				addPmType.setStatus("Y");
				addPmType.setCreateId(null);
				addPmType.setRemark(null);
				addPmType.setRemark2(null);
				addPmType.setRemark3(null);
				addPmType.setRemark4(null);
				pmTypeService.addPmType(addPmType);
			}
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
		}
	}

	private void initTextInfo(Merchant merchant) {
		try {
			AddTextInfo addTextInfo = new AddTextInfo();
			addTextInfo.setMerchantNo(merchant.getMerchantNo());
			addTextInfo.setMerchantName(merchant.getMerchantName());
			addTextInfo.setTextType("SHOP_WORK_ANALYZE");
			addTextInfo.setContent("销售就像谈恋爱，你不主动怎么可能成！");
			addTextInfo.setDimStart("0");
			addTextInfo.setDimEnd("0");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("SHOP_WORK_ANALYZE");
			addTextInfo.setContent("小主，还有一波工作等待您解决！");
			addTextInfo.setDimStart("1");
			addTextInfo.setDimEnd("10");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);


			addTextInfo.setTextType("SHOP_WORK_ANALYZE");
			addTextInfo.setContent("人争一口气佛受一炷香，就差那么一点点！");
			addTextInfo.setDimStart("11");
			addTextInfo.setDimEnd("30");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("SHOP_WORK_ANALYZE");
			addTextInfo.setContent("你现在的进度很理想，继续努力！");
			addTextInfo.setDimStart("31");
			addTextInfo.setDimEnd("50");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("SHOP_WORK_ANALYZE");
			addTextInfo.setContent("请注意：一大波订单正向你袭来");
			addTextInfo.setDimStart("51");
			addTextInfo.setDimEnd("70");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("SHOP_WORK_ANALYZE");
			addTextInfo.setContent("照这种速度完成，你这个月薪资很可观！加油！");
			addTextInfo.setDimStart("71");
			addTextInfo.setDimEnd("90");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("SHOP_WORK_ANALYZE");
			addTextInfo.setContent("今天您已使尽洪荒之力，完美！");
			addTextInfo.setDimStart("91");
			addTextInfo.setDimEnd("100");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("SHOP_WORK_ANALYZE");
			addTextInfo.setContent("店长都要膜拜你了！");
			addTextInfo.setDimStart("101");
			addTextInfo.setDimEnd("120");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("SHOP_WORK_ANALYZE");
			addTextInfo.setContent("无敌是多么寂寞！");
			addTextInfo.setDimStart("121");
			addTextInfo.setDimEnd("180");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);


			addTextInfo.setTextType("GM_WORK_ANALYZE");
			addTextInfo.setContent("销售就像谈恋爱，你不主动怎么可能成！");
			addTextInfo.setDimStart("0");
			addTextInfo.setDimEnd("0");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("GM_WORK_ANALYZE");
			addTextInfo.setContent("小主，还有一波工作等待您解决！");
			addTextInfo.setDimStart("1");
			addTextInfo.setDimEnd("10");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);


			addTextInfo.setTextType("GM_WORK_ANALYZE");
			addTextInfo.setContent("人争一口气佛受一炷香，就差那么一点点！");
			addTextInfo.setDimStart("11");
			addTextInfo.setDimEnd("30");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("GM_WORK_ANALYZE");
			addTextInfo.setContent("你现在的进度很理想，继续努力！");
			addTextInfo.setDimStart("31");
			addTextInfo.setDimEnd("50");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("GM_WORK_ANALYZE");
			addTextInfo.setContent("请注意：一大波订单正向你袭来");
			addTextInfo.setDimStart("51");
			addTextInfo.setDimEnd("70");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("GM_WORK_ANALYZE");
			addTextInfo.setContent("照这种速度完成，你这个月薪资很可观！加油！");
			addTextInfo.setDimStart("71");
			addTextInfo.setDimEnd("90");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("GM_WORK_ANALYZE");
			addTextInfo.setContent("今天您已使尽洪荒之力，完美！");
			addTextInfo.setDimStart("91");
			addTextInfo.setDimEnd("100");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("GM_WORK_ANALYZE");
			addTextInfo.setContent("店长都要膜拜你了！");
			addTextInfo.setDimStart("101");
			addTextInfo.setDimEnd("120");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);

			addTextInfo.setTextType("GM_WORK_ANALYZE");
			addTextInfo.setContent("无敌是多么寂寞！");
			addTextInfo.setDimStart("121");
			addTextInfo.setDimEnd("180");
			addTextInfo.setDimKeyWord("TODO");
			textInfoService.addTextInfo(addTextInfo);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IMerchantService#delMerchant(com.lj.business
	 * .member.dto.DelMerchant)
	 */
	@Override
	public DelMerchantReturn delMerchant(DelMerchant delMerchant)throws TsfaServiceException {logger.debug("delMerchant(DelMerchant delMerchant={}) - start",delMerchant);
	AssertUtils.notNull(delMerchant);
	AssertUtils.notNull(delMerchant.getCode(), "ID不能为空！");
	try {
		merchantDao.deleteByPrimaryKey(delMerchant.getCode());
		DelMerchantReturn delMerchantReturn = new DelMerchantReturn();
		logger.debug("delMerchant(DelMerchant) - end - return value={}", delMerchantReturn); 
		return delMerchantReturn;
	} catch (TsfaServiceException e) {
		logger.error(e.getMessage(), e);
		throw e;
	} catch (Exception e) {
		logger.error("删除商户表信息错误！", e);
		throw new TsfaServiceException(ErrorCode.MERCHANT_DEL_ERROR,
				"删除商户表信息错误！", e);

	}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IMerchantService#updateMerchant(com.lj
	 * .business.member.dto.UpdateMerchant)
	 */
	@Override
	public UpdateMerchantReturn updateMerchant(UpdateMerchant updateMerchant)throws TsfaServiceException {
		logger.debug("updateMerchant(UpdateMerchant updateMerchant={}) - start", updateMerchant); 
		AssertUtils.notNull(updateMerchant);
		AssertUtils.notNullAndEmpty(updateMerchant.getCode(), "ID不能为空");
		try {
			Merchant merchant = new Merchant();
			// update数据录入
			merchant.setCode(updateMerchant.getCode());
			merchant.setMerchantNo(updateMerchant.getMerchantNo());
			merchant.setMerchantName(updateMerchant.getMerchantName());
			merchant.setStatus(updateMerchant.getStatus());
			merchant.setEshopStatus(updateMerchant.getEshopStatus());
			merchant.setEshopUrl(updateMerchant.getEshopUrl());
			merchant.setAddress(updateMerchant.getAddress());
			merchant.setEmail(updateMerchant.getEmail());
			merchant.setBusinessType(updateMerchant.getBusinessType());
			merchant.setLogoAddr(updateMerchant.getLogoAddr());
			merchant.setWebsiteUrl(updateMerchant.getWebsiteUrl());
			merchant.setTelephone(updateMerchant.getTelephone());
			merchant.setBeginProbationTime(updateMerchant.getBeginProbationTime());
			merchant.setEndProbationTime(updateMerchant.getEndProbationTime());
			merchant.setProbationStatus(updateMerchant.getProbationStatus());
			merchant.setUpdateId(updateMerchant.getUpdateId());
			merchant.setRemark(updateMerchant.getRemark());
			merchant.setRemark2(updateMerchant.getRemark2());
			merchant.setRemark3(updateMerchant.getRemark3());
			merchant.setRemark4(updateMerchant.getRemark4());
			AssertUtils.notUpdateMoreThanOne(merchantDao.updateByPrimaryKeySelective(merchant));
			UpdateMerchantReturn updateMerchantReturn = new UpdateMerchantReturn();
			logger.debug("updateMerchant(UpdateMerchant) - end - return value={}", updateMerchantReturn); 
			return updateMerchantReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("商户表信息更新错误！", e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_UPDATE_ERROR,
					"商户表信息更新错误！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IMerchantService#findMerchant(com.lj.business
	 * .member.dto.FindMerchant)
	 */
	@Override
	public FindMerchantReturn findMerchant(FindMerchant findMerchant)throws TsfaServiceException {
		logger.debug("findMerchant(FindMerchant findMerchant={}) - start", findMerchant); 
		AssertUtils.notNull(findMerchant);
		AssertUtils.notAllNull(findMerchant.getCode(), "ID不能为空");
		try {
			Merchant merchant = merchantDao.selectByPrimaryKey(findMerchant.getCode());
			if (merchant == null) {
				throw new TsfaServiceException(ErrorCode.MERCHANT_NOT_EXIST_ERROR, "商户表信息不存在");
			}
			FindMerchantReturn findMerchantReturn = new FindMerchantReturn();
			// find数据录入
			findMerchantReturn.setCode(merchant.getCode());
			findMerchantReturn.setMerchantNo(merchant.getMerchantNo());
			findMerchantReturn.setMerchantName(merchant.getMerchantName());
			findMerchantReturn.setStatus(merchant.getStatus());
			findMerchantReturn.setEshopStatus(merchant.getEshopStatus());
			findMerchantReturn.setEshopUrl(merchant.getEshopUrl());
			findMerchantReturn.setAddress(merchant.getAddress());
			findMerchantReturn.setEmail(merchant.getEmail());
			findMerchantReturn.setBusinessType(merchant.getBusinessType());
			findMerchantReturn.setLogoAddr(merchant.getLogoAddr());
			findMerchantReturn.setWebsiteUrl(merchant.getWebsiteUrl());
			findMerchantReturn.setTelephone(merchant.getTelephone());
			findMerchantReturn.setBeginProbationTime(merchant.getBeginProbationTime());
			findMerchantReturn.setEndProbationTime(merchant.getEndProbationTime());
			findMerchantReturn.setProbationStatus(merchant.getProbationStatus());
			findMerchantReturn.setCreateId(merchant.getCreateId());
			findMerchantReturn.setCreateDate(merchant.getCreateDate());
			findMerchantReturn.setUpdateId(merchant.getUpdateId());
			findMerchantReturn.setUpdateDate(merchant.getUpdateDate());
			findMerchantReturn.setRemark(merchant.getRemark());
			findMerchantReturn.setRemark2(merchant.getRemark2());
			findMerchantReturn.setRemark3(merchant.getRemark3());
			findMerchantReturn.setRemark4(merchant.getRemark4());
			logger.debug("findMerchant(FindMerchant) - end - return value={}", findMerchantReturn); 
			return findMerchantReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找商户表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_FIND_ERROR,
					"查找商户表信息错误！", e);
		}

	}

	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IMerchantService#findMerchantByCodeOrMerchantNo(com.lj.business.member.dto.FindMerchantDto)
	 */
	/**
	 * 查找商户信息(个人中心)
	 */
	@Override
	public FindMerchantReturnDto selectMerchant(FindMerchantDto findMerchantDto) throws TsfaServiceException {
		logger.debug("findMerchant(FindMerchant findMerchant={}) - start", findMerchantDto); 
		AssertUtils.notNull(findMerchantDto);
		AssertUtils.notNull(findMerchantDto.getMerchantNo(), "MerchantNo不能为空");
		try {
			Merchant merchant = merchantDao.selectMerchant(findMerchantDto);
			if (merchant == null) {
				return null;
			} 
			FindMerchantReturnDto findMerchantReturnDto = new FindMerchantReturnDto();
			// find数据录入
			findMerchantReturnDto.setMerchantNo(merchant.getMerchantNo());
			findMerchantReturnDto.setMerchantName(merchant.getMerchantName());
			findMerchantReturnDto.setLogoAddr(merchant.getLogoAddr());
			findMerchantReturnDto.setWebsiteUrl(merchant.getWebsiteUrl());
			findMerchantReturnDto.setTelephone(merchant.getTelephone());
			findMerchantReturnDto.setProbationStatus(merchant.getProbationStatus());
			findMerchantReturnDto.setEshopStatus(merchant.getEshopStatus());
			findMerchantReturnDto.setEshopUrl(merchant.getEshopUrl());
			logger.debug("findMerchant(FindMerchant) - end - return value={}", findMerchantReturnDto); 
			return findMerchantReturnDto;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找商户表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_FIND_ERROR,
					"查找商户表信息错误！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IMerchantService#findMerchantPage(com.
	 * lj.business.member.dto.FindMerchantPage)
	 */
	@Override
	public Page<FindMerchantPageReturn> findMerchantPage(
			FindMerchantPage findMerchantPage) throws TsfaServiceException {
		logger.debug(
				"findMerchantPage(FindMerchantPage findMerchantPage={}) - start", findMerchantPage); 

		AssertUtils.notNull(findMerchantPage);
		List<FindMerchantPageReturn> returnList = null;
		int count = 0;
		try {
			returnList = merchantDao.findMerchantPage(findMerchantPage);
			count = merchantDao.findMerchantPageCount(findMerchantPage);
		} catch (Exception e) {
			logger.error("商户表信息分页查询错误", e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_FIND_PAGE_ERROR,
					"商户表信息分页查询错误.！", e);
		}
		Page<FindMerchantPageReturn> returnPage = new Page<FindMerchantPageReturn>(
				returnList, count, findMerchantPage);

		logger.debug(
				"findMerchantPage(FindMerchantPage) - end - return value={}", returnPage); 
		return returnPage;
	}

	@Override
	public List<FindMerchantPageReturn> findMerchants(
			FindMerchantPage findMerchantPage) throws TsfaServiceException {
		logger.debug("findMerchants(FindMerchantPage findMerchantPage={}) - start", findMerchantPage); 

		AssertUtils.notNull(findMerchantPage);
		List<FindMerchantPageReturn> returnList = null;
		try {
			returnList = merchantDao.findMerchants(findMerchantPage);
		} catch (Exception e) {
			logger.error("商户表信息查询错误", e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_FIND_ERROR,"商户表信息查询错误.！", e);
		}
		logger.debug("findMerchants(FindMerchantPage) - end - return value={}", returnList); 
		return returnList;
	}

	@Override
	public List<FindMerchantPageReturn> findAllMerchant() throws TsfaServiceException {
		try {
			return merchantDao.findAllMerchant();
		} catch (Exception e) {
			logger.error("查询所有商户错误", e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_FIND_ERROR,"查询所有商户错误", e);
		}
	}
	
	@Override
	public UpdateMerchantReturn updateByMerchantNo(UpdateMerchant updateMerchant)
			throws TsfaServiceException {
		logger.debug("updateMerchant(UpdateMerchant updateMerchant={}) - start", updateMerchant); 
		AssertUtils.notNull(updateMerchant);
		AssertUtils.notNullAndEmpty(updateMerchant.getMerchantNo(), "商户编号不能为空！");
		try {
			Merchant merchant = new Merchant();
			// update数据录入
			merchant.setCode(updateMerchant.getCode());
			merchant.setMerchantNo(updateMerchant.getMerchantNo());
			merchant.setMerchantName(updateMerchant.getMerchantName());
			merchant.setStatus(updateMerchant.getStatus());
			merchant.setAddress(updateMerchant.getAddress());
			merchant.setEmail(updateMerchant.getEmail());
			merchant.setBusinessType(updateMerchant.getBusinessType());
			merchant.setLogoAddr(updateMerchant.getLogoAddr());
			merchant.setWebsiteUrl(updateMerchant.getWebsiteUrl());
			merchant.setTelephone(updateMerchant.getTelephone());
			merchant.setUpdateId(updateMerchant.getUpdateId());
			merchant.setRemark(updateMerchant.getRemark());
			merchant.setRemark2(updateMerchant.getRemark2());
			merchant.setRemark3(updateMerchant.getRemark3());
			merchant.setRemark4(updateMerchant.getRemark4());
			AssertUtils.notUpdateMoreThanOne(merchantDao.updateByMerchantNo(merchant));
			UpdateMerchantReturn updateMerchantReturn = new UpdateMerchantReturn();
			logger.debug("updateMerchant(UpdateMerchant) - end - return value={}", updateMerchantReturn); 
			return updateMerchantReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("商户表信息更新错误！", e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_UPDATE_ERROR,
					"商户表信息更新错误！", e);
		}
	}
	
	

}
