package com.lj.business.st.service.impl.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.st.dao.ICountAddFriendsDao;
import com.lj.business.st.domain.CountAddFriendsEntity;
import com.lj.cc.clientintf.IJob;

@Service
public class CountAddFriendsJobService implements IJob{

    private static final Logger logger = LoggerFactory.getLogger(CountAddFriendsJobService.class);

	@Resource
	private  IAddFriendsService addFriendsService;
	@Resource
	private  ICountAddFriendsDao countAddFriendsJobDao;
	
	@Override
	public void runJob() {
		this.triggerCountAddFriendsJob();
		
	}
	
	
	public synchronized void triggerCountAddFriendsJob()throws TsfaServiceException{
		        //远程调用 member服务，查询统计数据
				List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> list = new ArrayList<>();
				try {
					//先看今天有没统计过，如果统计过放弃本次任务
					List<CountAddFriendsEntity>  lastList = countAddFriendsJobDao.getLastDay();
					
					
					//如果是第一次执行任务，需要把最近3个月的数据先跑一次出来
					Integer count = countAddFriendsJobDao.getAllDataCountNum("1"); //1按商户查询
					Calendar calendar = Calendar.getInstance(); //得到日历  
					calendar.add(calendar.DATE, -1);
				    Date endDate =calendar.getTime();
				    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式 
				    String defaultEndDate = sdf.format(endDate);
				    
				    
				    if(lastList != null && lastList.size() > 0) {
				    	System.out.println("当天已统计"+defaultEndDate);
						return;
					}
				    
				    //*****************************按商户统计***********************
				    //第一次执行
					if( count == 0) {
						  Date dNow = new Date();   //当前时间  
					      Date dBefore = new Date();
					      
					      calendar.setTime(dNow);//把当前时间赋给日历  
					      calendar.add(Calendar.MONTH, -3);  //设置为前3月  
					      dBefore = calendar.getTime();   //得到前3月的时间  
					       
					      String defaultStartDate = sdf.format(dBefore);
					      
					      System.out.println("三个月之前时间======="+defaultStartDate); 
					      
                          for(int i =1 ;i < 93; i++) {
                        	  System.out.println("日期循环"+defaultStartDate+ ":" +defaultEndDate); 
                        	  if(defaultStartDate.equals(defaultEndDate)) {
                        		  break;
                        	  }
                        	  Calendar   calendar2   =   new   GregorianCalendar(); 
                        	  calendar2.setTime(dBefore); 
                        	  calendar2.add(Calendar.DATE,i);//把日期往后增加一天.正数往后推,负数往前推 
                        	  Date date2=calendar2.getTime();   //这个时间就是日期往后推一天的结果
                        	    
                              
                        	  defaultStartDate = sdf.format(date2);    //格式化前3月的时间  
                        	  System.out.println("统计日期"+defaultStartDate);
                        	  list = addFriendsService.countAddFriendsByDate(defaultStartDate, "1");
                        	//保存统计数据
          					countAddFriendsJobDao.insertList(list);
                          }
                          
						
					}else {
						System.out.println("开始统计昨天数据"+defaultEndDate);
						//正常每日备份
						list = addFriendsService.countAddFriendsByDate(defaultEndDate, "1");
						//保存统计数据
						countAddFriendsJobDao.insertList(list);
					}
					
					
					//如果是第一次执行任务，需要把最近3个月的数据先跑一次出来
					Integer wxCount = countAddFriendsJobDao.getAllDataCountNum("2");  //2按微信查询
					
					//****************************按微信统计**********************
					//第一次执行
					if( wxCount == 0) {
						  Date dNow = new Date();   //当前时间  
					      Date dBefore = new Date();
					      
					      calendar.setTime(dNow);//把当前时间赋给日历  
					      calendar.add(Calendar.MONTH, -3);  //设置为前3月  
					      dBefore = calendar.getTime();   //得到前3月的时间  
					       
					      String defaultStartDate = sdf.format(dBefore);
					      
					      System.out.println("三个月之前时间======="+defaultStartDate); 
					      
                          for(int i =1 ;i < 93; i++) {
                        	  System.out.println("日期循环"+defaultStartDate+ ":" +defaultEndDate); 
                        	  if(defaultStartDate.equals(defaultEndDate)) {
                        		  break;
                        	  }
                        	  Calendar   calendar2   =   new   GregorianCalendar(); 
                        	  calendar2.setTime(dBefore); 
                        	  calendar2.add(Calendar.DATE,i);//把日期往后增加一天.正数往后推,负数往前推 
                        	  Date date2=calendar2.getTime();   //这个时间就是日期往后推一天的结果
                        	    
                              
                        	  defaultStartDate = sdf.format(date2);    //格式化前3月的时间  
                        	  System.out.println("统计日期"+defaultStartDate);
                        	  list = addFriendsService.countAddFriendsByDate(defaultStartDate, "2");
                        	//保存统计数据
          					countAddFriendsJobDao.insertList(list);
                          }
                          
                          
						
					}else {
						//正常每日备份
						list = addFriendsService.countAddFriendsByDate(defaultEndDate, "2");
						//保存统计数据
						countAddFriendsJobDao.insertList(list);
					}
					
					
					
					
				} catch (Exception e) {
					// 远程调用统计数据失败
		            e.printStackTrace();
		            logger.debug("CountAddFriendsJobService:远程调用统计数据失败");
				}
				
	}
	
	
}
