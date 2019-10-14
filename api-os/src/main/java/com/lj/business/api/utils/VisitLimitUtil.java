package com.lj.business.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import com.lj.base.core.util.DateUtils;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.dto.merchantParams.FindMerchantParamsReturn;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.distributecache.IDistributeCache;

/**
 * 
 * 类说明：访问控制时调用
 * <p>
 * 详细描述：对外接口加入访问控制
 *   
 * @Company: 扬恩科技有限公司
 * @author 李端强
 *   
 * CreateDate: 2018年1月9日
 */
public class VisitLimitUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(VisitLimitUtil.class);
	
	/**
	 * 方法说明：判断该商户调用对应的方法和key是否被限制,distributeCache 商户编号=时间戳+"_"+单日累积次数
	 * @param merchentNo 商户编号
	 * @param distributeCache
	 * @param localCacheSystemParams
	 * @param keyPre 配置项前缀
	 * @return true被限制,false可调用
	 * @author 李端强 CreateDate: 2018年1月9日
	 *
	 */
	public static synchronized boolean limited(String merchentNo,IDistributeCache distributeCache,IMerchantParamsService merchantParamsService,String keyPre) {
		logger.debug("limited(String merchentNo={}, IDistributeCache distributeCache={}, IMerchantParamsService merchantParamsService={}, String keyPre={}) - start", merchentNo, distributeCache, merchantParamsService, keyPre); //$NON-NLS-1$
		boolean ret = false;
		try {
			FindMerchantParams findMerchantParams = new FindMerchantParams();
			findMerchantParams.setGroupName("openApi_frequency_control");
			List<FindMerchantParamsReturn> openApiList = merchantParamsService.findMerchantParamsByGN(findMerchantParams);//获取cm商户系统参数
			if(openApiList==null || openApiList.size()==0) {
				logger.debug("limited(String, IDistributeCache, IMerchantParamsService, String) - end - return value={}", ret); //$NON-NLS-1$
				return ret;//未设置限制
			}
			float frequency = 1;//每分钟频率限制,默认一分钟1次
			float times = 0;//一天总次数限制
			for (FindMerchantParamsReturn api : openApiList) {
				if(api.getMerchantNo().equals(merchentNo)){//商户已配置该参数
					if(api.getSysParamName().equals(keyPre+"_frequency")) {
						frequency = Float.parseFloat(api.getSysParamValue());
					}
					if(api.getSysParamName().equals(keyPre+"_times")) {
						times = Float.parseFloat(api.getSysParamValue());
					}
				}
			}
			if(times==0) {
				logger.debug("limited(String, IDistributeCache, IMerchantParamsService, String) - end - return value={}", ret); //$NON-NLS-1$
				return ret;//未设置限制
			}
			String historyVisit = distributeCache.get("limited_"+merchentNo);//取缓存
			if(historyVisit==null) {
				distributeCache.set("limited_"+merchentNo, System.currentTimeMillis()+"_"+1);//更新缓存
			}else {
				String[] hisArr = historyVisit.split("_");
				long currentTime = System.currentTimeMillis();//当前时间
				long hisTime = Long.parseLong(hisArr[0]);//上次时间
				int  hisCount = Integer.parseInt(hisArr[1]);//当天已累积次数
				if((currentTime-hisTime)<60*1000/frequency) {//每分间隔判断
					logger.debug("limited(String, IDistributeCache, IMerchantParamsService, String) - end - return value={}", true); //$NON-NLS-1$
					return true;
				}
				//当天
				long currentDate = DateUtils.parseDate(DateUtils.formatDate(new Date(currentTime),"yyyy-MM-dd"), "yyyy-MM-dd").getTime();
				long hisDate = DateUtils.parseDate(DateUtils.formatDate(new Date(hisTime),"yyyy-MM-dd"), "yyyy-MM-dd").getTime();
				if(currentDate == hisDate) {//同一天
					if((hisCount+1)>times) {//同一天总访问次数判断
						logger.debug("limited(String, IDistributeCache, IMerchantParamsService, String) - end - return value={}", true); //$NON-NLS-1$
						return true;
					}
				}
				distributeCache.del("limited_"+merchentNo);
				distributeCache.set("limited_"+merchentNo, System.currentTimeMillis()+"_"+(hisCount+1));//更新缓存
			}
		} catch (Exception e) {
			logger.error("limited(String, IDistributeCache, IMerchantParamsService, String)", e);
			return true;
		}
		logger.debug("limited(String, IDistributeCache, IMerchantParamsService, String) - end - return value={}", ret); //$NON-NLS-1$
		return ret;
	}
}
