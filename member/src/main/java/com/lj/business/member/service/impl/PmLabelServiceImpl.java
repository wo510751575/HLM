package com.lj.business.member.service.impl;

import java.util.Date;
import java.util.HashMap;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ape.common.utils.StringUtils;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IPersonMemberDao;
import com.lj.business.member.dao.IPmLabelDao;
import com.lj.business.member.dao.IPmLabelPmDao;
import com.lj.business.member.domain.PmLabel;
import com.lj.business.member.domain.PmLabelPm;
import com.lj.business.member.dto.AddPmLabel;
import com.lj.business.member.dto.AddPmLabelReturn;
import com.lj.business.member.dto.ChangePmLabel;
import com.lj.business.member.dto.DelPmLabel;
import com.lj.business.member.dto.DelPmLabelReturn;
import com.lj.business.member.dto.FindPmLabel;
import com.lj.business.member.dto.FindPmLabelPage;
import com.lj.business.member.dto.FindPmLabelPageReturn;
import com.lj.business.member.dto.FindPmLabelReturn;
import com.lj.business.member.dto.FindPmLabelReturnList;
import com.lj.business.member.dto.PmLabelDto;
import com.lj.business.member.dto.UpdatePmLabel;
import com.lj.business.member.dto.UpdatePmLabelReturn;
import com.lj.business.member.service.IPmLabelService;

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
public class PmLabelServiceImpl implements IPmLabelService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PmLabelServiceImpl.class);

	/** The pm label dao. */
	@Resource
	private IPmLabelDao pmLabelDao;

	/** The pm label pm dao. */
	@Resource
	private IPmLabelPmDao pmLabelPmDao;

	@Resource
	private IPersonMemberDao personMemberDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IPmLabelService#addPmLabel(com.lj.business.
	 * member.dto.AddPmLabel)
	 */
	@Override
	public AddPmLabelReturn addPmLabel(AddPmLabel addPmLabel) throws TsfaServiceException {
		logger.debug("addPmLabel(AddPmLabel addPmLabel={}) - start", addPmLabel);

		AssertUtils.notNull(addPmLabel);
		try {
			PmLabel pmLabel = new PmLabel();
			// add数据录入
			pmLabel.setCode(GUID.generateCode());
			pmLabel.setMerchantNo(addPmLabel.getMerchantNo());
			pmLabel.setLabelName(addPmLabel.getLabelName());
			pmLabel.setCreateId(addPmLabel.getCreateId());
			pmLabel.setRemark(addPmLabel.getRemark());
			pmLabel.setCreateDate(new Date());
			pmLabelDao.insert(pmLabel);
			AddPmLabelReturn addPmLabelReturn = new AddPmLabelReturn();
			addPmLabelReturn.setCode(pmLabel.getCode());
			logger.debug("addPmLabel(AddPmLabel) - end - return value={}", addPmLabelReturn);
			return addPmLabelReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增客户标签表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_ADD_ERROR, "新增客户标签表信息错误！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IPmLabelService#delPmLabel(com.lj.business.
	 * member.dto.DelPmLabel)
	 */
	@Override
	public DelPmLabelReturn delPmLabel(DelPmLabel delPmLabel) throws TsfaServiceException {
		logger.debug("delPmLabel(DelPmLabel delPmLabel={}) - start", delPmLabel);

		AssertUtils.notNull(delPmLabel);
		AssertUtils.notNull(delPmLabel.getCode(), "ID不能为空！");
		try {
			pmLabelDao.deleteByPrimaryKey(delPmLabel.getCode());
			DelPmLabelReturn delPmLabelReturn = new DelPmLabelReturn();

			logger.debug("delPmLabel(DelPmLabel) - end - return value={}", delPmLabelReturn);
			return delPmLabelReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除客户标签表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_DEL_ERROR, "删除客户标签表信息错误！", e);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IPmLabelService#updatePmLabel(com.lj.business.
	 * member.dto.UpdatePmLabel)
	 */
	@Override
	public UpdatePmLabelReturn updatePmLabel(UpdatePmLabel updatePmLabel) throws TsfaServiceException {
		logger.debug("updatePmLabel(UpdatePmLabel updatePmLabel={}) - start", updatePmLabel);

		AssertUtils.notNull(updatePmLabel);
		AssertUtils.notNullAndEmpty(updatePmLabel.getCode(), "ID不能为空");
		try {
			PmLabel pmLabel = new PmLabel();
			// update数据录入
			pmLabel.setCode(updatePmLabel.getCode());
			pmLabel.setMerchantNo(updatePmLabel.getMerchantNo());
			pmLabel.setLabelName(updatePmLabel.getLabelName());
			pmLabel.setCreateId(updatePmLabel.getCreateId());
			pmLabel.setRemark(updatePmLabel.getRemark());
			pmLabel.setCreateDate(updatePmLabel.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(pmLabelDao.updateByPrimaryKeySelective(pmLabel));

			/**
			 * 变动标签名，同步pm_label_pm
			 */
			if (StringUtils.isNotBlank(updatePmLabel.getOldLabelName())
					&& StringUtils.isNotBlank(updatePmLabel.getLabelName())
					&& !updatePmLabel.getOldLabelName().equals(updatePmLabel.getLabelName())) {
				pmLabelPmDao.synPmLabelName(updatePmLabel.getCode(), updatePmLabel.getLabelName());
			}

			UpdatePmLabelReturn updatePmLabelReturn = new UpdatePmLabelReturn();

			logger.debug("updatePmLabel(UpdatePmLabel) - end - return value={}", updatePmLabelReturn);
			return updatePmLabelReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("客户标签表信息更新错误！", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_UPDATE_ERROR, "客户标签表信息更新错误！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IPmLabelService#findPmLabel(com.lj.business.
	 * member.dto.FindPmLabel)
	 */
	@Override
	public FindPmLabelReturn findPmLabel(FindPmLabel findPmLabel) throws TsfaServiceException {
		logger.debug("findPmLabel(FindPmLabel findPmLabel={}) - start", findPmLabel);

		AssertUtils.notNull(findPmLabel);
		AssertUtils.notAllNull(findPmLabel.getCode(), "ID不能为空");
		try {
			PmLabel pmLabel = pmLabelDao.selectByPrimaryKey(findPmLabel.getCode());
			if (pmLabel == null) {
				throw new TsfaServiceException(ErrorCode.PM_LABEL_NOT_EXIST_ERROR, "客户标签表信息不存在");
			}
			FindPmLabelReturn findPmLabelReturn = new FindPmLabelReturn();
			// find数据录入
			findPmLabelReturn.setCode(pmLabel.getCode());
			findPmLabelReturn.setMerchantNo(pmLabel.getMerchantNo());
			findPmLabelReturn.setLabelName(pmLabel.getLabelName());
			findPmLabelReturn.setCreateId(pmLabel.getCreateId());
			findPmLabelReturn.setRemark(pmLabel.getRemark());
			findPmLabelReturn.setCreateDate(pmLabel.getCreateDate());

			logger.debug("findPmLabel(FindPmLabel) - end - return value={}", findPmLabelReturn);
			return findPmLabelReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户标签表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_FIND_ERROR, "查找客户标签表信息错误！", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lj.business.member.service.IPmLabelService#findPmLabelPage(com.lj.
	 * business.member.dto.FindPmLabelPage)
	 */
	@Override
	public Page<FindPmLabelPageReturn> findPmLabelPage(FindPmLabelPage findPmLabelPage) throws TsfaServiceException {
		logger.debug("findPmLabelPage(FindPmLabelPage findPmLabelPage={}) - start", findPmLabelPage);

		AssertUtils.notNull(findPmLabelPage);
		List<FindPmLabelPageReturn> returnList;
		int count = 0;
		try {
			returnList = pmLabelDao.findPmLabelPage(findPmLabelPage);
			count = pmLabelDao.findPmLabelPageCount(findPmLabelPage);
		} catch (Exception e) {
			logger.error("客户标签表信息分页查询错误", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_FIND_PAGE_ERROR, "客户标签表信息分页查询错误.！", e);
		}
		Page<FindPmLabelPageReturn> returnPage = new Page<FindPmLabelPageReturn>(returnList, count, findPmLabelPage);

		logger.debug("findPmLabelPage(FindPmLabelPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public List<FindPmLabelReturnList> findPmlabelGuidMember() throws TsfaServiceException {
		logger.debug("findPmLabelPage() - start");
		List<FindPmLabelReturnList> list = null;
		try {
			list = pmLabelDao.findPmlabelGuidMember();
		} catch (Exception e) {
			logger.error("客户标签表信息分页查询错误", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_FIND_PAGE_ERROR, "客户标签表信息分页查询错误.！", e);
		}
		return list;
	}

	@Override
	public List<FindPmLabelReturnList> findPmlabelMerchantNo() throws TsfaServiceException {
		logger.debug("findPmLabelPage() - start");
		List<FindPmLabelReturnList> list = null;
		try {
			list = pmLabelDao.findPmlabelMerchantNo();
		} catch (Exception e) {
			logger.error("客户标签表信息分页查询错误", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_FIND_PAGE_ERROR, "客户标签表信息分页查询错误.！", e);
		}
		return list;
	}

	@Override
	public List<FindPmLabelReturnList> findPmlabelShop() throws TsfaServiceException {
		logger.debug("findPmLabelPage() - start");
		List<FindPmLabelReturnList> list = null;
		try {
			list = pmLabelDao.findPmlabelShop();
		} catch (Exception e) {
			logger.error("客户标签表信息分页查询错误", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_FIND_PAGE_ERROR, "客户标签表信息分页查询错误.！", e);
		}
		return list;
	}

	@Override
	public List<FindPmLabelReturnList> findPmlabelAreaCode() throws TsfaServiceException {
		logger.debug("findPmLabelPage(FindPmLabelPage findPmLabelPage={}) - start");
		List<FindPmLabelReturnList> list = null;
		try {
			list = pmLabelDao.findPmlabelAreaCode();
		} catch (Exception e) {
			logger.error("客户标签表信息分页查询错误", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_FIND_PAGE_ERROR, "客户标签表信息分页查询错误.！", e);
		}
		return list;
	}

	@Override
	public List<PmLabelDto> findPmLabelByMerchantNo(String merchantNo) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(merchantNo);
		logger.debug("findPmLabelByMerchantNo(String merchantNo={}) - start", merchantNo);

		List<PmLabelDto> labels = null;
		try {
			labels = pmLabelDao.findPmLabelByMerchantNo(merchantNo);
		} catch (Exception e) {
			logger.error("根据商户号查询标签列表错误", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMLABEL_BY_MERCHANTNO_ERROR, "根据商户号查询标签列表错误.！", e);
		}

		logger.debug("findPmLabelByMerchantNo(String) - end - return value={}", labels);
		return labels;
	}

	@Override
	public void changePmLabel(ChangePmLabel changePmLabel) throws TsfaServiceException {
		logger.debug("changePmLabel(ChangePmLabel changePmLabel={}) - start", changePmLabel);

		AssertUtils.notNullAndEmpty(changePmLabel.getMemberNo(), "会员编号不能为空");
		AssertUtils.notNullAndEmpty(changePmLabel.getShopWx(), "终端微信不能为空");
		try {
			pmLabelPmDao.deleteByMemberNoAndMerchantNo(changePmLabel);// 删除原有标签关联

			// 插入新关联
			if (CollectionUtils.isNotEmpty(changePmLabel.getLabels())) {
				for (PmLabelDto pmLabelDto : changePmLabel.getLabels()) {
					PmLabelPm pmLabelPm = new PmLabelPm();
					pmLabelPm.setMemberNo(changePmLabel.getMemberNo());
					pmLabelPm.setPmLabelCode(pmLabelDto.getCode());
					pmLabelPm.setPmLabelName(pmLabelDto.getLabelName());
					pmLabelPm.setCreateDate(new Date());
					pmLabelPm.setShopWx(changePmLabel.getShopWx());
					pmLabelPm.setType(pmLabelDto.getType());
					pmLabelPmDao.insertOrUpdate(pmLabelPm);
				}
			}
		} catch (Exception e) {
			logger.error("更新客户标签错误", e);
			throw new TsfaServiceException(ErrorCode.CHANGE_PMLABEL_ERROR, "更新客户标签错误.！", e);
		}

		logger.debug("changePmLabel(ChangePmLabel) - end");
	}

	@Override
	public List<PmLabelDto> findPmLabelByMemberNoAndMerchantNo(FindPmLabel findPmLabel) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(findPmLabel.getMemberNo(), "客户编号不能为空");
		AssertUtils.notNullAndEmpty(findPmLabel.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(findPmLabel.getShopWx(), "终端微信不能为空");

		try {
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("memberNo", findPmLabel.getMemberNo());
			paramMap.put("merchantNo", findPmLabel.getMerchantNo());
			paramMap.put("shopWx", findPmLabel.getShopWx());
			return pmLabelPmDao.findPmLabelByCond(paramMap);
		} catch (Exception e) {
			logger.error("根据客户号和商户号查询客户标签错误", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMLABEL_BY_MEMBERNO_AND_MERCHANTNO_ERROR,
					"根据客户号和商户号查询客户标签错误.！", e);
		}
	}

	@Override
	public List<PmLabelDto> findPmLabelByLastTime(Map<String, Object> paramMap) throws TsfaServiceException {
		try {
			AssertUtils.notNullAndEmpty(paramMap.get("merchantNo"));
			AssertUtils.notNullAndEmpty(paramMap.get("lastTime"));
			return pmLabelDao.findPmLabelByLastTime(paramMap);
		} catch (Exception e) {
			logger.error("根据商户号和最后更新时间查询客户标签库异常", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMLABEL_BY_MERCHANTNO_ERROR, "根据商户号和最后更新时间查询客户标签库异常", e);
		}
	}

	@Override
	public List<Map<String, String>> findPmLabelByMerchantNoAndShopWx(Map<String, String> parmap)
			throws TsfaServiceException {
		logger.debug("findPmLabelByMerchantNoAndShopWx(Map<String, String> parmap={}) - start", parmap);
		AssertUtils.notNull(parmap);
		AssertUtils.notNullAndEmpty(parmap.get("merchantNo"), "商户号不能为空");
		AssertUtils.notNullAndEmpty(parmap.get("shopWx"), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(parmap.get("memberNoGm"), "导购号不能为空");

		List<Map<String, String>> returnList = null;
		try {
			returnList = pmLabelPmDao.findPmLabelByMerchantNoAndShopWx(parmap);
		} catch (Exception e) {
			logger.error("客户标签表信息查询错误", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_FIND_ERROR, "客户标签表信息查询错误.！", e);
		}
		return returnList;
	}

	@Override
	public int deletePmLabelByMemberNo(String memberNo, String pmLabelCode, String shopWx) {
		logger.debug("deletePmLabelByMemberNo(String memberNo={},String pmLabelCode={}) - start", memberNo,
				pmLabelCode);
		AssertUtils.notNullAndEmpty(memberNo, "会员编号不能为空");
		AssertUtils.notNullAndEmpty(pmLabelCode, "标签编号不能为空");
		AssertUtils.notNullAndEmpty(shopWx, "终端微信不能为空");
		int count = 0;
		try {
			PmLabelPm pmLabelPm = new PmLabelPm();
			pmLabelPm.setMemberNo(memberNo);
			pmLabelPm.setPmLabelCode(pmLabelCode);
			pmLabelPm.setShopWx(shopWx);
			count = pmLabelPmDao.deleteByPrimaryKey(pmLabelPm);
		} catch (Exception e) {
			logger.error("删除标签错误", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_DEL_ERROR, "删除标签错误！", e);
		}
		return count;
	}

	@Override
	public int addPmLabelByMemberNo(String memberNo, String pmLabelCode, String pmLabelName, String shopWx, int type) {
		logger.debug("deletePmLabelByMemberNo(String memberNo={},String pmLabelCode={},String pmLabelName={}) - start",
				memberNo, pmLabelCode, pmLabelName);
		AssertUtils.notNullAndEmpty(memberNo, "会员编号不能为空");
		AssertUtils.notNullAndEmpty(pmLabelCode, "标签编号不能为空");
		AssertUtils.notNullAndEmpty(pmLabelName, "标签名称不能为空");
		AssertUtils.notNullAndEmpty(shopWx, "终端微信不能为空");
		int count = 0;
		try {
			PmLabelPm pmLabelPm = new PmLabelPm();
			pmLabelPm.setCreateDate(new Date());
			pmLabelPm.setMemberNo(memberNo);
			pmLabelPm.setPmLabelCode(pmLabelCode);
			pmLabelPm.setPmLabelName(pmLabelName);
			pmLabelPm.setShopWx(shopWx);
			pmLabelPm.setType(type);
			count = pmLabelPmDao.insertOrUpdate(pmLabelPm);
		} catch (Exception e) {
			logger.error("删除标签错误", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_DEL_ERROR, "删除标签错误！", e);
		}
		return count;
	}

	@Override
	public int selectCountByMerchantNo(AddPmLabel addPmLabel) {
		return pmLabelDao.selectCountByMerchantNo(addPmLabel);
	}

}
