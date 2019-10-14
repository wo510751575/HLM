package com.lj.business.weixin.utils;

import java.util.Calendar;
import java.util.Date;
import com.lj.cc.domain.JobCenter;

public class JobUtils {

    private JobUtils() {
        
    }
    
    public static JobCenter getJobCenterWithDate(Date date) {
        JobCenter jc = new JobCenter();
        jc.setIsEnable("1");
        //构造corn表达式
        StringBuilder jobCalender = new StringBuilder("");
        Calendar calendar = Calendar.getInstance(); 
        
        calendar.setTime(date);//设置日历时间
        
        jobCalender.append(calendar.get(Calendar.SECOND))
        .append(" ").append(calendar.get(Calendar.MINUTE))
        .append(" ").append(calendar.get(Calendar.HOUR_OF_DAY))
        .append(" ").append(calendar.get(Calendar.DATE))
        .append(" ").append(calendar.get(Calendar.MONTH) + 1)
        .append(" ?")
        .append(" ").append(calendar.get(Calendar.YEAR));
        jc.setJobCalender(jobCalender.toString());//0 47 11 25 12 ? 2017
        
        return jc;
    }
    
}
