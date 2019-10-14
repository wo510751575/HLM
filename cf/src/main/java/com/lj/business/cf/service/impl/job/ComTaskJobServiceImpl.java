package com.lj.business.cf.service.impl.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lj.business.cf.dto.comTask.FindComTask;
import com.lj.business.cf.dto.comTask.FindComTaskReturn;
import com.lj.business.cf.dto.comTask.UpdateComTask;
import com.lj.business.cf.service.IComTaskService;
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.dto.UpdatePersonMember;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.cc.clientintf.IJob;
/**
 * 
 * 
 * 类说明：初次介绍更改状态调度
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年10月28日
 */
@Service
public class ComTaskJobServiceImpl implements IJob{
	
	@Resource
	private IMerchantService merchantService; 
	
	@Resource
	private IGuidMemberService guidMemberService;
	
	@Resource
	private IComTaskService comTaskService;
	
	@Resource
	private IPersonMemberService personMemberService;

	@Override
	public void runJob() {
		FindMerchantPage findMerchantPage=new FindMerchantPage();
	    findMerchantPage.setProductType("INVITE");
		List<FindMerchantPageReturn> merchantList = merchantService.findMerchants(findMerchantPage);//获取邀约型商户
		
		for(FindMerchantPageReturn pageReturn :merchantList){
	        	FindComTask findComTask=new FindComTask();
	        	findComTask.setMerchantNo(pageReturn.getMerchantNo());
	        	List<FindComTaskReturn> list=comTaskService.queryComTaskFinish(findComTask); //获取任务Code
	        	
	        	for(FindComTaskReturn comTaskPageReturn:list){
	        		UpdateComTask updateComTask=new UpdateComTask();
	        		updateComTask.setCode(comTaskPageReturn.getCode());
	        		updateComTask.setFinish("FINISH");
	        		updateComTask.setFinishDate(new Date());
	        		comTaskService.updateComTask(updateComTask);
	        		//意向客户生产邀约  非意向生成沟通任务
	        		UpdatePersonMember updatePersonMember=new UpdatePersonMember();
	        		updatePersonMember.setFirstIntroduce("N");
	        		updatePersonMember.setMemberNo(comTaskPageReturn.getMemberNo());
	        		updatePersonMember.setMemberNoGm(comTaskPageReturn.getMemberNoGm());
	        		updatePersonMember.setMerchantNo(comTaskPageReturn.getMerchantNo());
	        		updatePersonMember.setShopNo(comTaskPageReturn.getShopNo());
	        		personMemberService.updatePersonMemberByMGM(updatePersonMember);
	        		
	        	}
	        }
		
		}
		
		
	}

