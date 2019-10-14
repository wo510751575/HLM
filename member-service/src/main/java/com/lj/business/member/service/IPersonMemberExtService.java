package com.lj.business.member.service;

import com.lj.business.member.dto.MecMemberNoReturn;
import com.lj.business.member.dto.PersonMemberExtDto;

public interface IPersonMemberExtService {
	
	public int findCountByMemberNo(String memberNo);

	public void addPersonMemberExt(PersonMemberExtDto dto);
	
	/**
	 * 根据openid查询memberNo
	 * @param openId
	 * @return
	 */
	public MecMemberNoReturn findPmbByOpenId(String openId);
	
	/**
	 * 
	 *
	 * 方法说明：客户与openid绑定:
	 * 1、同一客户可绑定多个openid
	 * 2、一个openid最多只能绑定一个客户
	 *
	 * @param memberNo
	 * @param openId
	 *
	 * @author 曾垂瑜 CreateDate: 2018年6月5日
	 *
	 */
	public void bindMemberNo(String memberNo,String openId);
	
	/**
	 * 修改客户信息
	 * @param dto
	 */
	public void updatePersonMemberExt(PersonMemberExtDto dto);
	
	PersonMemberExtDto findByMemberNo(String memberNo);
	
}
