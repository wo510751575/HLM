package com.lj.business.cp.service.impl.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cp.dao.ICouponDao;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.dto.couponRule.UpdateCouponRule;
import com.lj.business.cp.emus.IsProduce;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.cc.clientintf.IJob;
/**
 * 
 * 
 * 类说明：生成优惠券调度任务
 * 每天零点执行
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 杨恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2018年1月25日
 */
@Service
public class CouponRuleTodayEnableDateJobs implements IJob {
	
	private static final Logger logger = LoggerFactory.getLogger(CouponRuleTodayEnableDateJobs.class);
	 
	@Resource
	private ICouponRuleService couponRuleService;
	
	@Resource
	private ICouponService couponService; 
	
//	@Resource
//	private IShopService shopService;
	
	@Resource
	private ICouponDao couponDao;
	
	@Override
	public void runJob() {
		
		logger.info("couponRuleTodayEnableDateJob start 开始"); 		
		//获取当天所有商户下状态为启用和在有效期内,未生成优惠券的规则
		List<FindCouponRuleReturn> list = couponRuleService.findcouponRuleTodayEnableDate();
		
		logger.debug("findcouponRuleTodayEnableDate()",list);
		try {
			for(FindCouponRuleReturn couponRuleReturn:list){
				//所有终端则平均分配券
//				if(couponRuleReturn.getUseScope().equals("ALL")){
//				      FindShop findShop = new FindShop();
//				      findShop.setMemberNoMerchant(couponRuleReturn.getMerchantNo());
//				      List<FindShopPageReturn> shops= shopService.findShops(findShop);
//				      Coupon coupon = new Coupon();
//				      int num = couponRuleReturn.getCouponAvgNum();
//				       for(FindShopPageReturn findShopPageReturn :shops){
//				    	     for(int i=0;i<num ;i++){
//				    	    	    coupon.setCode(GUID.getPreUUID());
//				    	    	    coupon.setMerchantNo(findShopPageReturn.getMemberNoMerchant());
//				    	    	    coupon.setMerchantName(findShopPageReturn.getMemberNameMerchant());
//				    	    	    coupon.setShopNo(findShopPageReturn.getShopNo());
//				    	    	    coupon.setShopName(findShopPageReturn.getShopName());
//				    	    	    coupon.setCouponNo(GUID.getPreUUID());
//				    	    	    coupon.setRuleNo(couponRuleReturn.getCode());
//				    	    	    coupon.setCouponStatus(CouponStatus.UNUSED.toString());
//				    	    	    coupon.setCreateDate(new Date());
//									couponDao.insert(coupon);
//				    	     }
//				      }
//					}else{
					 //指定终端分配
//					@SuppressWarnings("unchecked")
//					Map<String,String> map =(Map<String, String>) StringTokenizers(couponRuleReturn.getShopName(), couponRuleReturn.getShopNo(), true);
					/*@SuppressWarnings("unchecked")
					List<Integer> lists =(List<Integer>) StringTokenizers(couponRuleReturn.getUseNums(), null, false);
					Iterator<Entry<String, String>> entries = map.entrySet().iterator();
					int i=0;
					Coupon coupon = new Coupon();
			       	  while (entries.hasNext()){ 
			       	      Map.Entry<String, String> entry = entries.next();
			       	      int useNu = lists.get(i);
			       	      for(int k= 0;k< useNu;k++){
			       	    	coupon.setCode(GUID.getPreUUID());
							coupon.setMerchantNo(couponRuleReturn.getMerchantNo());
							coupon.setMerchantName(couponRuleReturn.getMerchantName());
//							coupon.setShopNo(entry.getKey());
//							coupon.setShopName(entry.getValue());
							coupon.setCouponNo(GUID.getPreUUID());
							coupon.setRuleNo(couponRuleReturn.getCode());
							coupon.setCouponStatus(CouponStatus.UNUSED.toString());
							coupon.setCreateDate(new Date());
							couponDao.insert(coupon);
			       	      }
			       	      
			       	  }
						
					}*/
				//制券成功则更新状态
				UpdateCouponRule updateCouponRule = new UpdateCouponRule();
				updateCouponRule.setCode(couponRuleReturn.getCode());
				updateCouponRule.setIsProduce(IsProduce.YES.toString());
				couponRuleService.updateCouponRule(updateCouponRule);
				}
		} catch (TsfaServiceException e) {
		 logger.error(e.getMessage(),e);
		 
		}catch (Exception e) {
			logger.error("执行优惠券调度任务异常！",e);
		}
		
		logger.info("couponRuleTodayEnableDateJob end 结束"); 	

	}
	
    public static Object StringTokenizers(String shopName,String shopNo,boolean b){
    	 Map<String,String> map = new HashMap<String, String>();
    	if(b){
       	  StringTokenizer token=new StringTokenizer(shopName,",");  
       	  StringTokenizer tokenizer=new StringTokenizer(shopNo,",");  
       	  while (token.hasMoreElements()) {
   			map.put(tokenizer.nextToken(), token.nextToken());
   		}
       	 return map;
    	}else{
    		StringTokenizer token=new StringTokenizer(shopName,",");  
    		List<Integer> list = Lists.newArrayList();
    	     while (token.hasMoreElements()) {
    	    	 list.add(Integer.valueOf(token.nextToken()));
			}
    	 	return list;
    	}
    	 
    }
   
	
}
