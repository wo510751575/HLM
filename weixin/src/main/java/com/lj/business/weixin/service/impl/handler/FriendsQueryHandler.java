package com.lj.business.weixin.service.impl.handler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.service.IImFriendsInfoService;


@Component
public  class FriendsQueryHandler {
	
	@Resource
	IGuidMemberService guidMemberService;
	
//	@Resource
//	IShopService shopService;
	
	@Resource
	IShopTerminalService shopTerminalService;
	
	@Resource
	IPersonMemberBaseService personMemberBaseService;
	
	@Resource
	IImFriendsInfoService imFriendsInfoService;
	
	
	@Resource
	IPersonMemberService personMemberService;
	

	
	/**
	 * 查询导购信息
	 * @param memberNoGm
	 * @return
	 */
	public GuidMemberReturnDto getGuidMember(String memberNoGm){
		FindGuidMemberDto guidMemberDto = new FindGuidMemberDto();
		guidMemberDto.setMemberNo(memberNoGm);
		GuidMemberReturnDto guidMember = guidMemberService.findGuid(guidMemberDto);
		AssertUtils.notNull(guidMember,"导购信息不存在");
		return  guidMember;
	}
	/**
	 * 查询终端
	 * @param shopNo
	 * @return
	 */
//	public FindShopReturn  getShop(String shopNo){
//		FindShop findShop = new FindShop();
//		findShop.setShopNo(shopNo);
//		FindShopReturn findShopReturn = shopService.findShopByShopNo(findShop);
//		AssertUtils.notNull(findShopReturn,"终端信息不存在");  
//		return findShopReturn;
//	}
	/**
	 * 查询客户信息
	 * @param memberNo
	 * @return
	 */
	public FindPersonMemberBaseReturn getPersonMember(String memberNo){
		FindPersonMemberBase personMemberBase = new FindPersonMemberBase();
		personMemberBase.setMemberNo(memberNo);
		FindPersonMemberBaseReturn member = personMemberBaseService.findPersonMemberBase(personMemberBase);
		AssertUtils.notNull(member,"客戶不存在");
		return member;
	}
	/**
	 * 根据微信号获取客户信息
	 * @param noWx
	 * @return
	 */
	public FindPersonMemberBaseReturn getPersonMemberByWxNo(String noWx){
		FindPersonMemberBase personMemberBase = new FindPersonMemberBase();
		personMemberBase.setNoWx(noWx);
		FindPersonMemberBaseReturn personMember = personMemberBaseService.findPersonMemberBase(personMemberBase);
		return personMember;
	}
	/**
	 * 查询终端终端
	 * @param imei
	 * @return
	 */
	public FindShopTerminalReturn getShopTerminal(String noWxGm){
		FindShopTerminalReturn shopTerminal = shopTerminalService.findShopTerminalByWx(noWxGm);
		AssertUtils.notNull(shopTerminal,"门店终端不存在");
		return shopTerminal;
	}
//	public FindShopTerminalReturn getShopTerminal(String imei){
//		FindShopTerminalReturn shopTerminal = shopTerminalService.findShopTerminalByImei(imei);
//		AssertUtils.notNull(shopTerminal,"终端终端不存在");
//		return shopTerminal;
//	}
	/**
	 * 查询朋友圈信息 
	 * @param friendsIds
	 * @return
	 */
	public ImFriendsInfoDto  getFriendsInfo(String friendsIds,String noWxShop){
		ImFriendsInfoDto imFriendsInfoDto  = imFriendsInfoService.getImFriendsInfoByFriendsId(friendsIds,noWxShop);
		return imFriendsInfoDto;
	}
	
	
	public FindShopTerminalReturn getShopTerminalByShopNo(String shopNo){
		List<FindShopTerminalReturn> tList = shopTerminalService.findShopTerminalByShopNo(shopNo);
		if(tList!=null&&tList.size()!=0)
			return tList.get(0);
		return null;
	}
	
	
	public FindShopTerminalReturn getShopTerminalServiceByNoWx(String noWx){
		FindShopTerminalReturn shopTerminal = shopTerminalService.findShopTerminalNormal(noWx);
		AssertUtils.notNull(shopTerminal,"终端终端不存在");
		return shopTerminal;
	}
}
