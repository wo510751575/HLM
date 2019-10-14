package com.lj.business.weixin.service.impl;

import java.net.MalformedURLException;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.util.DateUtils;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.dto.ToFriendsInfosDto;
import com.lj.business.weixin.dto.ToFriendsLikeDto;
import com.lj.business.weixin.dto.UpdateFriendsMemberInfo;
import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;

public class ImFriendsInfoServiceTest  extends SpringTestCase{
	
	
	@Resource
	IImFriendsFacade imFriendsFacade;
	
	
	@Resource
	IImFriendsInfoService imFriendsInfoService;
	
	@Resource
	IShopTerminalService shopTerminalService;
	
	@Test
	public void testSendFriendsInfo() throws MalformedURLException, ClassNotFoundException{

		
		//System.out.println(toFriendsInfosDto);
//		for(int i = 0;i<10;i++ ){
			ToFriendsInfosDto toFriendsInfosDto = new ToFriendsInfosDto();
				toFriendsInfosDto.setNoWxShop("wxid_klh1bsjjjg3e22");
//				toFriendsInfosDto.setContent("公开");
				toFriendsInfosDto.setType("1");
				toFriendsInfosDto.setImgAddr("http://192.168.3.7/logo/coupon58ecdb4c209ae9f2236775c2a3.png");
//				toFriendsInfosDto.setWhoType("1");
				
				toFriendsInfosDto.setWhoType("2");
				toFriendsInfosDto.setContent("私密");
				
//				toFriendsInfosDto.setWhoType("3");
//				toFriendsInfosDto.setWhoNoWxs("KENNYvip6688,wxid_ydt2s4czv3ua22");
//				toFriendsInfosDto.setContent("部分可见");
				
//				toFriendsInfosDto.setWhoType("4");
//				toFriendsInfosDto.setContent("不给谁看");
//				toFriendsInfosDto.setWhoNoWxs("KENNYvip6688");
				
//				toFriendsInfosDto.setWhoType("1");
//				toFriendsInfosDto.setContent("公开提醒");
//				toFriendsInfosDto.setRemindNoWxs("KENNYvip6688,wxid_ydt2s4czv3ua22");
				
//				toFriendsInfosDto.setWhoType("2");		微信不支持，需要控制
//				toFriendsInfosDto.setContent("私密提醒");
//				toFriendsInfosDto.setRemindNoWxs("KENNYvip6688,wxid_ydt2s4czv3ua22");
				
//				toFriendsInfosDto.setWhoType("3");		
//				toFriendsInfosDto.setContent("部分可见");
//				toFriendsInfosDto.setWhoNoWxs("KENNYvip6688");
//				toFriendsInfosDto.setRemindNoWxs("KENNYvip6688,wxid_ydt2s4czv3ua22");
				
//				toFriendsInfosDto.setWhoType("4");		
//				toFriendsInfosDto.setContent("不给谁看");
//				toFriendsInfosDto.setWhoNoWxs("KENNYvip6688");
//				toFriendsInfosDto.setRemindNoWxs("KENNYvip6688,wxid_ydt2s4czv3ua22");
				
			imFriendsFacade.toFriendsInfo(toFriendsInfosDto);
//		}
		/*	imFriendsFacade.toFriendsInfo(toFriendsInfosDto);*/
	}
	
	
	@Test
	public void testSendLikeInfo() throws MalformedURLException, ClassNotFoundException{
		ToFriendsLikeDto toFriendsInfosDto = new ToFriendsLikeDto();
		toFriendsInfosDto.setToFriendsId("-5744047124741738270");
//		toFriendsInfosDto.setShopNo("LJ_7347955bc5b64d8dbddf07d8cd4228d5");
		toFriendsInfosDto.setShopWxNo("fanfan558989");
		imFriendsFacade.toImLike(toFriendsInfosDto);
		
	}
	
	
	/*@Test
	public void testSendCommentInfo() throws MalformedURLException, ClassNotFoundException{
		String url = "http://192.168.6.62:18080/weixin/hessian/hessianProxy";
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			HesssianServiceProxy imFriendsFacade = (HesssianServiceProxy) factory.create(HesssianServiceProxy.class, url);
			ToFriendsCommentDto toFriendsInfosDto = new ToFriendsCommentDto();
			toFriendsInfosDto.setToFriendsId("-5744047124741738270");
			toFriendsInfosDto.setShopNo("LJ_7347955bc5b64d8dbddf07d8cd4228d5");
			toFriendsInfosDto.setShopWxNo("fanfan558989");
			toFriendsInfosDto.setToConent("测试评论");
			toFriendsInfosDto.setToWxNo("fanfan558989");
			toFriendsInfosDto.setToWxName("Louise");

		//	imFriendsFacade.toComment(toFriendsInfosDto);
			RequestWarpper requestWarpper =new  RequestWarpper();
			requestWarpper.setServiceInterface(HesssianServiceProxy.class.getName());
			requestWarpper.setMethod("toComment");
			requestWarpper.setParams(new Object[]{toFriendsInfosDto});
			requestWarpper.setRequestId("test");
			imFriendsFacade.handler(requestWarpper);
		}catch (Exception e){

		}
		
	}*/
	
	public void testFriendsPage(){

	}

	@Test
	public void testUpdateFriendsMemberInfo() {
		UpdateFriendsMemberInfo updateFriendsMemberInfo = new UpdateFriendsMemberInfo();
//		updateFriendsMemberInfo.setShopNo("LJ_c6d597199ef643a1a2e843175cdda24d");
		updateFriendsMemberInfo.setNoWxShop("ljkj7799");
		updateFriendsMemberInfo.setNoWx("wxid_1o5dj64vyyxu22");
		updateFriendsMemberInfo.setBeginTime(DateUtils.addDays(new Date(), -10));
		updateFriendsMemberInfo.setMemberNo("LJ_9e6a92dc86784f0ca0c1c7621bbf02a3");
		updateFriendsMemberInfo.setMemberName("浩浩");
		updateFriendsMemberInfo.setMemberNoGm("LJ_c8250a1894d34503987d7fe217b4674a");
		updateFriendsMemberInfo.setMemberNameGm("金波");
		imFriendsInfoService.updateFriendsMemberInfo(updateFriendsMemberInfo);
	}
}
