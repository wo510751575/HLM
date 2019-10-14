package com.lj.business.st.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.StringUtils;
import com.lj.business.st.dao.ICountAddFriendsDao;
import com.lj.business.st.domain.CountAddFriendsEntity;
import com.lj.business.st.service.IGetCountAddFriendsService;

@Service
public class GetCountAddFriendsServiceImpl implements IGetCountAddFriendsService {
	
	private static final Logger logger = LoggerFactory.getLogger(GetCountAddFriendsServiceImpl.class);

	@Resource
	private ICountAddFriendsDao countAddFriendsDao;
	
	@Override
	public List<CountAddFriendsEntity> getCountByTime(String intervalTime,String startTime,
			String endTime,String merchantNo, String noWx, String type) {

		System.out.println(intervalTime + startTime +"="+endTime);
		List<CountAddFriendsEntity> entities = new ArrayList<>();
		//查询按商户统计
		if(type.equals("1")) {
			if (StringUtils.isNotEmpty(intervalTime)) {
				//说明选择的是昨天，或7天，或30天
				if (intervalTime.equals("interval7")) {
					entities = countAddFriendsDao.getInterval7(merchantNo, "1", null);
				}else{
					entities = countAddFriendsDao.getInterval30(merchantNo, "1", null);
				}
			}else {
				//选择的是开始-结束时间，可能只有开始时间，只有开始时间时就只查具体的这一天
				if (StringUtils.isNotEmpty(startTime)&&StringUtils.isNotEmpty(endTime)) {
					entities = countAddFriendsDao.getStartAndEndTime(startTime,endTime,merchantNo, "1", null);
				}else if(StringUtils.isNotEmpty(startTime)) {
					entities = countAddFriendsDao.getOneDay(startTime,merchantNo, "1", null);
				}else {
					//参数错误
				}
			}
		}
		
		//查询按微信统计
		if(type.equals("2")) {
			if (StringUtils.isNotEmpty(intervalTime)) {
				//说明选择的是昨天，或7天，或30天
				if (intervalTime.equals("interval7")) {
					entities = countAddFriendsDao.getInterval7(merchantNo, "2", noWx);
				}else{
					entities = countAddFriendsDao.getInterval30(merchantNo, "2", noWx);
				}
			}else {
				//选择的是开始-结束时间，可能只有开始时间，只有开始时间时就只查具体的这一天
				if (StringUtils.isNotEmpty(startTime)&&StringUtils.isNotEmpty(endTime)) {
					entities = countAddFriendsDao.getStartAndEndTime(startTime,endTime,merchantNo, "2",noWx);
				}else if(StringUtils.isNotEmpty(startTime)) {
					entities = countAddFriendsDao.getOneDay(startTime,merchantNo, "2", noWx);
				}else {
					//参数错误
				}
			}
		}
		
		return entities;
	}

}
