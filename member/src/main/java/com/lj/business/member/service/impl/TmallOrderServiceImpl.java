package com.lj.business.member.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.httpclient.HttpClientUtils;
import com.lj.base.mvc.web.util.Util;
import com.lj.base.mvc.web.util.XMLParser;
import com.lj.base.mvc.web.util.XmlUtils;
import com.lj.base.mvc.wx.WeixinConfigDto;
import com.lj.base.mvc.wx.WeixinSignUtil;
import com.lj.base.mvc.wx.redpack.SendRedPackPo;
import com.lj.business.common.Constants;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.ITmallOrderDao;
import com.lj.business.member.domain.TmallOrder;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindTmallBonusConfigPage;
import com.lj.business.member.dto.FindTmallBonusRecordPage;
import com.lj.business.member.dto.FindTmallOrderPage;
import com.lj.business.member.dto.TmallBonusConfigDto;
import com.lj.business.member.dto.TmallBonusRecordDto;
import com.lj.business.member.dto.TmallOrderDto;
import com.lj.business.member.emus.BonusStatus;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.ITmallBonusConfigService;
import com.lj.business.member.service.ITmallBonusRecordService;
import com.lj.business.member.service.ITmallOrderService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.GroupName;
import com.lj.cc.enums.SystemAliasName;
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
 * CreateDate: 2019.02.19
 */
@Service
public class TmallOrderServiceImpl implements ITmallOrderService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(TmallOrderServiceImpl.class);
	

	@Resource
	private ITmallOrderDao tmallOrderDao;
	@Autowired
	private ITmallBonusConfigService iTmallBonusConfigService;
	@Autowired
	private ITmallBonusRecordService tmallBonusRecordService;
	@Autowired 
	private IPersonMemberBaseService personMemberBaseService;
	@Autowired 
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	@Override
	public void addTmallOrder(
			TmallOrderDto tmallOrderDto) throws TsfaServiceException {
		logger.debug("addTmallOrder(AddTmallOrder addTmallOrder={}) - start", tmallOrderDto); 

		AssertUtils.notNull(tmallOrderDto);
		try {
			TmallOrder tmallOrder = new TmallOrder();
			//add数据录入
			tmallOrder.setCode(GUID.generateByUUID());
			tmallOrder.setMerchantNo(tmallOrderDto.getMerchantNo());
			tmallOrder.setMemberName(tmallOrderDto.getMemberName());
			tmallOrder.setMobile(tmallOrderDto.getMobile());
			tmallOrder.setNoWw(tmallOrderDto.getNoWw());
			tmallOrder.setOrderNo(tmallOrderDto.getOrderNo());
			tmallOrder.setProductName(tmallOrderDto.getProductName());
			tmallOrder.setProductUrl(tmallOrderDto.getProductUrl());
			tmallOrder.setAmount(tmallOrderDto.getAmount());
			tmallOrder.setOrderDate(tmallOrderDto.getOrderDate());
			tmallOrder.setCommentLevel(tmallOrderDto.getCommentLevel());
			tmallOrder.setCommentCtx(tmallOrderDto.getCommentCtx());
			tmallOrder.setCreateDate(tmallOrderDto.getCreateDate());
			tmallOrder.setCreateId(tmallOrderDto.getCreateId());
			tmallOrder.setRemark(tmallOrderDto.getRemark());
			tmallOrder.setRemark2(tmallOrderDto.getRemark2());
			tmallOrder.setRemark3(tmallOrderDto.getRemark3());
			tmallOrder.setRemark4(tmallOrderDto.getRemark4());
			tmallOrderDao.insert(tmallOrder);
			logger.debug("addTmallOrder(TmallOrderDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增天猫订单信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TMALL_ORDER_ADD_ERROR,"新增天猫订单信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询天猫订单信息
	 *
	 * @param findTmallOrderPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<TmallOrderDto>  findTmallOrders(FindTmallOrderPage findTmallOrderPage)throws TsfaServiceException{
		AssertUtils.notNull(findTmallOrderPage);
		List<TmallOrderDto> returnList=null;
		try {
			returnList = tmallOrderDao.findTmallOrders(findTmallOrderPage);
		} catch (Exception e) {
			logger.error("查找天猫订单信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.TMALL_ORDER_NOT_EXIST_ERROR,"天猫订单信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateTmallOrder(
			TmallOrderDto tmallOrderDto)
			throws TsfaServiceException {
		logger.debug("updateTmallOrder(TmallOrderDto tmallOrderDto={}) - start", tmallOrderDto); //$NON-NLS-1$

		AssertUtils.notNull(tmallOrderDto);
		AssertUtils.notNullAndEmpty(tmallOrderDto.getCode(),"Code不能为空");
		try {
			TmallOrder tmallOrder = new TmallOrder();
			//update数据录入
			tmallOrder.setCode(tmallOrderDto.getCode());
			tmallOrder.setMerchantNo(tmallOrderDto.getMerchantNo());
			tmallOrder.setMemberName(tmallOrderDto.getMemberName());
			tmallOrder.setMobile(tmallOrderDto.getMobile());
			tmallOrder.setNoWw(tmallOrderDto.getNoWw());
			tmallOrder.setOrderNo(tmallOrderDto.getOrderNo());
			tmallOrder.setProductName(tmallOrderDto.getProductName());
			tmallOrder.setProductUrl(tmallOrderDto.getProductUrl());
			tmallOrder.setAmount(tmallOrderDto.getAmount());
			tmallOrder.setOrderDate(tmallOrderDto.getOrderDate());
			tmallOrder.setCommentLevel(tmallOrderDto.getCommentLevel());
			tmallOrder.setCommentCtx(tmallOrderDto.getCommentCtx());
			tmallOrder.setCreateDate(tmallOrderDto.getCreateDate());
			tmallOrder.setCreateId(tmallOrderDto.getCreateId());
			tmallOrder.setRemark(tmallOrderDto.getRemark());
			tmallOrder.setRemark2(tmallOrderDto.getRemark2());
			tmallOrder.setRemark3(tmallOrderDto.getRemark3());
			tmallOrder.setRemark4(tmallOrderDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(tmallOrderDao.updateByPrimaryKeySelective(tmallOrder));
			logger.debug("updateTmallOrder(TmallOrderDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("天猫订单信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TMALL_ORDER_UPDATE_ERROR,"天猫订单信息更新信息错误！",e);
		}
	}

	

	@Override
	public TmallOrderDto findTmallOrder(
			TmallOrderDto tmallOrderDto) throws TsfaServiceException {
		logger.debug("findTmallOrder(FindTmallOrder findTmallOrder={}) - start", tmallOrderDto); //$NON-NLS-1$

		AssertUtils.notNull(tmallOrderDto);
		AssertUtils.notAllNull(tmallOrderDto.getCode(),"Code不能为空");
		try {
			TmallOrder tmallOrder = tmallOrderDao.selectByPrimaryKey(tmallOrderDto.getCode());
			if(tmallOrder == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.TMALL_ORDER_NOT_EXIST_ERROR,"天猫订单信息不存在");
			}
			TmallOrderDto findTmallOrderReturn = new TmallOrderDto();
			//find数据录入
			findTmallOrderReturn.setCode(tmallOrder.getCode());
			findTmallOrderReturn.setMerchantNo(tmallOrder.getMerchantNo());
			findTmallOrderReturn.setMemberName(tmallOrder.getMemberName());
			findTmallOrderReturn.setMobile(tmallOrder.getMobile());
			findTmallOrderReturn.setNoWw(tmallOrder.getNoWw());
			findTmallOrderReturn.setOrderNo(tmallOrder.getOrderNo());
			findTmallOrderReturn.setProductName(tmallOrder.getProductName());
			findTmallOrderReturn.setProductUrl(tmallOrder.getProductUrl());
			findTmallOrderReturn.setAmount(tmallOrder.getAmount());
			findTmallOrderReturn.setOrderDate(tmallOrder.getOrderDate());
			findTmallOrderReturn.setCommentLevel(tmallOrder.getCommentLevel());
			findTmallOrderReturn.setCommentCtx(tmallOrder.getCommentCtx());
			findTmallOrderReturn.setCreateDate(tmallOrder.getCreateDate());
			findTmallOrderReturn.setCreateId(tmallOrder.getCreateId());
			findTmallOrderReturn.setRemark(tmallOrder.getRemark());
			findTmallOrderReturn.setRemark2(tmallOrder.getRemark2());
			findTmallOrderReturn.setRemark3(tmallOrder.getRemark3());
			findTmallOrderReturn.setRemark4(tmallOrder.getRemark4());
			
			logger.debug("findTmallOrder(TmallOrderDto) - end - return value={}", findTmallOrderReturn); //$NON-NLS-1$
			return findTmallOrderReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找天猫订单信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TMALL_ORDER_FIND_ERROR,"查找天猫订单信息信息错误！",e);
		}


	}

	
	@Override
	public Page<TmallOrderDto> findTmallOrderPage(
			FindTmallOrderPage findTmallOrderPage)
			throws TsfaServiceException {
		logger.debug("findTmallOrderPage(FindTmallOrderPage findTmallOrderPage={}) - start", findTmallOrderPage); //$NON-NLS-1$

		AssertUtils.notNull(findTmallOrderPage);
		List<TmallOrderDto> returnList=null;
		int count = 0;
		try {
			returnList = tmallOrderDao.findTmallOrderPage(findTmallOrderPage);
			count = tmallOrderDao.findTmallOrderPageCount(findTmallOrderPage);
		}  catch (Exception e) {
			logger.error("天猫订单信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.TMALL_ORDER_FIND_PAGE_ERROR,"天猫订单信息不存在错误.！",e);
		}
		Page<TmallOrderDto> returnPage = new Page<TmallOrderDto>(returnList, count, findTmallOrderPage);

		logger.debug("findTmallOrderPage(FindTmallOrderPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public String validateOrder(String orderNo,String openId) throws TsfaServiceException{
		logger.debug("String orderNo={}) - start", orderNo);

		AssertUtils.notNullAndEmpty(orderNo,"订单号不能为空");
		String amount = null;
		try {
			//获取订单记录
			FindTmallOrderPage findTmallOrderPage = new FindTmallOrderPage();
			TmallOrderDto paramOrderDto = new TmallOrderDto();
			paramOrderDto.setOrderNo(orderNo);
			findTmallOrderPage.setParam(paramOrderDto);
			TmallOrderDto tmallOrderDto = get(findTmallOrderPage);
			if(null == tmallOrderDto 
					|| StringUtils.isEmpty(tmallOrderDto.getNoWw()) 
					|| tmallOrderDto.getAmount()==null 
					|| tmallOrderDto.getAmount()==0
					) {
				throw new TsfaServiceException(ErrorCode.TMALL_ORDER_NOT_EXIST_ERROR,"验证不成功，请重新发送订单号！");
			}
			
			//五星好评并发布10字以上评价
			if(StringUtils.isNotEmpty(tmallOrderDto.getCommentLevel()) 
					&& StringUtils.isNotEmpty(tmallOrderDto.getCommentCtx())
					&& StringUtils.isNumeric(tmallOrderDto.getCommentLevel())
					&& Integer.valueOf(tmallOrderDto.getCommentLevel()) == 5
					&& tmallOrderDto.getCommentCtx().length()>=10
					) {
				String merchantNo = tmallOrderDto.getMerchantNo();
				
				//订单已领取过红包
				TmallBonusRecordDto record = tmallBonusRecordService.findByOrderNo(orderNo, merchantNo);
				if(null != record) {
					throw new TsfaServiceException("","验证不成功，请重新发送订单号！");
				}
				
				
				//一个客户只可收到一个红包，一个客户指同一旺旺号或同一微信
				//获取红包记录
				FindTmallBonusRecordPage findTmallBonusRecordPage = new FindTmallBonusRecordPage();
				TmallBonusRecordDto paramRecordDto = new TmallBonusRecordDto();
				paramRecordDto.setMerchantNo(merchantNo);
				findTmallBonusRecordPage.setParam(paramRecordDto);
				findTmallBonusRecordPage.setNoWxEq(tmallOrderDto.getNoWw());
				List<TmallBonusRecordDto> listRecord = tmallBonusRecordService.findTmallBonusRecords(findTmallBonusRecordPage);
				if(null != listRecord && listRecord.size()>0) {
					TmallBonusRecordDto bonusRecordDto= listRecord.get(0);
					if(bonusRecordDto.getStatus().equals(BonusStatus.Y.toString())) {
						throw new TsfaServiceException("","您已经领过红包，请继续关注我们其他活动！");
					}else {
						throw new TsfaServiceException("","发送公众号红包失败，稍后为您补发，请耐心等待，感谢您的支持。");
					}
				}
				
				//订单金额是否满足配置
				FindTmallBonusConfigPage findTmallBonusConfigPage = new FindTmallBonusConfigPage();
				TmallBonusConfigDto paramBonusConfig = new TmallBonusConfigDto();
				paramBonusConfig.setMerchantNo(merchantNo);
				findTmallBonusConfigPage.setParam(paramBonusConfig);
				List<TmallBonusConfigDto> list = iTmallBonusConfigService.findTmallBonusConfigs(findTmallBonusConfigPage);
				if(null !=list && list.size()>0) {
					BigDecimal ordAmount = new BigDecimal(tmallOrderDto.getAmount());
					for (TmallBonusConfigDto tmallBonusConfigDto : list) {
						//符合区间范围内
						BigDecimal ordAmtMin =new BigDecimal(tmallBonusConfigDto.getOrdAmtMin());
						BigDecimal ordAmtMax =new BigDecimal(tmallBonusConfigDto.getOrdAmtMax());
						logger.info("是否满足：{}",(ordAmount.compareTo(ordAmtMin)==BigDecimal.ONE.intValue()
								||ordAmount.compareTo(ordAmtMin)==BigDecimal.ZERO.intValue())
								&& ordAmtMax.compareTo(ordAmount)==BigDecimal.ONE.intValue());
						if((ordAmount.compareTo(ordAmtMin)==BigDecimal.ONE.intValue()
								||ordAmount.compareTo(ordAmtMin)==BigDecimal.ZERO.intValue())
								&& ordAmtMax.compareTo(ordAmount)==BigDecimal.ONE.intValue()) {
							//返回红包区间随机数
							int min = tmallBonusConfigDto.getBonuxMin().intValue();
							int max = tmallBonusConfigDto.getBonuxMax().intValue();
							amount =getRandom(min, max);
							logger.info("发放金额：{}",amount);
							break;  
						}
					}
				}
			}
			
		}catch (TsfaServiceException e) {
			throw e;
		}catch (Exception e) {
			logger.error("天猫订单验证错误",e);
			throw new TsfaServiceException(ErrorCode.TMALL_ORDER_VALIDATE_ERROR,"发送公众号红包失败，稍后为您补发，请耐心等待，感谢您的支持。",e);
		}

		logger.debug("findTmallOrderPage(FindTmallOrderPage) - end - return value={}", amount); 
		return  amount;
	} 

	/**
	 * 取区间随机数
	 * @param min
	 * @param max
	 * @return
	 */
	private static String getRandom(int min, int max){
	    Random random = new Random();
	    int s = random.nextInt(max) % (max - min + 1) + min;
	    return String.valueOf(s);
	}


	@Override
	public TmallOrderDto get(FindTmallOrderPage findTmallOrderPage) throws TsfaServiceException {
		logger.debug("get(FindTmallOrderPage findTmallOrderPage={}) - start", findTmallOrderPage); 

		AssertUtils.notNull(findTmallOrderPage);
		TmallOrderDto tmallOrderDto = null;
		try {
			List<TmallOrderDto> list = findTmallOrders(findTmallOrderPage);
			
			if(list != null && list.size()>0) {
				tmallOrderDto= list.get(0);
			}
			logger.debug("get(FindTmallOrderPage findTmallOrderPage) - end - return value={}", tmallOrderDto); 
			return tmallOrderDto;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找天猫订单信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TMALL_ORDER_FIND_ERROR,"查找天猫订单信息信息错误！",e);
		}
	} 
	
	/**
	 * 重发公众号红包
	 * @param code
	 * @throws TsfaServiceException
	 */
	public void repeatRedPack(String code) throws TsfaServiceException{
		
		AssertUtils.notNullAndEmpty(code,"红包记录不存在");
		TmallBonusRecordDto paramBonusRecordDto =new TmallBonusRecordDto();
		paramBonusRecordDto.setCode(code);
		TmallBonusRecordDto  tmallBonusRecordDto =tmallBonusRecordService.findTmallBonusRecord(paramBonusRecordDto);
		AssertUtils.notNull(tmallBonusRecordDto,"红包记录不存在");
		
		//获取用户基本信息
		FindPersonMemberBaseReturn baseReturn= personMemberBaseService.findMemberBaseByNoWxOrAlias(tmallBonusRecordDto.getNoWx(), tmallBonusRecordDto.getNoWx());
		if(null ==baseReturn) {
			logger.error("客户信息不存在，请补充客户信息");
			throw new TsfaServiceException("","客户微信不存在，请补充客户信息!");
		}
		
    	//统一公众号，不区分商户号
		Map<String, String> wxMap = localCacheSystemParams.getSystemParamGroup(SystemAliasName.api.toString(),GroupName.mec_weixin.toString());
		logger.info("公众号配置：{}",wxMap);
		String mchId = wxMap.get(Constants.WEIXIN_MCHID);
		String appId = wxMap.get(Constants.WEIXIN_APPID);
		String appSecret = wxMap.get(Constants.WEIXIN_APPSECRET);
		String ssLfile = wxMap.get(Constants.WEIXIN_SSLFILE);
		
		//仅校验APPID 和 APPSECRET
		if(StringUtils.isEmpty(appId)|| StringUtils.isEmpty(appSecret) || StringUtils.isEmpty(mchId)|| StringUtils.isEmpty(ssLfile)){
			//检测未配置完整微信公众号信息则抛异常。
			throw new TsfaServiceException("", "微信公众号配置信息不全");
		}
		
		SendRedPackPo sendredpack = new SendRedPackPo();
		sendredpack.setAct_name("微信红包");
		sendredpack.setNonce_str(GUID.generateByUUID());
		sendredpack.setRe_openid(baseReturn.getOpenIdGzhWx());
		sendredpack.setClient_ip(tmallBonusRecordDto.getMchBillno());
		sendredpack.setMch_billno(Util.getMchBillno(mchId));
		sendredpack.setMch_id(mchId);
		sendredpack.setRemark(tmallBonusRecordDto.getOrderNo());	//天猫订单
		sendredpack.setSend_name("碧生源");
		sendredpack.setTotal_amount(tmallBonusRecordDto.getBonuxAmt().toString());
		sendredpack.setTotal_num("1");
		sendredpack.setWishing("感谢您的参与，祝您生活愉快！");
		sendredpack.setWxappid(appId);
		sendredpack.setScene_id("PRODUCT_2");	//固定抽奖
		
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("act_name",sendredpack.getAct_name());
		parameters.put("client_ip",sendredpack.getClient_ip());
		parameters.put("mch_billno",sendredpack.getMch_billno());
		parameters.put("mch_id",sendredpack.getMch_id());
		parameters.put("nonce_str",sendredpack.getNonce_str());
		parameters.put("re_openid",sendredpack.getRe_openid());
		parameters.put("remark",sendredpack.getRemark());
		parameters.put("send_name",sendredpack.getSend_name());
		parameters.put("total_amount",sendredpack.getTotal_amount());
		parameters.put("total_num",sendredpack.getTotal_num());
		parameters.put("wishing",sendredpack.getWishing());
		parameters.put("wxappid",sendredpack.getWxappid());
		parameters.put("scene_id",sendredpack.getScene_id());
		// 生成签名
		WeixinConfigDto config = new WeixinConfigDto();
		config.setApiKey(appSecret);
		String sign = WeixinSignUtil.getSign(parameters, config);
		logger.info("生成的签名：{}",sign);
		
		// 扩展xstream，使其支持CDATA块
		String requestXml = XmlUtils.toXml(parameters,sign);
		logger.info("请求xml ={}",requestXml);
		String result;
		try {
			result = HttpClientUtils.sendPost(Constants.sendredpack_url, requestXml,HttpClientUtils.getSSLClientFile(Constants.keyStore_type, ssLfile, mchId));
			logger.info("成功返回XML值：{}",result);
			Map<String, Object> map = XMLParser.getMapFromXML(result);
			logger.info("成功返回MAP值：{}",map);
			
			String status =BonusStatus.N.toString();
			String return_msg = "";
			//通讯成功
			if(map.get("return_code").toString().equals("SUCCESS")) {
				//发红包成功
				if(map.get("result_code").toString().equals("SUCCESS")) {
					status =BonusStatus.Y.toString();
				}
				return_msg = map.get("err_code_des").toString();
			}else {
				return_msg= map.get("return_msg").toString();
			}
			
			
			//修改红包记录信息
			TmallBonusRecordDto updateBonusRecordDto = new TmallBonusRecordDto();
			updateBonusRecordDto.setCode(code);
			updateBonusRecordDto.setStatus(status);
			updateBonusRecordDto.setRemark(return_msg);
			tmallBonusRecordService.updateTmallBonusRecord(updateBonusRecordDto);
			
			if(status.equals(BonusStatus.N.toString())) {
				throw new TsfaServiceException("","重发红包失败，原因："+return_msg);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
    }
}
