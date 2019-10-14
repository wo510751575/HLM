/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.h5;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lj.base.core.encryption.MD5;
import com.lj.base.mvc.web.httpclient.HttpClientUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.weixin.dto.WxRedpackDetailInfoDto;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.IDistributeCache;


/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年4月13日
 */
@Controller
@RequestMapping("/h5/")
public class H5CommonAction extends Action {

//	private static Logger logger = LoggerFactory.getLogger(H5CommonAction.class);
	
//	@Resource
//	private ITodayMemberSummaryService todayMemberSummaryService;
	@Resource
	public IGuidMemberService guidMemberService;
	@Resource
	private IAddFriendsService addFriendsService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
	private IDistributeCache redisCache;
	
	@Resource
	private IWxRedpackDetailInfoService wxRedpackDetailInfoService;
	
	/**
	 * 
	 *
	 * 方法说明：添加今日客户汇总记录
	 *
	 * @param addTodayMemberSummary
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月13日
	 *
	 */
//	@RequestMapping(value="addMemberSummary.do",produces="application/json;charset=UTF-8")
//	@ResponseBody
//	public GeneralResponse addMemberSummary(AddTodayMemberSummary addTodayMemberSummary)  {
//		todayMemberSummaryService.addTodayMemberSummary(addTodayMemberSummary);
//		return GeneralResponse.generateSuccessResponse();
//	}


	/**
	 * 
	 *
	 * 方法说明：根据商户号查询终端列表
	 *
	 * @param merchantNo
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年04月13日
	 *
	 */
	/*@RequestMapping(value = "findShops.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<FindShopPageReturn> findShops(String merchantNo) {
		Map<String, String> map = localCacheSystemParams.getSystemParamGroup(SystemAliasName.ms.name(), "SUMMARY_SHOP");
		Set<Entry<String, String>> set = map.entrySet();
		List<FindShopPageReturn> returnList = new ArrayList<>();
		FindShopPageReturn shop = null;
		for(Entry<String, String> entry : set) {
			shop = new FindShopPageReturn();
			shop.setShopNo(entry.getValue());
			shop.setShopName(entry.getKey());
			returnList.add(shop);
		}
//		FindShop findShop = new FindShop();
//		findShop.setMemberNoMerchant(merchantNo);
//		List<FindShopPageReturn> returnList = shopService.findShops(findShop);
        return returnList;
    }*/
	
	/**
	 * 
	 *
	 * 方法说明：查询终端下导购
	 *
	 * @param merchantNo
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年04月13日
	 *
	 */
	@RequestMapping(value = "findGmByShop.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<GuidMemberReturnDto> findGmByShop(FindGuidMemberDto findGuidMemberDto) {
		return guidMemberService.findGuidMemberList(findGuidMemberDto);
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：查询最少客户的二维码
	 * 
	 * @param shopNo
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "selectZKQcode.do")
	public String selectZKQcode(String merchantNo,String code, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
		
		try {
			if(code == null) {
				request.setAttribute("error", "code 不能为空");
				return "activity/showScanCode/qcode";
			}
			if(merchantNo == null) {
				request.setAttribute("error", "merchantNo 不能为空");
				return "activity/showScanCode/qcode";
			}
			int dayLimit = 250;
			int totalLimit = 4000;
			String qcode = "";
			qcode = addFriendsService.findWxGmByMerchantNo(merchantNo, code, dayLimit , totalLimit );
			String fileUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.name(), "upload", "uploadUrl");
			if(qcode != null && !qcode.equals("null") && !qcode.equals("")) {
				qcode = fileUrl + qcode;
			}
			request.setAttribute("code", qcode);
			
			return "/activity/showScanCode/qcode";
		} catch (Exception e) {
			 logger.error("获取二维码信息错误", e);
             return "";
		}
	}
	
	@RequestMapping(value = "toSelectJudePhonePage.do")
	public String toSelectJudePhonePage(String noWx,String noWxShop,HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setAttribute("noWx", noWx);
		request.setAttribute("noWxShop", noWxShop);
          return "/activity/scanTheLandingPage/phone";
	}
	/**
	 * 
	 *
	 * 方法说明：向客户提供的接口传手机号，核对是不是会员，专为plum提供，对接其他商户的时候可拷贝修改
	 * 判断下一步操作
	 * @param shopNo
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "selectJudePhone.do")
	@ResponseBody
	public Map<String,String> selectJudePhone(String phone, String noWx,String noWxShop, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String,String> map = new HashMap<String,String>();
		String result = null;
		try {
			//防止1秒内多次提交
			String p = redisCache.get("REDIS-KEY-QUERY-PHONE-"+ phone);
			if(p != null && p.length() > 0) {
				map.put("result", "false");
	    		map.put("message", "不要二次提交");
				return map;
			}
			//防止1秒内多次提交
			redisCache.set("REDIS-KEY-QUERY-PHONE-"+ phone, phone, 1); 
			
			String bathNo = "20190107";
			String ts = String.valueOf(System.currentTimeMillis());
			String url = "https://open.aplum.com/juke/query-by-phone?phone="+phone+"&ts="+ts+"&sn="+MD5.encryptByMD5(ts+"plum&juke");
		    result = HttpClientUtils.httpGet(url);
		    logger.info("第三方返回结果："+ result);
		    JSONObject jsStr = JSONObject.parseObject(result);
		    //请求成功
		    if(jsStr.get("success").equals("true") || (Boolean)jsStr.get("success") == true) {
		    	JSONObject s = (JSONObject)jsStr.get("data");
		    	if(s.get("ordered").equals("true") || (Boolean)s.get("ordered") == true) {
		    		map.put("result", "ture");
		    		logger.info("验证通过" + result);
		    		map.put("message", "验证已通过，稍后系统核实发送红包");
		    		WxRedpackDetailInfoDto wxRedpackDetailInfoDto = new WxRedpackDetailInfoDto();
		    		wxRedpackDetailInfoDto.setAmount(1L);//1L 代表1分钱 ； 100L代表 1元
		    		wxRedpackDetailInfoDto.setBatchCode(bathNo);
		    		wxRedpackDetailInfoDto.setNoWx(noWx);
		    		wxRedpackDetailInfoDto.setPhoneNumber(phone);
		    		wxRedpackDetailInfoDto.setNoWxShop(noWxShop);
		    		Map<String,String> hashmap = wxRedpackDetailInfoService.addWxRedpackDetailInfoByActivity(wxRedpackDetailInfoDto);
		    	
		    		return hashmap;
		    	}
		    	//不存在手机号
		    	if( !(s.get("ordered").equals("true") || (Boolean)s.get("ordered") == true)) {
		    		map.put("result", "false");
		    		map.put("message", "亲，你提供的手机号没有购买过我们的产品");
		    		return map;
		    	}
		    	
		    }
		    if(jsStr.get("success").equals("false") || (Boolean)jsStr.get("false") == true) {
		    	map.put("result", "false");
		    	map.put("message", jsStr.getString("errors"));
		    }
			return map;
		} catch (Exception e) {
			logger.info("第三方返回结果错误："+ result, e);
             return map;
		}
	}
}
