package com.lj.business.cf.service.impl.job.unfinish;

import java.util.Date;
import java.util.Map;

import com.lj.business.cf.dto.FindWorkTask;
import com.lj.business.cf.emus.TaskType;
import com.lj.business.cf.service.IWorkTaskService;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.dto.merchantParams.FindMerchantParamsReturn;
import com.lj.business.cm.service.IMerchantParamsService;

/**
 * 
 * 
 * 类说明：未完成任务提醒操作枚举类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 梅宏博
 *   
 * CreateDate: 2017年10月24日
 */
public enum UnfinishTaskRemindEnum {
	
	
	SUBMIT_TIME("submitTime", "提交时间", new IUnfinishTaskRemindOperate(){

		@Override
		public String operate(String merchantNo, String memberNoGm, String groupName, Map<String, Boolean> flag) {
			FindMerchantParams findMerchantParams = new FindMerchantParams();
			findMerchantParams.setMerchantNo(merchantNo);
			findMerchantParams.setGroupName(groupName);
			FindMerchantParamsReturn merchantParams = merchantParamsService.findMerchantParamsSelect(findMerchantParams);
			if(merchantParams != null){
				return merchantParams.getSysParamValue();
			}
			return null;
		}
		
	}),
	
	UNFINISH_NEW_CUSTOMER("unfinishNewCustomer", "未完成的新增客户量", new IUnfinishTaskRemindOperate(){

		@Override
		public String operate(String merchantNo, String memberNoGm, String groupName, Map<String, Boolean> flag) {
			int num = getUnfinishNum(merchantNo, memberNoGm, TaskType.XIN_ZENG);
			if(num <= 0){
				num = 0;
			} else {
				flag.put(PUSH_FLAG_KEY, flag.get(PUSH_FLAG_KEY) | true);
			}
			return num + "";
		}
		
	}),
	
	UNFINISH_ORDER("unfinishOrder", "未完成销售额", new IUnfinishTaskRemindOperate(){

		@Override
		public String operate(String merchantNo, String memberNoGm, String groupName, Map<String, Boolean> flag) {
			int num = getUnfinishNum(merchantNo, memberNoGm, TaskType.XIAO_SHOU) / 100;
			if(num <= 0){
				num = 0;
			} else {
				flag.put(PUSH_FLAG_KEY, flag.get(PUSH_FLAG_KEY) | true);
			}
			return num + "";
		}
		
	})
	
	;
	
	
	UnfinishTaskRemindEnum(String key, String name, IUnfinishTaskRemindOperate operator){
		this.key = key;
		this.name = name;
		this.operator = operator;
	};
	
	
	private static IMerchantParamsService merchantParamsService;
	
	private static IWorkTaskService workTaskService;
	
	public final static String PUSH_FLAG_KEY = "flag";//推送标志key
	
	public static void setService(IMerchantParamsService _merchantParamsService, IWorkTaskService _workTaskService){
		merchantParamsService = _merchantParamsService;
		workTaskService = _workTaskService;
	}
	
	private String key;
	
	private String name;
	
	private IUnfinishTaskRemindOperate operator;//回调接口
	
	/**
	 * 
	 *
	 * 方法说明：替换模板参数
	 *
	 * @param taskRemindTemp
	 * @param merchantNo
	 * @param memberNoGm
	 * @param hour
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月24日
	 *
	 */
	public String excute(String taskRemindTemp, String merchantNo, String memberNoGm, int hour, Map<String, Boolean> flag){
		String paramValue = operator.operate(merchantNo, memberNoGm, key + hour, flag);
		if (isContains(taskRemindTemp)) {
			taskRemindTemp = taskRemindTemp.replaceAll("\\$\\{" + key + "\\}", paramValue);
		}
		return taskRemindTemp;
	}
	
	/**
	 * 
	 *
	 * 方法说明：判断该模板中是否包含该参数
	 *
	 * @param taskRemindTemp
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月24日
	 *
	 */
	public boolean isContains(String taskRemindTemp){
		return taskRemindTemp.contains("${" + key + "}");
	}

	/**
	 * 
	 *
	 * 方法说明：根据导购查询该任务类型的剩余数量
	 *
	 * @param merchantNo
	 * @param memberNoGm
	 * @param taskType
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月24日
	 *
	 */
	private static int getUnfinishNum(String merchantNo, String memberNoGm, TaskType taskType){
		FindWorkTask findWorkTask = new FindWorkTask();
		findWorkTask.setMerchantNo(merchantNo);
		findWorkTask.setMemberNoGm(memberNoGm);
		findWorkTask.setTaskType(taskType.toString());
		findWorkTask.setWorkDate(new Date());
		int unfinishnum = workTaskService.findUnfinishNumByType(findWorkTask);
		return unfinishnum;
	}
	
	/**
	 * 
	 * 
	 * 类说明：回调接口
	 *  
	 * 
	 * <p>
	 * 详细描述：
	 *   
	 * @Company: 扬恩科技有限公司
	 * @author 梅宏博
	 *   
	 * CreateDate: 2017年10月30日
	 */
	private interface IUnfinishTaskRemindOperate {
		public String operate(String merchantNo, String memberNoGm, String groupName, Map<String, Boolean> flag);
	}
}
