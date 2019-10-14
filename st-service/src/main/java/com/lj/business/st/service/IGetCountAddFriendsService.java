package com.lj.business.st.service;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.domain.CountAddFriendsEntity;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportGmReturn;

/**
 * 
 * 
 * 类说明：远程获取新增客户统计的总数   接口类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 龚文学
 *   
 * CreateDate: 2018年12月19日
 */
public interface IGetCountAddFriendsService {
	
	/**
	 * 
	 * 方法说明：根据传递的 时间字符串，动态的获取 每天新增客户的总记录数
	 *
	 * @param intervalTime:昨天，7天，30天
	 * @param startTime:开始时间；endTime：结束时间 （intervalTime和startTime不能同存）
	 * @param merchantNo:商户编号
	 *
	 * @author 龚文学  CreateDate: 2018年12月19日
	 */
	public List<CountAddFriendsEntity> getCountByTime (String intervalTime, String startTime, String endTime, String merchantNo , String noWx, String type) ;
	
}
