package com.lj.business.weixin.service.impl;

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
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.KeyConstant;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.friends.AddRedPackMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IRedPackFacade;
import com.lj.business.weixin.constant.ErrorCodeWxRedpackDetailInfo;
import com.lj.business.weixin.dao.IWxRedpackDetailInfoDao;
import com.lj.business.weixin.domain.WxRedpackDetailInfo;
import com.lj.business.weixin.dto.FindWxRedpackDetailInfoPage;
import com.lj.business.weixin.dto.UpdateRedpackDetailByPoll;
import com.lj.business.weixin.dto.WxJobRedPackInfoDto;
import com.lj.business.weixin.dto.WxRedpackDetailInfoDto;
import com.lj.business.weixin.emus.RedPackStatus;
import com.lj.business.weixin.emus.RedPackTypeEnum;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.RedisCache;
import com.lj.distributelock.IDistributeLock;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 罗丹青
 * 
 * 
 * CreateDate: 2017-08-22
 */
@Service
public class WxRedpackDetailInfoServiceImpl implements IWxRedpackDetailInfoService { 

	
	private static final String REDIS_KEY_LOCK_NOWX = "REDIS-KEY-LOCK-NOWX-";
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WxRedpackDetailInfoServiceImpl.class);
	@Autowired 
	ICommonService commonService;
	@Resource
	private IWxRedpackDetailInfoDao wxRedpackDetailInfoDao;
	@Resource
	private IPersonMemberService personMemberService;
	@Resource
	private IShopTerminalService shopTerminalService;
    @Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Autowired 
    private RedisCache redisCache;
    @Resource 
	private IDistributeLock redisLock;
	
	@Override
	public void addWxRedpackDetailInfo(
			WxRedpackDetailInfoDto wxRedpackDetailInfoDto) throws TsfaServiceException {
		logger.debug("addWxRedpackDetailInfo(AddWxRedpackDetailInfo addWxRedpackDetailInfo={}) - start", wxRedpackDetailInfoDto); 

		AssertUtils.notNull(wxRedpackDetailInfoDto);
		try {
			WxRedpackDetailInfo wxRedpackDetailInfo = new WxRedpackDetailInfo();
			//add数据录入
			wxRedpackDetailInfo.setCode(GUID.generateCode());
			wxRedpackDetailInfo.setRedPackId(wxRedpackDetailInfoDto.getRedPackId());
			wxRedpackDetailInfo.setBatchCode(wxRedpackDetailInfoDto.getBatchCode());
			wxRedpackDetailInfo.setNoWxShop(wxRedpackDetailInfoDto.getNoWxShop());
			wxRedpackDetailInfo.setMemberNameGm(wxRedpackDetailInfoDto.getMemberNameGm());
			wxRedpackDetailInfo.setMemberNoGm(wxRedpackDetailInfoDto.getMemberNoGm());
//			wxRedpackDetailInfo.setShopNo(wxRedpackDetailInfoDto.getShopNo());
			wxRedpackDetailInfo.setMerchantNo(wxRedpackDetailInfoDto.getMerchantNo());
			wxRedpackDetailInfo.setMemberNo(wxRedpackDetailInfoDto.getMemberNo());
			wxRedpackDetailInfo.setMemberName(wxRedpackDetailInfoDto.getMemberName());
			wxRedpackDetailInfo.setNoWx(wxRedpackDetailInfoDto.getNoWx());
			if(StringUtils.isNotEmpty(wxRedpackDetailInfoDto.getContent())){
				wxRedpackDetailInfo.setContent(wxRedpackDetailInfoDto.getContent());
			}else {
				wxRedpackDetailInfo.setContent("恭喜发财,大吉大利");
			}
			wxRedpackDetailInfo.setAmount(wxRedpackDetailInfoDto.getAmount());
			wxRedpackDetailInfo.setStatus(wxRedpackDetailInfoDto.getStatus());
			wxRedpackDetailInfo.setCreateDate(wxRedpackDetailInfoDto.getCreateDate());
			wxRedpackDetailInfo.setSendDate(wxRedpackDetailInfoDto.getSendDate());
			wxRedpackDetailInfo.setReceiveDate(wxRedpackDetailInfoDto.getReceiveDate());
			wxRedpackDetailInfo.setType(wxRedpackDetailInfoDto.getType() == null ? 0 : wxRedpackDetailInfoDto.getType());
			wxRedpackDetailInfoDao.insert(wxRedpackDetailInfo);
			logger.debug("addWxRedpackDetailInfo(WxRedpackDetailInfoDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增微信红包任务明细信息错误！",e);
			throw new TsfaServiceException(ErrorCodeWxRedpackDetailInfo.WX_REDPACK_DETAIL_INFO_ADD_ERROR,"新增微信红包任务明细信息错误！",e);
		}
	}
	
	@Override
    public WxRedpackDetailInfo addWxRedpackDetailInfo2(
            WxRedpackDetailInfoDto wxRedpackDetailInfoDto) throws TsfaServiceException {
        logger.debug("addWxRedpackDetailInfo(AddWxRedpackDetailInfo addWxRedpackDetailInfo={}) - start", wxRedpackDetailInfoDto); 

        AssertUtils.notNull(wxRedpackDetailInfoDto);
        try {
            WxRedpackDetailInfo wxRedpackDetailInfo = new WxRedpackDetailInfo();
            //add数据录入
            wxRedpackDetailInfo.setCode(GUID.generateCode());
            wxRedpackDetailInfo.setRedPackId(wxRedpackDetailInfoDto.getRedPackId());
            wxRedpackDetailInfo.setBatchCode(wxRedpackDetailInfoDto.getBatchCode());
            wxRedpackDetailInfo.setNoWxShop(wxRedpackDetailInfoDto.getNoWxShop());
            wxRedpackDetailInfo.setMemberNameGm(wxRedpackDetailInfoDto.getMemberNameGm());
            wxRedpackDetailInfo.setMemberNoGm(wxRedpackDetailInfoDto.getMemberNoGm());
//            wxRedpackDetailInfo.setShopNo(wxRedpackDetailInfoDto.getShopNo());
            wxRedpackDetailInfo.setMemberNo(wxRedpackDetailInfoDto.getMemberNo());
            wxRedpackDetailInfo.setMemberName(wxRedpackDetailInfoDto.getMemberName());
            wxRedpackDetailInfo.setNoWx(wxRedpackDetailInfoDto.getNoWx());
            if(StringUtils.isNotEmpty(wxRedpackDetailInfoDto.getContent())){
                wxRedpackDetailInfo.setContent(wxRedpackDetailInfoDto.getContent());
            }else {
                wxRedpackDetailInfo.setContent("恭喜发财,大吉大利");
            }
            wxRedpackDetailInfo.setAmount(wxRedpackDetailInfoDto.getAmount());
            wxRedpackDetailInfo.setStatus(wxRedpackDetailInfoDto.getStatus());
            wxRedpackDetailInfo.setCreateDate(wxRedpackDetailInfoDto.getCreateDate());
            wxRedpackDetailInfo.setSendDate(wxRedpackDetailInfoDto.getSendDate());
            wxRedpackDetailInfo.setReceiveDate(wxRedpackDetailInfoDto.getReceiveDate());
            wxRedpackDetailInfo.setType(wxRedpackDetailInfoDto.getType() == null ? 0 : wxRedpackDetailInfoDto.getType());
            wxRedpackDetailInfoDao.insert(wxRedpackDetailInfo);
            logger.debug("addWxRedpackDetailInfo(WxRedpackDetailInfoDto) - end - return"); 
            return wxRedpackDetailInfo;
        }catch (TsfaServiceException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }  catch (Exception e) {
            logger.error("新增微信红包任务明细信息错误！",e);
            throw new TsfaServiceException(ErrorCodeWxRedpackDetailInfo.WX_REDPACK_DETAIL_INFO_ADD_ERROR,"新增微信红包任务明细信息错误！",e);
        }
    }
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询微信红包任务明细信息
	 *
	 * @param findWxRedpackDetailInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017年07月10日
	 *
	 */
	public List<WxRedpackDetailInfoDto>  findWxRedpackDetailInfos(FindWxRedpackDetailInfoPage findWxRedpackDetailInfoPage)throws TsfaServiceException{
		AssertUtils.notNull(findWxRedpackDetailInfoPage);
		List<WxRedpackDetailInfoDto> returnList=null;
		try {
				returnList = wxRedpackDetailInfoDao.findWxRedpackDetailInfos(findWxRedpackDetailInfoPage);
		} catch (Exception e) {
			logger.error("查找微信红包任务明细信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeWxRedpackDetailInfo.WX_REDPACK_DETAIL_INFO_NOT_EXIST_ERROR,"微信红包任务明细信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateWxRedpackDetailInfo(
			WxRedpackDetailInfoDto wxRedpackDetailInfoDto)
			throws TsfaServiceException {
		logger.debug("updateWxRedpackDetailInfo(WxRedpackDetailInfoDto wxRedpackDetailInfoDto={}) - start", wxRedpackDetailInfoDto); 

		AssertUtils.notNull(wxRedpackDetailInfoDto);
		AssertUtils.notNullAndEmpty(wxRedpackDetailInfoDto.getCode(),"Code不能为空");
		try {
			WxRedpackDetailInfo wxRedpackDetailInfo = new WxRedpackDetailInfo();
			//update数据录入
			wxRedpackDetailInfo.setCode(wxRedpackDetailInfoDto.getCode());
			wxRedpackDetailInfo.setRedPackId(wxRedpackDetailInfoDto.getRedPackId());
			wxRedpackDetailInfo.setBatchCode(wxRedpackDetailInfoDto.getBatchCode());
			wxRedpackDetailInfo.setNoWxShop(wxRedpackDetailInfoDto.getNoWxShop());
			wxRedpackDetailInfo.setMemberNameGm(wxRedpackDetailInfoDto.getMemberNameGm());
			wxRedpackDetailInfo.setMemberNoGm(wxRedpackDetailInfoDto.getMemberNoGm());
//			wxRedpackDetailInfo.setShopNo(wxRedpackDetailInfoDto.getShopNo());
			wxRedpackDetailInfo.setMemberNo(wxRedpackDetailInfoDto.getMemberNo());
			wxRedpackDetailInfo.setMemberName(wxRedpackDetailInfoDto.getMemberName());
			wxRedpackDetailInfo.setNoWx(wxRedpackDetailInfoDto.getNoWx());
			wxRedpackDetailInfo.setContent(wxRedpackDetailInfoDto.getContent());
			wxRedpackDetailInfo.setAmount(wxRedpackDetailInfoDto.getAmount());
			wxRedpackDetailInfo.setStatus(wxRedpackDetailInfoDto.getStatus());
			wxRedpackDetailInfo.setCreateDate(wxRedpackDetailInfoDto.getCreateDate());
			wxRedpackDetailInfo.setSendDate(wxRedpackDetailInfoDto.getSendDate());
			wxRedpackDetailInfo.setReceiveDate(wxRedpackDetailInfoDto.getReceiveDate());
			wxRedpackDetailInfo.setErrorMsg(wxRedpackDetailInfoDto.getErrorMsg());
			AssertUtils.notUpdateMoreThanOne(wxRedpackDetailInfoDao.updateByPrimaryKeySelective(wxRedpackDetailInfo));
			logger.debug("updateWxRedpackDetailInfo(WxRedpackDetailInfoDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("微信红包任务明细信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCodeWxRedpackDetailInfo.WX_REDPACK_DETAIL_INFO_UPDATE_ERROR,"微信红包任务明细信息更新信息错误！",e);
		}
	}

	

	@Override
	public WxRedpackDetailInfoDto findWxRedpackDetailInfo(
			WxRedpackDetailInfoDto wxRedpackDetailInfoDto) throws TsfaServiceException {
		logger.debug("findWxRedpackDetailInfo(FindWxRedpackDetailInfo findWxRedpackDetailInfo={}) - start", wxRedpackDetailInfoDto); 

		AssertUtils.notNull(wxRedpackDetailInfoDto);
		AssertUtils.notAllNull(wxRedpackDetailInfoDto.getCode(),"Code不能为空");
		try {
			WxRedpackDetailInfo wxRedpackDetailInfo = wxRedpackDetailInfoDao.selectByPrimaryKey(wxRedpackDetailInfoDto.getCode());
			if(wxRedpackDetailInfo == null){
				return null;
				//throw new TsfaServiceException(ErrorCodeWxRedpackDetailInfo.WX_REDPACK_DETAIL_INFO_NOT_EXIST_ERROR,"微信红包任务明细信息不存在");
			}
			WxRedpackDetailInfoDto findWxRedpackDetailInfoReturn = new WxRedpackDetailInfoDto();
			//find数据录入
			findWxRedpackDetailInfoReturn.setCode(wxRedpackDetailInfo.getCode());
			findWxRedpackDetailInfoReturn.setRedPackId(wxRedpackDetailInfo.getRedPackId());
			findWxRedpackDetailInfoReturn.setBatchCode(wxRedpackDetailInfo.getBatchCode());
			findWxRedpackDetailInfoReturn.setNoWxShop(wxRedpackDetailInfo.getNoWxShop());
			findWxRedpackDetailInfoReturn.setMemberNameGm(wxRedpackDetailInfo.getMemberNameGm());
			findWxRedpackDetailInfoReturn.setMemberNoGm(wxRedpackDetailInfo.getMemberNoGm());
//			findWxRedpackDetailInfoReturn.setShopNo(wxRedpackDetailInfo.getShopNo());
			findWxRedpackDetailInfoReturn.setMemberNo(wxRedpackDetailInfo.getMemberNo());
			findWxRedpackDetailInfoReturn.setMemberName(wxRedpackDetailInfo.getMemberName());
			findWxRedpackDetailInfoReturn.setNoWx(wxRedpackDetailInfo.getNoWx());
			findWxRedpackDetailInfoReturn.setContent(wxRedpackDetailInfo.getContent());
			findWxRedpackDetailInfoReturn.setAmount(wxRedpackDetailInfo.getAmount());
			findWxRedpackDetailInfoReturn.setStatus(wxRedpackDetailInfo.getStatus());
			findWxRedpackDetailInfoReturn.setCreateDate(wxRedpackDetailInfo.getCreateDate());
			findWxRedpackDetailInfoReturn.setSendDate(wxRedpackDetailInfo.getSendDate());
			findWxRedpackDetailInfoReturn.setReceiveDate(wxRedpackDetailInfo.getReceiveDate());
			findWxRedpackDetailInfoReturn.setType(wxRedpackDetailInfo.getType());
			findWxRedpackDetailInfoReturn.setSource(wxRedpackDetailInfo.getSource());
			findWxRedpackDetailInfoReturn.setMsgId(wxRedpackDetailInfo.getMsgId());
			logger.debug("findWxRedpackDetailInfo(WxRedpackDetailInfoDto) - end - return value={}", findWxRedpackDetailInfoReturn); 
			return findWxRedpackDetailInfoReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找微信红包任务明细信息信息错误！",e);
			throw new TsfaServiceException(ErrorCodeWxRedpackDetailInfo.WX_REDPACK_DETAIL_INFO_FIND_ERROR,"查找微信红包任务明细信息信息错误！",e);
		}


	}

	
	@Override
	public Page<WxRedpackDetailInfoDto> findWxRedpackDetailInfoPage(
			FindWxRedpackDetailInfoPage findWxRedpackDetailInfoPage)
			throws TsfaServiceException {
		logger.debug("findWxRedpackDetailInfoPage(FindWxRedpackDetailInfoPage findWxRedpackDetailInfoPage={}) - start", findWxRedpackDetailInfoPage); 

		AssertUtils.notNull(findWxRedpackDetailInfoPage);
		List<WxRedpackDetailInfoDto> returnList=null;
		int count = 0;
		try {
			returnList = wxRedpackDetailInfoDao.findWxRedpackDetailInfoPage(findWxRedpackDetailInfoPage);
			count = wxRedpackDetailInfoDao.findWxRedpackDetailInfoPageCount(findWxRedpackDetailInfoPage);
		}  catch (Exception e) {
			logger.error("微信红包任务明细信息不存在错误",e);
			throw new TsfaServiceException(ErrorCodeWxRedpackDetailInfo.WX_REDPACK_DETAIL_INFO_FIND_PAGE_ERROR,"微信红包任务明细信息不存在错误.！",e);
		}
		Page<WxRedpackDetailInfoDto> returnPage = new Page<WxRedpackDetailInfoDto>(returnList, count, findWxRedpackDetailInfoPage);

		logger.debug("findWxRedpackDetailInfoPage(FindWxRedpackDetailInfoPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public void addWxRedpackDetailInfoBatch(List<String> noWxs,WxJobRedPackInfoDto wxJobRedPackInfoDto)
			throws TsfaServiceException {
		  for (String noWx : noWxs) {
			  WxRedpackDetailInfoDto wxRedpackDetailInfoDto = new WxRedpackDetailInfoDto();
			  wxRedpackDetailInfoDto.setBatchCode(wxJobRedPackInfoDto.getCode());
			  wxRedpackDetailInfoDto.setAmount(wxJobRedPackInfoDto.getRedpackAmount());
			  wxRedpackDetailInfoDto.setContent(wxJobRedPackInfoDto.getRedpackContent());
			  wxRedpackDetailInfoDto.setNoWx(noWx);
			  FindPersonMemberReturn personMember = personMemberService.findPersonMemberByNoWxAndShopWx(noWx, wxJobRedPackInfoDto.getWxNoShop());
			  if(personMember!=null){
				  wxRedpackDetailInfoDto.setMemberName(personMember.getMemberName());
				  wxRedpackDetailInfoDto.setMemberNo(personMember.getMemberNo());
				  wxRedpackDetailInfoDto.setMemberNameGm(personMember.getMemberNameGm());
				  wxRedpackDetailInfoDto.setMemberNoGm(personMember.getMemberNoGm());
			  }
			 // FindShopTerminalReturn shopTerminal = shopTerminalService.findShopTerminalNormal(wxJobRedPackInfoDto.getWxNoShop());
//			  wxRedpackDetailInfoDto.setShopNo(wxJobRedPackInfoDto.getShopNo());
			  wxRedpackDetailInfoDto.setNoWxShop(wxJobRedPackInfoDto.getWxNoShop());
			  wxRedpackDetailInfoDto.setCreateDate(new Date());
			  wxRedpackDetailInfoDto.setStatus(RedPackStatus.NOSEND.getStatus());
			  addWxRedpackDetailInfo(wxRedpackDetailInfoDto);
		  }
		

	}


	@Override
	public Long getSumAmtByNoWxToday(String noWx) {
		 return wxRedpackDetailInfoDao.getSumAmtByNoWxToDay(noWx);

	}
	
	@Override
	public Long findWxRedpackDetailTotalMoney(FindWxRedpackDetailInfoPage findWxRedpackDetailInfoPage) {
		return wxRedpackDetailInfoDao.findWxRedpackDetailTotalMoney(findWxRedpackDetailInfoPage);
	} 

	public Integer findWxRedpackDetailTotalCount(FindWxRedpackDetailInfoPage findWxRedpackDetailInfoPage) {
		return wxRedpackDetailInfoDao.findWxRedpackDetailInfoPageCount(findWxRedpackDetailInfoPage);
	}

	@Override
	public List<String> findWxRedpackDetailYears() {
		return wxRedpackDetailInfoDao.findWxRedpackDetailYears();
	}


	@Override
	public WxRedpackDetailInfoDto findWxRedpackDetailinfoByOrderNo(
			String orderNo) {
		 return wxRedpackDetailInfoDao.findWxRedpackDetailinfoByOrderNo(orderNo);
	}



    @Override
    public void sendRedPackAfterAddWxFriends(Map<String, String> paramsMap, String noWx, String wxNoShop, String merchantNo, String shopNo, String nickName) {
        logger.debug("sendRedPackAfterAddWxFriends(Map<String,String> paramsMap={}, String noWx={}, String wxNoShop={}, String merchantNo={}, String shopNo={}, String nickName={}) - start", paramsMap, noWx, wxNoShop, merchantNo, shopNo, nickName); 
        
        //限制每天发送个数，有随机和定额红包，红包说明
        if (MapUtils.isNotEmpty(paramsMap) && StringUtils.isNotEmpty(paramsMap.get("redPack_min_amt")) && StringUtils.isNotEmpty(paramsMap.get("redPack_max_amt"))) {
            //限制每个微信全库只能领取一次
            Long count = wxRedpackDetailInfoDao.findRedPackCountByNoWxAndType(noWx, 2);
            if (count >= 1) {
                logger.info("sendRedPackAfterAddWxFriends->该微信号{}已领取过", noWx);
                return;
            }
            
            String dayLimitString = paramsMap.get("redPack_day_limit");
            String today = DateUtils.formatDate(new Date(), DateUtils.PATTERN_yyyyMMdd);
            String key = KeyConstant.RED_PACK_SEND + merchantNo + today;
            if (StringUtils.isNotEmpty(dayLimitString)) {//有次数限制
            	int dayLimit = Integer.parseInt(dayLimitString);
            	
            	String todaySentString = redisCache.get(key);	// 少个商户编号
            	if (StringUtils.isEmpty(todaySentString)) {
            		redisCache.set(key, "0");
            	} else {
            		int todaySent = Integer.parseInt(todaySentString);
            		if (todaySent >= dayLimit) {
            			logger.warn("sendRedPackAfterAddWxFriends->该商户当天的红包发送数量达到上限");
            			return;
            		}
            	}
            }
            
            int minAmt = Integer.parseInt(paramsMap.get("redPack_min_amt"));
            int maxAmt = Integer.parseInt(paramsMap.get("redPack_max_amt"));
            if (minAmt > maxAmt) {
                logger.error("sendRedPackAfterAddWxFriends->红包最大金额不能低于最小金额：maxAmt->{},minAmt->{}", maxAmt, minAmt);
                return;
            }
            long amt = new Random().nextInt(maxAmt - minAmt + 1) + minAmt;
            logger.debug("sendRedPackAfterAddWxFriends->中控{}发给微信{}红包金额大小:{}", wxNoShop, noWx, amt);
            
            WxRedpackDetailInfoDto wxRedpackDetailInfoDto = new WxRedpackDetailInfoDto();
            wxRedpackDetailInfoDto.setAmount(amt);
            wxRedpackDetailInfoDto.setContent(paramsMap.get("redPack_desc"));
            wxRedpackDetailInfoDto.setNoWx(noWx);
            FindPersonMemberReturn personMember = personMemberService.findPersonMemberByNoWxAndShopWx(noWx, wxNoShop);
            if(personMember != null){
                wxRedpackDetailInfoDto.setMemberName(personMember.getMemberName());
                wxRedpackDetailInfoDto.setMemberNo(personMember.getMemberNo());
                wxRedpackDetailInfoDto.setMemberNameGm(personMember.getMemberNameGm());
                wxRedpackDetailInfoDto.setMemberNoGm(personMember.getMemberNoGm());
            }
//            wxRedpackDetailInfoDto.setShopNo(shopNo);
            wxRedpackDetailInfoDto.setNoWxShop(wxNoShop);
            wxRedpackDetailInfoDto.setCreateDate(new Date());
            wxRedpackDetailInfoDto.setSendDate(new Date());
            wxRedpackDetailInfoDto.setStatus(RedPackStatus.SENDING.getStatus());
            wxRedpackDetailInfoDto.setType(2);//加好友发红包类型
            wxRedpackDetailInfoDto.setMemberName(nickName);
            //插入红包发送记录
            WxRedpackDetailInfo addWxRedpackDetailInfo2 = addWxRedpackDetailInfo2(wxRedpackDetailInfoDto);
            
            //发红包
            AddRedPackMessage addRedPackMessage = new AddRedPackMessage();
            addRedPackMessage.setRpCode(addWxRedpackDetailInfo2.getCode());
            addRedPackMessage.setNoWx(wxRedpackDetailInfoDto.getNoWx());
            addRedPackMessage.setNoWxShop(wxRedpackDetailInfoDto.getNoWxShop());
            addRedPackMessage.setType("1");
            
            String desc = StringUtils.isEmpty(paramsMap.get("redPack_desc")) ? "恭喜发财,大吉大利" : paramsMap.get("redPack_desc");
            addRedPackMessage.setDes(desc);
            addRedPackMessage.setAmt(wxRedpackDetailInfoDto.getAmount());
            logger.info("sendRedPackAfterAddWxFriends->sendRedPackRequest:{}", addRedPackMessage);
            
            IRedPackFacade basic = commonService.getHessianIRedPackFacade(addRedPackMessage.getNoWxShop());
            basic.sendRedPackRequest(addRedPackMessage);
            
            redisCache.setIncr(key);
        } else {
            logger.warn("sendRedPackAfterAddWxFriends->没有配置商户参数:{}", paramsMap);
        }

        logger.debug("sendRedPackAfterAddWxFriends(Map<String,String>, String, String, String, String, String) - end"); 
    }

    /**
	 * 
	 *
	 * 方法说明：根据中控返回轮询红包结果更新红包信息
	 *
	 * @param updateRedpackDetailByPoll
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月18日
	 *
	 */
	@Override
	public void updateRedpackDetailByPoll(UpdateRedpackDetailByPoll updateRedpackDetailByPoll) throws TsfaServiceException {
		logger.debug("updateRedpackDetailByPoll(UpdateRedpackDetailByPoll updateRedpackDetailByPoll={}) - start", updateRedpackDetailByPoll); 
		
		AssertUtils.notNull(updateRedpackDetailByPoll);
		AssertUtils.notNullAndEmpty(updateRedpackDetailByPoll.getCode(), "CODE不能为空");
		AssertUtils.notNullAndEmpty(updateRedpackDetailByPoll.getStatus(), "状态不能为空");
		
		
		try {
			WxRedpackDetailInfo wxRedpackDetailInfo = wxRedpackDetailInfoDao.selectByPrimaryKey(updateRedpackDetailByPoll.getCode());
			if(null != wxRedpackDetailInfo) {
				Map<String, String> paramMap = localCacheSystemParams.getSystemParamGroup(SystemAliasName.weixin.name(), "redPackJob");
				int maxPollCount = Integer.valueOf(StringUtils.toString(paramMap.get("maxPollCount"), "3"));		// 最大轮询次数
				
				WxRedpackDetailInfo update = new WxRedpackDetailInfo();
				update.setCode(updateRedpackDetailByPoll.getCode());
				
				int pollCount = wxRedpackDetailInfo.getPollCount() == null ? 1 : wxRedpackDetailInfo.getPollCount() + 1;
				update.setPollCount(pollCount);
				
				String status = updateRedpackDetailByPoll.getStatus();
				if(RedPackStatus.NOSEND.getStatus().equals(updateRedpackDetailByPoll.getStatus()) || RedPackStatus.SENDING.getStatus().equals(updateRedpackDetailByPoll.getStatus())) {
					if(maxPollCount <= pollCount) {	// 超过轮询次数
						status = RedPackStatus.FAIL.getStatus();
					}
				} else if("99".equals(updateRedpackDetailByPoll.getStatus())) {	// 中控没找到红包记录，即发送失败
					status = RedPackStatus.FAIL.getStatus();
				}
				
				update.setStatus(status);
				update.setRedPackId(updateRedpackDetailByPoll.getRedPackId());
				update.setErrorMsg(updateRedpackDetailByPoll.getFailMsg());
				logger.info("更新红包信息:{}", update);
				wxRedpackDetailInfoDao.updateByPrimaryKeySelective(update);
			} else {
				logger.error("红包不存在:{}", updateRedpackDetailByPoll.getCode());
			}
		} catch(Exception e) {
			logger.error("根据中控返回轮询红包结果更新红包信息失败", e);
		}
		
		logger.debug("updateRedpackDetailByPoll(UpdateRedpackDetailByPoll) - end"); 
	}
	
	
	
	/**
	 * 根据活动给用户添加红包，发送红包(必须加锁)
	 * @return
	 * @throws TsfaServiceException
	 */
	@Override
	public synchronized Map<String, String> addWxRedpackDetailInfoByActivity(WxRedpackDetailInfoDto redpack)throws TsfaServiceException {
		Map<String, String> map = new HashMap<String, String >();
		try {
			//加分布式锁
			redisLock.getLockNoWait("REDIS-KEY-LOCK-PHONE-" + redpack.getPhoneNumber(), redpack.getPhoneNumber(), 4, 4);
			  //必须先查询该用户在该次活动中是否已经发红包
			List<WxRedpackDetailInfoDto>  list =wxRedpackDetailInfoDao.findWxRedpackDetailinfoByPhoneNumber(redpack.getPhoneNumber(), redpack.getBatchCode());
			if(list != null && list.size() > 0) {
				map.put("result", "false");
				map.put("message", "同一手机不能二次领取");
				return map;
			}
			
			List<WxRedpackDetailInfoDto>  list2 =wxRedpackDetailInfoDao.findWxRedpackDetailinfoByWX(redpack.getNoWx(), redpack.getBatchCode());
			if(list2 != null && list2.size() > 0) {
				map.put("result", "false");
				map.put("message", "同一微信不能二次领取");
				return map;
			}   
			
			//每天限100个名额
			Integer count = wxRedpackDetailInfoDao.findWxRedpackInDayCount(redpack.getNoWxShop(), redpack.getBatchCode());
			if(count == 100 || count > 100) {
				map.put("result", "false");
				map.put("message", "微信每天限额100个红包，今日红包已经发送完毕，明日将补发。请您耐心等一下哈，也请持续关注我们的优质内容！");
				return map;
			}

			  WxRedpackDetailInfo wxRedpackDetailInfo = new WxRedpackDetailInfo();
				//add数据录入
				wxRedpackDetailInfo.setCode(GUID.generateCode());

				wxRedpackDetailInfo.setBatchCode(redpack.getBatchCode());
				wxRedpackDetailInfo.setNoWxShop(redpack.getNoWxShop());
				wxRedpackDetailInfo.setNoWx(redpack.getNoWx());
				wxRedpackDetailInfo.setPhoneNumber(redpack.getPhoneNumber());
				if(StringUtils.isNotEmpty(redpack.getContent())){
					wxRedpackDetailInfo.setContent(redpack.getContent());
				}else {
					wxRedpackDetailInfo.setContent("恭喜发财,大吉大利");
				}
				wxRedpackDetailInfo.setAmount(redpack.getAmount());
				wxRedpackDetailInfo.setStatus(RedPackStatus.SENDING.getStatus());
				wxRedpackDetailInfo.setCreateDate(new Date());
				wxRedpackDetailInfo.setSendDate(new Date());
		
				wxRedpackDetailInfo.setType(1);
				wxRedpackDetailInfoDao.insert(wxRedpackDetailInfo);
				
			 // addWxRedpackDetailInfo(wxRedpackDetailInfoDto);
			  //发红包
	          AddRedPackMessage addRedPackMessage = new AddRedPackMessage();
	          addRedPackMessage.setRpCode(wxRedpackDetailInfo.getCode());
	          addRedPackMessage.setNoWx(wxRedpackDetailInfo.getNoWx());
	          addRedPackMessage.setNoWxShop(wxRedpackDetailInfo.getNoWxShop());
	          addRedPackMessage.setType("1");
	
	          addRedPackMessage.setDes(wxRedpackDetailInfo.getContent());
	          addRedPackMessage.setAmt(wxRedpackDetailInfo.getAmount());
	          logger.info("sendRedPackAfterAddWxFriends->sendRedPackRequest:{}", addRedPackMessage);
	          
	          IRedPackFacade basic = commonService.getHessianIRedPackFacade(addRedPackMessage.getNoWxShop());
	          basic.sendRedPackRequest(addRedPackMessage);
	          
			  map.put("result", "true");
			  map.put("message", "已核实内容正确，稍后操作发送!");
			  
		}catch(Exception e) {
			logger.error("发红包错误" ,e);
			map.put("result", "false");
			map.put("message", "错误");
		}finally {
			redisLock.releaseLock("REDIS-KEY-LOCK-PHONE-" + redpack.getPhoneNumber());
		}
		return map;
	}

	@Override
	public synchronized void sendWxRedpackByIm(WxRedpackDetailInfoDto redpack,String messageType, String source)throws TsfaServiceException {
		try {
			AssertUtils.notNullAndEmpty(redpack.getNoWx(),"客户微信不能为空");
			AssertUtils.notNullAndEmpty(redpack.getNoWxShop(),"中控微信不能为空");
			AssertUtils.notNullAndEmpty(redpack.getAmount(),"金额不能为空");
			AssertUtils.notNullAndEmpty(redpack.getMemberNo(),"客户编号不能为空");
			AssertUtils.notNullAndEmpty(redpack.getMemberNoGm(),"导购编号不能为空");
			AssertUtils.notNullAndEmpty(redpack.getMerchantNo(),"商户编号不能为空");
			//加分布式锁
			redisLock.getLockNoWait(REDIS_KEY_LOCK_NOWX + redpack.getNoWx(), redpack.getNoWx(), 4, 4);
			//每天限100个名额
			Integer count = wxRedpackDetailInfoDao.findWxRedpackInDayCount(redpack.getNoWxShop(), null);
			if(count == 100 || count > 100) {
				throw new TsfaServiceException(ErrorCodeWxRedpackDetailInfo.WX_REDPACK_DETAIL_INFO_ADD_ERROR,"微信每天限额100个红包，今日红包已限额。");
			}

			  WxRedpackDetailInfo wxRedpackDetailInfo = new WxRedpackDetailInfo();
				//add数据录入
				wxRedpackDetailInfo.setCode(GUID.generateCode());
				wxRedpackDetailInfo.setNoWxShop(redpack.getNoWxShop());
				wxRedpackDetailInfo.setNoWx(redpack.getNoWx());
				if(StringUtils.isNotEmpty(redpack.getContent())){
					wxRedpackDetailInfo.setContent(redpack.getContent());
				}else {
					wxRedpackDetailInfo.setContent("恭喜发财,大吉大利");
				}
				wxRedpackDetailInfo.setAmount(redpack.getAmount());
				wxRedpackDetailInfo.setStatus(RedPackStatus.SENDING.getStatus());
				wxRedpackDetailInfo.setCreateDate(new Date());
				wxRedpackDetailInfo.setSendDate(new Date());
				wxRedpackDetailInfo.setType(Integer.valueOf(redpack.getType()));
				wxRedpackDetailInfo.setMemberNo(redpack.getMemberNo());
				wxRedpackDetailInfo.setMemberNoGm(redpack.getMemberNoGm());
				wxRedpackDetailInfo.setMemberName(redpack.getMemberName());
				wxRedpackDetailInfo.setMemberNameGm(redpack.getMemberNameGm());
				wxRedpackDetailInfo.setMerchantNo(redpack.getMerchantNo());
				wxRedpackDetailInfo.setMsgId(redpack.getMsgId());
				wxRedpackDetailInfo.setSource(source);
				wxRedpackDetailInfoDao.insert(wxRedpackDetailInfo);
				
			  //发红包或转账
	          AddRedPackMessage addRedPackMessage = new AddRedPackMessage();
	          addRedPackMessage.setRpCode(wxRedpackDetailInfo.getCode());
	          addRedPackMessage.setNoWx(wxRedpackDetailInfo.getNoWx());
	          addRedPackMessage.setNoWxShop(wxRedpackDetailInfo.getNoWxShop());
	          addRedPackMessage.setType(messageType);
	          addRedPackMessage.setNickName(redpack.getMemberName());
	          addRedPackMessage.setDes(wxRedpackDetailInfo.getContent());
	          addRedPackMessage.setAmt(wxRedpackDetailInfo.getAmount());
	          addRedPackMessage.setMsgId(redpack.getMsgId());
	          logger.info("sendRedPackAfterAddWxFriends->sendRedPackRequest:{}", addRedPackMessage);
	          
	          IRedPackFacade basic = commonService.getHessianIRedPackFacade(addRedPackMessage.getNoWxShop());
	          basic.sendRedPackRequest(addRedPackMessage);
	          
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch(Exception e) {
			logger.error("发红包错误" ,e);
			throw new TsfaServiceException(ErrorCodeWxRedpackDetailInfo.WX_REDPACK_DETAIL_INFO_ADD_ERROR,"发红包错误！",e);
		}finally {
			redisLock.releaseLock(REDIS_KEY_LOCK_NOWX + redpack.getNoWx());
		}
	}

	@Override
	public Integer findWxRedpackDetailTotalSendCount(FindWxRedpackDetailInfoPage find) {
		return wxRedpackDetailInfoDao.findWxRedpackDetailTotalSendCount(find);
	}
	
}
