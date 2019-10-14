package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
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
import com.lj.business.cp.constant.ErrorCode;
import com.lj.business.cp.dao.ICouponRuleExDao;
import com.lj.business.cp.domain.CouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.AddCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.AddCouponRuleExReturn;
import com.lj.business.cp.dto.couponRuleEx.DelCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.DelCouponRuleExReturn;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExPage;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExPageReturn;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExReturn;
import com.lj.business.cp.dto.couponRuleEx.UpdateCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.UpdateCouponRuleExReturn;
import com.lj.business.cp.service.ICouponRuleExService;

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
 * CreateDate: 2017-06-14
 */
@Service
public class CouponRuleExServiceImpl implements ICouponRuleExService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CouponRuleExServiceImpl.class);
	

	@Resource
	private ICouponRuleExDao couponRuleExDao;
	
	
	@Override
	public AddCouponRuleExReturn addCouponRuleEx(
			AddCouponRuleEx addCouponRuleEx) throws TsfaServiceException {
		logger.debug("addCouponRuleEx(AddCouponRuleEx addCouponRuleEx={}) - start", addCouponRuleEx); 

		AssertUtils.notNull(addCouponRuleEx);
		try {
			CouponRuleEx couponRuleEx = new CouponRuleEx();
			//add数据录入
			couponRuleEx.setCode(GUID.getPreUUID());
			couponRuleEx.setRuleCode(addCouponRuleEx.getRuleCode());
			couponRuleEx.setUseNum(addCouponRuleEx.getUseNum());
			couponRuleEx.setSurplusNum(addCouponRuleEx.getSurplusNum());
			couponRuleEx.setPv(addCouponRuleEx.getPv());
			couponRuleEx.setUv(addCouponRuleEx.getUv());
			couponRuleEx.setCv(addCouponRuleEx.getCv());
			couponRuleEx.setCreateId(addCouponRuleEx.getCreateId());
			couponRuleEx.setCreateDate(new Date());
			couponRuleExDao.insert(couponRuleEx);
			AddCouponRuleExReturn addCouponRuleExReturn = new AddCouponRuleExReturn();
			
			logger.debug("addCouponRuleEx(AddCouponRuleEx) - end - return value={}", addCouponRuleExReturn); 
			return addCouponRuleExReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增优惠券规则业务扩展表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_EX_ADD_ERROR,"新增优惠券规则业务扩展表信息错误！",e);
		}
	}
	
	
	@Override
	public DelCouponRuleExReturn delCouponRuleEx(
			DelCouponRuleEx delCouponRuleEx) throws TsfaServiceException {
		logger.debug("delCouponRuleEx(DelCouponRuleEx delCouponRuleEx={}) - start", delCouponRuleEx); 

		AssertUtils.notNull(delCouponRuleEx);
		AssertUtils.notNull(delCouponRuleEx.getCode(),"CODE不能为空！");
		try {
			couponRuleExDao.deleteByPrimaryKey(delCouponRuleEx.getCode());
			DelCouponRuleExReturn delCouponRuleExReturn  = new DelCouponRuleExReturn();

			logger.debug("delCouponRuleEx(DelCouponRuleEx) - end - return value={}", delCouponRuleExReturn); 
			return delCouponRuleExReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除优惠券规则业务扩展表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_EX_DEL_ERROR,"删除优惠券规则业务扩展表信息错误！",e);

		}
	}

	@Override
	public UpdateCouponRuleExReturn updateCouponRuleEx(
			UpdateCouponRuleEx updateCouponRuleEx)
			throws TsfaServiceException {
		logger.debug("updateCouponRuleEx(UpdateCouponRuleEx updateCouponRuleEx={}) - start", updateCouponRuleEx); 

		AssertUtils.notNull(updateCouponRuleEx);
		AssertUtils.notNullAndEmpty(updateCouponRuleEx.getCode(),"CODE不能为空");
		try {
			CouponRuleEx couponRuleEx = new CouponRuleEx();
			//update数据录入
			couponRuleEx.setCode(updateCouponRuleEx.getCode());
			couponRuleEx.setRuleCode(updateCouponRuleEx.getRuleCode());
			couponRuleEx.setUseNum(updateCouponRuleEx.getUseNum());
			couponRuleEx.setSurplusNum(updateCouponRuleEx.getSurplusNum());
			couponRuleEx.setPv(updateCouponRuleEx.getPv());
			couponRuleEx.setUv(updateCouponRuleEx.getUv());
			couponRuleEx.setCv(updateCouponRuleEx.getCv());
			couponRuleEx.setCreateId(updateCouponRuleEx.getCreateId());
			couponRuleEx.setCreateDate(updateCouponRuleEx.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(couponRuleExDao.updateByPrimaryKeySelective(couponRuleEx));
			UpdateCouponRuleExReturn updateCouponRuleExReturn = new UpdateCouponRuleExReturn();

			logger.debug("updateCouponRuleEx(UpdateCouponRuleEx) - end - return value={}", updateCouponRuleExReturn); 
			return updateCouponRuleExReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("优惠券规则业务扩展表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_EX_UPDATE_ERROR,"优惠券规则业务扩展表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindCouponRuleExReturn findCouponRuleEx(
			FindCouponRuleEx findCouponRuleEx) throws TsfaServiceException {
		logger.debug("findCouponRuleEx(FindCouponRuleEx findCouponRuleEx={}) - start", findCouponRuleEx); 

		AssertUtils.notNull(findCouponRuleEx);
		AssertUtils.notAllNull(findCouponRuleEx.getCode(),"CODE不能为空");
		try {
			CouponRuleEx couponRuleEx = couponRuleExDao.selectByPrimaryKey(findCouponRuleEx.getCode());
			if(couponRuleEx == null){
				throw new TsfaServiceException(ErrorCode.COUPON_RULE_EX_NOT_EXIST_ERROR,"优惠券规则业务扩展表信息不存在");
			}
			FindCouponRuleExReturn findCouponRuleExReturn = new FindCouponRuleExReturn();
			//find数据录入
			findCouponRuleExReturn.setCode(couponRuleEx.getCode());
			findCouponRuleExReturn.setRuleCode(couponRuleEx.getRuleCode());
			findCouponRuleExReturn.setUseNum(couponRuleEx.getUseNum());
			findCouponRuleExReturn.setSurplusNum(couponRuleEx.getSurplusNum());
			findCouponRuleExReturn.setPv(couponRuleEx.getPv());
			findCouponRuleExReturn.setUv(couponRuleEx.getUv());
			findCouponRuleExReturn.setCv(couponRuleEx.getCv());
			findCouponRuleExReturn.setCreateId(couponRuleEx.getCreateId());
			findCouponRuleExReturn.setCreateDate(couponRuleEx.getCreateDate());
			
			logger.debug("findCouponRuleEx(FindCouponRuleEx) - end - return value={}", findCouponRuleExReturn); 
			return findCouponRuleExReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找优惠券规则业务扩展表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_EX_FIND_ERROR,"查找优惠券规则业务扩展表信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindCouponRuleExPageReturn> findCouponRuleExPage(
			FindCouponRuleExPage findCouponRuleExPage)
			throws TsfaServiceException {
		logger.debug("findCouponRuleExPage(FindCouponRuleExPage findCouponRuleExPage={}) - start", findCouponRuleExPage); 

		AssertUtils.notNull(findCouponRuleExPage);
		List<FindCouponRuleExPageReturn> returnList;
		int count = 0;
		try {
			returnList = couponRuleExDao.findCouponRuleExPage(findCouponRuleExPage);
			count = couponRuleExDao.findCouponRuleExPageCount(findCouponRuleExPage);
		}  catch (Exception e) {
			logger.error("优惠券规则业务扩展表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_EX_FIND_PAGE_ERROR,"优惠券规则业务扩展表信息不存在错误.！",e);
		}
		Page<FindCouponRuleExPageReturn> returnPage = new Page<FindCouponRuleExPageReturn>(returnList, count, findCouponRuleExPage);

		logger.debug("findCouponRuleExPage(FindCouponRuleExPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public int updateCouponUseNum(UpdateCouponRuleEx updateCouponRuleEx)
			throws TsfaServiceException {
		logger.debug("findCouponRuleExPage(CouponRuleEx couponRuleEx={}) - start", updateCouponRuleEx); 
		int i = 0 ;
		try {
			 CouponRuleEx couponRuleEx = new CouponRuleEx();
			 couponRuleEx.setRuleCode(updateCouponRuleEx.getRuleCode());
			 i = couponRuleExDao.updateCouponUseNum(couponRuleEx);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
		}catch (Exception e) {
			logger.error("修改规则扩展表信息错误！",e);
		}
		
		return i;
	}


	@Override
	public FindCouponRuleExReturn findCouponRuleExByRuleCode(
			FindCouponRuleEx findCouponRuleEx) {
			logger.debug("findCouponRuleEx(FindCouponRuleEx findCouponRuleEx={}) - start", findCouponRuleEx); 
			AssertUtils.notNull(findCouponRuleEx);
			AssertUtils.notAllNull(findCouponRuleEx.getRuleCode(),"RuleCode不能为空");
			try {
				CouponRuleEx couponRuleEx = couponRuleExDao.findCouponRuleExByRuleCode(findCouponRuleEx.getRuleCode());
				if(couponRuleEx == null){
					throw new TsfaServiceException(ErrorCode.COUPON_RULE_EX_NOT_EXIST_ERROR,"优惠券规则业务扩展表信息不存在");
				}
				FindCouponRuleExReturn findCouponRuleExReturn = new FindCouponRuleExReturn();
				//find数据录入
				findCouponRuleExReturn.setCode(couponRuleEx.getCode());
				findCouponRuleExReturn.setRuleCode(couponRuleEx.getRuleCode());
				findCouponRuleExReturn.setUseNum(couponRuleEx.getUseNum());
				findCouponRuleExReturn.setSurplusNum(couponRuleEx.getSurplusNum());
				findCouponRuleExReturn.setPv(couponRuleEx.getPv());
				findCouponRuleExReturn.setUv(couponRuleEx.getUv());
				findCouponRuleExReturn.setCv(couponRuleEx.getCv());
				findCouponRuleExReturn.setCreateId(couponRuleEx.getCreateId());
				findCouponRuleExReturn.setCreateDate(couponRuleEx.getCreateDate());
				
				logger.debug("findCouponRuleEx(FindCouponRuleEx) - end - return value={}", findCouponRuleExReturn); 
				return findCouponRuleExReturn;
			
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_EX_NOT_EXIST_ERROR,"优惠券规则业务扩展表信息不存在");
		}catch (Exception e) {
			logger.error("查询规则扩展表信息错误！",e);
		}
		return null;
	}


	@Override
	public int updateCouponCv(UpdateCouponRuleEx updateCouponRuleEx)
			throws TsfaServiceException {
		AssertUtils.notAllNull(updateCouponRuleEx.getRuleCode(),"规则编号不能为空！");
		int i = 0;
		try {
			i = couponRuleExDao.updateCouponCv(updateCouponRuleEx);
		} catch (TsfaServiceException e) {
		  logger.error(e.getMessage(),e);
		}catch (Exception e) {
          logger.error("更新优惠券规则扩展表信息错误！",e);
		}
		return i;
	}


    @Override
    public int updateCouponRuleExData(UpdateCouponRuleEx couponRuleEx) throws TsfaServiceException {
        AssertUtils.notAllNull(couponRuleEx.getRuleCode(), "规则编号不能为空！");
        int i = 0;
        try {
            i = couponRuleExDao.updateCouponRuleExData(couponRuleEx);
        } catch (TsfaServiceException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error("更新浏览量错误！", e);
        }

        return i;
    }

}
