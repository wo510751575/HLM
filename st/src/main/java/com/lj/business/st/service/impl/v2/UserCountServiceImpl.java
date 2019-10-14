package com.lj.business.st.service.impl.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dao.IUserCountDao;
import com.lj.business.st.dto.FindUserCountPage;
import com.lj.business.st.dto.FindUserCountPageReturn;
import com.lj.business.st.dto.UserCount;
import com.lj.business.st.service.IUserCountService;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年5月30日下午5:32:47
 */
@Service
public class UserCountServiceImpl implements IUserCountService {

	private static final Logger logger = LoggerFactory.getLogger(UserCountServiceImpl.class);
	
	@Resource
	private IUserCountDao userCountDao;
	
	@Override
	public Page<FindUserCountPageReturn> findUserCountPage(FindUserCountPage findUserCountPage)
			throws TsfaServiceException {
		logger.info("findUserCountPage(FindUserCountPage findUserCountPage = {})", findUserCountPage);
		List<FindUserCountPageReturn> list = new ArrayList<>();
		int count = 0;
		try {
			count = userCountDao.findUserCount(findUserCountPage);
			if(count>0){
				list = userCountDao.findUserCountList(findUserCountPage);
			}
			
		} catch (Exception e) {
			logger.error("查询用户统计信息错误!", e);
		}
		Page<FindUserCountPageReturn> returnPage = new Page<FindUserCountPageReturn>(list, count,findUserCountPage);
		return returnPage;
	}

	
	
	@Override
	public int findCount() {
		int count = userCountDao.findCount();
		return count;
	}


	@Override
	public void addUserCount(UserCount userCount) {
		userCountDao.addUserCount(userCount);
	}


	
	@Override
	public int findTotalMember(Map map) throws TsfaServiceException {
		int count = userCountDao.findTotalMember(map);
		return count;
	}



	
	@Override
	public int findTotalMemberPhone(Map map) {
		int count = userCountDao.findTotalMemberPhone(map);
		return count;
	}

}
