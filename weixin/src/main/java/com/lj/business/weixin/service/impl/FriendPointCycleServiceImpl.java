package com.lj.business.weixin.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.weixin.dao.IFriendPointCycleDao;
import com.lj.business.weixin.domain.FriendPointCycle;
import com.lj.business.weixin.service.IFriendPointCycleService;
/**
 * 
 * 类说明：客户朋友圈提示周期
 * <p>
 * 详细描述：客户朋友圈提示周期(导购助手端)
 * @Company: 扬恩科技有限公司
 * @author 李端强
 * CreateDate: 2018年1月26日16:29:39
 */
@Service
public class FriendPointCycleServiceImpl implements IFriendPointCycleService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(FriendPointCycleServiceImpl.class);

    @Resource
	private IFriendPointCycleDao friendPointCycleDao;
	
    public int deleteByPrimaryKey(String code) {
    	return friendPointCycleDao.deleteByPrimaryKey(code);
    }

    public int insert(FriendPointCycle record) {
    	return friendPointCycleDao.insert(record);
    }

    public int insertSelective(FriendPointCycle record){
    	return friendPointCycleDao.insertSelective(record);
    }

    public FriendPointCycle selectByPrimaryKey(String code){
    	return friendPointCycleDao.selectByPrimaryKey(code);
    }
    /**
     * 根据客户编号查询朋友圈提示周期
     * @param memberNo
     * @return
     * @author 李端强
     */
    public FriendPointCycle selectByMemberNo(String memberNo){
    	return friendPointCycleDao.selectByMemberNo(memberNo);
    }
    /**
     * 根据客户编号修改朋友圈提示周期
     * @param memberNo
     * @return
     * @author 李端强
     */
    public int updateByMemberNo(FriendPointCycle record){
		logger.debug("updateByMemberNo(FriendPointCycle={}) - start", record);
    	AssertUtils.notNull(record.getMemberNo(), "客户编号不能为空");
    	AssertUtils.notNull(record.getCycle(), "朋友圈提示周期不能为空");
    	FriendPointCycle friendPoint  = friendPointCycleDao.selectByMemberNo(record.getMemberNo());
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR,record.getCycle());//下次提示时间
    	if(friendPoint!=null) {//修改
    		friendPoint.setCycle(record.getCycle());//提示周期
    		friendPoint.setMemberNo(record.getMemberNo());//客户编号
    		friendPoint.setLastTime(getDayBeginTime(new Date()));//上次时间
    		friendPoint.setNextTime(getDayBeginTime(cal.getTime()));//下次时间
			int returnint = friendPointCycleDao.updateByPrimaryKeySelective(friendPoint);
			logger.debug("updateByMemberNo(FriendPointCycle) - end");
    		return returnint;//根据主键修改
    	}else {//新建
    		record.setCode(GUID.generateByUUID());
    		record.setCreateDate(new Date());//创建时间
    		record.setLastTime(getDayBeginTime(new Date()));//上次时间
    		record.setNextTime(getDayBeginTime(cal.getTime()));//下次时间
			int returnint = friendPointCycleDao.insert(record);
			logger.debug("updateByMemberNo(FriendPointCycle) - end");
    		return returnint;
    	}
    }
    
    /**
     * 忽略时分秒
     * @param date
     * @return
     * @throws Exception
     */
    private static Date getDayBeginTime(Date date){
		try {
			String formatDate = DateUtils.formatDate(date, "yyyy-MM-dd");
			return DateUtils.parseDate(formatDate, "yyyy-MM-dd");
		} catch (ParseException e) {
			return new Date();
		}
	}

    public int updateByPrimaryKey(FriendPointCycle record){
    	return friendPointCycleDao.updateByPrimaryKey(record);
    }
}