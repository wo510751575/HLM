/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dao;

import org.apache.ibatis.annotations.Param;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.PersonCarInfo;
import com.lj.business.member.dto.FindPersonCarInfoReturn;
import com.lj.business.member.dto.FindPersonMemberPageReturn;


/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年10月17日
 */
public interface IPersonCarInfoDao {
	
	int deleteByPrimaryKey(String code);

    int insert(PersonCarInfo record);

    int insertSelective(PersonCarInfo record);

    PersonCarInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PersonCarInfo record);
    
    int updateByMemberSelective(PersonCarInfo record);

    int updateByPrimaryKey(PersonCarInfo record);

    /**
	 * 方法说明：通过客户号和导购号查找车辆信息.
	 *
	 * @param findMemberPersonCarInfo
	 *            the find person car info
	 * @return the find person car info return
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭俊霖 CreateDate: 2017年10月17日
	 */
	PersonCarInfo findMemberPersonCarInfo(@Param("memberNo")String memberNo, @Param("memberNoGm")String memberNoGm);

	/**
	 * 方法说明：通过客户号和导购号查找车辆信息.
	 *
	 * @param findPersonCarInfo
	 *            the find person car info
	 * @return the find person car info return
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 * @author 彭俊霖 CreateDate: 2017年10月17日
	 */
	FindPersonCarInfoReturn findPersonCarInfo(
			FindPersonMemberPageReturn findPersonMemberReturn);
}
