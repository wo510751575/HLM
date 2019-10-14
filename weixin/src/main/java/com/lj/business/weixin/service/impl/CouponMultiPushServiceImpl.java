package com.lj.business.weixin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaException;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.KeyConstant;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.constant.ErrorCode;
import com.lj.business.weixin.dao.ICouponMultiPushDao;
import com.lj.business.weixin.dao.ICouponMultiPushPmDao;
import com.lj.business.weixin.dao.ICouponMultiPushTerminalDao;
import com.lj.business.weixin.domain.CouponMultiPush;
import com.lj.business.weixin.domain.CouponMultiPushPm;
import com.lj.business.weixin.domain.CouponMultiPushTerminal;
import com.lj.business.weixin.dto.couponmultipush.AddCouponMultiPush;
import com.lj.business.weixin.dto.couponmultipush.AddCouponMultiPushReturn;
import com.lj.business.weixin.dto.couponmultipush.ShopTerminalMappingPm;
import com.lj.business.weixin.emus.CouponMultiPushStatus;
import com.lj.business.weixin.service.ICouponMultiPushService;
import com.lj.business.weixin.utils.JobUtils;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.domain.JobCenter;
import com.lj.cc.service.IJobMgrService;
import com.lj.distributecache.RedisCache;

/**
 * 
 * 
 * 类说明：群发优惠券业务实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年1月25日
 */
@Service
public class CouponMultiPushServiceImpl implements ICouponMultiPushService {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(CouponMultiPushServiceImpl.class);
    
    @Autowired
    private ICouponRuleService couponRuleService;
    @Autowired
    private ICouponMultiPushDao couponMultiPushDao;
    @Autowired
    private ICouponMultiPushTerminalDao couponMultiPushTerminalDao;
    @Autowired
    private ICouponMultiPushPmDao couponMultiPushPmDao;
    @Resource
    private IGuidMemberService guidMemberService; // 导购服务
    @Autowired
    private IShopTerminalService shopTerminalService;
    @Resource
    private IPersonMemberService personMemberService; // 客户信息服务
    @Resource
    private IJobMgrService jobMgrService;
    @Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
    @Autowired 
    private RedisCache redisCache;

    @Override
    public AddCouponMultiPushReturn addCouponMultiPush (
            AddCouponMultiPush addCouponMultiPush) throws TsfaException {
        logger.debug("addCouponMultiPush(AddCouponMultiPush addCouponMultiPush={}) - start", addCouponMultiPush); 

        AssertUtils.notNull(addCouponMultiPush);
        AssertUtils.notNullAndEmpty(addCouponMultiPush.getAssistantNo(), "助手编号不能为空！");
        AssertUtils.notNullAndEmpty(addCouponMultiPush.getMerchantNo(), "商户编号不能为空！");
        AssertUtils.notNullAndEmpty(addCouponMultiPush.getCouponRuleCode(), "优惠券规则CODE不能为空！");
        AssertUtils.notNullAndEmpty(addCouponMultiPush.getShopNoWxs(), "网点微信号不能为空！");
        AssertUtils.notNullAndEmpty(addCouponMultiPush.getNoWxMapping(), "要发送的客户不能为空！");
        if (Integer.valueOf("1").equals(addCouponMultiPush.getExecuteType())) {
            if (addCouponMultiPush.getExecuteTime() == null) {
                throw new TsfaServiceException(ErrorCode.COUPON_MULTI_PUSH_EXECUTE_TIME_EMPTY_ERROR,
                        "保存任务时执行时间不能为空");
            }
            if (addCouponMultiPush.getExecuteTime().before(new Date())) {
                throw new TsfaServiceException(ErrorCode.COUPON_MULTI_PUSH_EXECUTE_TIME_BEFORE_NOW_ERROR,
                        "执行时间不能早于当前时间");
            }
        }
        
        try {
            //转换数据,字符拼接noWx#num#codes;noWx#num#codes
            String noWxMapping = addCouponMultiPush.getNoWxMapping();
            String[] noWxMappingArr = noWxMapping.split(";");
            List<ShopTerminalMappingPm> shopTerminalMappingPms = new ArrayList<>(noWxMappingArr.length);
            for (String string : noWxMappingArr) {
                String[] split = string.split("#");
                ShopTerminalMappingPm shopTerminalMappingPm = new ShopTerminalMappingPm();
                shopTerminalMappingPm.setShopNoWx(split[0]);
                
                CouponMultiPushTerminal findCouponMultiPushTerminal = new CouponMultiPushTerminal();
                findCouponMultiPushTerminal.setNoWxShop(split[0]);
                findCouponMultiPushTerminal.setStatus(CouponMultiPushStatus.INIT.getCode());
                List<CouponMultiPushTerminal> list = couponMultiPushTerminalDao.selectBySelective(findCouponMultiPushTerminal );//检查该终端微信是否有未完成任务
                if (CollectionUtils.isNotEmpty(list)) {
                    throw new TsfaServiceException(ErrorCode.COUPON_MULTI_PUSH_SHOP_NO_WX_PENDING_ERROR, "该终端微信:" + split[0] + "有群发任务在执行中");
                }
                
                shopTerminalMappingPm.setNum(Integer.valueOf(split[1]));
                if (shopTerminalMappingPm.getNum() == 0) {//如果num为零，不加入集合
                    continue;
                }
                if (split.length >= 3) {
                    shopTerminalMappingPm.setCodes(split[2]);
                }
                if (split.length >= 4) {
                    shopTerminalMappingPm.setNoWxs(split[3]);
                }
                shopTerminalMappingPms.add(shopTerminalMappingPm);
            }
            addCouponMultiPush.setShopNoWxMappingPm(shopTerminalMappingPms);
            logger.debug("转换数据后：{}", shopTerminalMappingPms);
            
            //微信去重
            List<String> memberWxList = new ArrayList<>();
            for (ShopTerminalMappingPm shopTerminalMappingPm : shopTerminalMappingPms) {
                if (StringUtils.isBlank(shopTerminalMappingPm.getNoWxs())) {//没有选中客户
                    continue;
                }
                String[] noWxArr = shopTerminalMappingPm.getNoWxs().split(",");//客户微信数组
                String[] codeArr = shopTerminalMappingPm.getCodes().split(",");
                for (int i = 0; i < noWxArr.length; i++) {
                    String noWx = noWxArr[i];
                    String code = codeArr[i];
                    if (memberWxList.contains(noWx)) {
                        shopTerminalMappingPm.setNum(shopTerminalMappingPm.getNum() - 1);
                        String newCodes = null;
                        String newNoWxs = null;
                        if (i == 0) {
                            newCodes = shopTerminalMappingPm.getCodes().replace(code + ",", "");
                            newNoWxs = shopTerminalMappingPm.getNoWxs().replace(noWx + ",", "");
                        } else {
                            newCodes = shopTerminalMappingPm.getCodes().replace("," + code, "");
                            newNoWxs = shopTerminalMappingPm.getNoWxs().replace("," + noWx, "");
                        }
                        shopTerminalMappingPm.setCodes(newCodes);
                        shopTerminalMappingPm.setNoWxs(newNoWxs);
                        continue;
                    }
                    memberWxList.add(noWx);
                }
            }
            logger.debug("微信号去重后：{}", shopTerminalMappingPms);
            
            //生成群发表记录
            CouponMultiPush couponMultiPush = new CouponMultiPush();
            couponMultiPush.setCode(GUID.getPreUUID());
            
            FindCouponRule findCouponRule = new FindCouponRule();
            findCouponRule.setCode(addCouponMultiPush.getCouponRuleCode());
            FindCouponRuleReturn couponRule = couponRuleService.findCouponRule(findCouponRule);
            if (couponRule == null) {
                throw new TsfaServiceException(ErrorCode.COUPON_MULTI_PUSH_COUPON_RULE_NOT_FOUND_ERROR,
                        "该优惠券不存在");
            }
            couponMultiPush.setMerchantNo(couponRule.getMerchantNo());
            couponMultiPush.setMerchantName(couponRule.getMerchantName());
            couponMultiPush.setCouponRuleCode(couponRule.getCode());
            couponMultiPush.setCouponName(couponRule.getCouponName());
            couponMultiPush.setShopNoWxs(addCouponMultiPush.getShopNoWxs());
            
            //计算要发送的客户总数量
            int pmNum = 0;
            List<ShopTerminalMappingPm> shopNoWxMappingPmList = addCouponMultiPush.getShopNoWxMappingPm();
            for (ShopTerminalMappingPm shopTerminalMappingPm : shopNoWxMappingPmList) {
                pmNum += shopTerminalMappingPm.getNum();
            }
            couponMultiPush.setPmNum(pmNum);
            couponMultiPush.setSentPmNum(0);
            couponMultiPush.setDelayTimes(addCouponMultiPush.getDelayTimes());
            logger.debug("addCouponMultiPush.getExecuteType:" + addCouponMultiPush.getExecuteType());
            if (Integer.valueOf(1).equals(addCouponMultiPush.getExecuteType())) {
                couponMultiPush.setExecuteTime(addCouponMultiPush.getExecuteTime());
            } else {
                couponMultiPush.setExecuteTime(new Date());
            }
            logger.debug("addCouponMultiPush.getExecuteTime:" + couponMultiPush.getExecuteTime());
            
            //对比执行时间+延迟时间和优惠券的过期时间
            Date latestTime = DateUtils.addMinutes(couponMultiPush.getExecuteTime(), addCouponMultiPush.getDelayTimes());
            if (latestTime.after(couponRule.getEndDate())) {
                throw new TsfaServiceException(ErrorCode.COUPON_MULTI_PUSH_COUPON_EXECUTE_DATE_AFTER_EXPIRE_DATE_ERROR,
                        "该优惠券在发送前可能过期");
            }
            
            couponMultiPush.setStatus(CouponMultiPushStatus.INIT.getCode());
            couponMultiPush.setCreateId(addCouponMultiPush.getAssistantNo());
            couponMultiPush.setCreateDate(new Date());
            couponMultiPush.setUpdateId(addCouponMultiPush.getAssistantNo());
            couponMultiPush.setUpdateDate(new Date());
            couponMultiPushDao.insertSelective(couponMultiPush);
            
            //设置redis参数
            redisCache.hset(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPush.getCode(), "pmNum", pmNum + "");
            redisCache.hset(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPush.getCode(), "sentPmNum", "0");
            
            //存放群发终端对象，后面发起临时任务
            List<CouponMultiPushTerminal> couponMultiPushTerminalList = new ArrayList<>(shopNoWxMappingPmList.size());
            
            //生成群发终端表记录
            Set<Integer> randomDelaySet = new HashSet<>();//随机延迟时间的集合，不能重复
            for (ShopTerminalMappingPm shopTerminalMappingPm : shopNoWxMappingPmList) {
                if (shopTerminalMappingPm.getNum() == 0) {
                    continue;
                }
                
                CouponMultiPushTerminal couponMultiPushTerminal = new CouponMultiPushTerminal();
                couponMultiPushTerminal.setCode(GUID.getPreUUID());
                couponMultiPushTerminal.setMerchantNo(couponRule.getMerchantNo());
                couponMultiPushTerminal.setMerchantName(couponRule.getMerchantName());
                
                FindShopTerminalReturn findShopTerminalNormal = shopTerminalService.findShopTerminalNormal(shopTerminalMappingPm.getShopNoWx());
                if (findShopTerminalNormal == null) {
                    logger.error("优惠券群发任务错误:该终端微信{}不存在", shopTerminalMappingPm.getShopNoWx());
                    continue;
                }
//                couponMultiPushTerminal.setShopNo(findShopTerminalNormal.getShopNo());
//                couponMultiPushTerminal.setShopName(findShopTerminalNormal.getShopName());
                couponMultiPushTerminal.setPushCode(couponMultiPush.getCode());
                couponMultiPushTerminal.setNoWxShop(shopTerminalMappingPm.getShopNoWx());
                couponMultiPushTerminal.setPmNum(shopTerminalMappingPm.getNum());
                couponMultiPushTerminal.setSentPmNum(0);
                couponMultiPushTerminal.setCouponRuleCode(addCouponMultiPush.getCouponRuleCode());
                
                int delay = new Random().nextInt(addCouponMultiPush.getDelayTimes()) + 1;
                //生成随机延迟，如果重复再次生成
                while (randomDelaySet.contains(delay)) {
                    delay = new Random().nextInt(addCouponMultiPush.getDelayTimes()) + 1;
                }
                randomDelaySet.add(delay);
                Date executeTime = DateUtils.addMinutes(couponMultiPush.getExecuteTime(), delay);
                //这里先改为一分钟后，测试用
//                Date executeTime = DateUtils.addMinutes(addCouponMultiPush.getExecuteTime(), 1);
                couponMultiPushTerminal.setExecuteTime(executeTime);
                couponMultiPushTerminal.setStatus(CouponMultiPushStatus.INIT.getCode());
                couponMultiPushTerminal.setCreateId(addCouponMultiPush.getAssistantNo());
                couponMultiPushTerminal.setCreateDate(new Date());
                couponMultiPushTerminal.setUpdateId(addCouponMultiPush.getAssistantNo());
                couponMultiPushTerminal.setUpdateDate(new Date());
                
                couponMultiPushTerminalList.add(couponMultiPushTerminal);//加入集合
                couponMultiPushTerminalDao.insertSelective(couponMultiPushTerminal);
                
                redisCache.hset(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPush.getCode(), couponMultiPushTerminal.getCode() + ":" + "pmNum", shopTerminalMappingPm.getNum() + "");
                redisCache.hset(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPush.getCode(), couponMultiPushTerminal.getCode() + ":" + "sentPmNum", "0");
                
                //生成群发客户表记录
                String[] pmCodeArr = shopTerminalMappingPm.getCodes().split(",");
                
                FindPersonMember findPersonMember = new FindPersonMember();
                for (String pmCode : pmCodeArr) {
                    CouponMultiPushPm couponMultiPushPm = new CouponMultiPushPm();
                    couponMultiPushPm.setCode(GUID.getPreUUID());
                    couponMultiPushPm.setMerchantNo(couponRule.getMerchantNo());
                    couponMultiPushPm.setMerchantName(couponRule.getMerchantName());
//                    couponMultiPushPm.setShopNo(findShopTerminalNormal.getShopNo());
//                    couponMultiPushPm.setShopName(findShopTerminalNormal.getShopName());
                    couponMultiPushPm.setPushCode(couponMultiPush.getCode());
                    couponMultiPushPm.setPushTerminalCode(couponMultiPushTerminal.getCode());
                    couponMultiPushPm.setNoWxShop(shopTerminalMappingPm.getShopNoWx());
                    
                    //查询导购信息
                    findPersonMember.setCode(pmCode);
                    FindPersonMemberReturn findPersonMemberReturn = personMemberService.findPersonMember(findPersonMember);
                    couponMultiPushPm.setMemberNo(findPersonMemberReturn.getMemberNo());
                    couponMultiPushPm.setMemberNoGm(findPersonMemberReturn.getMemberNoGm());
                    couponMultiPushPm.setStatus(CouponMultiPushStatus.INIT.getCode());
                    couponMultiPushPm.setCreateId(addCouponMultiPush.getAssistantNo());
                    couponMultiPushPm.setCreateDate(new Date());
                    couponMultiPushPm.setUpdateId(addCouponMultiPush.getAssistantNo());
                    couponMultiPushPm.setUpdateDate(new Date());
                    couponMultiPushPmDao.insertSelective(couponMultiPushPm);
                    
                    //初始状态
                    redisCache.hset(KeyConstant.COUPON_MULTI_PUSH_KEY + couponMultiPush.getCode(), couponMultiPushTerminal.getCode() + ":" + couponMultiPushPm.getCode() + ":status", "0");
                }
            }
            
            //注册临时任务
            logger.debug("CouponMultiPush:开始注册群发优惠券任务调度");
            for (CouponMultiPushTerminal couponMultiPushTerminal : couponMultiPushTerminalList) {
                JobCenter jc = JobUtils.getJobCenterWithDate(couponMultiPushTerminal.getExecuteTime());
                String jobName = "couponMultiPushJob:" + couponMultiPushTerminal.getCode();
                jc.setJobEnglishName(jobName);
                
                String callbackUrl = localCacheSystemParams.getSystemParam("weixin", "couponMultiPushJob", "couponMultiPushJobCallbackUrl");
                jc.setJobIntf(callbackUrl);
                jc.setJobName("群发优惠券任务调度");
                jc.setSystemAliasName("weixin");
                
                //构造任务调度入参
                jc.setJobParam(couponMultiPushTerminal.getCode());
                logger.debug("couponMultiPushJob: " + jc);
                
                jobMgrService.addTempJob(jc);
            }
        } catch (TsfaServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("新增优惠券群发任务错误！", e);
            throw new TsfaServiceException(ErrorCode.COUPON_MULTI_PUSH_JOB_ADD_ERROR, "新增优惠券群发任务错误！", e);
        }
        
        AddCouponMultiPushReturn addCouponMultiPushReturn = new AddCouponMultiPushReturn();

        logger.debug("addCouponMultiPush(AddCouponMultiPush) - end - return value={}", addCouponMultiPushReturn); 
        return addCouponMultiPushReturn;
    }

}
