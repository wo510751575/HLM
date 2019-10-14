package com.lj.business.weixin.service.impl.job;

import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.business.common.KeyConstant;
import com.lj.business.cp.dto.coupon.AddCoupon;
import com.lj.business.cp.dto.couponMemberRelation.AddCouponMemberRelation;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.emus.CouponStatus;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.weixin.dao.ICouponMultiPushDao;
import com.lj.business.weixin.dao.ICouponMultiPushPmDao;
import com.lj.business.weixin.dao.ICouponMultiPushTerminalDao;
import com.lj.business.weixin.domain.CouponMultiPush;
import com.lj.business.weixin.domain.CouponMultiPushPm;
import com.lj.business.weixin.domain.CouponMultiPushTerminal;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.emus.CouponMultiPushStatus;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.cc.clientintf.IParamJob;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.distributecache.RedisCache;

/**
 * 
 * 
 * 类说明：群发优惠券任务回调，启动重发等
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 * 
 *         CreateDate: 2018年1月25日
 */
@Service
public class CouponMultiPushJobTask implements IParamJob, ApplicationListener<ContextRefreshedEvent> {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(CouponMultiPushJobTask.class);

    @Autowired
    private ICouponMultiPushDao couponMultiPushDao;
    @Autowired
    private ICouponMultiPushTerminalDao couponMultiPushTerminalDao;
    @Autowired
    private ICouponMultiPushPmDao couponMultiPushPmDao;
    @Autowired
    private ICouponRuleService couponRuleService;
    // 优惠券service
    @Resource
    private ICouponService couponService;
    // 优惠券用户关联service
    @Resource
    private ICouponMemberRelationService couponMemberRelationService;
    @Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
    @Resource
    private IImChatInfoService imChatInfoService;
    @Resource
    private IMerchantService merchantService;
    @Autowired 
    private RedisCache redisCache;

    /**
     * param：CouponMultiPushTerminal CODE
     */
    @Override
    public void runParamJob(String param) {
        logger.debug("CouponMultiPushJobTask runParamJob(String param={}) - start", param); 

        try {
            CouponMultiPushTerminal couponMultiPushTerminal = couponMultiPushTerminalDao.selectByPrimaryKey(param);
            
            FindCouponRule findCouponRule = new FindCouponRule();
            findCouponRule.setCode(couponMultiPushTerminal.getCouponRuleCode());
            FindCouponRuleReturn findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);

            CouponMultiPushPm record = new CouponMultiPushPm();
            record.setPushTerminalCode(param);
            List<CouponMultiPushPm> list = couponMultiPushPmDao.selectBySelective(record);
            
            //逐个发送优惠券
            for (CouponMultiPushPm couponMultiPushPm : list) {
                try {

                    AddCoupon addCoupon = new AddCoupon();
                    addCoupon.setMerchantNo(couponMultiPushTerminal.getMerchantNo());
                    addCoupon.setMerchantName(couponMultiPushTerminal.getMerchantName());
//                    addCoupon.setShopNo(couponMultiPushTerminal.getShopNo());
//                    addCoupon.setShopName(couponMultiPushTerminal.getShopName());
                    addCoupon.setRuleNo(findCouponRuleReturn.getCode());
                    addCoupon.setCouponNo(GUID.generateCode());
                    addCoupon.setCouponStatus(CouponStatus.USED.toString());
                    addCoupon.setUseDate(new Date());
                    addCoupon.setCreateDate(new Date());
                    couponService.addCoupon(addCoupon); // 新增优惠券

                    AddCouponMemberRelation addCouponMemberRelation = new AddCouponMemberRelation();
                    addCouponMemberRelation.setMemberNoGm(couponMultiPushPm.getMemberNoGm());
//                    addCouponMemberRelation.setMemberNameGm(guidMember.getMemberName());
                    addCouponMemberRelation.setMemberNo(couponMultiPushPm.getMemberNo());
//                    addCouponMemberRelation.setMemberName(personMemberBase.getMemberName());
                    addCouponMemberRelation.setCouponNo(addCoupon.getCouponNo());
                    addCouponMemberRelation.setCouponStatus(addCoupon.getCouponStatus());
                    addCouponMemberRelation.setUseDate(new Date());
                    addCouponMemberRelation.setCreateDate(new Date());
                    couponMemberRelationService.addCouponMemberRelation(addCouponMemberRelation); // 新增优惠券用户绑定关系

                    String cp_result_url = localCacheSystemParams.getSystemParam("cp", "result_url", "cp_result_url");
                    // 优惠券领取url
                    String resultUrl = String.format(cp_result_url, couponMultiPushPm.getMemberNoGm(), couponMultiPushPm.getMemberNo(), addCoupon.getCouponNo());

                    FindMerchantDto findMerchantDto = new FindMerchantDto();
                    findMerchantDto.setMerchantNo(findCouponRuleReturn.getMerchantNo());
                    FindMerchantReturnDto findMerchantReturnDto =
                            merchantService.selectMerchant(findMerchantDto);

                    // 通过中控版本扫码（1导购扫码、2客户扫码）进来的客户，则通知IM功能推送优惠券
                    SendImChatInfo sendImChatInfo = new SendImChatInfo();
                    sendImChatInfo.setSenderFlag(1);
                    sendImChatInfo.setNoWxGm(couponMultiPushPm.getNoWxShop());
                    sendImChatInfo.setMemberNo(couponMultiPushPm.getMemberNo());
                    sendImChatInfo.setType("491"); // 分享优惠券
                    if (findMerchantReturnDto != null
                            && StringUtils.isNotEmpty(findMerchantReturnDto.getLogoAddr())) {
                        String uploadUrl =
                                localCacheSystemParams.getSystemParam("ms", "upload", "uploadUrl"); // 商户图片静态地址
                        sendImChatInfo.setResources(uploadUrl + findMerchantReturnDto.getLogoAddr()); // 商户图标
                    } else {
                        String couponUrl =
                                localCacheSystemParams.getSystemParam("api", "logo", "couponLogoUrl");
                        sendImChatInfo.setResources(couponUrl);
                    }
                    sendImChatInfo.setShareTitle(findCouponRuleReturn.getCouponName());
                    sendImChatInfo.setShareDes("说明:"+findCouponRuleReturn.getCouponRemark());
                    sendImChatInfo.setShareUrl(resultUrl);
                    sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
                    sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
                    logger.info("发送优惠券：{}", sendImChatInfo);
                    imChatInfoService.sendChat(sendImChatInfo);
                    
                    //发送成功，删除redis中此客户的状态数据
                    redisCache.hdel(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), couponMultiPushTerminal.getCode() + ":" + couponMultiPushPm.getCode() + ":status");
                    
                    //更新群发客户表记录
                    CouponMultiPushPm updateCouponMultiPushPm = new CouponMultiPushPm();
                    updateCouponMultiPushPm.setCode(couponMultiPushPm.getCode());
                    updateCouponMultiPushPm.setStatus(CouponMultiPushStatus.SUCCESS.getCode());
                    updateCouponMultiPushPm.setCouponCode(addCoupon.getCouponNo());
                    updateCouponMultiPushPm.setUpdateDate(new Date());
                    couponMultiPushPmDao.updateByPrimaryKeySelective(updateCouponMultiPushPm);
                    
                    //增加该微信终端的已发送数量，并比较是否全部发送
                    String shopTerminalPmNum = redisCache.hget(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), couponMultiPushTerminal.getCode() + ":" + "pmNum");
                    if (shopTerminalPmNum != null) {
                        long shopTerminalSentPmNum = redisCache.hIncrBy(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), couponMultiPushTerminal.getCode() + ":" + "sentPmNum", 1L);
                        
                      //更新群发终端表记录
                        CouponMultiPushTerminal updateCouponMultiPushTerminal = new CouponMultiPushTerminal();
                        updateCouponMultiPushTerminal.setCode(param);
                        updateCouponMultiPushTerminal.setSentPmNum((int)shopTerminalSentPmNum);
                        updateCouponMultiPushTerminal.setUpdateDate(new Date());
                        if (shopTerminalPmNum.equals(shopTerminalSentPmNum + "")) {//该终端微信下的客户已发送完毕
                            redisCache.hdel(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), couponMultiPushTerminal.getCode() + ":" + "pmNum");
                            redisCache.hdel(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), couponMultiPushTerminal.getCode() + ":" + "sentPmNum");
                            
                            updateCouponMultiPushTerminal.setStatus(CouponMultiPushStatus.SUCCESS.getCode());
                        }
                        couponMultiPushTerminalDao.updateByPrimaryKeySelective(updateCouponMultiPushTerminal);
                        
                        //群发任务总客户数，和总已发送客户数
                        String pmNum = redisCache.hget(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), "pmNum");
                        if (pmNum != null) {
                            long sentPmNum = redisCache.hIncrBy(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), "sentPmNum", 1L);
                            
                            
                            CouponMultiPush updateCouponMultiPush = new CouponMultiPush();
                            updateCouponMultiPush.setCode(couponMultiPushTerminal.getPushCode());
                            updateCouponMultiPush.setSentPmNum((int)sentPmNum);
                            updateCouponMultiPush.setUpdateDate(new Date());
                            
                            if (pmNum.equals(sentPmNum + "")) {//所有客户已发送完毕
                                redisCache.del(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode());
                                
                                updateCouponMultiPush.setStatus(CouponMultiPushStatus.SUCCESS.getCode());
                            }
                            couponMultiPushDao.updateByPrimaryKeySelective(updateCouponMultiPush);
                        }
                    }
                    
                    if (list.indexOf(couponMultiPushPm) != list.size() - 1) {
                        int second = (new Random().nextInt(30) + 1) + 30;
                        Thread.sleep(second * 1000);
                    }
                
                } catch (Exception e) {
                    logger.error("该客户发送优惠券失败:" + couponMultiPushPm.toString(), e);
                }
            }
        } catch (Exception e) {
            logger.error("发送优惠券失败", e);
        }

        logger.debug("CouponMultiPushJobTask runParamJob(String) - end"); 
    }

    /**
     * 
     *
     * 方法说明：服务器启动，重新发送未完成的任务
     *
     *
     * @author 许新龙 CreateDate: 2018年2月1日
     *
     */
    public void reSendUnCompleteCoupon() {
        logger.debug("reSendUnCompleteCoupon() - start"); 

        try {
            //查询未发送的群发终端表记录
            CouponMultiPushTerminal couponMultiPushTerminalCondition = new CouponMultiPushTerminal();
            couponMultiPushTerminalCondition.setStatus(CouponMultiPushStatus.INIT.getCode());
            List<CouponMultiPushTerminal> unCompleteList = couponMultiPushTerminalDao.selectBySelective(couponMultiPushTerminalCondition);
            if (CollectionUtils.isEmpty(unCompleteList)) {
                return;
            }
            
            for (CouponMultiPushTerminal couponMultiPushTerminal : unCompleteList) {
                if (couponMultiPushTerminal.getExecuteTime().after(new Date())) {//任务调度在将来的某个时刻会回调发送，这里就不做发送了
                    continue;
                }
                
                FindCouponRule findCouponRule = new FindCouponRule();
                findCouponRule.setCode(couponMultiPushTerminal.getCouponRuleCode());
                FindCouponRuleReturn findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);

                //查询未发送的群发客户表记录
                CouponMultiPushPm record = new CouponMultiPushPm();
                record.setPushTerminalCode(couponMultiPushTerminal.getCode());
                record.setStatus(CouponMultiPushStatus.INIT.getCode());
                List<CouponMultiPushPm> list = couponMultiPushPmDao.selectBySelective(record);
                
                //逐个发送优惠券
                for (CouponMultiPushPm couponMultiPushPm : list) {
                    //如果redis中的记录不存在说明已发送，直接忽略
                    if (!redisCache.hexists(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), 
                            couponMultiPushTerminal.getCode() + ":" + couponMultiPushPm.getCode() + ":status")) {
                        continue;
                    }
                    
                    try {
                        AddCoupon addCoupon = new AddCoupon();
                        addCoupon.setMerchantNo(couponMultiPushTerminal.getMerchantNo());
                        addCoupon.setMerchantName(couponMultiPushTerminal.getMerchantName());
//                        addCoupon.setShopNo(couponMultiPushTerminal.getShopNo());
//                        addCoupon.setShopName(couponMultiPushTerminal.getShopName());
                        addCoupon.setRuleNo(findCouponRuleReturn.getCode());
                        addCoupon.setCouponNo(GUID.generateCode());
                        addCoupon.setCouponStatus(CouponStatus.USED.toString());
                        addCoupon.setUseDate(new Date());
                        addCoupon.setCreateDate(new Date());
                        couponService.addCoupon(addCoupon); // 新增优惠券

                        AddCouponMemberRelation addCouponMemberRelation = new AddCouponMemberRelation();
                        addCouponMemberRelation.setMemberNoGm(couponMultiPushPm.getMemberNoGm());
//                        addCouponMemberRelation.setMemberNameGm(guidMember.getMemberName());
                        addCouponMemberRelation.setMemberNo(couponMultiPushPm.getMemberNo());
//                        addCouponMemberRelation.setMemberName(personMemberBase.getMemberName());
                        addCouponMemberRelation.setCouponNo(addCoupon.getCouponNo());
                        addCouponMemberRelation.setCouponStatus(addCoupon.getCouponStatus());
                        addCouponMemberRelation.setUseDate(new Date());
                        addCouponMemberRelation.setCreateDate(new Date());
                        couponMemberRelationService.addCouponMemberRelation(addCouponMemberRelation); // 新增优惠券用户绑定关系

                        String cp_result_url = localCacheSystemParams.getSystemParam("cp", "result_url", "cp_result_url");
                        // 优惠券领取url
                        String resultUrl = String.format(cp_result_url, couponMultiPushPm.getMemberNoGm(), couponMultiPushPm.getMemberNo(), addCoupon.getCouponNo());

                        FindMerchantDto findMerchantDto = new FindMerchantDto();
                        findMerchantDto.setMerchantNo(findCouponRuleReturn.getMerchantNo());
                        FindMerchantReturnDto findMerchantReturnDto =
                                merchantService.selectMerchant(findMerchantDto);

                        // 通过中控版本扫码（1导购扫码、2客户扫码）进来的客户，则通知IM功能推送优惠券
                        SendImChatInfo sendImChatInfo = new SendImChatInfo();
                        sendImChatInfo.setSenderFlag(1);
                        sendImChatInfo.setNoWxGm(couponMultiPushPm.getNoWxShop());
                        sendImChatInfo.setMemberNo(couponMultiPushPm.getMemberNo());
                        sendImChatInfo.setType("491"); // 分享优惠券
                        if (findMerchantReturnDto != null
                                && StringUtils.isNotEmpty(findMerchantReturnDto.getLogoAddr())) {
                            String uploadUrl =
                                    localCacheSystemParams.getSystemParam("ms", "upload", "uploadUrl"); // 商户图片静态地址
                            sendImChatInfo.setResources(uploadUrl + findMerchantReturnDto.getLogoAddr()); // 商户图标
                        } else {
                            String couponUrl =
                                    localCacheSystemParams.getSystemParam("api", "logo", "couponLogoUrl");
                            sendImChatInfo.setResources(couponUrl);
                        }
                        sendImChatInfo.setShareTitle(findCouponRuleReturn.getCouponName());
                        sendImChatInfo.setShareDes("说明:"+ findCouponRuleReturn.getCouponRemark());
                        sendImChatInfo.setShareUrl(resultUrl);
                        sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
                        sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
                        logger.info("发送优惠券：{}", sendImChatInfo);
                        imChatInfoService.sendChat(sendImChatInfo);
                        
                        //发送成功，删除redis中此客户的状态数据
                        redisCache.hdel(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), couponMultiPushTerminal.getCode() + ":" + couponMultiPushPm.getCode() + ":status");
                        
                        //更新群发客户表记录
                        CouponMultiPushPm updateCouponMultiPushPm = new CouponMultiPushPm();
                        updateCouponMultiPushPm.setCode(couponMultiPushPm.getCode());
                        updateCouponMultiPushPm.setStatus(CouponMultiPushStatus.SUCCESS.getCode());
                        updateCouponMultiPushPm.setCouponCode(addCoupon.getCouponNo());
                        couponMultiPushPmDao.updateByPrimaryKeySelective(updateCouponMultiPushPm);
                        
                        //增加该微信终端的已发送数量，并比较是否全部发送
                        String shopTerminalPmNum = redisCache.hget(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), couponMultiPushTerminal.getCode() + ":" + "pmNum");
                        if (shopTerminalPmNum != null) {
                            long shopTerminalSentPmNum = redisCache.hIncrBy(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), couponMultiPushTerminal.getCode() + ":" + "sentPmNum", 1L);
                            
                            //更新群发终端表记录
                            CouponMultiPushTerminal updateCouponMultiPushTerminal = new CouponMultiPushTerminal();
                            updateCouponMultiPushTerminal.setCode(couponMultiPushTerminal.getCode());
                            updateCouponMultiPushTerminal.setSentPmNum((int)shopTerminalSentPmNum);
                            if (shopTerminalPmNum.equals(shopTerminalSentPmNum + "")) {//该终端微信下的客户已发送完毕
                                redisCache.hdel(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), couponMultiPushTerminal.getCode() + ":" + "pmNum");
                                redisCache.hdel(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), couponMultiPushTerminal.getCode() + ":" + "sentPmNum");
                                
                                updateCouponMultiPushTerminal.setStatus(CouponMultiPushStatus.SUCCESS.getCode());
                            }
                            couponMultiPushTerminalDao.updateByPrimaryKeySelective(updateCouponMultiPushTerminal);
                            
                            //群发任务总客户数，和总已发送客户数
                            String pmNum = redisCache.hget(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), "pmNum");
                            if (pmNum != null) {
                                long sentPmNum = redisCache.hIncrBy(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode(), "sentPmNum", 1L);
                                
                                
                                CouponMultiPush updateCouponMultiPush = new CouponMultiPush();
                                updateCouponMultiPush.setCode(couponMultiPushTerminal.getPushCode());
                                updateCouponMultiPush.setSentPmNum((int)sentPmNum);
                                
                                if (pmNum.equals(sentPmNum + "")) {//所有客户已发送完毕
                                    redisCache.del(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPushTerminal.getPushCode());
                                    
                                    updateCouponMultiPush.setStatus(CouponMultiPushStatus.SUCCESS.getCode());
                                }
                                couponMultiPushDao.updateByPrimaryKeySelective(updateCouponMultiPush);
                            }
                        }
                        
                        if (list.indexOf(couponMultiPushPm) != list.size() - 1) {
                            int second = (new Random().nextInt(30) + 1) + 30;
                            Thread.sleep(second * 1000);
                        }
                    
                    } catch (Exception e) {
                        logger.error("该客户发送优惠券失败:" + couponMultiPushPm.toString(), e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("重发优惠券失败", e);
        }

        logger.debug("reSendUnCompleteCoupon() - end"); 
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            reSendUnCompleteCoupon();
        }
    }

}
