package com.ye.business.hx.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.httpclient.HttpClientUtils;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.constant.HxConstant;
import com.ye.business.hx.dao.IHxClueDao;
import com.ye.business.hx.dao.IHxClueOrderDao;
import com.ye.business.hx.dao.IHxPatientDao;
import com.ye.business.hx.dao.IShopServerDao;
import com.ye.business.hx.dao.IShopServerDetailDao;
import com.ye.business.hx.domain.HxClue;
import com.ye.business.hx.domain.HxClueOrder;
import com.ye.business.hx.domain.HxPatient;
import com.ye.business.hx.domain.ShopServer;
import com.ye.business.hx.domain.ShopServerDetail;
import com.ye.business.hx.dto.FindHxCluePage;
import com.ye.business.hx.dto.HxClueDto;
import com.ye.business.hx.dto.params.ClueParams;
import com.ye.business.hx.dto.vo.ClueListVo;
import com.ye.business.hx.dto.vo.shopServerListVo;
import com.ye.business.hx.emus.ClueOrderStatus;
import com.ye.business.hx.emus.ClueOrderType;
import com.ye.business.hx.emus.ClueStatus;
import com.ye.business.hx.emus.ClueValidStatus;
import com.ye.business.hx.emus.PatientType;
import com.ye.business.hx.emus.ServerDetailShop;
import com.ye.business.hx.service.IHxClueService;
import com.ye.business.hx.util.FormatUtil;
import com.ye.business.hx.util.GenerateNo;
import com.ye.business.hx.util.ObjectUtil;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 *         CreateDate: 2019.02.19
 */
@Service
public class HxClueServiceImpl implements IHxClueService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory
			.getLogger(HxClueServiceImpl.class);

	@Resource
	private IHxClueDao hxClueDao;

	@Resource
	private IHxClueOrderDao hxClueOrderDao;

	@Resource
	private IShopServerDetailDao shopServerDetailDao;

	@Resource
	private IHxPatientDao hxPatientDao;

	@Resource
	private IShopServerDao shopServerDao;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Override
	public void addHxClue(HxClueDto hxClueDto) throws TsfaServiceException {
		logger.debug("addHxClue(AddHxClue addHxClue={}) - start", hxClueDto);

		AssertUtils.notNull(hxClueDto);
		try {
			HxClue hxClue = new HxClue();
			// add数据录入
			hxClue.setCode(GUID.getPreUUID());
			hxClue.setName(hxClueDto.getName());
			hxClue.setPhone(hxClueDto.getPhone());
			hxClue.setSex(hxClueDto.getSex());
			hxClue.setAge(hxClueDto.getAge());
			hxClue.setProvince(hxClueDto.getProvince());
			hxClue.setCity(hxClueDto.getCity());
			hxClue.setArea(hxClueDto.getArea());
			hxClue.setAddrInfo(hxClueDto.getAddrInfo());
			hxClue.setSource(hxClueDto.getSource());
			hxClue.setWechatNo(hxClueDto.getWechatNo());
			hxClue.setWechatName(hxClueDto.getWechatName());
			hxClue.setUserType(hxClueDto.getUserType());
			hxClue.setUserPrice(hxClueDto.getUserPrice());
			hxClue.setStatus(hxClueDto.getStatus());
			hxClue.setValidStatus(hxClueDto.getValidStatus());
			hxClue.setCreateTime(hxClueDto.getCreateTime());
			hxClue.setOrderNo(hxClueDto.getOrderNo());
			hxClue.setProject(hxClueDto.getProject());
			hxClue.setReserveDate(hxClueDto.getReserveDate());
			hxClue.setReserveTime(hxClueDto.getReserveTime());
			hxClue.setFollowName(hxClueDto.getFollowName());
			hxClue.setWishLevel(hxClueDto.getWishLevel());
			hxClue.setComplaint(hxClueDto.getComplaint());
			hxClue.setRemark(hxClueDto.getRemark());
			hxClue.setPatientNo(hxClueDto.getPatientNo());
			hxClueDao.insertSelective(hxClue);
			logger.debug("addHxClue(HxClueDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增线索信息错误！", e);
			throw new TsfaServiceException(ErrorCode.HX_CLUE_ADD_ERROR,
					"新增线索信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询线索信息
	 *
	 * @param findHxCluePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<HxClueDto> findHxClues(FindHxCluePage findHxCluePage)
			throws TsfaServiceException {
		AssertUtils.notNull(findHxCluePage);
		List<HxClueDto> returnList = null;
		try {
			returnList = hxClueDao.findHxClues(findHxCluePage);
		} catch (Exception e) {
			logger.error("查找线索信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.HX_CLUE_NOT_EXIST_ERROR,
					"线索信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateHxClue(HxClueDto hxClueDto) throws TsfaServiceException {
		logger.debug("updateHxClue(HxClueDto hxClueDto={}) - start", hxClueDto); //$NON-NLS-1$

		AssertUtils.notNull(hxClueDto);
		AssertUtils.notNullAndEmpty(hxClueDto.getCode(), "Code不能为空");
		try {
			HxClue hxClue = new HxClue();
			// update数据录入
			hxClue.setCode(hxClueDto.getCode());
			hxClue.setName(hxClueDto.getName());
			hxClue.setPhone(hxClueDto.getPhone());
			hxClue.setSex(hxClueDto.getSex());
			hxClue.setAge(hxClueDto.getAge());
			hxClue.setProvince(hxClueDto.getProvince());
			hxClue.setCity(hxClueDto.getCity());
			hxClue.setArea(hxClueDto.getArea());
			hxClue.setAddrInfo(hxClueDto.getAddrInfo());
			hxClue.setSource(hxClueDto.getSource());
			hxClue.setWechatNo(hxClueDto.getWechatNo());
			hxClue.setWechatName(hxClueDto.getWechatName());
			hxClue.setUserType(hxClueDto.getUserType());
			hxClue.setUserPrice(hxClueDto.getUserPrice());
			hxClue.setStatus(hxClueDto.getStatus());
			hxClue.setValidStatus(hxClueDto.getValidStatus());
			hxClue.setCreateTime(hxClueDto.getCreateTime());
			hxClue.setOrderNo(hxClueDto.getOrderNo());
			hxClue.setProject(hxClueDto.getProject());
			hxClue.setReserveDate(hxClueDto.getReserveDate());
			hxClue.setReserveTime(hxClueDto.getReserveTime());
			hxClue.setFollowName(hxClueDto.getFollowName());
			hxClue.setWishLevel(hxClueDto.getWishLevel());
			hxClue.setComplaint(hxClueDto.getComplaint());
			hxClue.setRemark(hxClueDto.getRemark());
			hxClue.setPatientNo(hxClueDto.getPatientNo());
			AssertUtils.notUpdateMoreThanOne(hxClueDao
					.updateByPrimaryKeySelective(hxClue));
			logger.debug("updateHxClue(HxClueDto) - end - return"); //$NON-NLS-1$
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("线索信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.HX_CLUE_UPDATE_ERROR,
					"线索信息更新信息错误！", e);
		}
	}

	@Override
	public HxClueDto findHxClue(HxClueDto hxClueDto)
			throws TsfaServiceException {
		logger.debug("findHxClue(FindHxClue findHxClue={}) - start", hxClueDto); //$NON-NLS-1$

		AssertUtils.notNull(hxClueDto);
		AssertUtils.notAllNull(hxClueDto.getCode(), "Code不能为空");
		try {
			HxClue hxClue = hxClueDao.selectByPrimaryKey(hxClueDto.getCode());
			if (hxClue == null) {
				return null;
				// throw new
				// TsfaServiceException(ErrorCode.HX_CLUE_NOT_EXIST_ERROR,"线索信息不存在");
			}
			HxClueDto findHxClueReturn = new HxClueDto();
			// find数据录入
			findHxClueReturn.setCode(hxClue.getCode());
			findHxClueReturn.setName(hxClue.getName());
			findHxClueReturn.setPhone(hxClue.getPhone());
			findHxClueReturn.setSex(hxClue.getSex());
			findHxClueReturn.setAge(hxClue.getAge());
			findHxClueReturn.setProvince(hxClue.getProvince());
			findHxClueReturn.setCity(hxClue.getCity());
			findHxClueReturn.setArea(hxClue.getArea());
			findHxClueReturn.setAddrInfo(hxClue.getAddrInfo());
			findHxClueReturn.setSource(hxClue.getSource());
			findHxClueReturn.setWechatNo(hxClue.getWechatNo());
			findHxClueReturn.setWechatName(hxClue.getWechatName());
			findHxClueReturn.setUserType(hxClue.getUserType());
			findHxClueReturn.setUserPrice(hxClue.getUserPrice());
			findHxClueReturn.setStatus(hxClue.getStatus());
			findHxClueReturn.setValidStatus(hxClue.getValidStatus());
			findHxClueReturn.setCreateTime(hxClue.getCreateTime());
			findHxClueReturn.setOrderNo(hxClue.getOrderNo());
			findHxClueReturn.setProject(hxClue.getProject());
			findHxClueReturn.setReserveDate(hxClue.getReserveDate());
			findHxClueReturn.setReserveTime(hxClue.getReserveTime());
			findHxClueReturn.setFollowName(hxClue.getFollowName());
			findHxClueReturn.setWishLevel(hxClue.getWishLevel());
			findHxClueReturn.setComplaint(hxClue.getComplaint());
			findHxClueReturn.setRemark(hxClue.getRemark());
			findHxClueReturn.setPatientNo(hxClue.getPatientNo());
			logger.debug(
					"findHxClue(HxClueDto) - end - return value={}", findHxClueReturn); //$NON-NLS-1$
			return findHxClueReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找线索信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.HX_CLUE_FIND_ERROR,
					"查找线索信息信息错误！", e);
		}

	}

	@Override
	public Page<HxClueDto> findHxCluePage(FindHxCluePage findHxCluePage)
			throws TsfaServiceException {
		logger.debug(
				"findHxCluePage(FindHxCluePage findHxCluePage={}) - start", findHxCluePage); //$NON-NLS-1$

		AssertUtils.notNull(findHxCluePage);
		List<HxClueDto> returnList = null;
		int count = 0;
		try {
			returnList = hxClueDao.findHxCluePage(findHxCluePage);
			count = hxClueDao.findHxCluePageCount(findHxCluePage);
		} catch (Exception e) {
			logger.error("线索信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.HX_CLUE_FIND_PAGE_ERROR,
					"线索信息不存在错误.！", e);
		}
		Page<HxClueDto> returnPage = new Page<HxClueDto>(returnList, count,
				findHxCluePage);

		logger.debug(
				"findHxCluePage(FindHxCluePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return returnPage;
	}

	@Override
	public Map<String, Object> clueQuantity() {
		try {
			HxClue clue = new HxClue();
			clue.setValidStatus(ClueValidStatus.VALID.toString());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", hxClueDao.clueQuantity(clue));
			clue.setStatus(ClueStatus.HASG.toString());
			map.put("hasrob", hxClueDao.clueQuantity(clue));
			return map;
		} catch (Exception e) {
			logger.error("查询线索数量错误", e);
			throw e;
		}
	}

	@Override
	public Page<ClueListVo> list(ClueParams params) {
		List<ClueListVo> list = null;
		int count = 0;
		try {
			count = hxClueDao.queryClueCount(params);
			list = hxClueDao.queryClueList(params);
			if (ObjectUtil.isNotEmpty(list)) {
				for (ClueListVo clue : list) {
					// 查询购买/锁定门诊
					if (clue.getStatus().equals(ClueStatus.FREEZE.toString())
							|| clue.getStatus().equals(
									ClueStatus.HASG.toString())) {
						HxClueOrder clueOrder = hxClueOrderDao
								.selectByClueCode(clue.getCode());
						if (clueOrder != null) {
							if (ObjectUtil.isNotEmpty(clueOrder
									.getMerchantName())) {
								clue.setClinicName(FormatUtil
										.formatClinic(clueOrder
												.getMerchantName()));
							}
						}
					}
					// 格式化姓名手机号
					clue.setName(FormatUtil.formatName(clue.getName()));
					clue.setPhone(FormatUtil.formatMobile(clue.getPhone()));
				}
			}
		} catch (Exception e) {
			logger.error("查询线索列表错误", e);
			throw e;
		}
		return new Page<ClueListVo>(list, count, params);
	}

	@Override
	public Page<ClueListVo> acceptlist(ClueParams params) {
		if (ObjectUtil.isEmpty(params.getMemberNoMerchant())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"商户编号不能为空");
		}
		List<ClueListVo> list = null;
		int count = 0;
		try {
			count = hxClueOrderDao.queryAcceptListCount(params);
			list = hxClueOrderDao.queryAcceptList(params);
			if (ObjectUtil.isNotEmpty(list)) {
				for (ClueListVo clue : list) {
					// 状态 锁定和取消 格式化姓名手机号
					if (clue.getStatus().equals(
							ClueOrderStatus.FREEZE.toString())
							|| clue.getStatus().equals(
									ClueOrderStatus.CANCEL.toString())) {
						clue.setName(FormatUtil.formatName(clue.getName()));
						clue.setPhone(FormatUtil.formatMobile(clue.getPhone()));
					}
				}
			}
		} catch (Exception e) {
			logger.error("查询线索列表错误", e);
			throw e;
		}
		return new Page<ClueListVo>(list, count, params);
	}

	@Override
	public List<shopServerListVo> shopserverlist(ClueParams params) {
		AssertUtils.notNullAndEmpty(params.getUserType(), "用户类型不能为空");
		AssertUtils.notNullAndEmpty(params.getMemberNoMerchant(), "商户编号不能为空");
		try {
			return shopServerDetailDao.shopServerList(params);
		} catch (Exception e) {
			logger.error("店铺服务列表", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String visiting(ClueParams params) {

		if (ObjectUtil.isEmpty(params.getMemberNoMerchant(),
				params.getClueCode(), params.getServerCode())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"接诊-参数错误！");
		}
		// 验证线索是否可接诊
		HxClue clue = hxClueDao.selectByPrimaryKey(params.getClueCode());
		ShopServerDetail server = shopServerDetailDao.selectByPrimaryKey(params
				.getServerCode());
		if (clue == null || server == null)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"接诊-参数错误！");
		// 验收线索是否可接诊
		if (!clue.getStatus().equals(ClueStatus.CANG.toString()))
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"线索已被接诊");
		if (server.getUnuseNum().intValue() <= 0)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"服务次数不足");
		Date dt = new Date();
		HxClueOrder clueOrder = new HxClueOrder();
		clueOrder.setCode(GUID.generateCode());
		clueOrder.setClueCode(params.getClueCode());
		clueOrder.setMemberNo(params.getMemberNoGuid());
		clueOrder.setMemberName(params.getMemberNameGuid());
		clueOrder.setShopNo(params.getShopNo());
		clueOrder.setShopName(params.getShopName());
		clueOrder.setMerchantNo(params.getMemberNoMerchant());
		clueOrder.setMerchantName(params.getMemberNameMerchant());
		clueOrder.setShopServerCode(server.getShopServerCode());
		clueOrder.setServerName(server.getServerName());
		clueOrder.setServerCode(server.getCode());
		clueOrder.setUserType(clue.getUserType());
		clueOrder.setUserPrice(clue.getUserPrice());
		clueOrder.setCreateId(params.getMemberNoGuid());
		clueOrder.setCreateDate(dt);
		clueOrder.setType(ClueOrderType.VISITING.toString());

		server.setUseNum(server.getUseNum() + 1);// 已使用数量 +1
		server.setUnuseNum(server.getUnuseNum() - 1);// 剩余数量 -1
		server.setUpdateId(params.getMemberNoGuid());
		server.setUpdateDate(dt);

		int res = 0;
		String cluestatus = null;// 工单线索状态
		// 判断是否接诊还是到店
		if (server.getIsShop().equals(ServerDetailShop.YES.toString())) {
			// 到店流程
			clueOrder.setStatus(ClueOrderStatus.FREEZE.toString());
			clue.setStatus(ClueStatus.FREEZE.toString());
			// 到店冻结数量+1
			server.setFreezeNum(server.getFreezeNum() + 1);
			cluestatus = "2";
		} else {
			// 接诊流程
			clueOrder.setStatus(ClueOrderStatus.OK.toString());
			clue.setStatus(ClueStatus.HASG.toString());
			// 接诊添加患者
			hxPatientDao.insertSelective(modelByClue(clue, params));//好乐美接诊后建患者
			cluestatus = "1";
		}
		// 添加线索订单
		hxClueOrderDao.insertSelective(clueOrder);
		// 修改线索表状态
		res = hxClueDao.updateByPrimaryKeySelective(clue);
		if (res != 1)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"接诊失败");
		// 修改服务数量
		res = shopServerDetailDao.updateByPrimaryKeySelective(server);
		if (res != 1)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"接诊失败");
		// TODO 同步工单状态

		String gdHost = localCacheSystemParams.getSystemParam(
				HxConstant.systemAliasName, HxConstant.group_gd,
				HxConstant.host_gd);
		// TODO 同步工单状态
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderno", clue.getOrderNo());
		map.put("clinicname", clueOrder.getMerchantName());
		map.put("cluestatus", cluestatus);
		String result = HttpClientUtils.postToWeb(gdHost + "/clue/visiting",
				map);
		if (ObjectUtil.isEmpty(result)) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"接诊-同步工单状态失败");
		}
		JSONObject obj = JSONObject.parseObject(result);
		if (obj == null || obj.getIntValue("result") != 1) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"接诊-同步工单状态失败");
		}
		return "success";
	}

	/**
	 * 线索生成患者实体
	 * 
	 * @param clue
	 * @param params
	 * @return
	 */
	private HxPatient modelByClue(HxClue clue, ClueParams params) {
		HxPatient model = new HxPatient();
		model.setCode(GUID.generateCode());
		model.setShopNo(params.getShopNo());
		model.setShopName(params.getShopName());
		model.setMerchantNo(params.getMemberNoMerchant());
		model.setMerchantName(params.getMemberNameMerchant());
		model.setName(clue.getName());
		model.setSex(clue.getSex());
		model.setType(PatientType.PT.toString());
		model.setCaseNo(GenerateNo.getInstance().getCaseNo(
				PatientType.PT.toString()));
		model.setAge(clue.getAge());
		model.setPhone(clue.getPhone());
		model.setWechat(clue.getWechatNo());
		model.setProvince(clue.getProvince());
		model.setCity(clue.getCity());
		model.setArea(clue.getArea());
		model.setAddrDetail(clue.getAddrInfo());
		model.setAddrInfo(clue.getAddrInfo());
		model.setRemark(clue.getComplaint());
		model.setCreateId(params.getMemberNoGuid());
		model.setCreateDate(new Date());
		model.setClueCode(clue.getCode());
		return model;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String toshop(ClueParams params) {
		if (ObjectUtil.isEmpty(params.getClueCode(),
				params.getMemberNoMerchant())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"到店-参数错误！");
		}
		HxClueOrder order = hxClueOrderDao.selectByPrimaryKey(params
				.getClueCode());
		if (order == null) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"到店-参数错误！");
		}
		if (!order.getStatus().equals(ClueOrderStatus.FREEZE.toString())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"非法操作！");
		}
		if (!order.getMerchantNo().equals(params.getMemberNoMerchant())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"非法操作！");
		}
		HxClue clue = hxClueDao.selectByPrimaryKey(order.getClueCode());
		ShopServerDetail server = shopServerDetailDao.selectByPrimaryKey(order
				.getServerCode());
		Date dt = new Date();
		// 修改订单状态
		order.setStatus(ClueOrderStatus.OK.toString());
		order.setUpdateId(params.getMemberNoGuid());
		order.setUpdateDate(dt);
		int res = hxClueOrderDao.updateByPrimaryKeySelective(order);
		if (res != 1)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"确认到店失败");
		// 修改线索状态
		clue.setStatus(ClueStatus.HASG.toString());
		res = hxClueDao.updateByPrimaryKeySelective(clue);
		if (res != 1)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"确认到店失败");
		// 修改服务数量
		server.setFreezeNum(server.getFreezeNum() - 1);// 冻结数量-1
		server.setUpdateId(params.getMemberNoGuid());
		server.setUpdateDate(dt);
		res = shopServerDetailDao.updateByPrimaryKeySelective(server);
		if (res != 1)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"确认到店失败");
		// 添加患者
		params.setMemberNameMerchant(order.getMerchantName());
		hxPatientDao.insertSelective(modelByClue(clue, params));//好乐美：确认到店 建患者

		// 同步工单状态
		String gdHost = localCacheSystemParams.getSystemParam(
				HxConstant.systemAliasName, HxConstant.group_gd,
				HxConstant.host_gd);
		// TODO 同步工单状态
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderno", clue.getOrderNo());
		String result = HttpClientUtils.postToWeb(gdHost + "/clue/toshop", map);
		if (ObjectUtil.isEmpty(result)) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"到店-同步工单状态失败");
		}
		JSONObject obj = JSONObject.parseObject(result);
		if (obj == null || obj.getIntValue("result") != 1) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"到店-同步工单状态失败");
		}

		return "success";
	}

	@Override
	public List<ClueListVo> broadcast() {
		try {
			List<ClueListVo> list = hxClueOrderDao
					.broadCastList(ClueOrderStatus.OK.toString());
			if (ObjectUtil.isNotEmpty(list)) {
				// 格式化门诊姓名
				for (ClueListVo clueOrder : list) {
					clueOrder.setClinicName(FormatUtil.formatClinic(clueOrder
							.getClinicName()));
				}
			}
			return list;
		} catch (Exception e) {
			logger.error("查询播报错误", e);
			throw e;
		}
	}

	@Override
	public void createClue(HxClue clue) {
		// 验证参数
		AssertUtils.notNull(clue);
		if (ObjectUtil.isEmpty(clue.getProvince(), clue.getCity(),
				clue.getArea())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"请选择地区");
		}
		if (ObjectUtil.isEmpty(clue.getUserType())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"请选择用户类型");
		}
		if (ObjectUtil.isEmpty(clue.getOrderNo())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"工单号不能为空");
		}
		clue.setAddrInfo(clue.getProvince() + clue.getCity() + clue.getArea());
		clue.setValidStatus(ClueValidStatus.VALID.toString());
		// 根据工单查询线索
		HxClue model = hxClueDao.selectByOrderNo(clue.getOrderNo());
		if (model == null) {
			// 添加
			clue.setCode(GUID.generateCode());
			clue.setStatus(ClueStatus.CANG.toString());
			clue.setCreateTime(new Date());
			hxClueDao.insertSelective(clue);
		} else {
			// 修改
			clue.setCode(model.getCode());
			int res = hxClueDao.updateByPrimaryKeySelective(clue);
			if (res <= 0)
				throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
						"操作失败");
		}
	}

	@Override
	public Page<shopServerListVo> clinicServices(ClueParams params) {
		AssertUtils.notNullAndEmpty(params.getUserType(), "用户类型不能为空");
		List<shopServerListVo> list = null;
		int count = 0;
		try {
			list = shopServerDetailDao.clinicServicesList(params);
			count = shopServerDetailDao.clinicServicesCount(params);
		} catch (Exception e) {
			logger.error("工单查询店铺服务列表错误", e);
			throw e;
		}
		return new Page<shopServerListVo>(list, count, params);
	}

	@Override
	public void upstatus(String orderno) {
		AssertUtils.notNullAndEmpty(orderno, "工单号不能为空");
		try {
			HxClue clue = hxClueDao.selectByOrderNo(orderno);
			if (clue != null) {
				clue.setValidStatus(ClueValidStatus.INVALID.toString());
				int res = hxClueDao.updateByPrimaryKeySelective(clue);
				if (res <= 0) {
					throw new TsfaServiceException(
							ErrorCode.HX_CLUE_PARAMS_ERROR, "修改线索为无效失败");
				}
			}
		} catch (Exception e) {
			logger.error("修改线索为无效错误", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void visiting_order(ClueParams params) {
		// params.getMemberNoMerchant()
		if (ObjectUtil.isEmpty(params.getOrderno(), params.getServerCode())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"派单-参数错误！");
		}
		// 验证线索是否可接诊
		HxClue clue = hxClueDao.selectByOrderNo(params.getOrderno());
		ShopServerDetail server = shopServerDetailDao.selectByPrimaryKey(params
				.getServerCode());
		if (clue == null || server == null)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"派单-参数错误！");
		// 验收线索是否可接诊
		if (!clue.getStatus().equals(ClueStatus.CANG.toString()))
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"线索已被接诊");
		if (server.getUnuseNum().intValue() <= 0)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"服务次数不足");
		// 查询商户
		ShopServer shop = shopServerDao.selectByPrimaryKey(server
				.getShopServerCode());

		Date dt = new Date();
		HxClueOrder clueOrder = new HxClueOrder();
		clueOrder.setCode(GUID.generateCode());
		clueOrder.setClueCode(clue.getCode());

		clueOrder.setShopNo(shop.getShopNo());
		clueOrder.setShopName(shop.getShopName());
		clueOrder.setMerchantNo(shop.getMerchantNo());
		clueOrder.setMerchantName(shop.getMerchantName());
		clueOrder.setShopServerCode(server.getShopServerCode());
		clueOrder.setServerName(server.getServerName());
		clueOrder.setServerCode(server.getCode());
		clueOrder.setUserType(clue.getUserType());
		clueOrder.setUserPrice(clue.getUserPrice());
		clueOrder.setCreateDate(dt);
		clueOrder.setType(ClueOrderType.ORDER.toString());

		server.setUseNum(server.getUseNum() + 1);// 已使用数量 +1
		server.setUnuseNum(server.getUnuseNum() - 1);// 剩余数量 -1
		server.setUpdateDate(dt);

		int res = 0;
		// 判断是否接诊还是到店
		if (server.getIsShop().equals(ServerDetailShop.YES.toString())) {
			// 到店流程
			clueOrder.setStatus(ClueOrderStatus.FREEZE.toString());
			clue.setStatus(ClueStatus.FREEZE.toString());
			// 到店冻结数量+1
			server.setFreezeNum(server.getFreezeNum() + 1);
		} else {
			// 接诊流程
			clueOrder.setStatus(ClueOrderStatus.OK.toString());
			clue.setStatus(ClueStatus.HASG.toString());
			// 接诊添加患者
			hxPatientDao.insertSelective(modelByClue(clue, params));//工单派单后，添加患者
		}
		// 添加线索订单
		hxClueOrderDao.insertSelective(clueOrder);
		// 修改线索表状态
		res = hxClueDao.updateByPrimaryKeySelective(clue);
		if (res != 1)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"派单失败");
		// 修改服务数量
		res = shopServerDetailDao.updateByPrimaryKeySelective(server);
		if (res != 1)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"派单失败");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void confirmorcancel(ClueParams params) {
		if (ObjectUtil.isEmpty(params.getOrderno(), params.getStatus())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"参数错误！");
		}
		HxClue clue = hxClueDao.selectByOrderNo(params.getOrderno());
		if (clue == null) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"参数错误！");
		}
		HxClueOrder order = hxClueOrderDao.selectByClueCode(clue.getCode());
		if (order == null) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"参数错误！");
		}
		if (!order.getStatus().equals(ClueOrderStatus.FREEZE.toString())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"非法操作！");
		}
		ShopServerDetail server = shopServerDetailDao.selectByPrimaryKey(order
				.getServerCode());
		Date dt = new Date();

		if (params.getStatus().equals(ClueOrderStatus.OK.toString())) {
			// 确认到店
			order.setStatus(ClueOrderStatus.OK.toString());
			clue.setStatus(ClueStatus.HASG.toString());
			// 添加患者
			params.setShopNo(order.getShopNo());
			params.setShopName(order.getShopName());
			params.setMemberNoMerchant(order.getMerchantNo());
			params.setMemberNameMerchant(order.getMerchantName());
			hxPatientDao.insertSelective(modelByClue(clue, params));//工单确认到店后 建患者
		} else {
			// 取消到店
			order.setStatus(ClueOrderStatus.CANCEL.toString());
			clue.setStatus(ClueStatus.CANG.toString());
			server.setUseNum(server.getUseNum() - 1);
			server.setUnuseNum(server.getUnuseNum() + 1);
		}
		order.setUpdateDate(dt);
		// 修改订单状态
		int res = hxClueOrderDao.updateByPrimaryKeySelective(order);
		if (res != 1)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"操作失败");

		// 修改线索状态
		res = hxClueDao.updateByPrimaryKeySelective(clue);
		if (res != 1)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"操作失败");

		// 修改服务数量
		server.setFreezeNum(server.getFreezeNum() - 1);// 冻结数量-1
		server.setUpdateDate(dt);
		res = shopServerDetailDao.updateByPrimaryKeySelective(server);
		if (res != 1)
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"操作失败");
	}

	@Override
	public int clientnum(ClueParams params) {
		if (ObjectUtil.isEmpty(params.getMemberNoMerchant())) {
			throw new TsfaServiceException(ErrorCode.HX_CLUE_PARAMS_ERROR,
					"商户号不能为空");
		}
		params.setType(ClueOrderType.ORDER.toString());
		params.setDate(DateUtils.formatDate(new Date(),
				DateUtils.PATTERN_yyyy_MM_dd));
		int num = 0;
		try {
			num = hxClueOrderDao.queryClientCount(params);
		} catch (Exception e) {
			logger.error("查询今日推荐客户数量错误", e);
			throw e;
		}
		return num;
	}
}
