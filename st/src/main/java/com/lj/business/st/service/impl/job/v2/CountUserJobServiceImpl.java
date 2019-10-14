package com.lj.business.st.service.impl.job.v2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.member.dto.FindPersonMemberPage;
import com.lj.business.member.dto.FindPersonMemberPageReturn;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.st.dto.UserCount;
import com.lj.business.st.service.IUserCountService;
import com.lj.cc.clientintf.IJob;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.ISystemService;

/**
 * 类说明: 统计员工加粉
 *@author 贾光朝
 *@createDate 2019年5月30日上午11:02:29
 */
@Service
public class CountUserJobServiceImpl implements IJob {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CountUserJobServiceImpl.class);
	
	private DateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化一下时间
	
	@Resource
	private ISystemService systemService;
	@Resource
	private IPersonMemberService personMemberService;
	@Resource
	private IUserCountService userCountService;
	
	
	@Override
	public void runJob() {
		try {
			Date dNow = new Date(); //当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); //得到日历
			calendar.setTime(dNow);//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -1); //设置为前一天
			dBefore = calendar.getTime(); //得到前一天的时间
			String startDate = dateFmt.format(dBefore); //格式化前一天
			startDate = startDate.substring(0,10)+" 00:00:00";
			String endDate = startDate.substring(0,10)+" 23:59:59";
			
			User user = new User();
			user.setDelFlag("0");
			List<User> list = systemService.findAllList(user);
			//如果初次执行,需要把之前的认领数据插入统计表中
			int count = userCountService.findCount();
			if(count==0){
				for (User userTemp : list) {
					FindPersonMemberPage findPersonMemberPage = new FindPersonMemberPage();
					findPersonMemberPage.setMemberNoGm(userTemp.getId());
					findPersonMemberPage.setEndTime(DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
					List<FindPersonMemberPageReturn> memberList = personMemberService.findPersonMemberList(findPersonMemberPage);
					for (FindPersonMemberPageReturn findPersonMemberPageReturn : memberList) {
						UserCount userCount = new UserCount();
						userCount.setCode(GUID.generateByUUID());
						userCount.setUserId(userTemp.getId());
						userCount.setCompany(userTemp.getCompany().getName());
						userCount.setOffice(userTemp.getOffice().getName());
						userCount.setShopWx(findPersonMemberPageReturn.getShopWx());
						userCount.setMerchantNo(findPersonMemberPageReturn.getMerchantNo());
						userCount.setName(userTemp.getName());
						userCount.setCreateTime(DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
						userCount.setTotalMember(1);
						userCount.setNewMember(1);
						userCount.setRemark(findPersonMemberPageReturn.getMemberNo());
						
						
						userCountService.addUserCount(userCount);
					}
				}
			}else{
				//把前一天认领的客户信息插入统计表
				for (User userTemp : list) {
					FindPersonMemberPage findPersonMemberPage = new FindPersonMemberPage();
					findPersonMemberPage.setMemberNoGm(userTemp.getId());
					findPersonMemberPage.setStartTime(DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
					findPersonMemberPage.setEndTime(DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
					List<FindPersonMemberPageReturn> memberList = personMemberService.findPersonMemberList(findPersonMemberPage);
					for (FindPersonMemberPageReturn findPersonMemberPageReturn : memberList) {
						UserCount userCount = new UserCount();
						userCount.setCode(GUID.generateByUUID());
						userCount.setUserId(userTemp.getId());
						userCount.setCompany(userTemp.getCompany().getName());
						userCount.setOffice(userTemp.getOffice().getName());
						userCount.setShopWx(findPersonMemberPageReturn.getShopWx());
						userCount.setMerchantNo(findPersonMemberPageReturn.getMerchantNo());
						userCount.setName(userTemp.getName());
						userCount.setCreateTime(DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
						userCount.setTotalMember(1);
						userCount.setNewMember(1);
						userCount.setRemark(findPersonMemberPageReturn.getMemberNo());
						
						userCountService.addUserCount(userCount);
					}
				}
			}
		} catch (Exception e) {
			logger.error("统计客户信息错误!", e);
		}
	}


}
