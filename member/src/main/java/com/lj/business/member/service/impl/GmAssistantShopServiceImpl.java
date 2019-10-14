package com.lj.business.member.service.impl;

import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IGmAssistantShopDao;
import com.lj.business.member.domain.GmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.AddGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.AddGmAssistantShopReturn;
import com.lj.business.member.dto.gmAssistantShop.DelGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.DelGmAssistantShopReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopByImReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPage;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPageReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.gmAssistantShop.UpdateGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.UpdateGmAssistantShopReturn;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.supcon.service.ICommonService;

/**
 * 
 * 
 * 类说明：实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年12月2日
 */
@Service
public class GmAssistantShopServiceImpl implements IGmAssistantShopService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GmAssistantShopServiceImpl.class);
	

	@Resource
	private IGmAssistantShopDao gmAssistantShopDao;
	@Autowired
	private ICommonService commonService;
	
	
	@Override
	public AddGmAssistantShopReturn addGmAssistantShop(
			AddGmAssistantShop addGmAssistantShop) throws TsfaServiceException {
		logger.debug("addGmAssistantShop(AddGmAssistantShop addGmAssistantShop={}) - start", addGmAssistantShop); 

		AssertUtils.notNull(addGmAssistantShop);
		try {
			GmAssistantShop gmAssistantShop = new GmAssistantShop();
			//add数据录入
			gmAssistantShop.setCode(GUID.getPreUUID());
			gmAssistantShop.setAssistantNo(addGmAssistantShop.getAssistantNo());
			gmAssistantShop.setAssistantName(addGmAssistantShop.getAssistantName());
			gmAssistantShop.setMerchantNo(addGmAssistantShop.getMerchantNo());
			gmAssistantShop.setMerchantName(addGmAssistantShop.getMerchantName());
			gmAssistantShop.setCreateId(addGmAssistantShop.getCreateId());
			gmAssistantShop.setCreateDate(new Date());
			gmAssistantShop.setRemark(addGmAssistantShop.getRemark());
			gmAssistantShop.setRemark2(addGmAssistantShop.getRemark2());
			gmAssistantShop.setRemark3(addGmAssistantShop.getRemark3());
			gmAssistantShop.setRemark4(addGmAssistantShop.getRemark4());
			gmAssistantShop.setTidCode(addGmAssistantShop.getTidCode());
			gmAssistantShop.setTerminalCode(addGmAssistantShop.getTerminalCode());
			gmAssistantShop.setNoWx(addGmAssistantShop.getNoWx());
			gmAssistantShop.setWxNickname(addGmAssistantShop.getWxNickname());
			gmAssistantShop.setLoginName(addGmAssistantShop.getLoginName());
			gmAssistantShop.setSource(addGmAssistantShop.getSource());
			gmAssistantShopDao.insertSelective(gmAssistantShop);
			AddGmAssistantShopReturn addGmAssistantShopReturn = new AddGmAssistantShopReturn();
			
			logger.debug("addGmAssistantShop(AddGmAssistantShop) - end - return value={}", addGmAssistantShopReturn); 
			return addGmAssistantShopReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增导购助手管理表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_ADD_ERROR,"新增导购助手管理表信息错误！",e);
		}
	}
	
	
	@Override
	public DelGmAssistantShopReturn delGmAssistantShop(
			DelGmAssistantShop delGmAssistantShop) throws TsfaServiceException {
		logger.debug("delGmAssistantShop(DelGmAssistantShop delGmAssistantShop={}) - start", delGmAssistantShop); 

		AssertUtils.notNull(delGmAssistantShop);
		AssertUtils.notNull(delGmAssistantShop.getCode(),"CODE不能为空！");
		try {
			gmAssistantShopDao.deleteByPrimaryKey(delGmAssistantShop.getCode());
			DelGmAssistantShopReturn delGmAssistantShopReturn  = new DelGmAssistantShopReturn();

			logger.debug("delGmAssistantShop(DelGmAssistantShop) - end - return value={}", delGmAssistantShopReturn); 
			return delGmAssistantShopReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除导购助手管理表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_DEL_ERROR,"删除导购助手管理表信息错误！",e);

		}
	}

	@Override
	public UpdateGmAssistantShopReturn updateGmAssistantShop(
			UpdateGmAssistantShop updateGmAssistantShop)
			throws TsfaServiceException {
		logger.debug("updateGmAssistantShop(UpdateGmAssistantShop updateGmAssistantShop={}) - start", updateGmAssistantShop); 

		AssertUtils.notNull(updateGmAssistantShop);
		AssertUtils.notNullAndEmpty(updateGmAssistantShop.getCode(),"CODE不能为空");
		try {
			GmAssistantShop gmAssistantShop = new GmAssistantShop();
			//update数据录入
			gmAssistantShop.setCode(updateGmAssistantShop.getCode());
			gmAssistantShop.setAssistantNo(updateGmAssistantShop.getAssistantNo());
			gmAssistantShop.setAssistantName(updateGmAssistantShop.getAssistantName());
			gmAssistantShop.setMerchantNo(updateGmAssistantShop.getMerchantNo());
			gmAssistantShop.setMerchantName(updateGmAssistantShop.getMerchantName());
//			gmAssistantShop.setShopNo(updateGmAssistantShop.getShopNo());
//			gmAssistantShop.setShopName(updateGmAssistantShop.getShopName());
			gmAssistantShop.setCreateId(updateGmAssistantShop.getCreateId());
			gmAssistantShop.setCreateDate(updateGmAssistantShop.getCreateDate());
			gmAssistantShop.setRemark(updateGmAssistantShop.getRemark());
			gmAssistantShop.setRemark2(updateGmAssistantShop.getRemark2());
			gmAssistantShop.setRemark3(updateGmAssistantShop.getRemark3());
			gmAssistantShop.setRemark4(updateGmAssistantShop.getRemark4());
			gmAssistantShop.setTidCode(updateGmAssistantShop.getTidCode());
			gmAssistantShop.setTerminalCode(updateGmAssistantShop.getTerminalCode());
			gmAssistantShop.setNoWx(updateGmAssistantShop.getNoWx());
			gmAssistantShop.setWxNickname(updateGmAssistantShop.getWxNickname());
			gmAssistantShop.setLoginName(updateGmAssistantShop.getLoginName());
			AssertUtils.notUpdateMoreThanOne(gmAssistantShopDao.updateByPrimaryKeySelective(gmAssistantShop));
			UpdateGmAssistantShopReturn updateGmAssistantShopReturn = new UpdateGmAssistantShopReturn();

			logger.debug("updateGmAssistantShop(UpdateGmAssistantShop) - end - return value={}", updateGmAssistantShopReturn); 
			return updateGmAssistantShopReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("导购助手管理表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_UPDATE_ERROR,"导购助手管理表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindGmAssistantShopReturn findGmAssistantShop(
			FindGmAssistantShop findGmAssistantShop) throws TsfaServiceException {
		logger.debug("findGmAssistantShop(FindGmAssistantShop findGmAssistantShop={}) - start", findGmAssistantShop); 

		AssertUtils.notNull(findGmAssistantShop);
		AssertUtils.notAllNull(findGmAssistantShop.getCode(),"CODE不能为空");
		try {
			GmAssistantShop gmAssistantShop = gmAssistantShopDao.selectByPrimaryKey(findGmAssistantShop.getCode());
			if(gmAssistantShop == null){
				throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_NOT_EXIST_ERROR,"导购助手管理表信息不存在");
			}
			FindGmAssistantShopReturn findGmAssistantShopReturn = new FindGmAssistantShopReturn();
			//find数据录入
			findGmAssistantShopReturn.setCode(gmAssistantShop.getCode());
			findGmAssistantShopReturn.setAssistantNo(gmAssistantShop.getAssistantNo());
			findGmAssistantShopReturn.setAssistantName(gmAssistantShop.getAssistantName());
			findGmAssistantShopReturn.setMerchantNo(gmAssistantShop.getMerchantNo());
			findGmAssistantShopReturn.setMerchantName(gmAssistantShop.getMerchantName());
			findGmAssistantShopReturn.setCreateId(gmAssistantShop.getCreateId());
			findGmAssistantShopReturn.setCreateDate(gmAssistantShop.getCreateDate());
			findGmAssistantShopReturn.setRemark(gmAssistantShop.getRemark());
			findGmAssistantShopReturn.setRemark2(gmAssistantShop.getRemark2());
			findGmAssistantShopReturn.setRemark3(gmAssistantShop.getRemark3());
			findGmAssistantShopReturn.setRemark4(gmAssistantShop.getRemark4());
			findGmAssistantShopReturn.setTidCode(gmAssistantShop.getTidCode());
			findGmAssistantShopReturn.setTerminalCode(gmAssistantShop.getTerminalCode());
			findGmAssistantShopReturn.setNoWx(gmAssistantShop.getNoWx());
			findGmAssistantShopReturn.setWxNickname(gmAssistantShop.getWxNickname());
			findGmAssistantShopReturn.setLoginName(gmAssistantShop.getLoginName());
			logger.debug("findGmAssistantShop(FindGmAssistantShop) - end - return value={}", findGmAssistantShopReturn); 
			return findGmAssistantShopReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找导购助手管理表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_FIND_ERROR,"查找导购助手管理表信息信息错误！",e);
		}


	}

	
	
	@Override
	public Page<FindGmAssistantShopPageReturn> findGmAssistantWithTerminalPage(
			FindGmAssistantShopPage findGmAssistantShopPage)
			throws TsfaServiceException {
		logger.debug("findGmAssistantShopPage(FindGmAssistantShopPage findGmAssistantShopPage={}) - start", findGmAssistantShopPage); 

		AssertUtils.notNull(findGmAssistantShopPage);
		List<FindGmAssistantShopPageReturn> returnList = new ArrayList<>();
		int count = 0;
		try {
			count = gmAssistantShopDao.findGmAssistantWithTerminalCount(findGmAssistantShopPage);
			if(count>0) {
				returnList = gmAssistantShopDao.findGmAssistantWithTerminalPage(findGmAssistantShopPage);
				for (FindGmAssistantShopPageReturn findGmAssistantShopPageReturn : returnList) {
					findGmAssistantShopPageReturn.setWxStatus(commonService.getClientStatus(findGmAssistantShopPageReturn.getNoWx()));
				}
			}
		}  catch (Exception e) {
			logger.error("导购助手管理表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_FIND_PAGE_ERROR,"导购助手管理表信息不存在错误.！",e);
		}
		Page<FindGmAssistantShopPageReturn> returnPage = new Page<FindGmAssistantShopPageReturn>(returnList, count, findGmAssistantShopPage);

		logger.debug("findGmAssistantShopPage(FindGmAssistantShopPage) - end - return value={}", returnPage); 
		return  returnPage;
	}
	@Override
	public Page<FindGmAssistantShopPageReturn> findGmAssistantShopPage(
			FindGmAssistantShopPage findGmAssistantShopPage)
			throws TsfaServiceException {
		logger.debug("findGmAssistantShopPage(FindGmAssistantShopPage findGmAssistantShopPage={}) - start", findGmAssistantShopPage); 

		AssertUtils.notNull(findGmAssistantShopPage);
		List<FindGmAssistantShopPageReturn> returnList = new ArrayList<>();
		int count = 0;
		try {
			count = gmAssistantShopDao.findGmAssistantShopPageCount(findGmAssistantShopPage);
			if(count>0) {
				returnList = gmAssistantShopDao.findGmAssistantShopPage(findGmAssistantShopPage);
			}
		}  catch (Exception e) {
			logger.error("导购助手管理表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_FIND_PAGE_ERROR,"导购助手管理表信息不存在错误.！",e);
		}
		Page<FindGmAssistantShopPageReturn> returnPage = new Page<FindGmAssistantShopPageReturn>(returnList, count, findGmAssistantShopPage);

		logger.debug("findGmAssistantShopPage(FindGmAssistantShopPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public List<FindGmAssistantShopReturn> findGmAssistantShopList(FindGmAssistantShop findGmAssistantShop)throws TsfaServiceException {
		List<FindGmAssistantShopReturn> list;
		try {
		 list=gmAssistantShopDao.findGmAssistantShopList(findGmAssistantShop);
		} catch (Exception e) {
			logger.error("导购助手管理表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_FIND_PAGE_ERROR,"导购助手管理表信息不存在错误.！",e);
		}
		return list;
	}

	@Override
	public List<FindGmAssistantShopByImReturn> findGmAssistantShopListByIm(FindGmAssistantShop findGmAssistantShop) throws TsfaServiceException {
		List<FindGmAssistantShopByImReturn> list;
		try {
		 list=gmAssistantShopDao.findGmAssistantShopListByIm(findGmAssistantShop);
		} catch (Exception e) {
			logger.error("IM导购助手查询包括可用终端终端的终端错误",e);
			throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_FIND_ERROR,"IM导购助手查询包括可用终端终端的终端错误.！",e);
		}
		return list;
	}


	@Override
	public String findTidCodesByAssistantNo(String assistantNo, String merchantNo) throws TsfaServiceException {
		logger.debug("findTidCodesByAssistantNo(String assistantNo={}, String merchantNo)={}) - start", assistantNo,merchantNo); 
		AssertUtils.notNullAndEmpty(assistantNo, "导购助手不能为空");
		AssertUtils.notNullAndEmpty(merchantNo, "商户号不能为空");
		try {
			 String tidCodes=gmAssistantShopDao.findTidCodesByAssistantNo(assistantNo, merchantNo);
			 logger.debug("findTidCodesByAssistantNo(String assistantNo, String merchantNo) - end - return value={}", tidCodes); 
			 return tidCodes;
			} catch (Exception e) {
				logger.error("获取终端编号集合根据导购助手分组错误",e);
				throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_FIND_PAGE_ERROR,"获取终端编号集合根据导购助手分组错误.！",e);
			}
				
	}


	@Override
	public int findGmAssistantShopPageCount(FindGmAssistantShopPage findGmAssistantShopPage)
			throws TsfaServiceException {
		logger.debug("findGmAssistantShopPageCount(FindGmAssistantShopPage findGmAssistantShopPage={}) - start", findGmAssistantShopPage); 

		AssertUtils.notNull(findGmAssistantShopPage);
		int count = 0;
		try {
			count = gmAssistantShopDao.findGmAssistantShopPageCount(findGmAssistantShopPage);
		}  catch (Exception e) {
			logger.error("导购助手管理表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_FIND_PAGE_ERROR,"导购助手管理表信息不存在错误.！",e);
		}

		logger.debug("findGmAssistantShopPageCount(FindGmAssistantShopPage) - end - return value={}", count); 
		return  count;
	}


	@Override
	public int deleteByAssistantNo(String assistantNo, String merchantNo) {
		logger.debug("deleteByAssistantNo(String assistantNo={}, String merchantNo)={}) - start", assistantNo,merchantNo); 

		AssertUtils.notNullAndEmpty(assistantNo, "导购助手不能为空");
		AssertUtils.notNullAndEmpty(merchantNo, "商户号不能为空");
		try {
			 int count=gmAssistantShopDao.deleteByAssistantNo(assistantNo, merchantNo);
			 logger.debug("deleteByAssistantNo(String assistantNo, String merchantNo) - end - return value={}", count); 
			 return count;
			} catch (Exception e) {
				logger.error("获取终端编号集合根据导购助手分组错误",e);
				throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_FIND_PAGE_ERROR,"获取终端编号集合根据导购助手分组错误.！",e);
			}
	} 

	@Override
	public String findGroupConcatByAssNo(String assistantNo) {
		logger.debug("findGroupConcatByAssNo(String assistantNo ={}) - start",assistantNo);
		String result = "";
		try {
			result = gmAssistantShopDao.findGroupConcatByAssNo(assistantNo);
			logger.debug("findGroupConcatByAssNo(String assistantNo) - end - return value={}", result); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e.getExceptionInfo());
			throw e;
		} catch (Exception e) {
			logger.error("查询导购助手绑定的微信集合！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_DEL_ERROR,"查询导购助手绑定的微信集合！", e);
		}
		return result;
	}


	@Override
	public void synByLoginName(String oldLoginName, String loginName) throws TsfaServiceException {
		logger.debug("synByLoginName(String oldLoginName={}, String loginName={}) ={}) - start",oldLoginName,loginName);
		AssertUtils.notNullAndEmpty(oldLoginName, "旧登录名不能为空");
		AssertUtils.notNullAndEmpty(loginName, "新登录名不能为空");
		
		try {
			int count = gmAssistantShopDao.synByLoginName(oldLoginName,loginName);
			logger.debug("synByLoginName() - end - return value={}", count); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e.getExceptionInfo());
			throw e;
		} catch (Exception e) {
			logger.error("同步登录名错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_UPDATE_ERROR,"同步登录名错误！", e);
		}
	}

	@Override
	public List<GmAssistantShop> findGmAssistantListByWx(FindGmAssistantShop findGmAssistantShop) {
		return gmAssistantShopDao.findGmAssistantListByWx(findGmAssistantShop);
	}

	public List<GmAssistantShop> findGmAssistantListByLoginName(FindGmAssistantShop findGmAssistantShop) {
		return gmAssistantShopDao.findGmAssistantListByWx(findGmAssistantShop);
	}


	@Override
	public FindGmAssistantShopReturn findGmByWxAndNo(String noWxGm, String assistantNo) throws TsfaServiceException {
		logger.debug("findGmByWxAndNo(String noWxGm={}, String assistantNo ={}) - start",noWxGm,assistantNo);
		List<FindGmAssistantShopReturn> list;
		try {
			FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
			findGmAssistantShop.setAssistantNo(assistantNo);
			findGmAssistantShop.setNoWx(noWxGm);
		 list=this.findGmAssistantShopList(findGmAssistantShop);
		 if(null != list && list.size()>0) {
			 return list.get(0);
		 }
		} catch (Exception e) {
			logger.error("导购助手管理表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_FIND_PAGE_ERROR,"导购助手管理表信息不存在错误.！",e);
		}
		return null;
	}


	
	@Override
	public void delete(UpdateGmAssistantShop updateGmAssistantShop) {
		gmAssistantShopDao.delete(updateGmAssistantShop);
		
	}

	@Override
	public List<FindGmAssistantShopReturn> findListGroupByNoWx(FindGmAssistantShop findGmAssistantShop)throws TsfaServiceException {
		List<FindGmAssistantShopReturn> list;
		try {
		 list=gmAssistantShopDao.findListGroupByNoWx(findGmAssistantShop);
		} catch (Exception e) {
			logger.error("导购助手管理表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GM_ASSISTANT_SHOP_FIND_PAGE_ERROR,"导购助手管理表信息不存在错误.！",e);
		}
		return list;
	}
}
