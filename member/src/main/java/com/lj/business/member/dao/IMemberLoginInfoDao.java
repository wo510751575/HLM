package com.lj.business.member.dao;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.MemberLoginInfo;
import com.lj.business.member.dto.FindMemberLoginInfoPage;
import com.lj.business.member.dto.FindMemberLoginInfoPageReturn;
import com.lj.business.member.dto.FindMemberLoginInfoReturn;

public interface IMemberLoginInfoDao {
    int deleteByPrimaryKey(String code);

    int insert(MemberLoginInfo record);

    int insertSelective(MemberLoginInfo record);

    MemberLoginInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MemberLoginInfo record);

    int updateByPrimaryKey(MemberLoginInfo record);

	List<FindMemberLoginInfoPageReturn> findMemberLoginInfoPage(
			FindMemberLoginInfoPage findMemberLoginInfoPage);

	int findMemberLoginInfoPageCount(
			FindMemberLoginInfoPage findMemberLoginInfoPage);
	
	/**
	 * 
	 *
	 * 方法说明：查询会员最后一条登录信息
	 *
	 * @param memberNo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月17日
	 *
	 */
	public FindMemberLoginInfoReturn findLastMemberLoginInfo(String memberNo);
}