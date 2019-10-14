package com.lj.business.api.controller.wx;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.dto.WxAuthDto;
import com.lj.business.api.dto.WxDto;
import com.lj.business.api.utils.WeixinUtil;
import com.lj.oms.service.OfficeHessianService;


@Controller
@RequestMapping("/weixin/")
public class WxAction {
	
	
	@Resource
	OfficeHessianService officeHessianService;
	
	private static Logger LOG=LoggerFactory.getLogger(WxAction.class);
	
	@RequestMapping(value="getOpenId.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse getOpenId(WxDto wxDto){
		LOG.info("WxAction.getOpenId(wxDto) --------start:{}",wxDto);
		String openId = WeixinUtil.getOpenId(wxDto.getWxCode(), "", "");
		AssertUtils.notNullAndEmpty(openId);
		return GeneralResponse.generateSuccessResponse(openId);
	} 
	
	/**
	 * 方法说明：获取小程序openId
	 * @param wxDto
	 * @return
	 *
	 * @author lhy CreateDate: 2018年1月22日
	 *
	 */
//	@RequestMapping(value="/xcx/getOpenId.do", produces="application/json;charset=UTF-8")
//	@ResponseBody
//	public GeneralResponse getXcxOpenId(WxDto wxDto){
//		LOG.info("WxAction.getOpenId(wxDto) --------start:{}",wxDto);
//		AssertUtils.notNull(wxDto.getShopNo(),"终端编号为空");
//		EshopCompanyAccountDto eshopCompanyAccountDto = new EshopCompanyAccountDto();
//		/*
//		 * 改从终端查询所属机构
//		 * update by   zengchuiyu
//		 * update date 2018-05-28
//		 * 
//		 * */Office office = officeHessianService.findByShopNo(wxDto.getShopNo());
//		AssertUtils.notNull(office,"收款机构不存在");
//		eshopCompanyAccountDto.setOrgCode(office.getId());
//		FindShopReturn shop = shopService.findshop(wxDto.getShopNo());
//		AssertUtils.notNull(shop,"收款机构不存在");
//		eshopCompanyAccountDto.setOrgCode(shop.getCompanyNo());
//		eshopCompanyAccountDto.setPayType(PayTypeEmus.WEIXIN_XCX.getPayType());
//		eshopCompanyAccountDto = eshopCompanyAccountService.findEshopCompanyAccountByOrgCodeAndPayType(eshopCompanyAccountDto);
//		AssertUtils.notNull(eshopCompanyAccountDto,"收款信息不存在");
//		String openId = WeixinUtil.getXcxOpenId(wxDto.getWxCode(), eshopCompanyAccountDto.getAppId(), eshopCompanyAccountDto.getPublicKey());
//		AssertUtils.notNullAndEmpty(openId);
//		return GeneralResponse.generateSuccessResponse(openId);
//	} 
	
//	@RequestMapping(value="toWxAuth.do")
//	public String toWxAuth(WxAuthDto wxAuthDto) throws UnsupportedEncodingException{
//		LOG.info("WxAction.toWxAuth(WxAuthDto) -----------start:{}",wxAuthDto);
//		AssertUtils.notNull(wxAuthDto);
//		AssertUtils.notNullAndEmpty(wxAuthDto.getRedirectUri(),"授权页面不能为空");
//		AssertUtils.notNullAndEmpty(wxAuthDto.getShopNo(),"终端号不能为空");
		//AssertUtils.notNullAndEmpty(wxAuthDto.getMemberNo(),"会员号不能为空");
		/*
		 * 改从终端查询所属机构
		 * update by   zengchuiyu
		 * update date 2018-05-28
		 * 
		 * Office office = officeHessianService.findByShopNo(wxAuthDto.getShopNo());
		AssertUtils.notNull(office,"终端所属机构不能为空");
		LOG.info("WxAction.toWxAuth  office:{}",office);
		EshopCompanyAccountDto eshopCompanyAccountDto  = new EshopCompanyAccountDto();
		eshopCompanyAccountDto.setOrgCode(office.getId());*/
//		FindShopReturn shop = shopService.findshop(wxAuthDto.getShopNo());
//		AssertUtils.notNull(shop,"终端所属机构不能为空");
//		LOG.info("WxAction.toWxAuth  office:{}",shop);
//		EshopCompanyAccountDto eshopCompanyAccountDto  = new EshopCompanyAccountDto();
//		eshopCompanyAccountDto.setOrgCode(shop.getCompanyNo());
//		eshopCompanyAccountDto.setPayType(PayTypeEmus.WEIXIN_OFFICIAL.getPayType());
//		eshopCompanyAccountDto = eshopCompanyAccountService.findEshopCompanyAccountByOrgCodeAndPayType(eshopCompanyAccountDto);
//		if(eshopCompanyAccountDto==null){  //如果没有公众号支付
//			eshopCompanyAccountDto  = new EshopCompanyAccountDto();
//			eshopCompanyAccountDto.setOrgCode(shop.getCompanyNo());
//			eshopCompanyAccountDto.setPayType(PayTypeEmus.WEIXIN_H5_PAY.getPayType());
//			eshopCompanyAccountDto = eshopCompanyAccountService.findEshopCompanyAccountByOrgCodeAndPayType(eshopCompanyAccountDto);
//		}
//		String url = wxAuthDto.getRedirectUri()+"?memberNo="+wxAuthDto.getMemberNo()+"&shopNo="+wxAuthDto.getShopNo()+"&parmas="+wxAuthDto.getParmas();
//		url  = URLEncoder.encode(url,"UTF-8");
//		AssertUtils.notNull(eshopCompanyAccountDto,"收款信息不存在");
//		String wxAuthUrl = ApiConstans.WX_AUTH_URL.replace("APPID", eshopCompanyAccountDto.getAppId()).replace("REDIRECT_URI",url);
//		LOG.info("redirect:{}",wxAuthUrl);
//		return "redirect:"+wxAuthUrl;

//	}
	
	
	
	
	

}
